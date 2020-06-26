[#ftl]
[#-- Project identifier --]
<input name="projectID" type="hidden" value="${project.id}" />
<input name="deliverableID" type="hidden" value="${deliverable.id}" />
<input type="hidden"  name="deliverable.deliverableInfo.phase.id" value="${(actualPhase.id)!}"/>
<input type="hidden"  name="className" value="${(deliverable.class.name)!}"/>
<input type="hidden"  name="id" value="${(deliverable.id)!}"/>
<input type="hidden"  name="modifiedBy.id" value="${(currentUser.id)!}"/>
<input type="hidden"  name="actionName" value="${(actionName)!}"/>
<input type="hidden"  name="phaseID" value="${(actualPhase.id)!}"/>

<input id="redirectionUrl" type="hidden" name="url" value="" />

[#assign recordsList = (action.getListLog(deliverable))!{} /]

<div class="clearfix"></div>
<div class="buttons">
  [#if !action.isDeliverableNew(deliverableID) && !hideJustification]
    [#if editable]
    <div class="form-group">
      [@customForm.textArea name="justification" i18nkey="saving.justification" required=true className="justification"/]
    </div>
    [/#if]
  [/#if]
  
  <div class="buttons-content">
    [#-- History Log --]
    [#if recordsList?has_content]
      [#import "/WEB-INF/global/macros/logHistory.ftl" as logHistory /]
      [@logHistory.logList list=recordsList itemName="deliverableID" itemId=deliverable.id /]
      <a href="" onclick="return false" class="form-button button-history"><span class="far fa-list-alt" aria-hidden="true"></span> [@s.text name="form.buttons.history" /]</a>
    [/#if]
    [#if editable || editStatus]
      [#-- Discard Button --]
      [@s.submit type="button" cssStyle="display:none"   name="cancel" cssClass="button-cancel"]<span class="fas fa-trash-alt" aria-hidden="true"></span> [@s.text name="form.buttons.discard" /] [/@s.submit]
      [#-- Save Button --]
      [@s.submit type="button" name="save" cssClass="button-save"]<span class="fas fa-save" aria-hidden="true"></span> <span class="saveText">[@s.text name="form.buttons.save" /]</span> [/@s.submit]
      [#-- Replicate to the next upkeep --]
      [#include "/WEB-INF/global/pages/replicateButton.ftl" /]
    [#elseif canEdit]
      [#-- Edit Button --]
      <a href="[@s.url][@s.param name="deliverableID" value=deliverableID /][#include "/WEB-INF/global/pages/urlGlobalParams.ftl" /][/@s.url]" class="form-button button-edit"><span class="fas fa-edit" aria-hidden="true"></span> [@s.text name="form.buttons.edit" /]</a>
    [/#if]
  </div>
  <div class="clearfix"></div>
</div>

[#-- Last update message --]
[#if recordsList?has_content]
[#assign lastRecord = recordsList[0] /]
<span id="lastUpdateMessage" class="float-right"> 
  Last edit was made on <span class="datetime">${(lastRecord.createdDate)?datetime} ${(timeZone)!}</span> by <span class="modifiedBy">${lastRecord.user.composedCompleteName}</span>  
</span>
[/#if]