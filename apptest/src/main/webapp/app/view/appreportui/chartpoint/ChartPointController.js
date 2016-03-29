Ext.define('Apptest.view.appreportui.chartpoint.ChartPointController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.chartPointController',

	/**This method is called from reportview controller on loading chartpoints*/
	fetchChartPoints:function(datapoints){
		var chartpoints = [];
		for (var x = 0; x < datapoints.length; x++) {
			// check if type of data point if the type is chart then set chatjson that used in data point widget to create chart point
			if (datapoints[x].hasOwnProperty("type") && datapoints[x].type.toLowerCase() == 'chart') {
				for (var x1 = 0; x1 < datapoints[x].chartJson.length; x1++) {
					chartpoints.push({
						chartJSON : datapoints[x].chartJson[x1]
					});
				}
			}
		}
		if (chartpoints.length > 0) {
			this.loadChartPoints(chartpoints);
		}
	},
	
	loadChartPoints : function(chartpoints) {
		this.getView().setHeight(this.getView().defualtHeight);
		var fusionchart;
		var width = Math.ceil(100/ chartpoints.length);
		var widthpixel=Math.ceil(1024/ chartpoints.length)
		for (var x = 0; x < chartpoints.length; x++) {
			chartpoints[x].chartJSON.height=this.getView().height;
			chartpoints[x].chartJSON.width=widthpixel;
			panel = this.getView().add({
				xtype : "panel",
				height : this.getView().height,
				margin : '5 5 0 0',
				width:width+"%",
				chartJSON : chartpoints[x].chartJSON,
				listeners : {
					afterrender : 'loadfusionchart1'
				}
			});
			
		}
		this.getView().doLayout();
		
	},
	loadfusionchart1 : function(panel) {
		var fusionchart = new FusionCharts(panel.chartJSON);
		fusionchart.render(panel.body.id);
		panel.doLayout();
	},
});