
/********************************************************************************************************

 * File Name:-ListViewBaseController.js

 * Date Created:-29-Sept-2015

 * Author:- Shrikrishna

 * Purpose :-


 *********************************************************************************************************
 Version   |	Date Modified    |  	Author 	   |	Modifications	

 * 1.0			29-Sept-2015 			 Shrikrishna			

 *********************************************************************************************************/


Ext.define('Apptest.view.fw.frameworkController.ListViewBaseController', {
	extend: 'Apptest.view.fw.frameworkController.FrameworkViewController',
	alias : 'controller.ListViewBaseController',
	/**
	 * *******************Main Controller
	 * Functions********************************
	 */

	componentArray: [],
	templateDataStore: [],
	recordCount: -1,
	activeRecord: 0,
	PAGE_SIZE : 25,

	uiClass : null,

	init : function() {
		var templateConfig = this.view.templateConfig;
		if(templateConfig) {
			this.uiClass = templateConfig.uiClass;
			if(templateConfig.url && templateConfig.url != "") {
				this.setListData(templateConfig);
			}
		} else {
			Ext.Msg.alert('Error', "List view panel rendering failed.");
		}
	},

	setListData : function(templateConfig) {

		var jsonData = {};
		var scope = this.getView();
		Ext.Ajax.request({
			url : templateConfig.url,
			method : templateConfig.requestMethodType,
			sender : scope,
			jsonData : jsonData,
			success : function(response, scope) {
				var responseText = Ext.JSON.decode(response.responseText);
				var responseData = responseText.response.data;
				scope.sender.controller.configForm(responseData);
			},
			failure : function(response, scope) {
			}
		}, scope);
	},

	configForm : function(responseData) {

		if(responseData){
		this.componentArray = new Array();
		this.templateDataStore = new Array();
		
		/*if (this.componentArray == null) {
			this.componentArray = new Array();
		}
		if (this.templateDataStore == null) {
			this.templateDataStore = new Array();
		}*/

		for (var index = 0; index < responseData.length; index = index + this.PAGE_SIZE) {
			var item = {
					data: responseData.slice(index, index + this.PAGE_SIZE)
			};
			this.templateDataStore.push(item);
			this.recordCount++;
		}
		for (var index = 0; index < responseData.length; index++) {
			var component = new Ext.create(this.uiClass, {
				itemId : Ext.id(),
				autoScroll:false
			});

			var componentConfigData = responseData[index];
			component.controller.modifyComponents(componentConfigData);
			this.componentArray.push(component);
		}

		var index = 0;
		for(; index< this.PAGE_SIZE; index ++) {

			if(this.componentArray[index]== null){
				this.view.down('#showMoreButton').setVisible(false);
				break;
			}

			//this.view.down('#containerPanel').add(this.componentArray[index]);
			this.view.add(this.componentArray[index]);
		}
		
		if(this.componentArray[index]) {
			this.view.down('#showMoreButton').setVisible(true);
		}
		}
	},

	getValues : function() {
		var jsonData = [];
		for (var index = 0; index < this.componentArray.length; index++) {
			if(this.componentArray[index]){
				jsonData.push(this.getData(this.componentArray[index]));
			}	
		}
		return jsonData;
	},

	onShowMoreResultClick : function(but){
		but.setDisabled(true);
		this.activeRecord++;
		var counter = this.activeRecord * this.PAGE_SIZE;	
		for(var index = counter ; index< counter+this.PAGE_SIZE; index ++){

			if(this.componentArray[index]== null){
				but.setText("No more result to display");
				but.setVisible(false);
				break;
			}
			//this.view.down('#containerPanel').add(this.componentArray[index]);
			this.view.add(this.componentArray[index]);
		}

		but.setDisabled(false);
		if(this.templateDataStore[this.activeRecord+1] == null){
			but.setText("No more result to display");
			but.setVisible(false);
		}
	},    

	reloadListPanel : function(responseData){
		//this.view.down('#containerPanel').removeAll();
		this.view.removeAll();
		//this.componentArray = new Array();
		//this.templateDataStore = new Array();
		this.configForm(responseData);
	}
});