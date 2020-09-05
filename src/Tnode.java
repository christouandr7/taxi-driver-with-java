import java.util.ArrayList;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;


public class Tnode {
        double Rdist; //real distan=euclid from the star-taxi point
        double Hdist;//evristiki apostasi=euclid from the client
        double SumDist;//sum of above
        double Xtelos;
        double Ytelos;
        Simia s=new Simia();
        double X;//current X,Y
        double Y;
        void putXY(Simia a){
                s = a;
            this.X=s.getX();
            this.Y=s.getY();
        }
        Simia getLastPath(){
           return path.get(path.size()-1);
        }

        ArrayList<Simia> path= new ArrayList<Simia>();
        void updatePath(ArrayList<Simia> E){
           
            for(Simia aa:E)
             this.path.add(aa);
            this.path.add(this.s);


        }
        ArrayList<Simia> getPath(){
            return this.path;
        }

        boolean isFinal(){
            if(this.s.getX()==Xtelos && this.s.getY()==Ytelos)
                return true;
           else
               return false;
        }

        void ClientXY(double X1,double Y1){ //client coordinates
            this.Xtelos=X1;
            this.Ytelos=Y1;
        }
        double euclid(){
        return sqrt(pow(this.X-this.Xtelos,2)+pow(this.Y-this.Ytelos,2));
    }
        Simia getPoint(){
            return this.s;
        }

        void updateReal(double x){
            this.Rdist =x;
            this.Hdist=euclid();
            this.SumDist=this.Hdist+this.Rdist;
            this.s.setReal(x);

        }

        double getHdist(){
            return Hdist;
        }
        double getRdist(){
            return Rdist;
        }
        double getSumDist(){
            return SumDist;
        }

}
