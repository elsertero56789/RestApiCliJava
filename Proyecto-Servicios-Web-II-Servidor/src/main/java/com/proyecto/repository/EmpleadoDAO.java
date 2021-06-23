package com.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.entity.Empleado;

public interface EmpleadoDAO extends JpaRepository<Empleado, Integer> {

}
