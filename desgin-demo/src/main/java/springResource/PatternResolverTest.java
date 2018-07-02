package springResource;

import org.junit.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import java.io.IOException;

public class PatternResolverTest {

    @Test
    public void test() throws IOException {

        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        Resource resources[] = resolver.getResources("classpath:com/**/**/*.xml");

        for(Resource resource : resources){
            System.out.println("111: "+resource.getDescription());
        }

    }
}
