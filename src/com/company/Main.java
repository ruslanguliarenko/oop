package com.company;

public class Main {

    public static void main(String[] args) {
        Cluster cluster = new Cluster(4);
        FailSearchEngine failSearchEngine = new FailSearchEngine();

        cluster.sendMessage();
        System.out.println(failSearchEngine.search(cluster));
    }
}
