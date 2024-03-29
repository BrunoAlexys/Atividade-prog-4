package br.com.voting.vote.services;

import br.com.voting.vote.dto.OpenSessionDTO;

public interface VoteSessionService {
    void openSession(OpenSessionDTO openSessionDTO);
    void updateExpiredVotingSessions();
}
