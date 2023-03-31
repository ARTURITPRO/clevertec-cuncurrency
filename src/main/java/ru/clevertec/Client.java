package ru.clevertec;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * The Client class represents a client that sends requests to a server and receives responses.
 */
@RequiredArgsConstructor
public class Client {

    private final Server server;

    @Getter
    private final List<Request> requests;

    @Getter
    private final List<Response> responses;

    /**
     * Sends the specified number of requests to the server asynchronously and stores the responses.
     *
     * @param numRequests the number of requests to send
     */
    @SneakyThrows
    public void sendRequests(int numRequests) {
        ExecutorService executor = Executors.newFixedThreadPool(numRequests);
        List<Future<Response>> futures = new ArrayList<>();
        for (int i = 1; i <= numRequests; i++) {
            Request request = new Request(i);
            requests.add(request);
            System.out.println(i);
            Callable<Response> callable = () -> server.processRequest(request);
            Future<Response> future = executor.submit(callable);
            futures.add(future);
        }
        for (Future<Response> future : futures) {
            Response response = future.get();
            responses.add(response);
        }
        executor.shutdown();
    }
}