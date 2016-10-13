import java.io.DataInputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Shengbo Lou on 2016/10/12.
 *
 * Server Class
 */
public class Server {
    //variables
    private final static int PORT_NUM = 52414;
    private static ServerSocket server;
    private static Socket clientSocket;
    private static DataInputStream inputStream;
    private static PrintStream outStream;

    public static void main(String[] args){

        try{
            //open a server socket
            server = new ServerSocket(PORT_NUM);
            System.out.println("Server is running at port " + server.getLocalPort());

            //user inputs
            String oc = "";
            int int1 = 0;
            int int2 = 0;

            //status code
            int statusCode = 200;

            while(true)
            {
                //start listening for clients
                clientSocket = server.accept();
                //initiate input stream to read inputs from client socket
                inputStream = new DataInputStream(clientSocket.getInputStream());
                //initiate output stream to send msg to clients
                outStream = new PrintStream(clientSocket.getOutputStream());

                if(inputStream.available()>0){
                    oc = inputStream.readLine();
                    int1 = Integer.parseInt(inputStream.readLine());
                    int2 = Integer.parseInt(inputStream.readLine());
                }else{
                    continue;
                }

                //validate inputs
                if((oc.equals("+")||oc.equals("-")||oc.equals("*")||oc.equals("/")) && int1==(int)int1 && int2==(int)int2)
                {
                    statusCode = 200;
                    outStream.println(int1+int2);
                    System.out.println(int1+int2);
                }
                else{
                    statusCode = 300;
                    outStream.println(-1);
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                inputStream.close();
                outStream.close();
                server.close();
                clientSocket.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
