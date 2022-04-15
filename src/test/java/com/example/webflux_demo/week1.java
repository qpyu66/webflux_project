package com.example.webflux_demo;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.Locale;

public class week1 {


    /** ok
     *  1. ["Blenders", "Old", "Johnnie"] 와 "[Pride", "Monk", "Walker"] 를
     *  순서대로 하나의 스트림으로 처리되는 로직 검증
     */
    @Test
    public void num1() {
        Flux<String> names1 = Flux.just("Blenders", "Old", "Johnnie")
                .delayElements(Duration.ofSeconds(1));
        Flux<String> names2 = Flux.just("Pride", "Monk", "Walker")
                .delayElements(Duration.ofSeconds(1));
        Flux<String> names = Flux.concat(names1, names2)
                .log();

        StepVerifier.create(names)
                .expectSubscription()
                .expectNext("Blenders", "Old", "Johnnie", "Pride", "Monk", "Walker")
                .verifyComplete();
    }


    /** ok
     *  2. 1~100 까지의 자연수 중 짝수만 출력하는 로직 검증
     */
    @Test
    public void num2(){
        Flux<Integer> flux = (Flux) Flux.range(1,100)
                .filter(i -> i%2 == 0)
                .log();

            StepVerifier.create(flux)
                    .expectNextCount(50)
                    .verifyComplete();
    }


    /** ok
     *  3. “hello”, “there” 를 순차적으로 publish하여 순서대로 나오는지 검증
     */
    @Test
    public void num3(){
        Flux<String> flux = Flux.just("hello","there")
                .publishOn(Schedulers.boundedElastic())
                .log();

        StepVerifier.create(flux)
                .expectNext("hello")
                .expectNext("there")
                .verifyComplete();
    }




    /** ok
     * 4. 아래와 같은 객체가 전달될 때 “JOHN”, “JACK” 등 이름이 대문자로 변환되어 출력되는 로직 검증
     *    Person("John", "[john@gmail.com](mailto:john@gmail.com)", "12345678")
     *    Person("Jack", "[jack@gmail.com](mailto:jack@gmail.com)", "12345678")
     */
    @Test
    public void num4(){

        class Person{
            String name;
            String email;
            String phone;

            public Person(String name, String email, String phone) {
                this.name = name;
                this.email = email;
                this.phone = phone;
            }

            public String getName() {
                return name;
            }

        }

        Person person1 = new Person("John", "[john@gmail.com](mailto:john@gmail.com)", "12345678");
        Person person2 = new Person("Jack", "[jack@gmail.com](mailto:jack@gmail.com)", "12345678");

        Flux<String> names =  Flux.just(person1,person2)
                    .map(p -> p.getName().toUpperCase(Locale.ROOT))
                    .log();

        StepVerifier.create(names)
                .expectNext("JOHN")
                .expectNext("JACK")
                .verifyComplete();
    }



    /** ok
     * 5. ["Blenders", "Old", "Johnnie"] 와 "[Pride", "Monk", "Walker”]를 압축하여 스트림으로 처리 검증
     *    result : 예상되는 스트림 결과값 ["Blenders Pride", "Old Monk", "Johnnie Walker”]
     */
    @Test
    public void num5(){
        Flux<String> person1 = Flux.just("Blenders", "Old", "Johnnie")
                .delayElements(Duration.ofSeconds(1));
        Flux<String> person2 = Flux.just("Pride", "Monk", "Walker")
                .delayElements(Duration.ofSeconds(1));

        Flux<String> names = Flux.zip(person1, person2, (p1, p2) -> p1 + " " + p2)
                        .log();

        StepVerifier.create(names)
                .expectSubscription()
                .expectNext("Blenders Pride", "Old Monk", "Johnnie Walker")
                .verifyComplete();
    }



    /** ok
     * 6. ["google", "abc", "fb", "stackoverflow”] 의 문자열 중 5자 이상 되는 문자열만 대문자로 비동기로 치환하여
     *    1번 반복하는 스트림으로 처리하는 로직 검증
     *    result :예상되는 스트림 결과값 ["GOOGLE", "STACKOVERFLOW", "GOOGLE", "STACKOVERFLOW"]
     */
    @Test
    public void num6(){

        Flux<String> person = Flux.just("google", "abc", "fb", "stackoverflow");

        Flux<String> result = person.filter( str-> str.length() >=5 )
                .flatMap(p -> Flux.just(p.toUpperCase()))
                .publishOn(Schedulers.boundedElastic())
                .repeat(1)
                .log();

        StepVerifier.create(result)
                .expectSubscription()
                .expectNext("GOOGLE", "STACKOVERFLOW", "GOOGLE", "STACKOVERFLOW")
                .verifyComplete();
    }




}
