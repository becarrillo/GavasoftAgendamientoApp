package com.microservices.agendamientoapp.services;

import com.microservices.agendamientoapp.entities.Agendamiento;
import com.microservices.agendamientoapp.repositories.AgendamientoRepository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendamientoService {
    @Autowired
    private AgendamientoRepository agendamientoRepository;
    
    public Agendamiento save(Agendamiento agendamiento) {
        agendamiento.setFechaHora(
            LocalDateTime.of(
                agendamiento.getFechaHora().getYear(),
                agendamiento.getFechaHora().getMonthValue(),
                agendamiento.getFechaHora().getDayOfMonth(),
                agendamiento.getFechaHora().getHour(),
                agendamiento.getFechaHora().getMinute(),
                agendamiento.getFechaHora().getSecond()
            )
                .atZone(ZoneId.of("GMT-5"))
                .toLocalDateTime()); // Corrección horaria a la de Bogotá
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

    public Agendamiento getCarritoDeComprasIdByUsuarioClienteId(Short usuarioClienteId) {
        final Optional<Agendamiento> agendamiento = agendamientoRepository.findAll()
                    .stream()
                    .filter(a -> a.getUsuarioClienteId().equals(usuarioClienteId) && a.getEstado().equals("tomado"))
                    .findAny();
        return agendamiento.orElse(null);
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
                .filter(a -> a.getCarritoDeComprasId().equals(carritoDeComprasId) && a.getUsuarioClienteId().equals(usuarioClienteId))
                .toList()
        );
        return optAgendamientosList.orElse(null);
    }

    public Agendamiento queryByFechaHora(LocalDateTime dateTime) {
    	final Optional<Agendamiento> optAgendamiento = Optional
            .of(agendamientoRepository.findByFechaHora(dateTime));
        return optAgendamiento.orElse(null);
    }

    public List<Agendamiento> listTomadosByUsuarioClienteId(Short usuarioClienteId) {
        final Optional<List<Agendamiento>> optAgendamientosList = Optional.of(agendamientoRepository.findAll()
                .stream()
                .filter(a -> a.getUsuarioClienteId().equals(usuarioClienteId) && a.getEstado().equals("tomado"))
                .toList()
        );
        return optAgendamientosList.orElse(null);
    }

    public List<Agendamiento> listPagadosByUsuarioClienteId(Short usuarioClienteId) {
        final Optional<List<Agendamiento>> optAgendamientosList = Optional.of(agendamientoRepository.findAll()
                .stream()
                .filter(a -> a.getUsuarioClienteId().equals(usuarioClienteId) && (
                        a.getEstado().equals("pagado") || a.getEstado().equals("materializado"))
                )
                .toList()
        );
        return optAgendamientosList.orElse(null);
    }

    public List<Agendamiento> setEstadoToFacturado(List<Agendamiento> agendamientosList) {
        agendamientosList.forEach(a -> a.setEstado("facturado"));
        return agendamientosList;
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

        if (foundAgendamiento != null && foundAgendamiento.getEstado().equals("pagado")) {
            foundAgendamiento.setEstado("cancelado");
            return agendamientoRepository
                    .save(foundAgendamiento);   // Se actualiza con .save()
        }
        return null;
    }

    public List<Agendamiento> listByUsuarioClienteId(Short usuarioClienteId) {
        return agendamientoRepository.findAllByUsuarioClienteId(usuarioClienteId);
    }

    public List<Agendamiento> listAll() {
        return agendamientoRepository.findAll();
    }

    public List<Agendamiento> listAllByUsuarioClienteId(Short usuarioClienteId) {
        return agendamientoRepository.findAll().stream()
                .filter(a -> a.getUsuarioClienteId().equals(usuarioClienteId))
                .toList();
    }
}
