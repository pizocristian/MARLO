[#ftl]
[#assign title = "Project Outcome Case Studies" /]
[#assign currentSectionString = "project-${actionName?replace('/','-')}-${expectedID}-phase-${(actualPhase.id)!}" /]
[#assign pageLibs = [ "select2", "blueimp-file-upload" ] /]
[#assign customJS = [
  "${baseUrlMedia}/js/projects/projectStudy.js",
  "${baseUrl}/global/js/autoSave.js",
  "${baseUrl}/global/js/fieldsValidation.js"
  ] 
/]
[#assign customCSS = [
  "${baseUrlMedia}/css/projects/projectStudies.css"
  ] 
/]

[#assign currentSection = "projects" /]
[#assign currentStage = "caseStudies" /]

[#assign breadCrumb = [
  {"label":"projectsList", "nameSpace":"/projects", "action":"${(crpSession)!}/projectsList"},
  {"label":"caseStudies", "nameSpace":"/projects", "action":"${(crpSession)!}/caseStudies", "param": "projectID=${projectID}"},
  {"label":"caseStudy", "nameSpace":"/projects", "action":""}
] /]

[#assign params = {
  "caseStudies": {"id":"caseStudiesName", "name":"project.caseStudies"}
  }
/] 

[#include "/WEB-INF/global/pages/header.ftl" /]
[#include "/WEB-INF/global/pages/main-menu.ftl" /]
[#import "/WEB-INF/global/macros/studiesTemplates.ftl" as studies /]


<div class="container helpText viewMore-block">
  <div class="helpMessage infoText">
    <img class="col-md-2" src="${baseUrl}/global/images/icon-help.jpg" />
    <p class="col-md-10"> [@s.text name="projectContributionsCrpList.help" /] </p>
  </div> 
  <div style="display:none" class="viewMore closed"></div>
</div>

<section class="container">
    <div class="row">
      [#-- Project Menu --]
      <div class="col-md-3">
        [#include "/WEB-INF/crp/views/projects/menu-projects.ftl" /]
      </div>
      [#-- Project Section Content --]
      <div class="col-md-9">
        [#-- Section Messages --]
        [#include "/WEB-INF/crp/views/projects/messages-caseStudy.ftl" /]
        
        [@s.form action=actionName cssClass="pure-form" enctype="multipart/form-data" ]  

          [#-- Back --]
          <small class="pull-right">
            <a href="[@s.url action='${crpSession}/studies'][@s.param name="projectID" value=project.id /][#include "/WEB-INF/global/pages/urlGlobalParams.ftl" /][/@s.url]">
              <span class="glyphicon glyphicon-circle-arrow-left"></span> [@s.text name="projectStudies.backProjectStudies" /]
            </a>
          </small>
          
          [#-- Outcome case studies list --]
          <h3 class="headTitle">[@s.text name="projectStudies.caseStudyInformation" /]</h3>
          <div id="caseStudiesBlock" class="">
            [@studies.studyMacro element=(expectedStudy)!{} name="caseStudy" index=0 isOutcomeCaseStudy=true /]
          </div> 
          
          [#-- Section Buttons & hidden inputs--]
          [#include "/WEB-INF/crp/views/projects/buttons-projectOutcomesCaseStudies.ftl" /]
         
        [/@s.form]
  
      </div>
    </div>  
</section>

[#-- Internal parameters --]
[#list params?keys as prop]<input id="${params[prop].id}" type="hidden" value="${params[prop].name}" />[/#list]

[#-- Element Macro Template --]
<ul style="display:none">
  [@customForm.listElementMacro name="caseStudy.subIDOs" element={} type="subIDO" index=-1 template=true /]
  [@customForm.listElementMacro name="caseStudy.srfTargets" element={} type="srfTarget" index=-1 template=true /]
  [@customForm.listElementMacro name="caseStudy.crps" element={} type="globalUnit" index=-1 template=true /]
  [@customForm.listElementMacro name="caseStudy.flagships" element={} type="crpProgram" index=-1 template=true /]
  [@customForm.listElementMacro name="caseStudy.externalPartners" element={} type="institution" index=-1 template=true /]
</ul>

[@studies.shareStudyMacro element={} name="caseStudy.projects" index=-1 template=true /]

[#include "/WEB-INF/global/pages/footer.ftl"]