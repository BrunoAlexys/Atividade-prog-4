package br.com.voting.vote.models;

import br.com.voting.vote.enums.ResultVoteEnum;

public class ResultVote {
    private int voteYes;
    private int voteNo;
    private ResultVoteEnum resultVote;

    public int getVoteYes() {
        return voteYes;
    }

    public void setVoteYes(int voteYes) {
        this.voteYes = voteYes;
    }

    public int getVoteNo() {
        return voteNo;
    }

    public void setVoteNo(int voteNo) {
        this.voteNo = voteNo;
    }

    public ResultVoteEnum getResultVote() {
        return resultVote;
    }

    public void setResultVote(ResultVoteEnum resultVote) {
        this.resultVote = resultVote;
    }
}
