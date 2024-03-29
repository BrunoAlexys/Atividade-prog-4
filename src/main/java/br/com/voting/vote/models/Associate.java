package br.com.voting.vote.models;

import br.com.voting.vote.dto.AssociateDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "associates")
public class Associate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String cpf;

    public Associate() {}

    public Associate(AssociateDTO dto) {
        this.name = dto.name();
        this.cpf = dto.cpf();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
