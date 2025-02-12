[#ftl]
[#assign srfItems= [
  { 'slug': 'slos',   'name': 'menu.superadmin.slos', 'action': 'marloSLOs',   'active': true },
  { 'slug': 'crossCutting',   'name': 'menu.superadmin.crossCutting', 'action': 'marloCrossCutting',   'active': true },
  { 'slug': 'idos',   'name': 'menu.superadmin.idos', 'action': 'marloIDOs',   'active': true },
  { 'slug': 'siteIntegration',   'name': 'menu.superadmin.siteIntegration', 'action': 'marloSiteIntegration',   'active': true }
]/]

[#assign standards= [
  { 'slug': 'marloBoard',   'name': 'menu.superadmin.board', 'action': 'marloBoard',   'active': true },
  { 'slug': 'users.types',            'name': 'Partner Types', 'action': 'marloUsers',   'active': false },
  { 'slug': 'customLocations',    'name': 'menu.superadmin.customLocations', 'action': 'customLocations',   'active': true }
]/]

[#if !action.isAiccra()]
  [#assign toolItems= [
    { 'slug': 'notifications',    'name': 'menu.superadmin.notifications', 'action': 'notifications',   'active': true },
    { 'slug': 'emails',    'name': 'menu.superadmin.emails', 'action': 'emails',   'active': true },
    { 'slug': 'permissions',    'name': 'menu.superadmin.permissions', 'action': 'marloPermissions',   'active': false }
    { 'slug': 'parameters',    'name': 'menu.superadmin.parameters', 'action': 'marloParameters',   'active': true },
    { 'slug': 'institutions',    'name': 'menu.superadmin.institutions', 'action': 'marloInstitutions',   'active': true }
    { 'slug': 'bulkReplication',    'name': 'menu.superadmin.bulkReplication', 'action': 'deliverablesReplication',   'active': true }
  ]/]
[#else]
  [#assign toolItems= [
    { 'slug': 'notifications',    'name': 'menu.superadmin.notifications', 'action': 'notifications',   'active': true },
    { 'slug': 'emails',    'name': 'menu.superadmin.emails', 'action': 'emails',   'active': true },
    { 'slug': 'permissions',    'name': 'menu.superadmin.permissions', 'action': 'marloPermissions',   'active': false }
    { 'slug': 'parameters',    'name': 'menu.superadmin.parameters', 'action': 'marloParameters',   'active': true },
    <#--  { 'slug': 'institutions',    'name': 'menu.superadmin.institutions', 'action': 'marloInstitutions',   'active': true }  -->
    { 'slug': 'bulkReplication',    'name': 'menu.superadmin.bulkReplication', 'action': 'deliverablesReplication',   'active': true }
  ]/]
  
  [#assign managers= [
    { 'slug': 'timelineManagement',    'name': 'menu.superadmin.timelineManagement', 'action': 'timelineManagement',   'active': true}
    { 'slug': 'feedbackManagement',    'name': 'menu.superadmin.feedbackManagement', 'action': 'feedbackManagement',   'active': true}
    { 'slug': 'buttonGuideManagement',    'name': 'menu.superadmin.buttonGuideManagement', 'action': 'buttonGuideManagement',   'active': true}
  ]/]
[/#if]

<nav id="secondaryMenu">
  <p>[@s.text name="menu.superadmin.srfMenuTitle"/]</p>
  <ul>
    <li>
      <ul>
        [#list srfItems as item]
          <li id="${item.slug}" class="[#if item.slug == currentStage]currentSection[/#if] ${(item.active)?string('enabled','disabled')}">
            <a href="[@s.url action=item.action ][#include "/WEB-INF/global/pages/urlGlobalParams.ftl" /][/@s.url]" onclick="return ${item.active?string}">
              [@s.text name=item.name/]
            </a>
          </li>
        [/#list] 
      </ul>
    </li>
  </ul>
  
  <p>Standards</p>
  <ul>
    <li>
      <ul>
        [#list standards as item]
          <li id="${item.slug}" class="[#if item.slug == currentStage]currentSection[/#if] ${(item.active)?string('enabled','disabled')}">
            <a href="[@s.url action=item.action ][#include "/WEB-INF/global/pages/urlGlobalParams.ftl" /][/@s.url]" onclick="return ${item.active?string}">
              [@s.text name=item.name/]
            </a>
          </li>
        [/#list] 
      </ul>
    </li>
  </ul> 
  
  <p>[@s.text name="menu.superadmin.toolsMenuTitle"/]</p>
  <ul>
    <li>
      <ul>
        [#list toolItems as item]
          <li id="${item.slug}" class="[#if item.slug == currentStage]currentSection[/#if] ${(item.active)?string('enabled','disabled')}">
            <a href="[@s.url action=item.action ][#include "/WEB-INF/global/pages/urlGlobalParams.ftl" /][/@s.url]" onclick="return ${item.active?string}">
              [@s.text name=item.name/]
            </a>
          </li>
        [/#list] 
      </ul>
    </li>
  </ul> 
  
  <p>Section Managers</p>
  <ul>
    <li>
      <ul>
        [#list managers as item]
          <li id="${item.slug}" class="[#if item.slug == currentStage]currentSection[/#if] ${(item.active)?string('enabled','disabled')}">
            <a href="[@s.url action=item.action ][#include "/WEB-INF/global/pages/urlGlobalParams.ftl" /][/@s.url]" onclick="return ${item.active?string}">
              [@s.text name=item.name/]
            </a>
          </li>
        [/#list] 
      </ul>
    </li>
  </ul> 
</nav>



<div class="clearfix"></div>