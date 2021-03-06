package org.una.inventario.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.inventario.dto.DepartamentoDTO;
import org.una.inventario.services.IDepartamentoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departamentos")
@Api(tags = {"Departamentos"})
public class DepartamentoController {

    @Autowired
    private IDepartamentoService departamentoService;

    @GetMapping("/estado/{term}")
    @ApiOperation(value="Listas de departamentos activo o inactivo",response = DepartamentoDTO.class,responseContainer =  "List",tags = "Departamentos")
    public ResponseEntity<?> findByEstado(@PathVariable(value="term")boolean estado){
        Optional<List<DepartamentoDTO>> result = departamentoService.findByEstado(estado);
        return new ResponseEntity<>(result,HttpStatus.OK);

    }

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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception {
        try {
            departamentoService.delete(id);
            return new ResponseEntity<>("Ok", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    public ResponseEntity<?> deleteAll() throws Exception {
//        //TODO: Implementar este m??todo
//        throw new Exception("Not implemented Function");

        return null;
    }
}
