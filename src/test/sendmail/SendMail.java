package test.sendmail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 发送简单的邮件
 */
public class SendMail {
    public static void main(String[] args) throws Exception{
        sendEmail("braycep@foxmail.com","1907371801@qq.com","mtyhonavcewebice","From A Method","Test",false);
    }

    public static boolean sendEMail(String to, String authCode){
        return  sendEmail("braycep@foxmail.com",to,"mtyhonavcewebice","请验证你的邮箱：",authCode,false);
    }

    public static boolean sendEmail(String from, String to, String password, String subject, String content, boolean debug){
        //发件服务器
        String host = "smtp.qq.com";

        //创建配置文件
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol","smtp");
        properties.setProperty("mail.smtp.host","smtp.qq.com");
        properties.setProperty("mail.smtp.auth","true");
        //开启SSL
        properties.setProperty("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.port","465");
        properties.setProperty("mail.smtp.socketFactory.port","465");
        //开启控制台调试
        properties.setProperty("mail.debug",debug+"");

        //认证
        Authenticator auth = new MyAuthenticator(from,password);

        //使用配置文件和认证获取Session实例
        Session session = Session.getInstance(properties, auth);

        try{
            //创建简单的消息体
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(content);

            //使用session获取邮件传输通道
            Transport transport = session.getTransport();
            //发送
            transport.send(message);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}

/**
 * 继承认证类
 */
class MyAuthenticator extends Authenticator{
    private String name;
    private String password;
    public MyAuthenticator(String name, String password){
        this.name = name;
        this.password = password;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(name,password);
    }
}