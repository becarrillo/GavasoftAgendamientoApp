package com.microservices.agendamientoapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.microservices.agendamientoapp.entities.Agendamiento;
import com.microservices.agendamientoapp.services.AgendamientoService;
import java.util.List;

@RestController
@RequestMapping(path = "/agendamientos")
public class AgendamientoController {
    @Autowired
    private AgendamientoService agendamientoService;

    @PostMapping(path = "/agregar/nuevo")
    public Agendamiento agregar(@RequestBody Agendamiento agendamiento) {
        return agendamientoService.save(agendamiento);
    }

    @GetMapping(path = "/consultar/{agendamientoId}")
    public Agendamiento consultar(@PathVariable("agendamientoId") String agendamientoId) {
        return agendamientoService.getOneById(agendamientoId);
    }

    @PostMapping(path = "/modificar/{agendamientoId}")
    public Agendamiento modificar(@PathVariable("agendamientoId") String agendamientoId, @RequestBody Agendamiento agendamiento) {
        return agendamientoService.updateById(agendamientoId, agendamiento);
    }

    @GetMapping(path = "/cancelar/{agendamientoId}")
    public String cancelarServicioAgendado(@PathVariable("agendamientoId") String agendamientoId) {
        return agendamientoService.cancelOneById(agendamientoId);
    }

    @PostMapping(path = "/cancelar/pagos/{agendamientoId}")
    public Agendamiento cancelarServicioPago(@PathVariable("agendamientoId") String agendamientoId) {
        return agendamientoService.cancelOnePaidById(agendamientoId);
    }

    @GetMapping(path = "/filtrar-por-cliente/{usuarioClienteId}")
    public List<Agendamiento> listarPorUsuarioClienteId(@PathVariable("usuarioClienteId") Short usuarioClienteId) {
        return agendamientoService.listByUsuarioClienteId(usuarioClienteId);

    }

    @GetMapping
    public List<Agendamiento> listarTodos() {
        return agendamientoService.listAll();
    }
}
