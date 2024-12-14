package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import services.UserService;
import utils.CloudinaryUtil;
import utils.UploadUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "UploadIconServlet", urlPatterns = "/upload-icon")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10,
        maxFileSize = 1024 * 1024 * 1024,
        maxRequestSize = 1024 * 1024 * 1024
)
public class UploadIconServlet extends HttpServlet {

    private UserService userService;
    private static final String PROFILE_ICONS = "profile_icons";

    @Override
    public void init() throws ServletException {
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession();

            Part filePart = req.getPart("profilePic");
            validateFilePart(filePart);

            File uploadedFile = UploadUtil.getFile(filePart);
            Map<String, Object> uploadParams = new HashMap<>();
            uploadParams.put("folder", PROFILE_ICONS);

            Map uploadResult = CloudinaryUtil.getInstance().uploader().upload(uploadedFile, uploadParams);

            String imageUrl = (String) uploadResult.get("secure_url");
            session.setAttribute("icon_url", imageUrl);

            int user_id = (int) session.getAttribute("user_id");
            userService.updateProfileIconUrl(user_id, imageUrl);

            uploadedFile.delete();

            getServletContext().getRequestDispatcher("/WEB-INF/jsp/personal_account.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void validateFilePart(Part filePart) throws ServletException {
        if (filePart == null || filePart.getSize() == 0) {
            throw new ServletException("Файл не был загружен или пуст.");
        }
    }
}