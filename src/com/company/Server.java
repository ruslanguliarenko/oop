package com.company;

import com.company.exception.NodeNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Server {

    private int number;
    private List<Optional<Node>> nodeList;

    public Server(int number, int amountNode){
        this.number = number;
        nodeList = createNode(amountNode);
    }

    private ArrayList<Optional<Node>> createNode(int amount){
        ArrayList<Optional<Node>> resultNode = new ArrayList<>();

        for(int i = 0;i<amount; i++){
            if(new Random().nextBoolean()) {
                resultNode.add(new Optional<>(new Node(i)));
            }
        }

        return resultNode;
    }

    public void setAllNodeFailed(){
        for(Optional<Node> node : nodeList){
            node.getValue().setFailed(true);
        }
    }

    public Optional<Node> getNode(int index) {
        if(nodeList.size() > index){
            return nodeList.get(index);
        }else{
            throw new NodeNotFoundException("Node " + index + " is not found!");
        }
    }

    public int getNumber() {
        return number;
    }

    public int getAmountNode() {
        return nodeList.size();
    }

    public List<Optional<Node>> getNodeList() {
        return nodeList;
    }
}
