package services;

import java.io.FileOutputStream;
import java.io.InputStream;

public class UploadFilesService {

    public boolean uploadFile(InputStream is, String fileName, String quiz_type, String path) {
        boolean test = false;

        try (FileOutputStream fos = new FileOutputStream(path)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
            test = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return test;
    }

    public String getPath(String fileName, String quiz_type) {
        String path = "C:/uploads/media/";

        if (quiz_type.equals("music_question")) {
            path = path + "musics/" + fileName;
        } else if (quiz_type.equals("image_question")) {
            path = path + "images/" + fileName;
        } else if (quiz_type.equals("video_question")) {
            path = path + "videos/" + fileName;
        }

        return path;
    }
}
