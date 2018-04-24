[#ftl]
[#assign title = "Project Innovations" /]
[#assign currentSectionString = "project-${actionName?replace('/','-')}-${projectID}-phase-${(actualPhase.id)!}" /]
[#-- TODO: Remove unused pageLibs--]
[#assign pageLibs = ["select2","font-awesome", "flat-flags"] /]
[#assign customJS = [
  "${baseUrlMedia}/js/projects/projectInnovations.js",
  "${baseUrl}/global/js/autoSave.js",
  "${baseUrl}/global/js/fieldsValidation.js"
] /]
[#assign customCSS = ["${baseUrlMedia}/css/projects/projectInnovations.css"] /]
[#assign currentSection = "projects" /]
[#assign currentStage = "innovations" /]

[#assign breadCrumb = [
  {"label":"projectsList", "nameSpace":"/projects", "action":"${(crpSession)!}/projectsList"},
  {"text":"P${project.id}", "nameSpace":"/projects", "action":"${crpSession}/description", "param": "projectID=${project.id?c}&edit=true&phaseID=${(actualPhase.id)!}"},
  {"label":"innovationsList", "nameSpace":"/projects", "action":"${(crpSession)!}/innovations" ,"param":"projectID=${projectID}"},
  {"label":"innovationInformation", "nameSpace":"/projects", "action":""}
]/]

[#import "/WEB-INF/global/macros/utils.ftl" as utils /]
[#include "/WEB-INF/global/pages/header.ftl" /]
[#include "/WEB-INF/global/pages/main-menu.ftl" /]

<div class="container helpText viewMore-block">
  <div class="helpMessage infoText">
    <img class="col-md-2" src="${baseUrl}/global/images/icon-help.jpg" />
    <p class="col-md-10"> [@s.text name="projectHighlight.help" /] </p>
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
    [#include "/WEB-INF/crp/views/projects/messages-highlight.ftl" /]

      [@s.form action=actionName cssClass="pure-form" method="POST" enctype="multipart/form-data" ]
      
        [#-- Back --]
        <small class="pull-right">
          <a href="[@s.url action='${crpSession}/innovations'][@s.param name="projectID" value=project.id /][#include "/WEB-INF/global/pages/urlGlobalParams.ftl" /][/@s.url]">
            <span class="glyphicon glyphicon-circle-arrow-left"></span> [@s.text name="projectInnovations.back" /]
          </a>
        </small>
        
        [#--  Innovation Title --]
        <h3 class="headTitle">[@s.text name="projectInnovations" /]</h3> 
        <div id="innovations" class="borderBox clearfix">

        <div class="">
          [#-- Title --] 
          <div class="form-group">
            [@customForm.input name="innovation.title" type="text" i18nkey="projectInnovations.title"  placeholder="" className="limitWords-20" required=true editable=editable /]
          </div>
        
          [#-- Narrative --] 
          <div class="form-group">
            [@customForm.textArea name="innovation.narrative"  i18nkey="projectInnovations.narrative"  placeholder="" className="limitWords-50" required=true editable=editable /]
          </div>
        
          [#-- Phase of research and Stage of innovation --] 
          <div class="form-group row">
            <div class="col-md-6 ">
              [@customForm.select name="innovation.repIndPhaseResearchPartnership.id" label=""  i18nkey="projectInnovations.phase" listName="phaseResearchList" keyFieldName="id"  displayFieldName="name"   required=true  className="" editable=editable/]
            </div>
            <div class="col-md-6 ">
              [@customForm.select name="innovation.repIndStageInnovation.id" label=""  i18nkey="projectInnovations.stage" listName="stageInnovationList" keyFieldName="id"  displayFieldName="name"   required=true  className="stageInnovationSelect" editable=editable/]
              [#assign isStageFour = (innovation.projectInnovationInfo.repIndStageInnovation.id == 4)!false]
            </div>
          </div>
        
          [#-- Geographic scope and innovation type --] 
          <div class="form-group row">
            <div class="col-md-6 ">
              [@customForm.select name="innovation.repIndGeographicScope.id" label=""  i18nkey="projectInnovations.geographicScope" listName="geographicScopeList" keyFieldName="id"  displayFieldName="name" required=true  className="" editable=editable/]
            </div>
            <div class="col-md-6 ">
              [@customForm.select name="innovation.repIndInnovationType.id" label=""  i18nkey="projectInnovations.innovationType" listName="innovationTypeList" keyFieldName="id"  displayFieldName="name" required=true  className="" editable=editable/]
            </div>
          </div>
        
          [#-- Region (if scope is Region) --] 
          <div class="form-group row">
            <div class="col-md-6 ">
              [@customForm.select name="innovation.repIndRegion.id" label=""  i18nkey="projectInnovations.region" listName="regionList" keyFieldName="id"  displayFieldName="name" required=true  className="" editable=editable/]
            </div>
            <div class="col-md-6 ">
            </div>
          </div>
        
          [#-- Country(ies) (if scope is Multi-national, National or Sub-National)  --]
          <div class="form-group countriesBlock chosen" >
            [#if editable]
              [@customForm.select name="innovation.countriesIds" label="" i18nkey="projectInnovations.countries" listName="countries" keyFieldName="isoAlpha2"  displayFieldName="name" value="innovation.countriesIds" className="countriesIds" multiple=true  /]
            [#else]
              <label>[@s.text name="projectInnovations.countries" /]:</label>
              <div class="select">
              [#if innovation.countries?has_content]
                [#list innovation.countries as element]<p class="checked">${(element.locElement.name)!}</p>[/#list]
              [#else]
                <p>[@s.text name="projectInnovations.countries" /]</p>
              [/#if]
              </div>
            [/#if]
          </div>
          
          [#-- Specify next user organizational type (Only if stage 4) --]
          <div class="form-group stageFourBlock" style="display:${isStageFour?string('block','none')}">
            [@elementsListComponent name="innovation.organizationTypes" elementType="organizationType" elementList=keyContributions label="projectInnovations.nextUserOrganizationalType"  listName="organizationTypeList" keyFieldName="id" displayFieldName="composedName"/]
          </div>
        
          [#-- Specify an Outcome Case Study (Only if stage 4) --]
          <div class="form-group stageFourBlock" style="display:${isStageFour?string('block','none')}">
            [@customForm.select name="innovation.projectExpectedStudy.id" label=""  i18nkey="projectInnovations.outcomeCaseStudy" listName="expectedStudyList" keyFieldName="id"  displayFieldName="composedName"  multiple=false required=true  className="keyOutput" editable=editable/]
          </div>
        
          [#-- Novel or adaptative research --] 
          <div class="form-group">
            [@customForm.textArea name="innovation.novel" i18nkey="projectInnovations.novelOrAdaptative"  placeholder="" className="limitWords-100" required=true editable=editable /]
          </div>
        
          [#-- Evidence Link --] 
          <div class="form-group">
            [@customForm.input name="innovation.evidenceLink"  type="text" i18nkey="projectInnovations.evidenceLink"  placeholder="marloRequestCreation.webSiteLink.placeholder" className="" required=false editable=editable /]
          </div>
        
          [#-- Or Deliverable ID (optional) --]
          <div class="form-group">
            [@elementsListComponent name="innovation.deliverables" elementType="deliverable" elementList=keyContributions label="projectInnovations.deliverableId"  listName="deliverableList " keyFieldName="id" displayFieldName="composedName"/]
          </div>
        
          [#-- Contributing CRPs/Platforms --]
          <div class="form-group">
            [@elementsListComponent name="innovation.crps" elementType="globalUnit" elementList=keyContributions label="projectInnovations.contributing"  listName="crpList" keyFieldName="id" displayFieldName="composedName"/]
          </div>
          
          [#-- Gender Relevance --]
          <div class="form-group">
            <label for="">[@s.text name="projectInnovations.genderRelevance" /]:[@customForm.req required=editable /]</label>
            <div class="simpleBox">
              <div class="form-group">
                [#list focusLevelList as level]
                  <p>[@customForm.radioFlat id="genderLevel-${level.id}" name="innovation.genderFocusLevel" label="${level.name}" value="${level.id}" checked=false cssClass="" cssClassLabel=""/]</p> 
                [/#list]
              </div>
              [#-- Brief explanation and evidence --] 
              <div class="form-group">
                [@customForm.textArea name="innovation.genderExplaniation" i18nkey="projectInnovations.genderRelevance.explanation"  placeholder="" className="" required=true editable=editable /]
              </div>
            </div>
          </div>
          
          [#-- Youth Relevance --]
          <div class="form-group">
            <label for="">[@s.text name="projectInnovations.youthRelevance" /]:[@customForm.req required=editable /]</label>
            <div class="simpleBox">
              <div class="form-group">
                [#list focusLevelList as level]
                  <p>[@customForm.radioFlat id="youthLevel-${level.id}" name="innovation.youthFocusLevel" label="${level.name}" value="${level.id}" checked=false cssClass="" cssClassLabel=""/]</p> 
                [/#list]
              </div>
              [#-- Brief explanation and evidence --] 
              <div class="form-group">
                [@customForm.textArea name="innovation.youthExplaniation" i18nkey="projectInnovations.youthRelevance.explanation" placeholder="" className="" required=true editable=editable /]
              </div>
            </div>
          </div>
          
        </div>
      [/@s.form] 
    </div>
  </div>  
</section>
[/#if]

[#-- Element Macro Template --]
<ul style="display:none">
  [@listElementMacro name="innovation.organizationTypes" element={} type="organizationType" index=-1 template=true /]
  [@listElementMacro name="innovation.crps" element={} type="globalUnit" index=-1 template=true /]
  [@listElementMacro name="innovation.deliverables" element={} type="deliverable" index=-1 template=true /]
</ul>

[#include "/WEB-INF/global/pages/footer.ftl"]


[#-- MACROS --]

[#macro elementsListComponent name elementType elementList=[] label="" listName="" keyFieldName="" displayFieldName="" ]
  <div class="panel tertiary">
    <div class="panel-head"><label for="">[@s.text name=label /]</label></div>
    <div class="panel-body" style="min-height: 30px;">
      <ul class="list listType-${elementType}">
        [#if elementList?has_content]
          [#list elementList as item][@listElementMacro name=name element=item type=elementType index=item_index /][/#list]
        [/#if]
      </ul>
      [#if editable]
        [@customForm.select name="" className="setSelect2 elementType-${elementType}" showTitle=false listName=listName keyFieldName=keyFieldName  displayFieldName=displayFieldName /]
      [/#if]
    </div>
  </div>
[/#macro]

[#macro listElementMacro element name type index=-1 template=false]
  [#local customName = "${name}[${index}]"]
  <li id="relationElement-${type}-${template?string('template', index)}" class="relationElement">
    [#-- Hidden Inputs --]
    <input type="hidden" class="elementID" name="${customName}.id" value="${(element.id)!}" />
    <input type="hidden" class="elementRelationID" name="${customName}.${type}.id" value="${(element.relationship.id)!}" />
    [#-- Remove button --]
    [#if editable]<div class="removeElement sm removeIcon removeElementType-${type}" title="Remove"></div>[/#if] 
    [#-- Title --]
    <span class="elementName">${(element.name)!'{elementName}'}</span>
  </li>
[/#macro]