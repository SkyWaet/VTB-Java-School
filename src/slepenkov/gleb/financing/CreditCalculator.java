package slepenkov.gleb.financing;

public class CreditCalculator {
    public static long paymentCalculator(long creditSum, int period, double percent) {
        double monthlyPercent = percent / 12;
        double coeff = monthlyPercent * Math.pow(1 + monthlyPercent, period) / (Math.pow(1 + monthlyPercent, period) - 1);
        return Math.round(coeff * creditSum);

    }

    public static void main(String[] args) {
        long sum = 1_000_000L;
        int period = 36;
        double percent = 0.0999;

        System.out.println(paymentCalculator(sum, period, percent));
    }
}
