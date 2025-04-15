
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

   public Runnable getRunnable() { 

    return new Runnable() {
        @Override
        public void run(){

            int port =8010;

            try{

                InetAddress address = InetAddress.getByName("localhost");

                Socket socket= new Socket(address,port);
                try{

                    PrintWriter tosockeet = new PrintWriter(socket.getOutputStream(),true);
                    BufferedReader fromsocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    tosockeet.println("hi from cleint");
                    String line = fromsocket.readLine();
                    System.out.println(line);
                   
                }catch(Exception ex)
                {
                    ex.printStackTrace();
                }

            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }

        }
    };

   }

    public static void main(String [] args)
    {
        Client client = new Client();

        try{

            for(int i=0;i<100;i++)
            {
                Thread thread = new Thread(client.getRunnable());
                thread.start();
            }

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
}
