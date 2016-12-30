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

import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldValueRenderer;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.DDMFormFieldOptions;
import com.liferay.dynamic.data.mapping.model.LocalizedValue;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.Iterator;
import java.util.Locale;

/**
 * @author Renato Rego
 */
@Component(immediate = true, property = {"ddm.form.field.type.name=radio_grid"})
public class RadioGridDDMFormFieldValueRenderer
	implements DDMFormFieldValueRenderer {

	@Override
	public String render(DDMFormFieldValue ddmFormFieldValue, Locale locale) {
		JSONObject optionValuesJSONObject =
			radioGridDDMFormFieldValueAccessor.getValue(
				ddmFormFieldValue, locale);

		if (optionValuesJSONObject.length() == 0) {
			return StringPool.BLANK;
		}

		DDMFormFieldOptions rows = getDDMFormFieldOptions(
			ddmFormFieldValue, "rows");
		DDMFormFieldOptions columns = getDDMFormFieldOptions(
			ddmFormFieldValue, "columns");

		StringBundler sb = new StringBundler();

		Iterator<String> keys = optionValuesJSONObject.keys();
		while(keys.hasNext()) {
			String key = keys.next();
			LocalizedValue rowLabel = rows.getOptionLabels(key);
			LocalizedValue columnLabel =
				columns.getOptionLabels(optionValuesJSONObject.getString(key));

			sb.append(rowLabel.getString(locale));
			sb.append(StringPool.COLON);
			sb.append(StringPool.SPACE);
			sb.append(columnLabel.getString(locale));
			sb.append(StringPool.COMMA_AND_SPACE);
		}

		sb.setIndex(sb.index() - 1);

		return sb.toString();
	}

	protected DDMFormFieldOptions getDDMFormFieldOptions(
		DDMFormFieldValue ddmFormFieldValue, String optionType) {

		DDMFormField ddmFormField = ddmFormFieldValue.getDDMFormField();

		DDMFormFieldOptions options =
			(DDMFormFieldOptions) ddmFormField.getProperty(optionType);

		return options;
	}

	@Reference
	protected RadioGridDDMFormFieldValueAccessor radioGridDDMFormFieldValueAccessor;

}