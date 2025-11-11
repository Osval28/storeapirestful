package co.com.store.storeapirestful.controller.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ErrorResponse {
    private int status;
    private String message;
    private String error;
}
