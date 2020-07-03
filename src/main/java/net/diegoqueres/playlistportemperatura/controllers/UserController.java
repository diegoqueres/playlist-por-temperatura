package net.diegoqueres.playlistportemperatura.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import net.diegoqueres.playlistportemperatura.dtos.UserDto;
import net.diegoqueres.playlistportemperatura.services.UserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/users")
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping("/signup")
	public ResponseEntity<Void> signUp(@RequestBody UserDto objDto) {
		var user = service.fromDTO(objDto);
		user = service.signUp(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

}
