package ru.clevertec.integration;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.clevertec.Client;
import ru.clevertec.Request;
import ru.clevertec.Response;
import ru.clevertec.Server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClientServerIntegrationTest {
    @Tag("IntegrationTest")
    @Test
    public void testClientServerIntegration() {
        Server server = new Server(new ReentrantLock(), new Random());
        Client client = new Client(server, new ArrayList<>(), new ArrayList<>());
        client.sendRequests(10);

        List<Request> requests = client.getRequests();
        List<Response> responses = client.getResponses();

        assertEquals(10, requests.size());
        assertEquals(10, responses.size());

        List<Integer> requestValues = requests.stream().map(Request::getValue).toList();
        List<Integer> responseValues = responses.stream().map(Response::getValue).toList();

        for (int i = 0; i < requestValues.size(); i++) {
            int requestValue = requestValues.get(i);
            int responseValue = responseValues.get(i);
            assertEquals(100 - requestValue, responseValue);
        }
    }
    @Tag("TestMultithreading")
    @Tag("IntegrationTest")
    @Test
    @SneakyThrows
    public void testMultithreadingClientServerIntegration() {
        Server server = new Server(new ReentrantLock(), new Random());
        int numThreads = 10;
        Thread[] threads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            int finalI = i;
            threads[i] = new Thread(() -> {
                Request request = new Request(finalI);
                server.processRequest(request);
            });
            threads[i].start();
        }
        for (int i = 0; i < numThreads; i++) {
            threads[i].join();
        }
        assertTrue(true);
    }
}