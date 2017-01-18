package weather.model.current_weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*"wind":{"speed":7.31,"deg":187.002},*/
@JsonIgnoreProperties(ignoreUnknown = true)
public class Wind {
    private float speed;
    private int deg;
    private float gust;
    private int varBeg;
    private int varEnd;

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getDeg() {
        return deg;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }

    public float getGust() {
        return gust;
    }

    public void setGust(float gust) {
        this.gust = gust;
    }

    public int getVarBeg() {
        return varBeg;
    }

    public void setVarBeg(int varBeg) {
        this.varBeg = varBeg;
    }

    public int getVarEnd() {
        return varEnd;
    }

    public void setVarEnd(int varEnd) {
        this.varEnd = varEnd;
    }

    @Override
    public String toString() {
        return "Wind{" +
                "speed=" + speed +
                ", deg=" + deg +
                ", gust=" + gust +
                ", varBeg=" + varBeg +
                ", varEnd=" + varEnd +
                '}';
    }
}
