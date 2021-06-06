import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class TestUtility {
public static void main(String[] args) {
	PasswordEncoder encoder= new BCryptPasswordEncoder();
	for(int i=0;i<10;i++)
	{
		System.out.println(encoder.encode("pradeep"));	
	}
}
}
