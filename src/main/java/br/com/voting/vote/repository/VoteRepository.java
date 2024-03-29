package br.com.voting.vote.repository;

import br.com.voting.vote.enums.TypeVote;
import br.com.voting.vote.models.Associate;
import br.com.voting.vote.models.Topic;
import br.com.voting.vote.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    boolean existsByAssociateAndTopic(Associate associate, Topic topic);

    int countByTopicAndTypeVote(Topic topic, TypeVote typeVote);
}