/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.deploy;

import com.liferay.portal.kernel.deploy.Deployer;
import com.liferay.portal.kernel.plugin.PluginPackage;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.UnsecureSAXReaderUtil;
import com.liferay.portal.plugin.PluginPackageUtil;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.io.File;

import java.util.Map;
import java.util.Properties;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Igor Beslic
 */
public class ThemeDeployerTest extends BaseDeployerTestCase {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Override
	public Deployer getDeployer() {
		return new ThemeDeployer();
	}

	@Test
	public void testProcessPluginPackageProperties() throws Exception {
		processPluginPackageProperties();

		File xmlFile = new File(getWebInfDir(), "liferay-plugin-package.xml");

		validateLiferayPluginPackageXMLFile(xmlFile);

		xmlFile = new File(getWebInfDir(), "liferay-look-and-feel.xml");

		validateLiferayLookAndFeelXMLFile(xmlFile);
	}

	protected Map<String, String> processPluginPackageProperties()
		throws Exception {

		String displayName = "test-theme";
		Properties properties = getLiferayPluginPackageProperties();

		PluginPackage pluginPackage =
			PluginPackageUtil.readPluginPackageProperties(
				displayName, properties);

		Assert.assertNotNull(pluginPackage);
		Assert.assertEquals("Test Theme", pluginPackage.getName());

		Deployer deployer = getDeployer();

		Map<String, String> filterMap = deployer.processPluginPackageProperties(
			getRootDir(), displayName, pluginPackage);

		Assert.assertNotNull(filterMap);
		Assert.assertFalse(filterMap.toString(), filterMap.isEmpty());

		return filterMap;
	}

	protected void validateLiferayLookAndFeelXMLFile(File xmlFile)
		throws Exception {

		Assert.assertTrue(xmlFile.exists());

		String liferayLookAndFeelXML = FileUtil.read(xmlFile);

		Assert.assertNotNull(liferayLookAndFeelXML);

		Document document = UnsecureSAXReaderUtil.read(
			liferayLookAndFeelXML, true);

		Element rootElement = document.getRootElement();

		Element element = rootElement.element("theme");

		String value = element.attributeValue("name");

		Assert.assertEquals("Test Theme", value);

		value = element.attributeValue("id");

		Assert.assertNotNull(value);

		element = rootElement.element("compatibility");

		Assert.assertNotNull(element);

		Assert.assertNotNull(element.getTextTrim());
	}

}