/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.dao.orm.hibernate;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;

import org.hibernate.PropertyNotFoundException;
import org.hibernate.property.DirectPropertyAccessor;
import org.hibernate.property.Getter;
import org.hibernate.property.Setter;

/**
 * @author Shuyang Zhou
 */
@SuppressWarnings("rawtypes")
public class PrivatePropertyAccessor extends DirectPropertyAccessor {

	@Override
	public Getter getGetter(Class clazz, String propertyName)
		throws PropertyNotFoundException {

		Class<?> superClass = null;

		while ((superClass = clazz.getSuperclass()) != BaseModelImpl.class) {
			clazz = superClass;
		}

		propertyName = StringPool.UNDERLINE.concat(propertyName);

		return super.getGetter(clazz, propertyName);
	}

	@Override
	public Setter getSetter(Class clazz, String propertyName)
		throws PropertyNotFoundException {

		Class<?> superClass = null;

		while ((superClass = clazz.getSuperclass()) != BaseModelImpl.class) {
			clazz = superClass;
		}

		propertyName = StringPool.UNDERLINE.concat(propertyName);

		return super.getSetter(clazz, propertyName);
	}

}