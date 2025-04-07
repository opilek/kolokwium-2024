import java.time.LocalTime;

public abstract class ClockHand extends Clock
{

    public ClockHand(int hour, int minutes, int seconds,City city)
    {
        super(hour, minutes, seconds, city);
    }

    public abstract void setTime(LocalTime time);
    public abstract String toSvg();
}
