package com.yc.foods.biz;

import java.util.List;

import com.yc.bean.Active;

public interface IActive {

	/**
	 * 查询所有
	 * @return
	 * @throws Exception 
	 */
		public List<Active> findAll() throws Exception;
	/**
	 * 修改活动信息	
	 * @param active_id
	 * @return
	 */
		public int updateActive(Double activeLimit,Double activeReduce,Integer active_id);
}
