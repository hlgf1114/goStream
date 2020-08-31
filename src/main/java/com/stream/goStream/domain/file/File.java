package com.stream.goStream.domain.file;

import com.stream.goStream.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "file")
public class File extends BaseTimeEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long id;

    private String originalFileName;
    private String filePath;
    private Long fileSize;

    @Builder
    public File(String originalFileName, String filePath, Long fileSize) {
        this.originalFileName = originalFileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }

    public void update(String originalFileName,
                       String filePath,
                       Long fileSize) {
        this.originalFileName = originalFileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }
}
