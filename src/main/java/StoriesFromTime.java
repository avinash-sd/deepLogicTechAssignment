
// Reg no : 2018502006, Name : Avinash S
// This is done by using Java, Eclipse, and TomEEplus
// below json imports are done from apache-tomee-plus-8.0.9 version
// IMPORTANT : if not working from http://<localhost>/getTimeStories, Run this from http://<localhost>/Assignment/getTimeStories

import javax.json.*;
import javax.json.JsonArray;
import javax.ws.rs.*;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

@Path("/")
public class StoriesFromTime {
	
	@GET
	@Path("/getTimeStories")
	@Produces("application/json")
	public JsonArray getStories() throws IOException {
		URL url = new URL("https://time.com/");
	    Scanner sc = new Scanner(url.openStream());
	    
	    JsonArrayBuilder result = Json.createArrayBuilder();
	    
	    while(sc.hasNextLine()) {
	    	
	    	if(sc.nextLine().contains("latest-stories__heading")) {
	    		
	    		while(sc.hasNextLine()) {
	    			
	    			if(sc.nextLine().contains("\"latest-stories__item\"")){
	    				
	    				StringBuilder link = new StringBuilder(sc.nextLine().trim());
	    			
	    				StringBuilder title = new StringBuilder(sc.nextLine().trim());
	    				
	    				JsonObjectBuilder builder = Json.createObjectBuilder();
	    				builder.add("title", title.substring(42, title.length()-5));
	    				builder.add("link", url.toString() + link.substring(10, link.length()-2)).build();
	    				result.add(builder);
	    				
	    			}
	    		}
	    	}
	    }
	    
	    JsonArray res = result.build();
	    return res;
	    
	}
}