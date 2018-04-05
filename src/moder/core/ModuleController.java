package moder.core;

import java.io.InputStream;
import java.util.Map;


/**
 * 本接口为中央控制器
 * 负责包括：初始化，模块申请
* @author Fordring
* @date 2018年3月24日 下午1:57:38 
*
 */
public interface ModuleController {
//	/**
//	 * 根据你的需要申请一个模块，需要传入参数key，key可以在配置文件中设置。<br/>
//	 * 模块需要在配置文件中进行配置后方可申请。<br/>
//	 * 如果传入的key在配置文件中无法找到，或无法请求到该Module，则会返回null。
//	 * @param key
//	 * @return Module
//	 */
//	Module getModule(String key);
//	/**
//	 * 流读入初始化信息，并且加载所有配置文件中的模块，并进行初始化。每一个模块会获得一条线程，<br/>
//	 * 模块在初始化结束后,就必须结束分配给其初始化的线程，否则该模块将始终处于初始化中的状态，无法被申请到。
//	 * @param in
//	 */
//	void init(InputStream in);
	
	/**
	 * 发送一封请求到指定的模块
	 * @param app
	 * @return
	 */
	Map<Object,Object> sendApplication(Map<Object,Object> app);
	/**
	 * 添加一个模块
	 * @param module
	 */
	void addModule(Module module,InputStream src,String key);
	/**
	 * 移除一个模块
	 * @param key
	 */
	void removeModule(String key);
	/**
	 * 使模块不可用，无法被访问到
	 * @param key
	 */
	void pauseModule(String key);
	/**
	 * 使模块可用，与pauseModule相对应
	 * @param key
	 */
	void reuseModule(String key);
}
