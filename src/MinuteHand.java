import java.time.LocalTime;

public class MinuteHand extends ClockHand
{
    private double angle;

    public double getAngle() {return angle;}

    public MinuteHand(int hour, int minutes, int seconds, City city, double angle)
    {
        super(hour, minutes, seconds, city);
        this.angle=angle;
    }

    @Override
    public void setTime(LocalTime time)
    {
        int minutes=time.getMinute();
        int seconds=time.getSecond();

        this.angle = 360 * (minutes / 60.0 + (seconds / 60.0) / 60.0);
    }
    @Override
    public String  toSvg()
    {
        double length = 70;  // Długość wskazówki minutowej
        double thickness = 3; // Grubość
        return "<line x1=\"0\" y1=\"0\" x2=\"" + (length * Math.cos(Math.toRadians(angle))) + "\" " +
                "y2=\"" + (length * Math.sin(Math.toRadians(angle))) + "\" stroke=\"black\" stroke-width=\"" + thickness + "\" />";
    }
}
