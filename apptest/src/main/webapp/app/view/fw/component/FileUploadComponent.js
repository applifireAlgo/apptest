Ext.define('Apptest.view.fw.component.FileUploadComponent', {
	
	getValue : function() {
		return this.up().down('#filePathHidden').getValue();
	},
	
	requires :["Apptest.view.fw.frameworkController.FileUploadComponentController"],
	controller : 'FileUploadComponentController',
	xtype : "fileupload",
	extend : 'Ext.form.FieldSet',	
	itemId : 'dataUploadFieldSet',
	cls : 'entity-fieldset',
	collapsed : false,
	collapsible : false,
	
	items : [{
		
		xtype : 'form',
		layout : 'hbox',
		defaults :{
			margin: "5 5 5 5",
		},
		items : [{
			xtype : 'filefield',
			name : 'file',
			flex : 3,
			listeners : {
				change : function(button, e, value) {
					/** Set file limit up to 5mb */
					if(event.target.files[0].size > 5000000){
						Ext.Msg.alert('Error', 'Please upload file under 5mb');	
						this.setRawValue("");					
						return ;					
					}
					var newValue = e.replace(/^c:\\fakepath\\/i, ''); 
					this.setRawValue(newValue);
					this.up().down('#uploadButton').setDisabled(false);
				},
				render : function() {
					var view = this.up();
					this.setFieldLabel(view.fileUploadCaption);
				}
			}
		 },{
			 xtype : 'button',
			action:'dataUpload',
			text : 'Upload',
			itemId : 'uploadButton',
			disabled : true,
			flex : 1,
			handler : 'sendFile'
		 }
		         ]
		
	}]
});