import java.util.Scanner;

/**
 * Created by yoonjae on 10/6/16.
 */
public class Angle {
    private int currentHour;
    private int currentMinute;
    private double hourAngle;
    private double minuteAngle;
    private Scanner input;

    public Angle() {
        input = new Scanner(System.in);
    }

    public void getTimeFromCommand() {
        System.out.print("Enter current hour(0 ~ 23) : ");
        this.currentHour = input.nextInt() % 12;
        System.out.print("Enter current minute(0 ~ 59) : ");
        this.currentMinute = input.nextInt();
    }

    public void calculatingAngle() {
        this.hourAngle = this.currentHour * 30 + this.currentMinute * 0.5;
        this.minuteAngle = this.currentMinute * 6;
    }

    public void printCurrentAngle() {
        System.out.printf("\nHour angle : %.1f\n",this.hourAngle);
        System.out.printf("Minute angle : %.1f\n",this.minuteAngle);
        System.out.printf("Clock angle : %.1f\n",Math.abs((this.hourAngle - this.minuteAngle)) % 180);
    }
}
