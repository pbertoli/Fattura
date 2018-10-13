package fattura;
public class PrnUtilities {

    public static String prnLine(char sx, char c, char dx, int[] tabs) {
        String s = "\n" + String.valueOf(sx);
        for (int i = 0; i < tabs.length; i++) {
            for (int j = 0; j < tabs[i]; j++) {
                s += String.valueOf(c);
            }
            s += String.valueOf(dx);
        }
        return s;
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
}
