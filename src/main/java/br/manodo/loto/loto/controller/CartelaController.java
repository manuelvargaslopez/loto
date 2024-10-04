package br.manodo.loto.loto.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.manodo.loto.loto.TipoJogo;
import br.manodo.loto.loto.dtos.CartelaDTO;
import br.manodo.loto.loto.dtos.CartelaResultadoAcertos;
import br.manodo.loto.loto.model.Cartela;
import br.manodo.loto.loto.repository.CartelaRepository;
import br.manodo.loto.loto.service.CartelaService;
import jakarta.validation.Valid;



@RestController
@RequestMapping("/loto")
public class CartelaController {

    @Autowired
    private CartelaRepository cartelaRepository;
    
    @Autowired
    private CartelaService cartelaService;


    @GetMapping("/oficial")
    public List<Cartela> obterTodosOficial(){
        return cartelaRepository.findAll()
                                .stream()
                                .filter( c -> c.getTipo().equals(TipoJogo.OFICIAL))
                                .collect(Collectors.toList());     
    } 

    @GetMapping("/meus")
    public List<Cartela> obterTodosMeus(){        
        return  cartelaRepository.findAll()     
                                 .stream()
                                 .filter( c -> c.getTipo().equals(TipoJogo.MEU))
                                 .collect(Collectors.toList());     
    }

    @GetMapping("{id}")
    public Optional<Cartela> ObterJogoPorId(@PathVariable Integer id){

       return  cartelaRepository.findById(id);       
    }

    @GetMapping("/jogos/{sorteio}")
    public List<Cartela> ObterJogosSorteio(@PathVariable @Valid Integer sorteio){
      
        List<Cartela> cartela = cartelaRepository.findBySorteio(sorteio);

        if(cartela.isEmpty()){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, " Sorteio " + sorteio + " não encontrado");
        }
        return cartela;
    }

    @GetMapping("/acertos/{sorteio}")
    public List<CartelaResultadoAcertos> ConcursoAcertos(@PathVariable @Valid Integer sorteio){

        return cartelaService.verificarAcertosPorJogo(sorteio);
    }  

    @PostMapping("/novo")  
    public void salvar(@RequestBody @Valid CartelaDTO cartelaDTO){

       cartelaService.salvar(cartelaDTO);

    }    

    @DeleteMapping("/remover/{id}")
    public void deletarJogoPorId(@PathVariable Integer id){

        cartelaRepository.findById(id)
                         .map(jogo ->{
                             cartelaRepository.delete(jogo);
                             return Void.class;
                          })
                         .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Jogo não encontrado"));
    }

    @PutMapping("{id}")
    public void Atualiza(@PathVariable Integer id, @RequestBody Cartela cartela){

        cartelaRepository.findById(id).map(jogo -> {
                                                cartela.setId(jogo.getId());
                                                cartela.setTipo(jogo.getTipo());
                                                return cartelaRepository.save(cartela);
                                            }
        ).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Jogo não encontrado"));
    }
}
