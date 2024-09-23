package PrototypeDP;

public class Student implements Prototype {
    public String name;
    private int roll_no;
    public int age;

    public Student(){
    }

    public Student(String name, int roll_no, int age){
        this.name = name;
        this.roll_no = roll_no;
        this.age = age;
    }

    @Override
    public Prototype clone(){
        return new Student(name, roll_no, age);
    }
}
