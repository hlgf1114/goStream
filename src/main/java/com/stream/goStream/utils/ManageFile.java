package com.stream.goStream.utils;

import org.jcodec.api.FrameGrab;
import org.jcodec.api.JCodecException;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class ManageFile {


    public static String saveFile(MultipartFile file) throws IOException {

        final String path = "R://";

        UUID uuid = UUID.randomUUID();

        String filename = file.getOriginalFilename();
        String fileExtension = filename.substring(filename.lastIndexOf("."), filename.length());

        File folder = new File(path + uuid.toString());

        // 폴더가 존재 하지 않는다면
        if(!folder.exists()) {

            folder.mkdir();
            System.out.println(folder.toString() + " is created");

        }

        String filePath = path + uuid.toString() + "/" + uuid.toString() + fileExtension;

        FileOutputStream output = new FileOutputStream(filePath);
        output.write(file.getBytes());

        return filePath;
    }

    public static void getThumbnail(File source, File output) throws IOException, JCodecException {


        Picture picture = FrameGrab.getFrameFromFile(source, 100);

        BufferedImage bufferedImage = AWTUtil.toBufferedImage(picture);
        ImageIO.write(bufferedImage, "png", output);

    }

}
