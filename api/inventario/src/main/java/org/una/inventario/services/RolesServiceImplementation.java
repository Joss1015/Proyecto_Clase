package org.una.inventario.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.una.inventario.dto.RolesDTO;
import org.una.inventario.entities.Roles;
import org.una.inventario.exceptions.NotFoundInformationException;
import org.una.inventario.repositories.IRolesRepository;
import org.una.inventario.utils.MapperUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RolesServiceImplementation implements IRolesService{

    @Autowired
    private IRolesRepository rolesRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<RolesDTO> findById(Long id) {
        Optional<Roles> roles = rolesRepository.findById(id);
        if (roles.isEmpty()) throw new NotFoundInformationException();

        RolesDTO rolesDTO = MapperUtils.DtoFromEntity(roles.get(), RolesDTO.class);
        return Optional.ofNullable(rolesDTO);
    }


    @Override
    public Optional<List<RolesDTO>> findByFechaCreacionBetween(Date startDate, Date endDate) {
        return Optional.empty();
    }


    private RolesDTO getSavedRolesDTO(RolesDTO rolesDTO) {
        Roles roles = MapperUtils.EntityFromDto(rolesDTO, Roles.class);
        Roles rolesCreated = rolesRepository.save(roles);
        return MapperUtils.DtoFromEntity(rolesCreated, RolesDTO.class);
    }

    @Override
    public Optional<RolesDTO> create(RolesDTO rolesDTO) {
        return Optional.ofNullable(getSavedRolesDTO(rolesDTO));
    }

    @Override
    public Optional<RolesDTO> update(RolesDTO rolesDTO, Long id) {
        if (rolesRepository.findById(id).isEmpty()) throw new NotFoundInformationException();

        return Optional.ofNullable(getSavedRolesDTO(rolesDTO));
    }

    @Override
    public void delete(Long id) {
        rolesRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        rolesRepository.deleteAll();
    }


}
