package br.com.voting.vote.services.impl;

import br.com.voting.vote.dto.OpenSessionDTO;
import br.com.voting.vote.enums.StatusVotingSession;
import br.com.voting.vote.models.VotingSession;
import br.com.voting.vote.repository.TopicRepository;
import br.com.voting.vote.repository.VoteRepository;
import br.com.voting.vote.repository.VotingSessionRepository;
import br.com.voting.vote.services.VoteSessionService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VoteSessionSessionServiceImp implements VoteSessionService {

    private final VotingSessionRepository votingSessionRepository;
    private final VoteRepository voteRepository;
    private final TopicRepository topicRepository;

    public VoteSessionSessionServiceImp(VotingSessionRepository votingSessionRepository, VoteRepository voteRepository, TopicRepository topicRepository) {
        this.votingSessionRepository = votingSessionRepository;
        this.voteRepository = voteRepository;
        this.topicRepository = topicRepository;
    }

    @Transactional
    @Override
    public void openSession(OpenSessionDTO openSessionDTO) {
        var votingSession = new VotingSession();
        votingSession.setStartSession(LocalDateTime.now());

        if (openSessionDTO.endTime() != null) {
            votingSession.setEndSession(openSessionDTO.endTime());
        } else {
            votingSession.setEndSession(LocalDateTime.now().plusMinutes(1));
        }

        var topic = topicRepository.findById(openSessionDTO.topicId()).orElseThrow(() -> new RuntimeException("Topic not found"));

        votingSession.setTopic(topic);
        votingSession.setStatus(StatusVotingSession.OPEN);

        votingSessionRepository.save(votingSession);
    }

    @Transactional
    @Scheduled(fixedDelay = 60000) // Executa a cada minuto
    @Override
    public void updateExpiredVotingSessions() {
        List<VotingSession> votingSessions = votingSessionRepository.findByStatus(StatusVotingSession.OPEN);

        votingSessions.forEach(votingSession -> {
            if (votingSession.getEndSession().isBefore(LocalDateTime.now())) {
                votingSession.setStatus(StatusVotingSession.CLOSED);
                votingSessionRepository.save(votingSession);
            }
        });
    }
}
