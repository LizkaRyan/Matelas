package mg.itu.matelas;

import mg.itu.matelas.entity.Matelas;
import mg.itu.matelas.utils.Utilitaire;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class MatelasApplicationTests {

	@Test
	void contextLoads() {
		Matelas matelas=new Matelas(1l,1000000d,10);
		System.out.println(matelas.getPrixUnitaire());
	}

}
