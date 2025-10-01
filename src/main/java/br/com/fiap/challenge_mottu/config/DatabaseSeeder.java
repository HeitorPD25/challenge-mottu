package br.com.fiap.challenge_mottu.config;

import br.com.fiap.challenge_mottu.model.entity.*;
import br.com.fiap.challenge_mottu.model.enums.Model;
import br.com.fiap.challenge_mottu.model.enums.Status;
import br.com.fiap.challenge_mottu.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MotorcycleRepository motorcycleRepository;

    @Autowired
    private AdressRepository adressRepository;

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private PatioRepository patioRepository;

    // NÃO usa @Autowired aqui - vamos criar uma instância manualmente
    private BCryptPasswordEncoder passwordEncoder;

    public DatabaseSeeder() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        
        // Verificar se já existem dados
        if (userRepository.count() == 0) {
            System.out.println("=== INICIANDO DATABASE SEEDER ===");
            
            // Ordem CRÍTICA devido aos relacionamentos
            seedUsers();
            seedAdresses();
            seedAreas();
            seedPatios();
            seedMotorcycles();
            
            System.out.println("=== DATABASE SEEDER FINALIZADO COM SUCESSO ===");
        } else {
            System.out.println("=== DADOS JÁ EXISTEM NO BANCO ===");
        }
    }

    private void seedUsers() {
        
        System.out.println("Inserindo usuários...");
    
        User admin = User.builder()
                .email("admin@email.com")
                .name("Administrador")
                .password("Admin123!") // Texto simples que passa na validação
                .role("ADMIN")
                .build();
        userRepository.save(admin);

        User user = User.builder()
                .email("user@email.com")
                .name("Usuario Comum")
                .password("User123!")
                .role("USER")
                .build();
        userRepository.save(user);

        User moto1 = User.builder()
                .email("moto@email.com")
                .name("Motoboy Teste")
                .password("Moto123!")
                .role("MOTORCYCLE")
                .build();
        userRepository.save(moto1);

        User moto2 = User.builder()
                .email("joao@email.com")
                .name("Joao Motoboy")
                .password("Joao123!")
                .role("MOTORCYCLE")
                .build();
        userRepository.save(moto2);

        System.out.println("✓ 4 usuários inseridos");
    }

    // ... o resto do código permanece igual
    private void seedAdresses() {
        System.out.println("Inserindo endereços...");
        
        Adress adress1 = Adress.builder()
                .street("Rua Principal")
                .number("123")
                .neighborhood("Centro")
                .city("Sao Paulo")
                .state("Sao Paulo")
                .zipCode("01234-567")
                .complement("Proximo ao metro")
                .build();
        adressRepository.save(adress1);

        Adress adress2 = Adress.builder()
                .street("Avenida Secundaria")
                .number("456")
                .neighborhood("Jardins")
                .city("Sao Paulo")
                .state("Sao Paulo")
                .zipCode("04567-890")
                .complement("Edificio Commercial")
                .build();
        adressRepository.save(adress2);

        System.out.println("✓ 2 endereços inseridos");
    }

    private void seedAreas() {
        System.out.println("Inserindo áreas...");
        
        Area area1 = Area.builder()
                .name("Zona Norte")
                .build();
        areaRepository.save(area1);

        Area area2 = Area.builder()
                .name("Centro Expandido")
                .build();
        areaRepository.save(area2);

        Area area3 = Area.builder()
                .name("Zona Sul")
                .build();
        areaRepository.save(area3);

        System.out.println("✓ 3 áreas inseridas");
    }

    private void seedPatios() {
        System.out.println("Inserindo pátios...");
        
        // Buscar usuário admin
        User admin = userRepository.findByEmail("admin@email.com")
                .orElseThrow(() -> new RuntimeException("Admin não encontrado"));
        
        // Buscar endereços
        Adress adress1 = adressRepository.findAll().get(0);
        Adress adress2 = adressRepository.findAll().get(1);
        
        // Buscar áreas
        Area area1 = areaRepository.findAll().get(0);
        Area area2 = areaRepository.findAll().get(1);
        Area area3 = areaRepository.findAll().get(2);

        Patio patio1 = Patio.builder()
                .name("Patio Centro")
                .adress(adress1)
                .user(admin)
                .areas(Arrays.asList(area1, area2))
                .build();
        patioRepository.save(patio1);

        Patio patio2 = Patio.builder()
                .name("Patio Zona Sul")
                .adress(adress2)
                .user(admin)
                .areas(Arrays.asList(area3))
                .build();
        patioRepository.save(patio2);

        System.out.println("✓ 2 pátios inseridos");
    }

    private void seedMotorcycles() {
        System.out.println("Inserindo motocicletas...");
        
        // Buscar áreas
        Area area1 = areaRepository.findAll().get(0);
        Area area2 = areaRepository.findAll().get(1);
        Area area3 = areaRepository.findAll().get(2);

        Motorcycle moto1 = Motorcycle.builder()
                .plate("ABC-1234")
                .color("Vermelha")
                .model(Model.MottuSport)
                .status(Status.Livre)
                .area(area1)
                .build();
        motorcycleRepository.save(moto1);

        Motorcycle moto2 = Motorcycle.builder()
                .plate("XYZ9K87")
                .color("Preta")
                .model(Model.MottuE)
                .status(Status.Livre)
                .area(area2)
                .build();
        motorcycleRepository.save(moto2);

        Motorcycle moto3 = Motorcycle.builder()
                .plate("DEF-5678")
                .color("Azul")
                .model(Model.MottuPop)
                .status(Status.Alugado)
                .area(area3)
                .build();
        motorcycleRepository.save(moto3);

        System.out.println("✓ 3 motocicletas inseridas");
    }
}