package br.com.voting.vote.controller;

import br.com.voting.vote.dto.VoteDTO;
import br.com.voting.vote.models.ResultVote;
import br.com.voting.vote.services.VoteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/vote")
public class VoteController {

    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping
    public ResponseEntity<Void> vote(@RequestBody @Valid VoteDTO voteDTO) {
        voteService.vote(voteDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/result/{topicID}")
    public ResponseEntity<ResultVote> resultVote(@PathVariable String topicID) {
        ResultVote resultVote = voteService.voteResult(Long.parseLong(topicID));
        return ResponseEntity.ok(resultVote);
    }

}
