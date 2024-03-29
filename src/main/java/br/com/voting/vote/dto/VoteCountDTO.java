package br.com.voting.vote.dto;

import br.com.voting.vote.models.Vote;

public record VoteCountDTO(
        String topic,
        Long yes,
        Long no,
        Long total
) {
    public VoteCountDTO(Vote vote) {
        this(vote.getTopic().getName(), vote.getTypeVote().equals("SIM") ? 1L : 0L, vote.getTypeVote().equals("NAO") ? 1L : 0L, 1L);
    }
}
