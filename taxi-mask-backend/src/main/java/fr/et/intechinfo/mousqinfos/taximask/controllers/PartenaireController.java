package fr.et.intechinfo.mousqinfos.taximask.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.et.intechinfo.mousqinfos.taximask.models.Partenaire;
import fr.et.intechinfo.mousqinfos.taximask.services.PartenaireService;

@RestController
@RequestMapping("/api/test")
public class PartenaireController {
	
	@Autowired
	private PartenaireService partenaireservice;
	
	@GetMapping("/partenaires")
    public ResponseEntity < List < Partenaire >> getAllPartenaire() {
        return ResponseEntity.ok().body(partenaireservice.getAllPartenaire());
    }

    @GetMapping("/partenaires/{id}")
    public ResponseEntity < Partenaire > getPartenaireById(@PathVariable long id) {
        return ResponseEntity.ok().body(partenaireservice.getPartenaireById(id));
    }

    @PostMapping("/partenaires")
    public ResponseEntity < Partenaire > createPartenaire(@RequestBody Partenaire partenaire) throws IOException {
        return ResponseEntity.ok().body(this.partenaireservice.createPartenaire(partenaire));
    }

    @PutMapping("/partenaires/{id}")
    public ResponseEntity < Partenaire > updatePartenaire(@PathVariable long id, @RequestBody Partenaire Partenaire) throws IOException {
        Partenaire.setId(id);
        return ResponseEntity.ok().body(this.partenaireservice.updatePartenaire(Partenaire));
    }

    @DeleteMapping("/partenaires/{id}")
    public HttpStatus deletePartenaire(@PathVariable long id) {
        this.partenaireservice.deletePartenaire(id);
        return HttpStatus.OK;
    }
	
	
	

}
