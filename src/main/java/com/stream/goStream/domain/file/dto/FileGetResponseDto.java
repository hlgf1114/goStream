package com.stream.goStream.domain.file.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FileGetResponseDto {

    private Long id;
    private String originalFileName;
    private String filePath;
    private Long fileSize;

    @Builder
    public FileGetResponseDto(Long id, String originalFileName, String filePath, Long fileSize) {
        this.id = id;
        this.originalFileName = originalFileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }
}
