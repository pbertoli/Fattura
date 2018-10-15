package fattura;

public class Bolla {

    private String nominativo, indirizzo, ID;
    private SimpleDate data;
    private Dettaglio[] dettagli;
    private int nDettagli;

    public Bolla() {
        nominativo = "";
        indirizzo = "";
        ID = "";
        dettagli = new Dettaglio[10];
        data = new SimpleDate();
        nDettagli = 0;
    }

    public Bolla(String n, String i, String id, String d) {
        this();
        nominativo = n;
        indirizzo = i;
        ID = id;
        data = new SimpleDate(d);
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

    public SimpleDate getData() {
        return data;
    }

    public String getStringDate() {
        return data.getDateToString();
    }

    public void setNominativo(String nominativo) {
        this.nominativo = nominativo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setData(String data) {
        this.data = new SimpleDate(data);
    }

    // modifica
    public void setDettaglio(Dettaglio dettaglio, int i) {
        if (dettagli[i] != null) {
            if (i >= 0 && i < nDettagli) {
                this.dettagli[i] = dettaglio;
            }
        }
    }

    // inserimento
    public void add(Dettaglio d) {
        if (d != null) {
            if (nDettagli < dettagli.length) {
                dettagli[nDettagli] = d;
                nDettagli = nDettagli + 1; // dimens. dinamica
            }
        }
    }

    // calcolo costo d'acquisto totale
    public double getCostoTotaleListino() {
        double totale = 0;
        for (int i = 0; i < nDettagli; i++) {
            totale += dettagli[i].getCostoListino();
        }
        return totale;
    }

    // calcolo costo d'acquisto totale
    public double getCostoTotaleNetto() {
        double totale = 0;
        for (int i = 0; i < nDettagli; i++) {
            totale += dettagli[i].getCostoScontato();
        }
        return totale;
    }

    // calcolo sconto sull'acquisto totale
    public double getScontoTotaleNetto() {
        double totale = 0;
        for (int i = 0; i < nDettagli; i++) {
            totale += dettagli[i].getScontoNetto();
        }
        return totale;
    }

    public String prnDettagliCosti() {
        int[] tabs = {20, 12, 10, 6, 17};
        String s = Dettaglio.getTitolo()
                + PrnUtilities.prnLine('+', '-', '+', tabs);
        for (int i = 0; i < nDettagli; i++) {
            s += "\n" + dettagli[i].toString();
        }
        for (int i = nDettagli; i < dettagli.length; i++) {
            s += PrnUtilities.prnLine('|', ' ', '|', tabs);
        }
        s += PrnUtilities.prnLine('+', '-', '+', tabs);
        s += "\nTotale listino: " + String.format("%,.2f", getCostoTotaleListino());
        s += "\nTotale netto: " + String.format("%,.2f", getCostoTotaleNetto());
        s += "\nSconto netto: " + String.format("%,.2f", getScontoTotaleNetto());
        return s;
    }

    public String prnDettagli() {
        int[] tabs = {20, 12, 10, 6, 17};
        String s = Dettaglio.getTitolo()
                + PrnUtilities.prnLine('+', '-', '+', tabs);
        for (int i = 0; i < nDettagli; i++) {
            s += "\n" + dettagli[i].dettaglioVuoto();
        }
        for (int i = nDettagli; i < dettagli.length; i++) {
            s += PrnUtilities.prnLine('|', ' ', '|', tabs);
        }
        s += PrnUtilities.prnLine('+', '-', '+', tabs);
        s += "\nTotale netto: -";
        return s;
    }

    public String prnIntestazione() {
        int[] tabs = {69};
        String s = PrnUtilities.prnLine('+', '-', '+', tabs) + "\n"
                + PrnUtilities.wc(-41, "Destinatario: " + nominativo)
                + PrnUtilities.wc(30, "Codice: " + ID) + "\n"
                + PrnUtilities.wc(-41, "Indirizzo: " + indirizzo)
                + PrnUtilities.wc(30, "Data: " + data.toString())
                + PrnUtilities.prnLine('+', '-', '+', tabs);
        return s;
    }

    public String bollaVuota() {
        return prnIntestazione() + "\n"
                + prnDettagli();
    }

    public String toString() {
        return prnIntestazione() + "\n"
                + prnDettagliCosti();
    }

    public static void test() {
        Bolla b = new Bolla("Sig. Mario Rossi", "corso Indipendenza,3",
                "ABC DFG 123 123B", "08/10/2018");
        Dettaglio d1 = new Dettaglio("Pane", 3.20, 0.5, 0);
        Dettaglio d2 = new Dettaglio("Passata Cirio", 6.0, 10, 15);
        Dettaglio d3 = new Dettaglio("Olio", 7.3, 0.5, 2);
        b.add(d3);
        b.add(d2);
        b.add(d1);
        System.out.println(b.bollaVuota());
        System.out.println(b);
    }
}
