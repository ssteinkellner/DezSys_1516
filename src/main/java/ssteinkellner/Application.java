package ssteinkellner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sun.net.httpserver.HttpServer;

/**
 * @author SSteinkellner
 * @version 2016.03.18
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
//        HttpServer server = JdkHttpServerFactory.createHttpServer( URI.create( "http://localhost:8080/rest" ), rc);
    }
}
