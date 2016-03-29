Ext.define('Apptest.view.fw.DataPointPanelController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.dataPointPanelController',
	
	init: function(){
		
		this.dataPointPanel = this.view.down("#dataPointPanelId");
		this.dataPointPanel.setData(this.view.dpData);
	}
});