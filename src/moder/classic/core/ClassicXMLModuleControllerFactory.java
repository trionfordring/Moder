package moder.classic.core;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import moder.core.Module;
import moder.core.ModuleController;
import moder.core.ModuleControllerFactory;

public class ClassicXMLModuleControllerFactory implements ModuleControllerFactory {

	@Override
	public ModuleController getModuleController(InputStream in) {
		ModuleController mc = new ClassicModuleController();
		Document doc=null;
		try {
			SAXReader reader=new SAXReader();
			doc = reader.read(in);
			//接下来会开始读取xml文件的内容
			Element root = doc.getRootElement();
			List<Element> list = root.elements(Constants.MODULE);
			for (Element element : list) {	
				String clazz = element.attributeValue(Constants.CLASS);
				String config = element.attributeValue(Constants.CONFIG);
				String key = element.attributeValue(Constants.KEY);
System.out.println("MODULE "+key+":class-"+clazz+" || cfg-"+config);
				//检查key的安全性，如果key出错则直接跳过该模块的装载
				if(key==null||key.equals(""))
					continue;
				//开始构建module
				try {
					Class c = Class.forName(clazz);
					Object obj = c.newInstance();
System.out.println("\t"+c);
					if(obj instanceof Module){
System.out.println("successed in through the interface check~接口检查通过");
						Module module =(Module)obj;
						InputStream cfg =null;
						if(config==null||config.equals("")){
							String src = element.getText();
							if(src!=null||!src.equals(""))
								cfg = new ByteArrayInputStream(src.getBytes());
						}else{
System.out.println("使用src定义的配置文件");
							cfg = new FileInputStream(config);
						}
System.out.println("添加模块完成，调用install()");
						mc.addModule(module, cfg, key);
					}else{
System.out.println("接口检查未通过");
						continue;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
			doc = null;
		}finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.gc();
		}
		return mc;
	}

}
