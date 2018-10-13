package fattura;

import java.time.LocalDate;

public class SimpleDate {

    private LocalDate date;

    public SimpleDate() {
        date = LocalDate.now();
    }

    public SimpleDate(int g, int m, int a) {
        // verifica le precondizioni
        if (range(1, g, 31) && range(1, m, 12) && range(1870, a, 2100)) {
            date = LocalDate.of(a, m, g);
        } else {
            date = LocalDate.now();
        }
    }

    public SimpleDate(String s) {
        int[] ints = getIntsFromString(s);
        // verifica delle precondizzioni
        if (range(1, ints[2], 31) && range(1, ints[1], 12) && range(1870, ints[0], 2100)) {
            date = LocalDate.of(ints[0], ints[1], ints[2]);
        } else {
            date = LocalDate.now();
        }
    }

    // il vettore di interi fa l'inversione di anno e giorno
    private int[] getIntsFromString(String s) {
        String[] vs = s.split("[-/.]");
        int[] vi = {0, 0, 0};
        if (vs.length != 3) {
            return vi;
        }
        vi[0] = Integer.parseInt(vs[2]);
        vi[1] = Integer.parseInt(vs[1]);
        vi[2] = Integer.parseInt(vs[0]);
        return vi;
    }

    // NOME     range
    // SCOPO    verificare che un numero intero sia 
    //          interno ad un intervallo numerico
    // INGRESSO min     limite inferiore intero compreso
    //          max     limite superiore intero compreso
    //          value   valore da verificare nel'intervallo
    // USCITA   bool    vero se min<= value <= max
    // FIRMA    bool range (int min, int value, int max)
    //
    boolean range(int min, int value, int max) {
        return min <= value && value <= max;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDateToString() {
        return toString();
    }

    public void setDateFromString(String s) {
        int[] ints = getIntsFromString(s);
        if (range(1, ints[2], 31) && range(1, ints[1], 12) && range(1870, ints[0], 2100)) {
            date = LocalDate.of(ints[0], ints[1], ints[2]);
        } else {
            date = LocalDate.now();
        }
    }

    // NOME     setDateFromInts
    // SCOPO    modifica la data partendo da tre interi: g,m,a
    // INGRESSO g   intero del giorno
    //          m   intero del mese
    //          a   intero dell'anno
    // USCITA   void    modifica la data dell'oggetto
    // VINCOLI  1 <= g <= 31
    //          1 <= m <= 12
    //          1870 <= a <= 2100
    // FIRMA    void setDateFromInts(int g, int m, int a)
    //   
    public void setDateFromInts(int g, int m, int a) {
        // verificare i parametri in ingresso (precondizioni)
        if (range(1, g, 31) && range(1, m, 12) && range(1870, a, 2100)) {
            // modificare la data interna all'oggetto
            date = LocalDate.of(a, m, g);
        } else {
            // le cose non vanno bene: assegno la data odierna
            date = LocalDate.now();
        }
    }

    public boolean equals(SimpleDate d) {
        if (date.isEqual(d.date)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean after(SimpleDate d) {
        if (date.isAfter(d.date)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean before(SimpleDate d) {
        if (date.isBefore(d.date)) {
            return true;
        } else {
            return false;
        }
    }
    
    public static SimpleDate dopo30Giorni(SimpleDate d) {
        int g = d.getDate().getDayOfMonth();
        int m = d.getDate().getMonthValue();
        int a = d.getDate().getYear();
        if (m==12) {
            a++;
            m=0;
        }    
        SimpleDate newD = new SimpleDate (g,m+1,a);
        return newD;
    }

    public String toString() {
        String s = date.toString();
        String[] vs = s.split("-");
        return vs[2] + "/" + vs[1] + "/" + vs[0];
    }

    public static void test() {
        SimpleDate sd1 = new SimpleDate("10.12.2018");
        SimpleDate sd2;// = new SimpleDate("12.12.2018");
//        System.out.println(sd1);
//        SimpleDate sd2 = new SimpleDate("6-12-2100");
//        System.out.println(sd2);
//        sd2.setDateFromString("3/12/2008");
//        System.out.println(sd2);
//        sd2.setDateFromInts(15, 3, 1913);
//        System.out.println(sd2);
//        System.out.println(sd1.equals(sd2));
//        System.out.println(sd2.after(sd2));
//        System.out.println(sd2.equals(sd2));
//        System.out.println(sd2.before(sd2));
//        System.out.println(sd2.before(sd1));
        sd2 = SimpleDate.dopo30Giorni(sd1);   
        System.out.println(sd1);
        System.out.println(sd2);
        System.out.println((sd1.after(sd2))? "errore":"corretto");
    } 
}    


