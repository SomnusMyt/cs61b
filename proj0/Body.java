public class Body {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public static final double G = 6.67e-11;

	public Body(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	public Body(Body b){
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}
	public double calcDistance(Body b){
		double r = Math.sqrt( (b.xxPos - this.xxPos) * (b.xxPos - this.xxPos) +(b.yyPos - this.yyPos) * (b.yyPos - this.yyPos));
		return r; 
	}
	public double calcForceExertedBy(Body b){
		double distance = this.calcDistance(b);
		double f = (G * this.mass * b.mass) / Math.pow(distance,2);
		return f;
	}
	public double calcForceExertedByX(Body b){
		double fx = this.calcForceExertedBy(b) * (b.xxPos - this.xxPos) / this.calcDistance(b);
		return fx;
	}
	public double calcForceExertedByY(Body b){
		double fy = this.calcForceExertedBy(b) * (b.yyPos - this.yyPos) / this.calcDistance(b);
		return fy;
	}
	public double calcNetForceExertedByX(Body[] b){
		double sumfx = 0;
		for(int i=0;i<b.length;i++){
			if( !this.equals(b[i]) )
				sumfx += this.calcForceExertedByX(b[i]);
		}
		return sumfx;

	}
	public double calcNetForceExertedByY(Body[] b){
		double sumfy = 0;
		for(int i=0;i<b.length;i++){
			if( !this.equals(b[i]) )
				sumfy += this.calcForceExertedByY(b[i]);
		}
		return sumfy;

	}

	public void update(double dt, double fx, double fy){
		double ax = fx / mass ;
		double ay = fy / mass ;
		xxVel += dt * ax ;
		yyVel += dt * ay ;
		xxPos += dt * xxVel;
		yyPos += dt * yyVel;
	}
	public void draw(){
		StdDraw.picture(xxPos,yyPos,imgFileName);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
}
