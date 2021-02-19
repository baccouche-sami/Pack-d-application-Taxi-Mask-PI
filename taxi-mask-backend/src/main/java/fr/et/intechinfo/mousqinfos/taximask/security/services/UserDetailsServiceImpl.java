package fr.et.intechinfo.mousqinfos.taximask.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.et.intechinfo.mousqinfos.taximask.models.Utilisateur;
import fr.et.intechinfo.mousqinfos.taximask.repository.UtilisateurRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
    private UtilisateurRepository utilisateurRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Utilisateur user = utilisateurRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return UserDetailsImpl.build(user);
	}

	/**
	 * Retourne l'utilisateur courant
	 * @return
	 */
	public Utilisateur getCurrentUser(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth==="+authentication.getName());
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();
			return  utilisateurRepository.findByUsername(currentUserName)
					.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + currentUserName));

		}
		return null;
	}



}
