import java.util.*;

/**
 * Created by yoonjae on 10/16/16.
 */


public class Main {

    public static Building[] buildings = new Building[10001];

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("빌딩 수를 입력하세요. ");
        int numberOfBuilding = input.nextInt();
        input.nextLine();

        inputDetailBuildingInfo(input, numberOfBuilding);

        int skylines[] = findSkyline(buildings, 1, numberOfBuilding);

        printResultSkyline(skylines);
    }

    /**
     * Input detail information about each building.
     * int lx, h, rx.
     *
     * @param input
     * @param numberOfBuilding
     */
    private static void inputDetailBuildingInfo(Scanner input, int numberOfBuilding) {
        for (int i = 1; i <= numberOfBuilding; i++) {
            String line = input.nextLine();
            StringTokenizer stk = new StringTokenizer(line, ",");
            buildings[i] = (new Building(Integer.parseInt(stk.nextToken()),
                                         Integer.parseInt(stk.nextToken()),
                                         Integer.parseInt(stk.nextToken())));
        }
    }

    /**
     * Print skyline result.
     *
     * @param skylines
     */
    private static void printResultSkyline(int[] skylines) {
        for (int i = 1; i < skylines.length; i++) {
            if (i == skylines.length - 1)
                System.out.println(skylines[i]);
            else
                System.out.print(skylines[i] + ",");
        }
    }

    /**
     * Find skyline (Divied part)
     *
     * @param buildings
     * @param start
     * @param end
     * @return
     */
    public static int[] findSkyline(Building[] buildings, int start, int end) {
        if (start == end) {
            int[] skyline = {0};
            skyline = appendSkyline(skyline, buildings[start].lx, buildings[start].h);
            skyline = appendSkyline(skyline, buildings[end].rx, 0);

            return skyline;
        }

        int mid = (start + end) / 2;
        int[] sky1 = findSkyline(buildings, start, mid);
        int[] sky2 = findSkyline(buildings, mid + 1, end);

        return mergeSkyline(sky1, sky2);
    }

    /**
     * Merge Skyline with sky1 and sky2.
     *
     * @param sky1
     * @param sky2
     * @return
     */
    public static int[] mergeSkyline(int[] sky1, int[] sky2) {
        int[] skyline = {0};

        int currentX, maxH;
        int currentH1 = 0, currentH2 = 0;
        int sky1_count = sky1.length / 2;
        int sky2_count = sky2.length / 2;
        int sky1_pointer = 1;
        int sky2_pointer = 1;

        while (sky1_count > 0 && sky2_count > 0) {
            if (sky1[sky1_pointer] < sky2[sky2_pointer]) {
                currentX = sky1[sky1_pointer];
                currentH1 = sky1[sky1_pointer + 1];
                maxH = currentH1;

                if (currentH2 > maxH)
                    maxH = currentH2;

                skyline = appendSkyline(skyline, currentX, maxH);
                sky1_count--;
                sky1_pointer += 2;
            } else {
                currentX = sky2[sky2_pointer];
                currentH2 = sky2[sky2_pointer + 1];
                maxH = currentH1;

                if (currentH2 > maxH)
                    maxH = currentH2;

                skyline = appendSkyline(skyline, currentX, maxH);
                sky2_count--;
                sky2_pointer += 2;
            }
        }

        while (sky1_count > 0) {
            skyline = appendSkyline(skyline, sky1[sky1_pointer], sky1[sky1_pointer + 1]);
            sky1_count--;
            sky1_pointer += 2;
        }

        while (sky2_count > 0) {
            skyline = appendSkyline(skyline, sky2[sky2_pointer], sky2[sky2_pointer + 1]);
            sky2_count--;
            sky2_pointer += 2;
        }

        skyline = removeOverlapPart(skyline);

        return skyline;
    }

    /**
     * Append skyline to result int array.
     *
     * @param skyline
     * @param x
     * @param h
     * @return
     */
    public static int[] appendSkyline(int[] skyline, int x, int h) {
        int[] ret = new int[skyline.length + 2];
        System.arraycopy(skyline, 0, ret, 0, skyline.length);
        ret[skyline.length] = x;
        ret[skyline.length + 1] = h;
        return ret;
    }

    /**
     * Remove overlaped part of skyline.
     *
     * @param skyline
     * @return
     */
    private static int[] removeOverlapPart(int[] skyline) {
        int[] temp = new int[skyline.length];
        int newSize = 3;
        System.arraycopy(skyline, 0, temp, 0, 3);
        for (int i = 4; i < skyline.length; i += 2) {
            if (skyline[i] != skyline[i - 2]) {
                temp[newSize++] = skyline[i - 1];
                temp[newSize++] = skyline[i];
            }
        }
        int[] result = new int[newSize];
        System.arraycopy(temp, 0, result, 0, newSize);

        return result;
    }
}

/**
 * Buildling class
 */
class Building {
    int lx;
    int h;
    int rx;

    public Building(int lx, int h, int rx) {
        this.lx = lx;
        this.h = h;
        this.rx = rx;
    }
}


/**
 * Make skyline using priority queue
 */

/*
public class Main {

    public static StringBuffer answer = new StringBuffer();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        input.nextLine();

        PriorityQueue<Building> pq = new PriorityQueue<Building>();

        for(int i=1; i<=n; i++) {
            String line = input.nextLine();
            StringTokenizer stk = new StringTokenizer(line, ",");
            pq.add(new Building(Integer.parseInt(stk.nextToken()),
                                    Integer.parseInt(stk.nextToken()),
                                    Integer.parseInt(stk.nextToken())));
        }

        Building first = pq.poll();
        int high = first.h;
        int last = first.rx;
        answer.append(first.lx + "," + first.h + ",");

        while(!pq.isEmpty()) {
            Building building = pq.poll();

            if(building.lx > last) {
                answer.append(last + ",0," + building.lx + "," + building.h + ",");
                high = building.h;
                last = building.rx;
            } else if(building.lx == last) {
                if(building.h == high)
                    last = building.rx;
                else {
                    answer.append(building.lx + "," + building.h + ",");
                    high = building.h;
                    last = building.rx;
                }
            } else {
                if(building.h > high) {
                    if(building.rx < last)
                        pq.add(new Building(building.rx, high, last));
                    answer.append(building.lx + "," + building.h + ",");
                    high = building.h;
                    last = building.rx;
                } else {
                    if(building.rx > last)
                        pq.add(new Building(last, building.h, building.rx));
                }
            }
        }

        answer.append(last + ",0");
        System.out.println(answer.toString());

    }

    public static class Building implements Comparable<Building> {
        public int lx;
        public int h;
        public int rx;

        public Building(int lx, int h, int rx) {
            this.lx = lx;
            this.h = h;
            this.rx = rx;
        }

        @Override
        public String toString() {
            return "Building{" +
                    "lx=" + lx +
                    ", h=" + h +
                    ", rx=" + rx +
                    '}';
        }

        @Override
        public int compareTo(Building o) {
            return this.lx > o.lx ? 1:-1;
        }
    }
}

*/