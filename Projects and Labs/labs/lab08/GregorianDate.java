public class GregorianDate extends Date {

    private static final int[] MONTH_LENGTHS = {
        31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    };

    public GregorianDate(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
    }

    @Override
    public int dayOfYear() {
        int precedingMonthDays = 0;
        for (int m = 1; m < month; m += 1) {
            precedingMonthDays += getMonthLength(m);
        }
        return precedingMonthDays + dayOfMonth;
    }
    @Override
    public GregorianDate nextDate() {
        int m = MONTH_LENGTHS[this.month - 1];
        if (dayOfMonth + 1 > m && month == 12) {
            int nm = 1;
            int nd = 1;
            int ny = year + 1;
            return new GregorianDate(ny, nm, nd);
        } else if (dayOfMonth + 1 > m) {
            int nm = month + 1;
            int nd = 1;
            return new GregorianDate(year, nm, nd);
        } else {
            return new GregorianDate(year, month, dayOfMonth + 1);
        }
    }

    private static int getMonthLength(int m) {
        return MONTH_LENGTHS[m - 1];
    }
}
