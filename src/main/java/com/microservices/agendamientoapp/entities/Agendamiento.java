package com.microservices.agendamientoapp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(name = "estado")
    private String estado;

    @Column(name = "usuario_cliente_id")
    private Short usuarioClienteId;

    @Column(name = "carrito_de_compras_id")
    private String carritoDeComprasId;

    public Agendamiento(
            LocalDateTime fechaHora,
            String estado,
            String servicioId
    ) {
        this.fechaHora = fechaHora;
        this.estado = estado;
        this.servicioId = servicioId;
    }

    public Agendamiento(
            LocalDateTime fechaHora,
            String servicioId,
            Short usuarioClienteId
    ) {
        this.estado = "tomado";
        this.fechaHora = fechaHora;
        this.servicioId = servicioId;
        this.usuarioClienteId = usuarioClienteId;
    }

    public Agendamiento(
            LocalDateTime fechaHora,
            String servicioId,
            Short usuarioClienteId,
            String carritoDeComprasId
    ) {
        this.estado = "tomado";
        this.fechaHora = fechaHora;
        this.servicioId = servicioId;
        this.usuarioClienteId = usuarioClienteId;
        this.carritoDeComprasId = carritoDeComprasId;
    }
}
