package org.vyatsu.ahocorasick.utils;

public class Pair {
    int index;
    String value;

    public Pair(int index, String value) {
        this.index = index;
        this.value = value;
    }

    @Override
    public String toString() {
        return index + " : " + value;
    }
}