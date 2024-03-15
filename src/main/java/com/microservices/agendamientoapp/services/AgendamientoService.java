package com.microservices.agendamientoapp.services;

import com.microservices.agendamientoapp.entities.Agendamiento;
import com.microservices.agendamientoapp.repositories.AgendamientoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendamientoService {
    @Autowired
    private AgendamientoRepository agendamientoRepository;
    
    public Agendamiento save(Agendamiento agendamiento) {
        return agendamientoRepository.save(agendamiento);
    }

    public Agendamiento getOne(LocalDateTime fechaHora) {
        final Optional<Agendamiento> opt = agendamientoRepository.findAll().stream()
                .filter(a -> a.getFechaHora().equals(fechaHora)).findAny();
        return opt.orElse(null);
    }
    
    public Agendamiento getOneById(String agendamientoId) {
        final Optional<Agendamiento> optAgendamiento = agendamientoRepository.findById(agendamientoId);
        return optAgendamiento.orElse(null);
    }

    public List<Agendamiento> listByCarritoDeComprasId(String carritoDeComprasId) {
        final Optional<List<Agendamiento>> agendamientosList = Optional.of(agendamientoRepository.findAll());
        return agendamientosList.orElse(null)
                .stream()
                .filter(a -> a.getCarritoDeComprasId().equals(carritoDeComprasId))
                .toList();
    }

    public List<Agendamiento> listByCarritoDeComprasAndUsuarioClienteId(String carritoDeComprasId, Short usuarioClienteId) {
        final Optional<List<Agendamiento>> optAgendamientosList = Optional.of(agendamientoRepository.findAll()
                .stream()
                .toList()
        );
        return optAgendamientosList.orElse(null);
    }

    public List<Agendamiento> listTomadosByUsuarioClienteId(Short usuarioClienteId) {
        final Optional<List<Agendamiento>> optAgendamientosList = Optional.of(agendamientoRepository.findAll()
                .stream()
                .filter(a -> a.getUsuarioClienteId().equals(usuarioClienteId) && a.getEstado().equals("tomado"))
                .toList()
        );
        return optAgendamientosList.orElse(null);
    }

    public void setEstadoToFacturado(List<Agendamiento> agendamientosList) {
        agendamientosList.forEach(a -> a.setEstado("facturado"));
    }

    public Agendamiento updateById(String agendamientoId, Agendamiento agendamiento) {
        final Agendamiento foundAgendamiento = this.getOneById(agendamientoId);

        if (foundAgendamiento != null) {
            foundAgendamiento.setFechaHora(agendamiento.getFechaHora());
            foundAgendamiento.setServicioId(agendamiento.getServicioId());
            foundAgendamiento.setUsuarioClienteId(agendamiento.getUsuarioClienteId());
            return agendamientoRepository.save(foundAgendamiento);
        }
        return null;
    }

    public String cancelOneById(String agendamientoId) {
        final Agendamiento foundAgendamiento = this.getOneById(agendamientoId);
        agendamientoRepository.delete(foundAgendamiento);
        return agendamientoId;
    }

    public Agendamiento cancelOnePaidById(String agendamientoId) {
        final Agendamiento foundAgendamiento = this.getOneById(agendamientoId);

        if (foundAgendamiento != null && foundAgendamiento.getEstado().equals("pago")) {
            foundAgendamiento.setEstado("cancelado");
            agendamientoRepository.save(foundAgendamiento);   // Se actualiza con .save()
            return foundAgendamiento;
        }
        return null;
    }

    public List<Agendamiento> listByUsuarioClienteId(Short usuarioClienteId) {
        return agendamientoRepository.findByUsuarioClienteId(usuarioClienteId);
    }

    public List<Agendamiento> listAll() {
        return agendamientoRepository.findAll();
    }

    public List<Agendamiento> listAllByUsuarioClienteId(Short usuarioClienteId) {
        return agendamientoRepository.findAll().stream().filter(a -> a.getUsuarioClienteId().equals(usuarioClienteId)).toList();
    }
}
