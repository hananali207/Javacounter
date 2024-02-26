package accidentpack;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Program4Test {

	 private Program4 program;


	    String csvFile = "/Users/hananali/eclipse-workspace/Project1DS/src/accidentpack/accidents_small_sample.csv";

	    @BeforeEach
	    void setUp() throws IOException {
	        program = new Program4(); 
	    }

	    @Test
	    void testGetMinCountersNeeded() throws IOException {
	        AccidentQueue queue = Program4.constructQueue("CA", "Los Angeles");
	        int minCountersNeeded = program.getMinCountersNeeded("CA", "Los Angeles", queue);
	        assertEquals(1, minCountersNeeded);
	    }
	    
	    @Test
	    void testConstructQueue() throws IOException {
	        AccidentQueue queue = program.constructQueue("CA", "Los Angeles");
	        
	        assertNotNull(queue);
	        
	        assertEquals(26906, queue.size()); 
	    }

}
