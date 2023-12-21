package com.munoz.sisgestion.service;

import java.util.List;
import com.munoz.sisgestion.entity.Infraccion;
import java.sql.Date;

public interface InfraccionService {
    List<Infraccion> findAll();

    Infraccion findById(int id);

    List<Infraccion> findByDniContaining(String dni);

    List<Infraccion> findByPlacaContaining(String placa);

    List<Infraccion> findByInfraccionContaining(String infraccion);

    List<Infraccion> findByDescripcionContaining(String descripcion);

    List<Infraccion> findByFechaBetween(Date fechaInicio, Date fechaFin);

    Infraccion create(Infraccion infraccion);

    Infraccion update(Infraccion infraccion);

    int delete(int id);
}
