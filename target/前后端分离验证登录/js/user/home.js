$(function () {
    updateHead();
})


function updateHead() {
    $.getJSON("/user/getsession.do",function (data) {
        if(data.success){ // 说明用户已经登录了
            $("#login").css("display","none");
            $("#register").css("display","none");
            $("#exist-user-name").css("display","");
            $("#logout").css("display","");

            $("#exist-user-name").text(data.user.custNo);
        }
        else{ // 用户还未登录
            $("#login").css("display","");
            $("#register").css("display","");
            $("#exist-user-name").css("display","none");
            $("#logout").css("display","none");
        }
    })
}