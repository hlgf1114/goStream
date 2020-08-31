package com.stream.goStream.service.member;

import com.stream.goStream.domain.member.Member;
import com.stream.goStream.domain.member.MemberRepository;
import com.stream.goStream.domain.member.dto.MemberGetResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public Member findMember(Long id) {

        Member member = memberRepository.findById(id).get();

        if(member == null) {
            System.out.println("----------------------------");
            System.out.println("사용자 없음");
            System.out.println("----------------------------");
            return null;
        }

        return member;

    }

}
