package entity;

public class Student {
    private String name;
    private String surname;
    private String middleName;
    private String numGroup;
    private int age;
    private int id;

    public Student(String name, String surname, String middleName, String numGroup, int age) {
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.numGroup = numGroup;
        this.age = age;
    }

    public Student(String name, String surname, String middleName) {
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
}

    public Student(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getNumGroup() {
        return numGroup;
    }

    public void setNumGroup(String numGroup) {
        this.numGroup = numGroup;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return surname+" "+name+" "+middleName+", возраст = "+age+", номер группы = "+numGroup;
    }
}
