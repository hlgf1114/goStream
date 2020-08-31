package com.stream.goStream.web.main.file;


import com.stream.goStream.domain.file.dto.FileGetResponseDto;
import com.stream.goStream.service.file.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RequiredArgsConstructor
@RestController
public class FileApiController {

    private final FileService fileService;

    @RequestMapping("/api/downloadVideo/{fileId}")
    // @ResponseBody
    public StreamingResponseBody getVideo(@PathVariable(value = "fileId") Long fileId) throws IOException {


        FileGetResponseDto videoInfo = fileService.getVideo(fileId);

        File file = new File(videoInfo.getFilePath());
        final InputStream is = new FileInputStream(file);

        return os -> {
            readAndWrite(is, os);
        };

    }

    private void readAndWrite(final InputStream is, OutputStream os) throws IOException {
        byte[] data = new byte[2048];
        int read = 0;
        while ((read = is.read(data)) > 0) {
            os.write(data, 0, read);
        }
        os.flush();
    }

}
