Ext.define('Apptest.view.appreportui.chart.ChartViewController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.chartViewController',
	defaultColumnNo:null,
	
	/**This method is used to initialize the default columns
	   configured by user for viewing chart or else set to 2 by default*/
	afterChartViewAdded:function(panel){
		var defaultColumnNo=this.getView().reportJSON.data_json.defaultCColumn==null?2:parseInt(this.getView().reportJSON.data_json.defaultCColumn);
		this.defaultColumnNo=defaultColumnNo;
		if(this.defaultColumnNo!=2){
			//this.getView().header.items[0].items[defaultColumnNo-1].checked=true;
			//this.getView().getHeader().items[0].value=defaultColumnNo;
			//this.getView().plugins[0].config.responsiveConfig.landscape.config.layout.columns=defaultColumnNo;
		}
	},
	
	/*onColumnNoSelect:function(combo, records, eOpts){
		var curView=this.getView();
		curView.layout.columns=records.data.value;
		curView.doLayout();
		curView.plugins[0].config.responsiveConfig.landscape.config.layout.columns=records.data.value;
	},*/
	
	/**On radio button click for changing the chart view column format*/
	onChangeColumnRadio:function(th,newValue, oldValue, eOpts ){
		//console.log("ID"+th.id);
		var curView=this.getView();
		curView.layout.columns=newValue.rb;
		curView.doLayout();
		curView.plugins[0].config.responsiveConfig.landscape.config.layout.columns=newValue.rb;
	},
	
	/**This method is called from reportview controller for loading group as well as single charts*/
	loadCharts:function(chart)
	{
		if(chart.length<=1){
			//this.getView().header.items[0].defaults.hidden=true;
			//this.getView().header.items[0].disabled=true;
		}
		this.getView().removeAll();
		for(var i=0;i<chart.length;i++){
			if(chart[i].hasOwnProperty("group")&& chart[i].group==true){
				this.getView().add({xtype:'groupChartPanel',"groupChartJson":chart[i].groupCharts,
							title:chart[i].chartTitle,
							colspan:this.defaultColumnNo
						});
			}else{
				this.getView().add({xtype:'chartPanel',"chartJson":chart[i],title:chart[i].chartTitle});
			}
		}
	}
});