package br.com.voting.vote.dto;

import br.com.voting.vote.enums.TypeVote;
import jakarta.validation.constraints.NotNull;

public record VoteDTO(
        @NotNull(message = "Associate ID is required")
        Long associateID,
        @NotNull(message = "Topic ID is required")
        Long topicID,
        @NotNull(message = "session ID is required")
        Long sessionID,
        @NotNull(message = "Type vote is required")
        TypeVote typeVote
) {
}
