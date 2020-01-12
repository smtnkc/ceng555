package tr.edu.iyte.applicationservice;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import javax.jms.Queue;

@SpringBootApplication
@EnableEurekaClient
public class ApplicationServiceApplication extends SpringBootServletInitializer  {

    @Bean
    public Queue queue(){
        return new ActiveMQQueue("application-queue");
    }

    public static void main(String[] args) {
        SpringApplication.run(ApplicationServiceApplication.class, args);
    }
}
