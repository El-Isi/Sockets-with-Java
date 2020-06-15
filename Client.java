import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    public static void main(String[] args) {

        //Host
        final String HOST = "127.0.0.1";
        //Port
        final int PORT= 5000;
        DataInputStream in;
        DataOutputStream out;

        try {

            System.out.println("Type a word: ");
            Scanner scan = new Scanner(System.in);
            String palabra = scan.nextLine();

            //Create the socket
            Socket sc = new Socket(HOST, PORT);

            in = new DataInputStream(sc.getInputStream());
            out = new DataOutputStream(sc.getOutputStream());

            //Send message to server
            out.writeUTF(palabra);

            //Recieve message from server
            String message = in.readUTF();

            System.out.println(message);

            sc.close();

        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}