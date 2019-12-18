package esof.projeto.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
public class Explicador extends BaseModel {

    private String nome;

    public Explicador(String nome) {
        this.nome = nome;
    }

    @OneToMany(cascade = CascadeType.PERSIST)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonBackReference
    private Set<Idiomas> idiomas = new HashSet<>();

    @OneToMany(mappedBy = "explicador", cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private Set<Atendimento> atendimentos = new HashSet<>();

    @ManyToMany(mappedBy = "explicadores", cascade = CascadeType.PERSIST)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonBackReference
    private Set<Cadeira> cadeiras = new HashSet<>();

    @OneToMany(mappedBy = "explicador", cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private Set<Disponibilidade> disponibilidades = new HashSet<>();

    public void addIdioma(Idiomas idioma) {
        idiomas.add(idioma);
    }

    public void addAtendimento(Atendimento atendimento) {
            atendimentos.add(atendimento);
            atendimento.setExplicador(this);
    }

    public void addCadeira(Cadeira cadeira) {
            cadeiras.add(cadeira);
            cadeira.getExplicadores().add(this);
    }

    public void addDisponibilidade(Disponibilidade disponibilidade) {
        disponibilidades.add(disponibilidade);
        disponibilidade.setExplicador(this);
}



}