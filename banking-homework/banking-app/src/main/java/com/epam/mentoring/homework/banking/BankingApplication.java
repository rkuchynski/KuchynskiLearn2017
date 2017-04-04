package com.epam.mentoring.homework.banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * Application entry point.
 * <p/>
 * Date: 09.03.2017
 *
 * @author Raman Kuchynski
 */
@SpringBootApplication
@ImportResource(
        "classpath:com/epam/mentoring/homework/banking/repository/embedded/banking-app-embedded-repository-context.xml"
)
public class BankingApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankingApplication.class, args);
    }

}
