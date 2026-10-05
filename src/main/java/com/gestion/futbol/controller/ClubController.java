package com.gestion.futbol.controller;

import com.gestion.futbol.model.Club;
import com.gestion.futbol.service.ClubService;
import com.gestion.futbol.service.EntrenadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clubes")
public class ClubController {

    private final ClubService clubService;
    private final EntrenadorService entrenadorService;

    public ClubController(ClubService clubService, EntrenadorService entrenadorService) {
        this.clubService = clubService;
        this.entrenadorService = entrenadorService;
    }

    @GetMapping
    public ResponseEntity<List<Club>> listar() {
        return ResponseEntity.ok(clubService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Club> buscar(@PathVariable String id) {
        return clubService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Club> crear(@RequestBody ClubRequest req) {
        Club club = new Club();
        club.setNombre(req.getNombre());
        club.setCiudad(req.getCiudad());
        club.setPais(req.getPais());
        club.setAnioFundacion(req.getAnioFundacion());
        if (req.getEntrenadorId() != null && !req.getEntrenadorId().isBlank()) {
            entrenadorService.buscarPorId(req.getEntrenadorId()).ifPresent(club::setEntrenador);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(clubService.guardar(club));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Club> actualizar(@PathVariable String id,
                                            @RequestBody ClubRequest req) {
        Optional<Club> opt = clubService.buscarPorId(id);
        if (opt.isEmpty()) return ResponseEntity.notFound().build();
        Club club = opt.get();
        club.setNombre(req.getNombre());
        club.setCiudad(req.getCiudad());
        club.setPais(req.getPais());
        club.setAnioFundacion(req.getAnioFundacion());
        if (req.getEntrenadorId() != null && !req.getEntrenadorId().isBlank()) {
            entrenadorService.buscarPorId(req.getEntrenadorId()).ifPresent(club::setEntrenador);
        } else {
            club.setEntrenador(null);
        }
        return ResponseEntity.ok(clubService.guardar(club));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        if (clubService.buscarPorId(id).isEmpty()) return ResponseEntity.notFound().build();
        clubService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    public static class ClubRequest {
        private String nombre;
        private String ciudad;
        private String pais;
        private int anioFundacion;
        private String entrenadorId;

        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }
        public String getCiudad() { return ciudad; }
        public void setCiudad(String ciudad) { this.ciudad = ciudad; }
        public String getPais() { return pais; }
        public void setPais(String pais) { this.pais = pais; }
        public int getAnioFundacion() { return anioFundacion; }
        public void setAnioFundacion(int anioFundacion) { this.anioFundacion = anioFundacion; }
        public String getEntrenadorId() { return entrenadorId; }
        public void setEntrenadorId(String entrenadorId) { this.entrenadorId = entrenadorId; }
    }
}
