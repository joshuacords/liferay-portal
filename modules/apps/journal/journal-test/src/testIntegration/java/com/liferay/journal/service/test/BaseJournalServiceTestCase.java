/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.service.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.petra.xml.XMLUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.InputStream;

import org.junit.Before;
import org.junit.runner.RunWith;

/**
 * @author Marcellus Tavares
 */
@RunWith(Arquillian.class)
public abstract class BaseJournalServiceTestCase {

	@Before
	public void setUp() throws Exception {
		Group group = GroupTestUtil.addGroup();

		companyId = group.getCompanyId();
		groupId = group.getGroupId();
	}

	protected String generateId() throws Exception {
		String id = RandomTestUtil.randomString();

		return StringUtil.toUpperCase(id);
	}

	protected long getCompanyGroupId() throws Exception {
		Group companyGroup = GroupLocalServiceUtil.getCompanyGroup(companyId);

		return companyGroup.getGroupId();
	}

	protected String getDefultXsd() throws Exception {
		String xsd = readText("test-journal-structure-all-fields.xml");

		return XMLUtil.formatXML(xsd);
	}

	protected ServiceContext getServiceContext() {
		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		return serviceContext;
	}

	protected String readText(String fileName) throws Exception {
		Class<?> clazz = getClass();

		ClassLoader classLoader = clazz.getClassLoader();

		InputStream inputStream = classLoader.getResourceAsStream(
			"com/liferay/journal/dependencies/" + fileName);

		return StringUtil.read(inputStream);
	}

	protected long companyId;
	protected long groupId;

}