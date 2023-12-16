package ru.example.service;


import org.springframework.stereotype.Service;
import ru.example.dto.PostDto;
import ru.example.exception.NotFoundException;
import ru.example.mapper.PostMapper;
import ru.example.model.Post;
import ru.example.repository.PostRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private static final String ERROR_MESSAGE = "Запись с id=%d отсутствует";
    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public List<PostDto> all() {
        return PostMapper.toListDto(repository.all()
                .stream()
                .filter(post -> !post.getRemoved())
                .collect(Collectors.toList()));
    }

    public PostDto getById(long id) {
        Post p = repository.getById(id).orElseThrow(() ->
                new NotFoundException(String.format(ERROR_MESSAGE, id)));
        if (p.getRemoved())
            throw new NotFoundException(String.format(ERROR_MESSAGE, id));
        return PostMapper.toDto(p);
    }

    public PostDto save(PostDto postDto) {
        PostDto p = postDto;
        if (postDto.getId() != 0)
            p = getById(postDto.getId());
        Post result = repository.save(PostMapper.toPost(p));
        if (result == null)
            throw new NotFoundException(
                    String.format("Не удалось обновить запись с id = %d т.к. она отсутствует",
                            postDto.getId()));
        return PostMapper.toDto(result);
    }

    public void removeById(long id) {
        repository.removeById(id).orElseThrow(() ->
                new NotFoundException(String.format("Запись с id=%d отсутствует", id)));
    }
}

