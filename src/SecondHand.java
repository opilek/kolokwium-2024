import java.time.LocalTime;

public class SecondHand extends ClockHand
{
    private double angle;

    public double getAngle() {return angle;}

    public SecondHand(int hour, int minutes, int seconds, City city, double angle)
    {
        super(hour, minutes, seconds, city);
        this.angle=angle;
    }

    @Override
    public void setTime(LocalTime time)
    {
        int seconds=time.getSecond();

        this.angle=360*(seconds/60);
    }
    @Override
    public String  toSvg()
    {
        double length = 90;  // Długość wskazówki sekundowej
        double thickness = 1; // Grubość
        return "<line x1=\"0\" y1=\"0\" x2=\"" + (length * Math.cos(Math.toRadians(angle))) + "\" " +
                "y2=\"" + (length * Math.sin(Math.toRadians(angle))) + "\" stroke=\"red\" stroke-width=\"" + thickness + "\" />";
    }
}
