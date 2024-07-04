package com.microservices.agendamientoapp.repositories;

import com.microservices.agendamientoapp.entities.Agendamiento;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendamientoRepository extends JpaRepository<Agendamiento, String> {

    Agendamiento findByFechaHora(LocalDateTime dateTime);
    List<Agendamiento> findAllByUsuarioClienteId(Short usuarioClienteId);
}
