package me.code.webservicesrestfulapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class WebServicesRestfulAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebServicesRestfulAppApplication.class, args);
    }

}
