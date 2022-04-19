# 빗썸 테크 아카데미 & 코드스테이츠

   
## 과제   
- [1주차 과제 - Webflux 예제](https://github.com/qpyu66/webflux_project/blob/master/src/test/java/com/example/webflux_demo/week1.java)
- [2주차 과제(1) - Resr API 에 반응하는 local 웹서버 앱 구축](https://github.com/qpyu66/webflux_project/blob/master/src/main/java/com/example/webflux_demo/RouterConfig.java)
  - Spec
      - Spring 5+, Java 11+, WebFlux, Functional EndPoint
  
  - Request
    ```
      GET localhost:8080/hello?name$name
    ```

   - Response -  aplication/json
    ```
    {
       "to": '${name}',
       "message": 'hello ${name}'
    }
    ```



