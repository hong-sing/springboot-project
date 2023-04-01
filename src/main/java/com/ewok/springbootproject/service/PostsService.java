package com.ewok.springbootproject.service;

import com.ewok.springbootproject.domain.posts.PostsRepository;
import com.ewok.springbootproject.web.dto.PostSaveRequestDto;
import com.ewok.springbootproject.web.dto.PostsListResponseDto;
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
}
