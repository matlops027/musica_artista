package com.projeto.musicas;

import com.projeto.musicas.entities.Artista;
import com.projeto.musicas.entities.Musica;
import com.projeto.musicas.enums.Genero;
import com.projeto.musicas.enums.TipoArtista;
import com.projeto.musicas.exceptions.ValidationExecption;
import com.projeto.musicas.repository.ArtistaRepository;
import com.projeto.musicas.repository.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class MusicasApplication implements CommandLineRunner {

	private int opcao = 0;
	private final Scanner leitura = new Scanner(System.in);
	@Autowired
	private final MusicaRepository musicaRepository;

	@Autowired
	private final ArtistaRepository artistaRepository;

    public MusicasApplication(MusicaRepository musicaRepository, ArtistaRepository artistaRepository) {
        this.musicaRepository = musicaRepository;
        this.artistaRepository = artistaRepository;
    }

    public static void main(String[] args) {
		SpringApplication.run(MusicasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		exibirMenuPrincipal();
	}

	void exibirMenuPrincipal() {
		try {

			do {
				String menu = """
                    Informe a opção que deseja:
                    
                    1- Cadastrar artistas
                    
                    2- Cadastrar músicas
                    
                    3- Listar músicas
                    
                    4- Buscar músicas por artistas
                    
                    5- Listar artistas
                    
                    9- Sair
                """;

				System.out.println(menu);
				setOpcao(leitura.nextInt());
				leitura.nextLine();

				switch (opcao) {
					case 1:
						criaArtista();
						break;
					case 2:
						criaMusica();
						break;
					case 3:
						listaMusicas();
						break;
					case 4:
						buscarMusicasPorArtista();
						break;
					case 5:
						listaArtistas();
						break;
				}
			} while(opcao != 9);

		} catch (ValidationExecption e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("Algo deu errado!");
			System.out.println(e.getMessage());
		}

	}

	private void setOpcao(int opcao) {
		this.opcao = opcao;
	}

	void criaMusica() {
		String nome;
		Genero genero;
		String album;
		System.out.println("Informe o nome da musica:");
		nome = leitura.nextLine();
		System.out.println("Informe o nome do album:");
		album = leitura.nextLine();
		System.out.println("Informe o genero da música: (Rock, Sertanejo, Funk, Samba, Pagode)");
		genero = Genero.valueOf(leitura.nextLine().toUpperCase());
		listaArtistas();
		System.out.println("Informe o código do artista proprietário da música:");
		Optional<Artista> artista = artistaRepository.findById(leitura.nextLong());
		leitura.nextLine();

		if(artista.isPresent()) {
			Musica musica = new Musica(nome, genero, album, artista.get());
			musicaRepository.save(musica);
		}else {
			throw new ValidationExecption("Artista não encontrado!");
		}
	}

	void criaArtista() {
		String nome;
		TipoArtista tipoArtista;
		System.out.println("Informe o nome do artista:");
		nome = leitura.nextLine();
		System.out.println("Informe tipo do artista: (Banda, Solo, Dupla)");
		tipoArtista = TipoArtista.valueOf(leitura.nextLine().toUpperCase());

		Artista artista = new Artista(nome, tipoArtista);
		artistaRepository.save(artista);
	}

	void listaMusicas() {
		List<Musica> musicas = musicaRepository.findAll();
		musicas.forEach(System.out::println);
	}

	void listaArtistas() {
		List<Artista> artistas = artistaRepository.findAll();
		artistas.forEach(System.out::println);
	}

	void buscarMusicasPorArtista() {
		System.out.println("Informe o artista que deseja ver as músicas: ");
		String nomeArtista = leitura.nextLine();

		List<Musica> musicas = musicaRepository.buscaMusicasPorArtista(nomeArtista);
		if(!musicas.isEmpty()) {
			musicas.forEach(System.out::println);
		}else {
			throw new ValidationExecption("Musicas não encontradas!");
		}

	}
}
