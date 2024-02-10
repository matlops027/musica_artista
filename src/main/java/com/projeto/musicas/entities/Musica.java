package com.projeto.musicas.entities;

import com.projeto.musicas.enums.Genero;
import jakarta.persistence.*;

import java.util.Optional;

@Entity
@Table(name = "musicas")
public class Musica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nome;
    private String album;
    @Enumerated(EnumType.STRING)
    private Genero genero;
    @ManyToOne()
    Artista artista;

    public Musica() {
    }

    public Musica(String nome, Genero genero, String album, Artista artista) {
        setNome(nome);
        setGenero(genero);
        setAlbum(album);
        setArtista(artista);
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    @Override
    public String toString() {
        return "id = " + id +
                ", nome= " + nome +
                ", album = " + album +
                ", genero = " + genero.toString() +
                ", artista = " + artista.getNome();
    }
}
