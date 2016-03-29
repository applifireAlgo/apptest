Ext.define('Apptest.view.usermanagement.enduser.ChangePwdController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.changePwdController',

	validatePassword : function() {
		
		var newPassword = this.getView().down('#newPassword').getValue();
		var reTypeNewPassword = this.getView().down('#reTypeNewPassword').getValue();
		if (newPassword == reTypeNewPassword)
			return true;
		else
			return "Passwords do not match!";
	},

	onChangePasswordClick : function(btn, opts) {
		
		var form = btn.up().up();
		if (form.isValid()) {
			var formData = form.getValues();
			delete formData.reTypeNewPassword;

			var entMask = new Ext.LoadMask({
				msg : 'Updating...',
				target : this.getView()
			}).show();

			Ext.Ajax.request({
				timeout : 180000,
				url : "secure/PasswordGenerator/changePassword",
				method : 'PUT',
				entMask : entMask,
				jsonData : formData,
				me : this,
				success : function(response, sender) {
					var responseText = Ext.JSON.decode(response.responseText);
					if (responseText.response.success) {
						sender.entMask.hide();
						Ext.Msg.alert("Info", responseText.response.message);
						
						sender.me.onResetPasswordClick();
						
						var pathName= this.location.pathname;
						var initialPath=pathName.slice(0,pathName.lastIndexOf("/"));
						Ext.util.Cookies.clear('changePwd',initialPath);
						
						this.location.reload();
					} else {
						sender.entMask.hide();
						Ext.Msg.alert("Info", responseText.response.message);
					}
				},
				failure : function(response, sender) {
					sender.entMask.hide();
					Ext.Msg.alert("ERROR", "Cannot connect to server");
				}
			});
		}
	},

	onResetPasswordClick : function(btn, opts) {
		this.getView().down('#oldPassword').reset();
		this.getView().down('#newPassword').reset();
		this.getView().down('#reTypeNewPassword').reset();
	}
});