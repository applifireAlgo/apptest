Ext.define('Apptest.view.appreportui.chart.ChartView', {
	extend : 'Ext.panel.Panel',
	requires:['Apptest.view.appreportui.chart.ChartViewController',
	          'Apptest.view.appreportui.chart.ChartPanel',
	          'Apptest.view.appreportui.chart.GroupChartPanel'],
	controller:'chartViewController',
	xtype : 'chartViewPanel',
	title:'Analytics',
	autoScroll:true,
	bodyPadding:'2 0 0 0',
	defaults:{
		margin:'0 2 0 0'
	},
	itemId:'chartViewPanelId',
	plugins: 'responsive',
	responsiveConfig: {
	   //Tab Device
       portrait: {
    	   layout: {
    	        type: 'table',
    	        columns : 1,
    	        tableAttrs: {
    	        	 style: {
    	        		 width: "100%"
    	        	 }
    	        },
    	        tdAttrs : {
    	            align : 'center',
    	            valign : 'middle',
    	        }
    		}
        },
        //Desktop
        landscape: {
        	layout: {
                type: 'table',
                columns :2,
                tableAttrs: {
                	 style: {
                		 width: "100%"
                	 }
                },
                tdAttrs : {
                    align : 'center',
                    valign : 'middle',
                }
        	}
        }
    },
	header: {
        xtype: 'header',
        titlePosition: 0,
        defaults: {
            padding: '0 0 0 0'
        },
        items: [{

            xtype: 'radiogroup',
            //itemId:'columnRadio',
            defaults:{margin:'0 2 0 0'},
            items: [
                { boxLabel: '', name: 'rb', inputValue: 1 },
                { boxLabel: '', name: 'rb', inputValue: 2 ,checked:true  },
                { boxLabel: '', name: 'rb', inputValue: 3 },
                { boxLabel: '', name: 'rb', inputValue: 4 },
                { boxLabel: '', name: 'rb', inputValue: 5 },
                { boxLabel: '', name: 'rb', inputValue: 6 }
            ],
            listeners:{
            	change:'onChangeColumnRadio'
            },
            plugins: 'responsive',
			responsiveConfig: {
               portrait: {
                    visible: false,
                },
                landscape: {
                    visible: true
                }
            }
        
        },/*{
            xtype: 'combo',
            width:'4%',
            itemId:'columnCombo',
            displayField:'name',
            valueField:'value',
            value:2,
            store: Ext.create('Ext.data.Store', {
                fields : ['name', 'value'],
                data   : [
                    {name : '1', value: 1},
                    {name : '2', value: 2},
                    {name : '3', value: 3},
                    {name : '4', value: 4},
                    {name : '5', value: 5},
                    {name : '6', value: 6}
                ]
            }),
            listeners:{
            	select:'onColumnNoSelect',
            },
            plugins: 'responsive',
			responsiveConfig: {
               portrait: {
                    visible: false,
                },
                landscape: {
                    visible: true
                }
            }
     }*/]
    },
    listeners:{
    	scope:'controller',
    	added:'afterChartViewAdded'
    }
});