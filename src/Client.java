import javax.xml.crypto.Data;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by shengbo lou on 2016/10/12.
 *
 * Client Class
 */
public class Client {

    private final static int PORT_NUM = 52414;
    private static Socket socket;
    private static DataInputStream inputStream;
    private static DataOutputStream outputStream;

    public static void main(String[] args){
        try{
            System.out.println("Please enter oc and two number in lines");

            //initiate scanner to get user input
            Scanner scanner = new Scanner(System.in);
            String oc = scanner.nextLine();
            int int1 = Integer.valueOf(scanner.nextLine());
            int int2 = Integer.valueOf(scanner.nextLine());

            //open socket
            System.out.println("Client is running, connected to port "+ PORT_NUM);
            socket = new Socket("localhost",PORT_NUM);
            //initiate input stream to get msg from server
            inputStream = new DataInputStream(socket.getInputStream());
            //initiate output stram to send msg to server
            outputStream = new DataOutputStream(socket.getOutputStream());

            //send user inputs to server
            outputStream.writeBytes(oc+"\n");
            outputStream.writeBytes(int1+"\n");
            outputStream.writeBytes(int2+"\n");

            System.out.println(oc);
            System.out.println(int1);
            System.out.println(int2);

            while(inputStream.available()>0)
            {
                System.out.println(inputStream.readLine());
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                inputStream.close();
                outputStream.close();
                socket.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
