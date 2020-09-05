import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Taxi{
	double X;
	double Y;	
	int id;
	double trueX;
	double trueY;
	Simia t = new Simia();

	void taxi(double a,double b,int e){
	this.X=a;
	this.Y=b;
	this.id=e;
	}
	void changeXY(double a,double b){
	this.trueX=a;
	this.trueY=b;
	}

	Simia retPoint(){
		t.X = X;
		t.Y = Y;
		return t;
	}
	
	double getX(){
		return this.X;
	}
	double getY(){
		return this.Y;
	}

	double getrueX(){
		return this.trueX;
	}

	double getrueY(){
		return this.trueY;
	}
	
	int getID(){
		return this.id;
	}

	double euclid(double x1,double y1){
		return sqrt(pow(x1-X,2)+pow(y1-Y,2));
	}
}
