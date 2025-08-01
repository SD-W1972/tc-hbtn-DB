package demo;

import entities.Aluno;
import entities.Professor;
import models.AlunoModel;
import models.CursoModel;
import java.util.Calendar;
import java.util.Date;

public class GestaoCursosMain {
    public static void main(String[] args){
        AlunoModel alunoModel = new AlunoModel();
        CursoModel cursoModel = new CursoModel();
        Date d1 = new Date();
        Calendar calendar = Calendar.getInstance();
        d1 = calendar.getTime();

        Aluno a1 = new Aluno("Fulaninho de Tal",
                "00001", d1,
                "fulaninho@mailcom");

        Professor professor = new Professor(
              "Professor maneiro",
              "00002",
              "professor@maneiro.com"
        );



    }

}
