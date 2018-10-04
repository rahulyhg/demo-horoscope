package in.nareshsharma.horo.api;

import java.io.IOException;
import java.io.StringReader;

import javax.sql.DataSource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import in.nareshsharma.horo.config.HoroConfig;
import in.nareshsharma.horo.dao.HoroscopeRepository;
import in.nareshsharma.horo.pojo.Rss;

@RestController
@RequestMapping("/api")
public class HoroApi {
	
	@Autowired
	public HoroConfig horoConfig;
	
    @Autowired
    DataSource dataSource;

    @Autowired
    HoroscopeRepository horoscopeRepository;
	
	@RequestMapping(value = "/loadrss", method = RequestMethod.GET)
	public String loadRss() {
		return saveRssToDb();
	}
	
	public String saveRssToDb() {
		
		String getResponse = null;

	    try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
	    	HttpGet httpGet = new HttpGet("https://astrobix.com/feeds/rashiaries");
	        HttpResponse httpresponse = httpClient.execute(httpGet);
	        org.apache.http.HttpEntity resEntity = httpresponse.getEntity();
	        getResponse = EntityUtils.toString(resEntity);
	        System.out.println("getResponse: " + getResponse);
	        Rss myRss = xmlToPojo(getResponse);
	        String descStr = myRss.getChannel().getItem().getDescription();
	        System.out.println(descStr);
	        System.out.println(horoscopeRepository.findDescByZodiac("aries"));
	        System.out.println(horoscopeRepository.findDescByZodiac("aries1"));
	        
	    } catch (IOException e) {
			e.printStackTrace();
		}
        
        return getResponse;
	}
	
	public Rss xmlToPojo(String xmlString) {
		Rss obj = null;
		try {
			JAXBContext context = JAXBContext.newInstance(Rss.class);
			Unmarshaller um = context.createUnmarshaller();
			obj = (Rss) um.unmarshal(new StringReader(xmlString));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return obj;
	}
}
