package org.una.inventario.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.una.inventario.dto.DepartamentoDTO;
import org.una.inventario.entities.Departamento;
import org.una.inventario.exceptions.NotFoundInformationException;
import org.una.inventario.repositories.IDepartamentoRepository;
import org.una.inventario.utils.MapperUtils;

import java.util.List;
import java.util.Optional;

public class DepartamentoServiceImplementation implements IDepartamentoService {

    @Autowired
    private IDepartamentoRepository departamentoRepository;
  @Override
    @Transactional
    public Optional<DepartamentoDTO> create(DepartamentoDTO departamentoDTO) {
        return Optional.ofNullable(getSavedDepartamentoDTO(departamentoDTO));
    }


    @Override
    @Transactional
    public Optional<DepartamentoDTO> update(DepartamentoDTO departamentoDTO, Long id) {
        if (departamentoRepository.findById(id).isEmpty()) throw new NotFoundInformationException();

        return Optional.ofNullable(getSavedDepartamentoDTO(departamentoDTO));

    }
    @Override
    @Transactional(readOnly = true)
    public Optional<List<DepartamentoDTO>> findByEstado(Boolean estado) {
        List<Departamento> departamento = departamentoRepository.findByEstado(estado);

        if (departamento.isEmpty()) throw new NotFoundInformationException();

        List<DepartamentoDTO> departamentoDTOList = MapperUtils.DtoListFromEntityList(departamento, DepartamentoDTO.class);
        return Optional.ofNullable(departamentoDTOList);

    }

    @Override
    public void delete(Long id) {
        departamentoRepository.deleteById(id);
    }

    private DepartamentoDTO getSavedDepartamentoDTO(DepartamentoDTO departamentoDTO) {
        Departamento departamento = MapperUtils.EntityFromDto(departamentoDTO, Departamento.class);
        Departamento departamentoCreated = departamentoRepository.save(departamento);
        return MapperUtils.DtoFromEntity(departamentoCreated, DepartamentoDTO.class);
    }

}
