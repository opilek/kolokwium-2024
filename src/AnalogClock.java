import java.io.FileWriter;
import java.io.IOException;

public class AnalogClock extends Clock
{
    private double cx;
    private double cy;
    private double r;

    public double getCx() {return cx;}
    public double getCy() {return cy;}
    public double getR() {return r;}

    public AnalogClock(int hour, int minutes, int seconds, City city, double cx, double cy, double r)
    {
        super(hour, minutes, seconds, city);

        this.cx=cx;
        this.cy=cy;
        this.r=r;
    }

    public void toSvg(String filePath )
    {
         String content=
                 "<svg width=\"200\" height=\"200\" viewBox=\"-100 -100 200 200\" xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "  <!-- Tarcza zegara -->\n" +
            "  <circle cx=\"0\" cy=\"0\" r=\"90\" fill=\"none\" stroke=\"black\" stroke-width=\"2\" />\n" +
            "  <g text-anchor=\"middle\">\n" +
            "    <text x=\"0\" y=\"-80\" dy=\"6\">12</text>\n" +
            "    <text x=\"80\" y=\"0\" dy=\"4\">3</text>\n" +
            "    <text x=\"0\" y=\"80\" dy=\"6\">6</text>\n" +
            "    <text x=\"-80\" y=\"0\" dy=\"4\">9</text>\n" +
            "  </g>\n" +
            "</svg>";


        try(FileWriter writer=new FileWriter(filePath))
        {
                writer.write(content);

            System.out.println("Plik SVG został zapisany w " + filePath);
        }
        catch(IOException e)
        {
            System.out.println("Błąd zapisu do pliku " + e.getMessage());
        }
    }


}
