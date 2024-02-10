package com.projeto.musicas.repository;

import com.projeto.musicas.entities.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MusicaRepository extends JpaRepository<Musica, Long> {
    @Query("select m from Musica m JOIN Artista a ON a = m.artista WHERE a.nome ILIKE :nomeArtista")
    List<Musica> buscaMusicasPorArtista(String nomeArtista);
}
