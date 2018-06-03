package applications;

import controllers.Controller;
import entity.Groupe;
import entity.Student;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class AppStudent {
    private Scanner scanner = new Scanner(System.in);
    private Controller controller = new Controller();

    public void start() {
        mainRun();
    }

    private void mainRun() {
        do {
            mainMenu();
        } while (mainChoice());
    }

    private void mainMenu() {
        System.out.println("-----------------------------------");
        System.out.println("Введите цифру для нужного действия:");
        System.out.println("1 - просмотр всех групп");
        System.out.println("2 - добавление группы");
        System.out.println("3 - удаление группы");
        System.out.println("4 - поиск студентов по названию группы");
        System.out.println("5 - просмотр студентов всех групп");
        System.out.println("6 - добавление нового студента");
        System.out.println("7 - поиск по ФИО студента");
        System.out.println("0 - выход");
        System.out.println("-----------------------------------");
    }

    private boolean mainChoice() {
        List<Groupe> listGroupe = controller.getGroupe();
        String name;
        switch (action()) {
            case 1:
                out(controller.getGroupe());
                break;
            case 2:
                System.out.println("Введите название новой группы.");
                name = actionLine();
                while (checkGroupe(listGroupe, name)) {
                    System.out.println("Группа с таким именем уже существует");
                    System.out.println("Введите название новой группы.");
                    name = actionLine();
                }
                controller.addGroupe(name);
                System.out.println("Новая группа добавлена");
                break;
            case 3:
                System.out.println("Введите название удаляемой группы группы.");
                name = actionLine();
                while (!checkGroupe(listGroupe, name)) {
                    System.out.println("Группы с таким именем не существует");
                    System.out.println("Введите название удаляемой группы группы");
                    name = actionLine();
                }
                controller.deleteGroupe(name);
                System.out.println("Группа удалена");
                break;
            case 4:
                System.out.println("Введите название группы.");
                name = actionLine();
                while (!checkGroupe(listGroupe, name)) {
                    System.out.println("Группа с таким именем не существует");
                    System.out.println("Введите название группы.");
                    name = actionLine();
                }
                List<Student> list = controller.getStudentsByGroupe(name);
                Collections.sort(list, Comparator.comparing(Student::getSurname));
                out(list);
                break;
            case 5:
                List<Student> studentList = controller.getStudents();
                Collections.sort(studentList, Comparator.comparing(Student::getSurname));
                out(studentList);
                sortRun(studentList);
                break;
            case 6:
                controller.addStudent(getNewStudent());
                break;
            case 7:
                System.out.println("Введите фамилию студента");
                String studentSurname = actionLine();
                System.out.println("Введите имя студента");
                String studentName = actionLine();
                System.out.println("Введите отчество студента");
                String studentMiddleName = actionLine();
                Student studentFio = new Student(studentName, studentSurname, studentMiddleName);
                List<Student> groupeList = controller.getStudents(studentFio);
                out(groupeList);
                studentRun(groupeList.get(0));
                break;
            case 0:
                return false;
        }
        return true;
    }

    private void sortRun(List<Student> list) {
        do {
            sortMenu();
        } while (sortChoice(list));
    }

    private void sortMenu() {
        System.out.println("-----------------------------------");
        System.out.println("Сортировка списка(введите нужную цифру):");
        System.out.println("1 - по фамилии, 2 - по имени, 3 - возрасту, 4 - по группе");
        System.out.println("0 - возврат в предыдущее меню");
        System.out.println("-----------------------------------");
    }

    private boolean sortChoice(List<Student> list) {
        boolean returnBoolean = true;
        switch (action()) {
            case 1:
                Collections.sort(list, Comparator.comparing(Student::getSurname));
                out(list);
                break;
            case 2:
                Collections.sort(list, Comparator.comparing(Student::getName));
                out(list);
                break;
            case 3:
                Collections.sort(list, Comparator.comparing(Student::getAge));
                out(list);
                break;
            case 4:
                Collections.sort(list, Comparator.comparing(Student::getNumGroup));
                out(list);
                break;
            case 0:
                returnBoolean = false;
                break;
        }
        return returnBoolean;
    }

    private void studentRun(Student student) {
        do {
            studentMenu();
        } while (studentChoice(student));
    }

    private void studentMenu() {
        System.out.println("-----------------------------------");
        System.out.println("Введите цифру для нужного действия:");
        System.out.println("1 - удаление записи");
        System.out.println("2 - редактирование записи");
        System.out.println("0 - возврат в предыдущее меню");
        System.out.println("-----------------------------------");
    }

    private boolean studentChoice(Student student) {
        boolean returnBoolean = true;

        switch (action()) {
            case 1:
                controller.deleteStudent(student);
                System.out.println("Запись удалена");
                break;
            case 2:
                Student editStudent = getNewStudent();
                editStudent.setId(student.getId());
                controller.editStudent(editStudent);
                break;
            case 0:
                returnBoolean = false;
                break;
        }
        return returnBoolean;
    }

    public String actionLine() {
        String inputLine;
        while (true) {
            inputLine = scanner.nextLine();
            inputLine = inputLine.trim();
            if (inputLine.isEmpty() || inputLine.length() == 0) {
                System.out.println("Вы ввели пустую строку. Введите название корректно.");
            } else break;
        }
        return inputLine;
    }

    private int action() {
        int returnInt = 10;
        try {
            returnInt = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Ошибка ввода. Попробуйте снова.");
        }
        return returnInt;
    }

    private void out(List list) {
        for (Object i : list) {
            System.out.println(i.toString());
        }
    }

    private Student getNewStudent() {
        System.out.println("Введите фамилию студента");
        String newSurname = actionLine();
        System.out.println("Введите имя студента");
        String newName = actionLine();
        System.out.println("Введите отчество студента");
        String newMiddleName = actionLine();
        System.out.println("Введите возраст студента");
        int newAge = action();
        System.out.println("Введите название группы");
        String newGroupe = actionLine();
        List<Groupe> groupeList = controller.getGroupe();
        while (!checkGroupe(groupeList, newGroupe)) {
            System.out.println("Группа с таким именем не существует");
            System.out.println("Введите название группы.");
            newGroupe = actionLine();
        }
        Student student = new Student(newName, newSurname, newMiddleName, newGroupe, newAge);
        return student;
    }

    private boolean checkGroupe(List<Groupe> listGroupe, String nameGroupe) {
        for (Groupe s : listGroupe) {
            if (s.getName().equalsIgnoreCase(nameGroupe))
                return true;
        }
        return false;
    }


}