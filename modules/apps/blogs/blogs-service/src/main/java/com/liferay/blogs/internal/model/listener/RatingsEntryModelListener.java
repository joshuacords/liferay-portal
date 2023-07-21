/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.blogs.internal.model.listener;

import com.liferay.blogs.model.BlogsEntry;
import com.liferay.blogs.model.BlogsStatsUser;
import com.liferay.blogs.service.BlogsEntryLocalService;
import com.liferay.blogs.service.BlogsStatsUserLocalService;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portlet.ratings.model.impl.RatingsEntryModelImpl;
import com.liferay.ratings.kernel.model.RatingsEntry;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Preston Crary
 */
@Component(immediate = true, service = ModelListener.class)
public class RatingsEntryModelListener extends BaseModelListener<RatingsEntry> {

	@Override
	public void onBeforeCreate(RatingsEntry ratingsEntry)
		throws ModelListenerException {

		_updateBlogsStatsUser(ratingsEntry);
	}

	@Override
	public void onBeforeRemove(RatingsEntry ratingsEntry)
		throws ModelListenerException {

		String className = ratingsEntry.getClassName();

		if (!className.equals(BlogsEntry.class.getName())) {
			return;
		}

		try {
			BlogsEntry blogsEntry = _blogsEntryLocalService.getEntry(
				ratingsEntry.getClassPK());

			BlogsStatsUser blogsStatsUser =
				_blogsStatsUserLocalService.fetchStatsUser(
					blogsEntry.getGroupId(), blogsEntry.getUserId());

			if (blogsStatsUser != null) {
				double ratingsAverageScore = 0;
				double ratingsTotalScore = 0;

				int ratingsTotalEntries =
					blogsStatsUser.getRatingsTotalEntries() - 1;

				if (ratingsTotalEntries > 0) {
					ratingsTotalScore =
						blogsStatsUser.getRatingsTotalScore() -
							ratingsEntry.getScore();

					ratingsAverageScore =
						ratingsTotalScore / ratingsTotalEntries;
				}

				_blogsStatsUserLocalService.updateStatsUser(
					blogsEntry.getGroupId(), blogsEntry.getUserId(),
					ratingsTotalEntries, ratingsTotalScore,
					ratingsAverageScore);
			}
		}
		catch (PortalException portalException) {
			throw new ModelListenerException(portalException);
		}
	}

	@Override
	public void onBeforeUpdate(RatingsEntry ratingsEntry)
		throws ModelListenerException {

		_updateBlogsStatsUser(ratingsEntry);
	}

	private void _updateBlogsStatsUser(RatingsEntry ratingsEntry)
		throws ModelListenerException {

		String className = ratingsEntry.getClassName();

		if (!className.equals(BlogsEntry.class.getName())) {
			return;
		}

		try {
			BlogsEntry blogsEntry = _blogsEntryLocalService.getEntry(
				ratingsEntry.getClassPK());

			BlogsStatsUser blogsStatsUser =
				_blogsStatsUserLocalService.getStatsUser(
					blogsEntry.getGroupId(), blogsEntry.getUserId());

			int ratingsTotalEntries = blogsStatsUser.getRatingsTotalEntries();
			double ratingsTotalScore = blogsStatsUser.getRatingsTotalScore();

			RatingsEntryModelImpl ratingsEntryModelImpl =
				(RatingsEntryModelImpl)ratingsEntry;

			double originalScore = ratingsEntryModelImpl.getOriginalScore();

			ratingsTotalScore += ratingsEntry.getScore() - originalScore;

			if (ratingsEntry.isNew()) {
				ratingsTotalEntries++;
			}

			double ratingsAverageScore =
				ratingsTotalScore / ratingsTotalEntries;

			_blogsStatsUserLocalService.updateStatsUser(
				blogsEntry.getGroupId(), blogsEntry.getUserId(),
				ratingsTotalEntries, ratingsTotalScore, ratingsAverageScore);
		}
		catch (PortalException portalException) {
			throw new ModelListenerException(portalException);
		}
	}

	@Reference
	private BlogsEntryLocalService _blogsEntryLocalService;

	@Reference
	private BlogsStatsUserLocalService _blogsStatsUserLocalService;

}