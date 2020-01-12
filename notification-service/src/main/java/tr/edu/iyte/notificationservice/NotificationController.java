package tr.edu.iyte.notificationservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@Component
@EnableJms
@RequestMapping("/notification")
public class NotificationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationMessageConverter.class);

    private Queue notificationResponseQueue;
    private JmsTemplate jmsTemplate;
    private RestTemplate restTemplate;

    @Autowired
    public NotificationController(Queue notificationResponseQueue, JmsTemplate jmsTemplate, RestTemplate restTemplate) {
        this.notificationResponseQueue = notificationResponseQueue;
        this.jmsTemplate = jmsTemplate;
        this.restTemplate = restTemplate;
    }

    @JmsListener(destination = "application-queue")
    public void sendNotification(Application application){

        String applicationId = application.getApplicationId();
        LOGGER.info("Application [{}] received.", applicationId);
        AtomicReference<String> responseText = new AtomicReference<>("");

        if(mailSender(application)) {
            responseText.set("Notification [" + applicationId + "] successful.");
        } else {
            responseText.set("Notification [" + applicationId + "] failed.");
        }

        jmsTemplate.send("notification-response-queue", messageCreator -> {
            TextMessage textMessage = messageCreator.createTextMessage(responseText.get());
            textMessage.setJMSCorrelationID(applicationId);
            return textMessage;
        });

        LOGGER.info(responseText.get());
    }

    public boolean mailSender(Application application) {
        final String username = "ceng555grad@gmail.com";
        final String password = "cdbzxudnmjnsmlqv";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

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
            message.setText("Sayın " + application.getName() + "," +
                    "\n\nLisansüstü başvurunuza ilişkin detaylar aşağıdadır.\n" +
                    "\nBaşvuru Numarası: " + application.getApplicationId() +
                    "\nKimlik Numarası: " + application.getPersonalId() +
                    "\nAdı Soyadı: " + application.getName() +
                    "\nE-posta: " + application.getEmail() +
                    "\nALES Puanı: " + application.getAlesScore() +
                    "\nYDS Puanı: " + application.getYdsScore() +
                    "\nNot Ortalaması: " + application.getGpaScore());
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("/{applicationId}")
    public Notification getNotification(@PathVariable("applicationId") String applicationId){
        Application application = restTemplate.getForObject("http://application-service/applications/" +
                applicationId, Application.class);
        return new Notification(application);
    }
}
