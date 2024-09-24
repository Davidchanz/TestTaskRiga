package com.gn.testtaskriga.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class ErrorDto {

    @NotNull
    private String error;

    @NotNull
    private String description;
}
