package br.com.voting.vote.controller;

import br.com.voting.vote.dto.VoteDTO;
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

    @GetMapping("/countYes/{topicID}")
    public ResponseEntity<Integer> countVotesYes(@PathVariable Long topicID) {
        return ResponseEntity.ok(voteService.countVotesYes(topicID));
    }

    @GetMapping("/countNo/{topicID}")
    public ResponseEntity<Integer> countVotesNo(@PathVariable Long topicID) {
        return ResponseEntity.ok(voteService.countVotesNo(topicID));
    }

    @GetMapping("/result/{topicID}")
    public ResponseEntity<Boolean> voteResult(@PathVariable Long topicID) {
        return ResponseEntity.ok(voteService.approvedTopic(topicID));
    }

}
