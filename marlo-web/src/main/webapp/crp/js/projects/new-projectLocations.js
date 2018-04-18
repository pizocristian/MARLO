$(document).ready(init);
var map;
var style;
var infoWindow = null;
var markers = [];
var countID;
var countries = [];
var layer;
var arSelectedLocations = [];
var arInfoWindows = [];

function init() {
// Init select2
  $('form select').select2({
    width: "100%"
  });

  //Google Maps
  loadScript();

  /* Declaring Events */
  attachEvents();

}

function attachEvents() {

  /** Array countries * */
  // From other countries
  $("input[value='Country']").parents(".locationLevel").find(".locElement").each(function(i,e) {
    countries.push($(e).find(".locElementCountry").val());
  });

  if( $('.locationLevel[data-name="Country"] .suggestedCountriesList').find('.locations').exists() ){
    $('.locationLevel[data-name="Country"] .suggestedCountriesList').show();
  }

  // Add suggested countries list into the blue mapped countries in map
  $('.suggestedCountriesList').find('.locations').each(function(i,e){
    countries.push($(e).find('.locationName').attr('id'));
  });

  // REMOVE REGION
  $(".removeRegion").on("click", removeRegion);

  //Remove a location element-Event
  $(".removeLocation").on("click", removeLocationItem);

  // REGIONAL QUESTION
  $(".button-label").on("click", function() {
    var valueSelected = $(this).hasClass('yes-button-label');
    var $input = $(this).parent().find('input');
    $input.val(valueSelected);
    $(this).parent().find("label").removeClass("radio-checked");
    $(this).addClass("radio-checked");
    $(document).trigger('updateComponent');
  });

  $(".isRegional .button-label").on("click", function() {
    var valueSelected = $(this).hasClass('yes-button-label');
    if(!valueSelected) {
      $(".regionsBox").hide("slow");
      $(".regionsContent").hide("slow");
    } else {
      $(".regionsBox").show("slow");
      $(".regionsContent").show("slow");
    }
  });


  /** Remove region option from region select */
  var regionSelect = $("#regionSelect");
  $(".regionsContent").find(".recommended.locElement").each(function(i,e) {
    if($(e).find(".recommendedSelected").val() == "true") {
      var id = $(e).find(".elementID");
      var scope = $(e).find(".locScope");
      var option = regionSelect.find("option[value='" + id.val() + "-" + scope.val() + "']");
      option.prop('disabled', true);
    }
  });

  $("#regionList").find(".region").each(function(i,e) {
    var id = $(e).find("input.rId");
    var scope = $(e).find("input.regionScope");
    var option = regionSelect.find("option[value='" + id.val() + "-" + scope.val() + "']");
    option.prop('disabled', true);
  });

  // Region Select
  $("#regionSelect").on("change", function() {
    var option = $(this).find("option:selected");
    if(option.val() != "-1") {
      addRegion(option);
      // Remove option from select
      // option.remove();
      option.prop('disabled', true);
      $('#regionSelect').select2();
      // $(this).trigger("select2:change");
    }
  });

  //Events
  $("#locLevelSelect").on("change",function() {
    var option = $(this).find("option:selected");
    if(option.val() == "-1" || option.val().split("-")[0] == "10"){
      //If the selected option is "Climate Smart Village" hide the center indicator in the map
      $('#map .centerMarker').hide();
    }else{
      $('#map .centerMarker').show();
    }
    if(option.val() == "-1") {
      $("#addLocationButton").hide("slow");
      $(".locationForm-container .selectLocations").hide("slow");
      $(".locationForm-container .inputFormCoordinates-container").hide("slow");
    } else {
      $("#addLocationButton").show("slow");
      if(option.val().split("-")[1] == "true") {
        // LocElements options using ajax
        var select = $("#countriesCmvs");
        var url = baseURL + "/searchCountryListPL.do";
        var data = {
            parentId: option.val().split("-")[0],
            phaseID: phaseID
        };
        $.ajax({
            url: url,
            type: 'GET',
            dataType: "json",
            data: data
        }).done(
            function(m) {
              select.empty();
              for(var i = 0; i < m.locElements.length; i++) {
                select.append("<option class='" + m.locElements[i].isoAlpha2 + "' value='" + m.locElements[i].id
                    + "-" + m.locElements[i].isoAlpha2 + "-" + m.locElements[i].name + "' >"
                    + m.locElements[i].name + "</option>");
              }
            });
        $("#inputFormWrapper").slideUp();
        $(".selectLocations").slideDown();

      } else {
        $(".selectLocations").slideUp();
        $("#inputFormWrapper").slideDown();
      }
    }
    modalButtonsListeners();
  });

  // Clicking recommended location
  $('.recommendedLocName, .iconSelected').on('click',function() {
    var parent = $(this).parent();
    var selectedInput = parent.find("input.recommendedSelected");
    var option =
        $("#regionSelect").find(
            "option[value='" + parent.find("input.elementID").val() + "-" + parent.find("input.locScope").val()
                + "']");
    if(parent.find(".acceptLocation").exists()) {
      parent.find(".iconSelected").removeClass("acceptLocation");
      parent.find(".iconSelected").addClass("notAcceptLocation");
      selectedInput.val("false");
      option.prop('disabled', false);
      $('#regionSelect').select2();
    } else {
      checkRecommendedLocation(parent);
      parent.find(".iconSelected").removeClass("notAcceptLocation");
      parent.find(".iconSelected").addClass("acceptLocation");
      selectedInput.val("true");
      option.prop('disabled', true);
      $('#regionSelect').select2();

    }
    $(document).trigger('updateComponent');
  });

  //When suggested locations checkbox change do
  $('input.recommendedSelected').on('change', function() {
    // On checkbox change, put value (true or false) in next input
    $(this).next().val($(this).is(":checked"));

    var locIso = $(this).next().next().text();
    var locName = $(this).parent().find(".lName").children().text();
    var locId = $(this).parent().find(".elementID").val();

    var countryRow = $(".locationsDataTable").find("input.locationLevelId[value='2']");
    var countryList = $(".list-container").find(".Country");

    // Add the selected country in map, table and list
    if($(this).is(":checked")){

      //Add suggested location in table
      if(countryRow.exists()){
        addSuggestedCountry(locIso,locName,locId);
      }else{
        // If Country level location doesn't exists, so create level location in table
        var $locationItem = $("#locationLevel-template").clone(true).removeAttr("id");
        $locationItem.attr("data-name","Country");
        $locationItem.find(".locLevelName").html('Country');
        $locationItem.find("input.locationLevelId").val('2');
        $locationItem.find("input.locationLevelName").val('Country');
        $locationItem.find("input.isList").val(true);
        $(".locationsDataTable > tbody:last-child").append($locationItem);


        $locationItem.show("slow");
        updateIndex();

        //when country level is created, then add the checked suggested location
        addSuggestedCountry(locIso,locName,locId);
      }

      // When a suggested location is added, show the suggested row into the country level
      $('.locationLevel[data-name="Country"] .suggestedCountriesList').show();

      countries.push(locIso);
      layer.setMap(null);
      mappingCountries();
    }else{
      removeSuggestedCountry(locIso,locId,countryRow,countryList);

      // If there is not suggested countries in list, hide the row in table
      if($(".locationLevel[data-name='Country']").find('.suggestedCountriesList').find('.locations').length == 0){
        $('.locationLevel[data-name="Country"] .suggestedCountriesList').hide();
      }
      // If also that is the last country, remove the table country level
      if($(".locationLevel[data-name='Country']").find('.locations').length == 0) {
        $(".locationLevel[data-name='Country']").remove();
        updateIndex();
        checkItems($('#selectsContent'));
      }

      // Search the unmarked location in countries array and remove it
      var index = $.inArray(locIso,countries);
      if(index !== -1){
        countries.splice(index,1);
      }
      layer.setMap(null);
      mappingCountries();
    }
  });

  $('#countriesCmvs').on('change', function() {
    //console.log('Change');
  });

  $('.allLocationsButton').on('click',function(){
    changeMapDiv(this);
  });

  $('#addNewLocation-button').on('click',function(){
    changeMapDiv(this);
  });

  //Map markers list in all locations modal
  $('.marker-map').on('click',function(){
    var markerId = (this).id;
    var markerName = $(this).attr("name");

    $('.marker-map').removeClass('selected');
    $(this).addClass('selected');

    /* GET COORDINATES */
    var url = baseURL + "/geopositionByElement.do";
    var data = {
        "locElementID": markerId,
        phaseID: phaseID
    };
    $.ajax({
        url: url,
        type: 'GET',
        dataType: "json",
        data: data
    }).done(function(m) {

      if(m.geopositions.length != 0) {
        latitude = m.geopositions[0].latitude;
        longitude = m.geopositions[0].longitude;
        var latLng = new google.maps.LatLng(latitude, longitude);
        map.setCenter(latLng);
        map.setZoom(15);
      }else{
        var geocoder = new google.maps.Geocoder();

        geocoder.geocode( {'address' : markerName}, function(results, status) {
          if (status == google.maps.GeocoderStatus.OK) {
              map.setCenter(results[0].geometry.location);
              map.fitBounds(results[0].geometry.viewport);
          }
        });
      }
    });
  });

  $("#inputFormWrapper input.latitude, #inputFormWrapper input.longitude").blur(function() {
    var latitude = $("#inputFormWrapper input.latitude").val();
    var longitude = $("#inputFormWrapper input.longitude").val();
    var latLng = new google.maps.LatLng(latitude, longitude);
    map.setCenter(latLng)
  });

  $("#inputFormWrapper input.latitude, #inputFormWrapper input.longitude").on('click',function(){
    //var prevCoordinate = $(this).val();
    $(this).select();
  });

}

function modalButtonsListeners(){

  // Add location button
  $("#addLocationButton").on("click", function(e) {
    $(this).prop('disabled',true);

    //Select input
    var $locationLevelSelect = $("#locLevelSelect");
    var locationId = $locationLevelSelect.val().split("-")[0];
    var locationIsList = $locationLevelSelect.val().split("-")[1];
    var locationName = $locationLevelSelect.val().split("-")[2];

    var $locationSelect = $("#countriesCmvs");
    // checking if is list
    if(locationIsList == "true") {
      // Checking if locations select is empty
      if($locationSelect.val() != null) {

        // Checking if the location level exist in the bottom table
        if($(".locationsDataTable").find("input.locationLevelId[value='" + locationId + "']").exists()) {
          addCountryIntoLocLevel(locationId, $locationSelect, locationName);
        } else {
          addLocLevel(locationName, locationId, locationIsList, $locationSelect);
        }
      }
    } else {
      if($("#inputFormWrapper").find(".name").val().trim() == "") {
        $("#inputFormWrapper").find(".name").addClass("fieldError");
        $("#inputFormWrapper").find(".name").on('change',function(){
          $(this).removeClass('fieldError');
        });
      } else {
        $("#inputFormWrapper").find(".name").removeClass("fieldError");
        if($("#inputFormWrapper").find(".fieldError").exists()) {

        } else {
          // Checking if the location level exist in the bottom wrapper
          if($(".locationsDataTable").find("input.locationLevelId[value='" + locationId + "']").exists()) {
            addLocByCoordinates(locationId, $locationSelect, locationName)
          } else {
            addLocLevel(locationName, locationId, locationIsList, $locationSelect);
          }
        }
      }
    }
  });

  $('#addLocationModal').on('hidden.bs.modal', function () {
    cleanSelected();
  });

  // Cancel button
/*  $("#cancelButton-locations").on("click", function(e) {
    e.preventDefault();
    $("#close-modal-button").click();
  });*/

}

//Add Regions
function addRegion(option) {
  var canAdd = true;
  if(option.val() == "-1") {
    canAdd = false;
  }
  var optionValue = option.val().split("-")[0];
  var optionScope = option.val().split("-")[1];

  var $list = $(option).parents("#regionList").find(".list");
  var $item = $("#regionTemplate").clone(true).removeAttr("id");
  var v = $(option).text().length > 20 ? $(option).text().substr(0, 20) + ' ... ' : $(option).text();

  // Check if is already selected
  $list.find('.region').each(function(i,e) {
    if($(e).find('input.rId').val() == optionValue) {
      canAdd = false;
      return;
    }
  });
  if(!canAdd) {
    return;
  }

  // Set region parameters
  $item.find(".name").attr("title", $(option).text());
  $item.find(".name").html(v);
  $item.find(".rId").val(optionValue);
  $item.find(".regionScope").val(optionScope);
  $item.find(".id").val(-1);
  $list.append($item);
  $item.show('slow');
  updateRegionList($list);
  checkRegionList($list);

}

function removeRegion() {
  var $list = $(this).parents('.list');
  var $item = $(this).parents('.region');
  var value = $item.find(".rId").val();
  var scope = $item.find(".regionScope").val();
  var name = $item.find(".name").attr("title");

  var $select = $(".regionsSelect");
  $item.hide(300, function() {
    $item.remove();
    checkRegionList($list);
    updateRegionList($list);
  });
  var option = $select.find("option[value='" + value + "-" + scope + "']");
  option.prop('disabled', false);
  $('#regionSelect').select2();
}

function updateRegionList($list) {

  $($list).find('.region').each(function(i,e) {
    // Set regions indexes
    $(e).setNameIndexes(1, i);
  });
}

function checkRegionList(block) {
  var items = $(block).find('.region').length;
  if(items == 0) {
    $(block).parent().find('p.emptyText').fadeIn();
  } else {
    $(block).parent().find('p.emptyText').fadeOut();
  }
}

//Load script of google services
function loadScript() {
  var script = document.createElement("script");
  script.src = "https://maps.googleapis.com/maps/api/js?key=" + GOOGLE_API_KEY + "&libraries=places&sensor=false&callback=initMap";
  // function after load script
  script.onload = script.onreadystatechange = function() {

    $(".locationsDataTable").find(".locationLevel").each(function(index,item) {
      $(item).find(".locElement").each(function(i,locItem) {
        var latitude = $(locItem).find(".geoLatitude").val();
        var longitude = $(locItem).find(".geoLongitude").val();
        var isList = $(locItem).parent().parent().parent().find(".isList").val();
        var site = $(locItem).find(".locElementName").val();
        var idMarker = $(locItem).attr("id").split("-")[1];
        if(latitude != "" && longitude != "" && latitude != 0 && longitude != 0) {
          if($(item).find("input.locationLevelId").val() == "10") {
            addMarker(map, (idMarker), parseFloat(latitude), parseFloat(longitude), site, isList, 2);
          } else {
            addMarker(map, (idMarker), parseFloat(latitude), parseFloat(longitude), site, isList, 1);
          }
        }
      });
    });
  }
  document.body.appendChild(script);
}

//Initialization Google Map API
function initMap() {

  style = [
      {
          "featureType": "water",
          "stylers": [
              {
                "visibility": "on"
              }, {
                "color": "#b5cbe4"
              }
          ]
      }, {
          "featureType": "landscape",
          "stylers": [
            {
              "color": "#efefef"
            }
          ]
      }, {
          "featureType": "road.highway",
          "elementType": "geometry",
          "stylers": [
            {
              "color": "#83a5b0"
            }
          ]
      }, {
          "featureType": "road.arterial",
          "elementType": "geometry",
          "stylers": [
            {
              "color": "#bdcdd3"
            }
          ]
      }, {
          "featureType": "road.local",
          "elementType": "geometry",
          "stylers": [
            {
              "color": "#ffffff"
            }
          ]
      }, {
          "featureType": "poi.park",
          "elementType": "geometry",
          "stylers": [
            {
              "color": "#e3eed3"
            }
          ]
      }, {
          "featureType": "administrative",
          "stylers": [
              {
                "visibility": "on"
              }, {
                "lightness": 33
              }
          ]
      }, {
        "featureType": "road"
      }, {
          "featureType": "poi.park",
          "elementType": "labels",
          "stylers": [
              {
                "visibility": "on"
              }, {
                "lightness": 20
              }
          ]
      }, {}, {
          "featureType": "road",
          "stylers": [
            {
              "lightness": 20
            }
          ]
      }
  ];
  var mapDiv = document.getElementById('map');
  map = new google.maps.Map(mapDiv, {
      center: new google.maps.LatLng(14.41, -12.52),
      zoom: 3,
      mapTypeId: 'roadmap',
      styles: style
  });

  //Create the search box and link it to the UI element.
  var input = document.getElementById('pac-input');
  var searchBox = new google.maps.places.SearchBox(input);
  map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

  // Bias the SearchBox results towards current map's viewport.
  map.addListener('bounds_changed', function() {
    searchBox.setBounds(map.getBounds());
  });

  //Listen for the event fired when the user selects a prediction and retrieve
  // more details for that place.
  searchBox.addListener('places_changed', function() {
    var places = searchBox.getPlaces();

    if (places.length == 0) {
      return;
    }

    // For each place, get the icon, name and location.
    var bounds = new google.maps.LatLngBounds();
    places.forEach(function(place) {
      if (!place.geometry) {
        console.log("Returned place contains no geometry");
        return;
      }
      var icon = {
        url: place.icon,
        size: new google.maps.Size(71, 71),
        origin: new google.maps.Point(0, 0),
        anchor: new google.maps.Point(17, 34),
        scaledSize: new google.maps.Size(25, 25)
      };

      if (place.geometry.viewport) {
        // Only geocodes have viewport.
        bounds.union(place.geometry.viewport);
      } else {
        bounds.extend(place.geometry.location);
      }
    });
    map.fitBounds(bounds);
  });

  //Center marker into map on click
  $('<div/>').addClass('centerMarker').css('display','none').appendTo(map.getDiv()).click(function(){
    var option = $("#locLevelSelect").find("option:selected");
    if(option.val().split("-")[0] == "2"){
      addLocationFromMap();
    }
 });
  var centerControlDiv = document.createElement('div');
  if(editable && $("span.has_otherLoc").text() == "true") {
    var centerControl = new CenterControl(centerControlDiv, map);
    centerControlDiv.index = 1;
    map.controls[google.maps.ControlPosition.TOP_CENTER].push(centerControlDiv);
  }

  infoWindow = new google.maps.InfoWindow();

  google.maps.event.addListener(infoWindow, 'closeclick', function() {
    $(".locations").removeClass("selected");
  });

  google.maps.event.addListener(map, 'click', function(event) {
    // infoWindow.close();
    $(".locations").removeClass("selected");
  });

// google.maps.event.addListener(map, 'rightclick', function(e) {
// openInfoWindowForm(e);
// });

  if(markers.length > 0) {
    map.setCenter(markers[markers.length - 1].getPosition());
  }

  mappingCountries();

  // bounds of the desired area
  var allowedBounds = new google.maps.LatLngBounds(
      new google.maps.LatLng(-80.711903, -175.058594),
      new google.maps.LatLng(80.829945, 175.839844)
  );
  var lastValidCenter = map.getCenter();

  map.addListener('center_changed', function() {
    // after the center of the map has changed, pan back to the
    // marker.
    $("#inputFormWrapper").find(".latitude").val(map.getCenter().lat());
    $("#inputFormWrapper").find(".longitude").val(map.getCenter().lng());

    if (allowedBounds.contains(map.getCenter())) {
      // still within valid bounds, so save the last valid position
      lastValidCenter = map.getCenter();
      return;
    }

    // not valid anymore => return to last valid position
    map.panTo(lastValidCenter);

  });

}

function mappingCountries() {
  var query = "";
  layer = new google.maps.FusionTablesLayer();
  if(countries.length > 0) {

    $.each(countries, function(i,c) {
      if(i == countries.length - 1) {
        query = query + "'" + c + "'";
      } else {
        query = query + "'" + c + "',";
      }
    });
    var FT_Options = {
        suppressInfoWindows: true,
        query: {
            select: 'kml_4326',
            from: 2597535,
            where: "'ISO_2DIGIT' IN (" + query + ") "
        },
        styles: [
          {
            polygonOptions: {
                fillColor: "#2E2EFE",
                fillOpacity: 0.15
            }
          }
        ]
    };
    layer = new google.maps.FusionTablesLayer(FT_Options);
    layer.setMap(map);
/*    google.maps.event.addListener(layer, 'click', function(e) {
      openInfoWindowCountries(e);
    });*/
  }

}

//Remove a location element-Function
function removeLocationItem() {
  var globalList = $(this).parents("#selectsContent");
  var list = $(this).parents(".optionSelect-content");
  var $item = $(this).parents('.locElement');
  console.log($item.attr('data-locId'));
  //var $allLocListItem = $('.allLocations-container .list-container').find();

  if($item.find(".geoLatitude").val() != "" && $item.find(".geoLongitude").val() != "") {
    var optionValue = $item.attr("id").split('-');
    var id = optionValue[1];
    if(markers[id] == undefined) {

    } else {
      removeMarker(id);
    }
  }
  $item.hide(function() { //here
    $item.remove();

    if($(list).find(".locElement").length == 0 && $(".locationLevel[data-name='Country']").find('.suggestedCountriesList').find('.locations').length == 0) {
      $(list).parents(".locationLevel").remove();
    }
    updateIndex();
    checkItems(globalList);
  });
  layer.setMap(null);
  /* Remove of countries array */

  var index = countries.indexOf($item.find(".locElementCountry").val());
  if(index > -1) {
    countries.splice(index, 1);
  }
  mappingCountries();
}

//Set default country to countries select
function setCountryDefault() {
// Ajax for country name
  $.ajax({
      'url': 'https://maps.googleapis.com/maps/api/geocode/json',
      'data': {
          key: GOOGLE_API_KEY,
          latlng: (map.getCenter().lat() + "," + map.getCenter().lng())
      },
      success: function(data) {
        if(data.status == 'OK') {
          var country = getResultByType(data.results[0], 'country').short_name;
          var $countrySelect = $("#countriesCmvs");
          arSelectedLocations.push($countrySelect.find("option." + country).val());
          $countrySelect.val(arSelectedLocations);
          $countrySelect.select2().trigger("change");
        } else {
          console.log(data.status);
        }
      },
  });
}

//Get short and long country name
function getResultByType(results,type) {
  if(results) {
    for(var i = 0; i < results.address_components.length; i++) {
      var types = results.address_components[i].types;
      for(var typeIdx = 0; typeIdx < types.length; typeIdx++) {
        if(types[typeIdx] == type) {
          return {
              short_name: results.address_components[i].short_name,
              long_name: results.address_components[i].long_name
          };
        }
      }
    }
  } else {
    return undefined;
  }
}

function addLocationFromMap(){
  setCountryDefault();
}

function notify(text) {
  var notyOptions = jQuery.extend({}, notyDefaultOptions);
  notyOptions.text = text;
  notyOptions.type = 'alert';
  noty(notyOptions);
}


//Adding location level with locElements
function addLocLevel(locationName,locationId,locationIsList,$locationSelect) {
  var $locationItem = $("#locationLevel-template").clone(true).removeAttr("id");
  $locationItem.find(".locLevelName").html(locationName);
  $locationItem.find("input.locationLevelId").val(locationId);
  $locationItem.find("input.locationLevelName").val(locationName);
  $locationItem.find("input.isList").val(locationIsList);
  $(".locationsDataTable > tbody:last-child").append($locationItem);

  //$(".locationsDataTable").find("countriesList").children().append($locationItem);
  $locationItem.show("slow");
  updateIndex();
  if(locationIsList == "true") {
    if(locationName == "Country") {
    } else {
     $locationItem.find(".allCountriesQuestion").show();
     $locationItem.find("span.question").html($("span.qCmvSites").text());
    }
    addCountryIntoLocLevel(locationId, $locationSelect, locationName);
  } else {
    addLocByCoordinates(locationId, $locationSelect, locationName);
  }
}

function addLocByCoordinates(locationId,$locationSelect,locationName) {
  var countryName = "";
  var $list =
      $(".locationsDataTable").find("input.locationLevelId[value='" + locationId + "']").parent().find(
          ".optionSelect-content");
  var $item = $('#location-template').clone(true).removeAttr("id");
  countID++;
  var latitude = $("#inputFormWrapper").find(".latitude").val();
  var longitude = $("#inputFormWrapper").find(".longitude").val();
  console.log(latitude);
  console.log(longitude);
  var name = $("#inputFormWrapper").find("input.name").val();
  // Ajax for country name
  $.ajax({
      'url': 'https://maps.googleapis.com/maps/api/geocode/json',
      'data': {
          key: GOOGLE_API_KEY,
          latlng: (latitude + "," + longitude)
      },
      success: function(data) {
        if(data.status == 'OK') {
          $item.find('input.locElementCountry').val(getResultByType(data.results[0], 'country').short_name);
        } else {
          console.log(data.status);
        }
      },
      complete: function(data) {
        $item.attr("id", "location-" + (countID));
        $item.find('.locationName').html(
            '<span class="lName">' + name + '</span><span class="lPos"> (' + parseFloat(latitude).toFixed(4) + ', '
                + parseFloat(longitude).toFixed(4) + ' )</span> ');
        $item.find('.geoLatitude').val(latitude);
        $item.find('.geoLongitude').val(longitude);
        $item.find('.locElementName').val(name);
        $item.find('.locElementId').val(-1);
        $list.append($item);
        $item.show('slow');
        // add marker
        addMarker(map, (countID), parseFloat(latitude), parseFloat(longitude), name, "false", 1);

      //Show and hide Successfully added message
        $('#alert-succesfully-added').slideDown();
        setTimeout(function(){
          $('#alert-succesfully-added').slideUp();
        }, 2000);

        // update indexes
        updateIndex();
        checkItems($list.parents("#selectsContent"));
      }
  });
  $('#inputFormWrapper .nameWrapper input').val('');
}

function updateIndex() {
  $('.locationsDataTable ').find('.locationLevel').each(function(i,e) {
    $(e).setNameIndexes(1, i);
    $.each($(e).find(".locElement"), function(index,element) {
      $(element).setNameIndexes(2, index);
    });
  });
  // Update component event
  $(document).trigger('updateComponent');
}

function setMapCenterPosition($item,locId,locName,countID){
  /* GET COORDINATES */
  var url = baseURL + "/geopositionByElement.do";
  var data = {
      "locElementID": locId,
      phaseID: phaseID
  };
  $.ajax({
      url: url,
      type: 'GET',
      dataType: "json",
      data: data
  }).done(function(m) {

    if(m.geopositions.length != 0) {
      latitude = m.geopositions[0].latitude;
      longitude = m.geopositions[0].longitude;
      $item.find('.geoLatitude').val(latitude);
      $item.find('.geoLongitude').val(longitude);
      addMarker(map, (countID), parseFloat(latitude), parseFloat(longitude), locName, "true", 2);
      var latLng = new google.maps.LatLng(latitude, longitude);
      map.setCenter(latLng);
    }else{
      var geocoder = new google.maps.Geocoder();

      geocoder.geocode( {'address' : locName}, function(results, status) {
        if (status == google.maps.GeocoderStatus.OK) {
            map.setCenter(results[0].geometry.location);
        }
      });
    }
  });
}

//Adding locElement into location level(Country and CSVS)
function addCountryIntoLocLevel(locationId,$locationSelect,locationName) {
  var locationContent =
      $(".locationsDataTable").find("input.locationLevelId[value='" + locationId + "']").parent().find(
          ".optionSelect-content");
  var countryList = $(".list-container").find(".Country");
  $.each($locationSelect.val(), function(i,e) {
    var $item = $("#location-template").clone(true).removeAttr("id");
    var locId = e.split("-")[0];
    var locIso = e.split("-")[1];
    var locName = e.split("-")[2];
    // Check if the item doesn't exists into the list
    if(locationContent.find("input.locElementId[value='" + locId + "']").exists()) {
      notify(locName + " already exists into the " + locationContent.parent().parent().parent().find(".locationLevelName").val()
          + " list")
    } else {
      countID++;
      setMapCenterPosition($item,locId,locName,countID);

      $item.attr("id", "location-" + (countID));
      $item.find(".lName").html(locName);
      $item.find(".locElementName").val(locName);
      $item.find(".locElementId").val(locId);

      // If is a country
      if(locationName == "Country") {
        countries.push(locIso);
        $item.find(".locElementCountry").val(locIso);
      }
      locationContent.find(".countriesList").children().append($item);
      $item.show("slow");

      //Show and hide Successfully added message
      $('#alert-succesfully-added').slideDown();
      setTimeout(function(){
        $('#alert-succesfully-added').slideUp();
      }, 2000);


      // Add Country into all locations modal list
      var $listItem = $('#itemList-template').clone(true).removeAttr("id");
      $listItem.attr('id',locId);
      $listItem.attr('name',locIso);
      $listItem.find(".item-name").text(locName);
      countryList.append($listItem);
    }
  });
  updateIndex();
  checkItems(locationContent.parents("#selectsContent"));
  //infoWindow.close();
  if(locationName == "Country") {
    layer.setMap(null);
    mappingCountries();
  }
  cleanSelected();
  //$("#close-modal-button").click();
}

function cleanSelected(){
  arSelectedLocations=[];
  var option = $("#locLevelSelect").find("option:selected");
  //LocElements options using ajax
  var select = $("#countriesCmvs");
  var url = baseURL + "/searchCountryListPL.do";
  var data = {
      parentId: option.val().split("-")[0],
      phaseID: phaseID
  };
  $.ajax({
      url: url,
      type: 'GET',
      dataType: "json",
      data: data
  }).done(
    function(m) {
    select.empty();
    for(var i = 0; i < m.locElements.length; i++) {
      select.append("<option class='" + m.locElements[i].isoAlpha2 + "' value='" + m.locElements[i].id
          + "-" + m.locElements[i].isoAlpha2 + "-" + m.locElements[i].name + "' >"
          + m.locElements[i].name + "</option>");
    }
  });
}

function checkItems(block) {
  var items = $(block).find('.locElement').length;
  if(items == 0) {
    $(block).find('p.inf').fadeIn();
  } else {
    $(block).find('p.inf').fadeOut();
  }
}

//Map events

function addMarker(map,idMarker,latitude,longitude,sites,isList,locType) {
  // Close info window
  //infoWindow.close();
  var drag;
  if(editable && isList == "false") {
    drag = true;
  } else {
    drag = false;
  }

  var $item = $("#location-" + idMarker);
  var marker = new google.maps.Marker({
      id: idMarker,
      draggable: drag,
      position: {
          lat: latitude,
          lng: longitude
      },
      icon: baseUrl + '/global/images/otherSite-marker.png',
      name: sites,
      animation: google.maps.Animation.DROP,
      list: isList
  });
  markers[idMarker] = {
      gElement: marker,
      type: locType
  };
// To add the marker to the map, call setMap();
  marker.setMap(map);
  map.setCenter(marker.getPosition());

  // MARKER EVENTS
  marker.addListener('click', function() {

    $(".locations").removeClass("selected");
    $item.find(".locations").addClass("selected");

    var infoWindow = new google.maps.InfoWindow();
    infoWindow.setOptions({
      content: sites,
      position: marker.getPosition()
    });
    arInfoWindows.push(infoWindow);
    infoWindow.open(map);
  });

}

//Delete markers
function deleteMarkers() {
  setAllMap(null);
  markers = [];
}

// Sets the map on all markers in the array.
function setAllMap(map) {
  $.each(markers, function(index,marker) {
    if(marker.gElement) {
      marker.gElement.setMap(map);
    }
  });
}

// Remove individual marker by id
function removeMarker(id) {
  marker = markers[id].gElement;
  marker.setMap(null);
  delete markers[id];
}

// Removes the markers from the map, but keeps them in the array.
function clearMarkers() {
  setAllMap(null);
}

// Shows any markers currently in the array.
function showMarkers() {
  setAllMap(map);
}

// This function is for change the map's div
// for the "add locations modal" and the "all locations modal"
function changeMapDiv(selectedButton){
  var mapCurrentNode = map.getDiv();
  var selectedModal = $(selectedButton).data('target');

  //infoWindow.close();

  if(selectedModal == '.addLocationModal'){
    map.setZoom(3);
    $('#add-location-map').append(mapCurrentNode);
    $('#map').removeClass('all-locations');

    closeAllInfoWindows();

    var option = $("#locLevelSelect").find("option:selected");
    if(option.val() == "-1" || option.val().split("-")[0] == "10"){
      //If the selected option is "Climate Smart Village" hide the center indicator in the map
      $('#map .centerMarker').hide();
    }else{
      $('#map .centerMarker').show();
    }
  }else if(selectedModal == '.allLocationsModal'){
    $('#all-locations-map').append(mapCurrentNode);
    $('#map').addClass('all-locations');
    $('#map .centerMarker').hide();
  }
}

function closeAllInfoWindows(){
  for(var i=0; i<arInfoWindows.length;i++){
    arInfoWindows[i].close();
  }
}

function addSuggestedCountry(locIso,locName,locId){

  var countryRow = $(".locationsDataTable").find("input.locationLevelId[value='2']");
  var countryList = $(".list-container").find(".Country");

  // Add suggested country in locations table
  var $tableItem = $('#suggestedLocation-template').clone(true).removeAttr("id");

  $tableItem.find(".locationName").attr('id',locIso);
  $tableItem.find(".lName").text(locName);
  countryRow.parent().find('.suggestedCountriesList').children().append($tableItem);
  $tableItem.show('slow');

  // Add suggested country in locations list (all locations modal)
  var $listItem = $('#itemList-template').clone(true).removeAttr("id");
  $listItem.attr('id',locId);
  $listItem.attr('name',locName);
  $listItem.find(".item-name").text(locName);
  countryList.append($listItem);
}

// here
function removeSuggestedCountry(locIso,locId,countryRow,countryList){
  // Remove unmarked location from locations table
  countryRow.parent().find('.suggestedCountriesList').children().find('#'+locIso).
    parent().parent().remove();

  // Remove unmarked location from locations list (all locations modal)
  countryList.find("#"+locId).remove();
}