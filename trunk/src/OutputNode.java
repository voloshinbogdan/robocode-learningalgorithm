import java.util.*;

public class OutputNode extends Node {
	
	public OutputNode(ArrayList<Node> inputNodes)
	{
		super(inputNodes);
	}
	
	public OutputNode(ArrayList<Node> inputNodes, double[] inputWeights)
	{
		super(inputNodes, inputWeights);
	}
	
	public double getOutput()
	{
		if(alreadyCalled)
		{
			return value;
		}
		alreadyCalled = true;
		double weightedSum = 0.0;
		for(int i=0; i<inputNodes.size(); i++)
			weightedSum += inputWeights[i]*inputNodes.get(i).getOutput();
		value = weightedSum;
		return value;
	}

}
