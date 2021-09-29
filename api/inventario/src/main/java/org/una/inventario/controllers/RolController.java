package org.una.inventario.controllers;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.inventario.dto.RolDTO;
import org.una.inventario.services.IRolService;

import java.util.Optional;

@RestController
@RequestMapping("/roles")
@Api(tags = {"Roles"})
public class RolController {
    @Autowired
    private IRolService rolesService;
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody RolDTO rolDTO) {
        try {
            Optional<RolDTO> rolesCreated = rolesService.create(rolDTO);
            return new ResponseEntity<>(rolesCreated, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody RolDTO rolesModified) {
        try {
            Optional<RolDTO> rolesUpdated = rolesService.update(rolesModified, id);
            if (rolesUpdated.isPresent()) {
                return new ResponseEntity<>(rolesUpdated, HttpStatus.OK);

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
