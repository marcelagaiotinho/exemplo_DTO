package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Livro;

public interface Livrorepository extends JpaRepository<Livro, Long> {
}