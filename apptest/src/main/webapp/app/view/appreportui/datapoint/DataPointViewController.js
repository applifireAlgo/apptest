Ext.define('Apptest.view.appreportui.datapoint.DataPointViewController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.datapointController',
	datapoints:null,
	isDataPointGroupped:null,
	totalDp:null,
	isResized:false,
	
	/**Used from ReportViewController.js*/
	loadDataPoints:function(_datapoints,_isDataPointGroupped,_totalDp){
		this.datapoints=_datapoints;
		this.isDataPointGroupped=_isDataPointGroupped;
		this.totalDp=_totalDp;
		
		var totalWidth=this.getView().getWidth();
		this.createDataPoints(totalWidth);
		this.isResized=true;
		
	},
	createDataPoints:function(totalWidth){
		if(this.isDataPointGroupped==true){
			this.plotGroupedDP(this.datapoints,this.totalDp,totalWidth);
		}else{
			this.plotLinearDP(this.datapoints,this.totalDp,totalWidth);
		}
	},
	/**This method is called on datapoint panel resize event*/
	onResizeDataPointView:function(panel, width, height, oldWidth, oldHeight, eOpts){
		if(this.isResized==true){
			this.isResized = false;
			this.getView().down('#maindatapointpanel').removeAll();
			this.createDataPoints(width);
			this.isResized = true;
		}
	},
	
	plotLinearDP : function (datapoints,totalDp,totalWidth)
	{
		var minDpWidth=170;
		
		var widthReq=totalDp*minDpWidth;
		if(widthReq<totalWidth){
			minDpWidth = Math.round(100/totalDp) +"%";
		}
		 
		var dpPanel = this.view.down("#maindatapointpanel");
		dpPanel.removeAll();
		var colorArray = [ "#66B92F", "#E67E22", "#F47564", "#337ab7", "#d9534f", "#F0D65E", "#cc99ff", "#57BCD9", "#006600", "#FF4AFF" ];
		for(var dpCounter = 0, j=0; dpCounter < datapoints.length; dpCounter++){
			
			if(j==colorArray.length){
				j=0;
			}
			datapoints[dpCounter].value[0].color=colorArray[j];
			j++;
			datapoints[dpCounter].value[0].width="95%";
			dpPanel.add({
					xtype:'dataPointPanel',
					width: minDpWidth,
					minWidth : minDpWidth,
					dpData:datapoints[dpCounter].value[0],
			});
		}
	},
	
	plotGroupedDP : function(datapoints,totalDp,totalWidth)
	{
		//Width Calculation logic
		var minDpWidth=170;
		
		var widthReq=totalDp*minDpWidth;
		if(widthReq<totalWidth){
			minDpWidth=Math.round(totalWidth/totalDp);
		}
		
		var dpPanel = this.view.down("#maindatapointpanel");
		dpPanel.removeAll();
		var colorArray = [ "#66B92F", "#E67E22", "#F47564", "#337ab7", "#d9534f", "#F0D65E", "#cc99ff", "#57BCD9", "#006600", "#FF4AFF" ];
		
		for(var dpCounter = 0,j=0; dpCounter < datapoints.length; dpCounter++){
			
			//To check whether the datapoint has data in it then only plot them
			if(datapoints[dpCounter].value.length!=0){
				//Inner loop for assigning color to each datapoint
				for(var i=0;i<datapoints[dpCounter].value.length;i++){
					if(j==colorArray.length){
						j=0;
					}
					datapoints[dpCounter].value[i].color=colorArray[j];
					j++;
					datapoints[dpCounter].value[i].width=minDpWidth+"px";
				}
				/*var panelWidth=minDpWidth*datapoints[dpCounter].value.length;
				//Extra width is added to panelWidth coz of margins
				panelWidth=panelWidth + Math.round(6.25*datapoints[dpCounter].value.length);*/
				dpPanel.add({
							xtype:'dataPointPanel',
							//width:panelWidth,
							title : datapoints[dpCounter].label,
							dpData:datapoints[dpCounter].value,
							style:{
								border:'2px solid white'
							}
				});
			}
		}
	}

});