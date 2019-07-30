package com.company;

import com.company.exception.ServerNotFoundException;

public class Main {

    public static void main(String[] args) {
        Cluster cluster = new Cluster(4);
        FailSearchEngine failSearchEngine = new FailSearchEngine();

        cluster.sendMessage();

        try {

            System.out.println(failSearchEngine.search(cluster));

        }catch (ServerNotFoundException e){

            System.out.println(e.getMessage());

        }
    }
}
