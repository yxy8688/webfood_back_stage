package com.yc.foods.biz.Impl;

import java.util.List;

import com.yc.bean.Active;
import com.yc.bean.Resfood;
import com.yc.foods.biz.IActive;
import com.yc.foods.dao.DBHelper;
import com.yc.foods.utils.RedisUtil;

import redis.clients.jedis.Jedis;

public class ActiveBizImpl implements IActive {
	private Jedis jedis=new Jedis("127.0.0.1");
	RedisUtil<Active> ru=new RedisUtil<Active>();
	@Override
	public List<Active> findAll() throws Exception {
		
		DBHelper db =new DBHelper();
		
		
		List<Active> list=null;
		
		try {
			jedis.connect();
			if(jedis.isConnected()==true  && jedis.keys( "active:*").size()>0){
				
				list=ru.getFromHash(jedis,"active:*","active_id",Active.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
	if(list==null){
		
	
		String sql="select * from active";
		list=db.find(sql, Active.class);

		ru.saveToHash(jedis,"active","active_id",list,Active.class);
	}
	return list;
	}

	

	@Override
	public int updateActive(Double activeLimit, Double activeReduce, Integer active_id) {
		
		String sql="update active set activeLimit=? , activeReduce=? where active_id=?";
		
		DBHelper db =new DBHelper();
		
		return db.update(sql, activeLimit,activeReduce,active_id);
		
	}

}
