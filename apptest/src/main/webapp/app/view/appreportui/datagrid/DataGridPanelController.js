Ext.define('Apptest.view.appreportui.datagrid.DataGridPanelController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.dataGridPanelController',
	
	/**This method is used to add actionbutton if configured*/
	afterDataGridPanelAdded:function(panel, container, pos, eOpts){
		if (this.getView().reportDataJson.actionbutton != undefined && this.getView().reportDataJson.actionbutton.length > 0){
			this.setClickEvent(this.getView().reportDataJson.actionbutton);
			panel.addDocked({
				xtype : 'toolbar',
				dock : 'bottom',
				defaults:{
					style: {
				        background: '#3892d3',
				        borderColor:'#3892d3',
				    }
				},
				items : this.getView().reportDataJson.actionbutton
			});
		}
	},
	setClickEvent : function(buttons) {
		buttons.forEach(function(item) {
			item.listeners = {
				click : this.actionbuttonClick,
				scope : this
			};
		}, this);
	},
	
	/** Used to call action button click event for service call of action button */
	actionbuttonClick : function(btn) {
		var dataGridStore=this.getView().down('#dataGridViewId').getStore();
		dataGridStore.filter("checkcol", true);
		var seleStore = dataGridStore;
		if (seleStore.data.length > 0) {
			var data = this.formatStoreData(seleStore);
			finalURL=btn.serviceUrl;
			if(btn.urlMethod=='DELETE'){
				finalURL=finalURL+data[0][btn.primaryKeyField];
			}
			this.callActionButtonService(finalURL, btn.urlMethod, this.preparePrimaryKeyDateForActionButton(data,btn.primaryKeyField));
		}
		dataGridStore.clearFilter();
	},
	formatStoreData : function(store) {
		var data = [];
		Ext.Array.each(store.data.items, function(item) {
			var keys = Ext.Object.getKeys(item.data);
			var d = {};
			for (var x = 0; x < keys.length; x++) {
				// remove extra keys from data object
				if (keys[x] != "id" || keys[x] != "dataType" || keys[x] != "dateLongValue") {
					d[keys[x]] = item.data[keys[x]];
				}
			}
			data.push(d);
		}, {
			data : data
		});
		return data;
	},
	
	callActionButtonService : function(url, actionmethod, data) {
		var finalValue="{}";
		if(actionmethod!="DELETE"){
			finalValue=Ext.encode(data);
		}
		Ext.getBody().mask('loading...');		
		Ext.Ajax.request({
			url : "secure"+ url,
			method : actionmethod,
			scope : this,
			jsonData : finalValue,
			params : {},
			success : function(response, currentObject, options) {
				Ext.getBody().unmask();
				var responseText=Ext.decode(response.responseText);
				if(responseText.response.success==true){
					Ext.Msg.alert('Info', responseText.response.message);
					currentObject.scope.refresh();
				}else{
					Ext.Msg.alert('Info', responseText.response.message);
				}
			},
			failure : function() {
				Ext.getBody().unmask();
				Ext.Msg.alert('Error', 'Cannot connect to server');
			}
		});
	},
	
	preparePrimaryKeyDateForActionButton:function(data,primaryKey){
		var jsonArray=[];
		if(data!=undefined){
			for(var k=0;k<data.length;k++)
			{
				var jsonData={};
				jsonData[primaryKey]=data[k][primaryKey];
				jsonArray.push(jsonData);
			}
		}
		return jsonArray;
	},
	
	refresh:function(){
		pagingtoolbar = this.getView().reportView.query("pagingtoolbar")[0];
		pagingtoolbar.doRefresh();
		
		this.getView().reportView.controller.getDataPointChartDataByAjax();
	}
});