
public class InputNode extends Node {

	//input node has to know absolutely nothing about where it is
	public InputNode()
	{
		value = 0;
	}
	
	public double getOutput()
	{
		return value;
	}
	
}
