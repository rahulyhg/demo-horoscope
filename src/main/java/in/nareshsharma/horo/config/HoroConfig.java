package in.nareshsharma.horo.config;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@ConfigurationProperties(prefix = "horo")
@Component
@Data
public class HoroConfig {
	public String user, pass;
	public Map<String, String> url;
}
