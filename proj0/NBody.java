public class NBody {
	public static void main(String[] args) {
        double T, dt;
        String filename;
        T = Double.parseDouble(args[0]);
        dt = Double.parseDouble(args[1]);
        filename = args[2];
        Planet[] planets = readPlanets(filename);
        double radius = readRadius(filename);
        StdDraw.setScale(-radius,radius);
        StdDraw.picture(0,0,"./images/starfield.jpg");
        for (int i = 0; i < planets.length; i++)
        {
        	planets[i].draw();
        }
        
        double time = 0;
        int planetsNum = planets.length;

        double a[] =  StdAudio.read("./audio/2001.mid");
        StdAudio.play(a);

        while (time < T)
        {
        	double[] xForces = new double[planetsNum];
        	double[] yForces = new double[planetsNum];
        	for (int i = 0; i < planetsNum; i++)
        	{
        		xForces[i] = planets[i].calcNetForceExertedByX(planets);
        		yForces[i] = planets[i].calcNetForceExertedByY(planets);	
        	}
        	for (int i = 0; i < planetsNum; i++)
        	{
        		planets[i].update(dt, xForces[i], yForces[i]);
        		planets[i].draw();
        		StdDraw.show(10);
        		// StdDraw.pause(10);
        	}
        	time += dt;

        }

		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
   				planets[i].xxPos, planets[i].yyPos, planets[i].xxVel, planets[i].yyVel, planets[i].mass, planets[i].imgFileName);	
		}

        
	}
	public static double readRadius(String s)
	{
		In in = new In(s);
		String line = in.readLine();
        line = in.readLine();
		double radius = Double.parseDouble(line);
		return radius;
	}

	public static Planet[] readPlanets(String s)
	{
		In in = new In(s);
		int num = in.readInt();
        double radius = in.readDouble();
        Planet[] planets = new Planet[num];
        String member, imgFileName;
        double xxPos, yyPos, xxVel, yyVel, mass;
        for (int i = 0; i< num; i++)
        {
    		member = in.readString();
    		xxPos = Double.parseDouble(member);
    		member = in.readString();
    		yyPos = Double.parseDouble(member);
    		member = in.readString();
    		xxVel = Double.parseDouble(member);
    		member = in.readString();
    		yyVel = Double.parseDouble(member);
    		member = in.readString();
    		mass = Double.parseDouble(member);
    		imgFileName = in.readString();
        	planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
        }
        return planets;
	}
}

