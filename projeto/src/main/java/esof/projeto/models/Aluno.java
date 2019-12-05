package esof.projeto.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Aluno extends BaseModel {

  private String nome;

  @OneToMany(mappedBy = "atendimento",cascade = CascadeType.PERSIST)
  @JsonManagedReference
  private Set<Atendimento> atendimentos;

  private boolean marcarAtendimento() {
  return false;
  }

  private Set<Explicador> getExplicadorDia() {
  return null;
  }

  private Set<Explicador> getExplicadorIdioma() {
  return null;
  }

  private Set<Explicador> getExplicadorCadeira() {
  return null;
  }

  private Set<Explicador> getExplicadorPeriodoTempo() {
  return null;
  }

  private Set<Explicador> procurarExplicador() {
  return null;
  }

  private Set<Explicador> verCalendarioSemanal() {
  return null;
  }

}