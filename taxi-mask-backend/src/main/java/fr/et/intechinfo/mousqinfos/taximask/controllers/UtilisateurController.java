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
import org.springframework.web.bind.annotation.RestController;

import fr.et.intechinfo.mousqinfos.taximask.models.Utilisateur;
import fr.et.intechinfo.mousqinfos.taximask.services.UtilisateurService;

@RestController
public class UtilisateurController {
	
	@Autowired
    private UtilisateurService utilisateurService;

    @GetMapping("/utilisateurs")
    public ResponseEntity < List < Utilisateur >> getAllUser() {
        return ResponseEntity.ok().body(utilisateurService.getAllUser());
    }

    @GetMapping("/utilisateurs/{id}")
    public ResponseEntity < Utilisateur > getUserById(@PathVariable long id) {
        return ResponseEntity.ok().body(utilisateurService.getUserById(id));
    }

    @PostMapping("/utilisateurs")
    public ResponseEntity < Utilisateur > createUser(@RequestBody Utilisateur Utilisateur) {
        return ResponseEntity.ok().body(this.utilisateurService.createUser(Utilisateur));
    }

    @PutMapping("/utilisateurs/{id}")
    public ResponseEntity < Utilisateur > updateUser(@PathVariable long id, @RequestBody Utilisateur Utilisateur) {
        Utilisateur.setId(id);
        return ResponseEntity.ok().body(this.utilisateurService.updateUser(Utilisateur));
    }

    @DeleteMapping("/utilisateurs/{id}")
    public HttpStatus deleteUser(@PathVariable long id) {
        this.utilisateurService.deleteUser(id);
        return HttpStatus.OK;
    }

}
