import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class JUnitTester {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		File file = new File ("test.txt");
		file.createNewFile();
		Path p = Paths.get("test.txt");
        try {
            Files.writeString(p, "example", StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	    File myObj = new File("test.txt"); 
	    myObj.delete();

	}

	@Test
	public void blobConstructorTestFileName() throws IOException {
		Blob junitTest = new Blob ("test.txt");
		File myObj = new File ("c3499c2729730a7f807efb8676a92dcb6f8a3f8f.txt");
		assertTrue(myObj.exists());
	}
	
	public void blobConstructorTestContents() throws IOException {
		Path filePath = Path.of("c3499c2729730a7f807efb8676a92dcb6f8a3f8f.txt");
		assertTrue (Files.readString(filePath).equals("example"));
	}
	
	public void getHash() throws IOException {
		Blob junitTest = new Blob ("test.txt");
		assertTrue(junitTest.getHash().equals("c3499c2729730a7f807efb8676a92dcb6f8a3f8f"));
	}

}
