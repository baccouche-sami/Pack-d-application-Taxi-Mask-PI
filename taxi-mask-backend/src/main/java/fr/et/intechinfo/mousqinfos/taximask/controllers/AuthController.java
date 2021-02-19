package fr.et.intechinfo.mousqinfos.taximask.controllers;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.et.intechinfo.mousqinfos.taximask.models.ERole;
import fr.et.intechinfo.mousqinfos.taximask.models.Role;
import fr.et.intechinfo.mousqinfos.taximask.models.Utilisateur;
import fr.et.intechinfo.mousqinfos.taximask.payload.request.LoginRequest;
import fr.et.intechinfo.mousqinfos.taximask.payload.request.SignupRequest;
import fr.et.intechinfo.mousqinfos.taximask.payload.response.JwtResponse;
import fr.et.intechinfo.mousqinfos.taximask.payload.response.MessageResponse;
import fr.et.intechinfo.mousqinfos.taximask.repository.RoleRepository;
import fr.et.intechinfo.mousqinfos.taximask.repository.UtilisateurRepository;
import fr.et.intechinfo.mousqinfos.taximask.security.jwt.JwtUtils;
import fr.et.intechinfo.mousqinfos.taximask.security.services.UserDetailsImpl;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UtilisateurRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(),
												 userDetails.getNom(),
												 userDetails.getPrenom(),
												 userDetails.getAdresse(),
												 userDetails.getEmail(), 
												 roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Le nom d'utilisateur est déjà pris!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email est déjà utilisé!"));
		}

		// Creation nouveau utilisateur
		Utilisateur user = new Utilisateur(
							 signUpRequest.getPrenom(),
							 signUpRequest.getNom(),
							 signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()),
							 signUpRequest.getNomEntreprise(),
							 signUpRequest.getPays(),
							 signUpRequest.getAdresse(),
							 signUpRequest.getCodePostale(), 
							 signUpRequest.getVille(),
							 signUpRequest.getMobile()
							 
							 );
		
		Set<Role> roles = new HashSet<>();

		
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Le rôle n'est pas trouvé."));
			roles.add(userRole);

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("Client enregistré avec succès!"));
	}
	
	@PostMapping("/addadmin")
	@PreAuthorize("hasRole('SUPERADMIN')")
	public ResponseEntity<?> registerAdmin(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Le nom d'utilisateur est déjà pris!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email est déjà utilisé!"));
		}

		// Creation nouveau utilisateur
		Utilisateur user = new Utilisateur(
							 signUpRequest.getPrenom(),
							 signUpRequest.getNom(),
							 signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()),
							 signUpRequest.getNomEntreprise(),
							 signUpRequest.getPays(),
							 signUpRequest.getAdresse(),
							 signUpRequest.getCodePostale(), 
							 signUpRequest.getVille(),
							 signUpRequest.getMobile()
							 
							 );

	
		Set<Role> roles = new HashSet<>();

		
			Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
					.orElseThrow(() -> new RuntimeException("Error: Le rôle n'est pas trouvé."));
			roles.add(adminRole);
		

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("Admin enregistré avec succès!"));
	}
	
	

}
