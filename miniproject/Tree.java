package miniproject;
import java.util.*;
public class Tree 
{
	Treenode src;
	ArrayList<Integer> ancestors=new ArrayList<>();
	ArrayList<Treenode> destination=new ArrayList<>();
    void graphToTree(Graph gr,Treenode lroot, int dest)//O(n^2)
    {
    	if(lroot==null)
    	{
    		return;
    	}
    	if(lroot.city==dest)return;
    	if (ancestors!=null)
    	{
    		ancestors.clear();
    	}
    	findAncestor(lroot);
    	for(int i=1;i<gr.adj[lroot.city].size();i++)
    	{
    		if(lroot!=src)
    		{
    			if(ancestors.containsAll(gr.adj[lroot.city])) {
					return;
				}
    			if(ancestors.contains(gr.adj[lroot.city].get(i).city))
    			{
    				continue;
    			}
    			else
    			{
    				if(ancestors.contains(dest))
    				{
    					return;
    				}
    			}
    		}
    		if(lroot.left==null)
    		{
    			lroot.left=new Treenode(gr.adj[lroot.city].get(i).city);
    		}
   			else if(lroot.mid==null)
   			{
   				lroot.mid=new Treenode(gr.adj[lroot.city].get(i).city);
   			}
    		else if(lroot.right==null)
    		{
   				lroot.right=new Treenode(gr.adj[lroot.city].get(i).city);
   			}
    	}
    	graphToTree(gr, lroot.left, dest);
    	graphToTree(gr, lroot.mid, dest);
    	graphToTree(gr, lroot.right, dest);
    }
    Treenode findParent(Treenode lroot, Treenode kid)//O(n)
    {
    	if(lroot==null)
    	{
    		return null;
    	}
    	else if(lroot.left!=null && lroot.left.equals(kid))
    	{
    		return lroot;
    	}
    	else if(lroot.right!=null && lroot.right.equals(kid))
    	{
    		return lroot;
    	}
    	else if(lroot.mid!=null && lroot.mid.equals(kid))
    	{
    		return lroot;
    	}
    	else
    	{
    		Treenode l=findParent(lroot.left, kid);
    		if(l!=null)
    		{
    			return l;
    		}
    		Treenode m=findParent(lroot.mid, kid);
    		if(m!=null)
    		{
    			return m;
    		}
    		Treenode r=findParent(lroot.right, kid);
    		if(r!=null)
    		{
    			return r;
    		}
    		else
    		{
    			return null;
    		}
    	}
    }
    
    void findAncestor(Treenode lroot)//O(n^2)
    {
    	Treenode current;
    	current = lroot;
    	while(current.city!=src.city)
    	{
    		current=findParent(src,current);
    		if (current!=null)
    		{
    			ancestors.add(current.city);
    		}
    	}
    }
    
    
    void Search(int dest, Treenode lroot)//O(n)
    {
    	if(lroot==null)
    	{
    		return;
    	}
    	if(lroot.city==dest)
    	{
    		destination.add(lroot);
    		return;
    	}
    	else
    	{
    		Search(dest, lroot.left);
    		Search(dest, lroot.mid);
    		Search(dest, lroot.right);
    	}
    }
    
    void ShowConnectedPath(int dest, Mapp m)//O(n^2)
    {
    	Search(dest, src);
    	System.out.println("Available Routes are: ");
    	for(Treenode d:destination)
    	{
    		ancestors.clear();
    		findAncestor(d);
    		System.out.println();
    		for(int i=(ancestors.size()-1); i>=0; i--)
    		{
    			System.out.print(m.getCity(ancestors.get(i))+"--->");
    		}
    		System.out.print(m.getCity(dest)+"\n");
    	}
    }
}
