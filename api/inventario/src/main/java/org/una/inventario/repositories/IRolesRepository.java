package org.una.inventario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.una.inventario.entities.Roles;


import java.util.Date;
import java.util.List;

public interface IRolesRepository extends JpaRepository<Roles, Long> {
    List<Roles> findByid(Long id);
}
