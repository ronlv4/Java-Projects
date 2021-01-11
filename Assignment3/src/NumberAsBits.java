import java.util.Arrays;
import java.util.function.BinaryOperator;

public class NumberAsBits {

    private Bit[] bits;

    public NumberAsBits(Bit[] bits) {
        //Task 3.4
        // constructor: Number represented as binary bits.
        if (bits == null)
            throw new IllegalArgumentException("bits array can not be null");
        this.bits = new Bit[bits.length];
        for (int i = 0; i < bits.length; i = i + 1) {
            if (bits[i] == null)
                throw new IllegalArgumentException("bits cell can not be null");
            if (bits[i].toInt() == 1)
                this.bits[i] = new Bit(true);
            else
                this.bits[i] = new Bit(false);
        }
    }

    public String toString() {
        String acc = bin2BCD();
        acc = bcd2DecNum(acc);
        return acc;
    }
    private String bcd2DecNum(String acc) {
        // input is a string in a BCD form and converts it to Decimal.
        String sol = "";
        for (int i = 0; i < acc.length() / 4; i = i + 1) {
            sol = sol + smallBin2Dec(acc.substring(4 * i, 4 * (i + 1)));
        }
        return sol;
    }
    private int smallBin2Dec(String bin) {
        // converts small binary numbers to decimal.
        int power = 1;
        int num = 0;
        for (int i = 0; i < bin.length(); i = i + 1) {
            num = num + ((int) bin.charAt(bin.length() - 1 - i) - '0') * power;
            power = power * 2;
        }
        return num;
    }
    private String bin2BCD() {
        // implements Double Dabble algorithm
        // https://en.wikipedia.org/wiki/Double_dabble
        // constructing zeros string of sufficient length
        int strlen = bits.length + 4* (int)(Math.ceil(bits.length/3));
        String acc = "";
        for (int m=0; m<strlen; m=m+1)
            acc = acc + "0";
        //perform the algorithm, shift a digit to the left and than
        //add 3 to any 4-multiplayer index bcd substring if it is bigger than 4.
        for (int i = 0; i < bits.length; i = i + 1) {
            for (int j = 0; j < acc.length() / 4; j = j + 1) {
                int from = acc.length() - (j + 1) * 4;
                int to = acc.length() - j * 4;
                String bcd = acc.substring(from, to);
                if (!below5(bcd)) {
                    bcd = add3Bcd(bcd);
                    acc = replace(acc, bcd, from, to);
                }
            }
            acc = acc + bits[i].toString(); // shift a digit
        }
        acc = trimZeros (acc);
        while (acc.length() % 4 != 0 || acc.length()==0) // make sure string is in bcd form (every 4 digits represents decimal figure)
            acc = "0" + acc;
        if (bits.length==0) // handles the case where bits is an empty array
            acc="";
        return acc;
    }
    private String trimZeros (String acc){
        // trim unnecessary zeros at the left of the string
        while (acc.length()>1 && acc.charAt(0) == '0'){
            acc=acc.substring(1);
        }
        return acc;
    }
    private boolean below5 (String bin){
        // this function accepts String of length 4 representing a decimal figure in Binary
        // and return true iff it's decimal value is below 5
        boolean below = bin.equals("0000") | bin.equals("0001") | bin.equals("0010");
        below = below || bin.equals("0011") || bin.equals("0100");
        return below;
    }
    private String replace(String acc,String replacement, int from, int to){
        // replaces a substring within indexes from-to with the given replacement
        String reisha = acc.substring(0,from);
        String seifa = acc.substring(to);
        reisha = reisha + replacement + seifa;
        return reisha;
    }
    private String smallDec2Bin(int num){
        String bin = "";
        while (num>0){
            bin = num % 2 + bin;
            num = num /2;
        }
        return bin;
    }
    private String add3Bcd (String bin){
        // this function accepts a String of length 4 representing a decimal figure in Binary
        // and return it's value + 3 in BCD form
        String adder="";
        int fig = smallBin2Dec(bin);
        fig = fig +3;
        adder = smallDec2Bin(fig);
        return adder;
    }

    public String base2() {
        String ans ="";
        //Task 3.6
        for (int i=0;i<this.bits.length;i=i+1){
            ans = ans + this.bits[i].toInt();
        }
        ans = trimZeros(ans);
        return ans;
    }
}


