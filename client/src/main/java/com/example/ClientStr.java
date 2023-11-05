package com.example;

import java.io.*;
import java.net.*;
import java.util.*;

public class ClientStr {

    Socket miosocket;
    Scanner input;
    String stringaUtente;
    String stringaRicevutaDalServer;
    DataOutputStream outVersoServer;
    BufferedReader inDalServer;

    public Socket connetti(String nomeServer, int portaServer) {
        System.out.println("CLIENT in esecuzione ...");
        try {

            input = new Scanner(System.in);
            miosocket = new Socket(nomeServer, portaServer);

            outVersoServer = new DataOutputStream(miosocket.getOutputStream());
            inDalServer = new BufferedReader(new InputStreamReader(miosocket.getInputStream()));
            //creo un oggetto per ricevere la comunicazione da inDalClient e rispondere attraverso outVersoClient

        } catch (UnknownHostException e) {
            System.err.println("Host sconosciuto");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("something went wrong, closing client...");
            System.exit(1);
        }
        return miosocket;
    }
    public void comunica() {
        try {
            //leggo la stringa
            System.out.println("inserisci la stringa da trasmettere al server:" + '\n');
            stringaUtente = input.next();
            System.out.println("invio la stringa al server e attendo ...");
            outVersoServer.writeBytes(stringaUtente + '\n');
            
            stringaRicevutaDalServer = inDalServer.readLine();
            System.out.println("risposta dal server " + '\n' + stringaRicevutaDalServer);
            //chiudo la connessione
            System.out.println("CLIENT: termina elaborazione e chiude connessione");
            miosocket.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la comunicazione col server!");
            System.exit(1);
        }
    } 
}