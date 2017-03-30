package hello;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class WeatherReport {
    public final Coord coord;
    public final Sys sys;
    public final Weather weather[];
    public final Main main;
    public final Wind wind;
    public final Rain rain;
    public final Clouds clouds;
    public final long dt;
    public final long id;
    public final String name;
    public final long cod;

    @JsonCreator
    public WeatherReport(@JsonProperty("coord") Coord coord, @JsonProperty("sys") Sys sys, @JsonProperty("weather") Weather[] weather, @JsonProperty("main") Main main, @JsonProperty("wind") Wind wind, @JsonProperty("rain") Rain rain, @JsonProperty("clouds") Clouds clouds, @JsonProperty("dt") long dt, @JsonProperty("id") long id, @JsonProperty("name") String name, @JsonProperty("cod") long cod){
        this.coord = coord;
        this.sys = sys;
        this.weather = weather;
        this.main = main;
        this.wind = wind;
        this.rain = rain;
        this.clouds = clouds;
        this.dt = dt;
        this.id = id;
        this.name = name;
        this.cod = cod;
    }


	public static final class Coord {
        public final long lon;
        public final long lat;

        @JsonCreator
        public Coord(@JsonProperty("lon") long lon, @JsonProperty("lat") long lat){
            this.lon = lon;
            this.lat = lat;
        }
    }

    public static final class Sys {
        public final String country;
        public final long sunrise;
        public final long sunset;

        @JsonCreator
        public Sys(@JsonProperty("country") String country, @JsonProperty("sunrise") long sunrise, @JsonProperty("sunset") long sunset){
            this.country = country;
            this.sunrise = sunrise;
            this.sunset = sunset;
        }
    }

    public static final class Weather {
        public final long id;
        public final String main;
        public final String description;
        public final String icon;

        @JsonCreator
        public Weather(@JsonProperty("id") long id, @JsonProperty("main") String main, @JsonProperty("description") String description, @JsonProperty("icon") String icon){
            this.id = id;
            this.main = main;
            this.description = description;
            this.icon = icon;
        }
    }

    public static final class Main {
        public final double temp;
        public final long humidity;
        public final long pressure;
        public final double temp_min;
        public final double temp_max;

        @JsonCreator
        public Main(@JsonProperty("temp") double temp, @JsonProperty("humidity") long humidity, @JsonProperty("pressure") long pressure, @JsonProperty("temp_min") double temp_min, @JsonProperty("temp_max") double temp_max){
            this.temp = temp;
            this.humidity = humidity;
            this.pressure = pressure;
            this.temp_min = temp_min;
            this.temp_max = temp_max;
        }
    }

    public static final class Wind {
        public final double speed;
        public final double deg;

        @JsonCreator
        public Wind(@JsonProperty("speed") double speed, @JsonProperty("deg") double deg){
            this.speed = speed;
            this.deg = deg;
        }
    }

    public static final class Rain {
        public final long h;

        @JsonCreator
        public Rain(@JsonProperty("3h") long h){
            this.h = h;
        }
    }

    public static final class Clouds {
        public final long all;

        @JsonCreator
        public Clouds(@JsonProperty("all") long all){
            this.all = all;
        }
    }
}
