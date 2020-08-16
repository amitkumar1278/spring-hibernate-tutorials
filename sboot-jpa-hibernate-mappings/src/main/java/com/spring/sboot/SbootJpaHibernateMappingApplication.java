package com.spring.sboot;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.spring.sboot.domains.one2one.Address;
import com.spring.sboot.domains.one2one.User;
import com.spring.sboot.repositories.one2one.AddressRepository;
import com.spring.sboot.repositories.one2one.UserRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackages = { "com.spring.sboot.repositories" }) 
@EntityScan(basePackages = { "com.spring.sboot.domains" })
public class SbootJpaHibernateMappingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbootJpaHibernateMappingApplication.class, args);
	}

	/**
	 * testing one to one mapping
	 */
	@Bean
	public CommandLineRunner one2oneMappinngDemo(UserRepository userRepository, AddressRepository adddRepository) {
		return args -> {

			/**
			 * Create new USer with Addresses
			 */
			Address address1 = new Address("permanent", "patna", "patna", "Bihar", "800000", "India");
			User user1 = new User("Amit","am@am.com", "123", address1);
			address1.setUser(user1);
			//			user1 = userRepository.save(user1);

			Address address2 = new Address("Mailing", "madhapur", "hyderabad", "Telangana", "500000", "India");
			User user2 = new User("Sumit","su@am.com", "123", address2);
			address2.setUser(user2);
			//			user2 = userRepository.save(user2);

			userRepository.saveAll(Arrays.asList(user1, user2));

			/**
			 * retrieve all users
			 */
			Iterable<User> users1 = userRepository.findAll();
			List<User> users  = (List<User>) userRepository.findAll();
			//			Optional<User> u1 = userRepository.findById(user1.getId());
			//			System.out.println(u1.toString());

			/**
			 * print all users
			 */
			System.out.println("\n\n\n\n All Users are below ");
			System.out.println("################################################");

			users.forEach(user -> {
				System.out.println("---------------------------------------------");	
				System.out.println(user.getName());	
				System.out.println(user.getEmail());	
				System.out.println(user.toString());
			});
			
			System.out.println("\n\n################################################");
			users1.forEach(System.out::println);

			System.out.println("\n\n\n\n ");
		};
	}
}
