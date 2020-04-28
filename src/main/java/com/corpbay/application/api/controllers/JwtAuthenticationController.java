package com.corpbay.application.api.controllers;

import com.corpbay.application.api.config.JwtTokenUtil;
import com.corpbay.application.api.entity.JwtRequest;
import com.corpbay.application.api.entity.JwtResponse;
import com.corpbay.application.api.entity.Users;
import com.corpbay.application.api.services.AdminServices;
import com.corpbay.application.api.services.JwtUserDetailsService;
import com.corpbay.application.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class JwtAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private AdminServices adminServices;

    @RequestMapping(value = "/users/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest, HttpServletRequest request) throws Exception {
        try {
            authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

            final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(authenticationRequest.getEmail());

            final String token = jwtTokenUtil.generateToken(userDetails);
            System.out.println(userDetails.getUsername()+" is my name");
//            System.out.println("Remote User: " + request.getRemoteUser());
//            System.out.println("Remote Address: " + request.getRemoteAddr());
//            System.out.println("Server name: " + request.getServerName());
//            System.out.println("Locales: " + request.getLocales());
//            System.out.println("Locale: " + request.getLocale());
//            System.out.println("Local Address: " + request.getLocalAddr());
//            System.out.println("Local Name: " + request.getLocalName());
//            System.out.println("Local port: " + request.getLocalPort());
//            System.out.println("Remote host: " + request.getRemoteHost());
//            System.out.println("Remote port: " + request.getRemotePort());
            Object user;
            if(userDetails.getUsername().contains("@"))
                user = userService.getUserByEmail(userDetails.getUsername());
            else
                user = adminServices.getAdmin(userDetails.getUsername());
            System.out.println(user);
            Map<String, Object> mymap = new HashMap();
            mymap.put("token",new JwtResponse(token).getToken());
            mymap.put("user",user);
//            mymap.put("values",request);
            return ResponseEntity.ok(mymap);
        } catch(Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
