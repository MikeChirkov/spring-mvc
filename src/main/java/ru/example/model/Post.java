package ru.example.model;

import java.util.Objects;

public class Post {
    private long id;
    private String content;
    private Boolean removed;

    public Post() {
    }

    public Post(long id, String content, Boolean removed) {
        this.id = id;
        this.content = content;
        this.removed = removed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getRemoved() {
        return removed;
    }

    public void setRemoved(Boolean removed) {
        this.removed = removed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id == post.id && Objects.equals(content, post.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content);
    }
}
