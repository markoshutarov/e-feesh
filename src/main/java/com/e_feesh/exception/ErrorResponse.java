package com.e_feesh.exception;

import lombok.Data;

@Data
public class ErrorResponse {

    private Integer status;
    private String message;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
