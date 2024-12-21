package mg.itu.matelas.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class TransformationConfig {
    //Test
    @Value("${transformation.margin.percentage}")
    private double percentage;
}
