/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.model;

import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.Contact;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.ModelListenerRegistrationUtil;
import com.liferay.registry.BasicRegistryImpl;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceRegistration;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Leon Chi
 */
public class ModelListenerRegistrationUtilTest {

	@Test
	public void testGetModelListeners() {
		BaseModelListener<Contact> baseModelListener =
			new BaseModelListener<Contact>() {
			};

		RegistryUtil.setRegistry(new BasicRegistryImpl());

		Registry registry = RegistryUtil.getRegistry();

		ServiceRegistration<ModelListener> serviceRegistration =
			registry.registerService(ModelListener.class, baseModelListener);

		try {
			Assert.assertArrayEquals(
				new ModelListener[] {baseModelListener},
				ModelListenerRegistrationUtil.getModelListeners(Contact.class));
		}
		finally {
			serviceRegistration.unregister();
		}
	}

}