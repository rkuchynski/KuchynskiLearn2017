package com.epam.mentoring.homework.banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
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
        "classpath:com/epam/mentoring/homework/banking/repository/embedded/banking-app-embedded-repository-context.xml")
public class BankingApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(BankingApplication.class);
    }

    /**
     * Spring boot entry point method.
     * @param args application args.
     */
    public static void main(String[] args) {
        SpringApplication.run(BankingApplication.class, args);
    }

}
