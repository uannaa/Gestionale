/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Frame.Login;

import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Vector;

/**
 *
 * @author paolo
 */
public class AuthClient {
    public static boolean inviaRichiesta(String tipo, String username, String password) {
        try (
            Socket socket = new Socket("45.88.223.77", 12345);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            out.println(tipo + ":#$%#**&^$:" + username + ":#$%#**&^$:" + password);
            String response = in.readLine();
            return "OK".equals(response);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    //Ci servira per l invio dei dati sul server 
    public static boolean inviaDati(String tipo, String username, String s) {
        try (
            Socket socket = new Socket("45.88.223.77", 12345);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            out.println(tipo + ":#$%#**&^$:" + username + ":#$%#**&^$:" + s);
            String response = in.readLine();
            return "OK".equals(response);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static Vector<String> richiediDati(String username, String tipo2) {
        Vector<String> dati = new Vector<String>();

        try (
            Socket socket = new Socket("45.88.223.77", 12345);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            out.println("RICHIEDI" + ":#$%#**&^$:" + username + ":#$%#**&^$:" + tipo2);

            String line = in.readLine();
            if (line == null || line.equals("FALLITO!")) {
                System.out.println("Errore: richiesta fallita");
                return null;
            }
            
            if (!line.equals("FINE")) {
                dati.add(line);
            }

            // Prima riga valida, salviamola
            

            // Continua a leggere finché non arriva "FINE"
            while ((line = in.readLine()) != null && !line.equals("FINE")) {
                dati.add(line);
            }

            return dati;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

