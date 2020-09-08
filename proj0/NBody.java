public class NBody
{
	public static double readRadius(String fileName)
	{
		In in = new In(fileName); 
		int numberOfPlanets = in.readInt(); 
		double radius = in.readDouble(); 
		return radius; 
	}
}