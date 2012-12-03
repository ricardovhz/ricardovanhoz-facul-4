/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bancodados.server;

import bancodados.server.response.CsvResponse;
import bancodados.server.response.Response;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author desenv01
 */
public class ServerImpl implements IServer {

    private String url;
    private int port;

    public ServerImpl() {
        this.url = "localhost";
        this.port = 1234;
    }

    public ServerImpl(String url, int port) {
        this.url = url;
        this.port = port;
    }

    @Override
    public Response executeCommand(String command) throws CommandException {
        Socket s = null;
        try {
            s = new Socket(url, port);
            s.getOutputStream().write((command + "\n").getBytes());
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String status = br.readLine();
            if (status.equals("OK")) {
                StringBuilder sb = new StringBuilder();
                String line=null;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                    sb.append("\n");
                }
                if (line == null) {
                    String response = sb.toString();
                    if (!response.isEmpty()) {
                        response = response.substring(0, sb.length() - 1);
                    }
                    return new CsvResponse(status, response);
                } else {
                    return new CsvResponse(status);
                }
            } else {
                throw new CommandException(status.substring(5));
            }
        } catch (UnknownHostException ex) {
            Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (s != null) {
                    s.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return null;
    }
}
