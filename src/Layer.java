import java.util.*;

public class Layer {
	
	private ArrayList<Node> nodes;

	//If only passing #, assume that it must be input nodes because there's no
	//previous layer
	public Layer(int numNodes)
	{
		for(int i=0; i<numNodes; i++)
			nodes.add(new InputNode());
		nodes.add(new BiasNode());
	}

	//assumes making a hidden layer
	public Layer(int numNodes, Layer lastLayer)
	{
		for(int i=0; i<numNodes; i++)
			nodes.add(new Node(lastLayer.getList()));
		nodes.add(new BiasNode());
	}
	
	//assumes this is an output layer
	public Layer(int numNodes, Layer lastLayer, boolean outLayer)
	{
		for(int i=0; i<numNodes; i++)
			nodes.add(new OutputNode(lastLayer.getList()));
	}
	
	public int getSize()
	{
		return nodes.size();
	}
	
	public ArrayList<Node> getList()
	{
		return nodes;
	}
	
	public Node getNode(int nodeNum)
	{
		return nodes.get(nodeNum);
	}
}
