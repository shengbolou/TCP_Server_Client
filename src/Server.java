import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Shengbo Lou on 2016/10/12.
 *
 * Server Class
 */
public class Server {
    //port num
    private final static int PORT_NUM = 52414;

    public static void main(String argv[]) throws Exception
    {
        //client sentence contains oc and two integers
        String clientSentence;

        //status code and result values
        int statusCode = 200;
        int result = 0;

        //result msg that is sent back to clients
        String resultMsg;

        //open a server socket
        ServerSocket welcomeSocket = new ServerSocket(PORT_NUM);
        System.out.println("Server is running and listening at port " + welcomeSocket.getReceiveBufferSize());

        while(true) {
            //start accepting clients
            Socket connectionSocket = welcomeSocket.accept();
            System.out.println("Client is connected...");
            //initiate reader that reads inputs from clients
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            //initiate writer that sends msg to clients
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            //get client inputs
            clientSentence = inFromClient.readLine();
            //get oc and two integers
            String[] inputs = clientSentence.split(",");
            String oc = inputs[0];
            int int1 = Integer.valueOf(inputs[1]);
            int int2 = Integer.valueOf(inputs[2]);

            //if int1 and int2 are integers
            if(int1 == (int)int1 && int2 == (int)int2){
                switch (oc){
                    case "+":
                        result = int1 + int2;
                        break;
                    case "-":
                        result = int1 - int2;
                        break;
                    case "*":
                        result = int1 * int2;
                        break;
                    case "/":
                        result = int1 / int2;
                        break;
                    //invalid oc
                    default:
                        statusCode = 300;
                        result = -1;
                        break;
                }
            }
            //invalid integers
            else{
                statusCode = 300;
                result = -1;
            }
            System.out.println("Status code: " + statusCode);
            System.out.println("Result: " + result);
            resultMsg = statusCode+","+ result + '\n';
            outToClient.writeBytes(resultMsg);

            //close socket, reader and writer
            connectionSocket.close();
            inFromClient.close();
            outToClient.close();
        }
    }
}
