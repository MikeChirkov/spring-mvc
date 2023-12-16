package ru.example.mapper;

import ru.example.dto.PostDto;
import ru.example.model.Post;

import java.util.List;
import java.util.stream.Collectors;

public class PostMapper {
    public static PostDto toDto(Post post) {
        return new PostDto(post.getId(), post.getContent());
    }

    public static List<PostDto> toListDto(List<Post> posts) {
        return posts.stream()
                .map(post -> new PostDto(post.getId(), post.getContent()))
                .collect(Collectors.toList());
    }

    public static Post toPost(PostDto postDto) {
        return new Post(postDto.getId(), postDto.getContent(), false);
    }
}
