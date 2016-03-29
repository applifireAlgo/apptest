Ext.define('Apptest.view.googlemaps.map.MapPanelController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.MapPanelController',
	// configData:null,
	init : function() {
		this.mapPanel = this.getView();
	},
	
	loadGoogleMap : function() {
		try {
			var mapOptions = {
				center : {
					lat : 28.613939,
					lng : 77.209021
				},
				zoom : 5
			};
			
			if(this.mapPanel.body.dom){
			var map = new google.maps.Map(this.mapPanel.body.dom, mapOptions);
			this.googleMap = map;
			this.resizeMap();
			google.maps.event.addListenerOnce(map, 'idle', function() {
				google.maps.event.trigger(map, 'resize');
				// map.setCenter(new google.maps.LatLng(28.613939, 77.209021));
			});
			
			if(this.getView().hasOwnProperty("configData")){
				this.configData=this.getView().configData;
				this.addMarkers(this.getView().mapData);
			}
			}
		} catch (e) {}
	},
	showMarker : function() {
		this.addMarkers(this.mapPanel.mapData1);
	},
	addMarkers : function(data) {
		
		this.getView().setTitle(this.configData["mapSName"]);
		if (data == undefined)
			return;
		for (var i = 0; i < data.length; i++) {
			var currentJson = data[i];
			var marker = this.createMarker(currentJson);
			this.createInfoWindow(marker, currentJson);
		}
	},
	createMarker : function(currentJson) {
		var marker = new google.maps.Marker({
			position : new google.maps.LatLng(
					parseFloat(currentJson[this.configData["mapLatColumn"]]),
					parseFloat(currentJson[this.configData["mapLongColumn"]])),
			map : this.googleMap
		});
		return marker;
	},
	createInfoWindow : function(marker, currentJson) {
		var key = "";
		if (this.configData.hasOwnProperty("aggregate")) {
			var aggregateConfig = this.configData.aggregate;
			if (aggregateConfig.length > 0) {
				key = aggregateConfig[0].dfieldList;
			}
		} else {
			key = this.configData.displayName;
		}

		googleMap = this.googleMap;
		var infowindow = new google.maps.InfoWindow({
			content : currentJson[key]
		});
		var mainInfoWindow;
		configValue = this.configData;
		google.maps.event.addListener(marker, 'click', function() {
			infowindow.open(googleMap, marker);
		});

		google.maps.event.addListener(googleMap, 'click', function() {
			infowindow.close();
		});
	},
	
	resizeMap : function() {
		google.maps.event.trigger(this.googleMap, 'resize');
	}

});