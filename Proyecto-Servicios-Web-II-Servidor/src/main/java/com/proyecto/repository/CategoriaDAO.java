package com.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.entity.Categoria;

public interface CategoriaDAO extends JpaRepository<Categoria, Integer> {

}
