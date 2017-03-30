package hello;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;



@RestController
public class WeatherService {

	@RequestMapping("/weather")
	public String weather(){
		String out = null;
		try {
			out = new Scanner(new URL("http://api.openweathermap.org/data/2.5/weather?lat=35&lon=139&APPID=63e5b58e8d665a17ea97aa56868ed9c1").openStream(), "UTF-8").useDelimiter("\\A").next();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out;//new WeatherReport(null, null, null, null, null, null, null, 0, 0, null, 0);
	}
	
}
