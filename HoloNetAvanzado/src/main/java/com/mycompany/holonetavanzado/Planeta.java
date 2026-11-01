package com.mycompany.holonetavanzado;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "planetas")
public class Planeta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "planeta_id")
    private int id;

    @Column(name = "nombre", nullable = false, unique = true, length = 100)
    private String nombre;

    // En el script SQL tiene DEFAULT 'Neutral', por eso no es NOT NULL
    @Column(name = "afiliacion", length = 50)
    private String afiliacion;

    // Lado inverso de la relación (opcional, pero útil)
    @OneToMany(mappedBy = "planetaOrigen", fetch = FetchType.LAZY)
    private List<Nave> listaNaves = new ArrayList<>();

    public Planeta() {}

    public Planeta(String nombre, String afiliacion) {
        this.nombre = nombre;
        this.afiliacion = afiliacion;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getAfiliacion() { return afiliacion; }
    public void setAfiliacion(String afiliacion) { this.afiliacion = afiliacion; }

    public List<Nave> getListaNaves() { return listaNaves; }
    public void setListaNaves(List<Nave> listaNaves) { this.listaNaves = listaNaves; }

    @Override
    public String toString() {
        return "Planeta [ID=" + id + ", Nombre=" + nombre + ", Afiliación=" + afiliacion + "]";
    }
}
