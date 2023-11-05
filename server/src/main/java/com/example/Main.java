package com.example;

public class Main {
    public static void main( String[] args )
    {
        ServerStr server = new ServerStr(); //da 0 a 1023 sono usate dal SO, di solito si mette 3000
        server.attendi(3000);
        server.comunica();
    }
}