package t;

import java.io.InputStream;

import moder.classic.core.ClassicXMLModuleControllerFactory;
import moder.core.ModuleController;
import moder.core.ModuleControllerFactory;

public class m {

	public static void main(String[] args) throws Exception {
		ModuleControllerFactory mcf = new ClassicXMLModuleControllerFactory();
		InputStream in = Thread.currentThread().getContextClassLoader().getSystemClassLoader().getResource("config.xml").openStream();
		ModuleController mc = mcf.getModuleController(in);
	}

}
