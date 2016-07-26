[#ftl]
[#-------------------------------------------------------------------------------- 
    Please don't modify this file, if you want add/remove an extra dependency 
    refer to bower.js and execute (bower install/uninstall --save) then (grunt) 
    commands in marlo-web directory.
----------------------------------------------------------------------------------]

[#macro css_imports libraryName]
  [#-- bower:css --]
  [#if libraryName="jquery-ui" ]<link rel="stylesheet" href="${baseUrl}/bower_components/jquery-ui/themes/base/jquery-ui.min.css" />[/#if]
  [#if libraryName="jReject" ]<link rel="stylesheet" href="${baseUrl}/bower_components/jReject/css/jquery.reject.css" />[/#if]
  [#if libraryName="select2" ]<link rel="stylesheet" href="${baseUrl}/bower_components/select2/dist/css/select2.min.css" />[/#if]
  [#if libraryName="datatables" ]<link rel="stylesheet" href="${baseUrl}/bower_components/datatables/media/css/jquery.dataTables.css" />[/#if]
  [#if libraryName="dropzone" ]<link rel="stylesheet" href="${baseUrl}/bower_components/dropzone/dist/min/dropzone.min.css" />[/#if]
  [#if libraryName="animate.css" ]<link rel="stylesheet" href="${baseUrl}/bower_components/animate.css/animate.css" />[/#if]
  [#if libraryName="bootstrap" ]<link rel="stylesheet" href="${baseUrl}/bower_components/bootstrap/dist/css/bootstrap.min.css" />[/#if]
  [#if libraryName="flat-flags" ]<link rel="stylesheet" href="${baseUrl}/bower_components/flat-flags/css/main.css" />[/#if]
  [#if libraryName="bootstrap-select" ]<link rel="stylesheet" href="${baseUrl}/bower_components/bootstrap-select/dist/css/bootstrap-select.css" />[/#if]
  [#if libraryName="cytoscape-panzoom" ]<link rel="stylesheet" href="${baseUrl}/bower_components/cytoscape-panzoom/cytoscape.js-panzoom.css" />[/#if]
  [#if libraryName="cytoscape-panzoom" ]<link rel="stylesheet" href="${baseUrl}/bower_components/cytoscape-panzoom/font-awesome-4.0.3/css/font-awesome.css" />[/#if]
  [#if libraryName="datatables.net-bs" ]<link rel="stylesheet" href="${baseUrl}/bower_components/datatables.net-bs/css/dataTables.bootstrap.css" />[/#if]
  [#if libraryName="jQuery-Timelinr" ]<link rel="stylesheet" href="${baseUrl}/bower_components/jQuery-Timelinr/css/style.css" />[/#if]
  [#-- endbower --]
[/#macro]

[#macro js_imports libraryName]
  [#-- bower:js --]
  [#if libraryName="jquery"]<script src="${baseUrl}/bower_components/jquery/dist/jquery.min.js"></script>[/#if]
  [#if libraryName="jquery-ui"]<script src="${baseUrl}/bower_components/jquery-ui/jquery-ui.min.js"></script>[/#if]
  [#if libraryName="html5shiv"]<script src="${baseUrl}/bower_components/html5shiv/dist/html5shiv.js"></script>[/#if]
  [#if libraryName="jReject"]<script src="${baseUrl}/bower_components/jReject/js/jquery.reject.js"></script>[/#if]
  [#if libraryName="noty"]<script src="${baseUrl}/bower_components/noty/js/noty/packaged/jquery.noty.packaged.js"></script>[/#if]
  [#if libraryName="select2"]<script src="${baseUrl}/bower_components/select2/dist/js/select2.min.js"></script>[/#if]
  [#if libraryName="autogrow-textarea"]<script src="${baseUrl}/bower_components/autogrow-textarea/jquery.autogrowtextarea.js"></script>[/#if]
  [#if libraryName="datatables"]<script src="${baseUrl}/bower_components/datatables/media/js/jquery.dataTables.js"></script>[/#if]
  [#if libraryName="dropzone"]<script src="${baseUrl}/bower_components/dropzone/dist/min/dropzone.min.js"></script>[/#if]
  [#if libraryName="jsUri"]<script src="${baseUrl}/bower_components/jsUri/Uri.js"></script>[/#if]
  [#if libraryName="bootstrap"]<script src="${baseUrl}/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>[/#if]
  [#if libraryName="bootstrap-select"]<script src="${baseUrl}/bower_components/bootstrap-select/dist/js/bootstrap-select.js"></script>[/#if]
  [#if libraryName="vanilla-color-picker"]<script src="${baseUrl}/bower_components/vanilla-color-picker/dist/vanilla-color-picker.min.js"></script>[/#if]
  [#if libraryName="countdown"]<script src="${baseUrl}/bower_components/countdown/dest/jquery.countdown.js"></script>[/#if]
  [#if libraryName="pusher-websocket-iso"]<script src="${baseUrl}/bower_components/pusher-websocket-iso/dist/web/pusher.js"></script>[/#if]
  [#if libraryName="cytoscape"]<script src="${baseUrl}/bower_components/cytoscape/dist/cytoscape.js"></script>[/#if]
  [#if libraryName="cytoscape-panzoom"]<script src="${baseUrl}/bower_components/cytoscape-panzoom/cytoscape-panzoom.js"></script>[/#if]
  [#if libraryName="datatables.net"]<script src="${baseUrl}/bower_components/datatables.net/js/jquery.dataTables.js"></script>[/#if]
  [#if libraryName="datatables.net-bs"]<script src="${baseUrl}/bower_components/datatables.net-bs/js/dataTables.bootstrap.js"></script>[/#if]
  [#if libraryName="jQuery-Timelinr"]<script src="${baseUrl}/bower_components/jQuery-Timelinr/js/jquery.timelinr-0.9.6.js"></script>[/#if]
  [#-- endbower --]
[/#macro]
