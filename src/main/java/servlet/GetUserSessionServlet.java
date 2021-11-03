package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.Session;
import domain.UserDomain;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "GetUserSessionServlet", value = "/user/getsession.do")
public class GetUserSessionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> mp = new HashMap<>();

        HttpSession session = request.getSession();
        UserDomain userDomain = (UserDomain) session.getAttribute("user");
        if (userDomain == null) {
            mp.put("success", false);
        } else {
            mp.put("success", true);
            mp.put("user", userDomain);
        }
        out.write(mapper.writeValueAsString(mp));
    }
}
