package com.munoz.sisgestion.service.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.munoz.sisgestion.entity.Infraccion;
import com.munoz.sisgestion.service.InfraccionService;
import com.munoz.sisgestion.repository.InfraccionRepository;

@Service
public class InfraccionServiceImpl implements InfraccionService {

    @Autowired
    private InfraccionRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Infraccion> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Infraccion findById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Infraccion> findByDniContaining(String dni) {
        return repository.findByDniContaining(dni);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Infraccion> findByPlacaContaining(String placa) {
        return repository.findByPlacaContaining(placa);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Infraccion> findByInfraccionContaining(String infraccion) {
        return repository.findByInfraccionContaining(infraccion);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Infraccion> findByDescripcionContaining(String descripcion) {
        return repository.findByDescripcionContaining(descripcion);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Infraccion> findByFechaBetween(Date fechaInicio, Date fechaFin) {
        return repository.findByFechaBetween(fechaInicio, fechaFin);
    }

    @Override
    @Transactional
    public Infraccion create(Infraccion infraccion) {
        return repository.save(infraccion);
    }

    @Override
    @Transactional
    public Infraccion update(Infraccion infraccion) {
        Infraccion infraccionDb = findById(infraccion.getId());
        if (infraccionDb != null) {
            infraccionDb.setDni(infraccion.getDni());
            infraccionDb.setFecha(infraccion.getFecha());
            infraccionDb.setPlaca(infraccion.getPlaca());
            infraccionDb.setInfraccion(infraccion.getInfraccion());
            infraccionDb.setDescripcion(infraccion.getDescripcion());

            return repository.save(infraccionDb);
        } else {

            return null;
        }
    }

    @Override
    @Transactional
    public int delete(int id) {
        Infraccion infraccionDb = findById(id);
        if (infraccionDb != null) {
            repository.delete(infraccionDb);
            return 1;
        } else {
            
            return 0;
        }
    }
}
