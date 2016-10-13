import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Shengbo Lou on 2016/10/12.
 *
 * Client Class
 */
public class Client {

    private final static int PORT_NUM = 52414;

    public static void main(String argv[]) throws Exception
    {
        //user inputs
        String sentence;

        //result msg from server
        String result;


        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        //open socket
        Socket clientSocket = new Socket("localhost", PORT_NUM);

        //initiate output stream to send msg to server
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

        //initiate reader to get msg from server
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        //read oc and integers from user
        System.out.println("Client is running and connected to server");
        System.out.println("Please enter oc and integers, separate with commas");
        sentence = inFromUser.readLine();

        //send user inputs to server
        outToServer.writeBytes(sentence + '\n');

        //get result back from server
        result = inFromServer.readLine();

        System.out.println("FROM SERVER: ");

        if(result.split(",")[0].equals("300")){
            System.out.println("Inputs are invalid..");
        }
        else{
            System.out.println("Result: " + result.split(",")[1]);
        }


        //close socket, reader and writer
        clientSocket.close();
        inFromServer.close();
        outToServer.close();
    }
}
