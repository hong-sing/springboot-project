package com.ewok.springbootproject.service;

import com.ewok.springbootproject.domain.posts.Posts;
import com.ewok.springbootproject.domain.posts.PostsRepository;
import com.ewok.springbootproject.web.dto.PostSaveRequestDto;
import com.ewok.springbootproject.web.dto.PostsDetailResponseDto;
import com.ewok.springbootproject.web.dto.PostsListResponseDto;
import com.ewok.springbootproject.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    public Long save(PostSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional(readOnly = true) // readOnly = true 트랙잭션 범위는 유지하되, 조회기능만 남겨 조회 속도 개선. / 등록, 수정, 삭제 기능이 없는 서비스 메소드에서 사용 추천
    public List<PostsListResponseDto> findByLoginGoodDesc(String login) {
        return postsRepository.findByLoginGoodDesc(login).stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PostsDetailResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new PostsDetailResponseDto(entity);
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        posts.update(requestDto.getMeme(), requestDto.getSummary(), requestDto.getDescription(), requestDto.getReference(), requestDto.getAuthor());
        return id;
    }
}
