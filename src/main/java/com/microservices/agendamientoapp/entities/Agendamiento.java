package com.microservices.agendamientoapp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "agendamientos")
public class Agendamiento {
    @Id
    @Column(name = "agendamiento_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String agendamientoId;

    @Column(name = "fecha_hora")
    private LocalDateTime fechaHora;

    @Column(name = "servicio_id")
    private String servicioId;

    @Column(name = "usuario_cliente_id")
    private Short usuarioClienteId;

    @Column(name = "estado")
    private String estado;
}
