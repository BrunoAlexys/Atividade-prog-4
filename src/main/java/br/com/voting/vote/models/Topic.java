package br.com.voting.vote.models;

import br.com.voting.vote.dto.CreateTopicDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "topics")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Topic() {}

    public Topic(CreateTopicDTO dto) {
        this.name = dto.name();
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
}
