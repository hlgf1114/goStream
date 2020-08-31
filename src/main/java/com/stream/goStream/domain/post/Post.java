package com.stream.goStream.domain.post;


import com.stream.goStream.domain.BaseTimeEntity;
import com.stream.goStream.domain.file.File;
import com.stream.goStream.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "post")
public class Post extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(cascade = CascadeType.ALL)
    private File file;


    @Column(nullable = false, length = 100)
    private String title;

    @Column(length = 1000)
    private String content;

    @Builder
    public Post(Member member, String title, String content, File file) {
        this.member = member;
        this.title = title;
        this.content = content;
        this.file = file;
    }
}
