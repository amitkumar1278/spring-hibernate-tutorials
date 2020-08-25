package com.ag.sjh;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ag.sjh.domains.one2one.Address;
import com.ag.sjh.domains.one2one.User;
import com.ag.sjh.repositories.one2one.AddressRepository;
import com.ag.sjh.repositories.one2one.UserRepository;

@Order(value = 3)
@Component
public class One2OneMappingStartupRunner implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AddressRepository adddRepository;

	
	@Override
	public void run(String... args) throws Exception {

		System.out.println("\n\n=============================================================================================");
		System.out.println("######################    One2One Mapping demonstration Started    ##########################\n\n");
		
		/**
		 * Create Single a user with Address
		 */
		Address address3 = new Address("Permanent", "madhapur", "hyderabad", "Telangana", "500000", "India");
		User user3 = new User("Ankit", "an@am.com", "12345", address3);
		address3.setUser(user3);
		user3 = userRepository.save(user3);

		user3 = userRepository.findById(user3.getId()).get();
		System.out.println("\n\n######################    Get user by ID    ##########################");
		System.out.println(user3.toString() + "\n");


		
		/**
		 * Create multiple USer with their Address
		 */
		Address address1 = new Address("permanent", "patna", "patna", "Bihar", "800000", "India");
		User user1 = new User("Amit", "am@am.com", "123", address1);
		address1.setUser(user1);

		Address address2 = new Address("Mailing", "madhapur", "hyderabad", "Telangana", "500000", "India");
		User user2 = new User("Sumit", "su@am.com", "123", address2);
		address2.setUser(user2);

		userRepository.saveAll(Arrays.asList(user1, user2));

		
		
		/**
		 * retrieve all users into list 
		 */
		List<User> lstUsers2 = (List<User>) userRepository.findAll();
		
		System.out.println("\n######################    All Users are below  -> List user  ##########################");

		lstUsers2.forEach(user -> {
			System.out.println("name: "+user.getName()+", email: "+user.getEmail());
			System.out.println(user.toString());
			System.out.println("---------------------------------------------\n");
		});

		
		
		User user4 = userRepository.findByEmail("am@am.com").get(0);
		System.out.println("\n######################    Get user by email    ##########################");
		System.out.println(user4.toString() + "\n");
		
		
		user4.setName("Amit Updated");
		userRepository.save(user4);
		System.out.println("\n######################    User updated, Name : "+user4.getName()+"   ##########################\n");
		
		
		System.out.println("\n######################    Deleting User having id: "+ user3.getId()+" & name: "+ user3.getName()+". ##########################\n");
		userRepository.deleteById(user3.getId());

		
		
		/**
		 * retrieve all users into iterable
		 */
		Iterable<User> lstUsers1 = userRepository.findAll();
		System.out.println(
				"\n\n######################    All Users are below  -> Iterable user  ##########################");
		lstUsers1.forEach(System.out::println);

		
		
		System.out.println("\n\n######################    One2One Mapping demonstration Ended    ##########################");
		System.out.println("===========================================================================================\n\n");

	}

}
