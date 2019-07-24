package com.company;

import java.util.List;

public class FailSearchEngine {

    public String search(Cluster cluster){
        List<Server> servers =  cluster.getServers();

        int numberFailedServer = searchFailedServer(servers.size(), cluster, 0);
        int numberFailedNode = searchFailedNode(servers.get(numberFailedServer).getAmountNode(), cluster, 0, numberFailedServer);

        return numberFailedServer + " " + numberFailedNode;

    }



    private int searchFailedServer(int amountServer, Cluster cluster, int count)
    {
        int numberServer = amountServer/2 + count;
        int numberNode = cluster.getServers().get(numberServer).getAmountNode() - 1;

        if(numberServer == 0){
            return numberServer;
        }

        if(cluster.isFailed(numberServer, numberNode)){
            if(!cluster.isFailed(numberServer-1, numberNode-1)){
                return numberServer;
            }
            else{
                return searchFailedServer(numberServer - count, cluster, count);
            }
        }else{
            return searchFailedServer(numberServer -count, cluster, count+numberServer);
        }
    }

    private int searchFailedNode(int amountNode, Cluster cluster, int count, int numberFailedServer)
    {
        int numberNode = amountNode/2 + count;

        if(numberNode == 0 || numberNode == amountNode){
            return numberNode;
        }

        if(cluster.isFailed(numberFailedServer, numberNode)){
            if(!cluster.isFailed(numberFailedServer, numberNode-1)){
                return numberNode;
            }else {
                return searchFailedNode(numberNode - count, cluster, count, numberFailedServer);
            }
        }else{
            if(cluster.isFailed(numberFailedServer, numberNode+1)){
                return numberNode + 1;
            }else {
                return searchFailedNode(numberNode - count, cluster, count + numberNode, numberFailedServer);

            }
        }
    }
}
