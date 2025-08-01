package entities;

import javax.persistence.*;

@Entity
public class MaterialCurso {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    private String url;

    public MaterialCurso(String url, Curso curso) {
        this.url = url;
        this.curso = curso;
    }

    @OneToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;


    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public MaterialCurso(String url) {
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
