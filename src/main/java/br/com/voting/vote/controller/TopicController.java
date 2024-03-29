package br.com.voting.vote.controller;

import br.com.voting.vote.dto.CreateTopicDTO;
import br.com.voting.vote.services.TopicService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topic")
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @PostMapping
    public ResponseEntity<Void> createTopic(@RequestBody @Valid CreateTopicDTO topicDTO) {
        topicService.createTopic(topicDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
