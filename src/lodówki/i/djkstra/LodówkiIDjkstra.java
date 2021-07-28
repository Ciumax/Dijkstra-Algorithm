package lodówki.i.djkstra;

import java.util.ArrayList;
import java.util.List;

import java.awt.Point;
import static java.awt.geom.Point2D.distance;
import static java.lang.Math.abs;

public class LodówkiIDjkstra {

    public static void main(String[] args) {
        Search X = new Search();
        X.jadymy();

    }

}

class Search {

    List<Sklep> Q;
    List<Sklep> W;
    int zal;
    int odl;

    Search() {
        Q = new ArrayList<>();
        W = new ArrayList<>();
        int zal = 10;
        int odl = 0;
    }

    void jadymy() {
        stworz();

        W = Q;

        Sklep powrot = wybierz(W.get(0));

        while (true) {
            wybierz(powrot);
            if (Rozwiazanie()) {
                break;
            }
        }

        for (int i = 0; i < W.size(); i++) {
            System.out.println("Sklep " + W.get(i).nr + " " + W.get(i).imdone + " " + W.get(i).zamowione + " " + W.get(i).pkt);

        }
        System.out.println(odl);
    }

    // double x=odleglosc(Coord,Coord2);
    //   System.out.println(x);
    static double odleglosc(Point a, Point b) {
        return distance(a.x, a.y, b.x, b.y);
    }

    Sklep wybierz(Sklep jest) {
        double[] x = new double[W.size()];
        double c = 50;
        Point Z = new Point();
        Sklep test = new Sklep(0, false, 0, Z);
        System.out.println("Start:" + jest.pkt);

        for (int i = 0; i < W.size(); i++) {

            if (zal == 0) {
                test = W.get(0);
                zal = 10;
                System.out.println("Skończyły sie lodówki wracam");
                c = odleglosc(jest.pkt, W.get(0).pkt);
                break;
            }

            if (zal == W.get(i).zamowione) {
                test = W.get(i);
                c = odleglosc(jest.pkt, W.get(i).pkt);
                break;
            }
            x[i] = odleglosc(jest.pkt, W.get(i).pkt);
            System.out.println(x[i]);

            if (x[i] != 0 && x[i] < c && W.get(i).imdone == false) {
                c = x[i];

            }
            if (c == x[i]) {
                test = W.get(i);

            }

        }

        for (int n = 0; n < W.size(); n++) {
            if (test.pkt == W.get(n).pkt) {
                zal -= W.get(n).zamowione;
                W.get(n).zamowione = 0;
                if (zal <= 0) {
                    W.get(n).zamowione = abs(zal);
                    zal = 0;
                }
                if (W.get(n).zamowione == 0) {
                    W.get(n).imdone = true;
                }

            }
        }
        odl += c;
        System.out.println("Wybieram to: " + c);
        System.out.println("teraz tu jestem: " + test.nr);
        System.out.println("Tyle mi zostało: " + zal);
        //if()
        return test;
    }

    boolean Rozwiazanie() {

        return W.get(1).imdone == true && W.get(2).imdone == true && W.get(3).imdone == true && W.get(4).imdone == true && W.get(5).imdone == true;

    }

    void stworz() {
        Point Coord = new Point(-3, 3);
        Point Coord2 = new Point(0, 3);
        Point Coord3 = new Point(-3, -1);
        Point Coord4 = new Point(3, 1);
        Point Coord5 = new Point(-1, 2);
        Point Coord6 = new Point(0, -3);

        Sklep shop1 = new Sklep(3, false, 1, Coord);
        Sklep shop2 = new Sklep(2, false, 2, Coord2);
        Sklep shop3 = new Sklep(6, false, 3, Coord3);
        Sklep shop4 = new Sklep(5, false, 4, Coord4);
        Sklep shop5 = new Sklep(4, false, 5, Coord5);
        Sklep Maga = new Sklep(0, false, 0, Coord6);
        Q.add(Maga);
        Q.add(shop1);
        Q.add(shop2);
        Q.add(shop3);
        Q.add(shop4);
        Q.add(shop5);
    }

}

class Sklep {

    int zamowione;
    int nr;
    boolean imdone;
//int[] odleglosc = new int[5];
//int x,y;
    Point pkt = new Point();

    Sklep(int zam, boolean full, int n, Point x) {
        zamowione = zam;
        imdone = full;
        pkt = x;
        nr = n;
        //for(int i=0;i<odleglosc.length;i++){
        //  odleglosc[i]=x[i];
        //  }
    }
}
