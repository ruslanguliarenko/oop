package com.company;

import java.util.ArrayList;
import java.util.List;

public class Server {

    private int number;
    private List<Node> nodeList;

    public Server(int number, int amountNode){
        this.number = number;
        nodeList = createNode(amountNode);
    }

    private ArrayList<Node> createNode(int amount){
        ArrayList<Node> resultNode = new ArrayList<>();

        for(int i = 0;i<amount; i++){
            resultNode.add(new Node(i));
        }

        return resultNode;
    }

    public void setAllNodeFailed(){
        for(Node node : nodeList){
            node.setFailed(true);
        }
    }

    public Node getNode(int index) {
        return nodeList.get(index);
    }

    public int getNumber() {
        return number;
    }

    public int getAmountNode() {
        return nodeList.size();
    }
}
