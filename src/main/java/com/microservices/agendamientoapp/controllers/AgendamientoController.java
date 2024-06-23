package com.microservices.agendamientoapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.microservices.agendamientoapp.entities.Agendamiento;
import com.microservices.agendamientoapp.services.AgendamientoService;

import java.util.List;
import java.util.Objects;


@RestController
@CrossOrigin("http://USUARIO-APP")
@RequestMapping(path = "/v1/agendamientos")
public class AgendamientoController {
    @Autowired
    private AgendamientoService agendamientoService;

    @PostMapping(path = "/agregar/nuevo")
    public Agendamiento agregar(@RequestBody Agendamiento agendamiento) {
        return agendamientoService.save(agendamiento);
    }

    @GetMapping(path = "/{agendamientoId}")
    public Agendamiento consultar(@PathVariable("agendamientoId") String agendamientoId) {
        return agendamientoService.getOneById(agendamientoId);
    }

    @GetMapping(path = "/filtrar-por-cliente/{usuarioClienteId}/carrito-de-compras-id")
    public String obtenerCarritoDeComprasIdPorUsuarioClienteId(@PathVariable("usuarioClienteId") Short usuarioClienteId) {
        final Agendamiento agendamiento = Objects.requireNonNull(
            agendamientoService.getCarritoDeComprasIdByUsuarioClienteId(usuarioClienteId)
        );
        return agendamiento.getCarritoDeComprasId();
    }

    @GetMapping(path = "/filtrar-por-carrito-de-compras/{carritoId}")
    public List<Agendamiento> listarPorCarritoDeComprasId(@PathVariable("carritoId") String carritoDeComprasId) {
        return agendamientoService.listByCarritoDeComprasId(carritoDeComprasId);
    }

    @GetMapping(path = "/clientes/{usuarioClienteId}/tomados")
    public List<Agendamiento> listarTomadosPorUsuarioClienteId(@PathVariable Short usuarioClienteId) {
        return agendamientoService.listTomadosByUsuarioClienteId(usuarioClienteId);
    }

    @GetMapping(path = "/clientes/{usuarioClienteId}/pagados")
    public List<Agendamiento> listarPagadosPorUsuarioClienteId(@PathVariable Short usuarioClienteId) {
        return agendamientoService.listPagadosByUsuarioClienteId(usuarioClienteId);
    }

    @GetMapping(path = "/{agendamientoId}/modificar")
    public Agendamiento modificar(
            @PathVariable("agendamientoId") String agendamientoId,
            @RequestBody Agendamiento agendamiento
    ) {
        return agendamientoService.updateById(agendamientoId, agendamiento);
    }

    @GetMapping(path = "/carritos-de-compras/{carritoId}/actualizar-estado/facturado")
    public List<Agendamiento> actualizarEstadoToFacturado(
            @PathVariable("carritoId") String carritoDeCompras
    ) {
        return agendamientoService.setEstadoToFacturado(
            agendamientoService.listByCarritoDeComprasId(carritoDeCompras)
        );
    }

    @GetMapping(path = "/{agendamientoId}/cancelar")
    public String cancelarServicioAgendado(@PathVariable("agendamientoId") String agendamientoId) {
        return agendamientoService.cancelOneById(agendamientoId);
    }

    @PostMapping(path = "/{agendamientoId}/cancelar/pagos")
    public Agendamiento cancelarServicioPago(@PathVariable("agendamientoId") String agendamientoId) {
        return agendamientoService.cancelOnePaidById(agendamientoId);
    }

    @GetMapping(path = "/filtrar-por-cliente/{usuarioClienteId}")
    public List<Agendamiento> listarPorUsuarioClienteId(@PathVariable Short usuarioClienteId) {
        return agendamientoService.listByUsuarioClienteId(usuarioClienteId);
    }

    @GetMapping
    public List<Agendamiento> listarTodos() {
        return agendamientoService.listAll();
    }
}
