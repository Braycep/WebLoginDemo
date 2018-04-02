package test;

import test.sendmail.SendMail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "regServlet")
public class regServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        response.setContentType("text/html;charset=UTF-8");
        System.out.println(request.getAttribute("refer"));
        if (DBUtils.checkReg(username,password,email)){
            String authCode = "";
            for(int i = 0;i<8;i++){
                authCode += (char)((int)(Math.random()*27)+65);
            }
            //发送邮件验证码
            SendMail.sendEMail(email,authCode);
            session.setAttribute("authCode",authCode);
            session.setAttribute("username",username);
            response.sendRedirect("mailAuth.html");
        } else {
            response.sendRedirect("failed.html");
        }
    }
}
