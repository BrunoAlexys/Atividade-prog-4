package br.com.voting.vote.repository;

import br.com.voting.vote.enums.StatusVotingSession;
import br.com.voting.vote.models.VotingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface VotingSessionRepository extends JpaRepository<VotingSession, Long> {
    @Query("SELECT v FROM Vote v WHERE v.topic.id = :topicID AND v.votingSession.status = :statusVotingSession")
    VotingSession findByTopicIdAndSatus(Long topicID, StatusVotingSession statusVotingSession);

    List<VotingSession> findByStatus(StatusVotingSession statusVotingSession);
}