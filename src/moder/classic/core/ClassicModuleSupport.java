package moder.classic.core;

import java.io.InputStream;
import java.util.Map;

import moder.core.Module;
import moder.core.ModuleController;

public abstract class ClassicModuleSupport implements Module{
	private int condition;
	private ModuleController mc;
	private String key;
	@Override
	public int getCondition() {
		return condition;
	}

	@Override
	public Map<Object,Object> apply(Map<Object,Object> application,String to) {
		application.put(Constants.FROM, this.getKey());
		application.put(Constants.TO, to);
		return this.mc.sendApplication(application);
	}

	@Override
	public String getKey() {
		return this.key;
	}

	@Override
	public boolean install(InputStream src,ModuleController mc,String key) {
		this.condition = 0;
		this.key= key;
		this.mc = mc;
		boolean res =this.initModule(src);
		if(res){
			this.condition = 1;
		}else{
			this.condition = -1;
		}
		return res;
	}
	@Override
	public void pause() {
		if(this.condition==1){
			this.condition=2;
			this.whenPause();
		}
	}
	@Override
	public void reuse() {
		if(this.condition==2){
			this.whenReuse();
			this.condition=1;
		}
	}
	/**
	 * 初始化模块时会调用的方法，需要用户实现
	 * @param src
	 * @return
	 */
	public abstract boolean initModule(InputStream src);
	/**
	 * 当模块本暂停时会调用的方法
	 */
	public abstract void whenPause();
	/**
	 * 当模块复用时调用的方法
	 */
	public abstract void whenReuse();
}
