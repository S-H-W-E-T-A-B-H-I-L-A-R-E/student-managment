package com.guvi.student_managment;

public class Student {
    private int id;
    private int age;
    private String name;
    private String course;

    //Constructor
    public Student(int id, int age, String name, String course) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.course = course;
    }

    //Getters and setters
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
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

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    // Display student details
    @Override
    public String toString() {
        return "ID: " + id +
                ", Name: " + name +
                ", Age: " + age +
                ", Course: " + course;
    }
}
