public class DigitalClock extends Clock
{
    //Typ wyliczeniowy (nazwa z dużej litery!!!)
    public enum ClockType
    {
        TYPE12,
        TYPE24;
    }
    // Pole do przechowywania typu zegara
    private ClockType clockType;

    //Getter
    public ClockType getClockType() {return clockType;}

    //Konstruktor
    public DigitalClock(int hour, int minutes, int seconds,City city, ClockType clockType)
    {
        super(hour, minutes, seconds,city);

        this.clockType=clockType;
    }


    @Override

    public String toString()
    {
        if(clockType==clockType.TYPE24)
        {
            return super.toString();
        }
        else
        {
            String period="AM";
            int hour=getHour();
            int minutes=getMinutes();
            int seconds=getSeconds();

            if(hour>=12)
            {
                period="PM";
            }
            else if(hour>12)
            {
                hour-=12;
            }
            else if(hour==0)
            {
                hour=12;
            }

            return String.format("%d:%02d:%02d %s",hour,minutes,seconds,period);


        }
    }

}
