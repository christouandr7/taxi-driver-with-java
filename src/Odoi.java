import java.util.*;public class Odoi{	int id;	ArrayList<Simia> points= new ArrayList<Simia>();     void addId(int a){        this.id = a;    }	int getID(){		return this.id;	}	void odoi(int j){		this.id=j;	}	void addpoint(Simia point){        points.add(point);    }	ArrayList<Simia> getListSimia(){		return this.points;	}}