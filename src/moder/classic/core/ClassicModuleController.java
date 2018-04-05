package moder.classic.core;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import moder.core.Module;
import moder.core.ModuleController;


public class ClassicModuleController implements ModuleController{
	private Map<String,Module> modules;
	public ClassicModuleController(Map<String,Module> modules) {
		this.modules = modules;
	}
	public ClassicModuleController() {
		this.modules = new HashMap<String,Module>();
	}
	@Override
	public Map<Object,Object> sendApplication(Map<Object,Object> app) {
		if(app==null)
			return null;
		String to = (String) app.get(Constants.TO);
		if(to==null||to.equals(""))
			return null;
		Module mod = modules.get(to);
		if(mod==null||mod.getCondition()!=1)
			return null;
		return mod.receive(app);
	}

	@Override
	public void addModule(Module module,InputStream src,String key) {
		if(module==null||module.getCondition()!=0)
			return;
		module.install(src, this,key);
		modules.put(module.getKey(), module);
	}

	@Override
	public void removeModule(String key) {
		Module mod = modules.get(key);
		if(mod!=null){
			mod.uninstall();
			modules.remove(key);
			mod = null;
		}
	}

	@Override
	public void pauseModule(String key) {
		Module mod = this.modules.get(key);
		if(mod==null||mod.getCondition()!=1)
			return;
		mod.pause();
	}

	@Override
	public void reuseModule(String key) {
		Module mod = this.modules.get(key);
		if(mod==null||mod.getCondition()!=2)
			return;
		mod.reuse();
	}

}
