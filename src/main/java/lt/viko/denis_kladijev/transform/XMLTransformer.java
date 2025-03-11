package lt.viko.denis_kladijev.transform;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import lt.viko.denis_kladijev.model.Student;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;

public class XMLTransformer
{
    private final File xsdSchemaFile;

    public XMLTransformer(File xsdSchemaFile)
    {
        this.xsdSchemaFile = xsdSchemaFile;
    }

    public void TransformToXML(Student student, File outputFile)
    {
        try
        {
            // JAXB context shows root class
            // marshaller creation
            JAXBContext jaxbContext = JAXBContext.newInstance(Student.class);
            Marshaller marshaller = jaxbContext.createMarshaller();

            // make XML readable through formatting
            // save it to file and output to console
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(student, outputFile);
            marshaller.marshal(student, System.out);

            System.out.println("Object Student is transformed to XML file and saved to: " + outputFile.getPath());
        }
        catch (JAXBException e)
        {
            System.err.println("Marshaling error of Student object in XML:" + e.getMessage());
            e.printStackTrace();
        }
    }

    public Student TransformToPOJO(File xmlFile)
    {
        try
        {
            // show root class
            // create unmarshaller
            JAXBContext jaxbContext = JAXBContext.newInstance(Student.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            // connect XSD schema for XML validation
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sf.newSchema(xsdSchemaFile);
            unmarshaller.setSchema(schema);

            // unmarshall it
            Student student = (Student) unmarshaller.unmarshal(xmlFile);

            System.out.println("Object Student is transformed to POJO object: " + student);

            return student;
        }
        catch (JAXBException e)
        {
            System.err.println("Marshaling error of Student object in XML:" + e.getMessage());
            e.printStackTrace();
        }
        catch (Exception e)
        {
            System.err.println("XML Validation Error by XSD:" + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }
}
