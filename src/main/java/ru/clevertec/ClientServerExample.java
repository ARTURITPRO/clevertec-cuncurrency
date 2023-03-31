package ru.clevertec;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A simple Client-Server program using Java concurrency utilities.
 */
public class ClientServerExample {
    public static void main(String[] args) {
        Server server = new Server(new ReentrantLock(), new Random());
        Client client = new Client(server, new ArrayList<>(), new ArrayList<>());
        client.sendRequests(10);
    }
}