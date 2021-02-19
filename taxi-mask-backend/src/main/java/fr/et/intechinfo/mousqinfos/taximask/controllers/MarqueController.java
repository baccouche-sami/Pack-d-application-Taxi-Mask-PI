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

import fr.et.intechinfo.mousqinfos.taximask.models.Marque;
import fr.et.intechinfo.mousqinfos.taximask.services.MarqueService;

@RestController
@RequestMapping("/api/test")
public class MarqueController {
	
	@Autowired
	private MarqueService marqueservice;
	
	@GetMapping("/marques")
    public ResponseEntity < List < Marque >> getAllMarque() {
        return ResponseEntity.ok().body(marqueservice.getAllMarque());
    }

    @GetMapping("/marques/{id}")
    public ResponseEntity < Marque > getMarqueById(@PathVariable long id) {
        return ResponseEntity.ok().body(marqueservice.getMarqueById(id));
    }

    @PostMapping("/marques")
    public ResponseEntity < Marque > createMarque(@RequestBody Marque Marque) {
        return ResponseEntity.ok().body(this.marqueservice.createMarque(Marque));
    }

    @PutMapping("/marques/{id}")
    public ResponseEntity < Marque > updateMarque(@PathVariable long id, @RequestBody Marque Marque) {
        Marque.setId(id);
        return ResponseEntity.ok().body(this.marqueservice.updateMarque(Marque));
    }

    @DeleteMapping("/marques/{id}")
    public HttpStatus deleteMarque(@PathVariable long id) {
        this.marqueservice.deleteMarque(id);
        return HttpStatus.OK;
    }
	

}
