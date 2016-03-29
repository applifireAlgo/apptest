
Ext.define('Apptest.view.main.TopPanel.TopMenuController', {
    extend: 'Ext.app.ViewController',

    requires: [
        'Ext.MessageBox'
    ],

    alias: 'controller.topMenuController',
    
    init : function() 
    {
       
       this.callParent();
    },
    
    renderMenuPanel:function(jsonResponse,currentObject)
    {
    	
    	var jsonData = jsonResponse.responseBean.response.data;
    	if(jsonData)
    	{
    		var data = Ext.JSON.decode(jsonData);
    		for(var  i = 0 ; i < data.children.length ; i++)
    		{
    			var menuData = [];
    			var menuChildLength = data.children[i].children.length; 
 				if(menuChildLength!=0)
 				{
 					for(var  int = 0 ; int < menuChildLength ; int++)
 		    		{
 						
 						menuData.push(
 						{
 							text:data.children[i].children[int].text,
 							data:data.children[i].children[int],
 							
 							listeners:{
 								click:function(menu,e,eOpts)
 								{
 									
 									//currentObject.controller.getView().up().up().up().up();
 									/*currentObject.controller.renderFormPanel(this,menu.config);*/
 									this.renderCentralPanel(currentObject,menu);
 								}
 							},
 							renderCentralPanel:function(currentObject,menu)
 							{
 								if(menu.config.data.uiType && menu.config.data.uiType=='M')
 								{
 	 									
 	 									var component = Ext.create('Apptest.view.art.masterform.MasterPanel',{closable : true,title: menu.text, artMasterFormId:menu.config.data.refId});
 	 									var tabs =  Ext.getCmp('appMainTabPanel');
 	 									var tab = tabs.add({
 	 						    					  	xtype : component,
 	 						    						title: menu.text
 	 						    					});
 	 						    		tabs.setActiveTab(tab);
 								}
 								else
 								{
 									
 									var component = Ext.create(config.menuAction+"Main",{closable : true,title: menu.text});
						        		
						        		var tabs =  Ext.getCmp('appMainTabPanel');;
						    			var tab = tabs.add({
						    					  	xtype : component,
						    						title: menu.text
						    					});
						    			tabs.setActiveTab(tab);
 								}
 							}
 						});
 		    		}
 				}
 				
    			var menuItem = Ext.create('Ext.button.Split', {
    	    	    //text: data.children[i].text,
    				//icon: 'resources/css/images/icon-menu.png',
    				//icon: 'resources/css/images/r.png',
    				icon: 'resources/css/images/'+data.children[i].menuIcon,
    				tooltip :data.children[i].text,
    	    	    menu:menuData
    	    	});
    			this.getView().items.add(menuItem);
    		}
    	}
    }
});
