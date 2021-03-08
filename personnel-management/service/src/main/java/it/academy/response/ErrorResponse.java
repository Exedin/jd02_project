package it.academy.response;


import lombok.Data;

@Data
public class ErrorResponse {
    int status;
    String message;
    long timeStamp;
}
