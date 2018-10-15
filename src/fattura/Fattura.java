package fattura;

public class Fattura {

    // disegno per composizione
    String nominativo, indirizzo, ID;
    SimpleDate dataEmissione, dataScadenza;
    Bolla bolla;
    int ivaPerc;

    public Fattura(String n, String i, String id, Bolla b, int ivaPerc) {
        nominativo = n;
        indirizzo = i;
        ID = id;
        dataEmissione = new SimpleDate();
        dataScadenza = SimpleDate.dopo30Giorni(dataEmissione);
        bolla = b;
        this.ivaPerc = ivaPerc;
    }

    public String getNominativo() {
        return nominativo;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public String getID() {
        return ID;
    }

    public SimpleDate getDataEmissione() {
        return dataEmissione;
    }

    public SimpleDate getDataScadenza() {
        return dataScadenza;
    }

    public Bolla getBolla() {
        return bolla;
    }

    public int getIvaPerc() {
        return ivaPerc;
    }

    public void setNominativo(String nominativo) {
        if (nominativo.length() >= 3) {
            this.nominativo = nominativo;
        }
    }

    public void setIndirizzo(String indirizzo) {
        if (indirizzo.length() >= 3) {
            this.indirizzo = indirizzo;
        }
    }

    public void setID(String ID) {
        if (indirizzo.length() >= 11) {
            this.ID = ID;
        }
    }

    public void setDataEmissione(SimpleDate dataEmissione) {
        if (dataEmissione != null) {
            SimpleDate oggi = new SimpleDate();
            if (oggi.after(dataEmissione) || oggi.equals(dataEmissione)) {
                this.dataEmissione = dataEmissione;
                dataScadenza = SimpleDate.dopo30Giorni(dataEmissione);
            }
        }
    }

    public void setDataScadenza(SimpleDate dataScadenza) {
        if (dataScadenza.after(dataEmissione)) {
            this.dataScadenza = dataScadenza;
        }
    }

    public void setIvaPerc(int ivaPerc) {
        if (ivaPerc >= 0 && ivaPerc <= 100) {
            this.ivaPerc = ivaPerc;
        }
    }

    // funzionalità - responsabilità - behavior
    public double getAmmontareIVA() {
        if (ivaPerc == 0) {
            return 0.0;
        } else {
            return bolla.getCostoTotaleNetto() * ivaPerc / 100.0;
        }
    }

    public double getTotale() {
        return bolla.getCostoTotaleNetto() + getAmmontareIVA();
    }

    public String prnIntestazione() {
        int[] tabs = {69};
        String s = bolla.prnLine('+', '-', '+', tabs) + "\n"
                + PrnUtilities.wc(-41, "Aquirente: " + nominativo)
                + PrnUtilities.wc(30, "Codice: " + ID) + "\n"
                + PrnUtilities.wc(-41, "Indirizzo: " + indirizzo)
                + PrnUtilities.wc(30, "Data: " + dataEmissione.toString()) + "\n"
                + PrnUtilities.wc(71, "Scadenza: " + dataScadenza.toString())
                + bolla.prnLine('+', '-', '+', tabs);
        return s;
    }

    public String prnFooter() {
        int[] tabs = {69};
        String s = bolla.prnLine('+', '-', '+', tabs) + "\n"
                + "Ammontare IVA: € " + String.format("%,.2f", getAmmontareIVA())
                + "\t\t\tTotale: € " + String.format("%,.2f", getTotale())
                + bolla.prnLine('+', '-', '+', tabs);
        return s;
    }

    public String toString() {
        String s = prnIntestazione()
                + "\n" + bolla.prnDettagliCosti()
                + prnFooter();
        return s;
    }

    public static void test() {
        Bolla b = new Bolla("Sig. Mario Rossi", "Corso Indipendenza, 3",
                "ABC DFG 123 123B", "08/10/2018");
        Dettaglio d1 = new Dettaglio("Pane", 3.20, 0.5, 0);
        Dettaglio d2 = new Dettaglio("Passata Cirio", 6.0, 10, 15);
        Dettaglio d3 = new Dettaglio("Olio", 7.3, 0.5, 2);
        b.add(d3);
        b.add(d2);
        b.add(d1);
        Fattura f = new Fattura("Sig. Sergio Rossi", "Via della Vittoria, 2",
                "HIL MNO 345 678V", b, 22);
        System.out.println(f);
        System.out.println("\nSegue bolla di accompagnamento");
        System.out.println(b);
    }
}
