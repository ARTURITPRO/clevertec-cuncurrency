package ru.clevertec;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The Request class represents a request from a client to a server.
 */
@AllArgsConstructor
public class Request {
    @Getter
    private int value;
}