package com.group.stars;

import com.group.stars.consumer.FeedMeConsumer;
import com.group.stars.transformer.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;

@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Autowired
    private FeedMeConsumer feedMeConsumer;

    @Bean
    public Transformer transformer() {

        return new Transformer(new HashMap<>(), new HashMap<>());    }



    @Bean
    public BufferedReader bufferedReader(@Value("${tcp.server}") String host, @Value("${tcp.port}") int port)
            throws IOException {
        Socket socket = new Socket(host, port);
        return new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run(String... args) throws Exception {
        feedMeConsumer.read();

    }
}
