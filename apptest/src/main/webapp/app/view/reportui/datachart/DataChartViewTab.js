Ext.define('Apptest.view.reportui.datachart.DataChartViewTab', {
	extend : 'Ext.tab.Panel',
	requires : [ 'Apptest.view.reportui.datachart.DataChartTController',
	             'Apptest.view.reportui.datachart.datagrid.DataGridView',
	             'Apptest.view.reportui.datachart.chart.ChartTabView',
	             'Apptest.view.reportui.datachart.ChartPointView' ],
	controller : 'datacharttController',
	xtype : 'datachart-tabpanel',
	tabPosition : 'bottom',
	bodyStyle : 'background:#D8D8D8',
	listeners : {
		scope : "controller",
		tabchange : 'tabchange',
		afterrender:'afterTabPanelRender'
	}
});