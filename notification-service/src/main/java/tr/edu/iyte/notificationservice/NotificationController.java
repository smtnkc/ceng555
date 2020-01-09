package tr.edu.iyte.notificationservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private RestTemplate restTemplate;
    @RequestMapping("/email/{applicationId}")
    public String notificationMail(@PathVariable("applicationId") String applicationId){

        Application application = restTemplate.getForObject("http://localhost:8001/applications/" + applicationId,
                Application.class);

        final String username = "ceng555grad@gmail.com";
        final String password = "cdbzxudnmjnsmlqv";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); // TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("ceng555grad@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(application.getEmail())
            );
            message.setSubject("Lisansüstü Başvuru");
            message.setText("Sayın " + application.getName() +
                    ",\n\nLisansüstü başvurunuza ilişkin detaylar aşağıdadır.\n\n" +
                    "Başvuru Numarası: " + application.getApplicationId() +
                    "\nKimlik Numarası: " + application.getPersonalId() +
                    "\nAdı Soyadı: " + application.getName() +
                    "\nE-posta: " + application.getEmail() +
                    "\nALES Puanı: " + application.getAlesScore() +
                    "\nYDS Puanı: " +application.getYdsScore() +
                    "\nNot Ortalaması: " +application.getGpaScore());
            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return "Notification has been sent successfully.";
    }

    @RequestMapping("/{applicationId}")
    public Notification notificationItem (@PathVariable("applicationId") String applicationId){
        Application application = restTemplate.getForObject("http://localhost:8001/applications/" + applicationId,
                Application.class);
        return new Notification(application);
    }
}