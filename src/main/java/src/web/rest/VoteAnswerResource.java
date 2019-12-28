package src.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.service.VoteAnswerService;

import javax.transaction.Transactional;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/ourApi")
public class VoteAnswerResource {

    @Autowired
    private VoteAnswerService voteAnswerService;

    @PostMapping("/isvoted")
    @Transactional
    public ResponseEntity<Boolean> isReplyVoted(@RequestBody Map<String, Object> answerVotedMap){
        Map<String, Object> map = (Map<String, Object>) answerVotedMap.get("answerVotedMap");

        Integer idAnswer = (Integer) map.get("idAnswer");
        Integer idUser = (Integer) map.get("idUser");

        return new ResponseEntity<>(voteAnswerService.checkVote((long)idAnswer, (long)idUser),HttpStatus.OK);
    }
}
