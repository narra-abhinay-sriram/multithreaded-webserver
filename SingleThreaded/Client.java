package SingleThreaded;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public void run() throws IOException{
        int port =8010;

    InetAddress address = InetAddress.getByName("localhost");

    Socket socket = new Socket(address,port);
    PrintWriter tosocket = new PrintWriter(socket.getOutputStream(),true);
    BufferedReader fromsocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    tosocket.println("hellow from client");
    String Line = fromsocket.readLine();
    System.out.println("hello from client"+Line);
    tosocket.close();
    fromsocket.close();
    socket.close();

    }

    public static void main(String[] args)
    {
        Client client = new Client();
        try{


            client.run();

        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    


}
