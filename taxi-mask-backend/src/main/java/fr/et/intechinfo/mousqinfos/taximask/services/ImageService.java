package fr.et.intechinfo.mousqinfos.taximask.services;

import java.io.IOException;
import java.util.List;

import fr.et.intechinfo.mousqinfos.taximask.models.Image;


public interface ImageService {
	
	Image createImage(Image image) throws IOException;

	Image updateImage(Image image) throws IOException;

    List < Image > getAllImage();

    Image getImageById(long imageId);

    void deleteImage(long id);

}
