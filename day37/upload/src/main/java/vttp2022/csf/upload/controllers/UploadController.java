package vttp2022.csf.upload.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import vttp2022.csf.upload.models.Post;
import vttp2022.csf.upload.services.UploadService;

import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;

@RestController
@RequestMapping(path = "/upload")
public class UploadController {

    public static final String SQL_INSERT_BLOB = "insert into post(title, media_type, pic) values (?, ?, ?)";
    public static final String SQL_GET_UPLOAD = "select * from post where post_id = ?";

    @Autowired
    private JdbcTemplate template;

    @Autowired
    private UploadService uploadSvc;

    @Autowired
    private AmazonS3 s3;

    @GetMapping(path = "{id}")
    public ResponseEntity<byte[]> getUpload(@PathVariable Integer id) {
        Optional<Post> opt = uploadSvc.getPost(id);
        Post p = opt.get();

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.parseMediaType(p.getMediaType()))
                .body(p.getContent());
    }
    @PostMapping(path="spaces", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> postSpacesUpoad(@RequestPart MultipartFile myfile, @RequestPart String title) {
        
        // My private metadata
        Map<String, String> myData = new HashMap<>();
        myData.put("title", title);
        myData.put("createdOn", (new Date()).toString());

        // Metadata for the object
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(myfile.getContentType());
        metadata.setContentLength(myfile.getSize());
        metadata.setUserMetadata(myData);

        String hash = UUID.randomUUID().toString().substring(0, 8);

        try {
            PutObjectRequest putReq = new PutObjectRequest("chuk", "bear/%s".formatted(hash), 
                myfile.getInputStream(), metadata);
            putReq = putReq.withCannedAcl(CannedAccessControlList.PublicRead);
            PutObjectResult result = s3.putObject(putReq);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        JsonObject data = Json.createObjectBuilder()
                .add("content-type", myfile.getContentType())
                .add("name", hash)
                .add("original_name", myfile.getOriginalFilename())
                .add("size", myfile.getSize())
                .add("form_title", title)
                .build();
        return ResponseEntity.ok(data.toString());
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
