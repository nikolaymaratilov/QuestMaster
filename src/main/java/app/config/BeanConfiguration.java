package app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@Configuration
public class BeanConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        // Този филтър позволява POST форма с _method=PATCH да се интерпретира правилно
        return new HiddenHttpMethodFilter();
    }

}

//TODO
// [OFF-TOPIC]Вчера когато се упражнявах на упражнението за state man,като направих импл на бутончетата "switch",
//локалхоста ми хвърли грешка,че не разпознава PATCH и трябваше да направя филтър HiddenHttpMethodFilter.Защо става така