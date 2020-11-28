package com.backend.springboot.docker.controller.request;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class PersonRequest {

    private static final String INVALID_DATE = "Invalid parameter date";
    private static final String INVALID_NAME = "Invalid parameter name";
    private static final String INVALID_JOB = "Invalid parameter job";
    private static final String INVALID_ID = "Invalid parameter id";

    @Pattern(regexp = "[0-9]+", message = INVALID_ID)
    @NotBlank(message = INVALID_ID)
    private Integer id;

    @Pattern(regexp = "[a-zA-Z]+", message = INVALID_NAME)
    @NotBlank(message = INVALID_NAME)
    private String name;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date date;

    @Pattern(regexp = "[0-9]+", message = INVALID_JOB)
    private Integer job;
}
