package com.stream.goStream.service.file;

import com.stream.goStream.domain.file.File;
import com.stream.goStream.domain.file.FileRepository;
import com.stream.goStream.domain.file.dto.FileGetResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

@RequiredArgsConstructor
@Service
public class FileService {
    
    private final FileRepository fileRepository;

    public void getPicture(
            Long fileId,
            HttpServletResponse response) throws IOException {


        File file = fileRepository.findById(fileId).get();

        String thumbnailPath = file.getFilePath();
        thumbnailPath = thumbnailPath.substring(0, thumbnailPath.lastIndexOf(".")) + ".png";

        java.io.File img = new java.io.File(thumbnailPath);

        if(!img.exists()) {
            img = new java.io.File("R://default.png");
        }

        BufferedImage image = ImageIO.read(img);

        OutputStream out = response.getOutputStream();
        ImageIO.write(image, "png", out);

        out.flush();

    }

    public void getVideo(
            Long fileId,
            HttpServletResponse response,
            HttpServletRequest request) throws Exception {

        File f = fileRepository.findById(fileId).get();


        String path = f.getFilePath();
        // 모든 썸네일은 png로
        String thumbnailPath = path.substring(0, path.lastIndexOf(".")) + ".png";

        FileGetResponseDto dto = FileGetResponseDto.builder()
                .id(f.getId())
                .filePath(f.getFilePath())
                .fileSize(f.getFileSize())
                .originalFileName(f.getOriginalFileName())
                .thumbnailPath(thumbnailPath)
                .build();

        java.io.File file = new java.io.File(dto.getFilePath());

        // 파일이 없다면
        if(!file.exists()) {
            throw new FileNotFoundException();
        }

        RandomAccessFile randomFile = new RandomAccessFile(file, "r");

        long rangeStart = 0;
        long rangeEnd = 0;
        boolean isPart = false; // 부분 요청일 경우 true 아니면 false;

        try {

            // 동영상 파일 크기
            long videoSize = randomFile.length();

            // 스트림 요청 범위
            String range = request.getHeader("range");

            // 기본형식은 "bytes={start}-{end}"
            // range가 0이거나 null 이라면 전체 요청
            // 요청 범위 구함

            if(range != null) {

                if(range.endsWith("-")) {
                    range = range + (videoSize - 1);
                }


                int idxm = range.trim().indexOf("-");
                rangeStart = Long.parseLong(range.substring(6, idxm));
                rangeEnd = Long.parseLong(range.substring(idxm + 1));

                if(rangeStart > 0) {
                    isPart = true;
                }

            }
            else { // 전송 범위가 전체일 경우
                rangeStart = 0;
                rangeEnd = videoSize - 1;
            }

            // 전송 파일 크기
            long partSize = rangeEnd - rangeStart + 1;

            response.reset();
            response.setStatus(isPart ? HttpServletResponse.SC_PARTIAL_CONTENT : HttpServletResponse.SC_OK);
            response.setHeader("Content-Range", "bytes " + rangeStart + "-" + rangeEnd + "/" + videoSize);
            response.setHeader("Accept-Ranges", "bytes");
            response.setHeader("Content-Length", "" + partSize);

            // Content-Type 수정
            String MimeType = Files.probeContentType(Paths.get(dto.getFilePath()));
            response.setContentType(MimeType);


            int bufferSize = dto.getFileSize().intValue() / 100;

            response.setBufferSize(bufferSize);

            OutputStream out = response.getOutputStream();

            // 동영상 파일의 전송시작 위치 지정
            randomFile.seek(rangeStart);

            // 파일을 전송한다.
            byte[] buf = new byte[bufferSize];

            do {
                int block = partSize > bufferSize ? bufferSize : (int)partSize;
                int len = randomFile.read(buf, 0, block);
                out.write(buf, 0, len);
                out.flush();
                partSize -= block;
            } while(partSize > 0);


        } catch (Exception e) {

        } finally {
            randomFile.close();
        }


    }


}
