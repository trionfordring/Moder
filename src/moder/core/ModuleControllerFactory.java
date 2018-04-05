package moder.core;

import java.io.InputStream;

public interface ModuleControllerFactory {
	ModuleController getModuleController(InputStream in);
}
