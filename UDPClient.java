/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author HP G4
 */

import java.io.*;
import java.net.*;

//receives IP and port in command line
public class UDPClient {
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException{
        String theRequest = "Hello server"; //a request
        DatagramSocket clientSocket = new DatagramSocket(); //make a socket object
        
        InetAddress IPAdd = InetAddress.getByName(args[0]);
        
        byte[] sendData = new byte[2048]; //to hold the data to be sent
        byte[] getData = new byte[2048]; //to hold incoming data
        
        sendData = theRequest.getBytes(); //convert string to bytes
        
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAdd, Integer.parseInt(args[1])); //prepare the data to be sent into a packet, complete with the target's ip address and the port
        
        clientSocket.send(sendPacket); //send the packet to the server
        
        DatagramPacket getPacket = new DatagramPacket(getData, getData.length); //prepare a packet to receive a packet from the server
        
        clientSocket.receive(getPacket); //receive a packet from the server
        
        String theResponse = new String(getPacket.getData());
        System.out.println(("Server says: " + theResponse).trim());
        
        clientSocket.close();
        
    }
}
