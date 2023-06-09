package com.ewok.springbootproject.domain.posts;

import com.ewok.springbootproject.domain.BaseTimeEntity;
import com.ewok.springbootproject.domain.user.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String meme;

    @Column
    private String summary;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column
    private String reference;

    @Column
    private String author;

    @Column
    @ColumnDefault("0")
    private int good;

    @Column
    private String login;

    @Column
    private String sub;

    @Builder
    public Posts(String meme, String summary, String description, String reference, String author, String login, int good, String sub) {
        this.meme = meme;
        this.summary = summary;
        this.description = description;
        this.reference = reference;
        this.author = author;
        this.login = login;
        this.good = good;
        this.sub = sub;
    }

    public void update(String meme, String summary, String description, String reference, String author) {
        this.meme = meme;
        this.summary = summary;
        this.description = description;
        this.reference = reference;
        this.author = author;
    }
}
