Ext.define('Apptest.view.mobileview.main.TopPanel.TopPanelController', {
	extend : 'Ext.app.ViewController',

	requires : [ 
	             //'Ext.MessageBox' 
	             ],

	alias : 'controller.topPanelController',

	init : function() {
		this.control({
			'container button[action=menuBtnToggle]' : {
				toggle : this.onButtonPress
			}
		});
		this.callParent();
	},
	onDrawerBtnClick :function(button, pressed) {
		
	},
	onButtonPress : function(button, pressed) {
		
		//mbadd
		var westPanel = this.getView().up().up().down('#westPanel');
		
		westPanel.setHidden(!westPanel.isHidden());
		
		var appMainTabPanel = this.getView().up().up().down('#appMainTabPanel');
		appMainTabPanel.setDisabled(!westPanel.isHidden());
		
	},
	onSearchClick : function(button) {

		//	"Apptest.view.mobileview.scheduler.scheduler"
		var component = Ext.create(
				"Apptest.view.mobileview.searchengine.search.SearchEngineMainPanel", {
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
				"Apptest.view.mobileview.clouddrive.CloudDrive", {
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
					this.location.reload();
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
