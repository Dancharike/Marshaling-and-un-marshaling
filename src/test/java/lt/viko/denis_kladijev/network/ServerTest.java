package lt.viko.denis_kladijev.network;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * Unit test for Server class
 */

public class ServerTest
{
    @Test
    void TestServerStartsWithoutException()
    {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        assertDoesNotThrow(() -> {
            executor.submit(() -> {
                Server server = new Server();
                server.start();
            });

            Thread.sleep(2000);
            executor.shutdown();
        });
    }
}
