package in.nareshsharma.horo.pojo;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement(name="rss")
public class Rss {
    private Channel channel;
}
