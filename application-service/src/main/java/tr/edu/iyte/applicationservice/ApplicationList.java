package tr.edu.iyte.applicationservice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApplicationList {

    private List<Application> applications = new ArrayList<>(Arrays.asList());

    public ApplicationList() {
    }

    public ApplicationList(List<Application> applications) {
        this.applications = applications;
    }

    public List<Application> getApplicationList() {
        return applications;
    }

    public void setApplicationList(List<Application> applications) {
        this.applications = applications;
    }

    public void addApplication(Application application) {
        this.applications.add(application);
    }

    public Application getApplication(String applicationId) {
        return applications.stream().filter(a -> a.getApplicationId().equals(applicationId)).findFirst().get();
    }
}