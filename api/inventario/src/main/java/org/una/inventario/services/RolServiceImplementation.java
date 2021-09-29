package org.una.inventario.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.una.inventario.dto.RolDTO;
import org.una.inventario.entities.Rol;
import org.una.inventario.exceptions.NotFoundInformationException;
import org.una.inventario.repositories.IRolRepository;
import org.una.inventario.utils.MapperUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RolServiceImplementation implements IRolService {

    @Autowired
    private IRolRepository rolesRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<RolDTO> findById(Long id) {
        Optional<Rol> roles = rolesRepository.findById(id);
        if (roles.isEmpty()) throw new NotFoundInformationException();

        RolDTO rolDTO = MapperUtils.DtoFromEntity(roles.get(), RolDTO.class);
        return Optional.ofNullable(rolDTO);
    }


    @Override
    public Optional<List<RolDTO>> findByFechaCreacionBetween(Date startDate, Date endDate) {
        return Optional.empty();
    }


    private RolDTO getSavedRolesDTO(RolDTO rolDTO) {
        Rol rol = MapperUtils.EntityFromDto(rolDTO, Rol.class);
        Rol rolCreated = rolesRepository.save(rol);
        return MapperUtils.DtoFromEntity(rolCreated, RolDTO.class);
    }

    @Override
    public Optional<RolDTO> create(RolDTO rolDTO) {
        return Optional.ofNullable(getSavedRolesDTO(rolDTO));
    }

    @Override
    public Optional<RolDTO> update(RolDTO rolDTO, Long id) {
        if (rolesRepository.findById(id).isEmpty()) throw new NotFoundInformationException();

        return Optional.ofNullable(getSavedRolesDTO(rolDTO));
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
