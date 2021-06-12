package miniproject;
import java.util.*;

public class Graph 
{
    LinkedList<Node> adj[] ;
    int v,e;
    Graph(){}
    Graph(int v,int e)
    {
    	this.v=v;
    	this.e=e;
    	adj=new LinkedList[v];
    	for(int i=0;i<v;i++)
    	{
    		adj[i]= new LinkedList<Node>();
    		adj[i].add(new Node(i,0));
    	}
    }
    void addEdge(int a,int b,int d)//O(1)
    {
       Node temp=new Node(b,d);
       adj[a].add(temp);
       Node temp2=new Node(a,d);
       adj[b].add(temp2);
    }
    void showAdj()//O(n^2)
    {
    	for(int i=0;i<v;i++)
    	{
    		for(int j=0;j<adj[i].size();j++)
    		{
    			System.out.print(adj[i].get(j).city+" ");
    		}
    		System.out.println();
    	}
    }
    void createRG()
    {
    	addEdge(0,1,150);
    	addEdge(0,2,710);
    	addEdge(0,3,592);
    	addEdge(0,4,2000);
    	addEdge(1,2,836);
    	addEdge(1,3,583);
    	addEdge(1,4,1421);
    	addEdge(2,3,450);
    	addEdge(2,4,1080);
    	addEdge(3,4,866);
  
    }
    void createAG()
    {
    	addEdge(0,3,0);
    	addEdge(0,1,0);
    	addEdge(3,1,0);
    	addEdge(2,1,0);
    	addEdge(2,3,0);
    	addEdge(0,4,0);
    	addEdge(2,4,0);
    	
    }
    int dijsktra(int src,int des,Mapp map1)//O(n^2)
    {
    	int path[]=new int[v];
    	boolean visited[]=new boolean[v];
    	int distance[]=new int[v];
    	Arrays.fill(visited, false);
    	Arrays.fill(distance, Integer.MAX_VALUE);
    	Arrays.fill(path,src);
    	visited[src]=true;
    	int current=src;
    	distance[src]=0;
    	while(current!=des)
    	{
    		for(int i=1;i<adj[current].size();i++)
    		{
    			int vertex=adj[current].get(i).city;
    			int d=adj[current].get(i).dist;
    			if(distance[vertex]>(distance[current]+d)&&visited[vertex]==false)
    			{
    				distance[vertex]=distance[current]+d;
    				path[vertex]=current;
    				
    			}
    		}
    		int minv=current;
    		int mindis=Integer.MAX_VALUE;
    		for(int j=0;j<v;j++)
    		{
    			if(mindis>distance[j] && visited[j]==false)
    			{
    				mindis=distance[j];
    				minv=j;
    			}
    		}
    		current=minv;
    		visited[current]=true;
    	}
    	show(path,src,des,map1);
    	return distance[des];
    }
    void show(int path[],int src,int des,Mapp map1)//O(n)
    {
    	
    	Stack<Integer> st=new Stack<Integer>();
    	st.push(des);
    	int current=des;
    	while(current!=src)
    	{
    		current = path[current];
    		st.push(current);
    	}
    	while(!st.isEmpty())
    	{
    		int key=st.pop();
    		System.out.print(map1.getCity(key));
    		if(st.size()!=0) {System.out.print("--->");}
    	}
    	System.out.println();
    }
    
}
