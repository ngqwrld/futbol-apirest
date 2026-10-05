package com.gestion.futbol.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document(collection = "futbolistas")
public class Futbolista {

    @Id
    private String id;

    private String nombre;
    private String apellido;
    private int edad;
    private String nacionalidad;
    private String posicion;   // Portero, Defensa, Centrocampista, Delantero
    private int dorsal;
    private double valorMercado; // en millones €

    @DocumentReference
    private Club club;

    public Futbolista() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public String getNacionalidad() { return nacionalidad; }
    public void setNacionalidad(String nacionalidad) { this.nacionalidad = nacionalidad; }

    public String getPosicion() { return posicion; }
    public void setPosicion(String posicion) { this.posicion = posicion; }

    public int getDorsal() { return dorsal; }
    public void setDorsal(int dorsal) { this.dorsal = dorsal; }

    public double getValorMercado() { return valorMercado; }
    public void setValorMercado(double valorMercado) { this.valorMercado = valorMercado; }

    public Club getClub() { return club; }
    public void setClub(Club club) { this.club = club; }
}
