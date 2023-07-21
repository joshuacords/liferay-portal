/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.dao.orm.hibernate.event;

import java.util.Map;

import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.engine.ActionQueue;
import org.hibernate.engine.PersistenceContext;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.event.AutoFlushEvent;
import org.hibernate.event.EventSource;
import org.hibernate.event.def.DefaultAutoFlushEventListener;
import org.hibernate.stat.Statistics;
import org.hibernate.stat.StatisticsImplementor;

/**
 * @author Shuyang Zhou
 */
public class NestableAutoFlushEventListener
	extends DefaultAutoFlushEventListener {

	public static final NestableAutoFlushEventListener INSTANCE =
		new NestableAutoFlushEventListener();

	@Override
	public void onAutoFlush(AutoFlushEvent autoFlushEvent)
		throws HibernateException {

		EventSource eventSource = autoFlushEvent.getSession();

		if (!_isFlushable(eventSource)) {
			return;
		}

		ActionQueue actionQueue = eventSource.getActionQueue();

		int oldSize = actionQueue.numberOfCollectionRemovals();

		PersistenceContext persistenceContext =
			eventSource.getPersistenceContext();

		boolean flushing = persistenceContext.isFlushing();

		try {
			flushEverythingToExecutions(autoFlushEvent);
		}
		finally {
			persistenceContext.setFlushing(flushing);
		}

		if (_isFlushReallyNeeded(autoFlushEvent, eventSource)) {
			persistenceContext.setFlushing(true);

			try {
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
		else if (!persistenceContext.isFlushing()) {
			actionQueue.clearFromFlushNeededCheck(oldSize);
		}

		autoFlushEvent.setFlushRequired(
			_isFlushReallyNeeded(autoFlushEvent, eventSource));
	}

	private boolean _isFlushable(EventSource eventSource) {
		FlushMode flushMode = eventSource.getFlushMode();

		if (flushMode.lessThan(FlushMode.AUTO)) {
			return false;
		}

		if (eventSource.getDontFlushFromFind() != 0) {
			return false;
		}

		PersistenceContext persistenceContext =
			eventSource.getPersistenceContext();

		Map<?, ?> entityEntries = persistenceContext.getEntityEntries();

		if (!entityEntries.isEmpty()) {
			return true;
		}

		Map<?, ?> collectionEntries = persistenceContext.getCollectionEntries();

		if (!collectionEntries.isEmpty()) {
			return true;
		}

		return false;
	}

	private boolean _isFlushReallyNeeded(
		AutoFlushEvent autoFlushEvent, EventSource eventSource) {

		if (eventSource.getFlushMode() == FlushMode.ALWAYS) {
			return true;
		}

		ActionQueue actionQueue = eventSource.getActionQueue();

		return actionQueue.areTablesToBeUpdated(
			autoFlushEvent.getQuerySpaces());
	}

}