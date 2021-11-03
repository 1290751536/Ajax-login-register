$(function () {

    $("#submit").click(function () {
        var username = $("#username").val();
        var password = $("#password").val();
        var repassword = $("#repassword").val();
        var email = $("#email").val();
        var code = $("#code").val();

        if (checkStringEmpty(username)) {
            alert('用户名不能为空');
            return;
        }
        if (checkStringEmpty(password) || checkStringEmpty(repassword)) {
            alert('密码不能为空');
            return;
        }
        if (checkStringEmpty(email)) {
            alert('邮箱不能为空');
            return;
        }
        if (password != repassword) {
            alert('两次密码不一致');
            return;
        }

        if (!checkEmail(email)) {
            alert('邮箱格式非法');
            return;
        }

        if(!checkUserName(username)){
            alert("用户名长度应该在6-20之间，并且只能包括大小写字母和数字");
            return;
        }

        if(!checkPassword(password)){
            alert("密码必须包括大小写字母与数字");
            return;
        }

        // alert('tmp')
        $.ajax({
            type: 'POST',
            url: '/user/register.do',
            data: {
                custNo: username,
                password: password,
                repassword: repassword,
                email: email
            },
            success: function (msg) {
                if (msg.success) {
                    alert('注册成功');
                    window.location.href = '/login.html';
                } else {
                    alert(msg.errMsg);
                }
            }
        })

    })

    $("#username").blur(function () {
        var username = $("#username").val();
        if (checkStringEmpty(username)) {
            $("#username-text").text("用户名不能为空");
            return;
        }

        if(!checkUserName(username)){
            $("#username-text").text("用户名长度应该在6-20之间，并且只能包括大小写字母和数字");
            return;
        }

        $.getJSON('/user/existusername.do', {custNo: username}, function (msg) {
            if(msg.success){
                $("#username-text").text("用户名可用");
            }
            else{
                $("#username-text").text(msg.errMsg);
            }
        })
    })

    $("#email").blur(function () {
        var email  = $("#email").val();
        if(checkEmail(email)){
            $("#email-text").text("邮箱可用")
        }
        else if(checkStringEmpty(email)){
            $("#email-text").text("邮箱不能为空")
        }
        else{
            $("#email-text").text("邮箱格式非法")
        }
    })
})


function checkStringEmpty(str) {
    return str == null || str.trim() == "";
}

function checkEmail(email) {
    var reg = /^[a-zA-Z0-9]\w+[@]\w+[.][\w.]+$/;
    return reg.test(email);
}

function checkUserName(username) {
    var reg = /^[a-zA-Z0-9]{6,20}$/
    return reg.test(username);
}

function checkPassword(password) {
    var reg = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[^]{1,}$/
    return reg.test(password);
}