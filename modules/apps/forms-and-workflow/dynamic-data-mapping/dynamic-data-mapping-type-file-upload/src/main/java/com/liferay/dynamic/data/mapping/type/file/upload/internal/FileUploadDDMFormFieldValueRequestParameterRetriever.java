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

package com.liferay.dynamic.data.mapping.type.file.upload.internal;

import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldValueRequestParameterRetriever;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.upload.FileItem;
import com.liferay.portal.kernel.upload.UploadServletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * @author Dylan Rebelak
 */
@Component(
	immediate = true, property = "ddm.form.field.type.name=file_upload"
)
public class FileUploadDDMFormFieldValueRequestParameterRetriever
	implements DDMFormFieldValueRequestParameterRetriever {

	@Override
	public String get(
		HttpServletRequest httpServletRequest, String ddmFormFieldParameterName,
		String defaultDDMFormFieldParameterValue) {

		UploadServletRequest uploadServletRequest =
			PortalUtil.getUploadServletRequest(httpServletRequest);

		JSONObject jsonObject = jsonFactory.createJSONObject();

		Map<String, FileItem[]> multipartParameterMap =
			uploadServletRequest.getMultipartParameterMap();

		Set<String> keys = multipartParameterMap.keySet();
		File[] files = null;

		for(String key : keys) {
			if(key.endsWith(ddmFormFieldParameterName)) {
				files = uploadServletRequest.getFiles(key);
			}
		}

		return null;
	}

	@Reference
	protected JSONFactory jsonFactory;

}