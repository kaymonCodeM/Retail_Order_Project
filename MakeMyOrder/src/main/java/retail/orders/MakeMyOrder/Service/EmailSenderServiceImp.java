package retail.orders.MakeMyOrder.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderServiceImp implements EmailSenderService{

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public String sendEmail(String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("spam@gmail.com");
        message.setTo("spam@gmail.com");
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
        return "Message Successful";
    }
}
