/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.portlet.action;

import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.osb.provisioning.license.exporter.LicenseKeyExporter;
import com.liferay.osb.provisioning.license.model.LicenseKey;
import com.liferay.osb.provisioning.license.service.LicenseKeyService;
import com.liferay.osb.provisioning.license.util.LicenseUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Yuanyuan Huang
 */
@Component(
	property = {
		"javax.portlet.name=" + ProvisioningPortletKeys.ACCOUNTS,
		"javax.portlet.name=" + ProvisioningPortletKeys.LICENSES,
		"mvc.command.name=/accounts/download_license_keys",
		"mvc.command.name=/licenses/download_license_key"
	},
	service = MVCResourceCommand.class
)
public class DownloadLicenseKeyMVCResourceCommand
	extends BaseMVCResourceCommand {

	@Override
	public void doServeResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws PortletException {

		try {
			long[] licenseKeyIds = ParamUtil.getLongValues(
				resourceRequest, "licenseKeyIds");

			if (licenseKeyIds.length > 1) {
				downloadAggregateLicenseKey(
					resourceRequest, resourceResponse, licenseKeyIds);
			}
			else if (licenseKeyIds.length == 1) {
				downloadLicenseKey(
					resourceRequest, resourceResponse, licenseKeyIds[0]);
			}
			else {
				long licenseKeyId = ParamUtil.getLong(
					resourceRequest, "licenseKeyId");

				downloadLicenseKey(
					resourceRequest, resourceResponse, licenseKeyId);
			}
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}
	}

	protected void downloadAggregateLicenseKey(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse,
			long[] licenseKeyIds)
		throws Exception {

		List<LicenseKey> licenseKeys = new ArrayList<>();

		for (long licenseKeyId : licenseKeyIds) {
			LicenseKey licenseKey = _licenseKeyService.getLicenseKey(
				licenseKeyId);

			if (!licenseKey.isActive()) {
				continue;
			}

			licenseKeys.add(licenseKey);
		}

		if (LicenseUtil.isAggregate(licenseKeys)) {
			String[] hostNames = new String[licenseKeys.size()];
			String[] ipAddresses = new String[licenseKeys.size()];
			String[] macAddresses = new String[licenseKeys.size()];
			String[] serverIds = new String[licenseKeys.size()];

			for (int i = 0; i < licenseKeys.size(); i++) {
				LicenseKey licenseKey = licenseKeys.get(i);

				hostNames[i] = licenseKey.getHostName();
				ipAddresses[i] = licenseKey.getIpAddresses();
				macAddresses[i] = licenseKey.getMacAddresses();
				serverIds[i] = licenseKey.getServerId();
			}

			LicenseKey licenseKey = licenseKeys.get(0);

			String fileName = _licenseKeyExporter.getFileName(
				licenseKey.getProductName(), licenseKey.getProductVersion(),
				licenseKey.getName());

			String licenseXML = _licenseKeyExporter.toXML(
				licenseKey.getAccountName(), licenseKey.getLicenseEntryName(),
				licenseKey.getLicenseEntryType(),
				licenseKey.getLicenseVersion(), licenseKey.getProductName(),
				licenseKey.getProductId(), licenseKey.getProductVersion(),
				licenseKey.getOwner(), licenseKey.getMaxClusterNodes(),
				licenseKey.getMaxServers(), licenseKey.getMaxHttpSessions(),
				licenseKey.getMaxConcurrentUsers(), licenseKey.getMaxUsers(),
				licenseKey.getSizing(), licenseKey.getDescription(), hostNames,
				ipAddresses, macAddresses, serverIds, licenseKey.getStartDate(),
				licenseKey.getExpirationDate(), licenseKey.getCreateDate());

			PortletResponseUtil.sendFile(
				resourceRequest, resourceResponse, fileName,
				licenseXML.getBytes(), ContentTypes.TEXT_XML);
		}
		else {
			Set<String> names = new HashSet<>();
			Set<String> productNames = new HashSet<>();

			String[] licenseXMLs = new String[licenseKeys.size()];

			for (int i = 0; i < licenseKeys.size(); i++) {
				LicenseKey licenseKey = licenseKeys.get(i);

				names.add(licenseKey.getName());
				productNames.add(licenseKey.getProductName());

				licenseXMLs[i] = _licenseKeyExporter.toXML(
					licenseKey.getKey(), licenseKey.getAccountName(),
					licenseKey.getLicenseEntryName(),
					licenseKey.getLicenseEntryType(),
					licenseKey.getLicenseVersion(), licenseKey.getProductName(),
					licenseKey.getProductId(), licenseKey.getProductVersion(),
					licenseKey.getOwner(), licenseKey.getMaxClusterNodes(),
					licenseKey.getMaxServers(), licenseKey.getMaxHttpSessions(),
					licenseKey.getMaxConcurrentUsers(),
					licenseKey.getMaxUsers(), licenseKey.getSizing(),
					licenseKey.getDescription(), licenseKey.getHostName(),
					licenseKey.getIpAddresses(), licenseKey.getMacAddresses(),
					licenseKey.getServerId(), licenseKey.getStartDate(),
					licenseKey.getExpirationDate(), licenseKey.getCreateDate());
			}

			String fileName = _licenseKeyExporter.getFileName(
				ArrayUtil.toStringArray(productNames),
				ArrayUtil.toStringArray(names));

			String licenseXML = _licenseKeyExporter.aggregateXMLs(licenseXMLs);

			PortletResponseUtil.sendFile(
				resourceRequest, resourceResponse, fileName,
				licenseXML.getBytes(), ContentTypes.TEXT_XML);
		}
	}

	protected void downloadLicenseKey(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse,
			long licenseKeyId)
		throws Exception {

		LicenseKey licenseKey = _licenseKeyService.getLicenseKey(licenseKeyId);

		if (licenseKey.getLicenseVersion() == 1) {
			String encodedLicenseFile =
				_licenseKeyExporter.toEncodedLicenseFile(
					licenseKey.getServerId(), licenseKey.getKey());

			PortletResponseUtil.sendFile(
				resourceRequest, resourceResponse, "license",
				encodedLicenseFile.getBytes(),
				ContentTypes.APPLICATION_OCTET_STREAM);
		}
		else if (licenseKey.getLicenseVersion() >= 2) {
			String fileName = _licenseKeyExporter.getFileName(
				licenseKey.getProductName(), licenseKey.getProductVersion(),
				licenseKey.getName());

			String licenseXML = _licenseKeyExporter.toXML(
				licenseKey.getKey(), licenseKey.getAccountName(),
				licenseKey.getLicenseEntryName(),
				licenseKey.getLicenseEntryType(),
				licenseKey.getLicenseVersion(), licenseKey.getProductName(),
				licenseKey.getProductId(), licenseKey.getProductVersion(),
				licenseKey.getOwner(), licenseKey.getMaxClusterNodes(),
				licenseKey.getMaxServers(), licenseKey.getMaxHttpSessions(),
				licenseKey.getMaxConcurrentUsers(), licenseKey.getMaxUsers(),
				licenseKey.getSizing(), licenseKey.getDescription(),
				licenseKey.getHostName(), licenseKey.getIpAddresses(),
				licenseKey.getMacAddresses(), licenseKey.getServerId(),
				licenseKey.getStartDate(), licenseKey.getExpirationDate(),
				licenseKey.getCreateDate());

			PortletResponseUtil.sendFile(
				resourceRequest, resourceResponse, fileName,
				licenseXML.getBytes(), ContentTypes.TEXT_XML);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DownloadLicenseKeyMVCResourceCommand.class);

	@Reference
	private LicenseKeyExporter _licenseKeyExporter;

	@Reference
	private LicenseKeyService _licenseKeyService;

}