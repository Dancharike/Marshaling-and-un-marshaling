package lt.viko.denis_kladijev.transform;

import lt.viko.denis_kladijev.model.Student;
import lt.viko.denis_kladijev.model.Subject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for XmlTransformer class
 */

public class XmlTransformerTest
{
    private static final String xsdFilePath = "src/main/resources/student.xsd";
    private XMLTransformer transformer;
    private Student testStudent;

    @BeforeEach
    void Setup()
    {
        transformer = new XMLTransformer(new File(xsdFilePath));
        testStudent = new Student(100, "Test Student", 25, 4, true, "M", List.of(
                new Subject(1, 6, "IS", 'A'),
                new Subject(2, 6, "Web Services", 'B')
        ));
    }

    @Test
    void TestTransformToXML()
    {
        String tmpDir = System.getProperty("java.io.tmpdir");
        File outputFile = new File(tmpDir, "test_student.xml");
        transformer.TransformToXML(testStudent, outputFile);

        // check if the file is really existing
        assertTrue(outputFile.exists(), "XML file does not exist");
        assertTrue(outputFile.length() > 0, "XML file is empty");

        System.out.println("XML file is created" + outputFile.getAbsolutePath());
    }

    @Test
    void TestTransformToPOJO()
    {
        String tmpDir = System.getProperty("java.io.tmpdir");
        File xmlFile = new File(tmpDir, "test_student.xml");
        transformer.TransformToXML(testStudent, xmlFile);

        Student loadedStudent = transformer.TransformToPOJO(xmlFile);

        assertNotNull(loadedStudent, "Unmarshalling returned null");
        assertEquals(testStudent.getId(), loadedStudent.getId());
        assertEquals(testStudent.getName(), loadedStudent.getName());
        assertEquals(testStudent.getSubjects().size(), loadedStudent.getSubjects().size());
    }
}
