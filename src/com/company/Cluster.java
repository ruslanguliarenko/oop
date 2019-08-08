package com.company;

import com.company.exception.ServerNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cluster implements Fallible{

    private List<Optional<Server>> servers;

    public Cluster(int amountServer){
        servers = createServers(amountServer);
    }

    public void sendMessage(){

        Random random = new Random();

        int numberFailedServer = random.nextInt(servers.size());

        Optional<Server> failedServer = servers.get(numberFailedServer);

        int amountNode = failedServer.getValue().getAmountNode();
        int numberFailedNode = random.nextInt(amountNode);

        failedNode(failedServer, numberFailedNode);

    }

    @Override
    public boolean isFailed(int serverNumber, int nodeNumber) throws ServerNotFoundException {
        if(serverNumber < servers.size()){
            Optional<Server> server = servers.get(serverNumber);
            Optional<Node> node = server.getValue().getNode(nodeNumber);

            return node.getValue().isFailed();
        }else {
            throw new ServerNotFoundException("Server " + serverNumber + " is not found!");
        }
    }

    private ArrayList<Optional<Server>> createServers(int amount){
        ArrayList<Optional<Server>> resultServers = new ArrayList<>();

        for(int i = 0;i<amount; i++){
            if(new Random().nextBoolean()) {
                resultServers.add(new Optional<>(new Server(i, i + 1)));

            }
        }

        return resultServers;
    }

    private void failedNode(Optional<Server> failedServer, int numberFailedNode){

        for(int i = numberFailedNode; i < failedServer.getValue().getAmountNode(); i++){
            failedServer.getValue().getNode(i).getValue().setFailed(true);
        }

        for(int i = failedServer.getValue().getNumber()+1; i < servers.size(); i++){
            servers.get(i).getValue().setAllNodeFailed();
        }
    }

    public List<Optional<Server>> getServers() {
        return servers;
    }
}
