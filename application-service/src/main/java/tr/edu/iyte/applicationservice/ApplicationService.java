package tr.edu.iyte.applicationservice;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ApplicationService {

    FileIO fileIO = new FileIO();

    private ApplicationList applicationList = fileIO.readApplicationList();

    public ApplicationList getApplicationList() { return fileIO.readApplicationList(); }

    public Application getApplication(String applicationId) {
        return fileIO.readApplicationList().getApplication(applicationId);
    }

    public void setApplicationList(List<Application> applications) {
        this.applicationList.setApplicationList(applications);
        fileIO.updateApplicationList(this.applicationList);
    }

    public void addApplication(Application application) {
        this.applicationList.addApplication(application);
        fileIO.updateApplicationList(this.applicationList);
    }
}
