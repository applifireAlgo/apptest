Ext.define('Apptest.view.resource.AppMenuTreePanelController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.appMenuTreePanelController',
	
	init : function() 
	{
		this.formTreePanel = this.getView();
		this.callParent();
	},
	
	renderFormPanel: function( currentObject, record, item, index, e, eOpts){
    	
    	if(!record.data.leaf){
    		return;
    	}
    	if(record.data.menuAction == null || record.data.menuAction == ""){
    		Ext.Msg.alert('Error...', 'No view available');
    		return;
    	}
    	
    	var loadMask = new Ext.LoadMask({
		    msg    : 'Loading data...',
		    target : this.getView().up().up()//this.getView().up().up().up().up().up()
		}).show();
    	
    	if(record.data.leaf)
    	{
    		var flag = false;
    		var config = record.data;
    		var tabs =  Ext.getCmp('appMainTabPanel');
    		var tabBarLength = tabs.items.items.length;
    		if(tabBarLength>0)
    		{
    			for(var i = 0;i<tabBarLength;i++)
    				{
    					if(config.menuId == tabs.items.items[i].menuid)//we can change this condition for better code
    					{
    						flag = true;
    						tabs.setActiveTab(tabs.tabBar.items.items[i].config.card);
    						break;
    					}
    				}
    		}
    		
    		if(!flag)
    		{
    			var component = Ext.create(config.menuAction,{closable : true,title: record.data.text, refId:config.refId, menuid:	config.menuId, listeners:{ close: function(me, eopts) {
    				
    				
    				var json = {};
    				json.id = me.config.menuid;
    				json.value = me.config.menuAction;
    				
    				 Ext.Ajax.request({
    			 		 url : "secure/MenuService/deleteMenu",
    		             method:'POST', 
    		             jsonData: json,
    		             success : function(response){
    		             },
    		             failure : function(response){
    		             }
    		         });			
    				
    				
    			}} });
    			
    			if(!isMultiTab){
    				if(tabs.getActiveTab()){
    					tabs.getActiveTab().close();
    				}
    			}
    			
    			loadMask.hide();
    			
    			var tab = tabs.add({
    					  	xtype : component,
    						title: record.data.text,
                            menuId : config.menuId 
    					});
    			tabs.setActiveTab(tab);
    			
    			if(config.autoSave){
    				var json = {};
        			json.id = config.menuId;
        			json.value = config.menuAction;
        			
        			 Ext.Ajax.request({
    	    	 		 url : "secure/MenuService/storeMenu",
    	                 method:'POST', 
    	                 jsonData: json,
    	                 success : function(response){
    	                 },
    	                 failure : function(response){
    	                 }
    	             });	
    			}
    		}
    		}
    	loadMask.hide();
	}
});
