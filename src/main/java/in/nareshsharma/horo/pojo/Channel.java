package in.nareshsharma.horo.pojo;

import lombok.Data;

@Data
public class Channel {
	private String webmaster;
    private String pubDate;
    private String title;
    private String description;
    private String link;
    private Item item;
    private String generator;
    private String language;
}
