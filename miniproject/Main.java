package miniproject;
import java.util.*;
import java.io.*;

class Passenger{
	String name;
	int age;
	long aadhar;
	String src, dest;
	void accept() {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter passenger name : ");
		name = s.next();
		System.out.println("Enter passenger age : ");
		age = s.nextInt();
		System.out.println("Enter passenger aadhar number : ");
		aadhar = s.nextLong();
	}
	String toString(String s, String d) {
		return "\nPassenger name : "+name+" Passenger age : "+age+" Passenger aadhar number : "+ aadhar + " Passenger source : "+s +" Passenger destination : "+d;
	
	}
}
public class Main 
{

	public static void main(String[] args) throws IOException 
	{
		Graph roadGraph =new Graph(5,10);
		roadGraph.createRG();
		Graph airGraph=new Graph(5,7);
        airGraph.createAG();
        Mapp roadmap = new Mapp("road");
        Mapp airmap = new Mapp("air");
        File airDB  = new File("AirDB.txt");
        File roadDB  = new File("RoadDB.txt");
        FileWriter airfw = new FileWriter(airDB,true);
        FileWriter roadfw = new FileWriter(roadDB,true);
        
        int option = 0;
    	System.out.println("WELCOME!");
    	Scanner s = new Scanner(System.in);
    	int x = 0;
    	do {
    		System.out.println("How would you like to travel? \n1. Air \n2. Road");
    		int ch = s.nextInt();
    		Passenger p = new Passenger();
    		p.accept();
    		switch(ch) {
    		case 1:
    		{
        	System.out.println("Enter your starting location from the given options : ");
        	System.out.println("\nPune\nLondon\nFrankfurt\nNew York\nMelbourne");
        	String source = s.next();
        	System.out.println("Enter your destination from the given options : ");
        	System.out.println("\nPune\nLondon\nFrankfurt\nNew York\nMelbourne");
        	String destination = s.next();
        	airfw.write(p.toString(source,destination));
        	airfw.flush();
            Tree t = new Tree();
            int src = airmap.map1.get(source);
            int dest = airmap.map1.get(destination);
            Treenode root = new Treenode(src);
            t.src = root;
            t.graphToTree(airGraph, t.src, dest);
            t.ShowConnectedPath(dest, airmap);
        	
    		}
    		break;
    		case 2:
    		{
        	System.out.println("Enter your starting location from the given options : ");
        	System.out.println("\nPune\nMumbai\nNagpur\nIndore\nDelhi");
        	String source = s.next();
        	System.out.println("Enter your destination from the given options : ");
        	System.out.println("\nPune\nMumbai\nNagpur\nIndore\nDelhi");
        	String destination = s.next();
        	roadfw.write(p.toString(source,destination));
        	roadfw.flush();
            int src = roadmap.map1.get(source);
            int dest = roadmap.map1.get(destination);
            System.out.println("The shortest path from "+source+" to "+destination +" is : ");
            int distance = roadmap.shortestpath(roadGraph, source, destination);
            System.out.println("The minimum distance from "+source+" to "+destination +" is : "+distance+"km.");
            System.out.println("The time taken to travel by road on an average of 60kmph is : "+distance/60 +" hours.");
    		}
    		break;
    		default:break;
    		}
    		System.out.println("Do you wish to continue? \n1. Yes\n2. No");
    		x = s.nextInt();
    	}while(x!=2);

}
}
