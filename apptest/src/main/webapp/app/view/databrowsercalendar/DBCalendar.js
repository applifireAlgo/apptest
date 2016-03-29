Ext.define('Apptest.view.databrowsercalendar.DBCalendar', {
	extend : 'Apptest.view.databrowsercalendar.DBCalendarView',
	requires : [ 'Apptest.view.databrowsercalendar.DBCalendarController',
	             'Apptest.view.databrowsercalendar.DBCalendarView','Ext.layout.container.Card',
	             'Ext.calendar.view.Day', 'Ext.calendar.view.Week',
	             'Ext.calendar.view.Month',
	             'Ext.calendar.form.EventDetails',
	             'Ext.calendar.data.EventMappings'],
	controller : 'databrowsercalendar',
	items : [],
	listeners : {
		afterrender : 'loadData',
		scope : "controller"
	}
});
