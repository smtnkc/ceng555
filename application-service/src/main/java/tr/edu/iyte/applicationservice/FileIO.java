package tr.edu.iyte.applicationservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;

public class FileIO {
    public static ApplicationList readApplicationList() {
        String applicationListFilePath = new File(System.getProperty("user.dir")).getParentFile().getPath() +
                "/applicationList.json"; // This file must be kept outside of the project
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File applicationListFile = new File(applicationListFilePath);
            ApplicationList applicationList = objectMapper.readValue(applicationListFile, ApplicationList.class);
            return applicationList;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void updateApplicationList(ApplicationList applicationList) {

        String applicationListFilePath = new File(System.getProperty("user.dir")).getParentFile().getPath() +
                "/applicationList.json"; // This file must be kept outside of the project
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File applicationListFile = new File(applicationListFilePath);
            objectMapper.writeValue(applicationListFile, applicationList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
