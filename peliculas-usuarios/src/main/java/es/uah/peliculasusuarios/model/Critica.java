package es.uah.peliculasusuarios.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "criticas")
public class Critica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCritica", nullable = false)
    private Integer id;

    @Column(name = "idPelicula", nullable = false)
    private Integer idPelicula;

    @Column(name = "valoracion", nullable = false, length = 500)
    private String valoracion;

    @Column(name = "nota", nullable = false)
    private Integer nota;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "Users_idUsuario", referencedColumnName = "idUsuario", nullable
            = false)
    @JsonIgnoreProperties("criticas")
    private Usuario usuario;


}