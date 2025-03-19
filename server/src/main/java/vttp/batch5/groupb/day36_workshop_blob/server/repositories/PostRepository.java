package vttp.batch5.groupb.day36_workshop_blob.server.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp.batch5.groupb.day36_workshop_blob.server.models.Post;

@Repository
public class PostRepository {
    
    @Autowired
    private JdbcTemplate sqlTemplate;

    public boolean addPost(Post post) {
        
        final String SQL_INSERT_POST = "INSERT INTO posts(post_id, comments, picture) VALUES (?, ?, ?);";
        
        int added = sqlTemplate.update(SQL_INSERT_POST
            , post.getPostId(), post.getComments(), post.getPicture());

        return added > 0;
    }

    public List<Post> getAllPosts() {
        final String SQL_SELECT_ALL_POSTS = "SELECT * FROM posts";

        return sqlTemplate.query(SQL_SELECT_ALL_POSTS, (rs, rowNum) -> {
            Post post = new Post();
            post.setPostId(rs.getString("post_id"));
            post.setComments(rs.getString("comments"));
            post.setPictureBlob(rs.getBytes("picture")); // Correct way to retrieve BLOB
            return post;
        });
    }
}
