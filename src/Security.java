import java.util.Random;

public class Security {

	public String generatePassword() {
		String password = "";
		Random rand = new Random();
		
		do {
			password = "";
			for(int x = 0; x < 20; ++x) {
				// only about a 30% chance to get a valid password, 
				// but I'm not worried about optimization right now
				password += (char)rand.nextInt(94) + 33;
			}
		}while(validatePasswordStrength(password));
		
		return password;
		
	}
	
	public boolean validatePasswordStrength(String password) {
		/**
		 * password is 20 characters long
		 * contains at least 3 lowercase character
		 * contains at least 3 uppercase character
		 * contains at least 3 digit
		 * contains at least 3 special character !#$%()*+-.<>=[]{}^@_|\/~
		 * does NOT contain a character repeated 3x in a row
		 */
		byte lower = 3;
		byte upper = 3;
		byte digit = 3;
		byte special = 3;
		byte repeated = 3;
		//again, probably inefficient code, but I'll make it better later
		char character;
		for(int x = 0; x < 20; ++x) {
			character = password.charAt(x);
			if(character >=33 && character <= 47) {
				special--;
			}
			else if(character <= 57) {
				digit--;
			}
			else if(character <= 64) {
				special--;
			}
			else if(character <= 90){
				upper--;
			}
			else if(character <= 96) {
				special--;
			}
			else if(character <= 122) {
				lower--;
			}
			else {
				special--;
			}
		}
		if(lower > 0 || upper > 0 || digit > 0 || special > 0) {
			return false;
		}
		int counter = 1;
		char holder = password.charAt(0);
		for(int x = 1; x < 20; ++x) {
			if(holder == password.charAt(x)) {
				if(++counter == 3) {
					return false;
				}
			}
			else {
				holder = password.charAt(x);
				counter = 1;
			}
		}
		return true;
	}
	
}
