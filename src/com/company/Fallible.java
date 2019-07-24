package com.company;

public interface Fallible {
    boolean isFailed(int serverNumber, int nodeNumber);
}
