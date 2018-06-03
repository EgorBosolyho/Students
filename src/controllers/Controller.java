package controllers;

import dao.DbDao;
import dao.StudentDao;
import entity.Groupe;
import entity.Student;
import java.util.List;

public class Controller {
    private StudentDao dao = new DbDao();


    public List<Student> getStudents(){
        return dao.getList("SELECT * FROM students INNER JOIN groupe ON groupe.id = students.id_groupe;");
    }

    public List<Student> getStudentsByGroupe(String nameGroupe){
        return dao.getList("SELECT * FROM students INNER JOIN groupe ON" +
                " groupe.id = students.id_groupe WHERE groupe.name = '"+nameGroupe+"';");
    }

    public List<Student> getStudents(Student student){
        return dao.getList("SELECT * FROM students INNER JOIN groupe ON " +
                "groupe.id = students.id_groupe WHERE students.surname = '"+student.getSurname()+
                "' AND students.name = '"+student.getName()+"' AND students.middle_name = '"+student.getMiddleName()+"';");
    }

    public void editStudent(Student student){
        dao.setSql("UPDATE students set name = '"+student.getName()+"' , surname = '"+student.getSurname()+"' ," +
                " middle_name = '"+student.getMiddleName()+"' , age = '"+student.getAge()+"' , id_groupe =" +
                " (select id from groupe where name = '"+student.getNumGroup()+"') where id = '"+student.getId()+"'");
    }

    public void addStudent(Student student){
        String surname = student.getSurname();
        String name = student.getName();
        String middle_name = student.getMiddleName();
        int age = student.getAge();
        String numGroup = student.getNumGroup();
        dao.setSql("INSERT INTO students(surname,name,middle_name,age,id_groupe) " +
                "VALUES ("+surname+","+name+","+middle_name+","+age+",(SELECT id FROM groupe WHERE name = '"+numGroup+"'));");
    }

    public void deleteStudent(Student student){
        int id = student.getId();
        dao.setSql("DELETE FROM students WHERE id = "+id+";");
    }

    public List<Groupe> getGroupe(){
        return dao.getGroupe();
    }

    public void addGroupe(String name){
        dao.setSql("INSERT INTO groupe(name) VALUE ('"+name+"');");
    }

    public void deleteGroupe(String name){
        List<Groupe> list = dao.getGroupe();
        int id = 0;
        for(Groupe s: list){
            if (s.getName().equalsIgnoreCase(name)){
                id = s.getId();
            }
            dao.setSql("DELETE FROM groupe WHERE id ='"+id+"';");
        }
    }







}
