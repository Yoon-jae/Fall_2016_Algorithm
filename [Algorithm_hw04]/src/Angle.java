import java.util.Scanner;

/**
 * Created by yoonjae on 10/6/16.
 */
public class Angle {
    private double hourAngle;
    private double minuteAngle;

    public Angle() {

    }

    public String[] getTimeFromCommand() {
        System.out.print("Enter current hour(00:00 ~ 23:59) : ");
        Scanner input = new Scanner(System.in);
        String line = input.nextLine();
        System.out.println(line);
        return line.split(":");
    }

    public void calculatingAngle(int hour, int minute) {

    }

    @Override
    public String toString() {
        return "Angle{" +
                "hourAngle=" + hourAngle +
                ", minuteAngle=" + minuteAngle +
                '}';
    }
}
