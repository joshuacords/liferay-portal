/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.media;

import com.liferay.osgi.util.ServiceTrackerFactory;
import com.liferay.portal.kernel.exception.PortalException;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * @author Alec Sloan
 */
public class CommerceMediaResolverUtil {

	public static String getDefaultUrl(long groupId) {
		CommerceMediaResolver commerceMediaResolver =
			_serviceTracker.getService();

		return commerceMediaResolver.getDefaultUrl(groupId);
	}

	public static String getDownloadUrl(long cpAttachmentFileEntryId)
		throws PortalException {

		CommerceMediaResolver commerceMediaResolver =
			_serviceTracker.getService();

		return commerceMediaResolver.getDownloadUrl(cpAttachmentFileEntryId);
	}

	public static String getDownloadURL(
			long commerceAccountId, long cpAttachmentFileEntryId)
		throws PortalException {

		CommerceMediaResolver commerceMediaResolver =
			_serviceTracker.getService();

		return commerceMediaResolver.getDownloadURL(
			commerceAccountId, cpAttachmentFileEntryId);
	}

	public static byte[] getMediaBytes(HttpServletRequest httpServletRequest)
		throws IOException, PortalException {

		CommerceMediaResolver commerceMediaResolver =
			_serviceTracker.getService();

		return commerceMediaResolver.getMediaBytes(httpServletRequest);
	}

	public static String getThumbnailUrl(long cpAttachmentFileEntryId)
		throws PortalException {

		CommerceMediaResolver commerceMediaResolver =
			_serviceTracker.getService();

		return commerceMediaResolver.getThumbnailUrl(cpAttachmentFileEntryId);
	}

	public static String getThumbnailURL(
			long commerceAccountId, long cpAttachmentFileEntryId)
		throws PortalException {

		CommerceMediaResolver commerceMediaResolver =
			_serviceTracker.getService();

		return commerceMediaResolver.getThumbnailURL(
			commerceAccountId, cpAttachmentFileEntryId);
	}

	public static String getUrl(long cpAttachmentFileEntryId)
		throws PortalException {

		CommerceMediaResolver commerceMediaResolver =
			_serviceTracker.getService();

		return commerceMediaResolver.getUrl(cpAttachmentFileEntryId);
	}

	public static String getURL(
			long commerceAccountId, long cpAttachmentFileEntryId)
		throws PortalException {

		CommerceMediaResolver commerceMediaResolver =
			_serviceTracker.getService();

		return commerceMediaResolver.getURL(
			commerceAccountId, cpAttachmentFileEntryId);
	}

	public static void sendMediaBytes(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException, PortalException {

		CommerceMediaResolver commerceMediaResolver =
			_serviceTracker.getService();

		commerceMediaResolver.sendMediaBytes(
			httpServletRequest, httpServletResponse);
	}

	public static void sendMediaBytes(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, String download)
		throws IOException, PortalException {

		CommerceMediaResolver commerceMediaResolver =
			_serviceTracker.getService();

		commerceMediaResolver.sendMediaBytes(
			httpServletRequest, httpServletResponse, download);
	}

	private static final ServiceTracker<?, CommerceMediaResolver>
		_serviceTracker = ServiceTrackerFactory.open(
			FrameworkUtil.getBundle(CommerceMediaResolverUtil.class),
			CommerceMediaResolver.class);

}