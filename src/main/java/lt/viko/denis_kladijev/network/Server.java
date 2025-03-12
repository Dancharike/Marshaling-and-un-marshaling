package lt.viko.denis_kladijev.network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server class starts server with port 9000
 * It additionally waits for client connection and sends XML file by net
 */

public class Server
{
    private static final int port = 9000;
    private static final String filePath = "src/main/resources/student.xml";

    public static void main(String[] args)
    {
        new Server().start();
    }

    /**
     * Starts the server and processes client connections
     */

    public void start()
    {
        try(ServerSocket serverSocket = new ServerSocket(port))
        {
            System.out.println("Server is ready and listening for port: " + port);

            while(true)
            {
                System.out.println("Waiting for client");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected" + clientSocket.getInetAddress());

                SendFile(clientSocket);

                clientSocket.close();
                System.out.println("Client disconnected");
            }
        }
        catch (IOException e)
        {
            System.out.println("Server error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Sends XML file by the net through client socket
     * @param clientSocket socket of connected client
     */

    private void SendFile(Socket clientSocket)
    {
        File xmlFile = new File(filePath);

        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(xmlFile));
            OutputStream os = clientSocket.getOutputStream())
        {
            byte[] buffer = new byte[4096];
            int bytesRead;

            System.out.println("Sending file: " + xmlFile.getName());

            while((bytesRead = bis.read(buffer)) != -1)
            {
                os.write(buffer, 0, bytesRead);
            }

            os.flush();
            System.out.println("File sent");
        }
        catch(IOException e)
        {
            System.out.println("Sending file error " + e.getMessage());
            e.printStackTrace();
        }
    }
}
