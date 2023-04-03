package com.ewok.springbootproject.service;

import com.ewok.springbootproject.domain.posts.Posts;
import com.ewok.springbootproject.domain.posts.PostsRepository;
import com.ewok.springbootproject.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    // 페이징
    @Transactional(readOnly = true) // readOnly = true 트랙잭션 범위는 유지하되, 조회기능만 남겨 조회 속도 개선. / 등록, 수정, 삭제 기능이 없는 서비스 메소드에서 사용 추천
    public ResponseEntity<PostsPagingListResponseDto> findByLoginGoodDesc2(String login, Pageable pageable) {
//        return postsRepository.findByLoginGoodDesc(login, pageable).stream()
//                .map(PostsListResponseDto::new)
//                .collect(Collectors.toList());

        Page<Posts> result = postsRepository.findByLoginGoodDesc2(login, pageable);

//        List<PostsPagingListResponseDto> list = result.stream().map(posts -> new PostsPagingListResponseDto(result.getContent(), (long) result.getTotalPages(), result.getTotalElements())).collect(Collectors.toList());

//        return list;

        return new ResponseEntity<>(PostsPagingListResponseDto.builder()
                .postsList(result.getContent())
                .totalCount(result.getTotalElements())
                .totalPage((long) result.getTotalPages())
                .build(), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public PostsDetailResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 글이 없습니다. id=" + id));
        return new PostsDetailResponseDto(entity);
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 글이 없습니다. id=" + id));
        posts.update(requestDto.getMeme(), requestDto.getSummary(), requestDto.getDescription(), requestDto.getReference(), requestDto.getAuthor());
        return id;
    }

    public void delete(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 글이 없습니다. id=" + id));
        postsRepository.delete(posts);
    }
}
