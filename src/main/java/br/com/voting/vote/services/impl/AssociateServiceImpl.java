package br.com.voting.vote.services.impl;

import br.com.voting.vote.dto.AssociateDTO;
import br.com.voting.vote.models.Associate;
import br.com.voting.vote.repository.AssociateRepository;
import br.com.voting.vote.services.AssociateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AssociateServiceImpl implements AssociateService {

    private final AssociateRepository associateRepository;

    public AssociateServiceImpl(AssociateRepository associateRepository) {
        this.associateRepository = associateRepository;
    }

    @Transactional
    @Override
    public void create(AssociateDTO associateDTO) {
        var associate = new Associate(associateDTO);
        associateRepository.save(associate);
    }

    @Override
    public List<Associate> getAssociates() {
        return associateRepository.findAll();
    }

    @Override
    public Associate getAssociate(String id) {
        return associateRepository.findById(Long.parseLong(id)).orElseThrow(() -> new RuntimeException("Associate not found"));
    }

    @Override
    public void deleteAssociate(String id) {
        var associate = getAssociate(id);

        if (associate != null) {
            associateRepository.delete(associate);
        }

    }

    @Transactional
    @Override
    public void updateAssociate(String id, AssociateDTO associateDTO) {
        var associate = getAssociate(id);

        if(associateDTO.name() != null) {
            associate.setName(associateDTO.name());
        }

        if(associateDTO.cpf() != null) {
            associate.setCpf(associateDTO.cpf());
        }
    }
}
