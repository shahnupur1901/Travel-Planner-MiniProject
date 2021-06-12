package miniproject;
import java.util.*;

public class Mapp
{
	   HashMap<String,Integer> map1=new HashMap<String,Integer>();
	   Mapp(String s)
	   {
		   if(s.equalsIgnoreCase("road"))
		   {
			map1.put("Pune",0);
	   		map1.put("Mumbai", 1);
	   		map1.put("Nagpur",2);
	   		map1.put("Indore",3);
	   		map1.put("Delhi",4);}
		   else
		   {
			   map1.put("Pune", 0);
			   map1.put("London", 1);
			   map1.put("Frankfurt", 2);
			   map1.put("New York", 3);
			   map1.put("Melbourne", 4);
		   }
	   }
	   public String getCity(int j)
	   {
		  
		   for(Map.Entry m:map1.entrySet())
		   {
			   int x=(int)m.getValue();
			   if(x==j)
			   {
				   return (String)m.getKey();
			   }
		   }
		   return null;
	   }
	   int shortestpath(Graph g,String city1,String city2)
	   {
		   return g.dijsktra((int)map1.get(city1),(int)map1.get(city2),this);
	   }
}
