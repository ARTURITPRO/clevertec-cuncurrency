package ru.clevertec;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The Server class represents a server that receives and processes requests from clients.
 */
@RequiredArgsConstructor
public class Server {
    private final Lock lock;
    private final Random random;

    /**
     * Processes the specified request and returns a response.
     *
     * @param request the request to process
     * @return the response
     */
    @SneakyThrows
    public Response processRequest(Request request) {
        lock.lock();
        try {
            System.out.print("Client request vale = " + request.getValue() + "-->");
            Thread.sleep(random.nextInt(2000));
            int value = 100 - request.getValue();
            System.out.println(" Server response value = " + value);
            return new Response(value);
        } finally {
            lock.unlock();
        }
    }
}