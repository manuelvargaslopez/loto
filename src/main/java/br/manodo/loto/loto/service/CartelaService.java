package br.manodo.loto.loto.service;



import java.util.List;

import br.manodo.loto.loto.dtos.CartelaDTO;
import br.manodo.loto.loto.dtos.CartelaResultadoAcertos;

public interface CartelaService {   
    
    void salvar(CartelaDTO cartelaDTO);
    
    List<CartelaResultadoAcertos> verificarAcertosPorJogo(Integer sorteio);
}
