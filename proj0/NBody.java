public class NBody
{
	public static In in; 
	public static int numberOfPlanets; 
	public static double radius; 
	public static Body[] bodies; 

	public static double readRadius(String fileName)
	{
		in = new In(fileName); 
		numberOfPlanets = in.readInt(); 
		radius = in.readDouble(); 
		return radius; 
	}

	public static Body[] readBodies(String fileName)
	{
		in = new In(fileName); 
		numberOfPlanets = in.readInt(); 
		radius = in.readDouble(); 
		bodies = new Body[numberOfPlanets]; 
		double xxPos, yyPos, xxVel, yyVel, mass; 
		String imgFileName; 
		for(int i = 0; i < numberOfPlanets; i++)
		{
			xxPos = in.readDouble(); 
			yyPos = in.readDouble(); 
			xxVel = in.readDouble(); 
			yyVel = in.readDouble(); 
			mass = in.readDouble(); 
			imgFileName = in.readString(); 

			bodies[i] = new Body(xxPos, yyPos, xxVel, yyVel, mass, imgFileName); 
		}

		return bodies; 
	}

	public static void main(String[] args)
	{
		double T, dt;
		T = Double.parseDouble(args[0]); 
		dt = Double.parseDouble(args[1]); 

		String fileName = args[2]; 
		radius = readRadius(fileName); 
		bodies = readBodies(fileName); 

		// drawing the background 
		
		StdDraw.enableDoubleBuffering(); 
		double initialTime = 0; 
		for(; initialTime <= T; initialTime += dt)
		{
			double[] xForces = new double[numberOfPlanets]; 
			double[] yForces = new double[numberOfPlanets]; 
			for(int i = 0; i < numberOfPlanets; i++)
			{
				xForces[i] = bodies[i].calcNetForceExertedByX(bodies); 
				yForces[i] = bodies[i].calcNetForceExertedByY(bodies); 
			}

			for(int j = 0; j < numberOfPlanets; j++)
				bodies[j].update(dt, xForces[j], yForces[j]); 

			String imageToDraw = "images/starfield.jpg"; 
			StdDraw.enableDoubleBuffering();
			StdDraw.setScale(-radius, radius); 
			StdDraw.clear();
			StdDraw.picture(0, 0, imageToDraw); 
			StdDraw.show(); 

			for(int i = 0; i < numberOfPlanets; i++)
				bodies[i].draw(); 

			StdDraw.show(); 
			StdDraw.pause(10); 
		} 

		// print the universe
		StdOut.printf("%d\n", bodies.length); 
		StdOut.printf("%.2e\n", radius); 
		for(int i = 0; i < bodies.length; i++)
		{
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", 
				bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel, 
				bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName); 
		}

	}
}