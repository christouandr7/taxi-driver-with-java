import java.util.Comparator;

public class StepsComparator implements  Comparator<Tnode> {

    @Override
    public int compare(Tnode A, Tnode B) {
        if (A.getSumDist() > B.getSumDist() )			//ilopii ti sigrisi gia to priority queue oste to proto stixio
            return 1;								// na einai to mikrotero se sinolo apostasis
        if (A.getSumDist() == B.getSumDist() )
            return 0;								//xrisimopiithike kodikas apo https://stackoverflow.com/questions/683041/how-do-i-use-a-priorityqueue
        return -1;
    }

}