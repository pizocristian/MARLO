[#ftl]
[#assign title = "Strategic Objectives" /]
[#assign currentSectionString = "${actionName?replace('/','-')}-phase-${(actualPhase.id)!}" /]
[#assign customJS = ["${baseUrlCdn}/global/js/usersManagement.js"] /]
[#assign currentSection = "admin" /]
[#assign currentStage = "strategicObjectives" /]

[#assign breadCrumb = [
  {"label":"superadmin.admin", "nameSpace":"/admin", "action":"objectives"},
  {"label":"superadmin.strategicObjectives", "nameSpace":"", "action":""}
]/]

[#include "/WEB-INF/global/pages/header.ftl" /]
[#include "/WEB-INF/global/pages/main-menu.ftl" /]

<section class="marlo-content">
  <div class="container"> 
    <div class="row">
      <div class="col-md-3">
        [#include "/WEB-INF/center/views/admin/menu-admin.ftl" /]
      </div>
      <div class="col-md-9">
        [@s.form action=actionName enctype="multipart/form-data" ]  
        
        <h4 class="sectionTitle form-group">[@s.text name="strategicObjectives.title" /]</h4>
        
        <div class="users-block">
        
          <div class="items-list simpleBox" listname="objectives">
            [#if objectives?has_content]
              [#list objectives as item]
                [@strategicObjectives element=item name="objectives" index=item_index /]
              [/#list]
            [/#if] 
            <p class="emptyMessage text-center usersMessage" style="display:${(objectives?has_content)?string('none','block')}">No Strategic Objectives added yet.</p>
          </div>
        </div>
        [#-- Add Milestone Button --]
          <div class="text-right">
            <div class=" button-blue"><span class="fas fa-plus-circle" aria-hidden="true"></span> [@s.text name="Add objective"/]</div>
          </div>
      
      [#-- Section Buttons--]
        <div class="buttons">
          <div class="buttons-content">
          [#if editable]
            <a href="[@s.url][#include "/WEB-INF/global/pages/urlGlobalParams.ftl" /][/@s.url]" class="form-button button-edit"><span class="far fa-eye" aria-hidden="true"></span> [@s.text name="form.buttons.back" /]</a>
            [@s.submit type="button" name="save" cssClass="button-save"]<span class="fas fa-save" aria-hidden="true"></span> [@s.text name="form.buttons.save" /][/@s.submit]
          [#else]
            [#if canEdit]
              <a href="[@s.url][#include "/WEB-INF/global/pages/urlGlobalParams.ftl" /][/@s.url]" class="form-button button-edit"><span class="fas fa-edit" aria-hidden="true"></span> [@s.text name="form.buttons.edit" /]</a>
            [/#if]
          [/#if]
          </div>
        </div>
                
        [/@s.form]
      </div>
    </div>
  </div>
</section>


[@strategicObjectives element={} name="coordinators" index=-1 template=true/]

[#include "/WEB-INF/global/pages/footer.ftl" /]

[#macro strategicObjectives element name index template=false]
  [#local customName = "${name}[${index}]" /]
  <div id="objective-${template?string('template',index)}" class="objective simpleBox" style="display:${template?string('none','block')}">
    <label for="">Strategic objective #${index+1}</label>
    [@customForm.textArea name="${customName}.description" showTitle=false   required=false className="outcome-statement limitWords-100" editable=true /]
    <div class="clearfix"></div>
    <input class="id" type="hidden" name="${customName}.id" value="${(element.id)!}"/>
    [#-- Remove Button --]
    [#if editable]
      <span class="fas fa-times float-right remove-userItem" aria-hidden="true"></span>
    [/#if]
  </div>
[/#macro]
