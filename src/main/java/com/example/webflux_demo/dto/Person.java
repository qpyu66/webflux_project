package com.example.webflux_demo.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Person {

    private String name;
    private String email;
    private String phone;

    public Person(String name, String email, String phone) {
                this.name = name;
                this.email = email;
                this.phone = phone;
            }
}
