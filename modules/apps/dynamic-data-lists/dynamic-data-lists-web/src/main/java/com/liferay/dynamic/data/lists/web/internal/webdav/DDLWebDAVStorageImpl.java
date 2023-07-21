/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.lists.web.internal.webdav;

import com.liferay.dynamic.data.lists.constants.DDLPortletKeys;
import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalService;
import com.liferay.dynamic.data.mapping.webdav.DDMWebDAV;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.webdav.BaseWebDAVStorageImpl;
import com.liferay.portal.kernel.webdav.Resource;
import com.liferay.portal.kernel.webdav.WebDAVException;
import com.liferay.portal.kernel.webdav.WebDAVRequest;
import com.liferay.portal.kernel.webdav.WebDAVStorage;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Juan Fernández
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + DDLPortletKeys.DYNAMIC_DATA_LISTS,
		"webdav.storage.token=dynamic_data_lists"
	},
	service = WebDAVStorage.class
)
public class DDLWebDAVStorageImpl extends BaseWebDAVStorageImpl {

	@Override
	public int deleteResource(WebDAVRequest webDAVRequest)
		throws WebDAVException {

		return _ddmWebDAV.deleteResource(
			webDAVRequest, getRootPath(), getToken(),
			_portal.getClassNameId(DDLRecordSet.class));
	}

	@Override
	public Resource getResource(WebDAVRequest webDAVRequest)
		throws WebDAVException {

		return _ddmWebDAV.getResource(
			webDAVRequest, getRootPath(), getToken(),
			_portal.getClassNameId(DDLRecordSet.class));
	}

	@Override
	public List<Resource> getResources(WebDAVRequest webDAVRequest)
		throws WebDAVException {

		try {
			String[] pathArray = webDAVRequest.getPathArray();

			if (pathArray.length == 2) {
				return getFolders(webDAVRequest);
			}
			else if (pathArray.length == 3) {
				String type = pathArray[2];

				if (type.equals(DDMWebDAV.TYPE_STRUCTURES)) {
					return getStructures(webDAVRequest);
				}
				else if (type.equals(DDMWebDAV.TYPE_TEMPLATES)) {
					return getTemplates(webDAVRequest);
				}
			}

			return new ArrayList<>();
		}
		catch (Exception exception) {
			throw new WebDAVException(exception);
		}
	}

	@Override
	public int putResource(WebDAVRequest webDAVRequest) throws WebDAVException {
		return _ddmWebDAV.putResource(
			webDAVRequest, getRootPath(), getToken(),
			_portal.getClassNameId(DDLRecordSet.class));
	}

	protected List<Resource> getFolders(WebDAVRequest webDAVRequest)
		throws Exception {

		return ListUtil.fromArray(
			_ddmWebDAV.toResource(
				webDAVRequest, DDMWebDAV.TYPE_STRUCTURES, getRootPath(), true),
			_ddmWebDAV.toResource(
				webDAVRequest, DDMWebDAV.TYPE_TEMPLATES, getRootPath(), true));
	}

	protected List<Resource> getStructures(WebDAVRequest webDAVRequest)
		throws Exception {

		List<Resource> resources = new ArrayList<>();

		List<DDMStructure> ddmStructures =
			_ddmStructureLocalService.getStructures(
				webDAVRequest.getGroupId(),
				_portal.getClassNameId(DDLRecordSet.class));

		for (DDMStructure ddmStructure : ddmStructures) {
			Resource resource = _ddmWebDAV.toResource(
				webDAVRequest, ddmStructure, getRootPath(), true);

			resources.add(resource);
		}

		return resources;
	}

	protected List<Resource> getTemplates(WebDAVRequest webDAVRequest)
		throws Exception {

		List<Resource> resources = new ArrayList<>();

		List<DDMTemplate> ddmTemplates =
			_ddmTemplateLocalService.getTemplatesByStructureClassNameId(
				webDAVRequest.getGroupId(),
				_portal.getClassNameId(DDLRecordSet.class),
				WorkflowConstants.STATUS_APPROVED, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null);

		for (DDMTemplate ddmTemplate : ddmTemplates) {
			Resource resource = _ddmWebDAV.toResource(
				webDAVRequest, ddmTemplate, getRootPath(), true);

			resources.add(resource);
		}

		return resources;
	}

	@Reference(unbind = "-")
	protected void setDDMStructureLocalService(
		DDMStructureLocalService ddmStructureLocalService) {

		_ddmStructureLocalService = ddmStructureLocalService;
	}

	@Reference(unbind = "-")
	protected void setDDMTemplateLocalService(
		DDMTemplateLocalService ddmTemplateLocalService) {

		_ddmTemplateLocalService = ddmTemplateLocalService;
	}

	@Reference(unbind = "-")
	protected void setDDMWebDav(DDMWebDAV ddmWebDAV) {
		_ddmWebDAV = ddmWebDAV;
	}

	private DDMStructureLocalService _ddmStructureLocalService;
	private DDMTemplateLocalService _ddmTemplateLocalService;
	private DDMWebDAV _ddmWebDAV;

	@Reference
	private Portal _portal;

}