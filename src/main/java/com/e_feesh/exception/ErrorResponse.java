package com.e_feesh.exception;

import lombok.Data;

@Data
public class ErrorResponse {
    
    private Integer status;
    private String message;

    public ErrorResponse(int i, String message) {
    }
}
