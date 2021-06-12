package miniproject;

public class Node
{
   int city;
   int dist;
   
   
	   Node(int c,int wt)
	   {
	     city=c;
	     dist=wt;
	   }
	   Node(){this(0,0);}
}