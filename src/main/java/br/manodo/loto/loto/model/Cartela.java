package br.manodo.loto.loto.model;

import java.time.LocalDate;
import java.util.Set;

import br.manodo.loto.loto.TipoJogo;
import br.manodo.loto.loto.validation.ListNumberValidacao;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class Cartela {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Campo de Sorteio obrigatorio")
    private Integer sorteio;

    @Enumerated(EnumType.ORDINAL)
    @NotNull
    private TipoJogo  tipo;

    private LocalDate data;
   
    @ListNumberValidacao(message="Valor não permitido de cartela tabela", min = 1, max = 34)
    private Set<Integer> numeros;   
    
    public Cartela() {
    }   

    public Cartela(Integer sorteio, TipoJogo tipo, LocalDate data, Set<Integer> numeros) {
        this.sorteio = sorteio;
        this.tipo = tipo;
        this.data = data;
        this.numeros = numeros;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
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

      /*   if (numeros.contains(null) || numeros.contains(0) )
                  throw new ResponseStatusException(HttpStatus.BAD_REQUEST," Valor nulo ou zero não são permitidos no conjunto de numeros" );   
       */
        this.numeros = numeros;
    }

    public LocalDate getData() {
        return data;
    }
    
    public void setData(LocalDate data) {
        this.data = data;
    }

    public TipoJogo getTipo() {
        return tipo;
    }

    public void setTipo(TipoJogo tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Cartela [id=" + id + ", sorteio=" + sorteio + ", tipo=" + tipo + ", data=" + data + ", numeros="
                + numeros + "]";
    } 
}
