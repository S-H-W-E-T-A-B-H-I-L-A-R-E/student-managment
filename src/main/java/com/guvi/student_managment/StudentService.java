package com.guvi.student_managment;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private final List<Student> students = new ArrayList<>();
    private static final String FILE_NAME = "student.txt";

    // Add student
    public boolean addStudent (Student student){
        if(findStudentById(student.getId())!=null){
            return false;
        }
        students.add(student);
        saveToFile();

        return true;
    }

    // View all students
    public List<Student> getAllStudent(){
        return new ArrayList<>(students);
    }

    //find by student id
    public Student findStudentById(int id){
        for(Student student : students){
            if(student.getId() ==id){
                return student;
            }
        }
        return null;
    }

    // Update student
    public boolean updateStudent (int id, int age, String name, String course) {
        Student student = findStudentById(id);

        if (student == null) {
            return false;
        }
        student.setAge(age);
        student.setName(name);
        student.setCourse(course);

        saveToFile();

        return true;
    }
    // Delete student
    public boolean deleteStudent (int id){
        Student student = findStudentById(id);

        if (student == null) {
            return false;
        }
        students.remove(student);
        saveToFile();

        return true;
    }
    // Save students to file
    private void saveToFile() {
        try(BufferedWriter writer = new BufferedWriter
                (new FileWriter(FILE_NAME, false))) {
            for (Student student : students) {
                writer.write(student.getId() + "," +
                        student.getName() + "," +
                        student.getAge() + "," +
                        student.getCourse());
                writer.newLine();
            }
        }catch(IOException e){
                System.out.println("Error saving records: " + e.getMessage());
            }
        }

        //Load students from file
        public void loadFromfile(){
        File file = new File(FILE_NAME);
        if(!file.exists()){
            return;
        }
        students.clear();
        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME)))
        {
            String line;
            while((line = reader.readLine())!= null){
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split(",", -1);

                if (data.length != 4) {
                    System.out.println("Skipping invalid record: " + line);
                    continue;
                }

                try {

                    int id = Integer.parseInt(data[0].trim());
                    String name = data[1].trim();
                    int age = Integer.parseInt(data[2].trim());
                    String course = data[3].trim();

                    students.add(new Student(id, age, name, course));

                } catch (NumberFormatException e) {

                    System.out.println("Skipping invalid record: " + line);
                }
            }

        } catch (IOException e) {

            System.out.println("Error loading records: " + e.getMessage());
        }
    }
}
