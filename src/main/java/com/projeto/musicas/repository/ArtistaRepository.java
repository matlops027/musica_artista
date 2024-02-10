package com.projeto.musicas.repository;

import com.projeto.musicas.entities.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
}
