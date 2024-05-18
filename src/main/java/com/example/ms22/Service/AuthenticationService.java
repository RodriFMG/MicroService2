package com.example.ms22.Service;

import com.example.ms22.Domain.Role;
import com.example.ms22.Domain.Cliente;
import com.example.ms22.Dto.JwtAuthenticationResponse;
import com.example.ms22.Dto.SignUpRequest;
import com.example.ms22.Dto.SigninRequest;
import com.example.ms22.Infrastracture.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final ClienteRepository clienteRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    // @Autowired
    // private DireccionService direccionService;

    @Autowired
    public AuthenticationService(ClienteRepository clienteRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.clienteRepository = clienteRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public JwtAuthenticationResponse signup(SignUpRequest request) {
        var cliente = new Cliente();
        cliente.setUserName(request.getUsername());
        cliente.setEmail(request.getEmail());
        cliente.setContrasena(passwordEncoder.encode(request.getContrasena()));
        cliente.setRole(Role.USER);
        // direccionService.insertarDireccion(request.getDireccion()); //Busco que tmb en el login aparezca la direccion
        //cliente.setDireccion(request.getDireccion());

        if (request.getAdmin()) {
            cliente.setRole(Role.ADMIN);
        }

        clienteRepository.save(cliente);
        var jwt = jwtService.generateToken(cliente);

        JwtAuthenticationResponse response = new JwtAuthenticationResponse();
        response.setToken(jwt);

        return response;
    }

    public JwtAuthenticationResponse signin(SigninRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getContrasena()));
        } catch (AuthenticationException ex) {
            throw new IllegalArgumentException("Credenciales inválidas", ex);
        }
    
        Cliente cliente = clienteRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
    
        String jwt = jwtService.generateToken(cliente);
    
        JwtAuthenticationResponse response = new JwtAuthenticationResponse();
        response.setToken(jwt);
    
        return response;
    }
    
}