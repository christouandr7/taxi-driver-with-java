import java.util.*;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;


public class Simia{
	double X;
	double Y;
	ArrayList<Integer> odoiL= new ArrayList<Integer>();
	boolean flag = false;
	double Rdist;
	void initRd(){
		this.Rdist=0;
	}

	boolean getFlag(){
		return flag;
	}

	void visit(){
		this.flag = true;
	}

	void simia(double a,double b) {
		this.X=a;
		this.Y=b;
	}
	double getX(){
		return this.X;
	}

	double getY(){
		return this.Y;
	}
	void initFlag(){
		this.flag=false;
	}

	void insertOdoi(Integer j){
		this.odoiL.add(j);
	}
	
	ArrayList<Integer> getOdoiList(){
		return this.odoiL;
	}


	double getReal(){
		return Rdist;
	}
	void setReal(double x){
		this.Rdist=x;
	}
	
	double euclid(Simia j){
		double x1,y1;
		x1 = j.getX();
		y1 = j.getY();
		return sqrt(pow(x1-X,2)+pow(y1-Y,2));
	}

 }
