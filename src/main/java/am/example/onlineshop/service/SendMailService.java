package am.example.onlineshop.service;

public interface SendMailService {

    void sendMail(String to, String subject, String content);
}
