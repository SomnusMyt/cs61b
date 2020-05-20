public class NBody {
	public static double readRadius(String s){
		In in = new In(s);
		int num = in.readInt();
		double radius = in.readDouble();
		return radius;
	}
	public static Planet[] readPlanets(String s){
		In in = new In(s);
		int num = in.readInt();
		double radius = in.readDouble();
		Planet[] bodies = new Planet[num];
		for(int i=0;i<num;i++){
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFileName = in.readString();
			//imgFileName = "./images/" + imgFileName;
			Planet b = new Planet(xxPos,yyPos,xxVel,yyVel,mass,imgFileName);			
			bodies[i] = b;
		}
		return bodies;
	}

	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		//String filename = "./data/planets.txt";
		double radius = readRadius(filename);

		String imageToDraw = "./images/starfield.jpg";
		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-radius,radius);
		StdDraw.clear();
		StdDraw.picture(0, 0, imageToDraw);
		Planet[] bodies = readPlanets(filename);
		for(int i=0;i<bodies.length;i++)
			bodies[i].draw();
		StdDraw.show();
		//StdDraw.pause(2000);

		double[] xForces = new double[bodies.length];
		double[] yForces = new double[bodies.length];

		int time = 0;
		while(time < T){
			for(int i=0;i<bodies.length;i++){
				xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
				yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
			}
			for(int i=0;i<bodies.length;i++){
				bodies[i].update(dt,xForces[i],yForces[i]);
			}
			StdDraw.clear();
			StdDraw.picture(0, 0, imageToDraw);
			for(int i=0;i<bodies.length;i++)
				bodies[i].draw();
			StdDraw.show();
			StdDraw.pause(10);
			time+= dt;
		}
		StdOut.printf("%d\n", bodies.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < bodies.length; i++) {
		    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		                  bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
		                  bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);   
		}
		

	}
}