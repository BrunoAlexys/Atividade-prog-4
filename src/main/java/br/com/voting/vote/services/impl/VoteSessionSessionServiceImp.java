package br.com.voting.vote.services.impl;

import br.com.voting.vote.dto.OpenSessionDTO;
import br.com.voting.vote.enums.StatusVotingSession;
import br.com.voting.vote.models.VotingSession;
import br.com.voting.vote.repository.TopicRepository;
import br.com.voting.vote.repository.VoteRepository;
import br.com.voting.vote.repository.VotingSessionRepository;
import br.com.voting.vote.services.VoteSessionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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

        if (openSessionDTO.startTime() != null) {
            votingSession.setStartSession(openSessionDTO.startTime());
        } else {
            votingSession.setStartSession(LocalDateTime.now());
        }

        if (openSessionDTO.endTime() != null) {
            votingSession.setEndSession(openSessionDTO.endTime());
        } else {
            votingSession.setEndSession(LocalDateTime.now().plusMinutes(1));
        }

        var topic = topicRepository.findById(Long.parseLong(openSessionDTO.topicId())).orElseThrow(() -> new RuntimeException("Topic not found"));

        votingSession.setTopic(topic);
        votingSession.setStatus(StatusVotingSession.OPEN);

        votingSessionRepository.save(votingSession);
    }

}
