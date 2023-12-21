package com.munoz.sisgestion.repository;




import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.munoz.sisgestion.entity.Infraccion;

@Repository
public interface InfraccionRepository extends JpaRepository<Infraccion, Integer> {
    List<Infraccion> findByDniContaining(String texto);

    List<Infraccion> findByPlacaContaining(String texto);

    List<Infraccion> findByInfraccionContaining(String texto);

    List<Infraccion> findByDescripcionContaining(String texto);

    List<Infraccion> findByFechaBetween(Date fechaInicio, Date fechaFin);

}
