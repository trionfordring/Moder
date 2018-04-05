package moder.core;

import java.io.InputStream;
import java.util.Map;

/**
 * 本接口为模块接口，负责管理一个模块
* @author Fordring
* @date 2018年3月24日 下午2:06:46 
*
 */
public interface Module {
	/**
	 * 获得该模块的状态，包括：模块不可用(-1)初始化中(0),运行中(1),pause(2)
	 * @return
	 */
	int getCondition();
	/**
	 * 向控制器提交请求
	 * @param 申请Application
	 * @return 结果Result
	 */
	Map<Object,Object> apply(Map<Object,Object> application,String to);
	/**
	 * 接收来自其他模块的请求
	 * @param application
	 * @return 处理的结果
	 */
	Map<Object,Object> receive(Map<Object,Object> application);
	/**
	 * 获取本module的key
	 * @return
	 */
	String getKey();
	/**
	 * 卸载模块
	 */
	void uninstall();
	/**
	 * 加载模块
	 * @return 是否加载成功
	 */
	boolean install(InputStream src,ModuleController mc,String key);
	/**
	 * 暂停本模块的使用
	 */
	void pause();
	/**
	 * 继续使用本模块
	 */
	void reuse();
}
