package br.manodo.loto.loto.dtos;

import java.util.Set;

import br.manodo.loto.loto.TipoJogo;

public class CartelaResultadoAcertos {

    private Integer sorteio;
    private Set<Integer> numeros;
    private TipoJogo tipoJogo;
    private Integer acertos;   

    public CartelaResultadoAcertos() {
    }

   

    public CartelaResultadoAcertos(Integer sorteio, Set<Integer> numeros, TipoJogo tipoJogo, Integer acertos) {
        this.sorteio = sorteio;
        this.numeros = numeros;
        this.tipoJogo = tipoJogo;
        this.acertos = acertos;
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

    public Integer getAcertos() {
        return acertos;
    }

    public void setAcertos(Integer acertos) {
        this.acertos = acertos;
    }

    public TipoJogo getTipoJogo() {
        return tipoJogo;
    }

    public void setTipoJogo(TipoJogo tipoJogo) {
        this.tipoJogo = tipoJogo;
    }

    @Override
    public String toString() {
        return "CartelaResultadoAcertos [sorteio=" + sorteio + ", numeros=" + numeros + ", tipoJogo=" + tipoJogo
                + ", acertos=" + acertos + "]";
    }    
}
