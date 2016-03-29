/** For giving custom behaviour to our RADIOGROUP component this class has been added */

Ext.define('Apptest.view.fw.component.CustomRadioGroup', {
	extend:'Ext.form.RadioGroup',
	xtype : 'customradiogroup',

	/** normal getValue() method of radio group returns 
	 * an object with radiogroup's name as key n radiogroup's value as value 
	 * but for using its value to send to our services
	 * we need this method which returns just the value */	
	getCmpValue : function() {
		var value = this.getValue();
		var key = this.name;
		return value[key];
	},
	
	/** As unlike setValue() method of all other Extjs components 
	 * RadioGroup needs value to set in form of json object
	 * having component name as key and value to set as value
	 * so this method has been written to do the same */	
	setCmpValue : function(valueToSet) {
		var value = {};
		var key = this.name;
		value[key] = valueToSet;
		this.setValue(value);
	}

});