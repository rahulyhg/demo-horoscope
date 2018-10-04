package in.nareshsharma.horo.dao;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "horoscope")
public class Horoscope {
	@Id
	Integer id;
	String zodiac;
	String description;
}
