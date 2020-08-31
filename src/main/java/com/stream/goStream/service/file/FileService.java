package com.stream.goStream.service.file;

import com.stream.goStream.domain.file.File;
import com.stream.goStream.domain.file.FileRepository;
import com.stream.goStream.domain.file.dto.FileGetResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class FileService {
    
    private final FileRepository fileRepository;

    public FileGetResponseDto getVideo(Long fileId) {

        File file = fileRepository.findById(fileId).get();


        FileGetResponseDto dto = FileGetResponseDto.builder()
                .id(file.getId())
                .filePath(file.getFilePath())
                .fileSize(file.getFileSize())
                .originalFileName(file.getOriginalFileName())
                .build();


        return dto;

    }


}
