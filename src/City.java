import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class City
{

    private String capital;
    private int timeZone;
    private double latitude;
    private double longitude;

    //Gettery
    public String getCapital() {return capital;}
    public int getTimeZone() {return timeZone;}
    public double getLatitude() {return latitude;}
    public double getLongitude() {return longitude;}

    //Konstruktor
    public City(String capital, int timeZone, double latitude, double longitude) {
        this.capital = capital;
        this.timeZone = timeZone;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static City parsleLine(String line)
    {
        String[] parts=line.split(",");
        String capital=parts[0].trim();
        int timeZone=Integer.parseInt(parts[1]);
        double latitude=Double.parseDouble(parts[2].trim());
        double longitude=Double.parseDouble(parts[3].trim());

        return new City(capital,timeZone,latitude,longitude);
    }

    public static Map<String,City> parseFile(String filePath)
    {
        // Tworzymy pustą mapę, która będzie przechowywać miasta(key:stolica,value:miasto)
        Map<String,City> cityMap=new HashMap<>();

        // Blok try-with-resources, który automatycznie zamknie BufferedReader po zakończeniu działania
        try(BufferedReader br=new BufferedReader(new FileReader(filePath)))
        {
            br.readLine();//Ingoruje 1 linie
            String line;//przechowuje linie odczytane z pliku


            while((line=br.readLine())!=null)
            {
                // Metoda `parseLine` parsuje linię tekstu i zwraca obiekt typu `City` na podstawie danych zawartych w tej linii.
                City city=parsleLine(line);

                //Dodanie miasta do cityMap
                cityMap.put(city.getCapital(),city);
            }
        }
        catch (IOException e)
        {
            System.out.println("Błąd z odczytem pliku");
        }

        return cityMap;
    }

    public LocalTime localMeanTime(LocalTime localTime)
    {
        // 1 stopień długości geograficznej = 4 minuty
        // Liczymy różnicę długości geograficznej w stopniach i mnożymy przez 4
        int longitudeOffsetMinutes = (int) (longitude * 4);

        // Strefa czasowa w minutach (czas UTC + offset)
        int timezoneOffsetMinutes = timeZone * 60;

        // Całkowite przesunięcie w minutach (geograficzne + strefa czasowa)
        int totalOffsetMinutes = longitudeOffsetMinutes + timezoneOffsetMinutes;

        // Zmieniamy czas lokalny w minutach
        int totalMinutes = localTime.getHour() * 60 + localTime.getMinute() + totalOffsetMinutes;

        // Obliczamy nową godzinę i minutę
        int newHour = totalMinutes / 60;
        int newMinute = totalMinutes % 60;

        // Ustawiamy godzinę w odpowiednich granicach (0-23)
        if (newHour < 0)
        {
            newHour += 24;
        }
        else if (newHour >= 24)
        {
            newHour -= 24;
        }

        // Tworzymy nowy obiekt LocalTime z obliczonymi godzinami i minutami
        return LocalTime.of(newHour, newMinute);
    }



    public static Comparator<City> worstTimezoneFit()
    {
        // Zwracamy Comparator z wykorzystaniem wyrażenia lambda
        return (city1, city2) -> {

            // Obliczamy różnicę pomiędzy strefą czasową a obliczoną wartością na podstawie długości geograficznej
            // Długość geograficzna podzielona przez 15 daje wartość, która odpowiada przesunięciu czasowemu
            // (1 godzina = 15 stopni długości geograficznej)

            double diff1 = Math.abs(city1.getTimeZone() - (city1.getLongitude() / 15));
            double diff2 = Math.abs(city2.getTimeZone() - (city2.getLongitude() / 15));


            // Zwracamy porównanie w odwrotnej kolejności, ponieważ chcemy sortować malejąco
            return Double.compare(diff2, diff1);
        };
    }








}
