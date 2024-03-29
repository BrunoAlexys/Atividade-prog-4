package br.com.voting.vote.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateTopicDTO(
        @NotBlank(message = "Topic is required")
        String name) {
}
