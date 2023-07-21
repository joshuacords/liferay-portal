/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.vulcan.internal.jaxrs.param.converter.provider;

import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.util.GetterUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import java.util.Optional;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;

/**
 * @author Javier Gamarra
 */
@Provider
public class SiteParamConverterProvider
	implements ParamConverter<Long>, ParamConverterProvider {

	public SiteParamConverterProvider(GroupLocalService groupLocalService) {
		_groupLocalService = groupLocalService;
	}

	@Override
	public Long fromString(String parameter) {
		MultivaluedMap<String, String> multivaluedMap =
			_uriInfo.getPathParameters();

		Long siteId = getGroupId(
			_company.getCompanyId(), multivaluedMap.getFirst("siteId"));

		if (siteId != null) {
			return siteId;
		}

		throw new NotFoundException(
			"Unable to get a valid site with ID " + parameter);
	}

	@Override
	public <T> ParamConverter<T> getConverter(
		Class<T> clazz, Type type, Annotation[] annotations) {

		if (Long.class.equals(clazz) && _hasSiteIdAnnotation(annotations)) {
			return (ParamConverter<T>)this;
		}

		return null;
	}

	public Long getGroupId(long companyId, String siteId) {
		if (siteId != null) {
			Group group = _groupLocalService.fetchGroup(companyId, siteId);

			if (group == null) {
				group = _groupLocalService.fetchGroup(
					GetterUtil.getLong(siteId));
			}

			Group liveGroup = Optional.ofNullable(
				group
			).map(
				Group::getLiveGroup
			).orElse(
				null
			);

			if (((group != null) && group.isSite()) ||
				((liveGroup != null) && liveGroup.isSite())) {

				return group.getGroupId();
			}
		}

		return null;
	}

	@Override
	public String toString(Long parameter) {
		return String.valueOf(parameter);
	}

	private boolean _hasSiteIdAnnotation(Annotation[] annotations) {
		for (Annotation annotation : annotations) {
			String annotationString = annotation.toString();

			if (annotationString.equals(
					"@javax.ws.rs.PathParam(value=siteId)")) {

				return true;
			}
		}

		return false;
	}

	@Context
	private Company _company;

	private final GroupLocalService _groupLocalService;

	@Context
	private UriInfo _uriInfo;

}