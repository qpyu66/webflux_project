package com.example.webflux_demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Message {

    private String to;
    private String job;
    private String message;

}
