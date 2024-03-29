package br.com.voting.vote.controller;

import br.com.voting.vote.dto.OpenSessionDTO;
import br.com.voting.vote.services.VoteSessionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vote/voting-session")
public class VotingSessionController {

    private final VoteSessionService voteSessionService;

    public VotingSessionController(VoteSessionService voteSessionService) {
        this.voteSessionService = voteSessionService;
    }

    @PostMapping
    public ResponseEntity<Void> openSession(@RequestBody @Valid OpenSessionDTO openSessionDTO) {
        voteSessionService.openSession(openSessionDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
