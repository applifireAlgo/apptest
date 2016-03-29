Ext.define('Apptest.view.fw.frameworkController.FrameworkViewController', {
    extend: 'Ext.app.ViewController',
    
    /********************* Main Controller Functions *********************************/
    
    /** Grid reconfiguration */
    onGridRefreshClick : function(me, eOpts, but){
        var grid = but.up()
        grid.store.reload() ;
    },
    
    /** Find record by Id */
    findRecordById : function (restURL, primaryKey){
      scope = this.view;
    	var data = Ext.Ajax.request({
             async: false,
             url: 'secure' + restURL + '/findById',
             method: 'POST',
             jsonData: primaryKey,
             sender: scope,
             success: function(response, scope) {
                  scope.sender.controller.addCommunicationLog(response, true);
             },
             failure: function(response, scope) {
                  scope.sender.controller.addCommunicationLog(response, false);
             }
        }, scope);
    	
    	return responseData = Ext.JSON.decode(data.responseText).response.data;
   },
    
   /** To add server calls in communication array */
   addCommunicationLog: function(response, success, compRef) {
	   try {
		   if (success) {
			   var responseData = Ext.JSON.decode(response.responseText);
		   }	
		   if (compRef == null) {
			   responseObj = {
					   name: '',
					   status: response.status,
					   statusText: response.statusText,
					   success: success,
					   message: success ? responseData.response.message : 'Failed'
			   };
		   } else {
			   responseObj = {
					   name: (compRef.fieldLabel != null) ? compRef.fieldLabel : compRef.title,
                       status: response.status,
                       statusText: response.statusText,
                       success: success,
                       message: success ? responseData.response.message : 'Failed'
                  	};
           }
		   this.view.communicationLog.push(responseObj);
         } catch (addCommunicationLogException) {}
    },    
    
    /** To remove the unwanted code from final json creation */
    modifyData: function(config) {
         if (!config) {
              return null;
         }
         for (i in config) {
              if (config[i]) {
                   config[i] = config[i].valueOf();
                   if (typeof config[i] == 'object') {
                        this.modifyData(config[i]);
                   }
              }
         }
    },
    
    /** To find the child in tree by property and value */
    findChild: function(node, key, value) {
         var dNode = node;
         if (node.data.bConfig != null && node.data.bConfig[key] == value) {
              return node;
         } else if (node.childNodes) {
              for (var counter = 0; counter < node.childNodes.length; counter++) {
                   dNode = this.findChild(node.childNodes[counter], key, value);
                   if (dNode) {
                        return dNode;
                   }
              }
         } else {
              return null;
         }
    },
    
    /********************************Form Controller Functions***********************************/

    /** To add the store to combo */
    assignComboData: function(component, data) {
   	  component.setValue('');	
         var storedata = Ext.JSON.decode(data);
         storedata = storedata.response.data;
         
         component.store.setData(storedata);
         component.getStore().sort('primaryDisplay', 'ASC');
    },
    
    /** To find all the combos from container and assign the data to each */ 
    assignAllComboData: function(container, itemId, data) {
         var allForms = container.query('form');
         for (var counter = 0; counter < allForms.length; counter++) {
              if (allForms[counter].down('#' + itemId) != null) {
                   container.controller.assignComboData(allForms[counter].down('#' + itemId), data);
              }
         }
    },
    
    /** To show the window with the server call log */
    onConsoleClick: function() {
         var window = Ext.create('Apptest.view.console.ConsoleWindow');
         var store = window.down('grid').store;
         store.loadData(this.view.communicationLog);
         window.show();
    },
    
    /** To delete the record from the grid in forms */
    onDeleteActionColumnClick: function(grid, rowIndex) {
         grid.store.removeAt(rowIndex);
         grid.refresh();
    },
    
    /** To find the records from store */
    fetchDataFromStore: function(store) {
         storeItems = store.data.items;
         obj = {};
         arr = [];
         for (counter in storeItems) {
              arr.push(storeItems[counter].data);
         }
         return obj['objArr'] = arr;
    },
    
    /** To display the date in form */
    modifyFormValues: function(values, form) {
         for (var key in values) {
              if (form.down('#' + key).dataType == 'DATE' || form.down('#' + key).dataType == 'DATETIME') {
                   if (form.down('#' + key).getValue() != null) {
                        values[key] = form.down('#' + key).getValue().getTime();
                   }
              }
              if (typeof values[key] === 'object') {
                   values[key] = this.modifyFormValues(values[key], form);
              }
         }
         return values;
    },
    
    /** For the tree filter */ 
    onTriggerfieldChange: function(me) {
         var tree = me.up().up();
         var v;
         try {
              v = new RegExp(me.getValue(), 'i');
              tree.store.filter({
                   filterFn: function(node) {
                        var children = node.childNodes,
                             len = children && children.length,
                             visible = node.isLeaf() ? v.test(node.get('text')) : false,
                             i;
                        for (i = 0; i < len && !(visible = children[i].get('visible')); i++);
                        return visible;
                   },
                   id: 'titleFilter'
              });
         } catch (e) {
              me.markInvalid('Invalid regular expression');
         }
    },
    
    /** To get all the items in view */
    getAllItems : function(items){
   	 
   	 	var allItems = new Array();
   	 	allItems = allItems.concat(items);
   	 
   	 	for (var counter = 0; counter < items.length; counter++) {
   	 		if(items[counter].items){
   	 			var moreItems = this.getAllItems(items[counter].items.items);
   	 			allItems = allItems.concat(moreItems);
   	 		}
		}
   	 	return allItems;
    },
    
    /** Checks if Components are Valid or Not,
     * 	Requires : An Array of Components,
     *  Returns : An Array of Invalid Components */
    validateComponents : function(componentArray) {
     	var invalidComponentArray = [];
     	for(counter=0; counter<componentArray.length; counter++) {
     		var component = componentArray[counter];
     		if(!component.isValid()) {
     			invalidComponentArray.push(component);
     		}
     	}
     	return invalidComponentArray;
    },
     
    /** Final jsonData creation function */
    getData : function(view) {
      	
    	var items = view.items.items;
      	var json = {};
      	for (var counter = 0; counter < items.length; counter++) {
  			var item = items[counter];
  			if(item.isListPanel){
  				if(item.bindable){
  					json[item.bindable] = item.controller.getValues();
  				} else{
  					return item.controller.getValues();
  				}
  			} else {
  				var xtype = item.xtype;
  	   			if(xtype == 'panel' ||  xtype == "fieldset" || xtype == "form"){
  	   				/** Skipping the form-with grid components */
  	   				if(item.fwgDetailForm == true){
  	   					continue;
  	   				}
  	   				var inner = this.getData(item);
  	   				if(item.bindable){
  	   					json[item.bindable] = inner;
  	   				}else{
  	   					this.addJson(json, inner);
  	   				}
  	   			} else if(item.bindable){
  	   				if(xtype == 'datefield' || xtype == 'hidden' || 
  	   						xtype == 'textfield' || xtype == 'hiddenfield' || 
  	   						xtype == 'textareafield'  || xtype == 'numberfield' || 
  	   						xtype == 'timefield' || xtype == 'datefields' || 
  	   						xtype == 'combo' || xtype == 'customcheckboxgroup' ||
  	   						xtype == "grids" || xtype == 'fileupload' ||
  	   						xtype == 'checkbox' || xtype == 'customdatetimefield' ||
  	   						xtype == "radiogroup" || xtype == 'customradiogroup' ||
  	   						xtype == 'displayfield' || xtype == 'datefields') {
  	   					this.addBindableLevelData(json, item);
  	   				}
  	   			}
  			}
  		}
      	return json;
     },
     
     /** Make Target JSON Object from provided source Object
      * This method is used locally by getData() method to create inner json */
     addJson : function(targetJSON, source) {
   	  
       	for(key in source) {
       		if(targetJSON[key]) {
       			this.addJson(targetJSON[key], source[key]);
       		} else {
       			targetJSON[key] = source[key];
       		}
       	}
     },     
     
    /** Adding data to json as per bindable level */
	addBindableLevelData : function(level, item) {

		var bindArr = item.bindable.split('.');
		for (var counter = 0; counter < bindArr.length; counter++) {
			var array_element = bindArr[counter];
			if (counter == bindArr.length - 1) {
				if (item.xtype == "customcheckboxgroup" || item.xtype == "customradiogroup") {
					level[array_element] = item.getCmpValue();
				} else if (item.xtype == "customdatetimefield") {
					if (item.getSubmitValue() != ""
						&& item.getSubmitValue() != null) {
						level[array_element] = item.getValues();
					}
				} else if (item.xtype == "datefield") {
					if (item.getSubmitValue() != "" && item.getSubmitValue() != null) {
						/** Appending time for datefield */
						var val = item.getSubmitValue() + " 0:0:0";
						var timezone = Ext.util.Cookies.get('XA_TID');
						level[array_element] = val + " "+ timezone;
					}
				} else if(item.xtype == "datefields"){
					level[array_element] = item.getValues();			
				} else if (item.xtype == "radiogroup") {
					level[array_element] = item.getValue()[array_element];
					//level[item.inputValue] = item.getValue()[item.inputValue];
				} else if (item.xtype == "combo" && item.compositeKeyArray) {

					/** Finding the composite key reference and changeing the bind level in final json creation */
					for(var comp in item.compositeKeyArray) {
						var compositeKey = item.compositeKeyArray[comp];
						for(var key in compositeKey){
							level[compositeKey[key]] = item.getValue()[key];
						}							
					}
				} else {
					if (item.getValue() == null || item.getValue().length == 0) {
						continue;
					} else {
						level[array_element] = item.getValue();
					}
				}
			} else {
				if (!level[array_element]) {
					level[array_element] = {};
				}
				level = level[array_element];
			}
		}
	},
	
    /** Adding tab to main tab panel */
	addTab : function (component) {
		
		var tabs =  Ext.getCmp('appMainTabPanel');
		tabs.add(component);
		tabs.setActiveTab(component);
    },
     
    /** Create window */
    createWindow : function (component) {
   		
    	new Ext.Window({
	    	   title: '', 
	    	   bodyPadding:'7',
	    	   layout:'fit',
	    	   width:'75%',
	    	   height:600,
	    	   closeAction:'close',
	    	   autoScroll:true,
	    	   items:[component]
			}).show();
    },
		
	/** List panel data set  */
	modifyComponents : function(configData, view){
		
		if(view){
			var items = view.items.items;
		} else {
			var items = this.view.items.items;
		}
		
		for (var itemCounter =0 ; itemCounter < items.length ; itemCounter++) {

		var xtype = items[itemCounter].xtype;
			if(items[itemCounter].isDynamic){
				var name = items[itemCounter].name;
				var inputValue = items[itemCounter].inputValue;
				var boxLabel = items[itemCounter].boxLabel;
				var componentDataArr = configData[items[itemCounter].groupData]; 
				if(Array.isArray(componentDataArr)){
					for(var createCounter = 0; createCounter < componentDataArr.length ; createCounter++){
						var box = {boxLabel: componentDataArr[createCounter][boxLabel],
								name : inputValue,
								bindValue : inputValue,
								inputValue: componentDataArr[createCounter][inputValue]};
						
						items[itemCounter].add(box);
					}
				}
			} else if(xtype == "customdatetimefield" || xtype == "datefields") {
				
				var dtData = configData[items[itemCounter]['bindable']];
				items[itemCounter].setValues(dtData);

			} else if(xtype == "datefield") {
				var dtData = configData[items[itemCounter]['bindable']];
				
				if(dtData){
					var res = dtData.replace(' '+Ext.util.Cookies.get('XA_TID'), '');
        			
					/**Additional condition check added for diffent date format */
        			var dt = Ext.Date.parse(res, 'd-m-Y H:i:s');
 					if(!dt){
 					 dt = Ext.Date.parse(res, 'd-n-Y H:i:s');							
 					}
        			items[itemCounter].setValue(dt);
				}
			} else if(xtype == "image" ) {

				var img = items[itemCounter];
				img.setSrc(configData[img.bindable]);
				
			} else if (xtype == 'combo' && items[itemCounter].compositeKeyArray){

				var value = {};
				for(var comp in items[itemCounter].compositeKeyArray){

					var compositeKey = items[itemCounter].compositeKeyArray[comp];
					for(var key in compositeKey){
						value.key = configData[compositeKey[key]];
					}
				}
				items[itemCounter].setValue(value);
				
			}  else if(xtype == 'hidden' || xtype =='displayfield' ||
	   				   xtype == 'textfield' || xtype == 'hiddenfield' || 
	   				   xtype == 'textareafield' || xtype == 'numberfield' ||
	   				   xtype == 'combo' || xtype == 'checkbox' ||
	   				   xtype == "grids") {
				
				var bindable = items[itemCounter].bindable;
				
				/** Skipping if bindable not found */
				if(!bindable){
						continue;						
					}
				var bindArr = bindable.split('.');
				var value = configData;
				for (var counter = 0; counter < bindArr.length; counter++) {
					value = value[bindArr[counter]];
				}
				items[itemCounter].setValue(value);	
				
			} else if(xtype == 'customradiogroup' || xtype == 'customcheckboxgroup') {
				items[itemCounter].setCmpValue(value);	
			} else if(xtype == "label") {
				items[itemCounter].setText(configData[items[itemCounter]['bindable']]);	
			} 
		}		
		
		for(var innerCounter in items){
			if(items[innerCounter].items){
				this.modifyComponents(configData, items[innerCounter]);
			}
		}
	},	
	
	/** New function for to set the component data by item id */
	modifyComponent : function(configData, componentId) {

		var item = this.view.down('#'+componentId);
		if(item.isDynamic){
			var name = item.name;
			var inputValue = item.inputValue;
			var boxLabel = item.boxLabel;
			var componentDataArr = configData[item.groupData]; 
			if(Array.isArray(componentDataArr)){
					
				for(var counter2 = 0; counter2 < componentDataArr.length ; counter2++){
					var box = {boxLabel: componentDataArr[counter2][boxLabel],
							name : inputValue,
							bindValue : inputValue,
							inputValue: componentDataArr[counter2][inputValue]};
					
					item.add(box);
					}
				}
			} else {
				item.setValue(configData[item.bindable]);
			}	
	},

	/** Set the inner grid data */
	setBindableComponentData : function(view, bindable, data) {
			
		var items = view.items.items;
		for (var counter = 0; counter < items.length; counter++) {
			var item = items[counter];
			if(item.bindable == bindable){
				
				if(item && item.xtype && item.xtype == 'grids'){
					item.setData(data[bindable]);
			    	}
	   		} else if(item.items && item.items.items.length > 0){
	   			this.setBindableComponentData(item,bindable, data);
	   		}
	   	}
	},
		
	/** Sets the data for the view*/
	setData : function(data) {
		
		this.view.getViewModel().setData(data);
		this.traversData(data);
	},

	/** Finds the inner elements(grids etc) to set the data */
	traversData : function(data) {
		
		for (var key in data) {
			if(data[key] instanceof Array){
				this.setBindableComponentData(this.view, key, data);
				}
		}
	},
		
	/** Funciton to hide the save button (will work in case of edit form */
	hideSaveButton: function() {
		
		var toolbars = this.view.query('toolbar');
		
		for (var counter = 0; counter < toolbars.length; counter++) {
			var items = toolbars[counter].items.items;
			for (var counter2 = 0; counter2 < items.length; counter2++) {
				if(items[counter2].CRUDType && items[counter2].CRUDType == "save"){
					items[counter2].setHidden(true);
					}
			}
		}
	},
		
	/** Adding New Tab */
	addToTab : function(component) {
		
		var tabs = Ext.getCmp('appMainTabPanel');
		var tab = tabs.add({
			xtype : component,
			closable : true 
			});
		tabs.setActiveTab(tab);
	},
		
	/** Function to set the data to all components */
	setResponseDataToComponents : function(responseData, componentsData) {
			
		var data = responseData.response.data;
		for (var counter = 0; counter < componentsData.length; counter++) {
			
			var bindArr = componentsData[counter].responseBindLevel.split('.');
			var value = data;
			for (var counter2 = 0; counter2 < bindArr.length; counter2++) {
				value = value[bindArr[counter2]];
			}
			this.view.down("#"+componentsData[counter]['compRefId']).setValue(value);
		} 
	},
    
	/** Function to set the data to Combobox with its Store */
	setComboComponentData : function(responseData, comboComponent, displayField, valueField) {
		comboComponent.reset();
		var store = Ext.create('Ext.data.Store', {
			fields : [ displayField, valueField ],
			data : responseData
		});
		comboComponent.setStore(store);
		comboComponent.getStore().sort(displayField, 'ASC');
	},
		
	/** Sets defaults data to components */
	setDefaultData : function(jsonData) {
		for(var counter in jsonData){
			var component = this.view.down(jsonData[counter]['key']);
			if(component.xtype == "customcheckboxgroup" || component.xtype == 'customradiogroup')
				component.setCmpValue(jsonData[counter]['value']);
			else
	    			component.setValue(jsonData[counter]['value']);
	    	}
	},
	    
	/** Close Current View's Parent Container */
	closeParentContainer : function(view) {
		var parentView = view.up();
		var parentsXtype = parentView.xtype;
		if(parentsXtype == "tabpanel") {
			view.close();
	    } else if(parentsXtype == "window") {
	    	parentView.close();
	    }
	},
	    
	/** Ajax response handler */
	responseHandler : function(responseData) {
		var alertText = "";
		if(responseData.message) {
			alertText = alertText+" Message : " + responseData.message;
	    }
		if(responseData.diagnosis) {
	   		alertText = alertText+"<br> Dagnosis : " + responseData.diagnosis;
		}
		if(responseData.solution) {
			alertText = alertText+"<br> Solution : " + responseData.solution;
	    }
		Ext.Msg.alert('Server Response', alertText);
	}
});
