/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.*;
import java.io.*;        

/**
 *
 * @author HP G4
 */

//get port number from cmd line args

public class UDPServer {  
    static DatagramSocket serverSocket = null;
    public static void main(String[] args)
    {
        try{
            serverSocket = new DatagramSocket(Integer.parseInt(args[0])); //creates a DatagramSocket object and tie it to port 9999
        }
        catch(NumberFormatException e){
            System.out.println(e.toString());
            //exit, or we can prompt the user for a port
            System.exit(1);
        }
        catch(IOException e){
            System.out.println(e.toString());
            //exit, or we can ask the user for a different port
            System.exit(1);
        }
        finally{
            System.out.println("Uncaught exception occured. Please quote Error code 1-1.");
            System.exit(1);
        }
        
        byte[] getData = new byte[2048]; //variable for incoming data, 2048 bytes
        byte[] sendData = new byte[2048]; //variable for outgoing data, 2048 bytes
        
        while(true){
                DatagramPacket getPacket = new DatagramPacket(getData, getData.length);
                         
                try{
                serverSocket.receive(getPacket); //receive data from client                
                }
                catch(IOException e){
                    System.err.println(e.toString());
                    System.exit(1);
                }
                
                String theString = "Hello, my name is â€¦â?â€¦â?. And my Id is â€¦â?â€¦â?"; //A response message
                
                InetAddress IPAdd = getPacket.getAddress(); //Gets the requesting computer's IP by going through the packet(it has the ip address attached
                int portNum = getPacket.getPort(); //Gets the requesting computer's port number by going through the packet(it has the ip address attached
                
                sendData = theString.getBytes(); //convert string into byte
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAdd, portNum); //compile the to be sent data into a DatagramPacket
                
                try{
                serverSocket.send(sendPacket); //send the packet
                }
                catch(IOException e){
                    System.err.println(e.toString());
                    System.exit(1);
                }
        }
    }
}
