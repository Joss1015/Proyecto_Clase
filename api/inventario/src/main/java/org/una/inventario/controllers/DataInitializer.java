package org.una.inventario.controllers;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.inventario.services.IDepartamentoService;

@RestController
@RequestMapping("/datainitializer")
@Api(tags = {"DataInitializer"})
public class DataInitializer {

    @Autowired
    private IDepartamentoService DataInitializerService;



    @DeleteMapping("/")
    public ResponseEntity<?> deleteAll() throws Exception {
//        //TODO: Implementar este método
//        throw new Exception("Not implemented Function");

        return null;
    }
}
