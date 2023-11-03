package madasi.controlPanel.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FaviconConfiguration implements WebMvcConfigurer {
	
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("/ddd/static/");
    }

//	@Autowired
//	ApplicationContext applicationContext;
//
//	@Bean
//	public SimpleUrlHandlerMapping faviconHandlerMapping() {
//		SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
//		mapping.setOrder(Integer.MIN_VALUE);
//		mapping.setUrlMap(Collections.singletonMap("/ddd/favicon.ico", faviconRequestHandler()));
//		return mapping;
//	}
//
//	@Bean
//	protected ResourceHttpRequestHandler faviconRequestHandler() {
//		ResourceHttpRequestHandler requestHandler = new ResourceHttpRequestHandler();
//		requestHandler.setLocations(Arrays.<Resource>asList(new ClassPathResource("/")));
//		return requestHandler;
//	}
}