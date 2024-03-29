package br.com.voting.vote.services;

import br.com.voting.vote.dto.AssociateDTO;
import br.com.voting.vote.models.Associate;

import java.util.List;

public interface AssociateService {
    void create(AssociateDTO associateDTO);

    List<Associate> getAssociates();

    Associate getAssociate(String id);

    void deleteAssociate(String id);

    void updateAssociate(String id, AssociateDTO associateDTO);
}
