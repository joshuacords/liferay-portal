/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.trash.internal.service;

import com.liferay.petra.model.adapter.util.ModelAdapterUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.trash.kernel.model.TrashVersion;
import com.liferay.trash.kernel.service.TrashVersionLocalServiceWrapper;
import com.liferay.trash.service.TrashVersionLocalService;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(immediate = true, service = ServiceWrapper.class)
public class ModularTrashVersionLocalServiceWrapper
	extends TrashVersionLocalServiceWrapper {

	public ModularTrashVersionLocalServiceWrapper() {
		super(null);
	}

	public ModularTrashVersionLocalServiceWrapper(
		com.liferay.trash.kernel.service.TrashVersionLocalService
			trashVersionLocalService) {

		super(trashVersionLocalService);
	}

	@Override
	public TrashVersion addTrashVersion(
		long trashEntryId, String className, long classPK, int status,
		UnicodeProperties typeSettingsProperties) {

		return ModelAdapterUtil.adapt(
			TrashVersion.class,
			_trashVersionLocalService.addTrashVersion(
				trashEntryId, className, classPK, status,
				typeSettingsProperties));
	}

	@Override
	public TrashVersion addTrashVersion(TrashVersion trashVersion) {
		return ModelAdapterUtil.adapt(
			TrashVersion.class,
			_trashVersionLocalService.addTrashVersion(
				ModelAdapterUtil.adapt(
					com.liferay.trash.model.TrashVersion.class, trashVersion)));
	}

	@Override
	public TrashVersion createTrashVersion(long versionId) {
		return ModelAdapterUtil.adapt(
			TrashVersion.class,
			_trashVersionLocalService.createTrashVersion(versionId));
	}

	@Override
	public TrashVersion deleteTrashVersion(long versionId)
		throws PortalException {

		com.liferay.trash.model.TrashVersion trashVersion =
			_trashVersionLocalService.deleteTrashVersion(versionId);

		if (trashVersion == null) {
			return null;
		}

		return ModelAdapterUtil.adapt(TrashVersion.class, trashVersion);
	}

	@Override
	public TrashVersion deleteTrashVersion(String className, long classPK) {
		com.liferay.trash.model.TrashVersion trashVersion =
			_trashVersionLocalService.deleteTrashVersion(className, classPK);

		if (trashVersion == null) {
			return null;
		}

		return ModelAdapterUtil.adapt(TrashVersion.class, trashVersion);
	}

	@Override
	public TrashVersion deleteTrashVersion(TrashVersion trashVersion) {
		com.liferay.trash.model.TrashVersion deleteTrashVersion =
			_trashVersionLocalService.deleteTrashVersion(
				ModelAdapterUtil.adapt(
					com.liferay.trash.model.TrashVersion.class, trashVersion));

		if (deleteTrashVersion == null) {
			return null;
		}

		return ModelAdapterUtil.adapt(TrashVersion.class, deleteTrashVersion);
	}

	@Override
	public TrashVersion fetchTrashVersion(long versionId) {
		com.liferay.trash.model.TrashVersion trashVersion =
			_trashVersionLocalService.fetchTrashVersion(versionId);

		if (trashVersion == null) {
			return null;
		}

		return ModelAdapterUtil.adapt(TrashVersion.class, trashVersion);
	}

	@Override
	public TrashVersion fetchVersion(String className, long classPK) {
		com.liferay.trash.model.TrashVersion trashVersion =
			_trashVersionLocalService.fetchVersion(className, classPK);

		if (trashVersion == null) {
			return null;
		}

		return ModelAdapterUtil.adapt(TrashVersion.class, trashVersion);
	}

	@Override
	public String getOSGiServiceIdentifier() {
		return _trashVersionLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public TrashVersion getTrashVersion(long versionId) throws PortalException {
		return ModelAdapterUtil.adapt(
			TrashVersion.class,
			_trashVersionLocalService.getTrashVersion(versionId));
	}

	@Override
	public List<TrashVersion> getTrashVersions(int start, int end) {
		return ModelAdapterUtil.adapt(
			TrashVersion.class,
			_trashVersionLocalService.getTrashVersions(start, end));
	}

	@Override
	public int getTrashVersionsCount() {
		return _trashVersionLocalService.getTrashVersionsCount();
	}

	@Override
	public List<TrashVersion> getVersions(long entryId) {
		return ModelAdapterUtil.adapt(
			TrashVersion.class, _trashVersionLocalService.getVersions(entryId));
	}

	@Override
	public List<TrashVersion> getVersions(long entryId, String className) {
		return ModelAdapterUtil.adapt(
			TrashVersion.class,
			_trashVersionLocalService.getVersions(entryId, className));
	}

	@Override
	public TrashVersion updateTrashVersion(TrashVersion trashVersion) {
		return ModelAdapterUtil.adapt(
			TrashVersion.class,
			_trashVersionLocalService.updateTrashVersion(
				ModelAdapterUtil.adapt(
					com.liferay.trash.model.TrashVersion.class, trashVersion)));
	}

	@Reference(unbind = "-")
	protected void setTrashVersionLocalService(
		TrashVersionLocalService trashVersionLocalService) {

		_trashVersionLocalService = trashVersionLocalService;
	}

	private TrashVersionLocalService _trashVersionLocalService;

}