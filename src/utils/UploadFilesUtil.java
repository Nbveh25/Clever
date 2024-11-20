package utils;

import java.io.FileOutputStream;
import java.io.InputStream;

public class UploadFilesUtil {

    public void uploadMediaFile(InputStream is, String path) {

        try (FileOutputStream fos = new FileOutputStream(path)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getPathForUpload(String fileName, String quiz_type) {
        String path = "C:/Users/timur/IdeaProjects/Clever/src/main/webapp/media/";

        if (quiz_type.equals("music_question")) {
            path = path + "musics/" + fileName;
        } else if (quiz_type.equals("image_question")) {
            path = path + "images/" + fileName;
        } else if (quiz_type.equals("video_question")) {
            path = path + "videos/" + fileName;
        }

        return path;
    }

    public String getPathForMedia(String fileName, String quiz_type) {
        String path = "http://localhost:8080/media/";

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
