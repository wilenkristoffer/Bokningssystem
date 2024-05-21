package org.example.bokningssystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;

@SpringBootApplication
public class BokningssystemApplication {

    public static void main(String[] args) {
        if (args.length == 0) {
            SpringApplication.run(BokningssystemApplication.class, args);
        } else if (Objects.equals(args[0], "FetchShippers")) {
            SpringApplication application = new SpringApplication(FetchShippers.class);
            application.setWebApplicationType(WebApplicationType.NONE);
            application.run(args);
        } else if (Objects.equals(args[0], "ContractCustomerApplication")) {
            SpringApplication application = new SpringApplication(ContractCustomerApplication.class);
            application.setWebApplicationType(WebApplicationType.NONE);
            application.run(args);
        }else if (Objects.equals(args[0], "EventApp")) {
            SpringApplication application  = new SpringApplication(EventApplication.class);
            application.setWebApplicationType(WebApplicationType.NONE);
            application.run(args);
        }
    }

}
