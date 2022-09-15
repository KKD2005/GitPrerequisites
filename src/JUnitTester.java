import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class JUnitTester {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		File file = new File ("test.txt");
		file.createNewFile();
		
        File file1 = new File ("test1.txt");
		file.createNewFile();
		Path p1 = Paths.get("test1.txt");
        try {
            Files.writeString(p1, "examplef", StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
}
	

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	    File myObj = new File("test.txt"); 
	    myObj.delete();
	    File myObj1 = new File("test1.txt"); 
	    myObj1.delete();
	}

	@Test
	public void testBlob() throws IOException {
		Blob junitTest = new Blob ("test.txt");
		Blob junitTest1 = new Blob ("test1.txt");
		File myObj = new File ("objects/da39a3ee5e6b4b0d3255bfef95601890afd80709.txt");
		File myObj1 = new File ("objects/d01259ed4ca82bd32e7e502c1b1452e58a0a7c7a.txt");
		assertTrue(junitTest.getHash().equals("da39a3ee5e6b4b0d3255bfef95601890afd80709"));
		assertTrue(myObj.exists()); //fails because jack created the sha1 file outside the objects folder
		assertTrue(myObj1.exists());
		Path filePath = Path.of("objects/da39a3ee5e6b4b0d3255bfef95601890afd80709.txt");
		assertTrue (Files.readString(filePath).equals(""));
	}
	
	public void testIndex() throws IOException {
		//should all fail except assert false statements because index class is a stencil class
		 File objects = new File ("objects");
	        objects.mkdir();
	        for (File file: Objects.requireNonNull(objects.listFiles())) {
	        	if (!file.isDirectory()) {
	        		file.delete();
	        	}
	        }
	        objects.delete();
	        File myObj = new File("index.txt"); 
	        myObj.delete();
		Index i = new Index();
		i.init();
		File Obj3 = new File ("objects");
		File myObj1 = new File ("index.txt");
		assertTrue(myObj1.exists());
		assertTrue(Obj3.exists());
		i.add("test.txt");
		File myObj2 = new File ("objects/da39a3ee5e6b4b0d3255bfef95601890afd80709.txt");
		assertTrue(myObj2.exists());
		Path filePath = Path.of("index.txt");
		assertTrue (Files.readString(filePath).equals("test : da39a3ee5e6b4b0d3255bfef95601890afd80709"));
		i.add("test1.txt");
		File myObj4 = new File ("objects/d01259ed4ca82bd32e7e502c1b1452e58a0a7c7a.txt");
		assertTrue(myObj4.exists());
		assertTrue (Files.readString(filePath).equals("test : da39a3ee5e6b4b0d3255bfef95601890afd80709\ntest1 : d01259ed4ca82bd32e7e502c1b1452e58a0a7c7a"));
		i.remove("test.txt");
		assertFalse(myObj2.exists());
		assertTrue (Files.readString(filePath).equals("test1 : d01259ed4ca82bd32e7e502c1b1452e58a0a7c7a"));
		i.remove("test1.txt");
		assertFalse(myObj4.exists());		
		assertTrue (Files.readString(filePath).equals(""));
	}
	

}
