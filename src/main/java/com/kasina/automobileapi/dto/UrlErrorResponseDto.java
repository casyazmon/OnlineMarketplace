package com.kasina.automobileapi.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UrlErrorResponseDto {
    private String status;
    private String error;
}
