package model;

public class User {
    private String cin;
    private String nom;
    private  int age;

    public User(String cin, String nom, int age) {
        this.cin = cin;
        this.nom = nom;
        this.age = age;
    }

    public User() {}

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "cin='" + cin + '\'' +
                ", nom='" + nom + '\'' +
                ", age=" + age +
                '}';
    }
}
