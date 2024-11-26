package com.example.sbb.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    //리스트 조회
    public List<Question> getList() {
        return this.questionRepository.findAll();
    }

    // 상세
    public Question getQuestion(Integer id){
        Optional<Question> question = this.questionRepository.findById(id);
        if (question.isPresent()){
            return question.get();
        } else {
            throw new RuntimeException();
        }
    }

    // 질문 등록
    public void create(String content, String title){
        Question question = Question.builder()
                .title(title)
                .content(content)
                .createDate(LocalDateTime.now())
                .build();
        this.questionRepository.save(question);
    }
}
