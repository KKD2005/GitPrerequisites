import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Tree {
	ArrayList <String> map;
	String body; 
	String Sha1;
	public Tree (ArrayList<String> input) throws IOException {
		map = input;
		for (int i = 0; i<map.size(); i++) {
			body = body + map.get(i) + "\n";
		}
		if (body.length()!=0) {
		body = body.substring(0,body.length()-1);
		}
		 try {
	            // getInstance() method is called with algorithm SHA-1
	            MessageDigest md = MessageDigest.getInstance("SHA-1");
	 
	            // digest() method is called
	            // to calculate message digest of the input string
	            // returned as array of byte
	            byte[] messageDigest = md.digest(Sha1.getBytes());
	 
	            // Convert byte array into signum representation
	            BigInteger no = new BigInteger(1, messageDigest);
	 
	            // Convert message digest into hex value
	            Sha1 = no.toString(16);
	 
	            // Add preceding 0s to make it 32 bit
	            while (Sha1.length() < 32) {
	                Sha1 = "0" + Sha1;
	            }
	 
	            
	            
	        }
	 
	        // For specifying wrong message digest algorithms
	        catch (NoSuchAlgorithmException e) {
	            throw new RuntimeException(e);
	        }
		 File objects = new File ("objects");
         File f2=new File ("objects/"+Sha1+".txt");
         objects.mkdir();
         f2.createNewFile();
         Path p = Paths.get("objects/"+Sha1+".txt");
         try {
             Files.writeString(p, "body", StandardCharsets.ISO_8859_1);
         } catch (IOException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }
		
	}
}
