package br.manodo.loto.loto;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.manodo.loto.loto.model.Cartela;
import br.manodo.loto.loto.repository.CartelaRepository;

@SpringBootApplication
public class LotoApplication implements CommandLineRunner{

	@Autowired
	private CartelaRepository cartelaRepository;

	public static void main(String[] args) {
		SpringApplication.run(LotoApplication.class, args);			
	}

	@Override
	public void run(String... args) throws Exception {

		Integer[] minhaLista = new Integer[] { 1, 2, 3,5,7,8,10,13,14,18,19,21,22,23,25}; 
		Integer[] concursoLista = new Integer[] {1, 2, 3,4,7,8,10,13,14,15,16,17,20,23,24};
		System.out.println("###################   Main craindo os primeiros MEU e OFICIAL  #########################################");
			
		Set<Integer> meujogo = new HashSet<>(Arrays.asList(minhaLista)) ;
		Set<Integer> concurso = new HashSet<>(Arrays.asList(concursoLista)) ;
		System.out.println("###################   Main craindo os primeiros Setando meujogo e concurso  #########################################");
		 
		Cartela cartelaMeuJogo = new Cartela(3333, TipoJogo.MEU, LocalDate.now(), meujogo);
		System.out.println("###################   Main instanciando cartelaMeuJogo  #########################################");
		Cartela cartelaConcurso = new Cartela(3333, TipoJogo.OFICIAL, LocalDate.now(), concurso);
		System.out.println("###################   Main instanciando cartelaConcurso  #########################################");

		System.out.println("###################   Main vou salvar cartelaMeuJogo  #########################################");
		cartelaRepository.save(cartelaMeuJogo);
		System.out.println("###################   Main salvo cartelaMeuJogo  #########################################");
		cartelaRepository.save(cartelaConcurso);
		System.out.println(cartelaMeuJogo);
		System.out.println("###################   Fim do MAIN    #########################################");
	}	
}
