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

import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldTemplateContextContributor;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.DDMFormFieldOptions;
import com.liferay.dynamic.data.mapping.render.DDMFormFieldRenderingContext;
import com.liferay.portal.kernel.json.JSONFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Marcellus Tavares
 */
@Component(
	immediate = true, property = "ddm.form.field.type.name=radio_grid",
	service = {
		RadioGridDDMFormFieldTemplateContextContributor.class,
		DDMFormFieldTemplateContextContributor.class
	}
)
public class RadioGridDDMFormFieldTemplateContextContributor
	implements DDMFormFieldTemplateContextContributor {

	@Override
	public Map<String, Object> getParameters(
		DDMFormField ddmFormField,
		DDMFormFieldRenderingContext ddmFormFieldRenderingContext) {

		Map<String, Object> parameters = new HashMap<>();

		parameters.put(
			"rows", getRows(ddmFormField, ddmFormFieldRenderingContext));
		parameters.put(
			"columns", getColumns(ddmFormField, ddmFormFieldRenderingContext));

		return parameters;
	}

	protected DDMFormFieldOptions getDDMFormFieldOptions(String optionType,
		DDMFormField ddmFormField,
		DDMFormFieldRenderingContext ddmFormFieldRenderingContext) {

		DDMFormFieldOptions ddmFormFieldOptions = new DDMFormFieldOptions();

		List<Map<String, String>> keyValuePairs =
			(List<Map<String, String>>)
				ddmFormFieldRenderingContext.getProperty(optionType);

		if (keyValuePairs.isEmpty()) {
			return ddmFormField.getDDMFormFieldOptions();
		}

		for (Map<String, String> keyValuePair : keyValuePairs) {
			ddmFormFieldOptions.addOptionLabel(
				keyValuePair.get("value"),
				ddmFormFieldRenderingContext.getLocale(),
				keyValuePair.get("label"));
		}

		return ddmFormFieldOptions;
	}

	protected List<Object> getColumns(
		DDMFormField ddmFormField,
		DDMFormFieldRenderingContext ddmFormFieldRenderingContext) {

		RadioGridDDMFormFieldContextHelper radioDDMFormFieldContextHelper =
			new RadioGridDDMFormFieldContextHelper(
				jsonFactory, getDDMFormFieldOptions("columns",
					ddmFormField, ddmFormFieldRenderingContext),
				ddmFormFieldRenderingContext.getValue(),
				ddmFormField.getPredefinedValue(),
				ddmFormFieldRenderingContext.getLocale());

		return radioDDMFormFieldContextHelper.getOptions();
	}

	protected List<Object> getRows(
		DDMFormField ddmFormField,
		DDMFormFieldRenderingContext ddmFormFieldRenderingContext) {

		RadioGridDDMFormFieldContextHelper radioDDMFormFieldContextHelper =
			new RadioGridDDMFormFieldContextHelper(
				jsonFactory, getDDMFormFieldOptions("rows",
				ddmFormField, ddmFormFieldRenderingContext),
				ddmFormFieldRenderingContext.getValue(),
				ddmFormField.getPredefinedValue(),
				ddmFormFieldRenderingContext.getLocale());

		return radioDDMFormFieldContextHelper.getOptions();
	}

	@Reference
	protected JSONFactory jsonFactory;

}