public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Planet(double xP, double yP, double xV, double yV, double m, String img)
	{
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;

	}

	public Planet(Planet p)
	{
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass  = p.mass;
		this.imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p)
	{
		double dx = this.xxPos - p.xxPos;
		double dy = this.yyPos - p.yyPos;
		double r  = dx * dx + dy * dy;
		return Math.sqrt(r);
	}

	public double calcForceExertedBy(Planet p)
	{
		double G = 6.67e-11;
		double r = calcDistance(p);
		double F =  G * this.mass * p.mass / (r * r);
		return F;
	}

	public double calcForceExertedByX(Planet p)
	{
		double F = calcForceExertedBy(p);
		double r = calcDistance(p);
		double dx = p.xxPos - this.xxPos;
		double F_x = F * dx / r ;
		return F_x;
	}

	public double calcForceExertedByY(Planet p)
	{
		double F = calcForceExertedBy(p);
		double r = calcDistance(p);
		double dy = p.yyPos - this.yyPos;
		double F_y = F * dy / r ;
		return F_y;
	}

	public double calcNetForceExertedByX(Planet[] planets)
	{
		int n = planets.length;
		double sumForce = 0;
		for (int i = 0; i< n ; i++)
		{
			if(this.equals(planets[i])) 
				continue;
			sumForce += calcForceExertedByX(planets[i]);
		}
		return sumForce;
	}

	public double calcNetForceExertedByY(Planet[] planets)
	{
		int n = planets.length;
		double sumForce = 0;
		for (int i = 0; i< n ; i++)
		{
			if(this.equals(planets[i])) 
				continue;
			sumForce += calcForceExertedByY(planets[i]);
		}
		return sumForce;
	}

	public void update(double dt, double fX, double fY)
	{
		double a_x = fX / mass;
		double a_y = fY / mass;

		xxVel = xxVel + dt * a_x;
		yyVel = yyVel + dt * a_y;

		xxPos = xxPos + dt * xxVel;
		yyPos = yyPos + dt * yyVel;
	}

	public void draw()
	{
		StdDraw.picture(xxPos, yyPos, "./images/".concat(imgFileName));
	}
}
