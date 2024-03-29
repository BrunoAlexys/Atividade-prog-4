package br.com.voting.vote.services.impl;

import br.com.voting.vote.dto.CreateTopicDTO;
import br.com.voting.vote.models.Topic;
import br.com.voting.vote.repository.TopicRepository;
import br.com.voting.vote.services.TopicService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TopicServiceImp implements TopicService {

    private final TopicRepository topicRepository;

    public TopicServiceImp(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Transactional
    @Override
    public void createTopic(CreateTopicDTO topicDTO) {
        var topic = new Topic(topicDTO);
        topicRepository.save(topic);
    }
}
