[#ftl]
[#assign title = "MARLO Bulk Replication" /]
[#assign currentSectionString = "${actionName?replace('/','-')}-phase-${(actualPhase.id)!}" /]
[#assign pageLibs = [] /]
[#assign customJS = [ "${baseUrl}/global/js/superadmin/marloBulk.js" ] /]
[#assign customCSS = [ "${baseUrl}/global/css/superadmin/superadmin.css" ] /]
[#assign currentSection = "superadmin" /]
[#assign currentStage = "bulkReplication" /]

[#assign breadCrumb = [
  {"label":"superadmin", "nameSpace":"", "action":"marloBoard"},
  {"label":"bulkReplication", "nameSpace":"", "action":""}
]/]

[#include "/WEB-INF/global/pages/header.ftl" /]
<hr />

<div class="container">
  [#include "/WEB-INF/global/pages/breadcrumb.ftl" /]
</div>
[#include "/WEB-INF/global/pages/generalMessages.ftl" /]

<section class="marlo-content">
  <div class="container"> 
    <div class="row">
      <div class="col-md-3">
        [#include "/WEB-INF/global/views/superadmin/menu-superadmin.ftl" /]
      </div>
      <div class="col-md-9">
        [@s.form action=actionName enctype="multipart/form-data" ]
        
        [#-- Cross-Cutting Issues --]
        <h4 class="sectionTitle">[@s.text name="marloBulkReplication.title" /]</h4>
        <div class="simpleBox">
          <div class="form-group row">
            <div class="col-md-4">
              <label for="globalUnitID">Global Unit:</label>
              <select name="globalUnitID" id="globalUnitID">
                <option value="-1">Select an option...</option>
                [#list (crps)![] as globalUnit]<option value="${globalUnit.id}">${globalUnit.acronym}</option>[/#list]
              </select>
            </div>
            <div class="col-md-4">
              <label for="phaseID">Phase:</label>
              <select name="phaseID" id="phaseID">
                <option value="">Select an option...</option>
              </select>
            </div>
          </div>
          <hr />
          <div class="form-group">
            <h4>Deliverables</h4>
            <div id="deliverables-checkbox">
              <ul>
                
              </ul>
            </div>
          </div>
          
          
        </div>
        
        [#-- Section Buttons--]
        <div class="buttons">
          <div class="buttons-content">
            [@s.submit type="button" name="save" cssClass="button-save"]<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span> [@s.text name="form.buttons.save" /][/@s.submit]
          </div>
        </div>
        
        [/@s.form]
      	
      </div>
    </div>
  </div>
</section>




[#-- Check Template --]
<div class="hide">
  [@customForm.checkmark id="check-template" label="title" name="deliverables" cssClass="" cssClassLabel="font-normal" /]
</div>


[#include "/WEB-INF/global/pages/footer.ftl" /]

