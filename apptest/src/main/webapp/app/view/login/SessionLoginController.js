Ext.define('Apptest.view.login.SessionLoginController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.sessionLoginCtrll',
	
	onSessionLoginClick : function() {

		
		var form = this.lookupReference('form');
		var container = this.view.container;

		var currentObject = this;
		if (form.isValid()) {
			
			Ext.Ajax.request({
				url : "secure/Authentication/reauthenticate",
				method : 'POST',
				controller : currentObject,
				maskEnable: true,
				jsonData : {
					"loginId" : '',
					"password" : form.getValues().password
				},
				success : function(response, scope) {
					
					var jsonResponse = Ext.JSON.decode(response.responseText);

					if (jsonResponse.response.success) {
						
						sessionTimeOutFlag = false;
						form.up().close();
					} else {
						if (jsonResponse.response.changePassword) {
							Ext.Msg.confirm('Confirm', jsonResponse.response.message, function(id, value) {
								if (id == 'yes') {
									// show change password screen
									sessionWindow=scope.controller.getView();
									sessionWindow.close();
									
									var changePwdWindow = Ext.create('Ext.window.Window', {
										width : '25%',
										height : 250,
										layout : 'fit',
										modal : true,
										resizable : true,
										closeAction : 'close',
										plain : true,
										title : 'Change Password',
										autoDestroy:true,
										closable:false
								});
								changePwdWindow.add(Ext.create('Apptest.view.usermanagement.enduser.ChangePwd'));
								changePwdWindow.show();
								}
							}, {
								controller : this,
								scope : scope
							});
						} else {
							Ext.Msg.alert({
								title : 'Info',
								msg : jsonResponse.response.message,
								icon : Ext.MessageBox.WARNING
							});
						}
					}
				},
				failure : function(response) {
					var jsonRespone = Ext.JSON.decode(response.responseText);
					Ext.Msg.alert('Authentication failed', jsonRespone.response.message);
				}
			});
		}
	}
});