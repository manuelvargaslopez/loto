package br.manodo.loto.loto.dtos;

import java.util.Set;

import br.manodo.loto.loto.TipoJogo;
import jakarta.validation.constraints.NotNull;

public class CartelaDTO { 

    @NotNull
    private Integer sorteio;

   //@ListNumberValidacao(message="Valor nao permitido DTO",min= 1, max = 34)
    private Set<Integer> numeros; 

    private TipoJogo tipoJogo;   
    
    
    public CartelaDTO() {
    } 

    public CartelaDTO(Integer sorteio, Set<Integer> numeros, TipoJogo tipoJogo ) {
        this.sorteio = sorteio;
        this.numeros = numeros;
        this.tipoJogo = tipoJogo;
    }

    public Integer getSorteio() {
        return sorteio;
    }

    public void setSorteio(Integer sorteio) {
        this.sorteio = sorteio;
    }

    public Set<Integer> getNumeros() {
        return numeros;
    }
    
    public void setNumeros(Set<Integer> numeros) {
        this.numeros = numeros;
    }

    public TipoJogo getTipoJogo() {
        return tipoJogo;
    }

    public void setTipoJogo(TipoJogo tipoJogo) {
        this.tipoJogo = tipoJogo;
    } 
}
