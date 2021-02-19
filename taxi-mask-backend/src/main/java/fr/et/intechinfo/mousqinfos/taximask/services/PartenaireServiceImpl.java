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

import fr.et.intechinfo.mousqinfos.taximask.models.Partenaire;
import fr.et.intechinfo.mousqinfos.taximask.repository.PartenaireRepository;

@Service
@Transactional
public class PartenaireServiceImpl implements PartenaireService {

	
	@Autowired
	PartenaireRepository partenaireRepository ;
	@Value("${upload-dir1}")
    private String FILE_DIRECTORY;
	
	@Override
	public Partenaire createPartenaire(Partenaire partenaire) throws IOException {
		// TODO Auto-generated method stub
		 String filename = storageFile(partenaire.getLogo());
		 partenaire.setLogoFileName(filename);
		return partenaireRepository.save(partenaire);
		
	}

	@Override
	public Partenaire updatePartenaire(Partenaire partenaire) throws IOException {
		// TODO Auto-generated method stub
		Optional < Partenaire > PartenaireDb = this.partenaireRepository.findById(partenaire.getId());
		if (PartenaireDb.isPresent()) {
			Partenaire partenaireupdate = PartenaireDb.get();
			String filename = storageFile(partenaire.getLogo());
			partenaireupdate.setLogoFileName(filename);
			partenaireupdate.setNom(partenaire.getNom());
			partenaireupdate.setUrl(partenaire.getUrl());
			partenaireRepository.save(partenaireupdate);
			return partenaireupdate;
		}
		else {
            throw new ResourceNotFoundException("Record not found with id : " + partenaire.getId());
        }
	}

	@Override
	public List<Partenaire> getAllPartenaire() {
		// TODO Auto-generated method stub
		return partenaireRepository.findAll();
	}

	@Override
	public Partenaire getPartenaireById(long partenaireId) {
		// TODO Auto-generated method stub
		Optional < Partenaire > PartenaireDb = this.partenaireRepository.findById(partenaireId);
		if (PartenaireDb.isPresent()) {
			return PartenaireDb.get();
		} else {
			 throw new ResourceNotFoundException("Record not found with id : " + partenaireId);
		}
	}

	@Override
	public void deletePartenaire(long id) {
		// TODO Auto-generated method stub
		Optional < Partenaire > PartenaireDb = this.partenaireRepository.findById(id);
		if (PartenaireDb.isPresent()) {
			partenaireRepository.delete(PartenaireDb.get());
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
