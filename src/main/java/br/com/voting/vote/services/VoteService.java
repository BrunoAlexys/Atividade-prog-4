package br.com.voting.vote.services;

import br.com.voting.vote.dto.VoteDTO;

public interface VoteService {
    void vote(VoteDTO voteDTO);
    int countVotesYes(Long topicID);
    int countVotesNo(Long topicID);
    boolean approvedTopic(Long topicID);
}
