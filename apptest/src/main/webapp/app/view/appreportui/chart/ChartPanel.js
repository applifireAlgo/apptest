Ext.define('Apptest.view.appreportui.chart.ChartPanel', {
	extend : 'Ext.panel.Panel',
	requires:['Apptest.view.appreportui.chart.ChartPanelController'],
	controller:'chartPanelController',
	xtype : 'chartPanel',
	chartJson:null,
	title:'Panel',
	layout:{
		type:'fit',
		align:'center',
		pack:'center'
	},
	listeners:{
		afterrender:'chartPanelAfterRender'
	}
});