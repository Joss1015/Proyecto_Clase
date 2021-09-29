package org.una.inventario.services;

import org.una.inventario.dto.RolesDTO;
import org.una.inventario.entities.Roles;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IRolesService {

    public Optional<RolesDTO> findById(Long id);


    public Optional<List<RolesDTO>> findByFechaCreacionBetween(Date startDate, Date endDate);

    public Optional<RolesDTO> create(RolesDTO rolDTO);

    public Optional<RolesDTO> update(RolesDTO rolesDTO, Long id);

    public void delete(Long id);

    public void deleteAll();

}

