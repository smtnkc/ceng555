package tr.edu.iyte.applicationservice;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ApplicationService {

    private List<Application> dummyApplications = new ArrayList<>(Arrays.asList(
            new Application("10000", "11111111111", "Samet Tenekeci",
                    "samettenekeci@gmail.com", 82.4, 80.0, 85.0),
            new Application("20000", "22222222222", "Hüseyin Ünlü",
                    "huseyin.unlu@outlook.com", 78.5, 92.0, 95.2)
    ));

    private ApplicationList applicationList = new ApplicationList(dummyApplications);

    public ApplicationList getApplicationList() {
        return applicationList;
    }

    public Application getApplication(String applicationId) {
        return applicationList.getApplication(applicationId);
    }

    public void setApplicationList(List<Application> applications) {
        this.applicationList.setApplicationList(applications);
    }

    public void addApplication(Application application) {
        this.applicationList.addApplication(application);
    }
}