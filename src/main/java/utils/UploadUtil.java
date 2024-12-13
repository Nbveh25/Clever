package utils;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class UploadUtil {
    private static final String FILE_PREFIX = "/tmp";
    private static final String MUSIC = "music";
    private static final String IMAGE = "image";
    private static final String VIDEO = "video";
    private static final String RAW = "raw";

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
        return switch (question_type) {
            case "music_question" -> MUSIC;
            case "image_question" -> IMAGE;
            case "video_question" -> VIDEO;
            default -> "";
        };
    }

    public static String getResourceType(String contentType) throws ServletException {
        if (contentType.startsWith("image/")) {
            return IMAGE;
        } else if (contentType.startsWith("video/")) {
            return VIDEO;
        } else if (contentType.startsWith("audio/")) {
            return RAW;
        } else {
            throw new ServletException("Unsupported file type: " + contentType);
        }
    }
}
