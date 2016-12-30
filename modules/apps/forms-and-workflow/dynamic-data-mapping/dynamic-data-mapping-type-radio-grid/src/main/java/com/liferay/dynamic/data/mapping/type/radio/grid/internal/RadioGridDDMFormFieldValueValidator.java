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

import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldValueValidationException;
import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldValueValidator;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.DDMFormFieldOptions;
import com.liferay.dynamic.data.mapping.model.Value;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * @author Marcellus Tavares
 */
@Component(
	immediate = true, property = "ddm.form.field.type.name=radio_grid",
	service = DDMFormFieldValueValidator.class
)
public class RadioGridDDMFormFieldValueValidator
	implements DDMFormFieldValueValidator {

	@Override
	public void validate(
			DDMFormField ddmFormField, DDMFormFieldValue ddmFormFieldValue)
		throws DDMFormFieldValueValidationException {

		DDMFormFieldOptions rows =
			(DDMFormFieldOptions) ddmFormField.getProperty("rows");
		DDMFormFieldOptions columns =
			(DDMFormFieldOptions) ddmFormField.getProperty("columns");

		if (rows == null || columns == null) {
			throw new DDMFormFieldValueValidationException(
				String.format(
					"Rows and columns must be set for radio grid field \"%s\"",
					ddmFormField.getName()));
		}

		Set<String> rowValues = rows.getOptionsValues();
		Set<String> columnValues = columns.getOptionsValues();

		if ((rowValues.isEmpty() || columnValues.isEmpty()) && ddmFormField.isRequired()) {
			throw new DDMFormFieldValueValidationException(
				"Rows and columns must contain at least one alternative each");
		}

		Value value = ddmFormFieldValue.getValue();

		Map<Locale, String> selectedValues = value.getValues();

		for (String selectedValue : selectedValues.values()) {
			validateSelectedValue(ddmFormField, rowValues, columnValues, selectedValue);
		}
	}

	protected JSONObject createJSONObject(String fieldName, String json) {
		try {
			return jsonFactory.createJSONObject(json);
		}
		catch (JSONException jsone) {

			// LPS-52675

			if (_log.isDebugEnabled()) {
				_log.debug(jsone, jsone);
			}

			throw new IllegalStateException(
				String.format(
					"Invalid data stored for radio grid field \"%s\"", fieldName));
		}
	}

	protected void validateSelectedValue(
			DDMFormField ddmFormField, Set<String> rowValues, Set<String> columnValues,
			String selectedValue)
		throws DDMFormFieldValueValidationException {

		String ddmFormFieldName = ddmFormField.getName();

		JSONObject jsonObject = createJSONObject(ddmFormFieldName, selectedValue);

		if (jsonObject.length() < rowValues.size() && ddmFormField.isRequired()) {
			throw new DDMFormFieldValueValidationException(
					String.format(
						"All rows of \"%s\" should have a value.",
						ddmFormFieldName));
		}

		Iterator<String> keys = jsonObject.keys();

		while (keys.hasNext()) {
			String key = keys.next();
			String value = (String) jsonObject.get(key);
			if (!rowValues.contains(key) || !columnValues.contains(value)) {
				throw new DDMFormFieldValueValidationException(
					String.format(
						"The selected option \"%s\" is not a valid choice",
						value));
			}
		}
	}

	@Reference
	protected JSONFactory jsonFactory;

	private static final Log _log = LogFactoryUtil.getLog(
		RadioGridDDMFormFieldValueValidator.class);

}