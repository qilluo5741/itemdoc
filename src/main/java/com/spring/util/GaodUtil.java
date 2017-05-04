package com.spring.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import com.spring.config.GaodeConfig;

/**
 * 高德地图操作工具
 * @author Administrator
 */
public class GaodUtil {
	/***
	 * 添加表数据   根据坐标定位
	 * @param name 数据名字（一般写小区名字）
	 * @param _locationLongitude  经度
	 * @param _localtionlatitude  纬度
	 * @param map 其他数据
	 * @return  {"info":"OK","infocode":"10000","status":1,"_id":"2"}
	 */
	public static String addTableDateOnLoctypeBylocation(String name,Double _locationLongitude,Double _localtionlatitude,Map<String,String> map){
		
		String url="http://yuntuapi.amap.com/datamanage/data/create";
		int loctype=1;//坐标定位
		String _location=_locationLongitude+","+_localtionlatitude;
		String param="loctype="+loctype+"&key="+GaodeConfig.getAppKey()+"&tableid="+GaodeConfig.getTableid();
		StringBuffer data=new StringBuffer();
		 data.append("{");
		 data.append("\"_location\":"+"\""+_location+"\",");
		Iterator<String> iter = map.keySet().iterator();
		while (iter.hasNext()) {
			String key=iter.next();
			data.append("\""+key+"\":"+"\""+map.get(key)+"\",");
		}
		data.append("\"_name\":"+"\""+name+"\"");
		data.append("}");
		return  HttpUtil.request_post(url,param+"&data="+data );
	}
	/**
	 *通过地址添加数据
	 * @param name 小区名字
	 * @param address 小区地址
	 * @param map 其他参数
	 * @return{"info":"OK","infocode":"10000","status":1,"_id":"3"}
	 */
	public static String addTableDateOnLoctypeByAddress(String name,String address,Map<String,Object> map){
		String url="http://yuntuapi.amap.com/datamanage/data/create";
		int loctype=2;//坐标定位
		String param="loctype="+loctype+"&key="+GaodeConfig.getAppKey()+"&tableid="+GaodeConfig.getTableid();
		StringBuffer data=new StringBuffer();
		 data.append("{");
		 data.append("\"_address\":"+"\""+address+"\",");
		Iterator<String> iter = map.keySet().iterator();
		while (iter.hasNext()) {
			String key=iter.next();
			data.append("\""+key+"\":"+"\""+map.get(key)+"\",");
		}
		data.append("\"_name\":"+"\""+name+"\"");
		data.append("}");
		return  HttpUtil.request_post(url,param+"&data="+data );
	}
	/**
	 * 创建表
	 * @param name表名字 、签名
	 * @return 例如：{"info":"OK","infocode":"10000","status":1,"tableid":"57b6a42f305a2a45ab5178d5"}
	 */
	public static String createTable(String name,String sig){
		String url="http://yuntuapi.amap.com/datamanage/table/create";
		String param="key="+GaodeConfig.getAppKey()+"&name="+name+"&sig="+(sig==null?"":sig);
		String result=HttpUtil.request_post(url, param);
		return result;
	}
	/**
	 * 删除单条数据
	 * @param id 地图ID
	 * @return {"info":"OK","infocode":"10000","status":1,"success":1,"fail":0}
	 */
	public static String deleteData(String id){
		String url="http://yuntuapi.amap.com/datamanage/data/delete";
		String arg="ids="+id+"&key="+GaodeConfig.getAppKey()+"&tableid="+GaodeConfig.getTableid();
		return HttpUtil.request_post(url, arg);
	}
	/**
	 * 删除多条数据
	 * @param ids  id 地图ID
	 * @return
	 */
	public static String deleteData(Set<String> ids){
		String url="http://yuntuapi.amap.com/datamanage/data/delete";
		String arg="key="+GaodeConfig.getAppKey()+"&tableid="+GaodeConfig.getTableid();
		StringBuffer sb_ids=new StringBuffer();
		for (String str: ids) {
			sb_ids.append(str+",");
		}
		sb_ids.replace(sb_ids.lastIndexOf(","), sb_ids.length(), "");
		arg+="&ids="+sb_ids;
		System.out.println(sb_ids);
		return HttpUtil.request_post(url, arg);
	}
	/**
	 * 修改表数据
	 * @param id  高德数据id
	 * @param map 高德自定义列 键值 对   键：自定义列名   值：值 
	 * @return
	 */
	public static String updateTableData(String id,Map<String, Object> map){
		String url="http://yuntuapi.amap.com/datamanage/data/update";
		String param="key="+GaodeConfig.getAppKey()+"&tableid="+GaodeConfig.getTableid();
		StringBuffer sb=new StringBuffer();
		sb.append("{\"_id\":\""+id+"\",");
		for (String key : map.keySet()) {
			sb.append("\""+key+"\":\""+map.get(key)+"\",");
		}
		sb.append("}");
		sb.replace(sb.lastIndexOf(","), sb.lastIndexOf(",")+1, "");
		param+="&data="+sb;
		System.out.println(param);
		return HttpUtil.request_post(url,param);
	}
}
