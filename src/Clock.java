import java.time.LocalTime;

public abstract class Clock
{
    private int hour;
    private int minutes;
    private int seconds;
    private City city;

    //Gettery
    public int getHour() {return hour;}
    public int getMinutes() {return minutes;}
    public int getSeconds() {return seconds;}
    public City getCity() {return city;}

    //Konstruktor
    public Clock(int hour, int minutes, int seconds,City city)
    {
        this.hour=hour;
        this.minutes=minutes;
        this.seconds=seconds;
        this.city=city;
    }
    //Metoda która ustawia obecny czas systemowy
    public void setCurrentTime(int hour, int minutes, int seconds)
    {
        LocalTime currentTime=LocalTime.now();

        this.hour=currentTime.getHour();
        this.minutes=currentTime.getMinute();
        this.seconds=currentTime.getSecond();
    }
    //Metoda która ustawia czas
    public void setTime(int hour, int minutes, int seconds) throws IllegalArgumentException {
        if (hour < 0 || hour > 23) {
            throw new IllegalArgumentException("Godzina powinna być podana z zakresu 0-23 podano: " + hour);
        }
        if (minutes < 0 || minutes > 59) {
            throw new IllegalArgumentException("Minuty powinny być podane z zakresu 0-59 podano: " + minutes);
        }
        if (seconds < 0 || seconds > 59) {
            throw new IllegalArgumentException("Sekundy powinny być podane z zakresu 0-59 podano: " + seconds);
        }

        this.hour = hour;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    @Override

    public String toString()
    {
        return String.format("%02d:%02d:%02d",hour,minutes,seconds);
    }


    public void setCity(City city)
    {
        this.city=city;

        this.hour=hour+city.getTimeZone();

        if(this.hour>=24)
        {
            this.hour-=24;
        }
        if(this.hour<0)
        {
            this.hour+=24;
        }



    }
}
