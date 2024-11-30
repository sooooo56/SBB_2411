package com.example.sbb.Member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Member> memberOpt = memberRepository.findByUsername(username);

        if(memberOpt.isEmpty()) {
            throw new RuntimeException("잘못된 회원 정보입니다.");
        }

        Member member = memberOpt.get();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        User loginedUser = new User(member.getUsername(), member.getPassword(), authorities);

        return loginedUser;
    }
}
