package entity;

public class Groupe {
    private String name;
    private int id;

    public Groupe(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public Groupe(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }
}
