package ThreadPool;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private final ExecutorService threadpool;
    public Server(int threadSize)
    {
        this.threadpool=Executors.newFixedThreadPool(threadSize);
    }


    public void handlepool(Socket acceptedSocket)
    {
        try{
            PrintWriter tocleint= new PrintWriter(acceptedSocket.getOutputStream());
            BufferedReader fromclient = new BufferedReader(new InputStreamReader( acceptedSocket.getInputStream()));

            tocleint.println("hi from server");
            String line = fromclient.readLine();
            System.out.println(line);
            tocleint.close();
            fromclient.close();
            acceptedSocket.close();


        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static void main(String [] args)
    {
        try{

            int port=8010;
            int threadSize=100;
            Server server = new Server(threadSize);

            ServerSocket socket=new ServerSocket(port);

            socket.setSoTimeout(10000);
            while(true)
            {
                Socket acceptedSocket= socket.accept();

                server.threadpool.execute(()->server.handlepool(acceptedSocket));
            }


        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}