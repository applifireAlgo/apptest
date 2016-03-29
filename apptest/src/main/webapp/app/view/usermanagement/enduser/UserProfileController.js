Ext.define('Apptest.view.usermanagement.enduser.UserProfileController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.userProfileController',

	commTypeAllData : null,
	cityData : null,
	stateData : null,

	addrTypeComboStore : null,
	countryComboStore : null,
	commGroupComboStore : null,

	deleteCommunicationDataArray : [],
	deleteAddressDataArray : [],
	deletePassRecoveryDataArray : [],

	init : function() {

		this.deleteCommunicationDataArray = [];
		this.deleteAddressDataArray = [];
		this.deletePassRecoveryDataArray = [];
		this.afterUserDetailPanelRender();

		// this.loadUserProfile();
	},

	onDeleteCommDataRowClick : function(grid, rowIndex) {

		var commDataId = grid.getStore().getAt(rowIndex).getData().commDataId;
		if (commDataId != undefined) {
			this.deleteCommunicationDataArray.push(grid.getStore().getAt(rowIndex).getData());
		}
		grid.getStore().removeAt(rowIndex);
	},

	onDeleteAddressDataRowClick : function(grid, rowIndex) {

		var addressId = grid.getStore().getAt(rowIndex).getData().addressId;
		if (addressId != undefined) {
			this.deleteAddressDataArray.push(grid.getStore().getAt(rowIndex).getData());
		}
		grid.getStore().removeAt(rowIndex);
	},

	onDeletePassRecoveryRowClick : function(grid, rowIndex) {

		var passRecoveryId = grid.getStore().getAt(rowIndex).getData().passRecoveryId;
		if (passRecoveryId != undefined) {
			this.deletePassRecoveryDataArray.push(grid.getStore().getAt(rowIndex).getData());
		}
		grid.getStore().removeAt(rowIndex);
	},

	makeoverDeleteJSON : function(record) {
		delete record.id;
		record.systemInfo = {
			activeStatus : -1
		};
	},

	prepareCommDataJSON : function(store) {

		storeItems = store.data.items;
		obj = {};
		arr = [];
		for (counter in storeItems) {
			var data = storeItems[counter].data;
			delete data.id;
			if (data.commDataId != undefined) {
				data.systemInfo = {
					activeStatus : 1
				};
			}
			arr.push(data);
		}
		// FOR DELETE CASE
		for (var i = 0; i < this.deleteCommunicationDataArray.length; i++) {
			var record = this.deleteCommunicationDataArray[i];
			this.makeoverDeleteJSON(record);
			arr.push(record);
		}
		return obj['objArr'] = arr;
	},

	prepareAddressJSON : function(store) {

		storeItems = store.data.items;
		obj = {};
		arr = [];
		for (counter in storeItems) {
			var data = storeItems[counter].data;
			delete data.id;
			if (data.addressId != undefined) {
				data.systemInfo = {
					activeStatus : 1
				};
			}
			arr.push(data);
		}
		// FOR DELETE CASE
		for (var i = 0; i < this.deleteAddressDataArray.length; i++) {
			var record = this.deleteAddressDataArray[i];
			this.makeoverDeleteJSON(record);
			arr.push(record);
		}
		return obj['objArr'] = arr;
	},

	preparePassRecoveryDataJSON : function(store) {

		storeItems = store.data.items;
		obj = {};
		arr = [];
		for (counter in storeItems) {
			var data = storeItems[counter].data;
			delete data.id;
			if (data.passRecoveryId != undefined) {
				data.systemInfo = {
					activeStatus : 1
				};
			}
			arr.push(data);
		}
		// FOR DELETE CASE
		for (var i = 0; i < this.deletePassRecoveryDataArray.length; i++) {
			var record = this.deletePassRecoveryDataArray[i];
			this.makeoverDeleteJSON(record);
			arr.push(record);
		}
		return obj['objArr'] = arr;
	},

	prepareUpdateJSON : function(jsonData) {

		// PREPARING BASE LOGIN OBJECT
		delete jsonData.userId;
		delete jsonData.qkeHash;
		delete jsonData.checkCookie;
		delete jsonData.sessionTimeout;
		delete jsonData.disabled;
		delete jsonData.userAccessCode;
		delete jsonData.passwordExpired;
		jsonData.systemInfo = {
			activeStatus : 1
		};
		// PREPARING INNER OBJECT USER OBJECT
		jsonData.loginId = jsonData.coreContacts.emailId;
		delete jsonData.user.userData;
		var quesAnsGrid = this.getView().down('#quesAnsGrid');
		jsonData.user.passRecovery = this.preparePassRecoveryDataJSON(quesAnsGrid.getStore());
		jsonData.user.systemInfo = {
			activeStatus : 1
		};
		// PREPARING CORE CONTACTS
		var commGrid = this.getView().down('#commGrid');
		var commData = this.prepareCommDataJSON(commGrid.getStore());
		jsonData.coreContacts.communicationData = commData;

		var addrGrid = this.getView().down('#addrGrid');
		var addrJSON = this.prepareAddressJSON(addrGrid.getStore());
		jsonData.coreContacts.address = addrJSON;
		return jsonData;
	},

	onUpdateFormClick : function(btn, opts) {
		var form = btn.up().up();
		if (form.isValid()) {
			var formData = form.getViewModel().getData();
			if (formData.loginId != formData.coreContacts.emailId) {
				Ext.Msg.confirm("Confirm", "Changing email address will also change your login-id automatically, <br>Are you sure you want to proceed?", function(id, value) {
					if (id == "yes") {
						this.controller.updateMyProfileWithChangedEmailId(this.form, this.formData);
					}
				}, {
					"controller" : this,
					"form" : form,
					"formData" : formData
				});
			} else {
				this.updateMyProfile(form, formData);
			}
		}
	},

	updateMyProfileWithChangedEmailId : function(form, formData) {
		var emailId = formData.coreContacts.emailId;
		Ext.Ajax.request({
			url : 'secure/PasswordGenerator/checkValidityOfLoginId',
			method : 'POST',
			jsonData : {
				findKey : emailId
			},
			controller : this,
			success : function(response, currentObject) {
				var jsonResponse = Ext.JSON.decode(response.responseText);
				if (jsonResponse.response.success == true) {
					currentObject.controller.updateMyProfile(form, formData);
				} else {
					Ext.Msg.alert({
						title : 'ERROR',
						msg : jsonResponse.response.message,
						icon : Ext.MessageBox.ERROR
					});
				}
			},
			failure : function() {
				Ext.Msg.alert({
					title : 'Error',
					msg : 'Cannot connect to server',
					icon : Ext.MessageBox.ERROR
				});
			}
		});
	},

	updateMyProfile : function(form, formData) {
		var me = this;
		formData.coreContacts.genderId = form.down('#genderId').getValue().genderId;
		formData.user.allowMultipleLogin = formData.user.allowMultipleLogin == true ? 1 : 0;

		var dateofbirth = form.down('#dateofbirth').getValue();
		dateofbirth = Ext.Date.format(dateofbirth, 'd-m-Y');
		if (dateofbirth != "" && dateofbirth != null) {
			var val = dateofbirth + " 0:0:0";
			var timezone = Ext.util.Cookies.get('XA_TID');
			formData.coreContacts.dateofbirth = val + " " + timezone;
		}

		var jsonData = this.prepareUpdateJSON(formData);

		if (jsonData.user.passRecovery.length < 3) {
			Ext.Msg.alert({
				title : 'Error',
				msg : 'Please Select atleast 3 security questions',
				icon : Ext.MessageBox.ERROR
			});
		} else {
			Ext.MessageBox.show({
				msg : 'Updating Details',
				progressText : 'Updating Details',
				width : 300,
				wait : true,
				waitConfig : {
					interval : 200
				},
				animateTarget : 'waitButton'
			});

			Ext.Ajax.request({
				url : 'secure/Login',
				method : 'PUT',
				jsonData : jsonData,
				timeout : 180000,
				success : function(response, eOpts) {
					var jsonResponse = Ext.JSON.decode(response.responseText);
					if (jsonResponse.response.success == true) {
						var data = jsonResponse.response.data;
						Ext.Msg.alert('Success', "Profile Updated Successfully");
						me.deleteCommunicationDataArray = [];
						me.deleteAddressDataArray = [];
						me.deletePassRecoveryDataArray = [];
						me.loadUserProfile();
					} else {
						Ext.Msg.alert({
							title : 'Error',
							msg : jsonResponse.response.message,
							icon : Ext.MessageBox.ERROR
						});
					}
				},
				failure : function() {
					Ext.Msg.alert({
						title : 'Error',
						msg : 'Cannot connect to server',
						icon : Ext.MessageBox.ERROR
					});
				}
			});
		}
	},

	loadUserProfile : function() {
		var me = this;
		var currentView = this.getView();

		Ext.Ajax.request({
			url : "secure/PasswordGenerator/findLoggedInUser",
			method : 'GET',
			me : me,
			currentView : currentView,
			success : function(response, currentObject, options) {
				var jsonResponse = Ext.JSON.decode(response.responseText);
				if (jsonResponse.response.success == true) {
					var data = jsonResponse.response.data;
					var form = currentObject.me.getView();
					if (data.coreContacts.age < 18) {
						data.coreContacts.age = "";
					}
					form.getViewModel().setData(data);

					if (data.coreContacts.genderId != null) {
						form.down('#genderId').query("[inputValue=" + data.coreContacts.genderId + "]")[0].setValue(true);
					}

					if (data.coreContacts.dateofbirth != null && data.coreContacts.dateofbirth != "") {
						var dateWithoutTimezone = data.coreContacts.dateofbirth.replace(' ' + Ext.util.Cookies.get('XA_TID'), '');
						var dt = Ext.Date.parse(dateWithoutTimezone, 'd-n-Y H:i:s');
						form.down('#dateofbirth').setValue(dt);
					}

					currentObject.currentView.down('#commGrid').getStore().loadData(data.coreContacts.communicationData);
					currentObject.currentView.down('#addrGrid').getStore().loadData(data.coreContacts.address);
					currentObject.currentView.down('#quesAnsGrid').getStore().loadData(data.user.passRecovery);

					if (data.loginId == "admin") {
						currentObject.currentView.down('#saveProfileBtn').disable();
					}
				} else {
					Ext.Msg.alert('Error', 'Data Loading failed!');
				}
			},
			failure : function() {
				Ext.Msg.alert('Error', 'Cannot connect to server!');
			}
		});
	},

	afterTitleComboRender : function(combo) {
		Ext.Ajax.request({
			url : "secure/Title/findAll",
			method : 'GET',
			combo : combo,
			jsonData : {},
			success : function(response, currentObject, options) {
				var jsonResponse = Ext.JSON.decode(response.responseText);
				var data = jsonResponse.response.data;
				currentObject.combo.getStore().loadData(data);
			},
			failure : function() {
				Ext.Msg.alert('Error', 'Cannot connect to server');
			}
		});
	},
	aftrAddrTypeComboRender : function(combo) {
		var me = this;
		Ext.Ajax.request({
			url : "secure/AddressType/findAll",
			method : 'GET',
			combo : combo,
			me : me,
			jsonData : {},
			success : function(response, currentObject, options) {
				var jsonResponse = Ext.JSON.decode(response.responseText);
				var data = jsonResponse.response.data;
				currentObject.combo.getStore().loadData(data);
				currentObject.me.addrTypeComboStore = currentObject.combo.getStore();
			},
			failure : function() {
				Ext.Msg.alert('Error', 'Cannot connect to server');
			}
		});
	},
	afterLanguageComboRender : function(combo) {
		Ext.Ajax.request({
			url : "secure/Language/findAll",
			method : 'GET',
			combo : combo,
			jsonData : {},
			success : function(response, currentObject, options) {
				var jsonResponse = Ext.JSON.decode(response.responseText);
				var data = jsonResponse.response.data;
				currentObject.combo.getStore().loadData(data);
			},
			failure : function() {
				Ext.Msg.alert('Error', 'Cannot connect to server');
			}
		});
	},
	aftrCommGroupComboRender : function(combo) {
		var me = this;
		Ext.Ajax.request({
			url : "secure/CommunicationGroup/findAll",
			method : 'GET',
			combo : combo,
			me : me,
			jsonData : {},
			success : function(response, currentObject, options) {
				var jsonResponse = Ext.JSON.decode(response.responseText);
				var data = jsonResponse.response.data;
				currentObject.combo.getStore().loadData(data);
				currentObject.me.commGroupComboStore = currentObject.combo.getStore();
			},
			failure : function() {
				Ext.Msg.alert('Error', 'Cannot connect to server');
			}
		});
	},
	onCommGroupChange : function(commGroupId) {
		var tempView = this.getView();
		Ext.Ajax.request({
			url : "secure/CommunicationType/findByCommGroupId",
			method : 'POST',
			currentView : tempView,
			jsonData : {
				findKey : commGroupId.value
			},
			success : function(response, currentObject, options) {
				var jsonResponse = Ext.JSON.decode(response.responseText);
				var data = jsonResponse.response.data;
				var commType = currentObject.currentView.down('#commType');
				commType.getStore().loadData(data);
			},
			failure : function() {
				Ext.Msg.alert('Error', 'Cannot connect to server');
			}
		});
	},
	aftrCountryComboRender : function(combo) {
		var me = this;
		Ext.Ajax.request({
			url : "secure/Country/findAll",
			method : 'GET',
			combo : combo,
			me : me,
			jsonData : {},
			success : function(response, currentObject, options) {
				var jsonResponse = Ext.JSON.decode(response.responseText);
				var data = jsonResponse.response.data;
				currentObject.combo.getStore().loadData(data);
				currentObject.me.countryComboStore = currentObject.combo.getStore();
			},
			failure : function() {
				Ext.Msg.alert('Error', 'Cannot connect to server');
			}
		});
	},
	onCountryChange : function(countryId) {
		var tempView = this.getView();
		Ext.Ajax.request({
			url : "secure/State/findByCountryId",
			method : 'POST',
			currentView : tempView,
			jsonData : {
				findKey : countryId.value
			},
			success : function(response, currentObject, options) {
				var jsonResponse = Ext.JSON.decode(response.responseText);
				var data = jsonResponse.response.data;
				var stateId = currentObject.currentView.down('#stateId');
				stateId.setValue("");
				stateId.getStore().loadData(data);
			},
			failure : function() {
				Ext.Msg.alert('Error', 'Cannot connect to server');
			}
		});
	},
	onStateChange : function(stateId) {
		var tempView = this.getView();
		Ext.Ajax.request({
			url : "secure/City/findByStateId",
			method : 'POST',
			currentView : tempView,
			jsonData : {
				findKey : stateId.value
			},
			success : function(response, currentObject, options) {
				var jsonResponse = Ext.JSON.decode(response.responseText);
				var data = jsonResponse.response.data;
				var cityId = currentObject.currentView.down('#cityId');
				cityId.setValue("");
				cityId.getStore().loadData(data);
			},
			failure : function() {
				Ext.Msg.alert('Error', 'Cannot connect to server');
			}
		});
	},
	addCommData : function(btn) {
		var commForm = btn.up().up().up();
		var grid = commForm.down('#commGrid');
		commForm.down('#commGroupId').allowBlank = false;
		commForm.down('#commType').allowBlank = false;
		commForm.down('#commData').allowBlank = false;
		if (commForm.isValid()) {
			var values = commForm.getValues();
			grid.getStore().add(values);
			commForm.reset();
		}
		commForm.down('#commGroupId').allowBlank = true;
		commForm.down('#commType').allowBlank = true;
		commForm.down('#commData').allowBlank = true;
	},

	addAddressData : function(btn) {
		var addrForm = btn.up().up().up();
		var grid = addrForm.down('#addrGrid');
		addrForm.down('#addressTypeId').allowBlank = false;
		addrForm.down('#address1').allowBlank = false;
		addrForm.down('#zipcode').allowBlank = false;
		addrForm.down('#cityId').allowBlank = false;
		addrForm.down('#stateId').allowBlank = false;
		addrForm.down('#countryId').allowBlank = false;
		if (addrForm.isValid()) {
			var values = addrForm.getValues();
			grid.getStore().add(values);
			addrForm.reset();
		}
		addrForm.down('#addressTypeId').allowBlank = true;
		addrForm.down('#address1').allowBlank = true;
		addrForm.down('#zipcode').allowBlank = true;
		addrForm.down('#cityId').allowBlank = true;
		addrForm.down('#stateId').allowBlank = true;
		addrForm.down('#countryId').allowBlank = true;
	},

	onResetUserForm : function() {
		// changes done by rushikesh
		// on reset calling reload method.
		this.loadUserProfile();
		/*
		 * this.getView().getForm().reset(); this.getView().down('#addrGrid').store.removeAll(); this.getView().down('#commGrid').store.removeAll();
		 */
	},

	removeId : function(data) {
		for (var int = 0; int < data.length; int++) {
			delete data[int]['id'];
		}
	},
	createObject : function(data, dataLevel, assignValue) {
		this.removeId(assignValue);
		var dataLevels = dataLevel.split('.');
		var currentObject = data;
		for ( var iterable_element in dataLevels) {
			var element = dataLevels[iterable_element];
			var elementValue = currentObject[element];
			if (currentObject[element]) {
				currentObject = elementValue;
			} else {
				currentObject[element] = assignValue;
				return currentObject;
			}
		}
		return data;
	},
	fetchDataFromStore : function(store) {
		storeItems = store.data.items;
		obj = {};
		arr = [];
		for (counter in storeItems) {
			arr.push(storeItems[counter].data);
		}
		return obj['objArr'] = arr;
	},
	commGroupColRender : function(value, metaData, record, rowIndex, colIndex, store, view) {
		var combo = this.getView().down('#commGroupId');
		var matchRec = combo.store.findRecord('commGroupId', value);
		return matchRec.data.commGroupName;

	},
	commTypeColRenderer : function(value, metaData, record, rowIndex, colIndex, store, view) {
		for (var i = 0; i < this.commTypeAllData.length; i++) {
			if (this.commTypeAllData[i].commType == value) {
				return this.commTypeAllData[i].commTypeName;
			}
		}
	},
	commTypeColRender : function(value, metaData, record, rowIndex, colIndex, store, view) {
		var combo = this.getView().down('#commType');
		var matchRec = combo.store.findRecord('commType', value);
		return matchRec.data.commTypeName;
	},
	addrTypeColRender : function(value, metaData, record, rowIndex, colIndex, store, view) {
		var combo = this.getView().down('#addressTypeId');
		var matchRec = combo.store.findRecord('addressTypeId', value);
		return matchRec.data.addressType;
	},
	cityColRender : function(value, metaData, record, rowIndex, colIndex, store, view) {
		var combo = this.getView().down('#cityId');
		var matchRec = combo.store.findRecord('cityId', value);
		return matchRec.data.cityName;
	},
	stateColRender : function(value, metaData, record, rowIndex, colIndex, store, view) {
		var combo = this.getView().down('#stateId');
		var matchRec = combo.store.findRecord('stateId', value);
		return matchRec.data.stateName;
	},
	countryColRender : function(value, metaData, record, rowIndex, colIndex, store, view) {
		var combo = this.getView().down('#countryId');
		var matchRec = combo.store.findRecord('countryId', value);
		return matchRec.data.countryName;
	},

	// code for questions part
	aftrQuestionComboRender : function(combo) {
		Ext.Ajax.request({
			url : "secure/Question/findAll",
			method : 'GET',
			combo : combo,
			jsonData : {},
			success : function(response, currentObject, options) {
				var jsonResponse = Ext.JSON.decode(response.responseText);
				var data = jsonResponse.response.data;
				currentObject.combo.getStore().loadData(data);
			},
			failure : function() {
				Ext.Msg.alert('Error', 'Cannot connect to server');
			}
		});
	},

	addQA : function(btn) {
		var questionId = this.getView().down('#secQuestion');
		var answer = this.getView().down('#secAnswer');
		var questionValue = questionId.getValue();
		var answerValue = answer.getValue();

		if (questionValue == "" || answerValue == "") {
			Ext.Msg.alert({
				title : 'Invalid Question Answer',
				msg : 'Question or answer cannot be empty',
				icon : Ext.MessageBox.ERROR
			});
		} else {
			var obj = {
					questionId : questionValue,
					answer : answerValue
				};
				var grid = this.getView().down('#quesAnsGrid');
				var matchRec = grid.store.findRecord('questionId', obj.questionId);
				if (matchRec == null && grid.getStore().getData().length < 5) {
					grid.getStore().add(obj);
				} else if (grid.getStore().getData().length >= 5) {
					Ext.Msg.alert({
						title : 'Info',
						msg : 'Max limit reached, You can add maximum of 5 questions',
						icon : Ext.MessageBox.INFO
					});
				} else if(matchRec != null){
					Ext.Msg.alert({
						title : 'Duplication error',
						msg : 'Cannot add duplicate question',
						icon : Ext.MessageBox.ERROR
					});
				}
				questionId.reset();
				answer.reset();
			}
	},

	onQuesColRender : function(value, metaData, record, rowIndex, colIndex, store, view) {
		var combo = this.getView().down('#secQuestion');
		var matchRec = combo.store.findRecord('questionId', value);
		return matchRec.data.question;
	},

	cityColRenderer : function(value, metaData, record, rowIndex, colIndex, store, view) {
		for (var i = 0; i < this.cityData.length; i++) {
			if (this.cityData[i].cityId == value) {
				return this.cityData[i].cityName;
			}
		}
	},
	stateColRenderer : function(value, metaData, record, rowIndex, colIndex, store, view) {
		for (var i = 0; i < this.stateData.length; i++) {
			if (this.stateData[i].stateId == value) {
				return this.stateData[i].stateName;
			}
		}
	},
	countryColRenderer : function(value, metaData, record, rowIndex, colIndex, store, view) {

		var matchRec = this.countryComboStore.findRecord('countryId', value);
		return matchRec.data.countryName;
	},

	afterUserDetailPanelRender : function() {

		var me = this;
		Ext.Ajax.request({
			url : "secure/CommunicationType/findAll",
			method : 'GET',
			me : me,
			jsonData : {},
			success : function(response, currentObject, options) {
				var jsonResponse = Ext.JSON.decode(response.responseText);
				var data = jsonResponse.response.data;
				currentObject.me.commTypeAllData = data;
			},
			failure : function() {
				Ext.Msg.alert('Error', 'Cannot connect to server');
			}
		});
		Ext.Ajax.request({
			url : "secure/State/findAll",
			method : 'GET',
			me : me,
			jsonData : {},
			success : function(response, currentObject, options) {
				var jsonResponse = Ext.JSON.decode(response.responseText);
				var data = jsonResponse.response.data;
				currentObject.me.stateData = data;
			},
			failure : function() {
				Ext.Msg.alert('Error', 'Cannot connect to server');
			}
		});
		Ext.Ajax.request({
			url : "secure/City/findAll",
			method : 'GET',
			me : me,
			jsonData : {},
			success : function(response, currentObject, options) {
				var jsonResponse = Ext.JSON.decode(response.responseText);
				var data = jsonResponse.response.data;
				currentObject.me.cityData = data;
			},
			failure : function() {
				Ext.Msg.alert('Error', 'Cannot connect to server');
			}
		});
	},

	calculateAge : function(field, value, eOpts) {
		var today = new Date();
		if (today.getFullYear() - value.getFullYear() >= 0) {
			var timeDiff = Math.abs(today.getTime() - value.getTime());
			var age = Math.floor(timeDiff / (1000 * 3600 * 24 * 365));
			if ((age > 18) || (age == 18 && value.getMonth() - today.getMonth() >= 0 && today.getDate() - value.getDate() >= 0)) {
				var form = field.up().up().up();
				form.down('#age').setValue(age);
			} else {
				var form = field.up().up().up();
				form.down('#dateofbirth').reset();
				form.down('#age').setValue("");
				Ext.Msg.alert({
					title : 'Error',
					msg : 'Age should be greater than 18. Please verify your date of birth.',
					icon : Ext.MessageBox.ERROR
				});
			}
		} else {
			Ext.Msg.alert({
				title : 'Error',
				msg : 'Date of birth cannot be future date.',
				icon : Ext.MessageBox.ERROR
			});
		}
	},

	afterrenderTimezone : function(combo) {
		Ext.Ajax.request({
			url : "secure/Timezone/findAll",
			method : 'GET',
			combo : combo,
			jsonData : {},
			success : function(response, currentObject, options) {
				var jsonResponse = Ext.JSON.decode(response.responseText);
				var data = jsonResponse.response.data;
				currentObject.combo.getStore().loadData(data);
			},
			failure : function() {
				Ext.Msg.alert('Error', 'Cannot connect to server');
			}
		});
	},
});