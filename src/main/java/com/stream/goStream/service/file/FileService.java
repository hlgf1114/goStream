package com.stream.goStream.service.file;

import com.stream.goStream.domain.file.File;
import com.stream.goStream.domain.file.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class FileService {
    
    private FileRepository fileRepository;


}
