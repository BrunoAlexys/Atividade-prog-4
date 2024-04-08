package br.com.voting.vote.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record OpenSessionDTO(
        @NotNull(message = "Topic ID is required")
        String topicId,
        LocalDateTime startTime,
        LocalDateTime endTime
) {
}
