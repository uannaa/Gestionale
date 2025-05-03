/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Frame.Login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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
    
    public static boolean inviaCategoria(String tipo, String username, String s) {
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
}
