import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class Main implements WebApplicationInitializer{

    public void onStartup(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext configWebApplicationContext = new
                AnnotationConfigWebApplicationContext();
        configWebApplicationContext.register(ServletConf.class);
        configWebApplicationContext.setServletContext(servletContext);

        ServletRegistration.Dynamic dynamic = servletContext.addServlet(
                "index", new DispatcherServlet(configWebApplicationContext));
        dynamic.setLoadOnStartup(1);
        dynamic.addMapping("/");
    }
}
