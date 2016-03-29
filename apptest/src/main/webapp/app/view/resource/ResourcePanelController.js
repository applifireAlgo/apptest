Ext.define('Apptest.view.resource.ResourcePanelController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.resourcePanelController',
	requires:['Ext.util.Cookies'],
	formResourcePanel :null,

	init : function() 
	{
		this.formResourcePanel = this.getView();
		this.callParent();
	},

	/**Fetch resource tree panel data**/
	onResourcePanelAfterRender:function(p, eOpts)
	{
		
		var currentObject = this;
		var loadMask = new Ext.LoadMask({
			msg    : 'Loading data...',
			target : currentObject.getView()
		}).show();
		Ext.Ajax.request({
			url : "secure/MenuService/findMainScreenMenus",
			method:'POST', 
			loadMask:loadMask,
			controller:currentObject,
			jsonData:{
			},
			success : function(response,currentObject){
				currentObject.loadMask.hide();
				var responseJson = Ext.JSON.decode(response.responseText);
				if(responseJson.response.success!=false){
					var mainScreenData = Ext.JSON.decode(responseJson.response.data);
					
					currentObject.controller.createDockedMenuPanel(mainScreenData.menus);
					currentObject.controller.createTreePanel(mainScreenData.menus);
					
					/** LOAD HOME PAGE*/
					if(mainScreenData.homeScreenMenus) {
						try {
							var mainScreenDataList = mainScreenData.homeScreenMenus;
							for(var i=0; i < mainScreenDataList.length; i++) {
								var isTabClosable = i==0 ? false : true;
								currentObject.controller.addTabToMainScreen(mainScreenDataList[i], isTabClosable);
							}
						} catch(e) {	}
					}
					
					/** CALL TO LOAD EXTERNAL API'S*/
					loadExternalApi();
				}else{
					//Ext.MessageBox.alert({title: 'Error',msg: "Data Loading Failed.Please re login",icon: Ext.MessageBox.ERROR});
					//Redirect to Login.html
					var pathName=location.pathname;
					var initialPath=pathName.slice(0,pathName.lastIndexOf("/"));
					location.href=location.origin+initialPath+"/";
				}
			},
			failure : function(response,currentObject){
				currentObject.loadMask.hide();
				//Ext.MessageBox.show({title: 'Error',msg: "Cannot connect to server.",icon: Ext.MessageBox.ERROR});
				//Redirect to Login.html
				var pathName=location.pathname;
				var initialPath=pathName.slice(0,pathName.lastIndexOf("/"));
				location.href=location.origin+initialPath+"/";
			}
		},currentObject);		
	},
	
	addTabToMainScreen : function(config, isTabClosable) {
		var tabs =  Ext.getCmp('appMainTabPanel');
		var component = Ext.create(config.menuAction, {
			closable : isTabClosable,
			title : config.text,
			refId : config.refId,
			menuid : config.menuId
		});
		var tab = tabs.add({
			xtype : component,
			title: config.text
		});
		tabs.setActiveTab(tab);
	},
	
	/**creates resource tree panel and loads data**/
	createTreePanel : function(data) {

		var me = this;

		for(var j = 0; j < data.length; j++) {
			var record = data[j];

			var treePanel = new Ext.create('Apptest.view.resource.AppMenuTreePanel');
			var rootNode = treePanel.getRootNode();
			Ext.Array.each(record, function(rec) {
				Ext.Array.each(rec, function(recChild){
					me.addChild(rootNode,recChild);
				});
			});

			me.formResourcePanel.add(treePanel);
		}

		
		var currentObject = this;
		Ext.Ajax.request({
			url : "secure/MenuService/fetchStoreMenu",
			method:'POST', 
			controller:currentObject,
			success : function(response,currentObject){

				var menus = Ext.decode(response.responseText);
				var data = Ext.decode(menus.response.data);

				var view = currentObject.controller.getView();

				var appMenuTreePanel = view.query('appMenuTreePanel');

				
				var tabs =  Ext.getCmp('appMainTabPanel');
				for (var i = 0; i < data.length; i++) {
					var respData = data[i];

					var matchedMenuNode = null;
					for(var k=0; k<appMenuTreePanel.length; k++){
						matchedMenuNode = null;
						var rootNode = appMenuTreePanel[k].getRootNode(); 
						matchedMenuNode = rootNode.findChild('menuId',respData.menuId, true);
						if(matchedMenuNode!=null){
							break;
						}
					}

					var config = matchedMenuNode.data; //appMenuTreePanel.store.data.find('menuId',respData.menuId).data;

					var component = Ext.create(config.menuAction,{closable : true,title: config.text, refId:config.refId ,menuid:	respData.menuId, listeners:{ close: function(me, eopts) {

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
					var tab = tabs.add({
						xtype : component,
						title: config.text
					});
					tabs.setActiveTab(tab);

				}

			},
			failure : function(response,currentObject){
			}
		},currentObject);		

	},

	/**function to add child nodes in resource tree**/
	addChild : function(parentNode, node) {
		if (node.children != null) {
			var child = {
					text : node.menuName,
					menuId : node.menuId,
					menuAction : node.menuAction,
					//cls:'menuTreeArrow',
//					cls : 'menuTreeNode'
			};
			
			if(node.menuIcon == ""){
				child['cls'] = 'menuTreeNode menuTreeNodeIcon';
			} else {
				child['icon'] = node.menuIcon;
				child['cls'] = 'menuTreeNode';
			}
			
			var newNode = parentNode.appendChild(child);
			for (var x = 0; x < node.children.length; x++) {

				if(node.children[x].children) {

					for (var i = 0; i < node.children[x].children.length; i++) {

						if(node.children[x].children[i].children) {
							for (var j = 0; j < node.children[x].children[i].children.length; j++) {
								var temp = node.children[x].children[i].children[j];
								temp.icon=temp.menuIcon;
							}
						}
					}
				}
				this.addChild(newNode, node.children[x]);
			}
		} else {
//			node['cls'] = 'menuTreeLeafNode menuTreeLeafNodeIcon';
			
			if(node.menuIcon == ""){
				node['cls'] = 'menuTreeLeafNode menuTreeLeafNodeIcon';
			} else {
				node['icon'] = node.menuIcon;
				node['cls'] = 'menuTreeLeafNode';
			}

			/** If later u want to give icon to tree's leaf node i.e. our clickable menu 
			 * ADD BELOW COMMENTED LINES and remove above line  
			 * node['cls'] = 'menuTreeLeafNode';
			 * node['icon'] = node.menuIcon;
			 * 
			 * currently this is so because if we do not provide "menuTreeLeafNodeIcon" in cls of this node 
			 * it will display a default file icon as for every normal treepanel **/

			parentNode.appendChild(node);
		}
	},

	/**creates resource docked menu panel and loads data**/
	createDockedMenuPanel : function(data) {

		var dockedMenuPanel = this.getView().getPlaceholder();		

		for(var i=0;i<data.length;i++) {

			if(data[i].hasOwnProperty("children")&& data[i].children!=null) {

				
				var button = {   
						xtype : 'button',
						leaf: data[i].leaf,
						menuAction: data[i].menuAction,
						menuCommands: data[i].menuCommands,
						menuId: data[i].menuId,
						menuLabel: data[i].menuLabel,
						menuName: data[i].menuName,
						menuTreeId: data[i].menuTreeId,
						refId: data[i].refId,
						userId: data[i].userId,
						menu : [],
						style : {
							backgroundColor: '#212121',
							border: 0
						},
						menuAlign : 'tr'
				};

				if(data[i].menuIcon!=undefined && data[i].menuIcon.trim()==""){
					button['icon'] = 'images/menu/menuhead.png';
				} else {
					button['icon'] = data[i].menuIcon;
				}
				
				/** leaf's value is defining menu is clickable or not 
				 * HERE its having children so it can't be leaf **/

				var mainmenu = dockedMenuPanel.add(button);

				var subMenus = data[i].children;
				for(var j=0; j<subMenus.length; j++)
				{
					this.addMenu(mainmenu,subMenus[j]);
				}			

			} else {
				
				var button = {   
						xtype : 'button',

						leaf: data[i].leaf,
						menuAction: data[i].menuAction,
						menuCommands: data[i].menuCommands,
						menuId: data[i].menuId,
						menuLabel: data[i].menuLabel,
						menuName: data.menuName,
						menuTreeId: data[i].menuTreeId,
						refId: data[i].refId,
						userId: data[i].userId,
						style : {
							backgroundColor: '#212121',
							border: 0
						}
				};
				
				if(data[i].menuIcon && data[i].menuIcon.trim() == ""){
					button['icon'] = 'images/menu/menuhead.png';
				} else {
					button['icon'] = data[i].menuIcon;
				}

				/** leaf's value is defining menu is clickable or not **/
				if(data[i].leaf){
					button['handler'] = 'onMenuItemClick';
				}

				dockedMenuPanel.add(button);
			}			
		}

	},

	addMenu:function(parentMenu,data)
	{
		if(data.hasOwnProperty("children")&& data.children!=null)
		{
			var menu={ 					
					leaf: data.leaf,
					menuAction: data.menuAction,
					menuCommands: data.menuCommands,
					menuIcon: data.menuIcon,
					menuId: data.menuId,
					menuLabel: data.menuLabel,
					menuName: data.menuName,
					menuTreeId: data.menuTreeId,
					refId: data.refId,
					text: data.text, 
					userId: data.userId,
					style : {
						backgroundColor: '#212121',
						border: 0,
						//padding : '10px 0px 10px 0px'
					},
					icon: data.menuIcon,
					cls : 'dockedSubMenuItem',
					activeCls : 'dockedActiveSubMenuItem',
					menu : []					
			};

			/** leaf's value is defining menu is clickable or not 
			 * HERE its having children so it can't be a leaf **/

			var newMenu = parentMenu.menu.add(menu);

			for(var x=0;x<data.children.length;x++)
			{	
				this.addMenu(newMenu,data.children[x]);
			}

		}else{

			var menu={					
					leaf: data.leaf,
					menuAction: data.menuAction,
					menuCommands: data.menuCommands,
					menuId: data.menuId,
					menuLabel: data.menuLabel,
					menuName: data.menuName,
					menuTreeId: data.menuTreeId,
					refId: data.refId,
					text: data.text, 
					userId: data.userId,
					icon: data.menuIcon,	
					cls : 'dockedClickableSubMenuItem',
					activeCls : 'dockedActiveClickableSubMenuItem',
			};

			/** leaf's value is defining menu is clickable or not **/
			if(data.leaf){
				menu['handler'] = 'onMenuItemClick';
			}

			parentMenu.menu.add(menu);
		}   	
	}

});