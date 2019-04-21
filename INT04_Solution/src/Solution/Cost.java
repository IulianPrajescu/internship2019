package Solution;

public class Cost {
    private double TVAAmount;
    private double feeAmount;

    public Cost(double TVAAmount,double feeAmount)
    {
        this.TVAAmount=TVAAmount;
        this.feeAmount=feeAmount;
    }

    public double getTVAAmount() {
        return TVAAmount;
    }

    public double getFeeAmount() {
        return feeAmount;
    }

    public void setTVAAmount(double TVAAmount) {
        this.TVAAmount = TVAAmount;
    }

    public void setFeeAmount(double feeAmount) {
        this.feeAmount = feeAmount;
    }

    @Override
    public String toString() {
        String s="";
        s+="TVA amount: "+String.valueOf(this.TVAAmount)+" | ";
        s+="Fee amount: "+String.valueOf(this.feeAmount);
        return s;
    }
}
