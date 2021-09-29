package org.una.inventario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.una.inventario.entities.Departamento;
import java.util.List;

@Repository
public interface IDepartamentoRepository extends JpaRepository<Departamento, Long> {

    List<Departamento> findByEstado(Boolean estado);


}