package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.UserDomain;
import service.UserService;
import service.impl.implUserService;
import util.CodeUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "RegisterServlet", value = "/user/register.do")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();

        /*if(!CodeUtil.checkVerifyCode(request)){
            map.put("success",false);
            map.put("errMsg","验证码错误");
            out.write(mapper.writeValueAsString(map));
            return;
        }*/

        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        if(!password.equals(repassword)){
            map.put("success",false);
            map.put("errMsg","两次密码不一致");
            out.write(mapper.writeValueAsString(map));
            return;
        }

        String custNo = request.getParameter("custNo");
        String email = request.getParameter("email");

        UserDomain userDomain = new UserDomain();
        userDomain.setCustNo(custNo);
        userDomain.setCustPwd(password);
        userDomain.setEmail(email);

        UserService userService  = new implUserService();
        if(userService.insertUser(userDomain) == 0){
            map.put("success",false);
            map.put("errMsg","该用户名已被注册");
            out.write(mapper.writeValueAsString(map));
            return;
        }

        map.put("success",true);
        out.write(mapper.writeValueAsString(map));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
