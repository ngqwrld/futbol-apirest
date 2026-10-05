package com.gestion.futbol.controller;

import com.gestion.futbol.model.Futbolista;
import com.gestion.futbol.service.ClubService;
import com.gestion.futbol.service.FutbolistaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/futbolistas")
public class FutbolistaController {

    private final FutbolistaService futbolistaService;
    private final ClubService clubService;

    public FutbolistaController(FutbolistaService futbolistaService, ClubService clubService) {
        this.futbolistaService = futbolistaService;
        this.clubService = clubService;
    }

    @GetMapping
    public ResponseEntity<List<Futbolista>> listar() {
        return ResponseEntity.ok(futbolistaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Futbolista> buscar(@PathVariable String id) {
        return futbolistaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/club/{clubId}")
    public ResponseEntity<List<Futbolista>> porClub(@PathVariable String clubId) {
        return ResponseEntity.ok(futbolistaService.buscarPorClub(clubId));
    }

    @PostMapping
    public ResponseEntity<Futbolista> crear(@RequestBody FutbolistaRequest req) {
        Futbolista f = buildFromRequest(new Futbolista(), req);
        f.setId(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(futbolistaService.guardar(f));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Futbolista> actualizar(@PathVariable String id,
                                                  @RequestBody FutbolistaRequest req) {
        Optional<Futbolista> opt = futbolistaService.buscarPorId(id);
        if (opt.isEmpty()) return ResponseEntity.notFound().build();
        Futbolista f = buildFromRequest(opt.get(), req);
        return ResponseEntity.ok(futbolistaService.guardar(f));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        if (futbolistaService.buscarPorId(id).isEmpty()) return ResponseEntity.notFound().build();
        futbolistaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    private Futbolista buildFromRequest(Futbolista f, FutbolistaRequest req) {
        f.setNombre(req.getNombre());
        f.setApellido(req.getApellido());
        f.setEdad(req.getEdad());
        f.setNacionalidad(req.getNacionalidad());
        f.setPosicion(req.getPosicion());
        f.setDorsal(req.getDorsal());
        f.setValorMercado(req.getValorMercado());
        if (req.getClubId() != null && !req.getClubId().isBlank()) {
            clubService.buscarPorId(req.getClubId()).ifPresent(f::setClub);
        } else {
            f.setClub(null);
        }
        return f;
    }

    public static class FutbolistaRequest {
        private String nombre;
        private String apellido;
        private int edad;
        private String nacionalidad;
        private String posicion;
        private int dorsal;
        private double valorMercado;
        private String clubId;

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
        public String getClubId() { return clubId; }
        public void setClubId(String clubId) { this.clubId = clubId; }
    }
}
