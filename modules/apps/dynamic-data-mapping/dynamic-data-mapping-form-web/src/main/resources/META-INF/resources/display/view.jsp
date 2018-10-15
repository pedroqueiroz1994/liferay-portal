<%@ taglib prefix="liferay-form" uri="http://liferay.com/tld/form" %>
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

<%@ include file="/display/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect", currentURL);

long formInstanceId = ddmFormDisplayContext.getFormInstanceId();
%>

<c:choose>
	<c:when test="<%= formInstanceId == 0 %>">
		<div class="alert alert-info">
			<liferay-ui:message key="select-an-existing-form-or-add-a-form-to-be-displayed-in-this-application" />
		</div>
	</c:when>
	<c:otherwise>

		<%
		String languageId = ddmFormDisplayContext.getDefaultLanguageId();

		Locale displayLocale = LocaleUtil.fromLanguageId(languageId);
		%>

		<c:choose>
			<c:when test="<%= ddmFormDisplayContext.isShowSuccessPage() %>">

				<%
				DDMFormSuccessPageSettings ddmFormSuccessPageSettings = ddmFormDisplayContext.getDDMFormSuccessPageSettings();

				LocalizedValue title = ddmFormSuccessPageSettings.getTitle();
				LocalizedValue body = ddmFormSuccessPageSettings.getBody();
				%>

				<div class="portlet-forms">
					<div class="ddm-form-basic-info ddm-form-success-page">
						<div class="container-fluid-1280">
							<h1 class="ddm-form-name"><%= HtmlUtil.escape(GetterUtil.getString(title.getString(displayLocale), title.getString(title.getDefaultLocale()))) %></h1>

							<h5 class="ddm-form-description"><%= HtmlUtil.escape(GetterUtil.getString(body.getString(displayLocale), body.getString(body.getDefaultLocale()))) %></h5>
						</div>
					</div>
				</div>
			</c:when>
			<c:when test="<%= ddmFormDisplayContext.isFormAvailable() %>">
				<portlet:actionURL name="addFormInstanceRecord" var="addFormInstanceRecordActionURL" />

				<%
				DDMFormInstance formInstance = ddmFormDisplayContext.getFormInstance();
				%>

				<div class="portlet-forms">
					<aui:form action="<%= addFormInstanceRecordActionURL %>" data-DDMFormInstanceId="<%= formInstanceId %>" method="post" name="fm">
						<liferay-form:ddm-form-renderer formInstanceId="<%= formInstance.getFormInstanceId() %>" />
					</aui:form>
				</div>

				<aui:script use="aui-base">
					var <portlet:namespace />intervalId;

					function <portlet:namespace />clearPortletHandlers(event) {
						if (<portlet:namespace />intervalId) {
							clearInterval(<portlet:namespace />intervalId);
						}

						Liferay.detach('destroyPortlet', <portlet:namespace />clearPortletHandlers);
					}

					Liferay.on('destroyPortlet', <portlet:namespace />clearPortletHandlers);

					<c:if test="<%= ddmFormDisplayContext.isFormShared() %>">
						document.title = '<%= HtmlUtil.escape(formInstance.getName(displayLocale)) %>';
					</c:if>

					function <portlet:namespace />fireFormView() {
						Liferay.fire(
							'ddmFormView',
							{
								formId: <%= formInstanceId %>,
								title: '<%= HtmlUtil.escape(formInstance.getName(displayLocale)) %>'
							}
						);
					}

					<c:choose>
						<c:when test="<%= ddmFormDisplayContext.isAutosaveEnabled() %>">
							var <portlet:namespace />form;

							<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="addFormInstanceRecord" var="autoSaveFormInstanceRecordURL">
								<portlet:param name="autoSave" value="<%= Boolean.TRUE.toString() %>" />
								<portlet:param name="languageId" value="<%= languageId %>" />
								<portlet:param name="preview" value="<%= String.valueOf(ddmFormDisplayContext.isPreview()) %>" />
							</liferay-portlet:resourceURL>

							function <portlet:namespace />autoSave() {
								A.io.request(
									'<%= autoSaveFormInstanceRecordURL.toString() %>',
									{
										data: {
											<portlet:namespace />formInstanceId: <%= formInstanceId %>,
											<portlet:namespace />serializedDDMFormValues: JSON.stringify(<portlet:namespace />form.toJSON())
										},
										method: 'POST'
									}
								);
							}

							function <portlet:namespace />startAutoSave() {
								if (<portlet:namespace />intervalId) {
									clearInterval(<portlet:namespace />intervalId);
								}

								<portlet:namespace />intervalId = setInterval(<portlet:namespace />autoSave, 60000);
							}

							<portlet:namespace />form = Liferay.component('<%= ddmFormDisplayContext.getContainerId() %>DDMForm');

							if (<portlet:namespace />form) {
								<portlet:namespace />startAutoSave();

								<portlet:namespace />fireFormView();
							}
							else {
								Liferay.after(
									'<%= ddmFormDisplayContext.getContainerId() %>DDMForm:render',
									function(event) {
										<portlet:namespace />form = Liferay.component('<%= ddmFormDisplayContext.getContainerId() %>DDMForm');

										if (<portlet:namespace />form) {
											<portlet:namespace />startAutoSave();

											<portlet:namespace />fireFormView();
										}
									}
								);
							}
						</c:when>
						<c:otherwise>
							function <portlet:namespace />startAutoExtendSession() {
								if (<portlet:namespace />intervalId) {
									clearInterval(<portlet:namespace />intervalId);
								}

								var tenSeconds = 10000;

								var time = Liferay.Session.get('sessionLength') || tenSeconds;

								<portlet:namespace />intervalId = setInterval(<portlet:namespace />extendSession, (time / 2));
							}

							function <portlet:namespace />extendSession() {
								Liferay.Session.extend();
							}

							<portlet:namespace />startAutoExtendSession();

							<portlet:namespace />fireFormView();
						</c:otherwise>
					</c:choose>
				</aui:script>
			</c:when>
			<c:otherwise>
				<div class="alert alert-warning">
					<liferay-ui:message key="this-form-not-available-or-it-was-not-published" />
				</div>
			</c:otherwise>
		</c:choose>
	</c:otherwise>
</c:choose>

<c:if test="<%= ddmFormDisplayContext.isShowConfigurationIcon() %>">
	<div class="icons-container lfr-meta-actions">
		<div class="btn-group lfr-icon-actions">
			<liferay-ui:icon
				cssClass="btn btn-link"
				iconCssClass="icon-cog"
				label="<%= true %>"
				message="select-form"
				method="get"
				onClick="<%= portletDisplay.getURLConfigurationJS() %>"
				url="<%= portletDisplay.getURLConfiguration() %>"
			/>
		</div>
	</div>
</c:if>