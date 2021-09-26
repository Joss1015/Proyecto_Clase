package org.una.inventario.services;

import org.una.inventario.dto.DepartamentoDTO;


import java.util.List;
import java.util.Optional;

public interface IDepartamentoService {

 public Optional<DepartamentoDTO> create(DepartamentoDTO departamentoDTO);

 public Optional<DepartamentoDTO> update(DepartamentoDTO departamentoDTO, Long id);

 public Optional<List<DepartamentoDTO>> findByEstado(Boolean estado);

 public void delete(Long id);


}
