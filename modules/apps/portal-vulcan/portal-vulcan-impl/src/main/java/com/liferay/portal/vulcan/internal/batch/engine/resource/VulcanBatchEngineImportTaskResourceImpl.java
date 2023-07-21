/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.vulcan.internal.batch.engine.resource;

import com.liferay.headless.batch.engine.resource.v1_0.ImportTaskResource;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.batch.engine.resource.VulcanBatchEngineImportTaskResource;

import javax.servlet.http.HttpServletRequest;

import javax.ws.rs.core.UriInfo;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Javier Gamarra
 */
@Component(service = VulcanBatchEngineImportTaskResource.class)
public class VulcanBatchEngineImportTaskResourceImpl
	implements VulcanBatchEngineImportTaskResource {

	public Object deleteImportTask(
			String name, String callbackURL, Object object)
		throws Exception {

		_initializeContext();

		return _importTaskResource.deleteImportTask(
			name, callbackURL, null, object);
	}

	public Object postImportTask(
			String name, String callbackURL, String fields, Object object)
		throws Exception {

		_initializeContext();

		return _importTaskResource.postImportTask(
			name, callbackURL, fields, null, object);
	}

	public Object putImportTask(String name, String callbackURL, Object object)
		throws Exception {

		_initializeContext();

		return _importTaskResource.putImportTask(
			name, callbackURL, null, object);
	}

	@Override
	public void setContextAcceptLanguage(AcceptLanguage contextAcceptLanguage) {
		_contextAcceptLanguage = contextAcceptLanguage;
	}

	@Override
	public void setContextCompany(Company contextCompany) {
		_contextCompany = contextCompany;
	}

	@Override
	public void setContextHttpServletRequest(
		HttpServletRequest contextHttpServletRequest) {

		_contextHttpServletRequest = contextHttpServletRequest;
	}

	@Override
	public void setContextUriInfo(UriInfo contextUriInfo) {
		_contextUriInfo = contextUriInfo;
	}

	@Override
	public void setContextUser(User contextUser) {
		_contextUser = contextUser;
	}

	private void _initializeContext() {
		_importTaskResource.setContextAcceptLanguage(_contextAcceptLanguage);
		_importTaskResource.setContextCompany(_contextCompany);
		_importTaskResource.setContextHttpServletRequest(
			_contextHttpServletRequest);
		_importTaskResource.setContextUriInfo(_contextUriInfo);
		_importTaskResource.setContextUser(_contextUser);
	}

	private AcceptLanguage _contextAcceptLanguage;
	private Company _contextCompany;
	private HttpServletRequest _contextHttpServletRequest;
	private UriInfo _contextUriInfo;
	private User _contextUser;

	@Reference
	private ImportTaskResource _importTaskResource;

}