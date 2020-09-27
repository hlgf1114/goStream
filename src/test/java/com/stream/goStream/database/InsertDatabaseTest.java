package com.stream.goStream.database;

import com.stream.goStream.domain.file.File;
import com.stream.goStream.domain.file.FileRepository;
import com.stream.goStream.domain.member.Member;
import com.stream.goStream.domain.member.MemberRepository;
import com.stream.goStream.domain.member.Role;
import com.stream.goStream.domain.post.Post;
import com.stream.goStream.domain.post.PostRepository;
import org.jcodec.common.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class InsertDatabaseTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    FileRepository fileRepository;

    @Autowired
    PostRepository postRepository;

    @Test
    public void 맴버_삽입_테스트() {


        // 데이터를 삽입해본다.
        Member member = Member.builder()
                .name("김")
                .email("email")
                .picture("dsd")
                .role(Role.USER)
                .build();

        Long id = memberRepository.save(member).getId();

        Member get = memberRepository.findById(id).get();
        
        // null 인지 확인
        assertNotNull(get);

        // 실패하는 경우
        assertNotEquals("error", get.getName());
        assertNotEquals("error", get.getEmail());
        assertNotEquals("error", get.getEmail());
        assertNotEquals(Role.ADMIN, get.getRole());

        // 성공하는 경우
        assertEquals("김", get.getName());
        assertEquals("email", get.getEmail());
        assertEquals("dsd", get.getPicture());
        assertEquals(Role.USER, get.getRole());


    }

    @Test
    public void 파일_삽입_테스트() {


        File file = new File().builder()
                .filePath("R://test.png")
                .fileSize(1203l)
                .originalFileName("너의_이름은_포스트")
                .build();


        Long id = fileRepository.save(file).getId();


        File get = fileRepository.findById(id).get();

        // null인지 아닌지 확인
        assertNotNull(file);

        // 틀린코드 넣어보기
        assertNotEquals("error", get.getFilePath());
        assertNotEquals(12, get.getFileSize().longValue());
        assertNotEquals("error", get.getOriginalFileName());

        // 맞는 코드 넣어보기
        assertEquals("R://test.png", get.getFilePath());
        assertEquals(1203l, get.getFileSize().longValue());
        assertEquals("너의_이름은_포스트", get.getOriginalFileName());

    }

    @Test
    public void 포스트_삽입_테스트() {

        Member member = Member.builder()
                .name("김")
                .email("email")
                .picture("dsd")
                .role(Role.USER)
                .build();

        memberRepository.save(member);

        File file = new File().builder()
                .filePath("R://test.png")
                .fileSize(1203l)
                .originalFileName("너의_이름은_포스트")
                .build();

        fileRepository.save(file);

        Post p = new Post().builder()
                .title("타이틀")
                .content("컨텐츠")
                .file(file)
                .member(member)
                .build();

        Long id = postRepository.save(p).getId();

        Post get = postRepository.findById(id).get();

        assertNotNull(get);

        // 틀린 코드
        assertNotEquals("error", get.getTitle());
        assertNotEquals("error", get.getContent());
        assertNotEquals(null, get.getMember());
        assertNotEquals(null, get.getFile());

        // 맞는 코드
        assertEquals("타이틀", get.getTitle());
        assertEquals("컨텐츠", get.getContent());
        assertEquals(file, get.getFile());
        assertEquals(member, get.getMember());

    }

}
