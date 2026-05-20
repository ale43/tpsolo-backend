package isi.deso.tpsolo.entidades;

import jakarta.persistence.*; 

@Entity
@Table(name = "huespedes") 
public class Huesped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @Column(name = "dni", unique = true)
    private String dni;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "email")
    private String email;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "posicion_iva")
    private String posicionIva;

    // 1. Constructor vacío (Obligatorio para Hibernate)
    public Huesped() {
    }

    // 2. Constructor de 3 parámetros (El que te está pidiendo a gritos HuespedDAOArchivo)
    public Huesped(String dni, String nombre, String apellido) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    // 3. Constructor de 4 parámetros (Por si se usaba en algún otro lado con email)
    public Huesped(String dni, String nombre, String apellido, String email) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
    }

    // 4. Constructor completo (El nuevo que vamos a usar en el Alta/Modificación)
    public Huesped(String dni, String nombre, String apellido, String email, String telefono, String direccion, String posicionIva) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.posicionIva = posicionIva;
    }

    // --- GETTERS Y SETTERS ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getPosicionIva() { return posicionIva; }
    public void setPosicionIva(String posicionIva) { this.posicionIva = posicionIva; }

    @Override
    public String toString() {
        return "Huesped {" + 
               "id=" + id + 
               ", DNI='" + dni + '\'' + 
               ", Nombre='" + nombre + '\'' + 
               ", Apellido='" + apellido + '\'' + 
               ", Email='" + email + '\'' + 
               ", Telefono='" + telefono + '\'' + 
               ", Direccion='" + direccion + '\'' + 
               ", PosicionIva='" + posicionIva + '\'' + 
               '}';
    }
}