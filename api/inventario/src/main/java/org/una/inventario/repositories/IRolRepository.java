package org.una.inventario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.una.inventario.entities.Rol;


import java.util.List;

public interface IRolRepository extends JpaRepository<Rol, Long> {
    List<Rol> findByid(Long id);
}
