package lt.viko.denis_kladijev.network;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Unit test for Client class
 */

public class ClientTest
{
    @Test
    void TestClientReceivesFileFromServer()
    {
        Thread serverThread = new Thread(() -> {
            Server server = new Server();
            server.start();
        });

        serverThread.start();

        try
        {
            Thread.sleep(1000);

            Client client = new Client();
            client.start();

            File receivedFile = new File("src/main/resources/received_student.xml");

            assertTrue(receivedFile.exists(), "File was not received from the server");
            assertTrue(receivedFile.length() > 0, "File is received from the server, but it is empty");
        }
        catch(InterruptedException e)
        {
            fail("Error in process of testing client: " + e.getMessage());
        }
        finally
        {
            serverThread.interrupt();
        }
    }
}
