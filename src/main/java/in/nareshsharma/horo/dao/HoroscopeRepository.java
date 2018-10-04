package in.nareshsharma.horo.dao;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Configuration
@Transactional
public interface HoroscopeRepository extends JpaRepository<Horoscope, Integer>{
	@Query("SELECT h.description FROM Horoscope h where h.zodiac = :zod or 1=1") 
	public Iterable<String> findDescByZodiac(@Param("zod") String zodiac);
}
