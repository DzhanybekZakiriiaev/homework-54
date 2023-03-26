package com.example.homework54.service;
import com.example.homework54.dto.PostDTO;
import com.example.homework54.entity.Comment;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class DataService {
    private Connection conn;

    private Connection getNewConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/postgres?user=postgres&password=qwerty";
        return DriverManager.getConnection(url);
    }

    private void init() throws SQLException {
        conn = getNewConnection();
    }

    private int executeUpdate(String query) throws SQLException {
        init();
        Statement statement = conn.createStatement();
        int result = statement.executeUpdate(query);
        return result;
    }

    public String shouldCreateTable() {
        try {
            createNewDatabase();
            conn.createStatement().execute("select  * from users");
            return "All is OK";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }
    public String subscribe(Integer userId) {
        try {
            String insertFollowedQuery = "INSERT INTO followed (userId, followerId, dateTime) " +
                    "VALUES (" + userId + ", 2 , '2022-03-16 12:10:00')";
            executeUpdate(insertFollowedQuery);
            return "All is OK";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }
    public String comment(Integer postId, Comment comment) {
        try {
            String insertCommentQuery = "INSERT INTO comment (postId, userEmail, text, dateTime) " +
                    "VALUES (" + postId + ", 'jane.doe@gmail.com'," +comment.getText() + ", '2022-03-16 12:15:00')";
            executeUpdate(insertCommentQuery);
            return "All is OK";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }
    public String deleteComment(Integer commentId) {
        try {
            conn.createStatement().execute("DELETE FROM comments WHERE id = " + commentId);
            return "All is OK";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }
    public String shouldInsertValues() {
        try {
            insertValues();
            conn.createStatement().execute("select  * from users");
            return "All is OK";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }
    public void insertValues() throws SQLException {
        String insertUserQuery = "INSERT INTO users (name, email, password, posts) " +
                "VALUES ('John Smith', 'john.smith@gmail.com', 'password123', 2)";
        executeUpdate(insertUserQuery);
        String insertPostQuery = "INSERT INTO post (image, description, dateTime, authorEmail) " +
                "VALUES ('image.jpg', 'This is a post', '2022-03-16 12:00:00', 'john.smith@gmail.com')";
        executeUpdate(insertPostQuery);
        String insertLikedQuery = "INSERT INTO liked (userEmail, postId, dateTime) " +
                "VALUES ('jane.doe@gmail.com', 1, '2022-03-16 12:05:00')";
        executeUpdate(insertLikedQuery);
        String insertFollowedQuery = "INSERT INTO followed (userId, followerId, dateTime) " +
                "VALUES (1, 2, '2022-03-16 12:10:00')";
        executeUpdate(insertFollowedQuery);
        String insertCommentQuery = "INSERT INTO comment (postId, userEmail, text, dateTime) " +
                "VALUES (1, 'jane.doe@gmail.com', 'Great post!', '2022-03-16 12:15:00')";
        executeUpdate(insertCommentQuery);
    }

    public void createNewDatabase() throws SQLException {
        String userTableQuery = "CREATE TABLE users (" +
                "id SERIAL PRIMARY KEY," +
                "name TEXT NOT NULL," +
                "email TEXT NOT NULL UNIQUE," +
                "password TEXT NOT NULL," +
                "posts INTEGER DEFAULT 0" +
                ");";
        String postTableQuery = "CREATE TABLE post (" +
                "id SERIAL PRIMARY KEY," +
                "image TEXT NOT NULL," +
                "description TEXT," +
                "dateTime TIMESTAMP NOT NULL," +
                "authorEmail TEXT NOT NULL," +
                "FOREIGN KEY (authorEmail) REFERENCES users(email) ON DELETE CASCADE" +
                ");";
        String likedTableQuery = "CREATE TABLE liked (" +
                "id SERIAL PRIMARY KEY," +
                "userEmail TEXT NOT NULL," +
                "postId INTEGER NOT NULL," +
                "dateTime TIMESTAMP NOT NULL," +
                "FOREIGN KEY (userEmail) REFERENCES users(email) ON DELETE CASCADE," +
                "FOREIGN KEY (postId) REFERENCES post(id) ON DELETE CASCADE" +
                ");";
        String followedTableQuery = "CREATE TABLE followed (" +
                "id SERIAL PRIMARY KEY," +
                "userId INTEGER NOT NULL," +
                "followerId INTEGER NOT NULL," +
                "dateTime TIMESTAMP NOT NULL," +
                "FOREIGN KEY (userId) REFERENCES users(id) ON DELETE CASCADE," +
                "FOREIGN KEY (followerId) REFERENCES users(id) ON DELETE CASCADE" +
                ");";
        String commentTableQuery = "CREATE TABLE comment (" +
                "id SERIAL PRIMARY KEY," +
                "postId INTEGER NOT NULL," +
                "userEmail TEXT NOT NULL," +
                "text TEXT NOT NULL," +
                "dateTime TIMESTAMP NOT NULL," +
                "FOREIGN KEY (postId) REFERENCES post(id) ON DELETE CASCADE," +
                "FOREIGN KEY (userEmail) REFERENCES users(email) ON DELETE CASCADE" +
                ");";

        executeUpdate(userTableQuery);
        executeUpdate(postTableQuery);
        executeUpdate(likedTableQuery);
        executeUpdate(followedTableQuery);
        executeUpdate(commentTableQuery);
    }
}
