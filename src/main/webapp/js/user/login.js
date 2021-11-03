$(function () {
    $("#submit").click(function () {
        var username = $("#username").val();
        var password = $("#password").val();
        if (checkStringEmpty(username)) {
            alert("用户名不能为空");
            return;
        }
        if (checkStringEmpty(password)) {
            alert("密码不能为空");
            return;
        }

        var code = $("#code").val();

        $.ajax({
            url: '/user/login.do',
            data: {
                custNo: username,
                password: password,
                verifyCode: code
            },
            success: function (msg) {
                if (msg.success) {
                    alert("登录成功");
                    window.location.href = "/home.html";
                } else {
                    alert(msg.errMsg);
                }
            }
        })
    })
})

function checkStringEmpty(str) {
    return str == null || str.trim() == "";
}