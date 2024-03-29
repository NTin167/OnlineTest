package com.ptithcm.onlinetest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ptithcm.onlinetest.model.audit.DateAudit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "quiz_answer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizAnswer extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int active;

    private int correct;

    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    private QuizQuestion quizQuestion;

    @ManyToOne(fetch = FetchType.EAGER)
    private Quiz quiz;

//    @OneToMany(mappedBy = "quizAnswer")
//    @JsonIgnore
//    private Set<TakeAnswer> takeAnswers = new HashSet<>();
}
