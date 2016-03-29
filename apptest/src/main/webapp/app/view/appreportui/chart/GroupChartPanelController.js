Ext.define('Apptest.view.appreportui.chart.GroupChartPanelController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.groupChartPanelController',
	
	grpChartPanelAfterRender:function(){
		var curView=this.getView();
		var groupChartJson=curView.groupChartJson;
		
		for(var i=0;i<groupChartJson.length;i++){
			this.getView().plugins[0].config.responsiveConfig.landscape.config.layout.columns=groupChartJson.length;
			this.getView().add({xtype:'chartPanel',"chartJson":groupChartJson[i],title:''});
		}
		
	}
});