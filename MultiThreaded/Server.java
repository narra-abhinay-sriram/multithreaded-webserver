
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class Server {

    public Consumer<Socket> getConsumer(){

        return (clientSocket)->{
            try{

                PrintWriter toclient = new PrintWriter(clientSocket.getOutputStream(),true);

                BufferedReader fromclient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                toclient.println("hello from server");

                String line = fromclient.readLine();
                System.out.println("message from cleint "+line);
                toclient.close();
                fromclient.close();
                clientSocket.close();
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }

        };
    }

    public static void main (String [] args)
    {
        Server server = new Server();
        try{
            int port=8010;

            ServerSocket socket = new ServerSocket(port);
            socket.setSoTimeout(10000);
            while (true) {

                Socket acceptedSocket = socket.accept();

                Thread thread= new Thread(()->server.getConsumer().accept(acceptedSocket));
                thread.start();
                
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
