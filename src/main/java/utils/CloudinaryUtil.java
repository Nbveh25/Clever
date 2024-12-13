package utils;

import com.cloudinary.Cloudinary;

import java.util.HashMap;
import java.util.Map;

public class CloudinaryUtil {

    private final static String CLOUD_NAME = "dsrqq4er2";
    private final static String API_KEY = "762381588228598";
    private final static String API_SECRET = "-192HN7J2qqQwKn1OeJbXL_wzE8";

    private static Cloudinary cloudinary;

    public static Cloudinary getInstance() {
        if (cloudinary == null) {
            Map<String, String> configMap = new HashMap<>();
            configMap.put("cloud_name", CLOUD_NAME);
            configMap.put("api_key", API_KEY);
            configMap.put("api_secret", API_SECRET);
            cloudinary = new Cloudinary(configMap);
        }
        return cloudinary;
    }
}