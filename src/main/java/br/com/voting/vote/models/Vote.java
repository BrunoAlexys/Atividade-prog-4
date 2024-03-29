package br.com.voting.vote.models;

import br.com.voting.vote.enums.TypeVote;
import jakarta.persistence.*;

@Entity
@Table(name = "votes")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_associate")
    private Associate associate;

    @ManyToOne
    @JoinColumn(name = "id_topic")
    private Topic topic;

    @Column(name = "type_vote")
    @Enumerated(EnumType.STRING)
    private TypeVote typeVote;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_voting_session", nullable = false)
    private VotingSession votingSession;

    public Vote(Associate associate, Topic topic, TypeVote typeVote, VotingSession votingSession) {
        this.associate = associate;
        this.topic = topic;
        this.typeVote = typeVote;
        this.votingSession = votingSession;
    }

    public Vote() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Associate getAssociate() {
        return associate;
    }

    public void setAssociate(Associate associate) {
        this.associate = associate;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public TypeVote getTypeVote() {
        return typeVote;
    }

    public void setTypeVote(TypeVote typeVote) {
        this.typeVote = typeVote;
    }

    public VotingSession getVotingSession() {
        return votingSession;
    }

    public void setVotingSession(VotingSession votingSession) {
        this.votingSession = votingSession;
    }
}
