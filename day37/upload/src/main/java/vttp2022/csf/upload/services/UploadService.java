package vttp2022.csf.upload.services;

import java.sql.ResultSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import vttp2022.csf.upload.models.Post;

@Service
public class UploadService {

    public static final String SQL_INSERT_BLOB = "insert into post(title, media_type, pic) values (?, ?, ?)";
    public static final String SQL_GET_UPLOAD = "select * from post where post_id = ?";

    @Autowired
    private JdbcTemplate template;

    public Optional<Post> getPost(Integer postId) {
        return template.query(SQL_GET_UPLOAD,
            (ResultSet rs) -> {
                if (!rs.next())
                    return Optional.empty();
                return Optional.of(Post.create(rs));
            },
            postId
        );

    }
    
}
