package domain;

import java.util.List;

public class User {
    private String cin;
    private String name;
    private  int age;

    public User(String cin, String name, int age) {
        this.cin = cin;
        this.name = name;
        this.age = age;
    }

    public User() {}



    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    @Override
    public String toString() {
        String RESET = "\u001B[0m";
        String BLUE = "\u001B[34m";
        String YELLOW = "\u001B[33m";
        String header = BLUE + "+-------------------+---------------------+------+" + RESET + "\n" +
                BLUE + "| " + YELLOW + "CIN" + RESET + "               | " + YELLOW + "Name" + RESET + "                " +
                "| " + YELLOW + "Age" + RESET + "  |" + RESET;
        String separator = BLUE + "+-------------------+---------------------+------+" + RESET;
        String row = String.format("| %-17s | %-19s | %4d |", cin, name, age);
        return header + "\n" + separator + "\n" + row + "\n" + separator;
    }
}
