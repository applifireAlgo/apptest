Ext.define('Apptest.view.appreportui.querycriteria.QueryCriteriaViewController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.queryCriteriaController',
	requires : ['Apptest.view.fw.DateRange'],
	
	/**After QC Panel ias added to the container*/
	afterQCPanelAdded:function()
	{
		var currentView = this.getView();
		//Auto Refresh Code
		var advanceConfigJson = currentView.reportDataJson.advanceConfigJson;

		var autoRefreshCheckbox = currentView.down("#isAutoRef");
		var combobox = currentView.down("#cmbrefInterval");

		if (advanceConfigJson != undefined && advanceConfigJson.hasOwnProperty("auto_refersh") && advanceConfigJson["auto_refersh"] == "on") {

			if (advanceConfigJson.hasOwnProperty("interval")) {
				combobox.setValue(advanceConfigJson["interval"]);
				autoRefreshCheckbox.expand();
			}
		} else {
			autoRefreshCheckbox.collapse();
			combobox.setValue(null);
		}
		
		// Check whether default value is present or not in QC and throw pop up accordingly
		if(this.getView().reportDataJson.queryCWidgetU[0]!=undefined && this.getView().reportDataJson.queryCWidgetU[0].defaultValue=="")
		{
			Ext.Msg.alert({title:'Info',msg:'No data found please enter Search Criteria.',icon:Ext.MessageBox.INFO});
			//var centerTabPanel=this.getView().reportView.down('#centerTabPanelId');
		}
		
		// set Query Criteria widgets
		var qcInnerPanel=currentView.down('#qcInnerPanelId');
		qcInnerPanel.add(currentView.reportDataJson.queryCWidgetU);
		
		var widgets=qcInnerPanel.items.items;

		// bind method to parent widget if dependency is defined
		this.bindDepandantWidget(widgets);
		this.setHrefValues(currentView, currentView.reportView);

		// load Services of Q C Widgets
		this.loadWidgetsServices(widgets);

		this.setDefaultValue(widgets);
	},
	
	/**This method is called to fetch queryCriteria json which is to be passed as params for getting report data*/
	fetchQueryCriteria:function(){
		var queryCriteria = [];
		this.getQueryCriteria(queryCriteria);
		
		//This is added for drill down functionality
		if (this.getView().reportView.rptHrefQC != undefined &&  this.getView().reportView.rptHrefQC.length!=0) {
			var hrefqc = this.getView().reportView.rptHrefQC;
			var qcfilter = [];
			for (var x = 0; x < queryCriteria.length; x++) {
				var match = false;
				for (var x1 = 0; x1 < hrefqc.length; x1++) {
					if (hrefqc[x1].name == queryCriteria[x].name) {
						match = true;
						break;
					}
				}
				if (match == false) {
					qcfilter.push(queryCriteria[x]);
				}
			}
			queryCriteria = Ext.Array.merge(qcfilter, this.getView().rptHrefQC);

		}
		if (this.getView().reportView.drilled != undefined && this.getView().reportView.drilled == true) {
			this.disableQCValues();
		}
		
		return queryCriteria;
	},
	
	getQueryCriteria : function(qc) {
		Ext.Array.each(this.getView().down('#qcInnerPanelId').items.items, function(item, idx, items) {
			var val;
			if (item.xtype == "adatepickerfield" || item.xtype == 'datefield') {
				val = item.getValue() != null ? item.getValue().getTime() : item.getValue();
				var defaultJson = {
					name : item.name,
					fromui : true,
					label : item.getFieldLabel(),
					datatype : item.datatype,
					value : val,
					index : item.index
				};
				if (item.sessionInput != undefined) {
					defaultJson.sessionInput = item.sessionInput;
					defaultJson.attributeName = item.attributeName;
					defaultJson.attributeDatatype = item.attributeDatatype;
				}
				this.filterCondition.push(defaultJson);
			} else if (item.xtype == "daterange") {
				this.filterCondition.push({
					name : item.fromname,
					fromui : true,
					label : "From Date",
					datatype : item.fromdatatype,
					dispValue : new Date(item.getFromDate()),
					value : item.getFromDate(),
					index : item.index
				});
				this.filterCondition.push({
					name : item.toname,
					fromui : true,
					label : "To Date",
					datatype : item.todatatype,
					dispValue : new Date(item.getToDate()),
					value : item.getToDate(),
					index : (parseInt( item.index)+1).toString()
				});
			} else {
				val = item.getValue();
				var defaultJson = {
					name : item.name,
					label : item.getFieldLabel(),
					fromui : true,
					datatype : item.datatype,
					value : val,
					index : item.index
				};
				if (item.sessionInput != undefined) {
					defaultJson.sessionInput = item.sessionInput;
					defaultJson.attributeName = item.attributeName;
					defaultJson.attributeDatatype = item.attributeDatatype;
				}
				this.filterCondition.push(defaultJson);
			}
		}, {
			filterCondition : qc
		});
	},
	
	/** this method is used to disable query criteria when the report is drilldown*/
	disableQCValues : function() {
		var centerTabPanel=this.getView().reportView.down('#centerTabPanelId');
		centerTabPanel.child('#querycriteria').tab.hide();
		Ext.Array.each(this.getView().down('#qcInnerPanelId').items.items, function(item, ind, items) {
			item.setDisabled(true);
		}, {
			scope : this
		});
		this.getView().down('#qcInnerPanelId').down("#btnClear").setDisabled(true);
		this.getView().down('#qcInnerPanelId').down("#btnSearch").setDisabled(true);

	},
	
	setHrefValues : function(qcPanel, reporUiObj) {
		var rptHrefQCUpd = [];
		Ext.Array.each(reporUiObj.rptHrefQC, function(item, indx, items) {
			var widgets = this.qcPanel.query("[name=" + item.name + "]");
			if (widgets.length > 0) {
				widgets[0]["hrefval"] = item.value;
				if (widgets[0].xtype == 'adatepickerfield') { // ||
					try {
						widgets[0].setValue(Ext.Date.parse(item.value, 'Y-m-d'));
					} catch (e) {
					}
				} else {
					widgets[0].setValue(item.value);
				}

			} else {
				this.rptHrefQCUpd.push(item);
			}
		}, {
			qcPanel : qcPanel,
			rptHrefQCUpd : rptHrefQCUpd
		});
		reporUiObj.rptHrefQC = rptHrefQCUpd;

	},
	bindDepandantWidget : function(widgets) {
		Ext.Array.each(widgets, function(item, ind, items) {
			if (item.parentWidget > 0) {
				var query = this.getView().query('[widgetId=' + item.parentWidget + ']');
				if (query.length > 0) {
					var widget = query[0];
					widget.on('change', this.refreshDepandantWidget);
				}
			}
		}, this);
	},
	refreshDepandantWidget : function(sender, newValue, oldValue, eOpts) {
		var mainQueryPanel=sender.up().up().up();
		var queryPanel = mainQueryPanel.down('#qcInnerPanelId');
		Ext.Array.each(queryPanel.items.items, function(item, idx, items) {
			if (this.sender.widgetId == item.parentWidget) {
				var params = this.scope.getQueryCriteriaParams(this.queryPanel, item);
				var params = {};
				params["findKey"] = newValue;
				this.scope.renderComboboxStore(item, params);
			}
		}, {
			sender : sender,
			scope : mainQueryPanel.controller,
			queryPanel : queryPanel
		});
	},
	renderComboboxStore : function(com, param) {
		var me=this;
		Ext.Ajax.request({
			url : 'secure' + com.serviceUrl,
			method : 'POST',
			async:false,
			scope : me,
			com:com,
			jsonData : param,
			success : function(response,currentObject, options) {
				var responseJson = Ext.JSON.decode(response.responseText);
				if (responseJson.response.success == true) {
					var data = responseJson.response.data;
					currentObject.scope.loadCombobox(currentObject.com,data);
				} else {
					Ext.Msg.alert('Error',responseJson.response.message);
				}
			},
			failure : function() {
				Ext.Msg.alert('Error','Cannot connect to server');
			}
		});
	},
	getQueryCriteriaParams : function(panel, refWidget) {
		var attrParms = "";
		panel.items.each(function(item) {
			if (refWidget.widgetId != item.widgetId) {
				if (attrParms.length > 0) {
					attrParms = attrParms + "&";
				}
				attrParms = attrParms + item.attribute + ":" + item.getValue();
			}

		});
		return attrParms;
	},
	loadWidgetsServices : function(widgets) {
		Ext.Array.each(widgets, function(widget, idx, items) {
			if (widget.serviceId != undefined && widget.serviceId.length > 0 && widget.parentWidget == 0) {

				this.loadDataInWidget(widget);
			}
		}, this);
	},
	setComboboxData : function(data, widget) {
		store = this.getStoreFromAPI(response[0], com.attribute != undefined ? com.attribute : "");
		com.bindStore(store);
		com.displayField = "primaryDisplay";
		com.valueField = "primaryKey";
	},
	loadDataInWidget : function(widget) {
		var me=this;
		Ext.Ajax.request({
			url : 'secure' + widget.serviceUrl,
			method : 'GET',
			async:false,
			scope : me,
			widget:widget,
			jsonData :{},
			success : function(response,currentObject, options) {
				var responseJson = Ext.JSON.decode(response.responseText);
				if (responseJson.response.success == true) {
					var data = responseJson.response.data;
					currentObject.scope.loadCombobox(currentObject.widget,data);
				} else {
					Ext.Msg.alert('Error',responseJson.response.message);
				}
			},
			failure : function() {
				Ext.Msg.alert('Error','Cannot connect to server');
			}
		});
	},
	loadCombobox : function(widget, data) {
		var fields = data.length > 0 ? Ext.Object.getKeys(data[0]) : [];
		var store = Ext.create('Ext.data.Store', {
			autoLoad : true,
			fields : fields,
			data : data,
			sorters : 'primaryDisplay'
		});
		widget.reset();
		widget.bindStore(store);
		widget.displayField = "primaryDisplay";
		widget.valueField = "primaryKey";
		widget.queryMode = 'local';
		widget.on("change", function(value, vae1, cmb) {});
		if (widget.hrefval != undefined) {
			widget.setValue(widget.hrefval);
		}
	},
	
	/**On Search Button Click*/
	/**This method is also called from filterdaterange function in reportview controller*/
	filterData : function(btn) {
		var reportVIEW=this.getView().reportView;
		var datagrid=reportVIEW.down('#dataGridViewId');
		var centerTabPanel=reportVIEW.down('#centerTabPanelId');
		centerTabPanel.setActiveTab(centerTabPanel.items.items[0]);
		
		var store=datagrid.getStore();
		store.getProxy().extraParams = reportVIEW.controller.getGridParams();
		
		reportVIEW.controller.getDataPointChartDataByAjax();
		store.load();
	},
	
	/**OnClear Button Click*/
	clearData : function() {
		Ext.Array.each(this.getView().down('#qcInnerPanelId').items.items, function(item, ind, items) {
			try {
				item.reset();
				//item.setValue(item.defaultValue);
				if (item.hasOwnProperty("parentWidget") && item.parentWidget != 0) {
					item.store.removeAll();
				}
			} catch (Ex) {
			}
		}, this);
		//this.filterData();
	},
	setDefaultValue : function(widgets) {
		Ext.Array.each(widgets, function(item, ind, items) {
			if (item.defaultValue.length > 0) {
				if (item.xtype != 'combobox') {
						var value = "";
						if (item.hrefval != undefined) {
							value = item.hrefval;
						} else {
							value = item.defaultValue;
						}
						if (item.xtype == 'datefield') {
							if (item.hrefval != undefined) {
								// value
								// widget.setValue(item.hrefval);
							} else {
								
								item.setValue(this.getDefaultDateValue(value));
							}
						} else if (item.xtype == 'daterange') {
							var rec = item.down("#defaultsDate").getStore().find("text", item.defaultValue);
							if (rec != -1) {
								item.down("#defaultsDate").setValue(item.down("#defaultsDate").getStore().getAt(rec).data.name);
							}
						} else {
							item.setValue(value);
						}

					}
			}
		}, this);
	},
	getDefaultDateValue : function(defaultVal) {
		// dDate defaultDate;
		var defaultDate = "";

		if (defaultVal.toUpperCase() == "TODAY") {
			defaultDate = new Date();
		}
		return defaultDate;
	},

	/**On collapse event of auto refresh checkbox so that refresh interval of data is made off*/
	onUncheckAutoRefresh:function(){
		currentView = this.getView();
		currentView.down("#cmbrefInterval").setValue(null);
		this.getView().reportView.controller.stopTimer(this.getView().reportView.controller);
	},
	
	/**On Refresh Interval Combo change event */
	changeTimers : function(cmb) {
		currentView = this.getView();
		if (currentView.down("#isAutoRef") != undefined && currentView.down("#isAutoRef").collapsed == false) {
			this.getView().reportView.controller.updateTimer(this.getView().reportView.controller, cmb.getValue());
		}
	}
});