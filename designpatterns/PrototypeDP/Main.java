package PrototypeDP;

public class Main {
    public static void main(String[] args) {
        Student student = new Student("dev parikh", 103, 21);
        Student cloneObj = (Student) student.clone();
        cloneObj.name = "dan brown";

        System.out.println("Student name:" + student.name);
        System.out.println("Clone name:" + cloneObj.name);
    }
}
