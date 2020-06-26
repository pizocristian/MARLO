[#ftl]
<input type="hidden"  name="phaseID" value="${(actualPhase.id)!}"/>

<div class="buttons">
  <div class="buttons-content">
  [#if editable]    
    [@s.submit type="button" name="save" cssClass="button-save"]<span class="fas fa-save" aria-hidden="true"></span> [@s.text name="form.buttons.save" /][/@s.submit]
  [#else]
    [#if canEdit]
      <a href="[@s.url][#include "/WEB-INF/global/pages/urlGlobalParams.ftl" /][/@s.url]" class="form-button button-edit"><span class="fas fa-edit" aria-hidden="true"></span> [@s.text name="form.buttons.edit" /]</a>
    [/#if]
  [/#if]
  </div>
</div>