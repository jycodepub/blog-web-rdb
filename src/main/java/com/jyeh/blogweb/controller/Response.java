package com.jyeh.blogweb.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Response {
    public enum Status {
        success, failed
    }

    private Status status;
    private String message;
    private String error;
}
