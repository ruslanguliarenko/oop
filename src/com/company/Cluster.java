package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cluster implements Fallible{

    private List<Server> servers;

    public Cluster(int amountServer){
        servers = createServers(amountServer);
    }

    public void sendMessage(){

        Random random = new Random();

        int numberFailedServer = random.nextInt(servers.size());

        Server failedServer = servers.get(numberFailedServer);

        int amountNode = failedServer.getAmountNode();
        int numberFailedNode = random.nextInt(amountNode);

        failedNode(failedServer, numberFailedNode);

    }

    @Override
    public boolean isFailed(int serverNumber, int nodeNumber) {

        Server server = servers.get(serverNumber);
        Node node = server.getNode(nodeNumber);

        return node.isFailed();
    }

    private ArrayList<Server> createServers(int amount){
        ArrayList<Server> resultServers = new ArrayList<>();

        for(int i = 0;i<amount; i++){
            resultServers.add(new Server(i, i+1));
        }

        return resultServers;
    }

    private void failedNode(Server failedServer, int numberFailedNode){

        for(int i = numberFailedNode; i < failedServer.getAmountNode(); i++){
            failedServer.getNode(i).setFailed(true);
        }

        for(int i = failedServer.getNumber()+1; i < servers.size(); i++){
            servers.get(i).setAllNodeFailed();
        }
    }

    public List<Server> getServers() {
        return servers;
    }
}
