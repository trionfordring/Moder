package moder.classic.xml;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import moder.classic.core.ClassicModuleController;
import moder.core.Module;
import moder.core.ModuleController;
/**
 * 本类为xml文件读取类
* @author Fordring
* @date 2018年3月25日 上午7:31:18 
*
 */
public class XMLReader {
	private ModuleController mc = new ClassicModuleController();
	
	
	/**
	 * 构造方法会直接解析xml文件，并进行封装
	 * @param in
	 */
	public XMLReader(InputStream in) {
		Document doc=null;
		try {
			SAXReader reader=new SAXReader();
			doc = reader.read(in);
			//接下来会开始读取xml文件的内容
			Element root = doc.getRootElement();
			List<Element> list = root.elements("module");
			for (Element element : list) {	
				String clazz = element.attributeValue("class");
				String config = element.attributeValue("config");
				String key = element.attributeValue("key");
				//检查key的安全性，如果key出错则直接跳过该模块的装载
				if(key==null||key.equals(""))
					continue;
				//开始构建module
				try {
					Class c = Class.forName(clazz);
					if(c.isAssignableFrom(Module.class)){
						Module module =(Module) c.newInstance();
						InputStream cfg =null;
						if(config!=null||!config.equals("")){
							cfg = new FileInputStream(config);
						}else{
							String src = element.getText();
							if(src!=null||!src.equals(""))
								cfg = new ByteArrayInputStream(src.getBytes());
						}
						mc.addModule(module, cfg, key);
					}else{
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
	}
}
