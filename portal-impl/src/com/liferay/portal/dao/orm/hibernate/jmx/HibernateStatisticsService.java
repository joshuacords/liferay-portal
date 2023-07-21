/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.dao.orm.hibernate.jmx;

import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.util.PropsValues;

import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.jmx.StatisticsService;

/**
 * @author Shuyang Zhou
 */
@OSGiBeanProperties(property = "jmx.objectname=Hibernate:name=statistics")
public class HibernateStatisticsService extends StatisticsService {

	public HibernateStatisticsService() {
		setStatisticsEnabled(PropsValues.HIBERNATE_GENERATE_STATISTICS);
	}

	public void setSessionFactoryImplementor(
		SessionFactoryImplementor sessionFactoryImplementor) {

		super.setSessionFactory(sessionFactoryImplementor);
	}

}