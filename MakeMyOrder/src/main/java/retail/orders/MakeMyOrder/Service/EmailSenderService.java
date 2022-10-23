package retail.orders.MakeMyOrder.Service;

public interface EmailSenderService {
    String sendEmail(String toEmail,String subject,String body);
    String emailOrderDetails(String toEmail,String subject,String orderUrl);
}
