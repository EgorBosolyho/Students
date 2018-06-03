package dao;

import entity.Groupe;
import entity.Student;
import java.util.List;

public interface StudentDao {
    List<Student> getList(String sql);
    void setSql(String sql);
    List<Groupe> getGroupe();
}
