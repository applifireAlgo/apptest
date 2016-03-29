Ext.define('Apptest.view.mainleftmenutree.TopPanel.TopPanelController', {
	extend : 'Ext.app.ViewController',

	requires : [],

	alias : 'controller.menuTopPanelController',
	
	init : function() {
		this.control({
			'container button[action=menuBtnToggle]' : {
				toggle : this.onButtonPress
			}
		});
		this.callParent();
	},

	afterTopPanelRender:function(panel)
	{
		//Get user cookie value set from angular js loginController
		var cookieUserInfo = Ext.util.Cookies.get('userInfo');
		if(cookieUserInfo==null){
			var pathName=location.pathname;
			var initialPath=pathName.slice(0,pathName.lastIndexOf("/"));
			location.href=location.origin+initialPath+"/";
		}
		panel.down('#userNameDField').setValue("Welcome "+decodeURI(cookieUserInfo));
		
	
	},
	
	onButtonPress : function(button, pressed) {
		
		var westPanel = this.getView().up().up().down('#resourcePanel');
		westPanel.toggleCollapse();
	},
	onCloudClick : function()
	{
		var component = Ext.create(
				"Apptest.view.clouddrive.CloudDrive", {
					closable : true,
					title : "Cloud Drive"
					//refId : "-1"
				});
		this.addTab(component);

		//var mainTabView=this.getView().up();
		/*var tab = mainTabView.add({
			xtype : component
		});
		mainTabView.setActiveTab(tab);
		*/
	},
	addTab:function(tabView)
	{
		var mainTabView=this.getView().up();
		var tab = null;
	
		if(!isMultiTab){
    		if(mainTabView.getActiveTab()){
				if(tabView.id != mainTabView.getActiveTab().id){	
							mainTabView.getActiveTab().close();
							 tab = mainTabView.add({
								xtype : tabView
							});
						}
    				} 
    	}
		else{
			 tab = mainTabView.add({
				xtype : tabView
			});
		}
		mainTabView.setActiveTab(tab);		
	},
	
	onMyProfileClick : function()
	{
		var component = Ext.create(
				"Apptest.view.usermanagement.enduser.UserProfile", {
					closable : true,
					title : "My Profile"
				});

		this.addTab(component);
	},
	
	onChangePwdClick:function()
	{
		var component = Ext.create(
				"Apptest.view.usermanagement.enduser.ChangePwd", {
					closable : true,
					title : "Change Password"
				});

		this.addTab(component);
	},
	
	onLogoutClick : function()
	{
		Ext.Ajax.request({
			url : "secure/Logout",
			method : 'POST',
			jsonData : {},
			success : function(response, scope) {
				
				var jsonRespone = Ext.JSON.decode(response.responseText);
				if (jsonRespone.response.success == "true") {
					//this.location.reload();
					var pathName=this.location.pathname;
					var initialPath=pathName.slice(0,pathName.lastIndexOf("/"));
					
					Ext.util.Cookies.clear('changePwd',initialPath);
					
					this.location.href=this.location.origin+initialPath+"/";
				} else {
					Ext.Msg.alert('Logout failed',jsonRespone.response.message);
				}
			},
			failure : function() {
				Ext.Msg.alert('Error', 'Cannot connect to server');
			}
		});
	}
});
