import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;


/**
 * @author World
 */
public class SSHReadFile {
    static Properties properties = new Properties();

    public static void main(String args[]) {
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String host = properties.getProperty("host");
        String port = properties.getProperty("port");;
        String remoteFile = "/home/john/test.txt";


        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, Integer.parseInt(port));
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

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                while (reader.readLine() != null){
                    String line = reader.readLine();
                    outputStream.write(Integer.parseInt(line));
                }
                inputStream.close();
                outputStream.close();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
