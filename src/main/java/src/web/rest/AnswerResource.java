package src.web.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.domain.Answer;
import src.service.AnswerService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/ourApi")
public class AnswerResource {

    @Autowired
    private AnswerService answerService;


    @GetMapping("/answers")
    public ResponseEntity<List<Answer>> getAnswersToQuestion(@RequestBody long questionId){
        return new ResponseEntity<>(answerService.getAnswersForQuestion(questionId), HttpStatus.OK);
    }

}
