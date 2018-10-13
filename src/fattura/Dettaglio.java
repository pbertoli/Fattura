package fattura;

public class Dettaglio {

    private String prodotto;
    private double prezzo;
    private double quantità;
    private int sconto; // percentuale di sconto

    public Dettaglio() {
        prodotto = "";
        prezzo = 0.0;
        quantità = 0.0;
        sconto = 0;
    }

    public Dettaglio(String p, double pr, double q, int s) {
        prodotto = p;
        prezzo = pr;
        quantità = q;
        sconto = s;
    }

    public String getProdotto() {
        return prodotto;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public double getQuantità() {
        return quantità;
    }

    public int getSconto() {
        return sconto;
    }

    public void setProdotto(String prodotto) {
        this.prodotto = prodotto;
    }

    public void setPrezzo(double prezzo) {
        if (prezzo >= 0) {
            this.prezzo = prezzo;
        }
    }

    public void setQuantità(double quantità) {
        if (quantità > 0) {
            this.quantità = quantità;
        }
    }

    public void setSconto(int sconto) {
        if (sconto >= 0 && sconto <= 100) {
            this.sconto = sconto;
        }
    }

    // funzionalità
    public double getCostoListino() {
            return prezzo * quantità;
    }
    
    public double getCostoScontato() {
        if (sconto == 0.0) {
            return prezzo * quantità;
        } else {
            return prezzo * quantità * (100 - sconto) / 100;
        }
    }
        public double getScontoNetto() {
        if (sconto == 0.0) {
            return 0.0;
        } else {
            return prezzo * quantità * sconto / 100;
        }
    }

    // TODO String getWidthColumn(int width, String s )
    public static String wc(int width, String s) {
        boolean aDestra = true;
        if (width < 0) {
            aDestra = false;
            width = -width;
        }
        if (s.length() == width) {
            return s;
        }
        s = s.trim();
        if (s.length() == width) {
            return s;
        }
        if (s.length() > width) {
            s = s.substring(0, width);
        } else {
            if (aDestra) {
                s = String.format("%" + width + "s", s);
            } else {
                s = String.format("%-" + width + "s", s);
            }
        }
        return s;
    }

    // TODO String getWidthColumn(int width, double d )
    public static String wc(int width, double d) {
        String s = String.format("%,.2f", d);
        return wc(width, s);
    }

    // TODO String getWidthColumn(int width, int i )
    public static String wc(int width, int i) {
        String s = String.format("%d", i);
        return wc(width, s);
    }

    public static String getTitolo() {
        return "|" + wc(-20, "prodotto") + "|" + wc(12, "prezzo") + "|"
                + wc(-10, "quantità") + "|" + wc(-6, "sconto") + "|€ "
                + wc(15, "costo") + "|";
    }

    public String dettaglioVuoto() {
        return "|" + wc(-20, prodotto) + "|€ " + wc(10, "-") + "|"
                + wc(10, quantità) + "|" + wc(4, sconto) + "% |€ "
                + wc(15, "-") + "|";
    }

    public String toString() {
        return "|" + wc(-20, prodotto) + "|€ " + wc(10, prezzo) + "|"
                + wc(10, quantità) + "|" + wc(4, sconto) + "% |€ "
                + wc(15, getCostoScontato()) + "|";
    }

    public static void test() {
        Dettaglio d1 = new Dettaglio("Scarpe", 72, 1, 10);
        System.out.println(d1.getTitolo());
        System.out.println(d1);
        Dettaglio d2 = new Dettaglio("Lenzuolo", 34, 3, 5);
        System.out.println(d2);
    }
}

