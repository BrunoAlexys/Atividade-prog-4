package br.com.voting.vote.services;

import br.com.voting.vote.dto.VoteDTO;
import br.com.voting.vote.models.ResultVote;

public interface VoteService {
    void vote(VoteDTO voteDTO);
    ResultVote voteResult(Long topicID);
}
