package org.una.inventario.controllers;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.inventario.dto.DepartamentoDTO;
import org.una.inventario.dto.UsuarioDTO;
import org.una.inventario.services.IDepartamentoService;
import org.una.inventario.services.IUsuarioService;

import java.util.Optional;

@RestController
@RequestMapping("/departamentos")
@Api(tags = {"Departamentos"})
public class DepartamentoController {
    @Autowired
    private IDepartamentoService departamentoService;
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody DepartamentoDTO departamentoDTO) {
        try {
            Optional<DepartamentoDTO> departamentoCreated = departamentoService.create(departamentoDTO);
            return new ResponseEntity<>(departamentoCreated, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody DepartamentoDTO departamentoModified) {
        try {
            Optional<DepartamentoDTO> departamentoUpdated = departamentoService.update(departamentoModified, id);
            if (departamentoUpdated.isPresent()) {
                return new ResponseEntity<>(departamentoUpdated, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    public ResponseEntity<?> deleteAll() throws Exception {
//        //TODO: Implementar este método
//        throw new Exception("Not implemented Function");

        return null;
    }
}
