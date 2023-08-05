package com.example.zad9live.service;

import com.example.zad9live.dto.FileDataDto;
import com.example.zad9live.dto.FileDataList;
import com.example.zad9live.exception.SdaException;
import com.example.zad9live.mappers.FileDataDtotMapper;
import com.example.zad9live.model.FileData;
import com.example.zad9live.repository.FileDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileDataService {

    private final FileDataRepository fileDataRepository;

    public FileDataList getFilesData() {
        List<FileData> filesData = fileDataRepository.findAll();
        List<FileDataDto> fileDataDtos = filesData.stream()
                .map(FileDataDtotMapper::map)
                .collect(Collectors.toList());
        return new FileDataList(fileDataDtos);
    }

    public FileDataDto getFileData(String id) {
        FileData fileData = fileDataRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new SdaException("File data not exists! Id: " + id));
        return FileDataDtotMapper.map(fileData);
    }

    public String createFileData(FileDataDto dataDto) {
        FileData fileData = FileData
                .builder()
                .fileName(dataDto.getFileName())
                .content(dataDto.getContent())
                .extension(dataDto.getExtension())
                .sizeInKb(dataDto.getSizeInKb())
                .build();
        FileData saved = fileDataRepository.save(fileData);
        log.info("createFileData finished! {}", saved.getId().toString());
        return saved.getId().toString();
    }

    public void updateFileData(String id, FileDataDto fileDataDto) {
        FileData fileData = fileDataRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new SdaException("File data not exists! Id: " + id));
        fileData.setFileName(fileDataDto.getFileName());
        fileData.setContent(fileDataDto.getContent());
        fileData.setSizeInKb(fileDataDto.getSizeInKb());
        fileData.setExtension(fileDataDto.getExtension());
        fileDataRepository.save(fileData);
        log.info("FileData {} was updated!", id);
    }

    public void deleteFileData(String id){
        FileData fileData = fileDataRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new SdaException("File data not exists! Id: " + id));
        fileDataRepository.delete(fileData);
        log.info("File data was removed {}", id);
    }
}
