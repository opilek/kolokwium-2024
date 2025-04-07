import java.util.HashMap;
import java.util.Map;

public class Main
{
    public static void main(String[] args)
    {
       DigitalClock c1=new DigitalClock(01,0,0,DigitalClock.ClockType.TYPE12);
        System.out.println(c1.toString());


        Map<String,City> cityMap=City.parseFile("src/strefy.csv");

        System.out.println(cityMap);

    }
}