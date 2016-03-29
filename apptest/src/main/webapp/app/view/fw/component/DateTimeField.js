/*
 * File: DateTimeField.js
 *
 * This file requires use of the Ext JS library, under independent license.
 * This is part of the UX for DateTimeField developed by Guilherme Portela
 */
Ext.define('Apptest.view.fw.component.DateTimeField', {
    extend: 'Ext.form.field.Date',
    alias: 'widget.customdatetimefield',
    xtype : 'customdatetimefield',
    requires: ['Apptest.view.fw.component.DateTimePicker'
               ],

    //<locale>
    /**
     * @cfg {String} format
     * The default date format string which can be overriden for localization support. The format must be valid
     * according to {@link Ext.Date#parse}.
     */
    format: "m/d/Y H:i",
    //</locale>
    //<locale>
    /**
     * @cfg {String} altFormats
     * Multiple date formats separated by "|" to try when parsing a user input value and it does not match the defined
     * format.
     */
    altFormats: "m/d/Y H:i:s|c",
    width: 270,

    mimicBlur: function(e) {
        var me = this,
            picker = me.picker;

        // ignore mousedown events within the picker element
        if (!picker || !e.within(picker.el, false, true) && !e.within(picker.timePicker.el, false, true)) {
            me.callParent(arguments);
        }
    },
    triggerBlur: function() {
        return false;
    },
    collapseIf: function(e) {
        var me = this,
            picker = me.picker;

        if (picker.timePicker && !e.within(picker.timePicker.el,false,true)) {
            me.callParent([e]);
        }
    },
    createPicker: function() {
        var me = this,
            format = Ext.String.format,
            parentPicker = this.callParent(),
            o = {};
            for(var key in parentPicker) {
                if (parentPicker.hasOwnProperty(key) && parentPicker[key]){
                    o[key] = parentPicker[key]
                }
            }
        return new Apptest.view.fw.component.DateTimePicker(o);
    },
    getRefItems: function() {
        var me = this,
            result = me.callParent();

        if (me.picker && me.picker.timePicker){
            result.push(me.picker.timePicker);
        }
        
        return result;
    }, 
    
    setValues :function(value) {
        var res = value.replace(' '+Ext.util.Cookies.get('XA_TID'), '');
                    
        var dt = Ext.Date.parse(res, 'd-m-Y H:i:s');
        if(!dt){
            dt = Ext.Date.parse(res, 'd-n-Y H:i:s');                            
        }
        this.setValue(dt);  

    },
    
    getValues : function(){
        if (this.getSubmitValue() != "" && this.getSubmitValue() != null) {
        	var timezone = Ext.util.Cookies.get('XA_TID');
            var str = ""+this.zeroPad(this.value.getDate(), 2);
            str = str + "-" + this.zeroPad((this.value.getMonth()+1), 2);
            str = str + "-" + (this.value.getYear()- 100 + 2000);
            str = str + " " + this.zeroPad(this.value.getHours(), 2);
            str = str + ":" + this.zeroPad(this.value.getMinutes(), 2);
            str = str + ":" + this.zeroPad(this.value.getSeconds(), 2);
            return str+" "+timezone;
		} else {
			return undefined;
		}
    },
    
    zeroPad : function(num, numZeros) {
	    var n = Math.abs(num);
	    var zeros = Math.max(0, numZeros - Math.floor(n).toString().length );
	    var zeroString = Math.pow(10,zeros).toString().substr(1);
	    if( num < 0 ) {
	        zeroString = '-' + zeroString;
	    }
        return zeroString+n;
    }
});