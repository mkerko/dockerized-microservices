package com.test.warehouse;

import com.test.warehouse.service.RabbitService;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.test.warehouse")
@Log4j2
public class Application {

    private static RabbitService rabbitService = new RabbitService();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        try {
            rabbitService.receive();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
