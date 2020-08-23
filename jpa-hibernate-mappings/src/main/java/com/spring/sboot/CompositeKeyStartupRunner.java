package com.spring.sboot;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.spring.sboot.domains.composite.Account;
import com.spring.sboot.domains.composite.AccountId;
import com.spring.sboot.domains.composite.Employee;
import com.spring.sboot.domains.composite.EmployeeId;
import com.spring.sboot.repositories.composite.AccountRepository;
import com.spring.sboot.repositories.composite.EmployeeRepository;


@org.springframework.core.annotation.Order(value = 6)
@Component
public class CompositeKeyStartupRunner implements CommandLineRunner{

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	
	@Override
	public void run(String... args) throws Exception {

		// TODO Auto-generated method stub
		System.out.println("\n\n=============================================================================================");
		System.out.println("######################    Composite Key demonstration Started    #########################\n\n");

		
		/** ======= `@IdClass` Annotation ======= */
		/** ########################################### */
		
		/** create new accounts*/
        accountRepository.save(new Account("456788", "Current", 4588));
        accountRepository.save(new Account("456789", "Checking", 2500));
        accountRepository.save(new Account("456790", "Saving", 100000));
        accountRepository.save(new Account("456791", "Checking", 45888));
        accountRepository.save(new Account("456792", "Current", 25000));
        accountRepository.save(new Account("456793", "Saving", 1000000));
        accountRepository.save(new Account("456794", "Checking", 45889));
        accountRepository.save(new Account("456795", "Checking", 25001));
        accountRepository.save(new Account("456796", "Saving", 1000001));
        
        
        
		List<Account> accountList = (List<Account>) accountRepository.findAll();
		System.out.println("\n######################    All account details  are below    ##########################");
		accountList.forEach(account -> {
			System.out.println(account.toString());
		});
		System.out.println("---------------------------------------------\n");
		
		
		
        /** fetch accounts by a given type */
        List<Account> accounts = accountRepository.findByAccountType("Checking");
		System.out.println("\n######################    All Accounts by given account type  ##########################");
        accounts.forEach(System.out::println);
		
        		
		
        /** fetch account by composite key */
        Optional<Account> account = accountRepository.findById(new AccountId("456790", "Saving"));
		System.out.println("\n######################    All Accounts by given composite key  ##########################");
        account.ifPresent(System.out::println);
				
		
        
        /** ======= `@EmbeddedId` Annotation ======= */
    	/** ########################################### */
		/** create new employees */
        employeeRepository.save(new Employee(new EmployeeId(100L, 10L), "Chris Evans", "chrisevans@example.com", "123456"));
        employeeRepository.save(new Employee(new EmployeeId(101L, 20L), "Elizabeth Olsen", "elizabetholsen@example.com", "654321"));

        
        
		List<Employee> employeeList = (List<Employee>) employeeRepository.findAll();
		System.out.println("\n######################    All Employee details  are below    ##########################");
		employeeList.forEach(emp -> {
			System.out.println(emp.toString());
		});
		System.out.println("---------------------------------------------\n");
	
		
		
        /** fetch employees by a given department id */
        List<Employee> employees = employeeRepository.findByEmployeeIdDepartmentId(20L);
		System.out.println("\n######################  Employee by department ID   ##########################");
        employees.forEach(System.out::println);

        
        
        /** fetch employee by composite key*/
        Optional<Employee> employee = employeeRepository.findById(new EmployeeId(100L, 10L));
		System.out.println("\n######################  Employee by composite key   ##########################");
        employee.ifPresent(System.out::println);
              
        
		System.out.println("\n\n######################    Composite Key demonstration Ended    #########################");
		System.out.println("===========================================================================================\n\n");

	}
	


}
