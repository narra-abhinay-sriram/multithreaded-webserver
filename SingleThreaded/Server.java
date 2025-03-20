package SingleThreaded;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Server{
public void run() throws UnknownHostException,IOException{
    int port =8010;
    ServerSocket socket =new ServerSocket(port);
    socket.setSoTimeout(10000);
    while(true)
    {
        System.out.println("Server is runnning on port"+port);
        Socket acceptedSocket= socket.accept();
        System.out.println("connected to"+ acceptedSocket.getRemoteSocketAddress());
        PrintWriter toclient = new PrintWriter(acceptedSocket.getOutputStream(),true);
        BufferedReader fromclient = new BufferedReader(new InputStreamReader(acceptedSocket.getInputStream()));
        toclient.println("Hello from the server");
        String line = fromclient.readLine();
        System.out.println("hello from client"+line);
        toclient.close();
        fromclient.close();
        socket.close();

    }

}
public static void main(String[] args){
    Server server =new Server();


    try{
        server.run();

    }catch(Exception ex)
    {
        ex.printStackTrace();
    }
}
}