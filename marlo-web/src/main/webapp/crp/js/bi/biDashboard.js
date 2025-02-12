$(document).ready(init);

function init() {
  addEvents();
}

function addEvents() {
  var idReport = $('.reportSection').children().first().attr('class');
  executePetition(idReport);
  $('.reportSection').on('click', function () {
    var idReport = $(this).children().first().attr('class');
    var inputsContainer = $('#' + idReport + '-contentOptions');
    if (!($(inputsContainer).hasClass('loaded'))) {
      executePetition(idReport);
    }
  });
  $('.reportSection a, .reportSection span').on('click', selectBIReport);
  // $('.selectedReportBIContainer').on('click', function () {
  //   reportsMenuToggle();
  // });
  // $('.selectedReportBIContainer').hover(function () {
  //   $('#repportsMenu').slideDown("fast");
  //   $('.reportsButtonsIcon').addClass("glyphicon-chevron-down");
  // }, function () {
  //   reportsMenuToggle();
  // });

  $('.setFullScreen').on('click', function () {
    fullScreenDashboard();
  });

  if(userCanLeaveComments = $('#userCanLeaveComments').html() == 'false'){
    $('[has-role-authorization="true"]').hide();
  }
  
}

// Toggle width of reports menu
function reportsMenuToggle() {
  $('#repportsMenu').slideToggle("fast");
  $('.reportsButtonsIcon').toggleClass("glyphicon-chevron-down");
}

// Open dashboard in full screen
function fullScreenDashboard() {
  // Get a reference to the embedded report HTML element
  var currentID = $("div[class$='current']").attr("id");
  var embedContainer = $("#" + currentID + '-contentOptions').children().first()[0];

  // Get a reference to the embedded report.
  report = powerbi.get(embedContainer);
  report.updateSettings({})
    // report.updateSettings(newSettings)
    .then(function () {
      report.fullscreen();
      console.log("full Screen Dashboard.");
    })
    .catch(function (error) {
      console.log(errors);
    });
}

//Request to BireportsTokenAction
function executePetition(idReport) {
  var $inputsContainer = $('#' + idReport + '-contentOptions');
  var data = {
    id: idReport.replace("BIreport-", "")
  }

  //Ajax request to back for PowerBi
  $.ajax({
    'url': baseURL + '/biReportsTokenAction.do',
    'type': "GET",
    'data': data,
    'dataType': "json",
    success: function (metadata) {
      var embedUrl = $inputsContainer.find('input[name=embedUrl]').val();
      var reportId = $inputsContainer.find('input[name=reportId]').val();
      // console.log(metadata.token, embedUrl, reportId, idReport);
      setReportTitle();
      embedPBI(metadata.token, embedUrl, reportId, idReport);
    },
    error: function (e) {
      console.log("error");
    }
  });
}

// Set the report title and description
function setReportTitle() {
  var reportTitle = $("div[class$='current']").attr("report-title");
  $('.headTitle.text-left').text(reportTitle + '');
}

// Embed Dashboard
function embedPBI(embedToken, embededURL, dashboardId, contentId) {
  // Get models. models contains enums that can be used.
  var models = window['powerbi-client'].models;
  var permissions = models.Permissions.All;
  var hasFilters = $("div[class$='current']").attr("has-filters");
  if (hasFilters == 'true') {
    hasFilters = true;
  } else {
    hasFilters = false;
  }

  // Embed configuration used to describe the what and how to embed.
  // This object is used when calling powerbi.embed.
  // This also includes settings and options such as filters.
  // You can find more information at https://github.com/Microsoft/PowerBI-JavaScript/wiki/Embed-Configuration-Details.

  var config = {
    type: 'report',
    tokenType: models.TokenType.Embed,
    accessToken: embedToken,
    embedUrl: embededURL,
    id: dashboardId,
    permissions: permissions,
    settings: {
      filterPaneEnabled: hasFilters,
      navContentPaneEnabled: true
    }
  };

  // Get a reference to the embedded dashboard HTML element
  //$embedContainer =
  var $dashboardContainer = $("#" + contentId + '-contentOptions').children().first();
  var dashboard = powerbi.embed($dashboardContainer.get(0), config);
  var $dashboard = $("#" + contentId + '-contentOptions');

  // Dashboard.off removes a given event handler if it exists.
  dashboard.off("loaded");

  // Dashboard.on will add an event handler which prints to Log window.
  dashboard.on("loaded", function () {
    $dashboard.addClass('loaded');
    removeNavPanel(contentId);
    //removeFilterPanel(contentId);
  });

  dashboard.on("error", function (event) {
    console.log(event.detail);
    dashboard.off("error");
  });

  dashboard.off("tileClicked");
}

//Function to set a value for the acronym filter, value is an array
function filterAcronym(value) {
  // Build the filter you want to use. For more information, See Constructing
  // Filters in https://github.com/Microsoft/PowerBI-JavaScript/wiki/Filters.
  const filter = {
    $schema: "http://powerbi.com/product/schema#basic",
    target: {
      table: "project_submission",
      hierarchy: "acronym Hierarchy",
      hierarchyLevel: "acronym"
    },
    operator: "In",
    values: value
  };

  // Get a reference to the embedded report HTML element
  var currentID = $("div[class$='current']").attr("id");
  var embedContainer = $("#" + currentID + '-contentOptions').children().first()[0];

  // Get a reference to the embedded report.
  report = powerbi.get(embedContainer);

  // Set the filter for the report.
  // Pay attention that setFilters receives an array.
  report.setFilters([filter])
    .then(function () {
      console.log("Report filter was set.");
    })
    .catch(function (errors) {
      console.log(errors);
    });
}

//Function to remove the filter panel
function removeFilterPanel(contentId) {
  var newSettings = {
    panes: {
      filters: {
        visible: false
      }
    }
  };
  //Get a reference to the embedded report HTML element
  var embedContainer = $("#" + contentId + '-contentOptions').children().first()[0];
  $('.dashboardContainer').height(250);

  // Get a reference to the embedded report.
  report = powerbi.get(embedContainer);

  // Remove the filters currently applied to the report.
  report.updateSettings(newSettings)
    .then(function () {
      console.log("Report filters were removed.");
    })
    .catch(function (errors) {
      console.log(errors);
    });
}


//Function to remove the navPanel
function removeNavPanel(contentId) {

  // Power bi models.
  var models = window['powerbi-client'].models;

  //The new settings that you want to apply to the report.
  const newSettings = {
    panes: {
      pageNavigation: {
        visible: true
      }
    },
    layoutType: models.LayoutType.Custom,
    customLayout: {
      displayOption: models.DisplayOption.FitToPage,
      // displayOption: models.DisplayOption.FitToWidth,
      pagesLayout: {}
    }
  };


  // Get a reference to the embedded report HTML element
  var embedContainer = $("#" + contentId + '-contentOptions').children().first()[0];


  // Get a reference to the embedded report.
  report = powerbi.get(embedContainer);

  // Update the settings by passing in the new settings you have configured.
  report.updateSettings(newSettings)
    .then(function () {
      console.log("Dashboard was updated.");
    })
    .catch(function (error) {
      console.log(errors);
    });

  // Update height of iframe container depending on dashboard page height
  updateReportHeight(contentId, report, models);

  report.on("buttonClicked", function () {
    updateReportHeight(contentId, report,models);
  });
}

function updateReportHeight(contentId, report, models) {
  var reportId = contentId.split('BIreport-')[1];
  report.getPages().then(function (pages) {
    pages[0].hasLayout(models.LayoutType.MobilePortrait).then(function (hasLayout) {
      pages.forEach(page => {
        if (page.isActive) {
          $("#dashboardContainer-" + reportId).css("height", (page.defaultSize.height));
        }
      });
    })
  });
}

function selectBIReport(e) {
  e.preventDefault();
  var $section = $(e.target).parents('.reportSection');
  var $content = $('#' + $section.attr('id') + '-contentOptions');
  $section.siblings().removeClass('current');
  $section.addClass('current');
  $content.siblings().hide();
  setReportTitle();
  // reportsMenuToggle();
  $content.fadeIn();
}