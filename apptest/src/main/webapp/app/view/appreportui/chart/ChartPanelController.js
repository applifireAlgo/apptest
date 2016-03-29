Ext.define('Apptest.view.appreportui.chart.ChartPanelController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.chartPanelController',
	
	chartPanelAfterRender:function(){
		var curView=this.getView();
		var chartJson=curView.chartJson;
		var chartPanel=new FusionCharts(chartJson);
		chartPanel.render(curView.body.id);
	}
});