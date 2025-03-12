package lt.viko.denis_kladijev;

import lt.viko.denis_kladijev.model.Student;
import lt.viko.denis_kladijev.model.Subject;
import lt.viko.denis_kladijev.transform.XMLTransformer;

import java.io.File;
import java.util.List;

/**
 * Moin class is not really needed in here, i just used it for checking how system is working
 */

public class Main
{
    public static void main(String[] args)
    {
        File xsdFile = new File("src/main/resources/student.xsd");
        XMLTransformer transformer = new XMLTransformer(xsdFile);

        Student student = new Student(1, "Denis", 20, 3.5, false, "M", List.of(
                new Subject(1, 6, "IS", '9'),
                new Subject(2, 6, "Web Services", '9'),
                new Subject(3, 3, "Multi-threaded Programming", '9')
        ));

        File outputXml = new File("src/main/resources/student.xml");
        transformer.TransformToXML(student, outputXml);
        Student loadedStudent = transformer.TransformToPOJO(outputXml);

        System.out.println("Loaded student from XML: \n" + loadedStudent);
    }
}