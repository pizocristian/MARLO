[#ftl]
[#-- Program identifier --]
<input type="hidden" name="programID" value="${crpProgramID}" />
<input type="hidden" name="crpProgramID" value="${crpProgramID}" />
<input type="hidden" name="outcomeID" value="${outcomeID}" />
<input type="hidden"  name="className" value="${(outcome.class.name)!}"/>
<input type="hidden"  name="id" value="${(outcome.id)!}"/>
<input type="hidden"  name="modifiedBy.id" value="${(currentUser.id)!}"/>
<input type="hidden"  name="actionName" value="${(actionName)!}"/>  
<input type="hidden"  name="phaseID" value="${(actualPhase.id)!}"/>
<input id="redirectionUrl" type="hidden" name="url" value="" />

[#assign recordsList = (action.getListLog(outcome))!{} /]

<div class="buttons"> 
  <div class="buttons-content">
    [#-- History Log--]
    [#if recordsList?has_content]
      [#import "/WEB-INF/global/macros/logHistory.ftl" as logHistory /]
      [@logHistory.logList list=recordsList itemName="outcomeID" itemId=outcomeID /]
      <a href="" onclick="return false" class="form-button button-history"><span class="far fa-list-alt" aria-hidden="true"></span> [@s.text name="form.buttons.history" /]</a>
    [/#if]
    [#if editable]
      [#-- Discard Button --]
      [@s.submit type="button" cssStyle="display:none" name="cancel" cssClass="button-cancel"]<span class="fas fa-trash-alt" aria-hidden="true"></span> [@s.text name="form.buttons.discard" /] [/@s.submit]
      
      [#-- Save Button --]
      [@s.submit type="button" name="save" cssClass="button-save"]<span class="fas fa-save" aria-hidden="true"></span> [@s.text name="form.buttons.save" /] [/@s.submit]
    [#elseif canEdit]
      [#-- Edit Button --]
      <a href="[@s.url][@s.param name="outcomeID" value=outcomeID /][#include "/WEB-INF/global/pages/urlGlobalParams.ftl" /][/@s.url]" class="form-button button-edit"><span class="fas fa-edit" aria-hidden="true"></span> [@s.text name="form.buttons.edit" /]</a>
    [/#if]
  </div>
</div>

[#-- Last update message --]
[#if recordsList?has_content]
[#assign lastRecord = recordsList[0] /]
<div class="clearfix"></div>
<span id="lastUpdateMessage" class="float-right"> 
  Last edit was made on <span class="datetime">${(lastRecord.createdDate)?datetime} ${(timeZone)!}</span> by <span class="modifiedBy">${lastRecord.user.composedCompleteName}</span>  
</span>
[/#if]