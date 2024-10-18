package services;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class UploadFilesService {

    public boolean uploadMediaFile(InputStream is, String path) {
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


    public String getPathForMedia(String fileName, String quiz_type) {
        String path = "C:\\Users\\timur\\IdeaProjects\\Clever\\media";

        if (quiz_type.equals("music_question")) {
            path = path + "/musics/" + fileName;
        } else if (quiz_type.equals("image_question")) {
            path = path + "/images/" + fileName;
        } else if (quiz_type.equals("video_question")) {
            path = path + "/videos/" + fileName;
        }

        return path;
    }
}
