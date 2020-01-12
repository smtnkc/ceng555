package tr.edu.iyte.notificationservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Message;

@Component
public class ApplicationMessageConverter implements MessageConverter {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(ApplicationMessageConverter.class);

    ObjectMapper mapper;

    public ApplicationMessageConverter() {
        mapper = new ObjectMapper();
    }

    @Override
    public Message toMessage(Object object, Session session) throws JMSException {

        String payload = null;
        try {
            Application application = (Application) object;
            try {
                payload = mapper.writeValueAsString(application);
                LOGGER.info("Application object is converted to JSON string = '{}'", payload);
            } catch (JsonProcessingException e) {
                LOGGER.error("Application object could not be converted to JSON string");
            }
        } catch (ClassCastException e) {
            LOGGER.error("Incoming object could not be casted to Application class", e);
        }

        TextMessage message = session.createTextMessage();
        message.setText(payload);

        return message;
    }

    @Override
    public Object fromMessage(Message message) throws JMSException {

        Application application = null;
        try {
            TextMessage textMessage = (TextMessage) message;
            String payload = textMessage.getText();
            LOGGER.info("Incoming message = '{}'", payload);

            try {
                application = mapper.readValue(payload, Application.class);
            } catch (Exception e) {
                LOGGER.error("Incoming message cannot be mapped to Application class", e);
            }
        } catch (ClassCastException e) {
            LOGGER.error("Incoming object could not be casted to TextMessage class", e);
        }

        return application;
    }
}

