package dao;

import connections.MySqlConnection;
import entity.Groupe;
import entity.Student;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DbDao implements StudentDao {


    @Override
    public List<Student> getList(String sql) {
        List<Student> list = new ArrayList<>();
        try(Connection connection = MySqlConnection.getConnetction()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                Student student = new Student();
                student.setAge(resultSet.getInt("age"));
                student.setSurname(resultSet.getString("surname"));
                student.setName(resultSet.getString("name"));
                student.setMiddleName(resultSet.getString("middle_name"));
                student.setNumGroup(resultSet.getString("groupe.name"));
                student.setId(resultSet.getInt("id"));
                list.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void setSql(String sql) {
        try(Connection connection = MySqlConnection.getConnetction()) {
            Statement statement = connection.createStatement();
            statement.execute(sql);
            } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Groupe> getGroupe() {
        List<Groupe> list = new ArrayList<>();
        try(Connection connection = MySqlConnection.getConnetction()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM groupe;");
            while(resultSet.next()){
                Groupe groupe = new Groupe();
                groupe.setName(resultSet.getString("name"));
                groupe.setId(resultSet.getInt("id"));
                list.add(groupe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
