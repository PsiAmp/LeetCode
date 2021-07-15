package sandbox;

public class Intro {

    static int z = 55;

    int x;
    int y;


    public static void main(String[] args) {
        z = 44;


        int x = 0;
        while (x < 100) {
            x++;
        }

        if (x == 0)
            System.out.println("x is equal 0");
        else if (x == 55)
            System.out.println("x is equal 55");
        else
            System.out.println(x);

        switch (x) {
            case 0:
                System.out.println("x is equal 0");
                break;
            case 55:
                System.out.println("x is equal 55");
                break;
            default:
                System.out.println(x);
        }

        Intro intro1 = new Intro();
        intro1.x = 2;
        intro1.y = 3;
        intro1.z = 4;

        Intro intro2 = new Intro();

        intro2.x = 0;
        intro2.y = 9;
    }

    public double getDoubleOfX(int a) {
        y = 4;

        a = 4;
        x = 4;

        return x * x;
    }

}
