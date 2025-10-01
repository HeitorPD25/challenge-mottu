package br.com.fiap.challenge_mottu;

// import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.context.annotation.Bean;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	// Método temporário para gerar senhas - execute a aplicação normalmente
    // @Bean
    // public CommandLineRunner generatePasswords() {
    //     return args -> {
    //         BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    //         System.out.println("=== SENHAS BCRYPT GERADAS ===");
    //         System.out.println("Senha 'Admin123!': " + encoder.encode("Admin123!"));
    //         System.out.println("Senha 'User123!': " + encoder.encode("User123!"));
    //         System.out.println("=== COPIE ESTAS SENHAS PARA O data.sql ===");
    //     };
	// }
}
