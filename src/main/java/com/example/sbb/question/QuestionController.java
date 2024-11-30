package com.example.sbb.question;

import com.example.sbb.Member.Member;
import com.example.sbb.Member.MemberService;
import com.example.sbb.answer.AnswerForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;
    private final MemberService memberService;

    // 게시글 조회
    @GetMapping("/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page){
        Page<Question> paging = this.questionService.getList(page);
        model.addAttribute("paging",paging);
        return "question_list";
    }

    // 상세보기
    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm){
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question",question);
        return "question_detail";
    }

    // 질문 등록
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm){
        return "question_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String questionCreate(Model model, @Valid QuestionForm questionForm, BindingResult bindingResult,
                                 Principal principal){
        if (bindingResult.hasErrors()){
            return "question_form";
        }
        Member siteUser = this.memberService.getUser(principal.getName());
        this.questionService.create(questionForm.getTitle(),questionForm.getContent(),siteUser);
        return "redirect:/question/list";
    }

}
