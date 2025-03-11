package lt.viko.denis_kladijev.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "subject")
@XmlAccessorType(XmlAccessType.FIELD)
public class Subject
{
    private int subjectId;
    private int credits;
    private String subjectName;
    private char grade;

    // this thing is used for JAXB
    public Subject() {}

    public Subject(int subjectId, int credits, String subjectName, char grade)
    {
        this.subjectId = subjectId;
        this.credits = credits;
        this.subjectName = subjectName;
        this.grade = grade;
    }

    @Override
    public String toString()
    {
        return "Subject{" +
                "subjectId=" + subjectId +
                ", subjectName='" + subjectName + '\'' +
                ", credits=" + credits +
                ", grade='" + grade + '\'' +
                '}';
    }
}
