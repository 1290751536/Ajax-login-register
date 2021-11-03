package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import service.UserService;
import service.impl.implUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "ExistUserNameServlet", value = "/user/existusername.do")
public class ExistUserNameServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();

        String custNo = request.getParameter("custNo");
        if (custNo == null || "".equals(custNo)) {
            map.put("success", false);
            map.put("errMsg", "用户名不能为空");
            out.write(mapper.writeValueAsString(map));
            return;
        }

        UserService userService = new implUserService();
        if (userService.isExitUserByCustNo(custNo)) {
            map.put("success", false);
            map.put("errMsg", "用户名已经存在");
            out.write(mapper.writeValueAsString(map));
            return;
        }

        map.put("success", true);
        out.write(mapper.writeValueAsString(map));
    }
}
