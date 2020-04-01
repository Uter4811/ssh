import com.jcraft.jsch.*;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Scanner;

/**
 * @author World
 */
public class SSHReadFile {

    public static void main(String args[]) {
        String user = "john";
        String password = "mypassword";
        String host = "192.168.100.23";
        int port = 22;
        String remoteFile = "/home/john/test.txt";

        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            System.out.println("Establishing Connection...");
            session.connect();
            System.out.println("Connection established.");
            System.out.println("Crating SFTP Channel.");
            ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp");
            sftpChannel.connect();
            System.out.println("SFTP Channel created.");

            InputStream inputStream = sftpChannel.get(remoteFile);
            FileOutputStream outputStream = new FileOutputStream("C:\\Program Files\\ssh");

            try (Scanner scanner = new Scanner(new InputStreamReader(inputStream))) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    outputStream.write(line);;
                }
                inputStream.close();
                outputStream.close();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
