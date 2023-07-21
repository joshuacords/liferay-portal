/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.tag.stats.service.impl;

import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.tag.stats.model.AssetTagStats;
import com.liferay.asset.tag.stats.service.base.AssetTagStatsLocalServiceBaseImpl;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * Provides the local service for accessing, adding, deleting, and updating
 * asset tag statistics.
 *
 * @author Jorge Ferrer
 */
@Component(
	property = "model.class.name=com.liferay.asset.kernel.model.AssetTagStats",
	service = AopService.class
)
public class AssetTagStatsLocalServiceImpl
	extends AssetTagStatsLocalServiceBaseImpl {

	/**
	 * Adds an asset tag statistics instance.
	 *
	 * @param  tagId the primary key of the tag
	 * @param  classNameId the asset entry's class name ID
	 * @return the asset tag statistics instance
	 */
	@Override
	public AssetTagStats addTagStats(long tagId, long classNameId) {
		long tagStatsId = counterLocalService.increment();

		AssetTagStats tagStats = assetTagStatsPersistence.create(tagStatsId);

		tagStats.setTagId(tagId);
		tagStats.setClassNameId(classNameId);

		try {
			tagStats = assetTagStatsPersistence.update(tagStats);
		}
		catch (SystemException systemException) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					StringBundler.concat(
						"Add failed, fetch {tagId=", tagId, ", classNameId=",
						classNameId, "}"));
			}

			tagStats = assetTagStatsPersistence.fetchByT_C(
				tagId, classNameId, false);

			if (tagStats == null) {
				throw systemException;
			}
		}

		return tagStats;
	}

	/**
	 * Deletes the asset tag statistics instance.
	 *
	 * @param tagStats the asset tag statistics instance
	 */
	@Override
	public void deleteTagStats(AssetTagStats tagStats) {
		assetTagStatsPersistence.remove(tagStats);
	}

	/**
	 * Deletes the asset tag statistics instance matching the tag statistics ID.
	 *
	 * @param tagStatsId the primary key of the asset tag statistics instance
	 */
	@Override
	public void deleteTagStats(long tagStatsId) throws PortalException {
		AssetTagStats tagStats = assetTagStatsPersistence.findByPrimaryKey(
			tagStatsId);

		deleteTagStats(tagStats);
	}

	/**
	 * Deletes all asset tag statistics instances associated with the asset
	 * entry matching the class name ID.
	 *
	 * @param classNameId the asset entry's class name ID
	 */
	@Override
	public void deleteTagStatsByClassNameId(long classNameId) {
		List<AssetTagStats> tagStatsList =
			assetTagStatsPersistence.findByClassNameId(classNameId);

		for (AssetTagStats tagStats : tagStatsList) {
			deleteTagStats(tagStats);
		}
	}

	/**
	 * Deletes all asset tag statistics instances associated with the tag.
	 *
	 * @param tagId the primary key of the tag
	 */
	@Override
	public void deleteTagStatsByTagId(long tagId) {
		List<AssetTagStats> tagStatsList = assetTagStatsPersistence.findByTagId(
			tagId);

		for (AssetTagStats tagStats : tagStatsList) {
			deleteTagStats(tagStats);
		}
	}

	/**
	 * Returns a range of all the asset tag statistics instances associated with
	 * the asset entry matching the class name ID.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end -
	 * start</code> instances. <code>start</code> and <code>end</code> are not
	 * primary keys, they are indexes in the result set. Thus, <code>0</code>
	 * refers to the first result in the set. Setting both <code>start</code>
	 * and <code>end</code> to {@link
	 * com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	 * result set.
	 * </p>
	 *
	 * @param  classNameId the asset entry's class name ID
	 * @param  start the lower bound of the range of results
	 * @param  end the upper bound of the range of results (not inclusive)
	 * @return the range of asset tag statistics associated with the asset entry
	 *         matching the class name ID
	 */
	@Override
	public List<AssetTagStats> getTagStats(
		long classNameId, int start, int end) {

		return assetTagStatsPersistence.findByClassNameId(
			classNameId, start, end);
	}

	/**
	 * Returns the asset tag statistics instance with the tag and asset entry
	 * matching the class name ID
	 *
	 * @param  tagId the primary key of the tag
	 * @param  classNameId the asset entry's class name ID
	 * @return Returns the asset tag statistics instance with the tag and asset
	 *         entry  matching the class name ID
	 */
	@Override
	public AssetTagStats getTagStats(long tagId, long classNameId) {
		AssetTagStats tagStats = assetTagStatsPersistence.fetchByT_C(
			tagId, classNameId);

		if (tagStats == null) {
			tagStats = assetTagStatsLocalService.addTagStats(
				tagId, classNameId);
		}

		return tagStats;
	}

	/**
	 * Updates the asset tag statistics instance.
	 *
	 * @param  tagId the primary key of the tag
	 * @param  classNameId the asset entry's class name ID
	 * @return the updated asset tag statistics instance
	 */
	@Override
	public AssetTagStats updateTagStats(long tagId, long classNameId)
		throws PortalException {

		AssetTag tag = assetTagLocalService.getTag(tagId);

		int assetCount = assetTagLocalService.getTagsSize(
			tag.getGroupId(), classNameId, tag.getName());

		AssetTagStats tagStats = getTagStats(tagId, classNameId);

		tagStats.setAssetCount(assetCount);

		return assetTagStatsPersistence.update(tagStats);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AssetTagStatsLocalServiceImpl.class);

}