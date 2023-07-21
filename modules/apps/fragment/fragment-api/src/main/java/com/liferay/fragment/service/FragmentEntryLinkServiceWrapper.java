/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link FragmentEntryLinkService}.
 *
 * @author Brian Wing Shun Chan
 * @see FragmentEntryLinkService
 * @generated
 */
public class FragmentEntryLinkServiceWrapper
	implements FragmentEntryLinkService,
			   ServiceWrapper<FragmentEntryLinkService> {

	public FragmentEntryLinkServiceWrapper(
		FragmentEntryLinkService fragmentEntryLinkService) {

		_fragmentEntryLinkService = fragmentEntryLinkService;
	}

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 #addFragmentEntryLink(long, long, long, long, long, String,
	 String, String, String, String, String, int, String,
	 ServiceContext)}
	 */
	@Deprecated
	@Override
	public com.liferay.fragment.model.FragmentEntryLink addFragmentEntryLink(
			long groupId, long originalFragmentEntryLinkId,
			long fragmentEntryId, long classNameId, long classPK, String css,
			String html, String js, String editableValues, String namespace,
			int position, String rendererKey,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fragmentEntryLinkService.addFragmentEntryLink(
			groupId, originalFragmentEntryLinkId, fragmentEntryId, classNameId,
			classPK, css, html, js, editableValues, namespace, position,
			rendererKey, serviceContext);
	}

	@Override
	public com.liferay.fragment.model.FragmentEntryLink addFragmentEntryLink(
			long groupId, long originalFragmentEntryLinkId,
			long fragmentEntryId, long classNameId, long classPK, String css,
			String html, String js, String configuration, String editableValues,
			String namespace, int position, String rendererKey,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fragmentEntryLinkService.addFragmentEntryLink(
			groupId, originalFragmentEntryLinkId, fragmentEntryId, classNameId,
			classPK, css, html, js, configuration, editableValues, namespace,
			position, rendererKey, serviceContext);
	}

	@Override
	public com.liferay.fragment.model.FragmentEntryLink deleteFragmentEntryLink(
			long fragmentEntryLinkId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fragmentEntryLinkService.deleteFragmentEntryLink(
			fragmentEntryLinkId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _fragmentEntryLinkService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.fragment.model.FragmentEntryLink updateFragmentEntryLink(
			long fragmentEntryLinkId, String editableValues)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fragmentEntryLinkService.updateFragmentEntryLink(
			fragmentEntryLinkId, editableValues);
	}

	@Override
	public com.liferay.fragment.model.FragmentEntryLink updateFragmentEntryLink(
			long fragmentEntryLinkId, String editableValues,
			boolean updateClassedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fragmentEntryLinkService.updateFragmentEntryLink(
			fragmentEntryLinkId, editableValues, updateClassedModel);
	}

	@Override
	public void updateFragmentEntryLinks(
			long groupId, long classNameId, long classPK,
			long[] fragmentEntryIds, String editableValues,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		_fragmentEntryLinkService.updateFragmentEntryLinks(
			groupId, classNameId, classPK, fragmentEntryIds, editableValues,
			serviceContext);
	}

	@Override
	public void updateFragmentEntryLinks(
			java.util.Map<Long, String> fragmentEntryLinksEditableValuesMap)
		throws com.liferay.portal.kernel.exception.PortalException {

		_fragmentEntryLinkService.updateFragmentEntryLinks(
			fragmentEntryLinksEditableValuesMap);
	}

	@Override
	public FragmentEntryLinkService getWrappedService() {
		return _fragmentEntryLinkService;
	}

	@Override
	public void setWrappedService(
		FragmentEntryLinkService fragmentEntryLinkService) {

		_fragmentEntryLinkService = fragmentEntryLinkService;
	}

	private FragmentEntryLinkService _fragmentEntryLinkService;

}