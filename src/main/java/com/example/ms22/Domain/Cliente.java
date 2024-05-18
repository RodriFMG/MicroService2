package com.example.ms22.Domain;

import jakarta.persistence.*;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;


@Entity

//Nombre de la tabla de la clase
@Table(name = "cliente")
public class Cliente implements UserDetails{


    //Generación automatica de id autoincrementandose en 1 por cada cliente creado.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Cambiado a GenerationType.IDENTITY para autoincrementar

    //Nombre de la columna = id, y que id no almacene datos nulos (nullable = false).
    @Column(name = "id", nullable = false) //

    private Long id;

    //Los demas atributos
    
    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name = "cantidad_compras")
    private Long cantidad_compras;

    @Column(name = "direccion")
    private String direccion;

    //Un solo liente se almacene varias compras y que esas compras solo formen parte de ese cliente.
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id")
    private List<Compra> compras;


    //Constructores
    public Cliente(){}
    public Cliente(Long id, String username, String email, String contrasena, Role role, Long cantidad_compras, String direccion, List<Compra> compras) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.contrasena = contrasena;
        this.role = role;
        this.cantidad_compras = cantidad_compras;
        this.direccion = direccion;
        this.compras = compras;
    }

    //getters
    public Long getId() {
        return id;
    }
    public String getContrasena(){ return contrasena; }
    @Override
    public String getPassword() {
        return contrasena;
    }
    public String getUserName() {
        return username;
    }
    @Override
    public String getUsername() {
        return username;
    }
    public String getEmail() { return email; }
    public String getDireccion() { return direccion; }
    public Long getCantidad_compras() { return cantidad_compras; }
    public List<Compra> getCompras() { return compras; }
    public Role getRole(){
        return role;
    }
    //setters
    public void setRole(Role role){
        this.role = role;
    }
    public void setId(Long id) { this.id = id; }
    public void setContrasena(String contrasena){ this.contrasena = contrasena; }
    public void setUserName(String username) { this.username = username; }
    public void setEmail(String email) { this.email = email; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public void setCantidad_compras(Long cantidad_compras) { this.cantidad_compras = cantidad_compras; }
    public void setCompras(List<Compra> compras) { this.compras = compras; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}