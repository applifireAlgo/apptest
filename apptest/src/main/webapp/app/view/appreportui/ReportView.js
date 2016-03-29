Ext.define('Apptest.view.appreportui.ReportView', {
	extend : 'Ext.form.Panel',
	requires : ['Apptest.view.appreportui.ReportViewController',
	            'Apptest.view.appreportui.datagrid.DataGridPanel',
	            'Apptest.view.appreportui.datagrid.DataGridView',
	            'Apptest.view.appreportui.querycriteria.QueryCriteriaView',
	            'Apptest.view.appreportui.chart.ChartView',
	            'Apptest.view.appreportui.datapoint.DataPointView',
	            'Apptest.view.googlemaps.map.MapPanel',
	            'Apptest.view.appreportui.chartpoint.ChartPointView'
	            ],
	xtype : 'reportView',
	controller : 'reportViewController',
	layout : 'border',
	reportJSON:null,
	listeners : {
		scope : 'controller',
		afterrender : 'afterRenderReport',
		boxready : 'fetchReportData'
	}
});
