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

package com.liferay.dynamic.data.mapping.type.paragraph.internal;

import com.google.template.soy.data.SanitizedContent;
import com.google.template.soy.data.UnsafeSanitizedContentOrdainer;

import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.type.BaseDDMFormFieldTypeSettingsTestCase;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.template.soy.utils.SoyHTMLSanitizer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Leonardo Barros
 */
@PrepareForTest({PortalClassLoaderUtil.class, ResourceBundleUtil.class})
@RunWith(PowerMockRunner.class)
public class ParagraphDDMFormFieldTemplateContextContributorTest
	extends BaseDDMFormFieldTypeSettingsTestCase {

	@Before
	public void setUp() throws Exception {
		setUpSoyHTMLSanitizer();
	}

	@Test
	public void testGetSanitizedText() {
		DDMFormField ddmFormField = new DDMFormField("field", "paragraph");

		String text = "<h1>This is a header</h1>";

		ddmFormField.setProperty("text", text);

		Object expectedSanitizedText = _soyHTMLSanitizer.sanitize(text);
		Object actualSanitizedText =
			_paragraphDDMFormFieldTemplateContextContributor.getSanitizedText(
				ddmFormField);

		Assert.assertEquals(
			expectedSanitizedText.toString(), actualSanitizedText.toString());
	}

	protected void setUpSoyHTMLSanitizer() {
		_soyHTMLSanitizer =
			value -> UnsafeSanitizedContentOrdainer.ordainAsSafe(
				value, SanitizedContent.ContentKind.HTML);
	}

	private final ParagraphDDMFormFieldTemplateContextContributor
		_paragraphDDMFormFieldTemplateContextContributor =
			new ParagraphDDMFormFieldTemplateContextContributor();
	private SoyHTMLSanitizer _soyHTMLSanitizer;

}