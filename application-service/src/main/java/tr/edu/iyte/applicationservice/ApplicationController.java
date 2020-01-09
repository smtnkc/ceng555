package tr.edu.iyte.applicationservice;

import org.apache.tomcat.util.modeler.NotificationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.util.List;

@Controller
public class ApplicationController {

    @Autowired
    private RestTemplate restTemplate;

    private ApplicationService applicationService = new ApplicationService();

    @GetMapping("/application")
    public String getApplicationForm(Model model) {
        model.addAttribute("applicationModel", new Application()); // to use in form.html
        return "form";
    }

    @PostMapping("/application")
    public String submitApplication(Application application, Model model) {
        model.addAttribute("applicationModel", application); // to use in success.html
        applicationService.addApplication(application);
        ResponseEntity<String> result=restTemplate.getForEntity("http://notification-service/notification/email/" +
                application.getApplicationId(),String.class);
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
