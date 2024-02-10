package com.projeto.musicas.entities;

import com.projeto.musicas.enums.TipoArtista;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "artistas")
public class Artista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nome;
    @Enumerated(EnumType.STRING)
    private TipoArtista tipoArtista;
    @OneToMany(mappedBy = "artista")
    private List<Musica> musicas;

    public Artista() {
    }

    public Artista(String nome, TipoArtista tipoArtista) {
        setNome(nome);
        setTipoArtista(tipoArtista);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipoArtista(TipoArtista tipoArtista) {
        this.tipoArtista = tipoArtista;
    }

    @Override
    public String toString() {
        return "código = " + id +
                ", nome = " + nome +
                ", tipo = " + tipoArtista.toString();
    }
}
