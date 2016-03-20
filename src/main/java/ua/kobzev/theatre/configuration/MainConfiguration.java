package ua.kobzev.theatre.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import ua.kobzev.theatre.enums.Repositories;
import ua.kobzev.theatre.repository.*;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "ua.kobzev.theatre")
@PropertySource("WEB-INF/jdbc.properties")
@Import({DiscountConfiguration.class, Jdbc.class})
public class MainConfiguration {

	@Autowired
	private AuditoriumsReader auditoriumsReader;

	@Autowired
	private Environment environment;

//	@Bean(name="HelloWorld")
//	public ViewResolver viewResolver() {
//		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//		viewResolver.setViewClass(JstlView.class);
//		viewResolver.setPrefix("/WEB-INF/views/");
//		viewResolver.setSuffix(".html");
//
//		return viewResolver;
//	}

	//start Thymeleaf specific configuration
	@Bean(name ="templateResolver")
	public ServletContextTemplateResolver getTemplateResolver() {
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
		templateResolver.setPrefix("/WEB-INF/views/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("XHTML");
		return templateResolver;
	}
	@Bean(name ="templateEngine")
	public SpringTemplateEngine getTemplateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(getTemplateResolver());
		return templateEngine;
	}
	@Bean(name="viewResolver")
	public ThymeleafViewResolver getViewResolver(){
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(getTemplateEngine());
		return viewResolver;
	}

	@Bean
	public AuditoriumRepository auditoriumRepository() {
		Repositories repositories = Repositories.valueOf(environment.getProperty("version.repositories"));

		switch (repositories) {
			case INMEMORY:
				ua.kobzev.theatre.repository.impl.inmemory.AuditoriumRepositoryImpl auditoriumRepository =
						new ua.kobzev.theatre.repository.impl.inmemory.AuditoriumRepositoryImpl();
				auditoriumRepository.setAuditoriumList(auditoriumsReader.readAuditoriumsFromProperties());

				return auditoriumRepository;
			case MYBATIS:
				return new ua.kobzev.theatre.repository.impl.mybatis.AuditoriumRepositoryImpl();
			case HIBERNATE:
				return new ua.kobzev.theatre.repository.impl.hibernate.AuditoriumRepositoryImpl();
			case JDBCTEMPLATE:
				return new ua.kobzev.theatre.repository.impl.jdbctemplate.AuditoriumRepositoryImpl();
			default:
				return null;
		}
	}

	@Bean
	public AssignedEventRepository assignedEventRepository(){
		Repositories repositories = Repositories.valueOf(environment.getProperty("version.repositories"));

		switch (repositories) {
			case INMEMORY:
				return new ua.kobzev.theatre.repository.impl.inmemory.AssignedEventRepositoryImpl();
			case MYBATIS:
				return new ua.kobzev.theatre.repository.impl.mybatis.AssignedEventRepositoryImpl();
			case HIBERNATE:
				return new ua.kobzev.theatre.repository.impl.hibernate.AssignedEventRepositoryImpl();
			case JDBCTEMPLATE:
				return new ua.kobzev.theatre.repository.impl.jdbctemplate.AssignedEventRepositoryImpl();
			default:
				return null;
		}
	}

	@Bean
	public EventRepository eventRepository(){
		Repositories repositories = Repositories.valueOf(environment.getProperty("version.repositories"));

		switch (repositories) {
			case INMEMORY:
				return new ua.kobzev.theatre.repository.impl.inmemory.EventRepositoryImpl();
			case MYBATIS:
				return new ua.kobzev.theatre.repository.impl.mybatis.EventRepositoryImpl();
			case HIBERNATE:
				return new ua.kobzev.theatre.repository.impl.hibernate.EventRepositoryImpl();
			case JDBCTEMPLATE:
				return new ua.kobzev.theatre.repository.impl.jdbctemplate.EventRepositoryImpl();
			default:
				return null;
		}
	}

	@Bean
	public TicketRepository ticketRepository(){
		Repositories repositories = Repositories.valueOf(environment.getProperty("version.repositories"));

		switch (repositories) {
			case INMEMORY:
				return new ua.kobzev.theatre.repository.impl.inmemory.TicketRepositoryImpl();
			case MYBATIS:
				return new ua.kobzev.theatre.repository.impl.mybatis.TicketRepositoryImpl();
			case HIBERNATE:
				return new ua.kobzev.theatre.repository.impl.hibernate.TicketRepositoryImpl();
			case JDBCTEMPLATE:
				return new ua.kobzev.theatre.repository.impl.jdbctemplate.TicketRepositoryImpl();
			default:
				return null;
		}
	}

	@Bean
	public UserRepository userRepository(){
		Repositories repositories = Repositories.valueOf(environment.getProperty("version.repositories"));

		switch (repositories) {
			case INMEMORY:
				return new ua.kobzev.theatre.repository.impl.inmemory.UserRepositoryImpl();
			case MYBATIS:
				return new ua.kobzev.theatre.repository.impl.mybatis.UserRepositoryImpl();
			case HIBERNATE:
				return new ua.kobzev.theatre.repository.impl.hibernate.UserRepositoryImpl();
			case JDBCTEMPLATE:
				return new ua.kobzev.theatre.repository.impl.jdbctemplate.UserRepositoryImpl();
			default:
				return null;
		}
	}
}
