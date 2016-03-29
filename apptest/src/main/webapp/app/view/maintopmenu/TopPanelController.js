Ext.define('Apptest.view.maintopmenu.TopPanelController', {
	extend : 'Ext.app.ViewController',

	requires : [ 'Ext.MessageBox' ],
	alias : 'controller.topToolbarController',

	onSearchClick : function(button) {

		//	"Apptest.view.scheduler.scheduler"
		var component = Ext.create(
				"Apptest.view.searchengine.search.SearchEngineMainPanel", {
					closable : true,
					title : "Search Engine",
					refId : "-1"
				});

		var tabs = Ext.getCmp('appMainTabPanel');
		;
		var tab = tabs.add({
			xtype : component,
			title : "Search Engine"
		});
		tabs.setActiveTab(tab);
	},
	
	onCloudClick : function(button)
	{
		var component = Ext.create(
				"Apptest.view.clouddrive.CloudDrive", {
					closable : true,
					title : "Cloud Drive",
					//refId : "-1"
				});

		var tabs = Ext.getCmp('appMainTabPanel');
		;
		var tab = tabs.add({
			xtype : component,
			title : "Cloud Drive"
		});
		tabs.setActiveTab(tab);
	},
	
	onLogoutClick : function() {
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
					Ext.Msg
							.alert('Logout failed',
									jsonRespone.response.message);
				}
			},
			failure : function() {
				Ext.Msg.alert('Error', 'Cannot connect to server');
			}
		});
	}
});
