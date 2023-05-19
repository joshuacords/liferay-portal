/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.asset.kernel.service;

import com.liferay.asset.kernel.model.AssetTagStats;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for AssetTagStats. This utility wraps
 * <code>com.liferay.portlet.asset.service.impl.AssetTagStatsLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see AssetTagStatsLocalService
 * @deprecated As of Judson (7.1.x), replaced by {@link
 com.liferay.asset.tag.stats.service.impl.AssetTagStatsLocalServiceImpl}
 * @generated
 */
@Deprecated
public class AssetTagStatsLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.portlet.asset.service.impl.AssetTagStatsLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the asset tag stats to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AssetTagStatsLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param assetTagStats the asset tag stats
	 * @return the asset tag stats that was added
	 */
	public static AssetTagStats addAssetTagStats(AssetTagStats assetTagStats) {
		return getService().addAssetTagStats(assetTagStats);
	}

	/**
	 * Adds an asset tag statistics instance.
	 *
	 * @param tagId the primary key of the tag
	 * @param classNameId the asset entry's class name ID
	 * @return the asset tag statistics instance
	 */
	public static AssetTagStats addTagStats(long tagId, long classNameId) {
		return getService().addTagStats(tagId, classNameId);
	}

	/**
	 * Creates a new asset tag stats with the primary key. Does not add the asset tag stats to the database.
	 *
	 * @param tagStatsId the primary key for the new asset tag stats
	 * @return the new asset tag stats
	 */
	public static AssetTagStats createAssetTagStats(long tagStatsId) {
		return getService().createAssetTagStats(tagStatsId);
	}

	/**
	 * Deletes the asset tag stats from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AssetTagStatsLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param assetTagStats the asset tag stats
	 * @return the asset tag stats that was removed
	 */
	public static AssetTagStats deleteAssetTagStats(
		AssetTagStats assetTagStats) {

		return getService().deleteAssetTagStats(assetTagStats);
	}

	/**
	 * Deletes the asset tag stats with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AssetTagStatsLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param tagStatsId the primary key of the asset tag stats
	 * @return the asset tag stats that was removed
	 * @throws PortalException if a asset tag stats with the primary key could not be found
	 */
	public static AssetTagStats deleteAssetTagStats(long tagStatsId)
		throws PortalException {

		return getService().deleteAssetTagStats(tagStatsId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the asset tag statistics instance.
	 *
	 * @param tagStats the asset tag statistics instance
	 */
	public static void deleteTagStats(AssetTagStats tagStats) {
		getService().deleteTagStats(tagStats);
	}

	/**
	 * Deletes the asset tag statistics instance matching the tag statistics ID.
	 *
	 * @param tagStatsId the primary key of the asset tag statistics instance
	 */
	public static void deleteTagStats(long tagStatsId) throws PortalException {
		getService().deleteTagStats(tagStatsId);
	}

	/**
	 * Deletes all asset tag statistics instances associated with the asset
	 * entry matching the class name ID.
	 *
	 * @param classNameId the asset entry's class name ID
	 */
	public static void deleteTagStatsByClassNameId(long classNameId) {
		getService().deleteTagStatsByClassNameId(classNameId);
	}

	/**
	 * Deletes all asset tag statistics instances associated with the tag.
	 *
	 * @param tagId the primary key of the tag
	 */
	public static void deleteTagStatsByTagId(long tagId) {
		getService().deleteTagStatsByTagId(tagId);
	}

	public static DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portlet.asset.model.impl.AssetTagStatsModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portlet.asset.model.impl.AssetTagStatsModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static AssetTagStats fetchAssetTagStats(long tagStatsId) {
		return getService().fetchAssetTagStats(tagStatsId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the asset tag stats with the primary key.
	 *
	 * @param tagStatsId the primary key of the asset tag stats
	 * @return the asset tag stats
	 * @throws PortalException if a asset tag stats with the primary key could not be found
	 */
	public static AssetTagStats getAssetTagStats(long tagStatsId)
		throws PortalException {

		return getService().getAssetTagStats(tagStatsId);
	}

	/**
	 * Returns a range of all the asset tag statses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portlet.asset.model.impl.AssetTagStatsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset tag statses
	 * @param end the upper bound of the range of asset tag statses (not inclusive)
	 * @return the range of asset tag statses
	 */
	public static List<AssetTagStats> getAssetTagStatses(int start, int end) {
		return getService().getAssetTagStatses(start, end);
	}

	/**
	 * Returns the number of asset tag statses.
	 *
	 * @return the number of asset tag statses
	 */
	public static int getAssetTagStatsesCount() {
		return getService().getAssetTagStatsesCount();
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
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
	 * @param classNameId the asset entry's class name ID
	 * @param start the lower bound of the range of results
	 * @param end the upper bound of the range of results (not inclusive)
	 * @return the range of asset tag statistics associated with the asset entry
	 matching the class name ID
	 */
	public static List<AssetTagStats> getTagStats(
		long classNameId, int start, int end) {

		return getService().getTagStats(classNameId, start, end);
	}

	/**
	 * Returns the asset tag statistics instance with the tag and asset entry
	 * matching the class name ID
	 *
	 * @param tagId the primary key of the tag
	 * @param classNameId the asset entry's class name ID
	 * @return Returns the asset tag statistics instance with the tag and asset
	 entry  matching the class name ID
	 */
	public static AssetTagStats getTagStats(long tagId, long classNameId) {
		return getService().getTagStats(tagId, classNameId);
	}

	/**
	 * Updates the asset tag stats in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AssetTagStatsLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param assetTagStats the asset tag stats
	 * @return the asset tag stats that was updated
	 */
	public static AssetTagStats updateAssetTagStats(
		AssetTagStats assetTagStats) {

		return getService().updateAssetTagStats(assetTagStats);
	}

	/**
	 * Updates the asset tag statistics instance.
	 *
	 * @param tagId the primary key of the tag
	 * @param classNameId the asset entry's class name ID
	 * @return the updated asset tag statistics instance
	 */
	public static AssetTagStats updateTagStats(long tagId, long classNameId)
		throws PortalException {

		return getService().updateTagStats(tagId, classNameId);
	}

	public static AssetTagStatsLocalService getService() {
		return _service;
	}

	public static void setService(AssetTagStatsLocalService service) {
		_service = service;
	}

	private static volatile AssetTagStatsLocalService _service;

}