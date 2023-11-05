package com.example;
import java.net.*;
import java.io.*;

public class ServerStr
{
    ServerSocket server = null;
    Socket client = null;
    String stringaRicevuta = null;
    String stringaModificata = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;

    public Socket attendi(int port) {
        try {
            System.out.println("SERVER partito in esecuzione ...");
            server = new ServerSocket(port);
            client = server.accept();//socket (indirizzo ip e porta) di chi vuole comunicare con me 
            //accept()-> si ferma finche nessuno si connette
            server.close();

            inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outVersoClient = new DataOutputStream(client.getOutputStream());
            //creo un oggetto per ricevere la comunicazione da inDalClient e rispondere attraverso outVersoClient

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server !");
            System.exit(1);
        }
        return client;
    }

    public void comunica() {
        try {
            stringaRicevuta = inDalClient.readLine(); //legge quello che manda il client, rimane fermo se non legge niente
            System.out.println("Ricevuta la stringa dal client : " + stringaRicevuta);
            stringaModificata = modificaStringa(stringaRicevuta);
            System.out.println("Invio la stringa modificata al client ...");
            outVersoClient.writeBytes(stringaModificata + '\n'); //scrivo la mia stringa modificata
            System.out.println("Stringa modificata inviata al client, chiusura connessione");
            client.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("something went wrong!");
            System.exit(1);
        }
    }

    public String modificaStringa(String stringaRicevuta) {
        return stringaRicevuta.toUpperCase(); //per renderlo maiuscolo
    }
}