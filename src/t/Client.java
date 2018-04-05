package t;

import java.io.InputStream;
import java.util.Map;

import moder.classic.core.ClassicModuleSupport;

public class Client extends ClassicModuleSupport{

	@Override
	public Map receive(Map application) {
		System.out.println(this.getKey()+" receive a msg~");
		return null;
	}

	@Override
	public void uninstall() {
		System.out.println(this.getKey()+"uninstall~");
	}

	@Override
	public boolean initModule(InputStream src) {
		System.out.println(this.getKey()+"install~");
		return true;
	}

	@Override
	public void whenPause() {
		System.out.println(this.getKey()+"pause~");
	}

	@Override
	public void whenReuse() {
		System.out.println(this.getKey()+"reuse~");
	}



}
