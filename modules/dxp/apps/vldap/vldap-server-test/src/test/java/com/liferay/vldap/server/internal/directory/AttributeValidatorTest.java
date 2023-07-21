/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.vldap.server.internal.directory;

import com.liferay.portal.test.rule.LiferayUnitTestRule;
import com.liferay.vldap.server.internal.directory.builder.AttributeValidator;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Jonathan McCann
 */
public class AttributeValidatorTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void testIsAlwaysValidAttribute() {
		_attributeValidator.addAlwaysValidAttribute("ou");

		Assert.assertTrue(_attributeValidator.isValidAttribute("ou", "*"));
		Assert.assertTrue(_attributeValidator.isValidAttribute("OU", "TEST1"));
	}

	@Test
	public void testIsValidAttribute() {
		_attributeValidator.addValidAttributeValues("cn", "test1", "test2");

		Assert.assertTrue(_attributeValidator.isValidAttribute("cn", "test1"));
		Assert.assertTrue(_attributeValidator.isValidAttribute("cn", "test2"));
		Assert.assertTrue(_attributeValidator.isValidAttribute("CN", "TEST1"));
		Assert.assertTrue(_attributeValidator.isValidAttribute("CN", "TEST2"));
		Assert.assertFalse(_attributeValidator.isValidAttribute("cn", "test3"));
		Assert.assertFalse(_attributeValidator.isValidAttribute("CN", "TEST3"));
	}

	@Test
	public void testIsValidAttributeWithNullAttributeValues() {
		Assert.assertFalse(_attributeValidator.isValidAttribute("ou", "*"));
		Assert.assertFalse(_attributeValidator.isValidAttribute("cn", "test1"));
	}

	private final AttributeValidator _attributeValidator =
		new AttributeValidator();

}