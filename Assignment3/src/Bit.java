
public class Bit {

    private boolean value;

    public Bit(boolean value){
        //Task 3.1
        this.value=value;
    }

    public int toInt(){ 
        int ans = 0;
        //Task 3.2
        if (this.value)
            ans = 1;
        else
            ans =0;
        return ans;
    }

    public String toString(){
        String ans = "";
        //Task 3.3
        ans = ans + toInt();
        return ans;
    }
}

