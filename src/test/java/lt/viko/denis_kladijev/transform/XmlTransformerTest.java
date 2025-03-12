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
    private static final String testXmlFile = "src/test/resources/test_student.xml";
    private static final String xsdFilePath = "src/main/resources/student.xsd";
    private XMLTransformer transformer;
    private Student testStudent;

    @BeforeEach
    void Setup()
    {
        transformer = new XMLTransformer(new File(xsdFilePath));
        testStudent = new Student(100, "Test Student", 25, 4, true, "M", List.of(
                new Subject(4, 3, "Sociology", '9'),
                new Subject(5, 3, "BMS", '8')
        ));
    }

    @Test
    void TestTransformToXML()
    {
        File outputFile = new File(testXmlFile);
        transformer.TransformToXML(testStudent, outputFile);

        assertTrue(outputFile.exists(), "XML file does not exist");
        assertTrue(outputFile.length() > 0, "XML file is empty");
    }

    @Test
    void TestTransformToPOJO()
    {
        File xmlFile = new File(testXmlFile);
        transformer.TransformToXML(testStudent, xmlFile);

        Student loadedStudent = transformer.TransformToPOJO(xmlFile);

        assertNotNull(loadedStudent, "Unmarshalling returned null");
        assertEquals(testStudent.getId(), loadedStudent.getId());
        assertEquals(testStudent.getName(), loadedStudent.getName());
        assertEquals(testStudent.getSubjects().size(), loadedStudent.getSubjects().size());
    }
}
