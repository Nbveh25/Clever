package utils;

import jakarta.servlet.http.Part;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class UploadUtil {

    private static final String FILE_PREFIX = "/tmp";

    public static File getFile(Part part) throws IOException {
        String filename = part.getSubmittedFileName();

        File file = new File(FILE_PREFIX + File.separator + filename);

        file.getParentFile().mkdirs();

        try (InputStream content = part.getInputStream();
             FileOutputStream outputStream = new FileOutputStream(file)) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = content.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
        return file;
    }

    public static String getFolderUpload(String question_type) {
        String folder = "";
        if (question_type.equals("music_question")) {
            folder = "music";
        } else if (question_type.equals("image_question")) {
            folder = "image";
        } else if (question_type.equals("video_question")) {
            folder = "video";
        }

        return folder;
    }
}
