package com.example.webflux_demo;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class example {

    //1~100 까지의 자연수 중 짝수만 출력하는 로직 검증
    @Test
    public void num2(){
        Flux flux = (Flux) Flux.range(1,100)
                .filter(i -> i%2 == 0)
                .subscribe(x -> System.out.print(x + " -> "));
//                .log();

        for (int i = 1; i < 100; i++) {
            // i%2 == 0
            StepVerifier.create(flux)
                    .expectNext(i%2 == 0)
                    .verifyComplete();
        }

    }


    //“hello”, “there” 를 순차적으로 publish하여 순서대로 나오는지 검증
    @Test
    public void num3(){

        Flux flux = Flux.just("hello","there")
                .publishOn(Schedulers.boundedElastic())
                .log();

        StepVerifier.create(flux)
                .expectNext("hello")
                .expectNext("there")
                .verifyComplete();
    }



    // 아래와 같은 객체가 전달될 때 “JOHN”, “JACK” 등 이름이 대문자로 변환되어 출력되는 로직 검증
    //Person("John", "[john@gmail.com](mailto:john@gmail.com)", "12345678")
    //Person("Jack", "[jack@gmail.com](mailto:jack@gmail.com)", "12345678")
    @Test
    public void num4(){


        Flux<String> person1 = Flux.just("John", "[john@gmail.com](mailto:john@gmail.com)", "12345678")
                .delayElements(Duration.ofSeconds(1));
        Flux<String> person2 = Flux.just("Jack", "[jack@gmail.com](mailto:jack@gmail.com)", "12345678")
                .delayElements(Duration.ofSeconds(1));


        Function<String,String> mapper = String::toUpperCase;
        Flux<String> out = person1.map(mapper);
//        System.out.println(""+out);


        StepVerifier.create(out)
                .expectNext("JOHN", "[john@gmail.com](mailto:john@gmail.com)", "12345678")
//                .expectNext("JACK")
                .verifyComplete();

    }




    //["Blenders", "Old", "Johnnie"] 와 "[Pride", "Monk", "Walker”]를 압축하여 스트림으로 처리 검증
    //result : 예상되는 스트림 결과값 ["Blenders Pride", "Old Monk", "Johnnie Walker”]
    @Test
    public void num5(){
        Flux<String> person1 = Flux.just("Blenders", "Old", "Johnnie")
                .delayElements(Duration.ofSeconds(1));
        Flux<String> person2 = Flux.just("Pride", "Monk", "Walker")
                .delayElements(Duration.ofSeconds(1));



    }




    //["google", "abc", "fb", "stackoverflow”] 의 문자열 중 5자 이상 되는 문자열만 대문자로 비동기로 치환하여
    // 1번 반복하는 스트림으로 처리하는 로직 검증
    //result :예상되는 스트림 결과값 ["GOOGLE", "STACKOVERFLOW", "GOOGLE", "STACKOVERFLOW"]
    @Test
    public void num6(){




    }




}
