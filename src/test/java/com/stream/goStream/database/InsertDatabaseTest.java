package com.stream.goStream.database;

import com.stream.goStream.domain.member.Member;
import com.stream.goStream.domain.member.MemberRepository;
import com.stream.goStream.domain.member.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InsertDatabaseTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 삽입_구문_테스트() {

        Member member = Member.builder()
                .name("김")
                .email("email")
                .picture("dsd")
                .role(Role.USER)
                .build();

        memberRepository.save(member);


    }

}
