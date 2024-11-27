package com.webtoiec.serverwebtoiec.api.admin;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin/imgur")
public class ImgurAPI {
  @Value("${imgur.client.id}")
  private String clientId;

  private static final String IMGUR_UPLOAD_URL = "https://api.imgur.com/3/image";

  @PostMapping("/upload")
  public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile file) {
    OkHttpClient client = new OkHttpClient();

    try {
      // Chuyển file sang RequestBody
      RequestBody requestBody = new MultipartBody.Builder()
          .setType(MultipartBody.FORM)
          .addFormDataPart("image", file.getOriginalFilename(),
              RequestBody.create(file.getBytes(), MediaType.parse(file.getContentType())))
          .build();

      // Tạo request gửi đến Imgur
      Request request = new Request.Builder()
          .url(IMGUR_UPLOAD_URL)
          .addHeader("Authorization", "Client-ID " + clientId)
          .post(requestBody)
          .build();

      // Gửi request
      Response response = client.newCall(request).execute();

      if (response.isSuccessful() && response.body() != null) {
        // Trích xuất URL ảnh từ JSON response
        String responseBody = response.body().string();
        return ResponseEntity.ok(responseBody);
      } else {
        return ResponseEntity.status(response.code()).body("Error uploading image");
      }
    } catch (IOException e) {
      return ResponseEntity.status(500).body("Error: " + e.getMessage());
    }
  }
}
