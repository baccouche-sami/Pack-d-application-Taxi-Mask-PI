package fr.et.intechinfo.mousqinfos.taximask.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.et.intechinfo.mousqinfos.taximask.models.Prix;
import fr.et.intechinfo.mousqinfos.taximask.services.PrixService;

@RestController
@RequestMapping("/api/test")
public class PrixController {
	
	@Autowired
	PrixService prixService;
	
	
	@GetMapping("/prix")
    public ResponseEntity < List < Prix >> getAllPrix() {
        return ResponseEntity.ok().body(prixService.getAllPrix());
    }
	
	@PutMapping("/prix/{id}")
    public ResponseEntity < Prix > updatePrix(@PathVariable long id, @RequestBody Prix prix) {
		prix.setId(id);
        return ResponseEntity.ok().body(this.prixService.updatePrix(prix));
    }

}
