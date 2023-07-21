/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.display.internal.contributor;

import com.liferay.asset.display.contributor.AssetDisplayContributor;
import com.liferay.asset.display.contributor.AssetDisplayField;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.ClassType;
import com.liferay.info.display.contributor.InfoDisplayContributor;
import com.liferay.info.display.contributor.InfoDisplayField;
import com.liferay.info.display.contributor.InfoDisplayObjectProvider;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * @author Jürgen Kappler
 */
public class AssetInfoDisplayContributorAdapter
	implements InfoDisplayContributor<AssetEntry> {

	public AssetInfoDisplayContributorAdapter(
		AssetDisplayContributor assetDisplayContributor) {

		_assetDisplayContributor = assetDisplayContributor;
	}

	@Override
	public String getClassName() {
		return _assetDisplayContributor.getClassName();
	}

	@Override
	public List<InfoDisplayField> getClassTypeInfoDisplayFields(
			long classTypeId, Locale locale)
		throws PortalException {

		List<InfoDisplayField> infoDisplayFields = new ArrayList<>();

		List<AssetDisplayField> assetDisplayFields =
			_assetDisplayContributor.getClassTypeFields(classTypeId, locale);

		for (AssetDisplayField assetDisplayField : assetDisplayFields) {
			InfoDisplayField infoDisplayField = new InfoDisplayField(
				assetDisplayField.getKey(), assetDisplayField.getLabel(),
				assetDisplayField.getType());

			infoDisplayFields.add(infoDisplayField);
		}

		return infoDisplayFields;
	}

	@Override
	public List<ClassType> getClassTypes(long groupId, Locale locale)
		throws PortalException {

		return _assetDisplayContributor.getClassTypes(groupId, locale);
	}

	@Override
	public Set<InfoDisplayField> getInfoDisplayFields(
			long classTypeId, Locale locale)
		throws PortalException {

		Set<InfoDisplayField> infoDisplayFields = new HashSet<>();

		Set<AssetDisplayField> assetDisplayFields =
			_assetDisplayContributor.getAssetDisplayFields(classTypeId, locale);

		for (AssetDisplayField assetDisplayField : assetDisplayFields) {
			InfoDisplayField infoDisplayField = new InfoDisplayField(
				assetDisplayField.getKey(), assetDisplayField.getLabel(),
				assetDisplayField.getType());

			infoDisplayFields.add(infoDisplayField);
		}

		return infoDisplayFields;
	}

	@Override
	public Map<String, Object> getInfoDisplayFieldsValues(
			AssetEntry assetEntry, Locale locale)
		throws PortalException {

		return _assetDisplayContributor.getAssetDisplayFieldsValues(
			assetEntry, locale);
	}

	@Override
	public Object getInfoDisplayFieldValue(
			AssetEntry assetEntry, String fieldName, Locale locale)
		throws PortalException {

		return _assetDisplayContributor.getAssetDisplayFieldValue(
			assetEntry, fieldName, locale);
	}

	@Override
	public InfoDisplayObjectProvider getInfoDisplayObjectProvider(long classPK)
		throws PortalException {

		return _assetDisplayContributor.getInfoDisplayObjectProvider(classPK);
	}

	@Override
	public InfoDisplayObjectProvider<AssetEntry> getInfoDisplayObjectProvider(
			long groupId, String urlTitle)
		throws PortalException {

		return _assetDisplayContributor.getInfoDisplayObjectProvider(
			groupId, urlTitle);
	}

	@Override
	public String getInfoURLSeparator() {
		return _assetDisplayContributor.getAssetURLSeparator();
	}

	@Override
	public String getLabel(Locale locale) {
		return _assetDisplayContributor.getLabel(locale);
	}

	@Override
	public Map<String, Object> getVersionInfoDisplayFieldsValues(
			AssetEntry assetEntry, long versionClassPK, Locale locale)
		throws PortalException {

		return _assetDisplayContributor.getAssetDisplayFieldsValues(
			assetEntry, versionClassPK, locale);
	}

	private final AssetDisplayContributor _assetDisplayContributor;

}