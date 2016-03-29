Ext.define('Apptest.view.usermanagement.roles.EditRolesController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.editRolesController',
	defaultMenuStore:null,
	roleId:null,
	versionId:null,
	
	init:function()
    {
    	this.initializeDragZone();
    },
    
    onDeviceTypeChange : function(radiogroup, newValue, oldValue, eOpts) {
		this.removeAllFeaturesFromTree();
		this.loadFeatureTree(newValue.eDeviceType);
	},
	
	/** REMOVING ALL FEATURES FROM TREE */
	removeAllFeaturesFromTree : function() {
		var allTreePanel = this.getView().down("#eAllFeatureTree");
		var rootNode = allTreePanel.getRootNode();
		allTreePanel.removeAll();
	},

	loadFeatureTree : function(appType) {

		var currentObject = this;
		var loadMask = new Ext.LoadMask({
			msg : 'Loading data...',
			target : currentObject.getView()
		}).show();
		Ext.Ajax.request({
			url : "secure/MenuService/findMenusByDeviceType",
			method : 'POST',
			loadMask : loadMask,
			controller : currentObject,
			jsonData : {
				"findKey" : appType
			},
			success : function(response, currentObject) {

				var responseJson = Ext.JSON.decode(response.responseText);
				var rawData = Ext.JSON.decode(responseJson.response.data);

				currentObject.controller.defaultMenuStore = rawData;
				currentObject.controller.createTree(rawData)
				currentObject.loadMask.hide();
			},
			failure : function(response, currentObject) {
				currentObject.loadMask.hide();
				Ext.MessageBox.show({
					title : 'Error',
					msg : "Cannot connect to server.",
					icon : Ext.MessageBox.ERROR
				});
			}
		}, currentObject);
	},

	afterAllFeatureTreeRender : function() {
		/** BY DEFAULT WEB-APP MENUS WILL BE LOADED WITH THE TYPE 1 */
		this.loadFeatureTree(1);
	},

	
	createTree:function(rawData)
	{
		var allTreePanel=this.getView().down("#eAllFeatureTree");	
  		var rootNode=allTreePanel.getRootNode();
    	/*for(i=0;i<rawData.length;i++){
    		data=rawData[i];
      		var fChild=data.children;*/
  			var fChild=rawData;
      		for(var x1=0;x1<fChild.length;x1++)
      		{
      			this.addChild(rootNode,fChild[x1]);
      		}
    	//}
	},
	addChild:function(parentNode,node)
    {
		
    	if(node.hasOwnProperty("children")&& node.children!=null)
    	{
    		var child={
        			text:node.text,
        			menuId:node.menuId,
        			icon : 'images/folder-database-icon.png',
        			read :true,
        	}
    		child["expanded"]=true;
    		child["isRead"]=true;
    		child["isWrite"]=true;
    		child["isExecute"]=false;
    		/** here we are not getting does the menu is head or not from db so i am adding headMenu property here to identify it**/
			child["isHeadMenu"] = true;
    		var newNode=parentNode.appendChild(child);
    		for(var x=0;x<node.children.length;x++)
    		{	
    			this.addChild(newNode,node.children[x]);
    		}
    	}else{
    			node["isRead"]=true;
    			node["isWrite"]=true;
    			node["isExecute"]=false;
    			node['visible'] = (node.text=="")?false:true;
    			node["isHeadMenu"] = false;
    			parentNode.appendChild(node);
    	}   	
    },
    
 // Propagate change downwards (for all children of current node).
	setChildrenCheckedStatus : function (node, property, checked) {
		
		if ((node.data.isHeadMenu) && (node.hasChildNodes())) { 	      		        
			for(var i=0; i<node.childNodes.length; i++) {
				var child = node.childNodes[i];
				if ((child.data.isHeadMenu) && (child.hasChildNodes())) {
					this.setChildrenCheckedStatus(child, property, checked);
				}
				if(child.data.visible)
					child.set(property,checked);
			}		        
		}	        
	},	

	// Propagate change upwards (if all siblings are the same, update parent).
	updateParentCheckedStatus : function (current, property, checked) {
		if (current.parentNode.data.isHeadMenu) {
			
			var parent = current.parentNode;

			var checkedCount = 0;
			var visibleChildLength = 0;

			parent.eachChild(function(n) {
				if(n.data.visible) {
					visibleChildLength++;
					checkedCount += (n.get(property) ? 1 : 0);
				}
			});			

			// a single child is checked, so check the parent.
			if (checkedCount == 1){
				
				parent.set(property, true);
				if(parent.parentNode.data.isHeadMenu  && checked == true) {
					this.updateParentCheckedStatus(parent, property, checked);
				}
			}         

			// Children have same value if all of them are checked or none is checked.
			var sameValue = (checkedCount == visibleChildLength) || (checkedCount == 0); //parent.childNodes.length) || (checkedCount == 0);
			if (sameValue) {
				parent.set(property, checked);
				if(parent.parentNode.data.isHeadMenu) {
					this.updateParentCheckedStatus(parent, property, checked);
				}
			}   
		}
	},

	onIsReadCheckChange : function(checkcolumn, rowIndex, checked, eOpts) {

		this.applyCheckBehaviour(rowIndex,'isRead',checked);		
	},
	
	onIsWriteCheckChange : function(checkcolumn, rowIndex, checked, eOpts) {

		this.applyCheckBehaviour(rowIndex,'isWrite',checked);	
	},

	onIsExecuteCheckChange : function(checkcolumn, rowIndex, checked, eOpts) {

		this.applyCheckBehaviour(rowIndex,'isExecute',checked);
	},
	
	onIsDeleteCheckChange : function(checkcolumn, rowIndex, checked, eOpts) {

		var mappingTree = this.getView().down('#eMappedFeatureTree');
		var node = mappingTree.store.getAt(rowIndex);

		var property = 'isDelete';

		// Propagate change downwards (for all children of current node).
		this.setChildrenCheckedStatus(node, property, checked);

		// Propagate change upwards (if all siblings are the same, update parent).
		if (node.parentNode.data.isHeadMenu) {
			
			var parent = node.parentNode;

			var checkedCount = 0;
			var visibleChildLength = 0;

			parent.eachChild(function(n) {
				if(n.data.visible) {
					visibleChildLength++;
					checkedCount += (n.get(property) ? 1 : 0);
				}
			});			

			// a single child is unchecked, so uncheck the parent.
			if(checkedCount == (visibleChildLength-1)){
								
				parent.set(property, false);
			} 

			// if all of children are checked then only check parent.
			var sameValue = (checkedCount == visibleChildLength) || (checkedCount == 0); 
			if (sameValue) {	                
				parent.set(property, checked);   	                
			}   
		}			
	},

	applyCheckBehaviour : function(rowIndex, property, checked){
		
		var mappingTree = this.getView().down('#eMappedFeatureTree');
		var node = mappingTree.store.getAt(rowIndex);

		// Propagate change downwards (for all children of current node).
		this.setChildrenCheckedStatus(node, property, checked);

		// Propagate change upwards (if all siblings are the same, update parent).
		this.updateParentCheckedStatus(node, property, checked);
	},
    
    initializeDragZone:function() 
    {	
    	var dragData  = null;
    	var treeComponentPanel = this.getView().down("#eAllFeatureTree");
    	
    	treeComponentPanel.on('itemmousedown', function( treeComponentPanel, record, item, index, e, eOpts ){
    				dragData = record  //record.data will give only the selected node
    	});
    	
    	treeComponentPanel.on('render', function(v) {
    		treeComponentPanel.dragZone = new Ext.dd.DragZone(v.getEl(), {	
    			onBeforeDrag : function(data, e)
    			{
    				//Parent Node not allowed to dragged
    				/*if(data.draggedRecord.data.leaf==false){
    					return false;
    				}*/
    				if (data.draggedRecord.cannotDrag) {
						return false;
					}	
				},
    		   getDragData: function(e)
    		   {
    		      var sourceEl = e.getTarget(v.itemSelector, 10);
    		      if (sourceEl) {
    		    	  d = sourceEl.cloneNode(true);
    		    	  var dragDataToSend = d.id; 		    	  
    		    	  if (dragData.component  == d.textContent){
    		    		  dragDataToSend = dragData;
    		    	  } 		        
    		    	  d.id = Ext.id();        
    		    	  return {
    		                   ddel: d,
    		                   sourceEl: sourceEl,
    		                   repairXY: Ext.fly(sourceEl).getXY(),
    		                   sourceStore: v.store,
    		                   draggedRecord: dragData    
    		          };
    		     }
    		  },
    		  getRepairXY: function() {
    		        	//console.log('Drag Zone: getRepairXY() called...');
    		            return this.dragData.repairXY;
    		        },
    		        ddGroup : 'myDDGroup'
    		  });    		 		
    	});
    },//initializeDragZone
    
    initializeDropZone:function(panel)
    {
    	
    	var me =this; //dBBuilderController
    	
    	/**Click Event of Panel's items*/
    	panel.getEl().on('click', function(e,el,panel){
			var targetNode =this.getNodeForEl(e.getTarget());
		},me);
		
		/**Initialize DropZone*/
		var drop = new Ext.dd.DropZone(panel.el, {
			ddGroup:'myDDGroup',
			scope:this,
			getTargetFromEvent: function(e)
			{		
	        //return e.getTarget(this.layoutPanel);
	        },
			notifyOver : function(src,e,data)
			{
			   return Ext.dd.DropZone.prototype.dropAllowed;
			},
			notifyDrop : function(src,e,data)
			{
				
				var rootNode=this.scope.getView().down('#eMappedFeatureTree').getRootNode();
				var draggedRecord=data.draggedRecord;
				
				//If leaf node is dragged then drag its parent nodes in hierarchy also
				if(draggedRecord.data.leaf==true)
				{
					var tempArr=[];
					while(draggedRecord.data.text!="Root")
					{
						tempArr.push(draggedRecord.data);
						draggedRecord=draggedRecord.parentNode;		
					}
					var parentNode=rootNode;
					for(i=tempArr.length-1;i>=0;i--)
					{
						if(parentNode.findChild("text",tempArr[i].text,true)==null){
							parentNode=parentNode.appendChild(tempArr[i]);
						}
						else{
							parentNode=parentNode.findChild("text",tempArr[i].text,true);
						}
					}
				}
				//If folder node is dragged then drag its parent nodes in hierarchy as well as all its children
				else{
						var tempArr1=[];
						tempArr1.push(draggedRecord);
						while(draggedRecord.parentNode.data.text!="Root")
						{
							draggedRecord=draggedRecord.parentNode;
							tempArr1.push(draggedRecord.data);
						}
						var parentNode=rootNode;
						for(i=tempArr1.length-1;i>=0;i--)
						{
							if(parentNode.findChild("text",tempArr1[i].text,true)==null){
								parentNode=parentNode.appendChild(tempArr1[i]);
							}
							else{
								parentNode=parentNode.findChild("text",tempArr1[i].text,true);
							}
						}
						this.scope.refreshDefaultTree()
				}
				
			},
			notifyOut : function(src,e,data)
			{ 
			 //this.removehighlightElement();
			 //Ext.fly(src).removeCls('my-row-highlight-class')
			}
		});
		panel.drop=drop;
    },//initializeDropZone ends
    
    getNodeForEl : function(el)
    {
		var search = 0;
		var target = null;
		while (search < 10) {
			target = Ext.ComponentMgr.get(el.id);
			if (target) {
				return target;
			}
			el = el.parentNode;
			if (!el) { break; }
			search++;
		}
		return null;
	},
	refreshDefaultTree : function() {
		if (this.defaultMenuStore != null){
			
			this.getView().down('#eAllFeatureTree').getRootNode().removeAll();
			this.createTree(this.defaultMenuStore);
		}	
	},
	
	saveEditedRolesClick:function()
	{
		
		var me =this;
		var roleId=this.roleId;
		var roleName=this.getView().down('#eRoleName').getValue();
		var roleDesc=this.getView().down('#eRoleDesc').getValue();
		if(roleName==""){ Ext.Msg.alert({title:'Info',msg:"Enter Role Name",icon:Ext.MessageBox.INFO});return;}
		
		if(roleDesc =="" || roleDesc==" ") {
			Ext.Msg.alert({
				title : 'Info',
				msg : "Enter Role Description",
				icon : Ext.MessageBox.INFO
			});
			return;
		}
		
		var mappedTree=this.getView().down('#eMappedFeatureTree');
		roleMenuBridge=this.prepareRoleMenuBridge(mappedTree);
		if(roleMenuBridge.length == 0) {
			Ext.Msg.alert({
				title : 'Info',
				msg : "Please add the role feature first",
				icon : Ext.MessageBox.INFO
			});
			return;
		}
		var jsonData = {
			versionId:this.versionId,
			systemInfo:{activeStatus:1},
			roleId:roleId,
			roleName : roleName,
			roleDescription : roleDesc,
			roleMenuBridge : roleMenuBridge
		};

		Ext.Ajax.request({
			timeout : 180000,
			url : 'secure/Roles',
			method : 'PUT',
			me:me,
			jsonData : jsonData,
			success : function(response,currentObject)
			{
				var responseJson = Ext.JSON.decode(response.responseText);
				if(responseJson.response.success==true)
				{
					var currentView=currentObject.me.getView();
					Ext.Msg.alert('Success',"Data Updated Successfully");
					rolesTree=currentView.up().up().down('#rolesTree');
					rolesTree.store.load();
					currentView.down('#eRoleFormPanel').reset();
					currentView.down('#eMappedFeatureTree').getRootNode().removeAll();
					currentView.disable();
					var activeTab=currentView.up().getActiveTab();
					currentView.up().setActiveTab(activeTab.previousSibling());
					//currentObject.me.resetEditedRolesClick();
				}
				else{
					Ext.Msg.alert({title : 'Error',msg :"Data Transaction failed",icon : Ext.MessageBox.ERROR});
				}
				
			},
			failure : function(response,currentObject) {
				
				Ext.Msg.alert({title : 'Error',msg : 'Cannot connect to server',icon : Ext.MessageBox.ERROR});
			}
		});		
	},//onSaveRolesClick ends
	
	prepareRoleMenuBridge : function(mappedTree)
	{
		
		var criteria = [];
		var store = mappedTree.getStore();
		Ext.Array.each(store.data.items,function(item, idx, items) {
							var object = {
									menuId : item.data.menuId,
									isRead: item.data.isRead,
									isWrite: item.data.isWrite,
									isExecute: item.data.isExecute,
									systemInfo:{activeStatus:(item.data.isDelete==true)?-1:1},   //check whether delete flag is true or not
									roleMenuMapId:item.data.roleMenuMapId,
									versionId:item.data.versionId
							};
							this.criteria.push(object);
						}, {
							criteria : criteria,
							mappedTree : mappedTree,
							scope : this
						});
		return criteria;
	},//prepareRoleMenuBridge ends
	
	resetEditedRolesClick:function()
	{
		var view=this.getView();
		var orgData=view.record.data;
		view.down('#eRoleName').setValue(orgData.text);
		view.down('#eRoleDesc').setValue(orgData.roleDescription);
		view.down('#eMappedFeatureTree').getRootNode().removeAll();
		view.up().up().controller.createMappedTree(view.up().up().down('#addRole').controller.defaultMenuStore,orgData.roleMenuBridge);
	}
});