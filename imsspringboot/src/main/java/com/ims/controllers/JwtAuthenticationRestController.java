package com.ims.controllers;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ims.exception.AuthenticationException;
import com.ims.jwt.dto.JwtTokenRequest;
import com.ims.jwt.dto.JwtTokenResponse;
import com.ims.jwt.utility.JwtTokenUtil;
import com.ims.userdetails.CredentialStatus;

@RestController
@CrossOrigin(origins = "${allowed.origin}")
public class JwtAuthenticationRestController {

	@Value("${jwt.http.request.header}")
	private String tokenHeader;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService jwtUsrDetailsService;

	@RequestMapping(value = "${jwt.get.token.uri}", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtTokenRequest authenticationRequest)
			throws AuthenticationException {

		CredentialStatus credentialStatus = authenticate(authenticationRequest.getUsername(),
				authenticationRequest.getPassword());
		return getResponse(authenticationRequest, credentialStatus);
	}

	private ResponseEntity<?> getResponse(JwtTokenRequest authenticationRequest, CredentialStatus credentialStatus) {
		switch (credentialStatus) {
		case INVALID_CREDENTIALS:
		case USER_DISABLED:
			return ResponseEntity.ok(new JwtTokenResponse(credentialStatus.name()));
		default:
			final UserDetails userDetails = jwtUsrDetailsService
					.loadUserByUsername(authenticationRequest.getUsername());
			final String token = jwtTokenUtil.generateToken(userDetails);
			return ResponseEntity.ok(new JwtTokenResponse(token));
		}
	}

	@RequestMapping(value = "${jwt.refresh.token.uri}", method = RequestMethod.GET)
	public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
		String authToken = request.getHeader(tokenHeader);
		final String token = authToken.substring(7);
//		String username = jwtTokenUtil.getUsernameFromToken(token);
//		JwtUserDetails user = (JwtUserDetails) jwtUsrDetailsService.loadUserByUsername(username);

		if (jwtTokenUtil.canTokenBeRefreshed(token)) {
			String refreshedToken = jwtTokenUtil.refreshToken(token);
			return ResponseEntity.ok(new JwtTokenResponse(refreshedToken));
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}

	@ExceptionHandler({ AuthenticationException.class })
	public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
	}

	private CredentialStatus authenticate(String username, String password) {
		CredentialStatus status = null;
		try {
			Objects.requireNonNull(username);
			Objects.requireNonNull(password);
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			status = CredentialStatus.VALID;
		} 
		catch (BadCredentialsException e) {
			status = CredentialStatus.INVALID_CREDENTIALS;
			throw new AuthenticationException("INVALID_CREDENTIALS", e);
		}
		catch (DisabledException e) {
			status = CredentialStatus.USER_DISABLED;
			throw new AuthenticationException("USER_DISABLED", e);
		}  finally {
			return status;
		}
	}
}