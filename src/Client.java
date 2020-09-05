import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Client{
	double X;
	double Y;
	double trueX;
	double trueY;
	
	void client(double a,double b){
		this.X=a;
		this.Y=b;
	}

	void changeXY(double a,double b){
		this.trueX=a;
		this.trueY=b;
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

	double euclid(double x1,double y1){
	return sqrt(pow(x1-X,2)+pow(y1-Y,2));
	}
}
