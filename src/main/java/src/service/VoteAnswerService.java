package src.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import src.domain.VoteAnswer;
import src.repository.VoteAnswerRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class VoteAnswerService {

    @Autowired
    VoteAnswerRepository voteAnswerRepository;

    public void save(VoteAnswer voteAnswer) {
        voteAnswerRepository.saveAndFlush(voteAnswer);
    }

    public void delete(VoteAnswer voteAnswer){
        VoteAnswer deletedVote = voteAnswerRepository
                                    .findAll()
                                    .stream()
                                    .filter(vA -> vA.getAnswer().getIdAnswer().equals(voteAnswer.getAnswer().getIdAnswer())
                                        && vA.getUserV().getId().equals(voteAnswer.getUserV().getId()))
                                    .findAny()
                                    .get();

        voteAnswerRepository.delete(deletedVote);
        voteAnswerRepository.flush();
    }

    public Boolean checkVote(long idAnswer, long idUser) {
        return voteAnswerRepository
                    .findAll()
                    .stream()
                    .anyMatch(vA -> vA.getAnswer().getIdAnswer().equals(idAnswer)
                        && vA.getUserV().getId().equals(idUser));
    }
}
