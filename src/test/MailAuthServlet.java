package test;

import test.sendmail.SendMail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MailAuthServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String authCodeServer = (String)request.getSession().getAttribute("authCode");
        String authCodeClient = request.getParameter("authCode").trim();
        if (authCodeServer.equals(authCodeClient.toUpperCase())){
            String username = request.getSession().getAttribute("username")+"";
            DBUtils.updateMailAuth(username);
            response.getWriter().write(username+"的邮箱验证成功！");
        }
    }
}
