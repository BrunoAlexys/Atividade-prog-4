package br.com.voting.vote.controller;

import br.com.voting.vote.dto.AssociateDTO;
import br.com.voting.vote.models.Associate;
import br.com.voting.vote.services.AssociateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/associate")
public class AssociateController {

    private AssociateService associateService;

    public AssociateController(AssociateService associateService) {
        this.associateService = associateService;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody AssociateDTO associateDTO) {
        associateService.create(associateDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Associate>> getAssociates() {
        return ResponseEntity.ok(associateService.getAssociates());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Associate> getAssociate(@PathVariable String id) {
        return ResponseEntity.ok(associateService.getAssociate(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssociate(@PathVariable String id) {
        associateService.deleteAssociate(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAssociate(@PathVariable String id, @RequestBody AssociateDTO associateDTO) {
        associateService.updateAssociate(id, associateDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
