package xyz.zeroherox.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);

    }


    @RestController
    public static class TestController {

        @RequestMapping("/")
        public String hello() {
            return "Hello World!、、、";
        }

    }

}
