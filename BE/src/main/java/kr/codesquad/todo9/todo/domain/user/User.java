package kr.codesquad.todo9.todo.domain.user;

import org.springframework.data.annotation.Id;

import java.util.Map;

public class User {

    @Id
    private Long id;
    private String username;
    private String profileImageUrl;

    public User() {}

    public User(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public User(Map<String, Object> data) {
        this.id = Long.parseLong(String.valueOf(data.get("id")));
        this.username = (String) data.get("username");
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username='" + username + '\'' + ", profileImageUrl='" + profileImageUrl + '\'' + '}';
    }
}
