package lt.viko.denis_kladijev.model;

import jakarta.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
public class Student
{
    private int id;
    private String name;
    private int age;
    private double gpa;
    private boolean isGraduated;
    private String gender;
    @XmlElementWrapper(name = "subjects")
    @XmlElement(name = "subject")
    private List<Subject> subjects;

    // JAXB thing
    public Student() {}

    public Student(int id, String name, int age, double gpa, boolean isGraduated, String gender, List<Subject> subjects)
    {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gpa = gpa;
        this.isGraduated = isGraduated;
        this.gender = gender;
        this.subjects = subjects;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getGpa() {
        return gpa;
    }

    public boolean isGraduated() {
        return isGraduated;
    }

    public String getGender() {
        return gender;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gpa=" + gpa +
                ", isGraduated=" + isGraduated +
                ", gender=" + gender +
                ", subjects=" + subjects +
                '}';
    }
}
