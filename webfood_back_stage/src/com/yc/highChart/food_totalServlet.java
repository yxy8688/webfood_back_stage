package com.yc.highChart;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.bean.Resfood;
import com.yc.food.servlet.BasicServlet;
import com.yc.foods.biz.IResorderitem;
import com.yc.foods.biz.Impl.ResorderItemBizImpl;
import com.yc.foods.dao.DBHelper;
import com.yc.web.model.JsonModel;



public class food_totalServlet extends BasicServlet {
	private static final long serialVersionUID = 1L;
    private JsonModel jm=new JsonModel();  
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		
		DBHelper dbHelper=new DBHelper();
		List<Resfood> list;
		try {
			String sql="select fid from resfood";
			list = dbHelper.find(sql,Resfood.class);
			for(int i=0;i<list.size();i++){
				int fid=list.get(i).getFid();
				int num=0;
				
				IResorderitem resorderitem=new ResorderItemBizImpl();
				String getnum=resorderitem.getNum(fid);
				
				if(getnum!=null){
					num=Integer.parseInt(resorderitem.getNum(fid));
				}
				list.get(i).setNum(num);
				
			}
		
			
			jm.setCode(1);
			jm.setObj(list);
			super.outJson(jm, response);
		} catch (Exception e) {
			e.printStackTrace();
			jm.setCode(0);
			jm.setErrorMsg(e.getMessage());
		}
	}

}
