Ext.define('Apptest.view.fw.mainViewPanel.MainPanelController', {
	extend : 'Ext.app.ViewController',
	requires : ['Ext.util.Cookies'],
	alias : 'controller.mainViewPanelController',
	
	afterRender:function()
	{
		/** ADDING FLAG TO SET SECURITY QUESTION WHEN FLAT IS TRUE USER GETS THE POP-UP TO SET SECURITY QUESTION FROM USER PROFILE OPTION */
		var setSecutityQuestions = Ext.util.Cookies.get("setSecutityQuestions");
		if (setSecutityQuestions == "true") {
			Ext.util.Cookies.clear("setSecutityQuestions");
			
			Ext.MessageBox.show({
				title : "Note",
				msg : "Security questions not set yet, In case you forgot the password it is must needed for password recovery.<br>GOTO My Profile and set security questions",
				icon : Ext.MessageBox.INFO

			});
		}
		
		var cookieChangePwd = Ext.util.Cookies.get('changePwd');
		if(cookieChangePwd=="true"){
			var window = Ext.create('Ext.window.Window', {
				width : '25%',
				height : 250,
				layout : 'fit',
				modal : true,
				resizable : true,
				closeAction : 'close',
				plain : true,
				title : 'Change Password',
				autoDestroy:true,
				closable:false
		});
		window.add(Ext.create('Apptest.view.usermanagement.enduser.ChangePwd'));
		window.show();
		}
	},
	aftrAppMainTabPanelRender:function(tabpanel)
	{
		contextMenu = new Ext.menu.Menu({
			  items: [{
			    text: 'Close All',
			    icon: 'images/closable.png',
			    listeners:{
					click:'onCloseAllClick',
					scope:this
				}
			  }]
			});

		tabpanel.tabBar.getEl().on('contextmenu', function(e) {
			     e.stopEvent();
			     contextMenu.showAt(e.getXY());
			});
	},
	onCloseAllClick:function()
	{
		var tabpanel=this.getView().down('#appMainTabPanel');
		while(tabpanel.items.items.length>0){
			tabpanel.items.items[0].close();
		}
	}
});