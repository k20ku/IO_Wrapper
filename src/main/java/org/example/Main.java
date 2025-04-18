package org.example;

import org.io.io_wrapper.IO;

public class Main {
    public static void main(String[] args) {
        IO<Void> ioVoid = IO.of(() -> "Hallo Welt!")
                .mapToVoid(System.out::println)
                .map(_ -> 3.141592)
                .mapToVoid(System.out::println);
        IO<Void> testIO = IO.of(()->"").mapToVoid(System.out::println)
                .flatMap(_-> ioVoid);
        testIO.unsafeRun();
    }
}