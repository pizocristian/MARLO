[#ftl]
[#assign title = "Project Other contributions" /]
[#assign currentSectionString = "project-${actionName?replace('/','-')}-${projectID}" /]
[#assign pageLibs = ["select2"] /]
[#assign customJS = ["${baseUrl}/js/projects/projectOtherContributions.js", "${baseUrl}/js/global/autoSave.js","${baseUrl}/js/global/fieldsValidation.js"] /]
[#assign customCSS = ["${baseUrl}/css/projects/projectOtherContributions.css"] /]
[#assign currentSection = "projects" /]
[#assign currentStage = "otherContributions" /]

[#assign breadCrumb = [
  {"label":"projectsList", "nameSpace":"/projects", "action":"${(crpSession)!}/projectsList"},
  {"label":"projectOtherContributions", "nameSpace":"/projects", "action":""}
] /]


[#include "/WEB-INF/global/pages/header.ftl" /]
[#include "/WEB-INF/global/pages/main-menu.ftl" /]

<div class="container">
  <div class="helpMessage"><img src="${baseUrl}/images/global/icon-help.png" /><p> [@s.text name="projectOtherContributions.help" /] </p></div> 
</div>
    
<section class="container">
    <div class="row">
      [#-- Project Menu --]
      <div class="col-md-3">
        [#include "/WEB-INF/views/projects/menu-projects.ftl" /]
      </div>
      [#-- Project Section Content --]
      <div class="col-md-9">
        [#-- Section Messages --]
        [#include "/WEB-INF/views/projects/messages-projects.ftl" /]
        
        [@s.form action=actionName method="POST" enctype="multipart/form-data" cssClass=""]
          
          <h3 class="headTitle">[@s.text name="projectOtherContributions.title" /]</h3>  
          <div id="otherContributions" class="borderBox"> 
          
            [#-- How are contributing to other CCAFS IP --]
            <div class="fullBlock">
              [@customForm.textArea name="projectOtherContributions.contribution" className="contribution limitWords-100" i18nkey="projectOtherContributions.contribution" editable=editable  /]  
            </div>
          
            [#-- -- -- REPORTING BLOCK -- -- --]
            [#-- Others impact pathways contributions --]
            [#if reportingActive]
              <div id="otherContributionsBlock">
                [#if project.otherContributions?has_content]
                  [#list project.otherContributions as element]
                    [@otherContribution element=element index=element_index /] 
                  [/#list]
                [#else]
                  <div class="emptyMessage simpleBox center"><p>There is not other contributions added</p></div>
                [/#if]
              </div>
              [#if editable]<div id="addOtherContribution"><a href="" class="addLink">[@s.text name="projectOtherContributions.addOtherContribution"/]</a></div>[/#if]
              <div class="clearfix"></div>
              <br />
            [/#if]
          
            [#-- Collaborating with other CRPs --]
            [#assign crpsName= "project.ipOtherContribution.crps"/]
            <div class="fullPartBlock">      
              <div class="crpContribution panel tertiary">
                <div class="panel-head">[@customForm.text name="projectOtherContributions.collaboratingCRPs" readText=!editable /]</div> 
                <div class="panel-body"> 
                  <ul id="contributionsBlock" class="list">
                  [#if project.ipOtherContribution?? && project.ipOtherContribution.crpContributions?has_content]  
                    [#list project.ipOtherContribution.crpContributions as crp]
                       
                    [/#list] 
                  [#else]
                    <p class="emptyText"> [@s.text name="projectOtherContributions.crpsEmpty" /] </p>  
                  [/#if]  
                  </ul>
                  [#if editable]
                    [@customForm.select name="" label="" disabled=!canEdit i18nkey="" listName="crps" keyFieldName="id"  displayFieldName="name" className="crpsSelect" value="" /]
                  [/#if] 
                </div>
              </div> 
            </div>
          
          </div> <!-- End otherContributions --> 
          
          [#-- Section Buttons & hidden inputs--]
          [#include "/WEB-INF/views/projects/buttons-projects.ftl" /]
          
        
        [/@s.form] 
      </div>
    </div>  
</section>

[#-- CRP Contribution template --]
[@crpContribution element={} name="" index=-1 isTemplate=true /]

[#-- Other contribution template --]
[@otherContribution element={} template=true /]
        
[#include "/WEB-INF/global/pages/footer.ftl"]



[#macro otherContribution element index="0" template=false]
  [#assign customName = "project.otherContributions[${template?string('-1',index)}]" /]
  [#assign contribution = element /]
  <div id="otherContribution-${template?string('template',index)}" class="otherContribution simpleBox" style="display:${template?string('none','block')}">
    <div class="loading" style="display:none"></div>
    [#-- Edit/Back/remove buttons --]
    [#if (editable && canEdit)]<div class="removeElement" title="[@s.text name="projectOtherContributions.removeOtherContribution" /]"></div>[/#if]
    [#-- Other Contribution ID --]
    <input type="hidden" name="${customName}.id" class="otherContributionId" value="${(contribution.id)!-1}"/>
    <div class="fullBlock">
      [#-- Region --]
      <div class="halfPartBlock">
        [@customForm.select name="${customName}.region" className="otherContributionRegion" label="" i18nkey="projectOtherContributions.region" listName="regions"  required=true editable=editable  /]
      </div> 
    </div>
    [#-- Indicator --]
    <div class="fullBlock">
      [@customForm.select name="${customName}.indicators" className="otherContributionIndicator" label="" i18nkey="projectOtherContributions.indicators" listName="otherIndicators" required=true editable=editable  /]
    </div>
    [#-- Describe how you are contributing to the selected outcome --]
    <div class="fullBlock">
      <label>[@customForm.text name="projectOtherContributions.description" param="${currentCycleYear}" readText=!editable /]:[@customForm.req required=editable  /]</label>
      [@customForm.textArea name="${customName}.description" className="otherContributionDescription limitWords-100"  i18nkey="" showTitle=false required=true editable=editable  /]
    </div>
    [#-- Target contribution --]
    <div class="fullBlock">
      <label>[@customForm.text name="projectOtherContributions.target" readText=!editable /]:</label>
      <div class="halfPartBlock">
        [@customForm.input name="${customName}.target" className="otherContributionTarget" i18nkey="" showTitle=false editable=editable  /]
      </div>
    </div>
  </div> 
[/#macro]



[#macro crpContribution element name index isTemplate=false]
<li id="crpOtherContribution-${isTemplate?string('template', index)}" class="crpOtherContribution clearfix" style="display:${isTemplate?string('none','block')}">
  [#local customName = "${name}[${index}]" /]
  <input class="id" type="hidden" name="${customName}.crp.id" value="${(crp.crp.id)!}" />
  [#-- Remove --]
  [#if editable]<span class="listButton remove">[@s.text name="form.buttons.remove" /]</span>[/#if]
  
  [#-- CRP Title --]
  <div class="fullPartBlock clearfix"><span class="name crpName">${(element.crp.name)!}</span></div>
  
  [#-- CRP Outcome Collaboration --]
  <div class="contributionsBlock">
    <div class="crpOutcomeContribution">
      <div class="form-group">
        [@customForm.textArea name="${customName}.natureCollaboration" className="crpCollaborationNature limitWords-50" i18nkey="projectOtherContributions.collaborationNature" required=true editable=editable /]  
      </div>
    </div>
  </div>
  [#if editable] 
  <div class="text-right">
    <div class="button-blue"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>Add Outcome Contribution</div>
  </div>
  [/#if]
  <br />
  
  
</li>
[/#macro]