[#ftl]
[#assign title = "Project Locations" /]
[#assign currentSectionString = "project-${actionName?replace('/','-')}-${projectID}-phase-${(actualPhase.id)!}" /]
[#assign pageLibs = ["select2"] /]
[#assign customJS = [
  "${baseUrlMedia}/js/projects/new-projectLocations.js", 
  "${baseUrl}/global/js/autoSave.js",
  "${baseUrl}/global/js/fieldsValidation.js"
  ] 
/] 
[#assign customCSS = ["${baseUrlMedia}/css/projects/projectLocations.css" ] /]
[#assign currentSection = "projects" /]
[#assign currentStage = "locations" /]
[#assign hideJustification = true /]

[#assign breadCrumb = [
  {"label":"projectsList", "nameSpace":"/projects", "action":"${(crpSession)!}/projectsList"},
  {"label":"projectLocations", "nameSpace":"/projects", "action":""}
] /]

[#assign locationLevelName = "project.locationsData"/]
[#assign locationName = "locElements"/]
[#assign countID = 0/]

[#include "/WEB-INF/crp/pages/header.ftl" /]
[#include "/WEB-INF/crp/pages/main-menu.ftl" /]
[#import "/WEB-INF/global/macros/utils.ftl" as utilities/]

<div class="container helpText viewMore-block">
  <div  class="helpMessage infoText">
    <img class="col-md-2" src="${baseUrl}/global/images/icon-help.jpg" />
    <p class="col-md-10">[#if reportingActive] [@s.text name="projectLocations.help2" /] [#else] [@s.text name="projectLocations.help1" /] [/#if] </p>
  </div> 
  <div style="display:none" class="viewMore closed"></div>
</div>
    
[#if (!availabePhase)!false]
  [#include "/WEB-INF/crp/views/projects/availability-projects.ftl" /]
[#else]
<section class="container">
    <div class="row">
      [#-- Project Menu --]
      <div class="col-md-3">
        [#include "/WEB-INF/crp/views/projects/menu-projects.ftl" /]
      </div>
      [#-- Project Section Content --]
      <div class="col-md-9">
        [#-- Section Messages --]
        [#include "/WEB-INF/crp/views/projects/messages-projects.ftl" /]
      
        [@s.form action=actionName method="POST" enctype="multipart/form-data" cssClass=""]
          <input class="projectInfo" type="hidden" name="project.projectInfo.id" value="${project.projectInfo.id}" />
          
          <div class="row">
            <h3 class="headTitle col-md-7">[@s.text name="projectLocations.title" /]</h3>  
          </div>
          <div id="" class="borderBox projectLocationsWrapper">
            [#-- Content--]
              <div class="row">
                [#-- GLOBAL DIMENSION --]
                <div class="form-group col-md-12">
                  [@customForm.yesNoInput  label="projectLocations.globalDimension" name="project.projectInfo.locationGlobal"  editable=editable && action.hasSpecificities("crp_other_locations") inverse=false  cssClass="" /] 
                </div>
                <br />
                <div class="form-group col-md-12 ">
                  <hr />
                </div>
                [#-- REGIONAL DIMENSION --]
                <div class="form-group col-md-12">
                  [@customForm.yesNoInput  label="projectLocations.regionalDimension" name="project.projectInfo.locationRegional"   editable=editable && action.hasSpecificities("crp_other_locations") inverse=false  cssClass="isRegional" /]
                  [#if editable && action.hasSpecificities("crp_other_locations")]
                    <small style="color: #337ab7;">[@s.text name="projectLocations.regionsNote" /]</small>
                  [/#if]
                </div>
                
                  [#-- REGIONS SELECT --]
                  <div class="row">
                    <div class="regionsBox form-group col-md-12" style="display:${(project.projectInfo.locationRegional?string("block","none"))!"none"};">
                      <div class="panel tertiary col-md-12">
                       <div class="panel-head">
                         <label for=""> [@customForm.text name="projectCofunded.selectRegions" readText=!editable /]:[@customForm.req required=editable /]</label>
                         <br />
                         <small style="color: #337ab7;">([@s.text name="projectLocations.standardLocations" /])</small>
                       </div>
                       
                        <div id="regionList" class="panel-body" listname="project.projectRegions"> 
                          <ul class="list">
                          [#if project.projectRegions?has_content]
                            [#list project.projectRegions as region]
                              <li id="" class="region clearfix col-md-3">
                                [#if editable ]
                                  <div class="removeRegion removeIcon" title="Remove region"></div>
                                [/#if]
                                  <input class="id" type="hidden" name="project.projectRegions[${region_index}].id" value="${region.id}" />
                                [#if region.locElement?has_content ]
                                  <span class="name" title="${(region.locElement.name)!}">[@utilities.wordCutter string=(region.locElement.name)!'No name' maxPos=20 /]</span>
                                  <input class="regionScope" type="hidden" name="project.projectRegions[${region_index}].scope" value="${(region.locElement.locElementType.scope?c)!}" />
                                  <input class="rId" type="hidden" name="project.projectRegions[${region_index}].locElement.id" value="${(region.locElement.id)!}" />
                                [#else]
                                  <span class="name" title="${(region.locElementType.name)!}">[@utilities.wordCutter string=(region.locElementType.name)!'No name' maxPos=20 /]</span>
                                  <input class="regionScope" type="hidden" name="project.projectRegions[${region_index}].scope" value="${(region.locElementType.scope?c)!}" />
                                  <input class="rId" type="hidden" name="project.projectRegions[${region_index}].locElementType.id" value="${(region.locElementType.id)!}" />
                                [/#if]  
                                  <div class="clearfix"></div>
                              </li>
                            [/#list]
                          [#else]
                            <p class="emptyText"> [@s.text name="No regions added yet." /]</p> 
                          [/#if]
                          </ul>
                          [#if editable ]
                            <select name="" id="regionSelect" class="regionsSelect">
                              <option value="-1">[@s.text name="form.select.placeholder" /]</option>
                              [#if scopeRegionLists?has_content]
                              <optgroup label="${(loggedCrp.acronym?upper_case)!} regions">
                                [#list scopeRegionLists as region]
                                <option value="${(region.id)!}-${(region.scope?c)!}">${(region.name)!}</option>
                                [/#list]
                              </optgroup>
                              [/#if]
                              [#if regionLists?has_content]
                              <optgroup label="[@s.text name="projectLocations.unStandard" /]">
                                [#list regionLists as region]
                                <option value="${(region.id)!}-${(region.locElementType.scope?c)!}">${(region.name)!}</option>
                                [/#list]
                              </optgroup>
                              [/#if]
                            </select>
                          [/#if] 
                        </div>
                      </div>
                    </div>
                  </div>
                  
                <div class="form-group col-md-12 ">
                  <hr />
                </div>
                [#-- NOTE --]
                <div class="col-md-12">
                  <div class="note left">
                    <div id="popup" class="helpMessage3"></div>
                    <p><b>NOTE: </b><small>[@s.text name="projectLocations.note" /] </small></p>
                  </div>
                </div>
                [#-- RECOMMENDED LOCATIONS --]
                <div class="col-md-12">
                  <h5 class="sectionSubTitle">Suggested Locations:</h5>
                  <label for="">[@s.text name="projectLocations.locationsBelow" /]:</label>
                  <div class="simpleBox col-md-12">
                    <div class="row recommendedList">
                      [#-- RECOMMENDED REGIONS LIST --]
                      [#if project.regionFS?has_content]
                      <div class="regionsContent" style="display:${(project.projectInfo.locationRegional?string("block","none"))!"none"};">
                        [#list project.regionFS as location]
                          [@recommendedLocation element=location name="project.regionFS" index=location_index template=false /]
                        [/#list]
                      </div>
                      [#else]
                        [#assign recommendedRegions=0]
                      [/#if]
                      [#-- RECOMMENDED COUNTRIES LIST --]
                      [#if project.countryFS?has_content]
                        [#list project.countryFS as location]
                          [@recommendedLocation element=location name="project.countryFS" index=location_index template=false /]
                        [/#list]
                      [#else]
                        [#assign recommendedCountries=0]
                      [/#if]
                      [#if recommendedCountries?? && recommendedCountries==0 && recommendedRegions?? && recommendedRegions==0]
                        <p class="text-center inf">[@s.text name="projectLocations.noLocations" /]</p>
                      [/#if]
                    </div>
                  </div>
                </div>
              
                [#-- OTHER LOCATIONS --]   
                [#if action.hasSpecificities('crp_other_locations')]
                  <div class="col-md-12">
                    [#if editable && action.hasSpecificities('crp_other_locations')]
                    <span class="pull-right glyphicon glyphicon-plus addLoc-locLevel loc-button" data-toggle="modal" data-target=".addLocationModal"><b> [@s.text name="Add new location" /]</b></span>
                    [/#if]
                    <h5 id="locations-list-title" class="sectionSubTitle">Locations list</h5>
                  </div>
                  
                  [#-- LOCATION LIST --]
                  <div class="col-md-12">
                    [#-- Add new location (Modal) --]
                    <div class="modal fade addLocationModal" tabindex="-1" role="dialog" aria-labelledby="addNewLocation" aria-hidden="true">
                      <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                          [#-- <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> --]
                          
                          <div class="locationForm-container">
                            <div class="title"></div>
                            <hr />
                              <div class="form-group col-md-7">
                              <label for="locLevelSelect" style="display:block;">Select a location level:</label>
                              <select name="" id="locLevelSelect"  class="selectLocationLevel select " >
                                <option value="-1" >Select an option...</option>
                                [#list locationsLevels as locLevels]
                                  [#list locLevels.locations as locations]
                                    <option value="${locations.id}-${locations.list?string}-${locations.name}" >${locations.name}</option>
                                  [/#list]
                                [/#list]
                              </select>
                              [#-- Select location(s) Form --]
                              <div class="selectLocations" style="display:none;">
                                <label for="">Select location(s)</label>
                                <select name="" data-placeholder="Click here to drop down the options" id="countriesCmvs" multiple="true"></select>
                              </div>
                              [#-- Location name -Lat -Lon --]
                              <div id="inputFormWrapper" style="display:none;">
                                <div class="nameWrapper"><label for="">Location name:</label><input placeholder="name (Required)" class="name form-control" type="text" /></div>
                                <div class="latitudeWrapper"><label for="">Latitude:</label><input placeholder="Latitude" class="latitude form-control" type="text" value="" /></div>
                                <div class="longitudeWrapper"><label for="">Longitude:</label><input placeholder="Longitude" class="longitude form-control " type="text"  value=""/></div>
                              </div>
                            </div>
                          </div>
                          
                          <div class="map-container col-md-5">
                            <div  class="col-md-12 map">
                              <div id="map" class="col-md-12"></div>
                            </div>
                          </div>
                          
                        </div>
                      </div>
                    </div>
                    <div id="selectsContent" class="col-md-12 " listname="project.locationsData">
                      <div class="row">
                        [#if project.locationsData?has_content]
                          [#list project.locationsData as locationLevels]
                            [@locationLevelTable element=locationLevels name="${locationLevelName}" index=locationLevels_index list=locationLevels.list?? && locationLevels.list /]
                          [/#list]
                        [#else]
                          <p class="text-center borderBox inf">No locations has been added, please use the map to add new locations.</p>
                        [/#if]
                      </div>
                    </div>
                  </div>
                [/#if]
              </div>
              </div>
          </div> 
          
          [#include "/WEB-INF/crp/views/projects/buttons-projects.ftl" /]
         
          [/@s.form] 
      </div>
    </div>  
</section>
[/#if]

[#-- Section hidden inputs--]

[@locationMacro element={} name="${locationLevelName}[-1].${locationName}" index=-1 template=true /]

[@recommendedLocation element={} name="${locationLevelName}.${locationName}" index=-1 template=true /]

<input type="hidden" id="locationLevelName" value="${locationLevelName}" />
<input type="hidden" id="locationName" value="${locationName}" />

[#-- Region element template --]
<ul style="display:none">
  <li id="regionTemplate" class="region clearfix col-md-3">
      <div class="removeRegion removeIcon" title="Remove region"></div>
      <input class="id" type="hidden" name="project.projectRegions[-1].id" value="" />
      <input class="rId" type="hidden" name="project.projectRegions[-1].locElement.id" value="" />
      <input class="regionScope" type="hidden" name="project.projectRegions[-1].scope" value="" />
      <span class="name"></span>
      <div class="clearfix"></div>
    </li>
</ul>

[#include "/WEB-INF/crp/pages/footer.ftl"]

[#macro locationLevelTable element  name index template=false list=false]
  [#local customName = "${name}[${index}]" /]
  [#local countryQuestion = '[@s.text name="projectLocations.selectAllCountries" /]'/]
  [#local cmvsQuestion = '[@s.text name="projectLocations.selectAllCmvs" /]'/]
  
  <table>
    <tr>
      <th width="20%">${(element.name)!}:</th>
      <td width="80%">
        <div class=" locationLevel-optionContent " listname="${customName}.locElements">
          <span class="allCountriesQuestion" style="display:none">
            <span class="question">[@s.text name="projectLocations.selectAllCmvs" /]</span>
            [@customForm.yesNoInput name="${customName}.allCountries"  editable=editable inverse=false  cssClass="allCountries text-center" /]
            <hr />
          </span>
          [#-- Content of locations--]
          <div class="optionSelect-content row">
            [#if element.locElements?has_content]
              [#list element.locElements as location]
                [@locationMacro element=location name="${customName}.${locationName}" index=location_index isList=list template=element.allCountries /]
              [/#list]
            [/#if]
          </div>
        </div>
      </td>
    </tr>
  </table>
  <input class="locationLevelId" type="hidden" name="${locationLevelName}[${index}].id" value="${(element.id)!}"/>
  <input class="locationLevelName" type="hidden" name="${locationLevelName}[${index}].name" value="${(element.name)!}"/>
  <input type="hidden" class="isList" name="${customName}.isList"  value="${(list)?string}"/>
[/#macro]

[#macro locationMacro element  name index template=false isList=false ]
  [#local customName = "${name}[${index}]" /]
  [#assign countID = countID+1/]
  [#-- Content collapsible--]
  <div id="location-${template?string('template',countID)}" class="col-md-4 locElement" style="display:${template?string('none','block')}">
    <div class="locations col-md-12">
      <div class="locationName">
        <span class="lName">${(element.name)!}</span> 
        [#if element.locGeoposition?? && element.locGeoposition.latitude?? && element.locGeoposition.longitude?? && element.locGeoposition.latitude!=0 && element.locGeoposition.longitude!=0]
          <br />
          <span class="lPos">[#if isList!=true](${(element.locGeoposition.latitude)!}, ${(element.locGeoposition.longitude)!})[/#if]</span> 
        [/#if] 
      </div>
      [#if editable]
      <div class="removeLocation removeIcon" title="Remove Location"></div>
      [/#if]
    </div>
    [#-- Hidden inputs --]
    <input type="hidden" class="locElementId" name="${customName}.id" value="${(element.id)!}"/>
    <input type="hidden" class="locElementName" name="${customName}.name" value="${(element.name)!}" />
    <input type="hidden" class="locElementCountry" name="${customName}.locElement.isoAlpha2" value="${(element.isoAlpha2)!}" />
    <input type="hidden" class="geoId" name="${customName}.locGeoposition.id"  value="${(element.locGeoposition.id)!}" />
    
    <input type="hidden" class="geoLatitude" name="${customName}.locGeoposition.latitude"  value="${(element.locGeoposition?? && element.locGeoposition.latitude?? && element.locGeoposition.latitude!=0)?string((element.locGeoposition.latitude?c)!,'')}" /> 
    <input type="hidden" class="geoLongitude" name="${customName}.locGeoposition.longitude"  value="${(element.locGeoposition?? && element.locGeoposition.longitude?? && element.locGeoposition.longitude!=0)?string((element.locGeoposition.longitude?c)!,'')}" />
  </div>
[/#macro]

[#macro recommendedLocation element  name index template=false ]
  [#local customName = "${name}[${index}]" /]
  [#-- Content collapsible--]
  <div id="recommendedLocation-${template?string('template',index)}" class="col-md-4 recommended locElement [#if !editable]${((element.selected)?string('', 'hidden'))!}[/#if]" style="display:${template?string('none','block')}">
    <div class="locations col-md-12">
      [#-- Location Name --]
      <div class="recommendedLocName pull-left">
        [#if element.locElement??]
          <input type="hidden" class="elementID" name="${customName}.locElement.id" value="${(element.locElement.id)!}"/>
          <input type="hidden" class="locScope" name="${customName}.scope" value="${(element.locElement.locElementType.scope?c)!}"/>
          <span class="lName">
          [#assign miniName= (element.locElement.name)!""]
          <b title="${(element.locElement.name)!}">[#if miniName?length < 23]${miniName}[#else]${miniName?substring(0,22)} ...[/#if]</b>
          </span> 
        [#else]
          <input type="hidden" class="elementID" name="${customName}.locElementType.id" value="${(element.locElementType.id)!}"/>
          <input type="hidden" class="locScope" name="${customName}.scope" value="${(element.locElementType.scope?c)!}"/>
          <span class="lName">
          [#assign miniName= (element.locElementType.name)!""]
          <b title="${(element.locElementType.name)!}">[#if miniName?length < 23]${miniName}[#else]${miniName?substring(0,22)}...[/#if]</b>
          </span> 
        [/#if]
      </div>  
       
      [#-- Check Icon --]
      [#if element.locElement??]
        [#if editable]
          <input type="checkbox" class="recommendedSelected pull-right" name="" [#if element.selected]checked[/#if]/>
        [#else]
          [#if element.selected]<span class="glyphicon glyphicon-ok text-success pull-right"></span>[/#if]
        [/#if]
        <input type="hidden" name="${customName}.selected" value="${element.selected?string}" />
        [#if element.locElement.locElementType.id==2 ]
          <span class="hidden isoAlpha">${(element.locElement.isoAlpha2)!}</span>
        [/#if]
      [/#if]
      [#if element.locElementType??]
        [#if editable]
        <input type="checkbox" class="recommendedSelected pull-right" name="" [#if element.selected]checked[/#if]/>
        [#else]
          [#if element.selected]<span class="glyphicon glyphicon-ok text-success pull-right"></span>[/#if]
        [/#if]
        <input type="hidden" name="${customName}.selected" value="${element.selected?string}" />
      [/#if]
    </div>
    
    <div class="col-md-12 fundingContent">
      [#if element.fundingSources?has_content]
        [#list element.fundingSources as fs]
          [#if action.hasSpecificities('crp_fs_w1w2_cofinancing')] ${(fs.w1w2?string('<small class="text-primary">(Co-Financing)</small>',''))!} [/#if]
          <span style="font-size:0.7em;">${fs.composedName}</span><br />
        [/#list]
      [/#if]
    </div>
     
  </div>
[/#macro]
