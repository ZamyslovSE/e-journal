package ru.zdb.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import ru.zdb.web.config.JwtTokenUtil;
import ru.zdb.web.model.ApiResponse;
import ru.zdb.web.model.JwtRequest;
import ru.zdb.web.model.User;
import ru.zdb.web.service.UserService;

import java.util.Objects;

@Slf4j
@RestController
@CrossOrigin
public class AuthenticationController {

	private final AuthenticationManager authenticationManager;

	private final JwtTokenUtil jwtTokenUtil;

	private final UserDetailsService userDetailsService;

	private final UserService userService;

	@Autowired
	public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, UserDetailsService userDetailsService, UserService userService) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenUtil = jwtTokenUtil;
		this.userDetailsService = userDetailsService;
		this.userService = userService;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<ApiResponse<String>> login(@RequestBody JwtRequest request) {
		ApiResponse<String> response = new ApiResponse<>();

		// Validate input
		if (StringUtils.isEmpty(request.getUsername()) || StringUtils.isEmpty(request.getPassword())) {
			response.setStatus("401", "Login/password combination must not be empty");
			return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
		}

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
			final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
			final String token = jwtTokenUtil.generateToken(userDetails.getUsername());
			response.setContent(token);
			response.setStatus("0", "OK");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (BadCredentialsException e) {
			response.setStatus("401", e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
		} catch (Throwable e) {
			log.error("Error during authorization", e);
			response.setStatus("500", e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<ApiResponse<String>> register(@RequestBody User user) {
		ApiResponse<String> response = new ApiResponse<>();

		// Validate input
		if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
			response.setStatus("400", "Login/password combination must not be empty");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			userService.saveUser(user); // TODO: error handling (username taken, etc.)
			response.setStatus("0", "OK");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Throwable e) {
			log.error("Error during authorization", e);
			response.setStatus("500", e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
