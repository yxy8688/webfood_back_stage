$(function(){

	$('#index_content_info').tabs('add',{
		title:"小萌神订餐", 
		href:'backgroundImg.html',/*注意：不是相对当前的js 而是相对于页面*/
		fit:true

	});
	$('#foods_menu').tree({
		onClick: function(node){
			/*alert(node.id+node.text);  // alert node text property when clicked*/			
			var id=node.id;
			var title="小萌神订餐";
			var href="backgroundImg.html";
			var tabObj=$('#index_content_info');
			
			if(id=="add_foods"){//添加食品
				if(tabObj.tabs("exists","添加食品")){//添加食品选项卡存在吗：
					//选中
					tabObj.tabs("select","添加食品");
					return;
				}else{
					title="添加食品";
					href="addfoods.html";
				}

			}else if(id=="manager_foods"){//管理食品
				if(tabObj.tabs("exists","管理食品")){//添加食品选项卡存在吗：
					//选中
					tabObj.tabs("select","管理食品");
					return;
				}else{
					title="管理食品";
					href="showfoods.html";
				}
			}else if(id=="manager_selfInfo"){//管理个人信息
				if(tabObj.tabs("exists","个人信息")){
					tabObj.tabs("select","个人信息");
					return ;
				}else{
					title="个人信息";
					href="manager_selfInfo.html";
				}
			}else if(id=="manager_userInfo"){//管理用户信息
				if(tabObj.tabs("exists","用户信息")){
					tabObj.tabs("select","用户信息");
					return ;
				}else{
					title="用户信息";
					href="manager_userInfo.html";
				}
			}else if(id=="manager_order"){//管理订单信息
				if(tabObj.tabs("exists","订单信息")){
					tabObj.tabs("select","订单信息");
					return ;
				}else{
					title="订单信息";
					href="manager_order.html";
				}
			}else if(id=="order_detailed"){//订单详情一览
				if(tabObj.tabs("exists","订单详情一览")){
					tabObj.tabs("select","订单详情一览");
					return ;
				}else{
					title="订单详情一览";
					href="order_detailed.html";
				}
			}else if(id=="food_total"){
				if(tabObj.tabs("exists","菜品销量统计")){
					tabObj.tabs("select","菜品销量统计");
					return ;
				}else{
					title="菜品销量统计";
					href="food_total.html";
				}
			}else if(id=="user_total"){
				if(tabObj.tabs("exists","用户下单统计")){
					tabObj.tabs("select","用户下单统计");
					return ;
				}else{
					title="用户下单统计";
					href="user_total.html";
				}
				
			}else if(id="active_manager"){
				if(tabObj.tabs("exists","每日活动")){
					tabObj.tabs("select","每日活动");
					return;
				}else{
					title="每日活动";
					href="active_manager.html";
				}
			}
			$('#index_content_info').tabs('add',{
				title:title, 
				href:href,
				fit:true,
				closable:true

			});

		}
	});

})