Ext.define('Apptest.apptest.web.com.view.organizationboundedcontext.location.AddressTypeMain', {
     "extend": "Ext.tab.Panel",
     "customWidgetType": "vdTabLayout",
     "autoScroll": false,
     "controller": "AddressTypeMainController",
     "restURL": "/AddressType",
     "defaults": {
          "split": true
     },
     "requires": ["Apptest.apptest.shared.com.model.organizationboundedcontext.location.AddressTypeModel", "Apptest.apptest.web.com.controller.organizationboundedcontext.location.AddressTypeMainController", "Apptest.apptest.shared.com.viewmodel.organizationboundedcontext.location.AddressTypeViewModel"],
     "communicationLog": [],
     "tabPosition": "bottom",
     "items": [{
          "title": "Data Browser",
          "layout": "border",
          "defaults": {
               "split": true
          },
          "autoScroll": false,
          "customWidgetType": "vdBorderLayout",
          "items": [{
               "xtype": "tabpanel",
               "customWidgetType": "vdTabLayout",
               "margin": "5 0 5 5",
               "border": 1,
               "style": {
                    "borderColor": "#f6f6f6",
                    "borderStyle": "solid",
                    "borderWidth": "1px"
               },
               "displayName": "Address Type",
               "name": "AddressTypeTreeContainer",
               "itemId": "AddressTypeTreeContainer",
               "restURL": "/AddressType",
               "autoScroll": false,
               "collapsible": true,
               "titleCollapse": true,
               "collapseMode": "header",
               "collapsed": false,
               "items": [{
                    "xtype": "treepanel",
                    "customWidgetType": "vdTree",
                    "title": "Browse",
                    "name": "entityTreePanel",
                    "useArrows": true,
                    "rootVisible": false,
                    "itemId": "AddressTypeTree",
                    "listeners": {
                         "select": "treeClick"
                    },
                    "tbar": [{
                         "xtype": "triggerfield",
                         "customWidgetType": "vdTriggerField",
                         "emptyText": "Search",
                         "triggerCls": "",
                         "listeners": {
                              "change": "onTriggerfieldChange",
                              "buffer": 250
                         }
                    }, "->", {
                         "xtype": "tool",
                         "type": "refresh",
                         "tooltip": "Refresh Tree Data",
                         "handler": "onTreeRefreshClick"
                    }]
               }, {
                    "title": "Advance Search",
                    "xtype": "form",
                    "customWidgetType": "vdFormpanel",
                    "itemId": "queryPanel",
                    "dockedItems": [{
                         "xtype ": "toolbar",
                         "customWidgetType": "vdBBar",
                         "dock": "bottom",
                         "isDockedItem": true,
                         "items": [{
                              "xtype": "tbfill",
                              "customWidgetType": "vdTbFill"
                         }, {
                              "xtype": "button",
                              "customWidgetType": "vdButton",
                              "text": "Filter",
                              "name": "filterButton",
                              "handler": "onFilterClick"
                         }]
                    }],
                    "items": [{
                         "name": "addressType",
                         "itemId": "addressType",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "Address Type",
                         "margin": "5 5 5 5",
                         "fieldLabel": "Address Type",
                         "fieldId": "564DE85B-3E27-4110-8612-4ED0BB948181",
                         "minLength": "0",
                         "maxLength": "128",
                         "errorMessage": "Please enter address type",
                         "bindable": "addressType"
                    }]
               }],
               "region": "west",
               "width": "20%"
          }, {
               "region": "center",
               "layout": "border",
               "defaults": {
                    "split": true
               },
               "customWidgetType": "vdBorderLayout",
               "items": [{
                    "customWidgetType": "vdFormpanel",
                    "xtype": "form",
                    "displayName": "Address Type",
                    "title": "Address Type",
                    "name": "AddressType",
                    "itemId": "AddressTypeForm",
                    "bodyPadding": 10,
                    "items": [{
                         "name": "addressTypeId",
                         "itemId": "addressTypeId",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "Address Type Id",
                         "margin": "5 5 5 5",
                         "fieldLabel": "Address Type Id<font color='red'> *<\/font>",
                         "fieldId": "88672EB6-AE85-4221-88A7-6AC3021DD751",
                         "minLength": "0",
                         "maxLength": "64",
                         "hidden": true,
                         "value": "",
                         "bindable": "addressTypeId"
                    }, {
                         "name": "addressType",
                         "itemId": "addressType",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "Address Type",
                         "margin": "5 5 5 5",
                         "fieldLabel": "Address Type<font color='red'> *<\/font>",
                         "allowBlank": false,
                         "fieldId": "564DE85B-3E27-4110-8612-4ED0BB948181",
                         "minLength": "0",
                         "maxLength": "128",
                         "errorMessage": "Please enter address type",
                         "bindable": "addressType",
                         "columnWidth": 0.5
                    }, {
                         "name": "addressTypeDesc",
                         "itemId": "addressTypeDesc",
                         "xtype": "textareafield",
                         "customWidgetType": "vdTextareafield",
                         "displayName": "Address Type Desc",
                         "margin": "5 5 5 5",
                         "fieldLabel": "Address Type Desc",
                         "fieldId": "E3156D92-24A8-4713-AA14-7B415BEF5670",
                         "bindable": "addressTypeDesc",
                         "columnWidth": 0.5
                    }, {
                         "name": "addressTypeIcon",
                         "itemId": "addressTypeIcon",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "Address Type Icon",
                         "margin": "5 5 5 5",
                         "fieldLabel": "Address Type Icon",
                         "fieldId": "C7265E0F-779E-43E4-8E63-253E747A1D9B",
                         "minLength": "0",
                         "maxLength": "128",
                         "bindable": "addressTypeIcon",
                         "columnWidth": 0.5
                    }, {
                         "name": "versionId",
                         "itemId": "versionId",
                         "xtype": "numberfield",
                         "customWidgetType": "vdNumberfield",
                         "displayName": "versionId",
                         "margin": "5 5 5 5",
                         "value": "-1",
                         "fieldLabel": "versionId",
                         "fieldId": "AA6EE1D2-01A0-4BA8-86E6-1CC6F5B8C433",
                         "bindable": "versionId",
                         "hidden": true
                    }],
                    "layout": "column",
                    "defaults": {
                         "columnWidth": 0.5,
                         "labelAlign": "left",
                         "labelWidth": 200
                    },
                    "autoScroll": true,
                    "dockedItems": [{
                         "xtype ": "toolbar",
                         "customWidgetType": "vdBBar",
                         "dock": "bottom",
                         "ui": "footer",
                         "isDockedItem": true,
                         "parentId": 1,
                         "customId": 964,
                         "items": [{
                              "xtype": "tbfill",
                              "customWidgetType": "vdTbFill",
                              "parentId": 964,
                              "customId": 55
                         }, {
                              "customWidgetType": "vdButton",
                              "xtype": "button",
                              "name": "saveFormButton",
                              "margin": "0 5 0 5",
                              "text": "Save",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "saveFormButton",
                              "parentId": 964,
                              "customId": 56,
                              "listeners": {
                                   "click": "saveForm"
                              }
                         }, {
                              "customWidgetType": "vdButton",
                              "xtype": "button",
                              "name": "resetFormButton",
                              "margin": "0 5 0 5",
                              "text": "Reset",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "resetFormButton",
                              "parentId": 964,
                              "customId": 57,
                              "listeners": {
                                   "click": "resetForm"
                              }
                         }],
                         "defaults": {}
                    }],
                    "listeners": {
                         "scope": "controller"
                    },
                    "tools": [{
                         "type": "help",
                         "tooltip": "Console",
                         "handler": "onConsoleClick"
                    }, {
                         "type": "refresh",
                         "tooltip": "Refresh Tab",
                         "handler": "init"
                    }],
                    "extend": "Ext.form.Panel",
                    "region": "center"
               }, {
                    "xtype": "gridpanel",
                    "customWidgetType": "vdGrid",
                    "displayName": "Address Type",
                    "title": "Details Grid",
                    "name": "AddressTypeGrid",
                    "itemId": "AddressTypeGrid",
                    "restURL": "/AddressType",
                    "store": [],
                    "bodyPadding": 10,
                    "columns": [{
                         "header": "Address Type Id",
                         "dataIndex": "addressTypeId",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "primaryDisplay",
                         "dataIndex": "primaryDisplay",
                         "hidden": true
                    }, {
                         "header": "primaryKey",
                         "dataIndex": "primaryKey",
                         "hidden": true
                    }, {
                         "header": "Address Type",
                         "dataIndex": "addressType",
                         "flex": 1
                    }, {
                         "header": "Address Type Desc",
                         "dataIndex": "addressTypeDesc",
                         "flex": 1
                    }, {
                         "header": "Address Type Icon",
                         "dataIndex": "addressTypeIcon",
                         "flex": 1
                    }, {
                         "header": "createdBy",
                         "dataIndex": "createdBy",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "createdDate",
                         "dataIndex": "createdDate",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "updatedBy",
                         "dataIndex": "updatedBy",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "updatedDate",
                         "dataIndex": "updatedDate",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "versionId",
                         "dataIndex": "versionId",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "activeStatus",
                         "dataIndex": "activeStatus",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "txnAccessCode",
                         "dataIndex": "txnAccessCode",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "xtype": "actioncolumn",
                         "customWidgetType": "vdActionColumn",
                         "width": 30,
                         "sortable": false,
                         "menuDisable": true,
                         "items": [{
                              "icon": "images/delete.gif",
                              "tooltip": "Delete Record",
                              "handler": "onDeleteActionColumnClickMainGrid"
                         }]
                    }],
                    "listeners": {
                         "itemclick": "onGridItemClick"
                    },
                    "tools": [{
                         "type": "refresh",
                         "tooltip": "Refresh Grid Data",
                         "handler": "onGridRefreshClick"
                    }],
                    "collapsible": true,
                    "titleCollapse": true,
                    "collapseMode": "header",
                    "region": "south",
                    "height": "40%"
               }]
          }]
     }, {
          "title": "Add New",
          "itemId": "addNewForm",
          "layout": "border",
          "customWidgetType": "vdBorderLayout",
          "autoScroll": false,
          "items": [{
               "customWidgetType": "vdFormpanel",
               "xtype": "form",
               "displayName": "Address Type",
               "title": "Address Type",
               "name": "AddressType",
               "itemId": "AddressTypeForm",
               "bodyPadding": 10,
               "items": [{
                    "name": "addressTypeId",
                    "itemId": "addressTypeId",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "Address Type Id",
                    "margin": "5 5 5 5",
                    "fieldLabel": "Address Type Id<font color='red'> *<\/font>",
                    "fieldId": "88672EB6-AE85-4221-88A7-6AC3021DD751",
                    "minLength": "0",
                    "maxLength": "64",
                    "hidden": true,
                    "value": "",
                    "bindable": "addressTypeId"
               }, {
                    "name": "addressType",
                    "itemId": "addressType",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "Address Type",
                    "margin": "5 5 5 5",
                    "fieldLabel": "Address Type<font color='red'> *<\/font>",
                    "allowBlank": false,
                    "fieldId": "564DE85B-3E27-4110-8612-4ED0BB948181",
                    "minLength": "0",
                    "maxLength": "128",
                    "errorMessage": "Please enter address type",
                    "bindable": "addressType",
                    "columnWidth": 0.5
               }, {
                    "name": "addressTypeDesc",
                    "itemId": "addressTypeDesc",
                    "xtype": "textareafield",
                    "customWidgetType": "vdTextareafield",
                    "displayName": "Address Type Desc",
                    "margin": "5 5 5 5",
                    "fieldLabel": "Address Type Desc",
                    "fieldId": "E3156D92-24A8-4713-AA14-7B415BEF5670",
                    "bindable": "addressTypeDesc",
                    "columnWidth": 0.5
               }, {
                    "name": "addressTypeIcon",
                    "itemId": "addressTypeIcon",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "Address Type Icon",
                    "margin": "5 5 5 5",
                    "fieldLabel": "Address Type Icon",
                    "fieldId": "C7265E0F-779E-43E4-8E63-253E747A1D9B",
                    "minLength": "0",
                    "maxLength": "128",
                    "bindable": "addressTypeIcon",
                    "columnWidth": 0.5
               }, {
                    "name": "versionId",
                    "itemId": "versionId",
                    "xtype": "numberfield",
                    "customWidgetType": "vdNumberfield",
                    "displayName": "versionId",
                    "margin": "5 5 5 5",
                    "value": "-1",
                    "fieldLabel": "versionId",
                    "fieldId": "AA6EE1D2-01A0-4BA8-86E6-1CC6F5B8C433",
                    "bindable": "versionId",
                    "hidden": true
               }],
               "layout": "column",
               "defaults": {
                    "columnWidth": 0.5,
                    "labelAlign": "left",
                    "labelWidth": 200
               },
               "autoScroll": true,
               "dockedItems": [{
                    "xtype ": "toolbar",
                    "customWidgetType": "vdBBar",
                    "dock": "bottom",
                    "ui": "footer",
                    "isDockedItem": true,
                    "parentId": 1,
                    "customId": 964,
                    "items": [{
                         "xtype": "tbfill",
                         "customWidgetType": "vdTbFill",
                         "parentId": 964,
                         "customId": 55
                    }, {
                         "customWidgetType": "vdButton",
                         "xtype": "button",
                         "name": "saveFormButton",
                         "margin": "0 5 0 5",
                         "text": "Save",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "saveFormButton",
                         "parentId": 964,
                         "customId": 56,
                         "listeners": {
                              "click": "saveForm"
                         }
                    }, {
                         "customWidgetType": "vdButton",
                         "xtype": "button",
                         "name": "resetFormButton",
                         "margin": "0 5 0 5",
                         "text": "Reset",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "resetFormButton",
                         "parentId": 964,
                         "customId": 57,
                         "listeners": {
                              "click": "resetForm"
                         }
                    }],
                    "defaults": {}
               }],
               "listeners": {
                    "scope": "controller"
               },
               "tools": [{
                    "type": "help",
                    "tooltip": "Console",
                    "handler": "onConsoleClick"
               }, {
                    "type": "refresh",
                    "tooltip": "Refresh Tab",
                    "handler": "init"
               }],
               "extend": "Ext.form.Panel",
               "region": "center"
          }]
     }]
});