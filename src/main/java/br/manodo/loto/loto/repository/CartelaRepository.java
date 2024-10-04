package br.manodo.loto.loto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.manodo.loto.loto.model.Cartela;


public interface CartelaRepository extends JpaRepository<Cartela, Integer> {

    List<Cartela> findBySorteio(Integer sorteio); 
    
}
