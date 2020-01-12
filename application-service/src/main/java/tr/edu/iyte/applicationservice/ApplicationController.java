package tr.edu.iyte.applicationservice;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.jms.Queue;
import javax.jms.TextMessage;

import org.apache.tomcat.util.modeler.NotificationInfo;
import java.util.List;

@Controller
public class ApplicationController {

    private Queue applicationIdQueue;
    private JmsTemplate jmsTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationMessageConverter.class);

    private ApplicationService applicationService = new ApplicationService();

    @Autowired
    public ApplicationController(Queue applicationIdQueue, JmsTemplate jmsTemplate) {
        this.applicationIdQueue = applicationIdQueue;
        this.jmsTemplate = jmsTemplate;
    }

    @GetMapping("/application")
    public String getApplicationForm(Model model) {
        model.addAttribute("applicationModel", new Application()); // to use in form.html
        return "form";
    }

    @PostMapping("/application")
    public String submitApplication(Application application, Model model) {
        model.addAttribute("applicationModel", application); // to use in success.html
        applicationService.addApplication(application);

        // set message header as applicationId and add into application-queue
        jmsTemplate.convertAndSend(applicationIdQueue, application, message -> {
            message.setJMSCorrelationID(application.getApplicationId());
            return message;
        });
        LOGGER.info("Message is added into the queue");
        return "success";
    }

    @GetMapping("/applications")
    @ResponseBody
    public ApplicationList getApplicationList() {
        return applicationService.getApplicationList();
    }

    @GetMapping("/applications/{applicationId}")
    @ResponseBody
    public Application getApplication(@PathVariable String applicationId) {
        return applicationService.getApplication(applicationId);
    }
}
