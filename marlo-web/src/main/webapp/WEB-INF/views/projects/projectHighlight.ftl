[#ftl]
[#assign highlight = 
              { "id": "1",
                "title": "Succesful communications on the Projected Shifts in Coffea arabica Suitability among Major Global Producing Regions Due to Climate Change",
                "author":"Ovalle-Rivera O, Läderach P, Bunn C, Obersteiner M, Schroth G",
                "year":"2015",
                "subject":"Coffea arabica, climate change, productivity",
                "":"",
                "":"",
                "":""
              }  
             /]
             

[#assign canEdit = true /]
[#assign editable = true /]
            
[#assign title = "Project Highlight" /]
[#assign currentSectionString = "project-${actionName?replace('/','-')}-${highlight.id}" /]
[#assign pageLibs = [ "select2" ] /]
[#assign customJS = ["${baseUrl}/js/projects/projectHighlight.js","${baseUrl}/js/global/fieldsValidation.js"] /]
[#assign customCSS = ["${baseUrl}/css/projects/projectHighlights.css"] /]
[#assign currentSection = "projects" /]
[#assign currentStage = "highlights" /]

[#assign breadCrumb = [
  {"label":"projectsList", "nameSpace":"/projects", "action":"${(crpSession)!}/projectsList"},
  {"label":"projectHighlights", "nameSpace":"/projects", "action":"${(crpSession)!}/highlights", "param" : "projectID=${projectID}"},
  {"label":"projectHighlights", "nameSpace":"/projects", "action":""}
] /]


[#include "/WEB-INF/global/pages/header.ftl" /]
[#include "/WEB-INF/global/pages/main-menu.ftl" /]



<div class="container helpText viewMore-block">
  <div class="helpMessage infoText">
    <img class="col-md-2" src="${baseUrl}/images/global/icon-help.jpg" />
    <p class="col-md-10"> [@s.text name="projectHighlight.help" /] </p>
  </div> 
  <div style="display:none" class="viewMore closed"></div>
</div>
    
<section class="container">
    <div class="row">
      [#-- Project Menu --]
      <div class="col-md-3">
        [#include "/WEB-INF/views/projects/menu-projects.ftl" /]
      </div>
      [#-- Project Section Content --]
      <div class="col-md-9">
  
        [@s.form action="highlight" cssClass="pure-form" method="POST" enctype="multipart/form-data" ]
         
          [#include "/WEB-INF/views/projects/dataInfo-projects.ftl" /]
          <br />
          
          [#--  Highlight Information --]
          <h3 class="headTitle">[@s.text name="projectHighlight.information" /]</h3> 
          <div id="highlight-information" class="borderBox clearfix">
            
            [#-- Title --]
            <div class="fullBlock">
              [@customForm.input name="highlight.title" type="text" i18nkey="highlight.title" editable=editable required=true  /]
            </div> 
            
            <div class="fullPartBlock">
              [#-- Author --]
              <div class="halfPartBlock" >
                [@customForm.input name="highlight.author" type="text" i18nkey="highlight.author" editable=editable  required=true  /]
              </div>
            
              [#-- Subject --]
              <div class="halfPartBlock" >
                [@customForm.input name="highlight.subject"  type="text" i18nkey="highlight.subject" help="highlight.subject.help" editable=editable /] 
              </div> 
            </div>
      
            <div class="fullPartBlock">
              [#-- Publisher --]
              <div class="halfPartBlock" >
                [@customForm.input name="highlight.publisher" type="text" i18nkey="highlight.publisher" help="highlight.publisher.help" editable=editable /]   
              </div>
              [#-- Year --]
              <div class="halfPartBlock">
                [@customForm.select name="highlight.year" value="${(highlight.year)!currentReportingYear}" i18nkey="highlight.year" listName="allYears" editable=editable stringKey=true required=true  /]
                [#if !editable]${(highlight.year)!}[/#if]
              </div>
            </div>
           
            <br />
            <div class="fullBlock">
              [#-- Types --]
              <div class="halfPartBlock highlightsTypes">
                <label for="highlight.types">[@s.text name="highlight.types" /]<span class="red">*</span></label>
                <div class="checkboxGroup">
                [#if editable]
                  [@s.fielderror cssClass="fieldError" fieldName="highlight.typesIds"/]
                  [@s.checkboxlist name="highlight.typesids" list="highlightsTypes" value="highlight.typesids" itemKey="id"  cssClass="checkbox" /]
                [#else]
                  [#if highlight.typesIds?has_content]
                    [#list highlight.typesIds as element]<p class="checked">${element.description}</p>[/#list]
                  [#else]
                    <div class="select"><p>Field is empty</p></div>
                  [/#if]
                [/#if]
                </div>
              </div>
              
              [#-- Image --]
              <div class="halfPartBlock imageBlock">
                <label for="highlight.image">[@customForm.text name="highlight.image" readText=!editable /]:</label>
                <div class="browseInput fileUpload">
                  [#if editable]
                    [#if highlight.file?has_content]
                      <p> 
                        [#if editable]<span id="remove-file" class="remove"></span>[#else]<span class="file"></span>[/#if] 
                        <a href="${(highlightsImagesUrl)!baseUrl}${(highlight.file.fileName)!'images/global/defaultImage.png'}">${(highlight.file.fileName)!}</a>  
                        <input type="hidden" name="highlight.photo" value="${highlight.file.fileName}" /> 
                      </p>
                    [#else]
                      [@customForm.inputFile name="file" /]
                    [/#if] 
                  [/#if]  
                </div>
                <div id="highlight.image" class="image">
                 [#if highlight.photo?has_content]
                   <img src="${(highlightsImagesUrl)!baseUrl}${(highlight.photo)!'images/global/defaultImage.png'}" width="100%">
                 [#else]
                   <img src="${baseUrl}/${(highlight.photo)!'images/global/defaultImage.png'}" width="100%">
                 [/#if]
                </div>
                <div class="clear"></div>
              </div>
            </div>
            <br />
            
            <div class="fullPartBlock">
              [#-- Start Date --]
              <div class="halfPartBlock">
                [@customForm.input name="highlight.startDateText" className="startDate" type="text" i18nkey="highlight.startDate" editable=editable/]
              </div>
        
              [#-- End Date --]
              <div class="halfPartBlock">
                [@customForm.input name="highlight.endDateText" className="endDate" type="text" i18nkey="highlight.endDate" editable=editable/]
              </div>
            </div>
            
            [#-- Is global --]
            <div class="fullBlock">
              <div class="halfPartBlock">
                [@customForm.checkbox  name="highlight.isGlobal" className="isGlobal" i18nkey="highlight.isGlobal" checked=(highlight.isGlobal)!false value="true" editable=editable/]
              </div>
            </div>
            
            [#-- Countries --]
            <div class="fullBlock countriesBlock chosen" style="display:${((highlight.isGlobal)!false)?string('none','block')}">
              [#if editable]
                [@customForm.select name="highlight.countriesIds" label="" i18nkey="highlight.countries" listName="countries" keyFieldName="id"  displayFieldName="name" value="highlight.countriesIds" multiple=true disabled="${(highlight.global?string(1, 0))!0}"/]              
              [#else]
                <label>[@s.text name="highlight.countries" /]:</label>
                <div class="select">
                [#if highlight.countries?has_content]
                  [#list highlight.countries as element]
                   <p class="checked">${element.name}</p>
                  [/#list]
                [#else]
                  <p>Field is empty</p>
                [/#if]
                </div>
              [/#if]
            </div>
      
            [#-- Keywords --]
            <div class="fullBlock">
              [@customForm.input name="highlight.keywords" type="text" i18nkey="highlight.keywords" help="highlight.keywords.help" editable=editable/]
            </div>
      
            [#-- Description --]
            <div class="fullBlock">
              [@customForm.textArea name="highlight.description" className="limitWords-300" i18nkey="highlight.descripition" editable=editable/]
            </div>
      
            [#-- Objectives --]
            <div class="fullBlock">
              [@customForm.textArea name="highlight.objectives" className="limitWords-100" i18nkey="highlight.objectives" editable=editable/]
            </div>
      
            [#-- Result --]
            <div class="fullBlock">
              [@customForm.textArea name="highlight.results" className="limitWords-300" i18nkey="highlight.results" editable=editable/]
            </div>
      
            [#-- Partners --]
            <div class="fullBlock">
              [@customForm.textArea name="highlight.partners" className="limitWords-300" i18nkey="highlight.partners" editable=editable/]
            </div>
      
            [#-- Links / resources --]
            <div class="fullBlock">
              [@customForm.textArea name="highlight.links" i18nkey="highlight.links" editable=editable/]
            </div>
            
          </div>
          
          
         
        [/@s.form] 
      </div>
      
      
    </div>  
</section>
  
[#-- File upload template --]
[@customForm.inputFile name="file" template=true /] 

[#include "/WEB-INF/global/pages/footer.ftl"]