package ru.clevertec;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * The Response class represents a response from a server to a client.
 */
@AllArgsConstructor
@EqualsAndHashCode
public class Response {

    @Getter
    private int value;
}