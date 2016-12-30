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

package com.liferay.dynamic.data.mapping.type.radio.grid.internal;

import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldValueRequestParameterRetriever;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Marcellus Tavares
 */
@Component(immediate = true, property = "ddm.form.field.type.name=radio_grid")
public class RadioGridDDMFormFieldValueRequestParameterRetriever
	implements DDMFormFieldValueRequestParameterRetriever {

	@Override
	public String get(
		HttpServletRequest httpServletRequest, String ddmFormFieldParameterName,
		String defaultDDMFormFieldParameterValue) {


		JSONObject jsonObject = jsonFactory.createJSONObject();

		Map<String, String[]> parametersMap =
			httpServletRequest.getParameterMap();


		for(Map.Entry<String, String[]> entry : parametersMap.entrySet()) {
			if (entry.getKey().startsWith(ddmFormFieldParameterName)) {
				String key = StringUtil.extractLast(entry.getKey(), ddmFormFieldParameterName);

				String value = entry.getValue()[0];
				jsonObject.put(key, value != null ? value : StringPool.BLANK);
			}

		}

		return jsonObject.toString();
	}

	@Reference
	protected JSONFactory jsonFactory;

}