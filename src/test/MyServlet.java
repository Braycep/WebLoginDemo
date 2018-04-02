package test;

import test.sendmail.SendMail;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        response.setContentType("text/html;charset=UTF-8");
        if (DBUtils.checkLogin(username,password)){
            System.out.println(DBUtils.query(username,"mailAuth"));
            if (DBUtils.query(username,"mailAuth").equals(0)){
                HttpSession session = request.getSession();
                session.setAttribute("username",username);
                String authCode = "";
                for(int i = 0;i<8;i++){
                    authCode += (char)((int)(Math.random()*27)+65);
                }
                session.setAttribute("authCode",authCode);
                SendMail.sendEMail(DBUtils.query(username,"email")+"".trim(),authCode);
                response.sendRedirect("mailAuth.html");
            }else {
                response.getWriter().write("登陆成功！");
            }
        } else {
            response.getWriter().write("登陆失败！");
        }
    }
}
