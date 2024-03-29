package br.com.voting.vote.services.impl;

import br.com.voting.vote.dto.VoteDTO;
import br.com.voting.vote.enums.TypeVote;
import br.com.voting.vote.models.Vote;
import br.com.voting.vote.repository.AssociateRepository;
import br.com.voting.vote.repository.TopicRepository;
import br.com.voting.vote.repository.VoteRepository;
import br.com.voting.vote.repository.VotingSessionRepository;
import br.com.voting.vote.services.VoteService;
import org.springframework.stereotype.Service;

@Service
public class VoteServiceImp implements VoteService {

    private final VotingSessionRepository votingSessionRepository;
    private final VoteRepository voteRepository;
    private final AssociateRepository associateRepository;
    private final TopicRepository topicRepository;

    public VoteServiceImp(VotingSessionRepository votingSessionRepository, VoteRepository voteRepository, AssociateRepository associateRepository, TopicRepository topicRepository) {
        this.votingSessionRepository = votingSessionRepository;
        this.voteRepository = voteRepository;
        this.associateRepository = associateRepository;
        this.topicRepository = topicRepository;
    }

    @Override
    public void vote(VoteDTO voteDTO) {
        var votingSession = votingSessionRepository.findById(voteDTO.sessionID());
        var associate = associateRepository.findById(voteDTO.associateID());
        var topic = topicRepository.findById(voteDTO.topicID());
        var vote = new Vote();

        if (votingSession.isEmpty()) {
            throw new RuntimeException("Voting session not found");
        }

        if (associate.isEmpty()) {
            throw new RuntimeException("Associate not found");
        }

        if (topic.isEmpty()) {
            throw new RuntimeException("Topic not found");
        }

        boolean hasVoted = voteRepository
                .existsByAssociateAndTopic(associate.get(), topic.get());

        if (hasVoted) {
            throw new RuntimeException("Associate has already voted");
        }

        associate.get().setId(voteDTO.associateID());
        vote.setAssociate(associate.get());
        vote.setTopic(votingSession.get().getTopic());
        vote.setVotingSession(votingSession.get());
        vote.setTypeVote(voteDTO.typeVote());

        voteRepository.save(vote);
    }

    @Override
    public int countVotesYes(Long topicID) {
        var topic = topicRepository.findById(topicID);

        if (topic.isEmpty()) {
            throw new RuntimeException("Topic not found");
        }

        int votesYes = voteRepository.countByTopicAndTypeVote(topic.get(), TypeVote.SIM);

        return votesYes;
    }

    @Override
    public int countVotesNo(Long topicID) {
        var topic = topicRepository.findById(topicID);

        if (topic.isEmpty()) {
            throw new RuntimeException("Topic not found");
        }

        return voteRepository.countByTopicAndTypeVote(topic.get(), TypeVote.NAO);
    }

    @Override
    public boolean approvedTopic(Long topicID) {
        int votesYes = countVotesYes(topicID);
        int votesNo = countVotesNo(topicID);

        return votesYes > votesNo ? true : false;
    }
}
