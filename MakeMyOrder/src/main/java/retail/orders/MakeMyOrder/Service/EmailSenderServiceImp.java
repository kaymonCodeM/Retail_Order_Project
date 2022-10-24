package retail.orders.MakeMyOrder.Service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class EmailSenderServiceImp implements EmailSenderService{

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Logger log;

    @Override
    public String sendEmail(String toEmail,String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("my.springboot.email@gmail.com");
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
        return "Message Successful to: " + toEmail;
    }

    @Override
    public String emailOrderDetails(String toEmail,String subject,String orderUrl){
        try {
            String body = Files.readString(Path.of(orderUrl));
            log.debug(sendEmail(toEmail,subject,body));
        }catch (Exception e){
            log.debug("Email to "+ toEmail + " was not sent");
            return "ERROR email order was not successful log" + e;
        }
        log.debug("Order summery to: " + toEmail+ "was successful");
        return "Email order details successfully";
    }
}
