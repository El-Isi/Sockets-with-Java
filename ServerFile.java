import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerFile {

    public static void main(String[] args) {

        ServerSocket server = null;
        Socket sc = null;
        DataInputStream in;
        DataOutputStream out;

        //port
        final int PORT = 5000;

        try {
            //Create the socket
            server = new ServerSocket(PORT);
            System.out.println("Server ready");
            String pth = "./Ejemplo.txt";
            File fle = new File(pth);
            BufferedWriter bw;
            int j;

            //Always is listening
            while (true) {

                //Wait for a client
                sc = server.accept();
                char[] output;

                System.out.println("Client connected, transforming the next word:");
                in = new DataInputStream(sc.getInputStream());
                out = new DataOutputStream(sc.getOutputStream());

                //Read the message
                String message = in.readUTF();

                output = message.toCharArray();

                System.out.println(output);

                for (int i = 0; i < output.length; i++){
                    if (Character.isLowerCase(output[i])){
                        output[i] = Character.toUpperCase(output[i]);
                    } else {
                        output[i] = Character.toLowerCase(output[i]);
                    }
                }

                if (fle.exists()) {
                    try {
                        FileWriter fstream = new FileWriter(pth, true);
                        BufferedWriter outp = new BufferedWriter(fstream);
                        Date fecha = new Date();
                        outp.append("\r\n");
                        outp.write(fecha + "\t" + String.valueOf(output) + "\t" + message);
                        outp.close();
                    } catch (IOException ex) {
                        System.out.println("Error: " + ex.getMessage());
                    }
                } else {
                    fle.createNewFile();

                    try {
                        FileWriter fstream = new FileWriter(pth, true);
                        BufferedWriter outp = new BufferedWriter(fstream);
                        Date fecha = new Date();
                        outp.write("Format: DATE - WORD SENDED - WORD RECIEVED");
                        outp.append("\r\n");
                        outp.write(fecha + "\t" + String.valueOf(output) + "\t" + message);
                        outp.close();
                    } catch (IOException ex) {
                        System.out.println("Error: " + ex.getMessage());
                    }
                }

                //Send the message
                out.writeUTF(String.valueOf(output));

                //Close socket
                sc.close();
                System.out.println("Client off");

            }

        } catch (IOException ex) {
            Logger.getLogger(ServerFile.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}