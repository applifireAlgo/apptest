
Ext.define('Apptest.view.searchengine.search.DouMeanView', {
	extend : 'Ext.form.Label',
	xtype : 'DouMeanView',

	//margin : '1 1 1 1',
	bodyStyle : 'background:#D8D8D8',
	border : 1,
	requires : [ 'Apptest.view.searchengine.search.DouMeanController' ],

	controller : 'DouMeanController',
	listeners : {
			scope : 'controller',
			afterrender : 'onAfterrender',
		
		},

	onRender : function() {

		this.callParent(arguments);

		this.getEl().on('click', this.onClick, this);
	},

	onClick : function(e, t) {
		topPanelSearchTextBox=Ext.getCmp("topPanelSearchTextBox");
		topPanelSearchButton=Ext.getCmp("topPanelSearchButton");
		topPanelSearchTextBox.setValue(this.newSearchString);
		topPanelSearchButton.fireEvent('click');
	}
});