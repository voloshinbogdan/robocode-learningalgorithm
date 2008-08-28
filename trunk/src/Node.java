import java.util.*;

public class Node {
	
	private ArrayList<Node> inputNodes;
	private ArrayList<Node> outputNodes;
	private double[] inputWeights;
	private double[] outputWeights;
	
	//used to minimize network calls when getting network output
	private boolean alreadyCalled;
	private double value;
	
	//choices for activation function
	private int sigmoid = 1;
	private int tanh = 2;
	
	//what our choice is
	private int choice = sigmoid;

	//Creates a new node
	public Node()
	{
		inputNodes = new ArrayList<Node>(0);
		outputNodes = new ArrayList<Node>(0);
		alreadyCalled = false;
	}
	
	//Constructor that knows the inputNodes and outputNodes
	public Node(ArrayList<Node> inNodes, ArrayList<Node> outNodes)
	{
		this();
	    //point the lists in the right direction
		for(int i=0; i<inNodes.size(); i++)
			inputNodes.add(inNodes.get(i));
		for(int i=0; i<outNodes.size(); i++)
			outputNodes.add(outNodes.get(i));
		
		//Need a function here to assign random weights, I say we draw from a gaussian at 0
		//With deviation 1 to allow a weight to be positive or negative
	}
	
	public Node(ArrayList<Node> inNodes, double[] inWeights, 
			ArrayList<Node> outNodes, double[] outWeights)
	{
		this(inNodes, outNodes);
		inputWeights = new double[inNodes.size()];
		outputWeights = new double[outNodes.size()];
		//assign the proper weights
		for(int i=0; i<inNodes.size(); i++)
			inputWeights[i] = inWeights[i];
		for(int i=0; i<outNodes.size(); i++)
			outputWeights[i] = outWeights[i];
	}
	
	
	//note that both inputNode and biasNode will inherit and overwrite getOutput, otherwise
	//we'll have some arrayIndexOutOfBounds exception or something
	public double getOutput()
	{
		if(alreadyCalled)
			return value;
		value=0;
		double weightedSum = 0.0;
		alreadyCalled = true;
		//do weighted sum of previous layers output
		for(int i=0; i<inputNodes.size(); i++)
		{
			weightedSum += inputWeights[i]*inputNodes.get(i).getOutput();
		}
		value = activationFunction(weightedSum);
		return value;
	}

	private double activationFunction(double x)
	{
		if(choice==sigmoid)
		{
			return 1.0/(1.0+Math.exp(x));
		}
		if(choice==tanh)
		{
			return Math.tanh(x);
		}
		
		//failure mode
		return -1;
	}
	
	
	
}
