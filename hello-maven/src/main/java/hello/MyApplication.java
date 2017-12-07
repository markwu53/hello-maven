package hello;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
public class MyApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, new String[] { "Hello", "World" });
    }

    @Bean
    @Qualifier("stringBean1")
    String messageBean() {
        System.out.println("in message bean");
        return "hello";
    }

    @Bean
    @Qualifier("stringBean2")
    String messageBean2(@Qualifier("stringBean1") String message) {
        System.out.println("in message bean 2");
        System.out.println(message);
        System.out.println("in message bean 2");
        return "hello2";
    }

    @Bean
    @Qualifier("stringBean3")
    String messageBean3(@Qualifier("stringBean2") String message) {
        System.out.println("in message bean 3");
        System.out.println(message);
        System.out.println("in message bean 3");
        return "hello3";
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("In command line runner");
        System.out.println(args.length);
        for (String arg : args) {
            System.out.println(arg);
        }
        System.out.println("In command line runner");
    }

}

@Controller
class MyController {

    @RequestMapping("/hello")
    @ResponseBody
    String hello() {
        return "Hello";
    }

    @RequestMapping("/welcome")
    String welcome() {
        return "/WEB-INF/welcome.jsp";
    }

}
