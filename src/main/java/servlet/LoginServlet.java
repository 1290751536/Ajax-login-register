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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "LoginServlet", value = "/user/login.do")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();

        String custNo = request.getParameter("custNo");
        String password = request.getParameter("password");

        if(!CodeUtil.checkVerifyCode(request)){
            map.put("success",false);
            map.put("errMsg","验证码错误");
            out.write(mapper.writeValueAsString(map));
            return;
        }

        if (checkStringEmpty(custNo) || checkStringEmpty(password)) {
            map.put("success", false);
            map.put("errMsg", "用户名以及密码不能为空");
            out.write(mapper.writeValueAsString(map));
            return;
        }

        UserService userService = new implUserService();
        List<UserDomain> user = userService.queryUser(custNo, password);
        if (user.size() == 0) {
            map.put("success", false);
            map.put("errMsg", "登录失败用户不存在");
            out.write(mapper.writeValueAsString(map));
        } else {
            map.put("success", true);
            map.put("errMsg", "登录成功");
            HttpSession session = request.getSession();
            session.setAttribute("user", user.get(0));
            out.write(mapper.writeValueAsString(map));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private boolean checkStringEmpty(String str) {
        return str == null || "".equals(str.trim());
    }
}
