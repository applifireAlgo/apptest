Ext.define('Apptest.view.fw.component.CustomCheckBoxGroup', {
	extend:'Ext.form.CheckboxGroup',
	xtype : 'customcheckboxgroup',

//	getCmpValue : function(){
//	var value = this.getValue();
//	if(this.name && value[this.name])
//	return value[this.name];
//	return value;
//	}

	getCmpValue : function(){
		return this.getValue()[this.name];
	},

	setCmpValue : function(data) {
		var cbGroupItems = this.items.items;
		for(var index = 0; index < cbGroupItems.length; index++) {
			var checkBox = cbGroupItems[index];

			for(var index1 = 0; index1 < data.length; index1++) {
				var cBoxName = checkBox.name;
				if(checkBox.inputValue[cBoxName] == data[index1][cBoxName]) {
					checkBox.setValue(true);
					break;
				}
			}
		}
	}

});