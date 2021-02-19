package fr.et.intechinfo.mousqinfos.taximask.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import fr.et.intechinfo.mousqinfos.taximask.exceptions.ResourceNotFoundException;
import fr.et.intechinfo.mousqinfos.taximask.models.Image;
import fr.et.intechinfo.mousqinfos.taximask.repository.ImageRepository;



@Service
@Transactional
public class ImageServiceImpl implements ImageService {

	
	@Autowired
	ImageRepository imageRepository ;
	@Value("${upload-dir2}")
    private String FILE_DIRECTORY;
	
	
	@Override
	public Image createImage(Image image) throws IOException {
		// TODO Auto-generated method stub
		 String filename = storageFile(image.getImage());
		 image.setImageFileName(filename);
		return imageRepository.save(image);
	}

	@Override
	public Image updateImage(Image image) throws IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				Optional < Image > ImageDb = this.imageRepository.findById(image.getId());
				if (ImageDb.isPresent()) {
					Image imageupdate = ImageDb.get();
					String filename = storageFile(image.getImage());
					imageupdate.setImageFileName(filename);
				
					imageRepository.save(imageupdate);
					return imageupdate;
				}
				else {
		            throw new ResourceNotFoundException("Record not found with id : " + image.getId());
		        }
	}

	@Override
	public List<Image> getAllImage() {
		// TODO Auto-generated method stub
		return imageRepository.findAll();
	}

	@Override
	public Image getImageById(long imageId) {
		// TODO Auto-generated method stub
				Optional < Image > ImageDb = this.imageRepository.findById(imageId);
				if (ImageDb.isPresent()) {
					return ImageDb.get();
				} else {
					 throw new ResourceNotFoundException("Record not found with id : " + imageId);
				}
	}

	@Override
	public void deleteImage(long id) {
		// TODO Auto-generated method stub
		Optional < Image > ImageDb = this.imageRepository.findById(id);
		if (ImageDb.isPresent()) {
			imageRepository.delete(ImageDb.get());
		} else {
			 throw new ResourceNotFoundException("Record not found with id : " + id);
		}
		
	}
	
	/**
     * Gestion de fichiers
     * @param file
     * @return
     * @throws IOException
     */
    public String storageFile(MultipartFile file) throws IOException {
        if(!new File(FILE_DIRECTORY).exists())
        {
            System.out.println("create");
            // Créer le dossier avec tous ses parents
            new File(FILE_DIRECTORY).mkdirs();

        }
        StringBuilder fileNames = new StringBuilder();
        Path fileNameAndPath = Paths.get(FILE_DIRECTORY, file.getOriginalFilename());
        fileNames.append(file.getOriginalFilename()+" ");

        Files.write(fileNameAndPath, file.getBytes());
        return FILE_DIRECTORY+"\\"+file.getOriginalFilename();
    }

}
