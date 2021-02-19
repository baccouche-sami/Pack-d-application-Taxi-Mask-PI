package fr.et.intechinfo.mousqinfos.taximask.controllers;

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

import fr.et.intechinfo.mousqinfos.taximask.models.Avis;
import fr.et.intechinfo.mousqinfos.taximask.services.AvisService;


@RestController
@RequestMapping("/api/test")
public class AvisController {
	
	@Autowired
	private AvisService avisservice;
	
	@GetMapping("/avis")
    public ResponseEntity < List < Avis >> getAllAvis() {
        return ResponseEntity.ok().body(avisservice.getAllAvis());
    }

    @GetMapping("/avis/{id}")
    public ResponseEntity < Avis > getAvisById(@PathVariable long id) {
        return ResponseEntity.ok().body(avisservice.getAvisById(id));
    }

    @PostMapping("/avis")
    public ResponseEntity < Avis > createAvis(@RequestBody Avis Avis) {
        return ResponseEntity.ok().body(this.avisservice.createAvis(Avis));
    }

    @PutMapping("/avis/{id}")
    public ResponseEntity < Avis > updateAvis(@PathVariable long id, @RequestBody Avis Avis) {
        Avis.setId(id);
        return ResponseEntity.ok().body(this.avisservice.validerAvis(Avis));
    }

    @DeleteMapping("/avis/{id}")
    public HttpStatus deleteAvis(@PathVariable long id) {
        this.avisservice.deleteAvis(id);
        return HttpStatus.OK;
    }

}
