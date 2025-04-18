package org.io.io_wrapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IOTest {
    static class valueField {
        private int number;
        public void setNumber(int number) {
            this.number = number;
        }
    }
    static class ReferenceField {
        private String str;
        public void setStr(String str) {
            this.str = str;
        }
    }
//    @BeforeEach


    @Test
    void flatMap() {

    }

    @Test
    void map() {
    }

    @Test
    void mapToVoid() {
    }

    @Test
    void peek() {
    }

    @Test
    void unsafeRun() {
    }
}