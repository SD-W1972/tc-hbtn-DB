package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Aluno {

    public Aluno(){

    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeCompleto;
    private String matricula;
    private Date nascimento;
    private String email;

    @ManyToMany
    @JoinTable(
            name = "aluno_curso",
            joinColumns = @JoinColumn(name = "aluno_id"),
            inverseJoinColumns = @JoinColumn(name = "curso_id")
    )
    private List<Curso> cursos = new ArrayList<>();

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL)
    private List<Telefone> telefones = new ArrayList<>();

    @OneToMany(mappedBy= "aluno", cascade = CascadeType.ALL)
    private List<Endereco> enderecos = new ArrayList<>();

    public void adicionarCurso(Curso curso){
        this.cursos.add(curso);
    }
    public void adicionarTelefone(Telefone telefone){
        this.telefones.add(telefone);
    }
    public void adicionarEndereco(Endereco endereco){
        this.enderecos.add(endereco);
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public Aluno(String nomeCompleto, String matricula, Date nascimento, String email) {

        this.nomeCompleto = nomeCompleto;
        this.matricula = matricula;
        this.nascimento = nascimento;
        this.email = email;
    }

    public Long getId() {
        return id;
    }


    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "id=" + id +
                ", nomeCompleto='" + nomeCompleto + '\'' +
                ", matricula='" + matricula + '\'' +
                ", nascimento=" + nascimento +
                ", email='" + email + '\'' +
                ", cursos=" + cursos +
                ", telefones=" + telefones +
                ", enderecos=" + enderecos +
                '}';
    }
}
