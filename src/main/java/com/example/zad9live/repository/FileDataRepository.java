package com.example.zad9live.repository;

import com.example.zad9live.model.FileData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FileDataRepository extends JpaRepository<FileData, UUID> {
}
