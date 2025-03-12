package lt.viko.denis_kladijev.network;

import lt.viko.denis_kladijev.model.Student;
import lt.viko.denis_kladijev.transform.XMLTransformer;

import java.io.*;
import java.net.Socket;

/**
 * Class Client connects to the server, receives XML file, saves it and transforms it to object Student
 */

public class Client
{
    private static final String serverHost = "localhost";
    private static final int serverPort = 9000;
    private static final String saveFilePath = "src/main/resources/received_student.xml";
    private static final String xsdFilePath = "src/main/resources/student.xsd";

    public static void main(String args[])
    {
        new Client().start();
    }

    /**
     * Connects to the server, receives XML, validates and deserialize it
     */

    public void start()
    {
        try(Socket socket = new Socket(serverHost, serverPort);)
        {
            System.out.println("Connected to " + serverHost + ":" + serverPort);

            ReceiveFile(socket);

            XMLTransformer transformer = new XMLTransformer(new File(xsdFilePath));
            Student receivedStudent = transformer.TransformToPOJO(new File(saveFilePath));

            System.out.println("Received student: " + receivedStudent);
        }
        catch(IOException e)
        {
            System.err.println("Client error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Receives file from the server through socket and saves it
     * @param socket socket of connected client
     */

    private void ReceiveFile(Socket socket)
    {
        try(InputStream is = socket.getInputStream();
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(saveFilePath)))
        {
            byte[] buffer = new byte[4096];
            int bytesRead;

            System.out.println("Reading file");

            while((bytesRead = is.read(buffer)) != -1)
            {
                bos.write(buffer, 0, bytesRead);
            }

            bos.flush();
            System.out.println("File read and saved to: " + saveFilePath);
        }
        catch(IOException e)
        {
            System.err.println("Receiving file from server error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
