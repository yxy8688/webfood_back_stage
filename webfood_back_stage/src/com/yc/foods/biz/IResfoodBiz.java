package com.yc.foods.biz;

import java.util.List;
import java.util.Map;

import com.yc.bean.Resfood;


public interface IResfoodBiz {
	/**
	 * 添加食品信息
	 * @param fname
	 * @param normprice
	 * @param realprice
	 * @param detail
	 * @param fphoto
	 * @return
	 */
	public int addFoods(String fname,Double normprice,Double realprice,String detail,String fphoto);

	/**
	 * 分页查询
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws Exception 
	 */
	public List<Resfood> findResfood(Integer pageNo,Integer pageSize) throws Exception;
	
	/**
	 * 通过id查询菜名
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws Exception 
	 */
	public String find(Integer fid) throws Exception;
	
	/**
	 * 获取总数
	 * @param tid
	 * @return
	 */
	public int getTotal(Integer fid);
	/**
	 * 修改食品信息
	 * @param fname
	 * @param normprice
	 * @param realprice
	 * @param detail
	 * @param fphoto
	 * @param fid
	 * @return
	 */
	public int updateFoods(String fname,Double normprice,Double realprice,String detail,String fphoto,Integer fid);

	/**
	 * 删除商品信息
	 * @param tid
	 * @return
	 */
	public int deleteFoods(String fid);
	/**
	 * 通过搜索框查询
	 * @param params
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws Exception 
	 */
	public List<Resfood> findFoods(Map<String,String> params,Integer pageNo,Integer pageSize) throws Exception;
	
	/**
	 * 查询所有的菜品id
	 * @return
	 */
	public List<String> findFid(); 
}
