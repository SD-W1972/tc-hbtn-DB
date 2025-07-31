package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String sigla;

    @ManyToMany(mappedBy = "cursos")
    private List<Aluno> alunos = new ArrayList<>();

    @OneToOne(mappedBy = "curso", cascade = CascadeType.ALL)
    private MaterialCurso materialCursoList;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public void setMaterialCursoList(MaterialCurso materialCursoList) {
        this.materialCursoList = materialCursoList;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public List<MaterialCurso> getMaterialCursoList() {
        return materialCursoList;
    }

    public void setMaterialCursoList(List<MaterialCurso> materialCursoList) {
        this.materialCursoList = materialCursoList;
    }

    public Curso(String nome, String sigla) {
        this.nome = nome;
        this.sigla = sigla;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
}
