Ext.define('Apptest.view.fw.component.Grids',{
	extend:'Ext.grid.Panel',
	xtype:'grids',
	
	getValues:function()
	{
		var data = [];
		var gridStoreData = this.getStore().getData().items;
		if(gridStoreData && gridStoreData.length>0)
		{
			for (var x = 0; x < gridStoreData.length; x++) {

				for(var counter in gridStoreData[x].data){
					
					if(gridStoreData[x].data[counter] == "" ){
						delete gridStoreData[x].data[counter];	
					}
				}
				
				delete gridStoreData[x].data["id"];	
				data.push(gridStoreData[x].data);
			}
		}
		return data;
	},
	
	getValue : function(){
		return this.getValues();
	},
	
	setValue: function(data){
		this.setData(data);
	},
	
	setData : function(data){
		var store = this.store;
		store.setData(data);
	}
});