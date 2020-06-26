[#ftl]
[#-- Hidden Inputs --]
<input type="hidden"  name="expectedID" value="${expectedID}"/>
<input type="hidden"  name="projectID" value="${(project.id)!}" />
<input type="hidden"  name="className" value="${(expectedStudy.class.name)!}"/>
<input type="hidden"  name="id" value="${(expectedStudy.id)!}"/>
<input type="hidden"  name="modifiedBy.id" value="${(currentUser.id)!}"/>
<input type="hidden"  name="actionName" value="${(actionName)!}"/>
<input type="hidden"  name="phaseID" value="${(actualPhase.id)!}"/>
<input type="hidden"  name="expectedStudy.year" value="${(expectedStudy.year)!}"/>
<input type="hidden"  name="expectedStudy.projectExpectedStudyInfo.id" value="${(expectedStudy.projectExpectedStudyInfo.id)!}"/>
<input id="redirectionUrl" type="hidden" name="url" value="" />


<div class="buttons">
  <div class="buttons-content">
    [#-- History Log  --]
    [#if action.getListLog(expectedStudy)?has_content]
      [#import "/WEB-INF/global/macros/logHistory.ftl" as logHistory /]
      [@logHistory.logList list=action.getListLog(expectedStudy) itemName="expectedID" itemId=(expectedStudy.id)! /]
      <a href="" onclick="return false" class="form-button button-history"><span class="far fa-list-alt" aria-hidden="true"></span> [@s.text name="form.buttons.history" /]</a>
    [/#if]
    
    [#if editable]
      [#-- Discard Button --]
      [@s.submit type="button" cssStyle="display:none"  name="cancel" cssClass="button-cancel"]<span class="fas fa-trash-alt" aria-hidden="true"></span> [@s.text name="form.buttons.discard" /] [/@s.submit]
      [#-- Save Button --]
      [@s.submit type="button" name="save" cssClass="button-save"]<span class="fas fa-save" aria-hidden="true"></span> <span class="saveText">[@s.text name="form.buttons.save" /]</span> [/@s.submit]
    [#elseif canEdit]
      [#-- Edit Button --]
      <a href="[@s.url][@s.param name="expectedID" value=(expectedID)! /][#include "/WEB-INF/global/pages/urlGlobalParams.ftl" /][/@s.url]" class="form-button button-edit"><span class="fas fa-edit" aria-hidden="true"></span> [@s.text name="form.buttons.edit" /]</a>
    [/#if]
  </div>
</div>