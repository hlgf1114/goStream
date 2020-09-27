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

    public MemberGetResponseDto findMemberById(Long id) {

        Member member = memberRepository.findById(id).get();

        if(member == null) {
            System.out.println("----------------------------");
            System.out.println("사용자 없음");
            System.out.println("----------------------------");
            return null;
        }

        MemberGetResponseDto dto = MemberGetResponseDto.builder()
                .id(member.getId())
                .email(member.getEmail())
                .name(member.getName())
                .picture(member.getPicture())
                .postList(member.getPostList())
                .build();

        return dto;

    }

    public Member findMemberEntityById(Long id) {

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
