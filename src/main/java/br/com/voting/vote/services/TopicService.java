package br.com.voting.vote.services;

import br.com.voting.vote.dto.CreateTopicDTO;

public interface TopicService {
    void createTopic(CreateTopicDTO topicDTO);
}
