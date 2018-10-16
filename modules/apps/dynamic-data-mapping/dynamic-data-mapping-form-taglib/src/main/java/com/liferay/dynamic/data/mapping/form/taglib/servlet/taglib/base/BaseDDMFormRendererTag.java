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

package com.liferay.dynamic.data.mapping.form.taglib.servlet.taglib.base;

import com.liferay.dynamic.data.mapping.form.taglib.internal.servlet.ServletContextUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

/**
 * @author Pedro Queiroz
 * @author Rafael Praxedes
 * @generated
 */
public abstract class BaseDDMFormRendererTag extends com.liferay.taglib.util.IncludeTag {

	@Override
	public int doStartTag() throws JspException {
		setAttributeNamespace(_ATTRIBUTE_NAMESPACE);

		return super.doStartTag();
	}

	public java.lang.Long getDDMFormInstanceId() {
		return _ddmFormInstanceId;
	}

	public java.lang.Long getDDMFormInstanceRecordId() {
		return _ddmFormInstanceRecordId;
	}

	public java.lang.Long getDDMFormInstanceRecordVersionId() {
		return _ddmFormInstanceRecordVersionId;
	}

	public java.lang.Long getDDMFormInstanceVersionId() {
		return _ddmFormInstanceVersionId;
	}

	public java.lang.String getNamespace() {
		return _namespace;
	}

	public boolean getShowFormBasicInfo() {
		return _showFormBasicInfo;
	}

	public boolean getShowSubmitButton() {
		return _showSubmitButton;
	}

	public void setDDMFormInstanceId(java.lang.Long ddmFormInstanceId) {
		_ddmFormInstanceId = ddmFormInstanceId;
	}

	public void setDDMFormInstanceRecordId(
		java.lang.Long ddmFormInstanceRecordId) {

		_ddmFormInstanceRecordId = ddmFormInstanceRecordId;
	}

	public void setDDMFormInstanceRecordVersionId(
		java.lang.Long ddmFormInstanceRecordVersionId) {

		_ddmFormInstanceRecordVersionId = ddmFormInstanceRecordVersionId;
	}

	public void setDDMFormInstanceVersionId(
		java.lang.Long ddmFormInstanceVersionId) {

		_ddmFormInstanceVersionId = ddmFormInstanceVersionId;
	}

	public void setNamespace(java.lang.String namespace) {
		_namespace = namespace;
	}

	public void setShowFormBasicInfo(boolean showFormBasicInfo) {
		_showFormBasicInfo = showFormBasicInfo;
	}

	public void setShowSubmitButton(boolean showSubmitButton) {
		_showSubmitButton = showSubmitButton;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		setServletContext(ServletContextUtil.getServletContext());
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_ddmFormInstanceId = null;
		_ddmFormInstanceRecordId = null;
		_ddmFormInstanceRecordVersionId = null;
		_ddmFormInstanceVersionId = null;
		_namespace = null;
		_showFormBasicInfo = false;
		_showSubmitButton = false;
	}

	@Override
	protected String getEndPage() {
		return _END_PAGE;
	}

	@Override
	protected String getStartPage() {
		return _START_PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute("liferay-form:ddm-form-renderer:formInstanceId",
			_ddmFormInstanceId);
		request.setAttribute("liferay-form:ddm-form-renderer:formInstanceRecordId",
			_ddmFormInstanceRecordId);
		request.setAttribute("liferay-form:ddm-form-renderer:formInstanceRecordVersionId",
			_ddmFormInstanceRecordVersionId);
		request.setAttribute("liferay-form:ddm-form-renderer:formInstanceVersionId",
			_ddmFormInstanceVersionId);
		request.setAttribute("liferay-form:ddm-form-renderer:namespace", _namespace);
		request.setAttribute("liferay-form:ddm-form-renderer:showFormBasicInfo", String.valueOf(_showFormBasicInfo));
		request.setAttribute("liferay-form:ddm-form-renderer:showSubmitButton", String.valueOf(_showSubmitButton));
	}

	protected static final String _ATTRIBUTE_NAMESPACE = "liferay-form:ddm-form-renderer:";

	private static final String _END_PAGE =
		"/ddm_form_renderer/end.jsp";

	private static final String _START_PAGE =
		"/ddm_form_renderer/start.jsp";

	private java.lang.Long _ddmFormInstanceId = null;
	private java.lang.Long _ddmFormInstanceRecordId = null;
	private java.lang.Long _ddmFormInstanceRecordVersionId = null;
	private java.lang.Long _ddmFormInstanceVersionId = null;
	private java.lang.String _namespace = null;
	private boolean _showFormBasicInfo = false;
	private boolean _showSubmitButton = false;

}