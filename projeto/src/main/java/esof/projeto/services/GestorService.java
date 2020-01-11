package esof.projeto.services;

import esof.projeto.models.Cadeira;
import esof.projeto.models.Curso;
import esof.projeto.models.Faculdade;
import esof.projeto.repositories.CadeiraRepo;
import esof.projeto.repositories.CursoRepo;
import esof.projeto.repositories.FaculdadeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GestorService {

    private FaculdadeRepo faculdadeRepo;
    private CursoRepo cursoRepo;
    private CadeiraRepo cadeiraRepo;

    @Autowired
    public GestorService(FaculdadeRepo faculdadeRepo, CursoRepo cursoRepo, CadeiraRepo cadeiraRepo) {
        this.faculdadeRepo = faculdadeRepo;
        this.cursoRepo = cursoRepo;
        this.cadeiraRepo = cadeiraRepo;
    }

    /**
     * inserir base da dados na bd
     * @param faculdade objeto faculdade a ser inserido
     * @return success-> optional da faculdade inserida, erro-> optional vazio
     */
    public Optional<Faculdade> createFaculdade(Faculdade faculdade) {
        Optional<Faculdade> optionalClient = this.faculdadeRepo.findByNome(faculdade.getNome());
        if (optionalClient.isPresent()) {
            return Optional.empty();
        }
        Faculdade createdFaculdade = this.faculdadeRepo.save(faculdade);
        return Optional.of(createdFaculdade);
    }

    /**
     * inserir curso dentro de uma determinada faculdade
     * @param faculdade nome da faculdade
     * @param curso objeto curso a ser inserido na faculdade
     * @return  success-> optional do curso inserido na faculdade
     *          erro-> optional empty que representa ou que o curso já existe ou que não existe
     * nenhuma faculdade com o nome
     */
    public Optional<Curso> createCursoByFaculdade(String faculdade, Curso curso) {
        Optional<Faculdade> optionalFaculdade = this.faculdadeRepo.findByNome(faculdade);
        if (optionalFaculdade.isPresent()) {
            if (optionalFaculdade.get().getCursos().contains(curso))
                return Optional.empty();
            optionalFaculdade.get().addCurso(curso);
            this.cursoRepo.save(curso);
            return Optional.of(curso);
        }
        return Optional.empty();
    }
    /**
     * inserir cadeira dentro de um determinado curso
     * @param curso nome da curso
     * @param cadeira objeto cadeira a ser inserido no curso
     * @return  success-> optional da cadeira inserida no curso
     *          erro-> optional empty que representa ou que a cadeira já existe ou que não existe
     * nenhum curso com pelo nome
     */
    public Optional<Cadeira> createCadeiraByCurso(String curso, Cadeira cadeira) {
        Optional<Curso> optionalCurso = this.cursoRepo.findByNome(curso);
        if (optionalCurso.isPresent()) {
            if (optionalCurso.get().getCadeiras().contains(cadeira))
                return Optional.empty(); //já existe esta cadeira no curso
            optionalCurso.get().addCadeira(cadeira);
            this.cadeiraRepo.save(cadeira);
            return Optional.of(cadeira);
        }
        return Optional.empty(); //o curso não existe
    }
}
