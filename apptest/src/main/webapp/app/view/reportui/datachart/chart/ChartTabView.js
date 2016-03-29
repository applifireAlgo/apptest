Ext.define('Apptest.view.reportui.datachart.chart.ChartTabView', {
	extend : 'Ext.panel.Panel',
	requires:['Apptest.view.reportui.datachart.chart.RatingField',
	          'Apptest.view.reportui.datachart.chart.ChartController'],
	xtype : 'chart-tabView',
	controller : 'chartController',
	//bodyStyle : 'background:#D8D8D8',
	overflowY: 'auto',
	//autoScroll : true,
	border : 0,
	margin : '0 0 5 0',
	layout : {
		type : 'table',
		columns : 4
	},
	initComponent : function()
	{
		var showdaterange=1;
		var tools=[];
		tools.push({
			xtype : 'ratingField',
			itemId:'chartcolumnlayout',
			hidden:false,
			RatingFieldTypeid : 'rating',
			numberOfStars : 6,
			tooltip : 'Choose columns',
			defaultHeightToReduce : 1,
			defaultWidthToReduce : 60,
			minLength : 1,
			value : 1,
			allowBlank : false,
			listeners : {
				change : 'resizeCharts',
				beforerender:'initObject'
			},
			plugins: 'responsive',
			responsiveConfig: {
               portrait: {
                    visible: false,
                    //value : 1,
                },
                landscape: {
                    visible: true
                }
            }
		});
		this.tools=tools;
		this.callParent(arguments);
	},
	listeners : {
		scope : 'controller',
		afterrender : 'afterRender',
		resize:'onPanelResize'
	}
});
