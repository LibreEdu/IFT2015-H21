package lindenmayerTest;

import java.io.IOException;

import lindenmayer.ReadJSON;

public class ReadJSONTest {

	private LSystemString ls;
	private TortueVirtuelle turtle;
	private ReadJSON readJSON;
	
	ReadJSONTest() {
        ls = new LSystemString();
        turtle = new TortueVirtuelle();
        readJSON = new ReadJSON();	
	}
	
	public void test() throws IOException {
        readJSON.readFile("./test/herbe.json", turtle, ls);
	}
	
}
