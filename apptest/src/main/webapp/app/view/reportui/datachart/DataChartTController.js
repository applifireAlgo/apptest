Ext.define('Apptest.view.reportui.datachart.DataChartTController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.datacharttController',
	requires : [ 'Apptest.view.reportui.ModifiedProxy' ],
	queryCriteria : null,
	reportView : null,
	reportViewController : null,
	reportQCList : null,
	datagrid : null,
	chartView : null,
	reportJSON : null,
	datapoint : null,
	chartDatas : null,
	mapView : null,
	init : function() {
		this.reportView = this.getView().up();
		this.reportViewController = this.reportView.controller;

		this.getView().add([{
			itemId : "datapanel",
			icon:'images/rpticon/datagrid_icon.png',
			autoScroll : true,
			style : 'background:#f6f6f6;',
			layout:'fit',
			items : [this.reportViewController.getDataGrid()],
			plugins:'responsive',
			responsiveConfig:{
				portrait: {
	                   title:''
	              },
	            landscape: {
	                   title:'Data Grid'
	              }
			}
		},{
			layout : "fit",
			itemId : "chartpanelId",
			icon:'images/rpticon/chart_icon.png',
			width:'100%',
			items : [ {
				xtype : "chart-tabView",
				title:'Analytic',
				itemId : "chart-view",
				reportJSON:this.reportView.reportJSON
			}],
			plugins:'responsive',
			responsiveConfig:{
				portrait: {
	                   title:''
	                },
	            landscape: {
	                   title:'Chart'
	                }
			}
		},{
			layout : "fit",
			itemId : "mappanelId",
			icon:'images/rpticon/map_icon.png',
			items : [ {
				xtype : 'mapPanel',
				bodyStyle : 'background:#D8D8D8'
			}],
			plugins:'responsive',
			responsiveConfig:{
				portrait: {
	                   title:''
	              },
	              landscape: {
	                   title:'Map'
	               }
			}
		},{
			xtype : 'querycriteria',
			icon:'images/rpticon/querycriteria_icon.png',
			plugins:'responsive',
			responsiveConfig:{
				portrait: {
	                   title:''
	              },
	              landscape: {
	                   title:'Search Criteria'
	              }
			}
		}
	 ]);

	},
	afterTabPanelRender:function(tabpanel)
	{
		if(this.reportView.drilled!=undefined && this.reportView.drilled==true){
			tabpanel.setActiveTab(tabpanel.items.items[0]);
		}else{
			if(this.reportJSON.queryCWidgetU[0]!=undefined && this.reportJSON.queryCWidgetU[0].defaultValue=="")
			{
				Ext.Msg.alert({title:'Info',msg:'No data found please enter Search Criteria.',icon:Ext.MessageBox.INFO});
				tabpanel.setActiveTab(tabpanel.items.items[3]);
			}else{
				tabpanel.setActiveTab(tabpanel.items.items[0]);
			}
		}
	},
	initObjects : function() {
		//this.queryCriteria = this.reportView.down("#querycriteria");
		this.queryCriteria = this.reportView.down('#qcInnerPanelId');
		this.datagrid = this.getView().down("#data-grid-view");
		this.chartView = this.getView().down("#chart-view");
		this.chartView.controller.reportView= this.reportView;
		//this.datapoint = this.getView().down("#datapoint");
		this.datapoint=this.reportView.down('#mainDatapoint');
		this.mapView = this.getView().down("#mapPanel");
	},
	refreshData:function(){
		this.filterData(this.datagrid,this);
		
	},
	loadData : function(reportJSON) {
		this.reportJSON = reportJSON;
		this.initObjects();

		// load Query Criteria
		var queryCriteria = this.reportViewController.loadQueryCriteria(this);

		// load chart data
		this.reportViewController.loadDataPointChart(queryCriteria, this);

		// load Grid Data
		this.reportViewController.loadGridData(queryCriteria, this.datagrid,this);
		// load map data
		this.reportViewController.loadMap(queryCriteria,this);
		
		// set display Query Criteria
		this.setReportDetails();

	},
	filterData : function() {
		this.reportViewController.filterData(this.datagrid, this);
		this.getView().setActiveTab(this.getView().down("#datapanel"));
	},

	setReportDetails : function() {
		this.reportQCList = [];
	},
	tabchange : function(tabPanel, newCard, oldCard, eOpts) {
		//resize charts if the active tab is charttab 
		if (newCard.itemId != "datapanel") {
			this.chartView.controller.resizeCharts(this.chartView.down("#chartcolumnlayout"));
		}
	},

});