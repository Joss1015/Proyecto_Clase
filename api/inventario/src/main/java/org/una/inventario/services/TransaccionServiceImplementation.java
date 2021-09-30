package org.una.inventario.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.una.inventario.dto.TransaccionDTO;
import org.una.inventario.entities.Transaccion;
import org.una.inventario.exceptions.NotFoundInformationException;
import org.una.inventario.repositories.ITransaccionRepository;
import org.una.inventario.utils.MapperUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransaccionServiceImplementation implements ITransaccionService {

    @Autowired
    private ITransaccionRepository transaccionRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<TransaccionDTO> findById(Long id) {
        Optional<Transaccion> transaccion = transaccionRepository.findById(id);
        if(transaccion.isEmpty()) throw new NotFoundInformationException();
        TransaccionDTO transaccionDTO = MapperUtils.DtoFromEntity(transaccion, TransaccionDTO.class);
        return Optional.ofNullable(transaccionDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TransaccionDTO>> findByUsuarioIdAndFechaCreacionBetween(Long usuarioId, Date startDate, Date endDate) {
        List<Transaccion> transaccionList = transaccionRepository.findByUsuarioIdAndFechaCreacionBetween(usuarioId, startDate, endDate);
        List<TransaccionDTO> transaccionDTOList = MapperUtils.DtoListFromEntityList(transaccionList, TransaccionDTO.class);
        if (transaccionDTOList.isEmpty()) throw new NotFoundInformationException();
        return Optional.ofNullable(transaccionDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TransaccionDTO>> findByIdAndFechaCreacionBetween(Long id, Date startDate, Date endDate) {
        List<Transaccion> transaccionList = transaccionRepository.findByIdAndFechaCreacionBetween(id, startDate, endDate);
        List<TransaccionDTO> transaccionDTOList = MapperUtils.DtoListFromEntityList(transaccionList, TransaccionDTO.class);
        if (transaccionDTOList.isEmpty()) throw new NotFoundInformationException();
        return Optional.ofNullable(transaccionDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TransaccionDTO>> findByObjetoAndFechaCreacionBetween(String objetoId, Date startDate, Date endDate) {
        List<Transaccion> transaccionList = transaccionRepository.findByObjetoAndFechaCreacionBetween(objetoId, startDate, endDate);
        List<TransaccionDTO> transaccionDTOList = MapperUtils.DtoListFromEntityList(transaccionList, TransaccionDTO.class);
        if (transaccionDTOList.isEmpty()) throw new NotFoundInformationException();
        return Optional.ofNullable(transaccionDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TransaccionDTO>> findByFechaCreacionBetween(Date startDate, Date endDate) {
        List<Transaccion> transaccionList = transaccionRepository.findByFechaCreacionBetween(startDate, endDate);
        List<TransaccionDTO> transaccionDTOList = MapperUtils.DtoListFromEntityList(transaccionList, TransaccionDTO.class);
        if (transaccionDTOList.isEmpty()) throw new NotFoundInformationException();
        return Optional.ofNullable(transaccionDTOList);
    }

    private TransaccionDTO getSavedTransaccionDTO(TransaccionDTO transaccionDTO) {
        Transaccion transaccion = MapperUtils.EntityFromDto(transaccionDTO, Transaccion.class);
        Transaccion transaccionCreated = transaccionRepository.save(transaccion);
        return MapperUtils.DtoFromEntity(transaccionCreated, TransaccionDTO.class);
    }

    @Override
    @Transactional
    public Optional<TransaccionDTO> create(TransaccionDTO transaccionDTO) {
        return Optional.ofNullable(getSavedTransaccionDTO(transaccionDTO));
    }
}

