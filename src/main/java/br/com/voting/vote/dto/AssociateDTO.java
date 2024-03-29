package br.com.voting.vote.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record AssociateDTO(
        @NotBlank(message = "Name is required")
        String name,
        @NotBlank(message = "CPF is required")
        @CPF(message = "CPF is invalid")
        String cpf

) {
}
