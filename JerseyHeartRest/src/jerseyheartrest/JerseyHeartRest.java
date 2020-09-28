/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jerseyheartrest;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author hinte
 */
@Path("message")
public class JerseyHeartRest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            HttpServer server = HttpServerFactory.create("http://localhost:8080/rest");
            server.start();
        } catch (IOException ex) {
            Logger.getLogger(JerseyHeartRest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @GET
    @Path("test1")
    @Produces(MediaType.TEXT_PLAIN)
    public String heart() {
        return String.valueOf(readHeart());
    }

    private static String readHeart() {
        BufferedReader bur = null;
        try {
            bur = new BufferedReader(new FileReader("Heart.txt"));
            return bur.readLine();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JerseyHeartRest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JerseyHeartRest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bur.close();
            } catch (IOException ex) {
                Logger.getLogger(JerseyHeartRest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

}
