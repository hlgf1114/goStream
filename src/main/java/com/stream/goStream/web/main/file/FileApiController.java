package com.stream.goStream.web.main.file;


import com.stream.goStream.domain.file.dto.FileGetResponseDto;
import com.stream.goStream.service.file.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RequiredArgsConstructor
@RestController
public class FileApiController {

    private final FileService fileService;


    @GetMapping("/api/video/thumbnail/{fileId}")
    public void getPicture(
            @PathVariable(value = "fileId")Long fileId,
            HttpServletResponse response) {

        try {
            fileService.getPicture(fileId, response);
        } catch (Exception e) {e.printStackTrace();}

    }

    @GetMapping("/api/video/{fileId}")
    // @ResponseBody
    public void streamVideo(
            @PathVariable(value = "fileId") Long fileId,
            HttpServletRequest request,
            HttpServletResponse response) {

        try {
            fileService.getVideo(fileId, response, request);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
