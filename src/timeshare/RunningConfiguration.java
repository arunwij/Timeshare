/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timeshare;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import kademlia.JKademliaNode;
import kademlia.KadConfiguration;
import kademlia.KadServer;
import kademlia.NameGenerator;
import kademlia.node.Node;
import kademlia.node.Port;
import kademlia.operation.ConnectOperation;
import kademlia.routing.Contact;

/**
 *
 * @author Artista
 */
public class RunningConfiguration {

    /* local node cofigurations */
    public final static int FILE_PORT = 11655; // file port
    public static ServerSocket SERVER_SOCKET;
    public static int LOCAL_NODE_PORT; // message port
    public static InetAddress LOCAL_INETADDRESS; //ip address
    public static String LOCAL_NODE_NAME; // node name
    public static Node LOCAL_NODE; // Node
    public static JKademliaNode LOCAL_JKNODE; // JKademlia node
    public static Contact LOCAL_NODE_CONTACT; // Contact
    public static KadServer KAD_SERVER; // Kad server
    public static KadConfiguration KAD_CONFIGURATION; // Kad server configuraions

    /* bootstrap node configurations */
    public static final int BOOTSTRAP_PORT = 11000; // bootstrap port
    public static final String BOOTSTRAP_ADDRESS = "192.168.1.5"; // bootstrap ip
    public final static String BOOTSTRAP_NODE_NAME = "bootstrap"; // bootstrap name
    public static InetSocketAddress BOOTSTRAP_NODE_SOCKET; // bootstrap socket
    public static Node BOOTSTRAP_NODE; // bootstrap Node

    public static String NODE_STATUS;
    public static String CONNECTION_STATUS;
    public static StringBuilder sb = new StringBuilder();
    
    public static int resultCount = 0;
    public static String[] results = new String[2];

    /* Startup node configurations */
    public static final boolean IS_BOOTSTRAP_NODE = true;
    private static boolean IS_WORKING = false;
<<<<<<< HEAD
    public static boolean IS_REDUNDANT = false;
   
    static { 
=======

    static {
>>>>>>> morning commit sanjula
        try {
            LOCAL_INETADDRESS = InetAddress.getLocalHost();
            if (IS_BOOTSTRAP_NODE) {
                BOOTSTRAP_NODE_SOCKET = new InetSocketAddress(LOCAL_INETADDRESS, BOOTSTRAP_PORT);
                LOCAL_JKNODE = new JKademliaNode(BOOTSTRAP_NODE_NAME, BOOTSTRAP_NODE_SOCKET);
                LOCAL_NODE_NAME = BOOTSTRAP_NODE_NAME;
                System.out.println("Kad-network bootstrap node started..");
//                sb.append("Kad-network bootstrap node started..\n");
                NODE_STATUS = "Kad-network bootstrap node started..";
            } else {
                BOOTSTRAP_NODE_SOCKET = new InetSocketAddress(BOOTSTRAP_ADDRESS, BOOTSTRAP_PORT);
                LOCAL_NODE_NAME = NameGenerator.get();
                LOCAL_JKNODE = new JKademliaNode(LOCAL_NODE_NAME, Port.RandomPort());
                System.out.println("Jkademlia local node created...");
                NODE_STATUS = "Jkademlia local node created...";
                BOOTSTRAP_NODE = new Node(BOOTSTRAP_NODE_SOCKET);
            }
        } catch (UnknownHostException ex) {
            Logger.getLogger(RunningConfiguration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RunningConfiguration.class.getName()).log(Level.SEVERE, null, ex);
        }

        KAD_SERVER = LOCAL_JKNODE.getServer();
        KAD_CONFIGURATION = LOCAL_JKNODE.getCurrentConfiguration();
        LOCAL_NODE = LOCAL_JKNODE.getNode();
        LOCAL_NODE_CONTACT = new Contact(LOCAL_NODE);
    }

    public static int getPeersCount() {
        //without bootstrap node
        return (LOCAL_JKNODE.getRoutingTable().getAllContacts().size() - 1);
    }

    public static List<Node> getNodeList() {
        List list = RunningConfiguration.LOCAL_JKNODE.getRoutingTable().getAllContacts();
        ListIterator lt = list.listIterator();
        List<Node> nodes = new <Node>ArrayList();
        while (lt.hasNext()) {
            Contact c = (Contact) lt.next();
            System.err.println("Node id:" + c.getNode().getNodeId());
            if (!c.getNode().equals(RunningConfiguration.BOOTSTRAP_NODE)) {//&& !c.getNode().equals(RunningConfiguration.LOCAL_NODE)) {
                nodes.add(c.getNode());
            }
        }
        return nodes;
    }

    public static void run() {
        if (!IS_BOOTSTRAP_NODE) {
            System.out.println("Connect operation starting");
            CONNECTION_STATUS = "Connect operation starting\n";
<<<<<<< HEAD
            long startTime = System.nanoTime();
            ConnectOperation connectOperation = new ConnectOperation(KAD_SERVER,LOCAL_JKNODE,BOOTSTRAP_NODE,KAD_CONFIGURATION);
=======
            ConnectOperation connectOperation = new ConnectOperation(KAD_SERVER, LOCAL_JKNODE, BOOTSTRAP_NODE, KAD_CONFIGURATION);
>>>>>>> morning commit sanjula
            try {
                connectOperation.execute();
                System.out.println("Connect operation done..");
                long endTime = System.nanoTime();
                LOCAL_JKNODE.getStatistician().setBootstrapTime(endTime - startTime);
                CONNECTION_STATUS += "Connect operation done..\n";
            } catch (IOException ex) {
                Logger.getLogger(RunningConfiguration.class.getName()).log(Level.SEVERE, null, ex);
            }
<<<<<<< HEAD
=======

>>>>>>> morning commit sanjula
        }
    }

    public static String checkNodeStatus() {
        return NODE_STATUS;
    }

    public static String connetionStatus() {
        return CONNECTION_STATUS;
    }

    public static void printResults() {
        
        try {
            while(resultCount<2){
            Thread.sleep(1000);
        }
            File resultFile = new File("data\\executor\\files\\Results.txt");
            resultFile.createNewFile();
            FileWriter writer = new FileWriter(resultFile);
            writer.write(results[0].toString());
            writer.flush();
            writer.close();
        } catch (Exception ex) {
            Logger.getLogger(RunningConfiguration.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       

    }

}
