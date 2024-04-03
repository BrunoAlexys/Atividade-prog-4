package br.com.voting.vote.services.impl;

import br.com.voting.vote.dto.VoteDTO;
import br.com.voting.vote.enums.ResultVoteEnum;
import br.com.voting.vote.enums.StatusVotingSession;
import br.com.voting.vote.enums.TypeVote;
import br.com.voting.vote.exception.*;
import br.com.voting.vote.models.ResultVote;
import br.com.voting.vote.models.Vote;
import br.com.voting.vote.repository.AssociateRepository;
import br.com.voting.vote.repository.TopicRepository;
import br.com.voting.vote.repository.VoteRepository;
import br.com.voting.vote.repository.VotingSessionRepository;
import br.com.voting.vote.services.VoteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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

    @Transactional(noRollbackFor = {SessionExpiredException.class})
    @Override
    public void vote(VoteDTO voteDTO) {
        var votingSession = votingSessionRepository.findById(voteDTO.sessionID())
                .orElseThrow(() -> new SessionNotFoundException("Session not found!"));
        var associate = associateRepository.findById(voteDTO.associateID())
                .orElseThrow(() -> new AssociateNotFoundException("Associate not found!"));
        var topic = topicRepository.findById(voteDTO.topicID())
                .orElseThrow(() -> new TopicNotFoundException("topic not found!"));

        boolean hasVoted = voteRepository
                .existsByAssociateAndTopic(associate, topic);


        if (votingSession.getEndSession().isBefore(LocalDateTime.now())) {
            //Não está alterando o Status no banco
            votingSession.setStatus(StatusVotingSession.CLOSED);
            votingSessionRepository.save(votingSession);
            throw new SessionExpiredException("Session expired!");
        } else if (hasVoted) {
            throw new AssociateHasAlreadyVotedException("Associate has already voted");
        } else {
            var vote = new Vote();
            associate.setId(voteDTO.associateID());
            vote.setAssociate(associate);
            vote.setTopic(votingSession.getTopic());
            vote.setVotingSession(votingSession);
            vote.setTypeVote(voteDTO.typeVote());
            voteRepository.save(vote);
        }

    }

    @Override
    public ResultVote voteResult(Long topicID) {
        var topic = topicRepository.findById(topicID)
                .orElseThrow(() -> new TopicNotFoundException("Topic not found!"));

        int votesYes = voteRepository.countByTopicAndTypeVote(topic, TypeVote.SIM);
        int votesNo = voteRepository.countByTopicAndTypeVote(topic, TypeVote.NAO);

        var resultVote = new ResultVote();
        resultVote.setVoteYes(votesYes);
        resultVote.setVoteNo(votesNo);

        boolean result = resultVote.getVoteYes() > resultVote.getVoteNo();

        if (result) {
            resultVote.setResultVote(ResultVoteEnum.APPROVED);
        } else {
            resultVote.setResultVote(ResultVoteEnum.DISAPPROVED);
        }

        return resultVote;
    }
}
