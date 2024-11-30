package com.example.sbb.Member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    //로그인
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    //회원가입
    @GetMapping("/join")
    public String join() {
        return "join";
    }

    @Getter
    @Setter
    public static class JoinForm{

        @NotBlank
        private String username;
        @NotBlank
        private String password;
        @NotBlank
        private String passwordConfirm;

        @NotBlank
        @Email
        private String email;

    }

    @PostMapping("/join")
    public String login(JoinForm joinForm){

        if(!joinForm.getPassword().equals(joinForm.getPasswordConfirm())) {
            throw new RuntimeException("비밀번호가 일치하지 않음");
        }

        memberService.join(joinForm.getUsername(),joinForm.getPassword(),joinForm.getEmail());

        return "redirect:/member/login";
    }



}
