package org.una.inventario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.una.inventario.entities.Transaccion;

import java.util.Date;
import java.util.List;

@Repository
public interface ITransaccionRepository extends JpaRepository<Transaccion, Long>{


    public List<Transaccion>findByUsuarioIdAndFechaCreacionBetween(Long usuarioId, Date startDate, Date endDate);

    public List<Transaccion> findByIdAndFechaCreacionBetween(Long id, Date startDate, Date endDate);

    public List<Transaccion> findByObjetoAndFechaCreacionBetween(String objetoId, Date startDate, Date endDate);

    public List<Transaccion> findByFechaCreacionBetween(Date startDate, Date endDate);

    
}