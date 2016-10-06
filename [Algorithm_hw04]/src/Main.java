import java.util.Scanner;

/**
 * Created by yoonjae on 10/6/16.
 */
public class Main {
    public static void main(String[] args) {
        Angle angle = new Angle();
        String time[] = angle.getTimeFromCommand();
        angle.calculatingAngle(Integer.parseInt(time[0]),Integer.parseInt(time[1]));

        System.out.println(angle);
    }
}
