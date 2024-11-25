package mg.itu.matelas;

import mg.itu.matelas.utils.Utilitaire;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class MatelasApplicationTests {

	@Test
	void contextLoads() {
		System.out.println(Utilitaire.isWeekend(LocalDate.of(2024,11,22)));
	}

}
