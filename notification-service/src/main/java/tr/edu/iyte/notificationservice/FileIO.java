package tr.edu.iyte.notificationservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class FileIO {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationMessageConverter.class);

    public static MailAuth readMailAuth() {
        String mailAuthFilePath = new File(System.getProperty("user.dir")).getParentFile().getPath() +
                "/mailAuth.json"; // This file must be kept outside of the project
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File mailAuthFile = new File(mailAuthFilePath);
            MailAuth mailAuth = objectMapper.readValue(mailAuthFile, MailAuth.class);
            return mailAuth;
        } catch (IOException e) {
            LOGGER.error("Cannot read mailAuth.json", e);
            e.printStackTrace();
            return null;
        }
    }
}
