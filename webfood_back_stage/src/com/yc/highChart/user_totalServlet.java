package com.yc.highChart;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.bean.Resuser;
import com.yc.food.servlet.BasicServlet;

import com.yc.foods.dao.DBHelper;
import com.yc.web.model.JsonModel;



public class user_totalServlet extends BasicServlet {
	private static final long serialVersionUID = 1L;
	private JsonModel jm=new JsonModel();  

	protected void doPost(HttpServletRequest request, HttpServletResponse response){

		DBHelper dbHelper=new DBHelper();
		List<Resuser> list;
		try {
			String sql="select userid from resuser";
			list=dbHelper.find(sql, Resuser.class);


			for(int i=0;i<list.size();i++){
				String sql1="select sum(dealprice) SUM ,count(ritem.roiid) count from resorderitem  ritem  inner join"
						+ " resorder rorder on ritem.roid=rorder.roid where userid="+list.get(i).getUserid();


				List<String> sum;
				double total=0;
				int order=0;


				sum=dbHelper.find(sql1);


				if(sum!=null){
					if(sum.get(0)!=null){
						total=Double.parseDouble(sum.get(0));
					}else{
						total=0;
					}

					order=Integer.parseInt(sum.get(1));
				}
				list.get(i).setSum(total);
				list.get(i).setOrder(order);

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
