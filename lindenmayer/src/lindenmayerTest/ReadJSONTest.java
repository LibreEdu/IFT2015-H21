package lindenmayerTest;

import java.io.IOException;

import lindenmayer.ReadJSON;

public class ReadJSONTest {

	private LSystemString ls;
	private TortueVirtuelle turtle;
	private ReadJSON readJSON;
	
	public void test() throws IOException {
        ls = new LSystemString();
        turtle = new TortueVirtuelle();
        readJSON = new ReadJSON();
        readJSON.readFile("./test/herbe.json", turtle, ls);
	}
	
}
