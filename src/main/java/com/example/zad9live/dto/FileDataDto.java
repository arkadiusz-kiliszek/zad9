package com.example.zad9live.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@Builder
public class FileDataDto {
    @NotBlank
    private String fileName;

    @NotBlank
    private String extension;

    @Min(1)
    private Integer sizeInKb;

    @NotBlank
    private String content;

}
