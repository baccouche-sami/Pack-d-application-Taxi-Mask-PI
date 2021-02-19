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

import fr.et.intechinfo.mousqinfos.taximask.models.Image;
import fr.et.intechinfo.mousqinfos.taximask.services.ImageService;

@RestController
@RequestMapping("/api/test")
public class ImageController {
	
	@Autowired
	private ImageService ImageService;
	
	@GetMapping("/images")
    public ResponseEntity < List < Image >> getAllImage() {
        return ResponseEntity.ok().body(ImageService.getAllImage());
    }

    @GetMapping("/images/{id}")
    public ResponseEntity < Image > getImageById(@PathVariable long id) {
        return ResponseEntity.ok().body(ImageService.getImageById(id));
    }

    @PostMapping("/images")
    public ResponseEntity < Image > createImage(@RequestBody Image image) throws IOException {
        return ResponseEntity.ok().body(this.ImageService.createImage(image));
    }

    @PutMapping("/images/{id}")
    public ResponseEntity < Image > updateImage(@PathVariable long id, @RequestBody Image image) throws IOException {
    	image.setId(id);
        return ResponseEntity.ok().body(this.ImageService.updateImage(image));
    }

    @DeleteMapping("/images/{id}")
    public HttpStatus deleteImage(@PathVariable long id) {
        this.ImageService.deleteImage(id);
        return HttpStatus.OK;
    }

}
