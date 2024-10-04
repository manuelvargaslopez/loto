package br.manodo.loto.loto.service.impl;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.manodo.loto.loto.TipoJogo;
import br.manodo.loto.loto.dtos.CartelaDTO;
import br.manodo.loto.loto.dtos.CartelaResultadoAcertos;
import br.manodo.loto.loto.model.Cartela;
import br.manodo.loto.loto.repository.CartelaRepository;
import br.manodo.loto.loto.service.CartelaService;
import jakarta.transaction.Transactional;

@Service
public class CartelaServiceImpl implements CartelaService{

  @Autowired
  private CartelaRepository cartelaRepository;

  public Cartela save(Cartela cartela){

    return cartelaRepository
          .findById(cartela.getId())
          .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Codigo do Cliente não existe"));
    
  }

  @Override
  @Transactional
  public void salvar(CartelaDTO cartelaDTO) {
  
    if(cartelaDTO != null) {       
          if(cartelaDTO.getNumeros().size() == 15 ){

            Cartela cartela = new Cartela();
            cartela.setSorteio(cartelaDTO.getSorteio());
            cartela.setNumeros(cartelaDTO.getNumeros());
            cartela.setData(LocalDate.now());
            cartela.setTipo(cartelaDTO.getTipoJogo());

            cartelaRepository.save(cartela); 
          }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Quantidade de numero por jogo deve ser igual a 15 numeros");
          } 
    }else{
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                            "A cartela está vazia");
    }
  } 
  
  public Integer acertosPorJogo(Optional<Cartela> resultado1, Cartela resultado){      

    Set<Integer> acertos = new HashSet<>() ;   

    resultado1.get().getNumeros().forEach(c -> {
      if(resultado.getNumeros().contains(c))
        acertos.add(c);
       });

    return acertos.size();  
  }

  @Override
  public  List<CartelaResultadoAcertos> verificarAcertosPorJogo(Integer sorteio){

    List<Cartela>  ResultadoSorteito = cartelaRepository.findBySorteio(sorteio);

    Optional<Cartela> resultado = ResultadoSorteito.stream()
                                                   .filter(c -> c.getTipo()
                                                   .equals(TipoJogo.OFICIAL))
                                                   .findFirst();
    return ResultadoSorteito
                            .stream().filter(c -> c.getTipo()
                            .equals(TipoJogo.MEU))
                            .map(u -> 
                                 new CartelaResultadoAcertos(u.getSorteio(), u.getNumeros(), u.getTipo(), acertosPorJogo(resultado, u)))
                            .collect(Collectors.toList());   
  }
}
