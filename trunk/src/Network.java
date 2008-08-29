import java.util.*;

public class Network {
	
	private Layer[] networkLayers;
	
	public Network(int numLayers, int[] numNodes)
	{
		networkLayers = new Layer[numLayers];
		//first layer
		networkLayers[0] = new Layer(numNodes[0]);
		//hidden layers
		for(int i=1; i<numLayers-1; i++)
			networkLayers[i] = new Layer(numNodes[i],networkLayers[i-1]);
		//output layer
		networkLayers[numLayers-1] = new Layer(numNodes[numLayers-1],networkLayers[numLayers-2],true);
	}
	
	public int getDimensionIn()
	{
		//-1 to account for bias node
		return networkLayers[0].getSize()-1;
	}
	
	public int getDimensionOut()
	{
		//no bias node in output layer
		return networkLayers[networkLayers.length-1].getSize();
	}
	
	public double[] getOutput(double[] input)
	{
		double[] output = new double[getDimensionOut()];
		setInput(input);
		for(int i=0; i<output.length; i++)
			output[i] = networkLayers[networkLayers.length-1].getNode(i).getOutput();
		resetNet();
		return output;
	}
	
	private void setInput(double[] input)
	{
		for(int i=0; i<input.length; i++)
			networkLayers[0].getNode(i).setValue(input[i]);
	}
	
	private void resetNet()
	{
		for(int i=0; i<networkLayers.length; i++)
			for(int j=0; i<networkLayers[i].getSize(); j++)
				networkLayers[i].getNode(j).reset();
	}
}