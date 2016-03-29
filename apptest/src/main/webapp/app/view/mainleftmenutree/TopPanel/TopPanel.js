Ext.define('Apptest.view.mainleftmenutree.TopPanel.TopPanel', {
	extend : 'Ext.toolbar.Toolbar',
	xtype : 'menuTopPanel',
	requires : [ 'Apptest.view.mainleftmenutree.TopPanel.TopPanelController' ],
	controller : 'menuTopPanelController',
	style : {
		backgroundColor: "#fff"
	},
	margin : '0 0 2 0',
	layout :'hbox',
	items : [{
		xtype : 'button',
		icon : 'images/menu/menu.png', 
		style : {
			backgroundColor: "transparent",
			border : 0
		},
		scale : 'medium',		
		action : 'menuBtnToggle',
		itemId: 'appplicationMainMenuBtn',
		pressed : true,
		tooltip : 'Menu',
		enableToggle : true
	},{
		xtype: 'tbfill'
	}, '->',
	{
		xtype:'displayfield',
		itemId:'userNameDField'
	},{
		xtype:'splitbutton',
		itemId:'userDp',
		//icon:'images/user/userdp.png',
		height:48,
		width:48,
		cls:'topToolbarSplitBtnAfter',
		scale: "large",
		style: "border:0;background-color:#fff;padding-bottom:0px;border-radius: 50%;background-image: url('images/user/userdp.png');background-repeat: no-repeat;",
		menu:[
		      {
		    	  text:'My Profile',
		    	  icon:'images/menu/myprofile.png',
		    	  listeners:{
						click:'onMyProfileClick',
		    	  }
		      },{
		    	  text:'Change Password',
		    	  icon:'images/menu/pwdReset.png',
		    	  listeners:{
						click:'onChangePwdClick',
		    	  }
		      },'-',
		      {
		    	  text:'Cloud Drive',
		    	  disabled : true,
		    	  icon:'images/menu/clouddrive.png',
		    	  listeners:{
						click:'onCloudClick',
		    	  }
		      },'-',
		      {
		    	  text:'Log Out',
		    	  icon:'images/menu/logout.png',
		    	  listeners:{
						click:'onLogoutClick',
		    	  }
		      }]
	}],
	listeners:{
		scope:'controller',
		afterrender:'afterTopPanelRender'
	}
});
