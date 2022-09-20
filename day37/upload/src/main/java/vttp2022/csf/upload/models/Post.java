package vttp2022.csf.upload.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Post {

    private Integer postId;
    private String title;
    private String mediaType;
    private byte[] content;

    public Integer getPostId() { return postId; }
    public void setPostId(Integer postId) { this.postId = postId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getMediaType() { return mediaType; }
    public void setMediaType(String mediaType) { this.mediaType = mediaType; }

    public byte[] getContent() { return content; }
    public void setContent(byte[] content) { this.content = content; }
    
    public static Post create(ResultSet rs) throws SQLException {
        Post p = new Post();
        p.setPostId(rs.getInt("post_id"));
        p.setTitle(rs.getString("title"));
        p.setMediaType(rs.getString("media_type"));
        p.setContent(rs.getBytes("pic"));
        return p;
    }
    
}
