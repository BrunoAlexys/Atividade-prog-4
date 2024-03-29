package br.com.voting.vote.repository;

import br.com.voting.vote.models.Associate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssociateRepository extends JpaRepository<Associate, Long> {
}