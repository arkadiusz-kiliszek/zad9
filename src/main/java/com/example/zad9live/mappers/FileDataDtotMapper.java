package com.example.zad9live.mappers;

import com.example.zad9live.dto.FileDataDto;
import com.example.zad9live.model.FileData;

public class FileDataDtotMapper {

    public static FileDataDto map(FileData fileData){
        return FileDataDto
                .builder()
                .content(fileData.getContent())
                .extension(fileData.getExtension())
                .fileName(fileData.getFileName())
                .sizeInKb(fileData.getSizeInKb())
               .build();
    }
}
