/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.dao.orm.hibernate.event;

import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.engine.PersistenceContext;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.event.EventSource;
import org.hibernate.event.FlushEvent;
import org.hibernate.event.def.DefaultFlushEventListener;
import org.hibernate.stat.Statistics;
import org.hibernate.stat.StatisticsImplementor;

/**
 * @author Matthew Tambara
 */
public class NestableFlushEventListener extends DefaultFlushEventListener {

	public static final NestableFlushEventListener INSTANCE =
		new NestableFlushEventListener();

	@Override
	public void onFlush(FlushEvent event) throws HibernateException {
		EventSource eventSource = event.getSession();

		PersistenceContext persistenceContext =
			eventSource.getPersistenceContext();

		Map<?, ?> entityEntries = persistenceContext.getEntityEntries();

		if (entityEntries.isEmpty()) {
			Map<?, ?> collectionEntries =
				persistenceContext.getCollectionEntries();

			if (collectionEntries.isEmpty()) {
				return;
			}
		}

		boolean flushing = persistenceContext.isFlushing();

		try {
			flushEverythingToExecutions(event);

			persistenceContext.setFlushing(true);

			performExecutions(eventSource);

			postFlush(eventSource);
		}
		finally {
			persistenceContext.setFlushing(flushing);
		}

		SessionFactoryImplementor sessionFactoryImplementor =
			eventSource.getFactory();

		Statistics statistics = sessionFactoryImplementor.getStatistics();

		if (statistics.isStatisticsEnabled()) {
			StatisticsImplementor statisticsImplementor =
				sessionFactoryImplementor.getStatisticsImplementor();

			statisticsImplementor.flush();
		}
	}

}