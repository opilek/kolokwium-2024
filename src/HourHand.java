import java.time.LocalTime;

public class HourHand extends ClockHand
{

    private double angle;

    public double getAngle() {return angle;}

    public HourHand(int hour, int minutes, int seconds, City city, double angle)
    {
        super(hour, minutes, seconds, city);
        this.angle=angle;
    }

    @Override
    public void setTime(LocalTime time)
    {
        int hour=time.getHour();
        int minutes=time.getMinute();

        this.angle = 360 * ((hour % 12) / 12.0 + (minutes / 60.0) / 12.0);
    }
    @Override
    public String  toSvg()
    {
        double length = 50;  // Długość wskazówki godzinowej
        double thickness = 4; // Grubość
        return "<line x1=\"0\" y1=\"0\" x2=\"" + (length * Math.cos(Math.toRadians(angle))) + "\" " +
                "y2=\"" + (length * Math.sin(Math.toRadians(angle))) + "\" stroke=\"black\" stroke-width=\"" + thickness + "\" />";
    }
}
