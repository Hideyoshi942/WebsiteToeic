package com.webtoiec.serverwebtoiec.api.admin;

import com.webtoiec.serverwebtoiec.entities.CommentPostNews;
import com.webtoiec.serverwebtoiec.entities.PostNews;
import com.webtoiec.serverwebtoiec.entities.UserToeic;
import com.webtoiec.serverwebtoiec.repository.CommentPostNewsRepo;
import com.webtoiec.serverwebtoiec.repository.PostNewsRepo;
import com.webtoiec.serverwebtoiec.repository.UserRepo;
import java.io.File;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin/news")
public class PostNewsAPI {

  private final PostNewsRepo postNewsRepo;
  private final CommentPostNewsRepo commentPostNewsRepo;
  private final UserRepo userRepo;

  public PostNewsAPI(PostNewsRepo postNewsRepo, CommentPostNewsRepo commentPostNewsRepo,
      UserRepo userRepo) {
    this.postNewsRepo = postNewsRepo;
    this.commentPostNewsRepo = commentPostNewsRepo;
    this.userRepo = userRepo;
  }

  @GetMapping("/list")
  public List<Map<String, Object>> getAllNews() {
    List<PostNews> postNews = postNewsRepo.findAll();
    List<Map<String, Object>> response = new ArrayList<>();

    for (PostNews news : postNews) {
      Map<String, Object> map = new HashMap<>();
      map.put("postid", news.getPostid());
      map.put("content", news.getContent());
      map.put("image", "/api/admin/news/image/" + URLEncoder.encode(news.getPostimage(), StandardCharsets.UTF_8));
      map.put("title", news.getTitle());
      response.add(map);
    }
    return response;
  }

  @GetMapping("/newsdetails/{postid}")
  public ResponseEntity<?> getNewsById(
      @PathVariable int postid
  ) {
    PostNews postNews = postNewsRepo.findById(postid).orElse(null);
    if (postNews == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    Map<String, Object> map = new HashMap<>();
    map.put("postid", postNews.getPostid());
    map.put("content", postNews.getContent());
    map.put("image", "/api/admin/news/image/" + URLEncoder.encode(postNews.getPostimage(), StandardCharsets.UTF_8));
    map.put("title", postNews.getTitle());
    return ResponseEntity.ok(map);
  }

  @GetMapping("/image/{filename}")
  public ResponseEntity<Resource> serveImage(@PathVariable String filename) {
    try {
      String decodedFilename = URLDecoder.decode(filename, StandardCharsets.UTF_8.name());
      Path filePath = Paths.get("src/main/resources/static/img/postnews").resolve(decodedFilename).normalize();
      Resource resource = new UrlResource(filePath.toUri());
      if (!resource.exists()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
      }

      String contentType = Files.probeContentType(filePath);
      if (contentType == null) {
        contentType = "application/octet-stream";
      }

      return ResponseEntity.ok()
          .contentType(MediaType.parseMediaType(contentType))
          .body(resource);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
  }

  @PostMapping("/createPostNews")
  public List<String> createPostNews(
      @RequestParam int id,
      @RequestParam String title,
      @RequestParam String content,
      @RequestParam(value = "postimage", required = false ) MultipartFile postimage
  ) {
    List<String> response = new ArrayList<String>();
    String rootDirectory = System.getProperty("user.dir");
    UserToeic userToeic = userRepo.findById(id).orElse(null);

    PostNews postNews = new PostNews();
    postNewsRepo.save(postNews);
    try {
      Path pathPostImage = Paths.get(rootDirectory + "/src/main/resources/static/img/postnews/" + postNews.getPostid() + "." + postimage.getOriginalFilename());
      postimage.transferTo(new File(pathPostImage.toString()));

      postNews.setUser(userToeic);
      postNews.setTitle(title);
      postNews.setContent(content);
      postNews.setPostimage(postNews.getPostid() + "." + postimage.getOriginalFilename());
      postNews.setCreated(LocalDateTime.now());
      postNewsRepo.save(postNews);
    } catch (Exception e) {
      response.add(e.toString());
      System.out.println("ErrorCreatePostNews: " + e);
    }
    return response;
  }

  @PutMapping("/updatePostNews")
  public ResponseEntity<?> updatePostNews(
      @RequestParam int postid,
      @RequestParam String title,
      @RequestParam String content,
      @RequestParam(value = "postimage", required = false) MultipartFile postimage // Cho phép hình ảnh là tùy chọn
  ) {
    PostNews postNews = postNewsRepo.findById(postid).orElse(null);
    if (postNews == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Post not found"));
    }

    try {
      // Cập nhật tiêu đề và nội dung
      postNews.setTitle(title);
      postNews.setContent(content);

      // Nếu người dùng tải lên hình ảnh mới
      if (postimage != null && !postimage.isEmpty()) {
        // Lưu trữ hình ảnh mới
        String rootDirectory = System.getProperty("user.dir");
        String newImageName = "postnewsid=" + postNews.getPostid() + "." + postimage.getOriginalFilename();
        Path pathPostImage = Paths.get(rootDirectory + "/src/main/resources/static/img/postnews/" + newImageName);

        // Xóa hình ảnh cũ nếu tồn tại
        String oldImagePath = rootDirectory + "/src/main/resources/static/img/postnews/" + postNews.getPostimage();
        File oldImage = new File(oldImagePath);
        if (oldImage.exists()) {
          oldImage.delete();
        }

        // Lưu hình ảnh mới
        postimage.transferTo(new File(pathPostImage.toString()));
        postNews.setPostimage(newImageName);
      }

      postNews.setCreated(LocalDateTime.now()); // Cập nhật thời gian chỉnh sửa
      postNewsRepo.save(postNews); // Lưu thay đổi

      return ResponseEntity.ok(Map.of("message", "Post updated successfully"));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Error updating post", "error", e.getMessage()));
    }
  }


  @DeleteMapping("/delete/{postid}")
  public ResponseEntity<?> deletePostNews(@PathVariable int postid) {
    PostNews postNews = postNewsRepo.findById(postid).orElse(null);
    if (postNews == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    postNewsRepo.delete(postNews);
    return ResponseEntity.ok().body(null);
  }


  // Comment
  @GetMapping("/imageCommentNews/{filename}")
  public ResponseEntity<Resource> serveImageNews(@PathVariable String filename) {
    try {
      String decodedFilename = URLDecoder.decode(filename, StandardCharsets.UTF_8.name());
      Path filePath = Paths.get("src/main/resources/static/img/commentpostnews").resolve(decodedFilename).normalize();
      Resource resource = new UrlResource(filePath.toUri());
      if (!resource.exists()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
      }

      String contentType = Files.probeContentType(filePath);
      if (contentType == null) {
        contentType = "application/octet-stream";
      }

      return ResponseEntity.ok()
          .contentType(MediaType.parseMediaType(contentType))
          .body(resource);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
  }

  @GetMapping("/listCommentPostNews/{postid}")
  public List<Map<String, Object>> getAllCommentPostNews(@PathVariable int postid) {
    PostNews postNews = postNewsRepo.findById(postid).orElse(null);
    if (postNews == null) {
      return Collections.emptyList();
    }
    List<CommentPostNews> list = commentPostNewsRepo.findByPostnews(postNews);

    List<Map<String, Object>> response = new ArrayList<>();
    for (CommentPostNews commentPostNews : list) {
      Map<String, Object> map = new HashMap<>();
      String imagePath = commentPostNews.getCommentpostnewsimage();

      map.put("commentpostnewsimage", imagePath != null
          ? "/api/admin/news/imageCommentNews/" + URLEncoder.encode(imagePath, StandardCharsets.UTF_8)
          : null); // Xử lý khi imagePath bị null
      map.put("content", commentPostNews.getContent());
      map.put("createdate", commentPostNews.getCreated());
      map.put("parenid", commentPostNews.getParentid());
      map.put("commentid", commentPostNews.getCommentid());
      map.put("updated", commentPostNews.getUpdated());
      map.put("postid", commentPostNews.getPostnews().getPostid());
      map.put("id", commentPostNews.getUser().getUsername());
      response.add(map);
    }
    return response;
  }


  @PostMapping("/createCommentPostNews")
  public ResponseEntity<?> createCommentPostNews(
      @RequestParam int postid,
      @RequestParam int id,
      @RequestParam String content,
      @RequestParam(value = "commentpostnewsimage", required = false) MultipartFile commentpostnewsimage
  ){
    PostNews postNews = postNewsRepo.findById(postid).orElse(null);
    if (postNews == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    UserToeic userToeic = userRepo.findById(id).orElse(null);
    if (userToeic == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    List<String> response = new ArrayList<String>();
    String rootDirectory = System.getProperty("user.dir");
    CommentPostNews commentPostNews = new CommentPostNews();

    commentPostNewsRepo.save(commentPostNews);
    try {
      Path pathPostImage = Paths.get(rootDirectory + "/src/main/resources/static/img/commentpostnews/" + postNews.getPostid() + "." + commentpostnewsimage.getOriginalFilename());
      commentpostnewsimage.transferTo(new File(pathPostImage.toString()));
      commentPostNews.setContent(content);
      commentPostNews.setCommentpostnewsimage(postNews.getPostid() + "." + commentpostnewsimage.getOriginalFilename());
      commentPostNews.setCreated(LocalDateTime.now());
      commentPostNews.setPostnews(postNews);
      commentPostNews.setUser(userToeic);
      commentPostNewsRepo.save(commentPostNews);
    } catch (Exception e) {
      response.add(e.toString());
      System.out.println("ErrorCreatePostNews: " + e);
    }

    return ResponseEntity.ok().body(null);
  }

  @PostMapping("/replyCommentPostNews")
  public ResponseEntity<?> replyCommentPostNews(
      @RequestParam int postid,
      @RequestParam int id,
      @RequestParam String content,
      @RequestParam(value = "commentpostnewsimage", required = false) MultipartFile commentpostnewsimage,
      @RequestParam int commentid
  ) {
    // Tìm bài viết
    PostNews postNews = postNewsRepo.findById(postid).orElse(null);
    if (postNews == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post not found");
    }

    // Tìm người dùng
    UserToeic userToeic = userRepo.findById(id).orElse(null);
    if (userToeic == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }

    // Tìm bình luận cha
    CommentPostNews parentComment = commentPostNewsRepo.findById(commentid).orElse(null);
    if (parentComment == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parent comment not found");
    }

    try {
      // Tạo đối tượng bình luận mới
      CommentPostNews reply = new CommentPostNews();
      reply.setContent(content);
      reply.setParentid(parentComment.getCommentid());
      reply.setCreated(LocalDateTime.now());
      reply.setPostnews(postNews);
      reply.setUser(userToeic);

      // Xử lý file ảnh nếu có
      if (commentpostnewsimage != null && !commentpostnewsimage.isEmpty()) {
        String rootDirectory = System.getProperty("user.dir");
        String filename = postid + "." + commentpostnewsimage.getOriginalFilename();
        Path pathPostImage = Paths.get(rootDirectory + "/src/main/resources/static/img/commentpostnews/");

        // Tạo thư mục nếu chưa tồn tại
        if (!Files.exists(pathPostImage)) {
          Files.createDirectories(pathPostImage);
        }

        Path filePath = pathPostImage.resolve(filename);
        commentpostnewsimage.transferTo(filePath.toFile());

        // Lưu đường dẫn file vào bình luận
        reply.setCommentpostnewsimage(filename);
      }

      // Lưu bình luận vào cơ sở dữ liệu
      commentPostNewsRepo.save(reply);

      return ResponseEntity.ok("Reply saved successfully");
    } catch (Exception e) {
      System.err.println("Error saving reply: " + e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save reply");
    }
  }


  @PutMapping("/updateCommentPostNews")
  public ResponseEntity<?> updateCommentPostNews(
      @RequestParam int commentid,
      @RequestParam String content,
      @RequestParam MultipartFile commentpostnewsimage
  ){
    CommentPostNews commentPostNews = commentPostNewsRepo.findById(commentid).orElse(null);
    if (commentPostNews == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    commentPostNews.setContent(content);
    commentPostNews.setCommentpostnewsimage(commentpostnewsimage.getOriginalFilename());
    commentPostNews.setUpdated(LocalDateTime.now());
    commentPostNewsRepo.save(commentPostNews);
    return ResponseEntity.ok().body(null);
  }

  @DeleteMapping("/deleteCommentPostNews/{commentid}")
  public ResponseEntity<?> deleteCommentPostNews(@PathVariable int commentid) {
    CommentPostNews commentPostNews = commentPostNewsRepo.findById(commentid).orElse(null);
    if (commentPostNews == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    commentPostNewsRepo.delete(commentPostNews);
    return ResponseEntity.ok().body(null);
  }
}
