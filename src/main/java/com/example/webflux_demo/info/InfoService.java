package com.example.webflux_demo.info;

import com.example.webflux_demo.dto.Message;
import com.google.common.net.HttpHeaders;
import org.assertj.core.annotations.Beta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.reactive.function.server.MockServerRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@Configuration
public class InfoService {

//    WebClient client = WebClient.create("http://localhost:8081");

    WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:8081")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();



    public Flux<Message> findAll() {
        return webClient.get()
                .uri("/hello")
                .retrieve()
                .bodyToFlux(Message.class);
    }


//    Mono<Message> messageMono = ... ;
//
//    Mono<Void> result2 = webClient.post()
//            .uri("/persons/{id}", id)
//            .contentType(MediaType.APPLICATION_JSON)
//            .body(messageMono, Message.class)
//            .retrieve()
//            .bodyToMono(Void.class);


//    public Mono<Message> getJob(int id) {
//        return webClient.get().uri("/hello/{name}", id).retrieve().bodyToMono(Message.class);
//    }


//    public Mono<Message> someRestCall(String job) {
//        return this.webClient.get().uri("/hello", job)
//                .retrieve().bodyToMono(Message.class);
//    }


}
