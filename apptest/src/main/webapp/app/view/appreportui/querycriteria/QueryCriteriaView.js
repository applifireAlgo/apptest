Ext.define('Apptest.view.appreportui.querycriteria.QueryCriteriaView',{
	extend : 'Ext.panel.Panel',
	requires : [ 'Apptest.view.appreportui.querycriteria.QueryCriteriaViewController' ],
	xtype : 'queryCriteriaPanel',
	controller : 'queryCriteriaController',
	bodyPadding : '10',
	bodyStyle: 'background:#D8D8D8;',
	itemId : 'querycriteria',
	reportDataJson:null,
	reportView:null,
	items:[{
		xtype:'panel',
		bodyPadding:'20',
		items:[{
	        xtype:'fieldset',
	        title: 'Auto Refresh',
	        checkboxName:'auto_refersh',
	        itemId:'isAutoRef',
	        checkboxToggle: true,
	        collapsed: true,
	        layout:'anchor',
	        bodyPadding:'10',
	        listeners:{
	        	collapse:'onUncheckAutoRefresh'
	        },
	        items :[{
	    		xtype : 'combo',
	    		itemId:'cmbrefInterval',
	    		fieldLabel : 'Refresh Interval',
	    		labelWidth:110,
	    		store : Ext.create('Ext.data.Store', {
	    			fields : [ 'displayValue', 'value' ],
	    			data : [ {
	    				"displayValue" : "5 Seconds",
	    				"value" : 5000
	    			}, {
	    				"displayValue" : "10 Seconds",
	    				"value" : 10000
	    			}, {
	    				"displayValue" : "20 Seconds",
	    				"value" : 20000
	    			}, {
	    				"displayValue" : "30 Seconds",
	    				"value" : 30000
	    			}, {
	    				"displayValue" : "1 Minute",
	    				"value" : 60000
	    			}, {
	    				"displayValue" : "3 Minute",
	    				"value" : 180000
	    			}, {
	    				"displayValue" : "5 Minute",
	    				"value" : 300000
	    			},
	    			//...
	    			]
	    		}),
	    		name : "interval",
	    		queryMode : 'local',
	    		displayField : 'displayValue',
	    		valueField : 'value',
	    		listeners:{
	    			select:'changeTimers'
	    		}
	    	}]
	    
		},{
			xtype:'panel',
			border:true,
			  style: {
		          borderColor: "#D8D8D8",
		          borderWidth: "2px",
		          borderStyle: "solid"
		     },
			bodyPadding:'10',
			itemId:'qcInnerPanelId',
			items:[],
			buttons:[{
				text : 'Clear',
				itemId:"btnClear",
				icon : 'images/closable.png',
				listeners:{
					click:'clearData'
				}
			}, {
				text : 'Search',
				itemId:"btnSearch",
				icon : 'resources/css/images/search.png',
				listeners:{
					click:'filterData'
				}
			}]
		}],
		plugins:'responsive',
		responsiveConfig:{
			portrait: {
                width:'100%'
            },
            landscape: {
               width:'35%'
            }
		}
	}],
	listeners:{
		scope:'controller',
		added:'afterQCPanelAdded'
	}
});