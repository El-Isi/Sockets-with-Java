import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    public static void main(String[] args) {

        ServerSocket server= null;
        Socket sc = null;
        DataInputStream in;
        DataOutputStream out;

        //port
        final int PUERTO = 5000;

        try {
            //Create the socket
            server = new ServerSocket(PUERTO);
            System.out.println("Server ready");

            //Siempre estara escuchando peticiones
            while (true) {

                //Wait for a client
                sc = server.accept();
                char[] output;

                System.out.println("Client connected, transforming the next word:");
                in = new DataInputStream(sc.getInputStream());
                out = new DataOutputStream(sc.getOutputStream());

                //Receive message
                String mensaje = in.readUTF();

                output = mensaje.toCharArray();

                System.out.println(output);

                for (int i = 0; i < output.length; i++){
                    if (Character.isLowerCase(output[i])){
                        output[i] = Character.toUpperCase(output[i]);
                    } else {
                        output[i] = Character.toLowerCase(output[i]);
                    }
                }

                //Send message
                out.writeUTF(String.valueOf(output));

                //Close socket
                sc.close();
                System.out.println("Cliente desconectado");

            }

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}