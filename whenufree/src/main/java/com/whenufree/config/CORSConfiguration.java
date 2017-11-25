
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.context.annotation.Bean;

@Configuration
public class CORSConfiguration{

    @Bean
    public WebMvcConfigurer corsConfigurer(){
	return new WebMvcConfigurerAdapter(){
	    @Override
	    public void addCorsMappins(CorsRegistry registry){
		registry.addMapping("/**");
	    }
	}
    }
}
