package com.wangguang.model.utils;

import com.google.common.collect.Lists;
import org.dozer.DozerBeanMapper;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 简单封装Dozer, 实现深度转换Bean<->Bean的Mapper.实现:
 * <p/>
 * 1. 持有Mapper的单例. 2. 返回值类型转换. 3. 批量转换Collection中的所有对象. 4.
 * 区分创建新的B对象与将对象A值复制到已存在的B对象两种函数.
 * 
 * @author calvin
 */
public class BeanMapper {

	/**
	 * 持有Dozer单例, 避免重复创建DozerMapper消耗资源.
	 */
	private static DozerBeanMapper dozer = new DozerBeanMapper();

	public static void map2map(Map source, Map destination) {
		for (Iterator i = source.keySet().iterator(); i.hasNext();) {
			String key = (String) i.next();
			if (key != null) {
				String value[] = (String[]) source.get(key);
				if (value != null && value.length > 0) {
					destination.put(key, value[0]);
				}
			}
		}
	}

	/**
	 * 基于Dozer转换对象的类型.
	 */
	public static <T> T map(Object source, Class<T> destinationClass) {
		return dozer.map(source, destinationClass);
	}

	/**
	 * 基于Dozer转换Collection中对象的类型.
	 */
	public static <T> List<T> mapList(Collection sourceList, Class<T> destinationClass) {
		List<T> destinationList = Lists.newArrayList();
		for (Object sourceObject : sourceList) {
			T destinationObject = dozer.map(sourceObject, destinationClass);
			destinationList.add(destinationObject);
		}
		return destinationList;
	}

	/**
	 * 基于Dozer将对象A的值拷贝到对象B中.
	 */
	public static void copy(Object source, Object destinationObject) {
		dozer.map(source, destinationObject);
	}
}