package Bashiru.com.Loan_Management_System.Controller;


import Bashiru.com.Loan_Management_System.Model.UserData;
import Bashiru.com.Loan_Management_System.Repository.UserRepository;
import Bashiru.com.Loan_Management_System.Service.UserService;
import Bashiru.com.Loan_Management_System.Utils.ResponseCodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// register user with given details
	
	@PostMapping("/app/signup")
	public ResponseEntity<String> registerUser(@Validated @RequestBody UserData user)
	{
		log.info("Register user with given details ");
		if (user.getRole().isBlank() || !(user.getRole().equalsIgnoreCase("ROLE_USER") || user.getRole().equalsIgnoreCase("ROLE_ADMIN"))) {
			log.info(ResponseCodes.INVALID_ROLE.getValue());

			return new ResponseEntity<>(ResponseCodes.INVALID_ROLE.getValue(), HttpStatus.BAD_REQUEST);
		}

		UserData userData = userService.fetchUser(user.getEmail());

		if(userData != null) {

			log.info(ResponseCodes.USER_ALREADY_EXIST.getValue());
			return new ResponseEntity<>(ResponseCodes.USER_ALREADY_EXIST.getValue(),HttpStatus.BAD_REQUEST);
		}

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		log.info("Password Encrypted Successfully");
		user.setRole(user.getRole());

		userService.registerUser(user);

		log.info(ResponseCodes.SIGNUP_SUCCESSFULL.getValue());
		return new ResponseEntity<>(ResponseCodes.SIGNUP_SUCCESSFULL.getValue(),HttpStatus.CREATED);
	}
	
	// first time user login with Email and password and got JWT token 
	
	@GetMapping("/signIn")
	public ResponseEntity<String> getLoggedInCustomerDetailsHandler(Authentication auth) {

		log.info("Signing in with email and password");
		
		UserData customer= userRepository.findByEmail(auth.getName());
		
		if(customer!=null )
		{
			if (customer.isVerified()) {
				log.info(ResponseCodes.LOGIN_SUCCESSFUL.getValue());
				return new ResponseEntity<>(ResponseCodes.LOGIN_SUCCESSFUL.getValue(), HttpStatus.ACCEPTED);
			}
			else {
				log.info(ResponseCodes.VERIFY_EMAIL_LOGIN.getValue());
				return new ResponseEntity<>(ResponseCodes.VERIFY_EMAIL_LOGIN.getValue(), HttpStatus.OK);
			}
		} else {
			log.info(ResponseCodes.SIGN_UP_FIRST.getValue());
			return new ResponseEntity<>(ResponseCodes.SIGN_UP_FIRST.getValue(),HttpStatus.OK);
		}
	}
	
	// Authentication with JWT token 
	
	@GetMapping("/logged-in/user")
	public ResponseEntity<String> LoginUser()
	{
		log.info("Checking the user role ");
		UserData user =  userService.loginUser();

		if (user != null) {
			String message = "Welcome to Yash's API World  : " +user.getFullName() + " role: " + user.getRole();

			log.info("User Role fetched successfully");
			return new ResponseEntity<>(message,HttpStatus.OK);
		}

		log.info(ResponseCodes.LOGIN_NECESSARY.getValue());
		return new ResponseEntity<>(ResponseCodes.LOGIN_NECESSARY.getValue(), HttpStatus.OK);
	}
}
