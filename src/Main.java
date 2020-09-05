import java.lang.reflect.Array;
import java.util.*;
import java.io.*;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import java.util.Comparator;
import java.util.PriorityQueue;


public class Main {
	public static void main(String[] args) {
		Client pelatis = new Client();
		String csvFile1 = "client.csv";
		BufferedReader br1 = null;
		String line1 = "";
		String csvSplitBy1 = ",";
		String[] triada1;
		double tdxc, tdyc;

		System.out.println("Give BeamSearch size: ");
		Scanner scan = new Scanner(System.in);
		int counter = scan.nextInt();
		System.out.println("Ok, wait for results... ");

		try {
			br1 = new BufferedReader(new FileReader(csvFile1));
			line1 = br1.readLine();
			triada1 = line1.split(csvSplitBy1);
			while ((line1 = br1.readLine()) != null) {
				triada1 = line1.split(csvSplitBy1);
				tdxc = Double.parseDouble(triada1[0]);
				tdyc = Double.parseDouble(triada1[1]);
				pelatis = new Client();
				pelatis.client(tdxc, tdyc);
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br1 != null) {
				try {
					br1.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		double minCli;
		minCli = 100000.0;
		double minCX, minCY;
		minCX = 0.0;
		minCY = 0.0;

		// create the <taxi> List "Taxi" */
		ArrayList<Taxi> ListTaxi = new ArrayList<Taxi>();
		// Reading of  taxi file starts and it fills <taxi> List "Taxi"
		String csvFile2 = "taxis.csv";
		BufferedReader br2 = null;
		String line2 = "";
		String csvSplitBy2 = ",";
		String[] triada2;
		double tdx, tdy;
		int tid;
		Taxi t_temp;
		try {
			br2 = new BufferedReader(new FileReader(csvFile2));
			line2 = br2.readLine();
			triada2 = line2.split(csvSplitBy2);
			while ((line2 = br2.readLine()) != null) {
				triada2 = line2.split(csvSplitBy2);
				tdx = Double.parseDouble(triada2[0]);
				tdy = Double.parseDouble(triada2[1]);
				tid = Integer.parseInt(triada2[2]);
				t_temp = new Taxi();
				t_temp.taxi(tdx, tdy, tid);
				ListTaxi.add(t_temp);
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br2 != null) {
				try {
					br2.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}


		int j = 0;
		int N;
		double b;
		N = ListTaxi.size();
		double[][] minTax = new double[N][3];
		int i;
		for (i = 0; i < N; i++) {
			minTax[i][0] = 100000.0;
			minTax[i][1] = 3.0;
			minTax[i][2] = 4.0;
		}


		String csvFile = "nodes.csv";
		BufferedReader br = null;
		String line = "";
		String csvSplitBy = ",";
		String[] triada;
		int i3, lastID = -1;
		double d1, d2;
		ArrayList<Simia> points = new ArrayList<Simia>();
		ArrayList<Odoi> streets = new ArrayList<Odoi>();
		boolean insertedP = false;
		boolean insertedO = false;

		double pi = 0.0;
		try {
			br = new BufferedReader(new FileReader(csvFile));
			line = br.readLine();
			triada = line.split(csvSplitBy);
			while ((line = br.readLine()) != null) {
				triada = line.split(csvSplitBy);
				d1 = Double.parseDouble(triada[0]);
				d2 = Double.parseDouble(triada[1]);
				i3 = Integer.parseInt(triada[2]);
				Simia point = new Simia();
				Simia bm = new Simia();

				if (points.size() > 0) {
					for (Simia a : points) {
						if ((d1 == a.getX()) && (d2 == a.getY())) {
							a.insertOdoi(i3);
							bm = a;
							insertedP = true;
							break;
						}
					}
				}

				Simia c = new Simia();
				if (insertedP == false) {
					point.simia(d1, d2);
					point.insertOdoi(i3);
					points.add(point);
					c = point;
				}

				Odoi str = new Odoi();
				str.addId(i3);
				if (streets.size() > 0) {
					if (lastID != i3) {
						if (insertedP == true) {
							str.addpoint(bm);
						} else {

							str.addpoint(c);
						}

						streets.add(str);
						insertedO = true;
					}
				} else {
					str.addpoint(bm);
					streets.add(str);
				}

				if (insertedO == false) {
					if (insertedP == true) {
						streets.get(streets.size() - 1).addpoint(bm);
						

					} else {
						streets.get(streets.size() - 1).addpoint(c);
						

					}
				}

				insertedP = false;
				insertedO = false;
				pi = pelatis.euclid(d1, d2);
				if (pi < minCli) {
					minCli = pi;
					minCX = d1;
					minCY = d2;
					
				}
				i = 0;
				
				for (Taxi a : ListTaxi) {
					b = a.euclid(d1, d2);
					if (b < minTax[i][0]) {
						minTax[i][0] = b;
						minTax[i][1] = d1;
						minTax[i][2] = d2;
						
					}
					i++;
				}
				lastID = i3;
			}
			//End of while loop

			i = 0;
			for (Taxi a : ListTaxi) {
				a.changeXY(minTax[i][1], minTax[i][2]);
				i = i + 1;
			}

			pelatis.changeXY(minCX, minCY);

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}


		//End of reading files and insert them in ArrayLists
		//Algorithm A* with Beam Search
		int metritisQ = 0;	//how many times algorithm runs
		int ef=0;			//loops for all taxis
		int maxQsize = 0;	//max size of PQ
		double minSumDist = 10000;
		int taxiMinDist = 0;	//taxi with minimum distance from client
		double[] arDist = new double[ListTaxi.size()];
		for (i=0;i<ListTaxi.size();i++)
			arDist[i]=0;
		//for all taxis available
		for(ef=0;ef<ListTaxi.size();ef++){
			Simia curPoint;
			Comparator<Tnode> comparator = new StepsComparator();
			PriorityQueue<Tnode> Q = new PriorityQueue<Tnode>(1, comparator);
			Tnode initial = new Tnode();
			Simia p ;
			Taxi pp ;
			pp = ListTaxi.get(ef);
			p = pp.retPoint();
			initial.putXY(p);
			initial.ClientXY(pelatis.getrueX(), pelatis.getrueY());
			initial.updateReal(0);
			ArrayList<Simia> emp = new ArrayList<Simia>();
			initial.updatePath(emp);
			Q.add(initial);
			curPoint = null;
			for (Simia a : points) {
				if (a.getX() == minTax[ef][1] && a.getY() == minTax[ef][2]) {
					curPoint = a;
					break;
				}
			}

			Simia simfordist = new Simia();
			int k;
			j=0;
			i = 0;
			while (!Q.isEmpty()) {
				i++;
				Tnode cur;
				cur = Q.poll();
				metritisQ++;
				cur.s.visit();
				if(i!=1) curPoint=cur.s;
				k=0;
				if (cur.isFinal()) {
					
					if (cur.getSumDist() < minSumDist) {
						minSumDist = cur.getSumDist();
						taxiMinDist = ef;
					}
					Haversine hav = new Haversine();
					System.out.println("Route for Taxi: "+ef);
					for (Simia sss : cur.path) {
						if (k!=0) {
							arDist[ef] += hav.haversine(simfordist.getX(),simfordist.getY(),sss.getX(),sss.getY());
						}
						simfordist = sss;
						System.out.println(sss.getX() + "," + sss.getY());
						k++;
					}
					System.out.println("Distance Travelled: "+arDist[ef]);
					break;
				}
				if (curPoint.getOdoiList() == null){
					System.out.println("Den pame kala " + i);}

				for (int d : (curPoint.getOdoiList())) {
					for (Odoi de : streets) {
						if (d == de.getID()) {
						
							for (Simia si : de.getListSimia()) {
								if (si == curPoint) continue;
								if (!si.getFlag() |  (si.getFlag() && si.getReal()>cur.s.getReal()+si.euclid(curPoint))) {
									Tnode state = new Tnode();
									j++;
									state.putXY(si);
									state.ClientXY(pelatis.getrueX(), pelatis.getrueY());
									state.updateReal(cur.Rdist + si.euclid(curPoint));
									state.updatePath(cur.path);
									state.s.visit();
									Q.add(state);
								}
							}
						}
					}

				}
				if (Q.isEmpty()) System.out.println("Algorithm cannot find result for taxi "+ef);
				if (Q.size()>maxQsize){
					maxQsize = Q.size();
				}

				//For BeamSearch with w = counter
				PriorityQueue<Tnode> PQtrimmed = new PriorityQueue<Tnode>(1, comparator);
				if (Q.size() >= counter) {
					for (i = 0; i < counter; i++) {
						PQtrimmed.add(Q.poll());
					}
					Q = PQtrimmed;
				}

			}

			//Clear PQ for next taxi
			Q.clear();
			//initialize all points with visited = false and initial real distance = 0
			for(Simia sin:points){
				sin.initFlag();
				sin.initRd();
			}
		}
		//Loop for all taxis ended

		System.out.println("Taxi with minDist: " + taxiMinDist);
		System.out.println("Max size of Priority Queue: " + maxQsize);
		System.out.println("Algorithm runs " + metritisQ + " times");


	}
}



	
