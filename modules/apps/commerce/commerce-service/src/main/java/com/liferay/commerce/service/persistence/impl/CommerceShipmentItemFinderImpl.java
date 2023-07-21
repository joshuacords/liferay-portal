/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.service.persistence.impl;

import com.liferay.commerce.service.persistence.CommerceShipmentItemFinder;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.Iterator;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceShipmentItemFinderImpl
	extends CommerceShipmentItemFinderBaseImpl
	implements CommerceShipmentItemFinder {

	public static final String GET_COMMERCE_SHIPMENT_ORDER_ITEMS_QUANTITY =
		CommerceShipmentItemFinder.class.getName() +
			".getCommerceShipmentOrderItemsQuantity";

	@Override
	public int getCommerceShipmentOrderItemsQuantity(
		long commerceShipmentId, long commerceOrderItemId) {

		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(
				getClass(), GET_COMMERCE_SHIPMENT_ORDER_ITEMS_QUANTITY);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar("SUM_VALUE", Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(commerceShipmentId);
			qPos.add(commerceOrderItemId);

			Iterator<Long> itr = q.iterate();

			if (itr.hasNext()) {
				Long sum = itr.next();

				if (sum != null) {
					return sum.intValue();
				}
			}

			return 0;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@ServiceReference(type = CustomSQL.class)
	private CustomSQL _customSQL;

}