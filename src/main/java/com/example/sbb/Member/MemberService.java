package com.example.sbb.Member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void join(String username, String password,String email){
        if(memberRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("이미 존재하는 아이디입니다.");
        }

        Member member = Member.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .build();

        memberRepository.save(member);
    }

    public Member getUser(String username){
        Optional<Member> siteUser = this.memberRepository.findByUsername(username);
        if (siteUser.isPresent()){
            return siteUser.get();
        } else {
            throw new RuntimeException("siteuser not found");
        }

    }
}
