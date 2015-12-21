<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(renderRequest, "tabs1", "assigned-to-me");

PortletURL portletURL = workflowTaskDisplayContext.getPortletURL();

String displayStyle = workflowTaskDisplayContext.getDisplayStyle();

DateSearchEntry dateSearchEntry = new DateSearchEntry();
%>

<div class="container-fluid-1280">
	<aui:form action="<%= portletURL.toString() %>" method="post" name="fm">
		<aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon">
			<aui:nav cssClass="navbar-nav">
				<portlet:renderURL var="viewAssignedToMeURL">
					<portlet:param name="mvcPath" value="/view.jsp" />
					<portlet:param name="tabs1" value="assigned-to-me" />
				</portlet:renderURL>

				<aui:nav-item
					href="<%= viewAssignedToMeURL %>"
					label="assigned-to-me"
					selected='<%= tabs1.equals("assigned-to-me") %>'
				/>

				<portlet:renderURL var="viewAssignedToMyRolesURL">
					<portlet:param name="mvcPath" value="/view.jsp" />
					<portlet:param name="tabs1" value="assigned-to-my-roles" />
				</portlet:renderURL>

				<aui:nav-item
					href="<%= viewAssignedToMyRolesURL %>"
					label="assigned-to-my-roles"
					selected='<%= tabs1.equals("assigned-to-my-roles") %>'
				/>
			</aui:nav>

			<aui:nav-bar-search>
				<aui:form action="<%= portletURL.toString() %>" method="post" name="fm1">
					<liferay-ui:input-search markupView="lexicon" />
				</aui:form>
			</aui:nav-bar-search>
		</aui:nav-bar>

		<liferay-util:include page="/toolbar.jsp" servletContext="<%= application %>" />

		<c:choose>
			<c:when test="<%= workflowTaskDisplayContext.isAssignedToMeTabSelected() %>">
				<liferay-ui:panel-container extended="<%= false %>" id="workflowTasksPanelContainer" persistState="<%= true %>">

					<%
					WorkflowTaskSearch workflowTaskSearch = workflowTaskDisplayContext.getTasksAssignedToMe();
					%>

					<%@ include file="/workflow_tasks.jspf" %>
				</liferay-ui:panel-container>
			</c:when>
			<c:otherwise>

				<%
				WorkflowTaskSearch workflowTaskSearch = workflowTaskDisplayContext.getTasksAssignedToMyRoles();
				%>

				<%@ include file="/workflow_tasks.jspf" %>
			</c:otherwise>
		</c:choose>
	</aui:form>
</div>