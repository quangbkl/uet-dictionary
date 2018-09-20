package TestDrive;

class Date {
    private int year, month, day;

    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public Date() {
        this(1, 1, 2014);
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void dayOfWeek() {
        System.out.println(day + "/" + month + "/" + year);
//        int val;
//        if (month < 3 && month >= 1) {
//            val = month + 10;
//        } else if (month >= 3 && month <= 12) {
//            val = month - 2;
//        } else {
//            System.out.println("Ngay khong hop le.");
//            return;
//        }
//
//        int century = (year / 100) * 10 + 4;
//        int year1 = (year % 100) * 10 + 4;
//        int year2 = (year / 100);
//        int weekday = (13 * val - 15 + century + year1 + day + (year % 100) - 2 * year2) % 7;
        int y0 = year - (14 - month) / 12;
        int x = y0 + y0 / 4 - y0 / 100 + y0 / 400;
        int m0 = month + 12 * ((14 - month) / 12) - 2;
        int weekday = (day + x + 31 * m0 / 12) % 7;
        if (weekday == 0) System.out.println("Chu nhat.");
        else System.out.println("Thu " + (weekday + 1));
    }
}

public class DateTestDrive {
    public static void main(String[] args) {
//        Date newDate = new Date(2018, 8, 26);
//        newDate.dayOfWeek();
//        System.out.println(13 / 2);
        String s1 = "Hello";
        String s2 = "Hello";
        System.out.println(s1 == s2);
    }
}
