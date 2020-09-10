import java.lang.Math; 
import java.time.Duration; 

public class Body
{
	public double xxPos;    // current x position
	public double yyPos;    // current y position 
	public double xxVel;    // current velocity in the x direction 
	public double yyVel;    // current velocity in the y direction 
	public double mass; 
	public String imgFileName; 
	public static final double gravConstant = 6.67E-11; 

	public Body(double xP, double yP, double xV, double yV, double m, String img)
	{
		xxPos = xP; 
		yyPos = yP; 
		xxVel = xV; 
		yyVel = yV; 
		mass = m; 
		imgFileName = img; 
	}

	public Body(Body b)
	{
		xxPos = b.xxPos; 
		yyPos = b.yyPos; 
		xxVel = b.xxVel; 
		yyVel = b.yyVel; 
		mass = b.mass; 
		imgFileName = b.imgFileName; 
	}

	public double calcDistance(Body b)
	{
		double distSquare = (b.xxPos - this.xxPos) * (b.xxPos - this.xxPos) + (b.yyPos - this.yyPos) * (b.yyPos - this.yyPos); 
		return Math.sqrt(distSquare); 
	}

	public double calcForceExertedBy(Body b)
	{
		double r = calcDistance(b); 
		double force = gravConstant * b.mass * this.mass / (r * r); 
		return force; 
	}

	public double calcForceExertedByX(Body b)
	{
		double r = calcDistance(b); 
		double force = calcForceExertedBy(b); 
		double dx = b.xxPos - this.xxPos; 
		double fx = force * dx / r; 
		return fx; 
	}

	public double calcForceExertedByY(Body b)
	{
		double r = calcDistance(b); 
		double force = calcForceExertedBy(b); 
		double dy = b.yyPos - this.yyPos; 
		double fy = force * dy / r; 
		return fy; 
	}

	public double calcNetForceExertedByX(Body[] a)
	{
		double netForceByX = 0; 
		for(int i = 0; i < a.length; i++)
		{
			if(a[i].equals(this))
				continue; 
			netForceByX = netForceByX + calcForceExertedByX(a[i]); 
		}
		return netForceByX; 
	}

	public double calcNetForceExertedByY(Body[] a)
	{
		double netForceByY = 0; 
		for(int i = 0; i < a.length; i++)
		{
			if(a[i].equals(this))
				continue; 
			netForceByY += calcForceExertedByY(a[i]); 
		}
		return netForceByY; 
	}

	public void update(double dt, double forceX, double forceY)
	{
		double ax = forceX / this.mass; 
		double ay = forceY / this.mass; 
		xxVel += (dt * ax); 
		yyVel += (dt * ay); 
		xxPos += (dt * xxVel); 
		yyPos += (dt * yyVel); 
	}

	public void draw()
	{
		String imageToDraw = "images/" + imgFileName; 
		StdDraw.picture(xxPos, yyPos, imageToDraw); 
		StdDraw.show(); 
		// StdDraw.pause(2000); 
	}

}