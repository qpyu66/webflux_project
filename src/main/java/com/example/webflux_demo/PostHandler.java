package com.example.webflux_demo;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.HashMap;


@Component
public class PostHandler {
    private HashMap<Object, Object> result = new HashMap<>();
    private Mono<HashMap<Object, Object>> mapper = Mono.just(result);

    public Mono<ServerResponse> getByName(ServerRequest request) {
        result.put("to", "{name}");
        result.put("message", "hello {name}");
        mapper.subscribe( (arg)->{
            System.out.println(arg);
            System.out.println("request > "+request);
        });
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromProducer(mapper, HashMap.class));
    }


}
