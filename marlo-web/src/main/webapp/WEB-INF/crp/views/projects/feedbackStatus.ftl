[#ftl]
[#assign title = "Feedback Status" /]
[#assign currentSectionString = "project-${actionName?replace('/','-')}-${projectID}-phase-${(actualPhase.id)!}" /]
[#assign pageLibs = ["powerbi-client"] /]
[#assign customJS = [
  "${baseUrlMedia}/js/projects/feedbackStatus.js?20220606a"
  ]
/]
[#assign customCSS = [
  "${baseUrlMedia}/css/projects/feedbackStatus.css?20220606a"
  ]
/]

[#assign currentStage = "feedback" /]

  [#assign currentSection = "clusters" /]
  [#assign breadCrumb = [
    {"label":"projectsList", "nameSpace":"${currentSection}", "action":"${(crpSession)!}/projectsList"},
    {"text":"C${project.id}", "nameSpace":"${currentSection}", "action":"${crpSession}/feedback", "param": "projectID=${project.id?c}&edit=true&phaseID=${(actualPhase.id)!}"},
    {"label":"feedbackStatus", "nameSpace":"${currentSection}", "action":""}
  ] /]


[#include "/WEB-INF/global/pages/header.ftl" /]
[#include "/WEB-INF/global/pages/main-menu.ftl" /]


[#import "/WEB-INF/global/macros/utils.ftl" as utils /]

[#attempt]
  [#assign canAccessCCAFS = false ]
  [#assign canAccessWLE = (action.crpID == 4)!false ]
[#recover]
  [#assign canAccessCCAFS = false ]
  [#assign canAccessWLE = false ]
[/#attempt]

[#assign crp = "CCAFS" /]


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

          <h3 class="headTitle">[@s.text name="projectDescription.title" /]</h3>
          <div id="projectDescription" class="borderBox">
              
             <section class="container containerBI">  
              [#if biReports?has_content]
              <div class="headContainer">
                <div class='selectedReportBIContainer col-md-2'>
                    <span class="selectedReportBI col-md button-bg" style="max-width:200px">
                        [#--  <p class="menu-item-title">[@s.text name="biDashboard.menu.title"/] </p>  --]
                        <p class="menu-item-title">Dashboards:</p>
                      [#--  <span class="glyphicon reportsButtonsIcon glyphicon-chevron-up" style="color: #1da5ce"></span>  --]
                    </span>
                    [#--  Reports Tabs --] 
                    <div id="repportsMenu" class="reportsButtons">
                      <div class="menuList col-md-12" style="padding:0">
                      [#list (biReports)?sort_by("reportOrder")![] as report]
                          <div id="BIreport-${report.id}" report-title="${report.reportTitle}"  has-filters="${report.hasFilters?c}" class="button-bg reportSection [#if report?index == 0]current[/#if]">
                          <a index="${report?index+1}" class="BIreport-${report.id}" href="">[@s.text name=report.reportName /]</a>
                          </div>
                      [/#list]
                      </div>
                    </div>
                    [#--  Reports Tabs --] 
                
                  </div>
                
                  [#--  Reports header  --]
                 <div class="headTitle-row-container">
          
                  <h3 class="headTitle text-left col-md-8">
                  </h3>
                  <span class="setFullScreen col-md-1 btn button-bg">
                      <p class="menu-item-title">Fullscreen</p>
                      <span class="glyphicon reportsButtonsIconFS glyphicon-fullscreen" style="color: #1da5ce"></span>
                  </span>
                 </div>
                  [#--  Reports header  --]
              </div>
        
                [#--  Reports Content --]
                <div class="summariesContent col-md-12" style="min-height:550px;">
                  <div class="">
                    [#list (biReports)?sort_by("reportOrder")![] as report]
                        <div id="BIreport-${report.id}-contentOptions" class="" style="display:[#if report?index !=0]none[/#if];">
                          <div id="dashboardContainer-${report.id}" class="dashboardContainer-${report.id}"></div>
                          <input type="hidden" id="reportName-${report.id}" name="reportName" value=${report.reportName} />
                          <input type="hidden" id="embeUrl-${report.id}" name="embedUrl" value=${report.embedUrl} /> 
                          <input type="hidden" id="reportID-${report.id}" name="reportId" value=${report.reportId} />
                        </div>
                    [/#list] 
                  </div>
                </div>
              [/#if]
             </section>

          </div>
        
          [/@s.form]
      </div>
    </div>
</section>
[/#if]
    

[#include "/WEB-INF/global/pages/footer.ftl"]