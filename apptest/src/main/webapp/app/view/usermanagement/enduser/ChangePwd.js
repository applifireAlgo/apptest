Ext.define('Apptest.view.usermanagement.enduser.ChangePwd', {
	extend : 'Ext.form.Panel',
	xtype : 'changePwdView',
	requires : [ 'Apptest.view.usermanagement.enduser.ChangePwdController' ],
	controller : 'changePwdController',
	margin : '3 0 0 0',
	layout : 'column',
	bodyPadding : '10',
	items : [ {
		columnWidth : .80,
		defaults : {
			width : '95%',
			allowBlank : false,
			padding:'10'
		},
		items : [ {
			xtype : 'textfield',
			name : 'oldPassword',
			itemId : 'oldPassword',
			fieldLabel : 'Old Password<font color="red">*</font>',
			inputType : 'password',
		}, {
			xtype : 'textfield',
			name : 'newPassword',
			itemId : 'newPassword',
			fieldLabel : 'New Password<font color="red">*</font>',
			inputType : 'password',
		}, {
			xtype : 'textfield',
			name : 'reTypeNewPassword',
			itemId : 'reTypeNewPassword',
			fieldLabel : 'Re-Type New Password<font color="red">*</font>',
			inputType : 'password',
			validator : function(value) {
				if (value == this.up().up().down('#newPassword').getValue()) {
					return true;
				} else {
					return 'New password is not matched'
				}
			}
		},{
			xtype : 'hidden',
			name : 'changePasswordInNextLogin',
			value : true
		}]
	} ],
	buttons : [ {
		text : 'Change Password',
		icon : 'images/user/pwdReset.png',
		handler : 'onChangePasswordClick'
	}, {
		text : 'Reset',
		icon : 'images/reset1.png',
		handler : 'onResetPasswordClick'
	} ]
});