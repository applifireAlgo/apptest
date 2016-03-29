Ext.define('Apptest.view.reportui.ReportView', {
	extend : 'Ext.panel.Panel',
	requires : [ 'Apptest.view.reportui.querycriteria.QueryCriteriaView',
			'Apptest.view.reportui.datachart.DataChartViewTab',
			'Apptest.view.reportui.datachart.DataChartViewPanel',
			'Apptest.view.reportui.ReportViewController' ,
			'Apptest.view.fw.MainDataPointPanel',
			'Apptest.view.googlemaps.map.MapPanel'
			],
	xtype : 'reportview',
	controller : 'reportviewController',
	layout : 'border',
	isCustomReport:false,
	reportWidgets :["1","2","3","4"],
	//autoScroll : true,
	//margin : '3 0 5 0',
	height:500,
	width:"100%",
	listeners : {
		scope : 'controller',
		afterrender : 'renderReport'
	}
});
