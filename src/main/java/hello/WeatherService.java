package hello;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Controller
public class WeatherService {
	
	public final String WeatherAPI = "http://api.openweathermap.org/data/2.5/weather";
	public final String APPID = "63e5b58e8d665a17ea97aa56868ed9c1";

	@RequestMapping(value = "/weather" , method = RequestMethod.GET, produces = "text/html")
	public String weather(@RequestParam(value="search", required=false) String search, Model model){
		
		if(search == null){
			UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext()
					   .getAuthentication().getPrincipal();
			   
			model.addAttribute("user", userDetails.getUsername());
			return "weather";
		}else{
			return "redirect:weather/" + search;
		}
			
	}
	
	@RequestMapping(value="weather/{location}", method=RequestMethod.GET, produces = "text/html")
	public String Search(@PathVariable String location, Model model){
		
		String search = WeatherAPI + "?q=" + location + "&APPID=" + APPID;
		String out = null;
		
		try {
			out = new Scanner(new URL(search).openStream(),"UTF-8").useDelimiter("\\A").next();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "redirect:searcherror";
		}
		
		WeatherReport report = MapToObject(out);
		
		model.addAttribute("location", location);
		model.addAttribute("desc", report.weather[0].description);
		model.addAttribute("temp", (int)report.main.temp - (int)273);
		model.addAttribute("humid", (int)report.main.humidity);
		
		return "searchresult";
	}
	
	@RequestMapping(value="weather/{location}.json", method=RequestMethod.GET, produces = "application/json")
	public @ResponseBody String searchXML(@PathVariable String location){
		
		String search = WeatherAPI + "?q=" + location + "&APPID=" + APPID;
		String out = null;
		
		try {
			out = new Scanner(new URL(search).openStream(),"UTF-8").useDelimiter("\\A").next();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error : resource failed to produce a resonse";
		}
		
		return out;
	}
	
	@RequestMapping("weather/save")
	public String save(@RequestParam(value="location", required=false) String location){
		System.out.println("Saving location!!!!!!!!! " + location);
		return "redirect:/weather";
	}
	
	public WeatherReport MapToObject(String out){
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		WeatherReport report = null;
		
		try {
			report = mapper.readValue(out, WeatherReport.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return report;
		
	}
	
	@RequestMapping("weather/searcherror")
	public String WeaterSearchError(){
		return "searcherror";
	}
	
}
