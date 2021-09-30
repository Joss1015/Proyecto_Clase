package org.una.inventario.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.inventario.dto.TransaccionDTO;
import org.una.inventario.services.ITransaccionService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transacciones")
@Api(tags = {"Transacciones"})
public class TransaccionController {
    @Autowired
    private ITransaccionService ITransaccionService;

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene una transaccion a partir de su id", response = TransaccionDTO.class, tags = "Transacciones")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        Optional<TransaccionDTO> usuarioFound = ITransaccionService.findById(id);
        return new ResponseEntity<>(usuarioFound, HttpStatus.OK);
    }

    @GetMapping("/usuarioId/{term}")
    @ApiOperation(value = "Obtiene una lista de los id de usuario con su fecha de creaacion", response = TransaccionDTO.class, responseContainer = "List", tags = "Transacciones")
    public ResponseEntity<?>findByUsuarioIdAndFechaCreacionBetween (@PathVariable(value = "term") Long term, Date startDate, Date endDate) {
        Optional<List<TransaccionDTO>> result = ITransaccionService.findByUsuarioIdAndFechaCreacionBetween(term, startDate, endDate);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/rolId/{term}")
    @ApiOperation(value = "Obtiene una lista de los id de transacciones con su fecha de creaacion", response = TransaccionDTO.class, responseContainer = "List", tags = "Transacciones")
    public ResponseEntity<?>findByIdAndFechaCreacionBetween (@PathVariable(value = "term") Long term, Date startDate, Date endDate) {
        Optional<List<TransaccionDTO>> result = ITransaccionService.findByIdAndFechaCreacionBetween(term, startDate, endDate);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/objetoId/{term}")
    @ApiOperation(value = "Obtiene una lista de los id de objetos con su fecha de creaacion", response = TransaccionDTO.class, responseContainer = "List", tags = "Transacciones")
    public ResponseEntity<?>findByObjetoAndFechaCreacionBetween (@PathVariable(value = "term") String term, Date startDate, Date endDate) {
        Optional<List<TransaccionDTO>> result = ITransaccionService.findByObjetoAndFechaCreacionBetween(term, startDate, endDate);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/transaccion/{term}")
    @ApiOperation(value = "Obtiene una lista de las transacciones con su fecha de creaacion", response = TransaccionDTO.class, responseContainer = "List", tags = "Transacciones")
    public ResponseEntity<?>findByFechaCreacionBetween (@PathVariable(value = "term")  Date startDate, Date endDate) {
        Optional<List<TransaccionDTO>> result = ITransaccionService.findByFechaCreacionBetween(startDate, endDate);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody TransaccionDTO transaccionDTO) {
        try {
            Optional<TransaccionDTO> transaccionCreated = ITransaccionService.create(transaccionDTO);
            return new ResponseEntity<>(transaccionCreated, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
