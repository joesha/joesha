package connector;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class Fetcher {

    public void fetchFile(String username, String host, String passwd) throws JSchException, SftpException, IOException {
        JSch conn = new JSch();
        Session session = null;


        ChannelSftp channel = (ChannelSftp)session.openChannel("sftp");
        channel.connect();

        //change folder on the remote server
        channel.cd("/opt/KnowledgePro/apache-tomcat-7.0.42/webapps/BIRTCDU/reports/IBReports");

        InputStream in = channel.get("testScp");
        // set local file
        String lf = "OBJECT_FILE";
        FileOutputStream tergetFile = new FileOutputStream(lf);

        // read containts of remote file to local
        int c;
        while ( (c= in.read()) != -1 ) {
            tergetFile.write(c);
        } 

        in.close();
        tergetFile.close();

        channel.disconnect();
        session.disconnect();   

    }

}