package vttp2022.csf.upload.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import vttp2022.csf.upload.models.Post;
import vttp2022.csf.upload.services.UploadService;

import java.sql.ResultSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;


@RestController
@RequestMapping(path="/upload")
public class UploadController {

    public static final String SQL_INSERT_BLOB = "insert into post(title, media_type, pic) values (?, ?, ?)";
    public static final String SQL_GET_UPLOAD = "select * from post where post_id = ?";

    @Autowired
    private JdbcTemplate template;

    @Autowired
    private UploadService uploadSvc;

    @GetMapping(path="{id}")
    public ResponseEntity<byte[]> getUpload(@PathVariable Integer id) {
        Optional<Post> opt = uploadSvc.getPost(id);
        Post p = opt.get();

        return ResponseEntity
            .status(HttpStatus.OK)
            .contentType(MediaType.parseMediaType(p.getMediaType()))
                .body(p.getContent());
    }

   @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
   public ResponseEntity<String> postUpoad(@RequestPart MultipartFile myfile, @RequestPart String title) {

        try {
            int updated = template.update(SQL_INSERT_BLOB, title, myfile.getContentType(), myfile.getInputStream());
            System.out.printf("updated: %d\n", updated);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        JsonObject data = Json.createObjectBuilder()
            .add("content-type", myfile.getContentType())
            .add("name", myfile.getName())
            .add("original_name", myfile.getOriginalFilename())
            .add("size", myfile.getSize())
            .add("form_title", title)
            .build();
        
        return ResponseEntity.ok(data.toString());
   }
}
