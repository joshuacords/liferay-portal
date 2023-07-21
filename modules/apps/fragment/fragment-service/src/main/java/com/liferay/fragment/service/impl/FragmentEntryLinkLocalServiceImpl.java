/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.service.impl;

import com.liferay.document.library.util.DLURLHelper;
import com.liferay.fragment.constants.FragmentEntryLinkConstants;
import com.liferay.fragment.model.FragmentCollection;
import com.liferay.fragment.model.FragmentEntry;
import com.liferay.fragment.model.FragmentEntryLink;
import com.liferay.fragment.processor.DefaultFragmentEntryProcessorContext;
import com.liferay.fragment.processor.FragmentEntryProcessorContext;
import com.liferay.fragment.processor.FragmentEntryProcessorRegistry;
import com.liferay.fragment.service.base.FragmentEntryLinkLocalServiceBaseImpl;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(
	property = "model.class.name=com.liferay.fragment.model.FragmentEntryLink",
	service = AopService.class
)
public class FragmentEntryLinkLocalServiceImpl
	extends FragmentEntryLinkLocalServiceBaseImpl {

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 *             #addFragmentEntryLink(long, long, long, long, long, long,
	 *             String, String, String, String, String, String, int, String,
	 *             ServiceContext)}
	 */
	@Deprecated
	@Override
	public FragmentEntryLink addFragmentEntryLink(
			long userId, long groupId, long originalFragmentEntryLinkId,
			long fragmentEntryId, long classNameId, long classPK,
			String rendererKey, ServiceContext serviceContext)
		throws PortalException {

		return addFragmentEntryLink(
			userId, groupId, originalFragmentEntryLinkId, fragmentEntryId,
			classNameId, classPK, StringPool.BLANK, StringPool.BLANK,
			StringPool.BLANK, StringPool.BLANK, StringPool.BLANK,
			StringPool.BLANK, 0, rendererKey, serviceContext);
	}

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 *             #addFragmentEntryLink(long, long, long, long, long, long,
	 *             String, String, String, String, String, String, int, String,
	 *             ServiceContext)}
	 */
	@Deprecated
	@Override
	public FragmentEntryLink addFragmentEntryLink(
			long userId, long groupId, long originalFragmentEntryLinkId,
			long fragmentEntryId, long classNameId, long classPK, String css,
			String html, String js, String editableValues, int position,
			ServiceContext serviceContext)
		throws PortalException {

		return addFragmentEntryLink(
			userId, groupId, originalFragmentEntryLinkId, fragmentEntryId,
			classNameId, classPK, css, html, js, StringPool.BLANK,
			editableValues, StringPool.BLANK, position, null, serviceContext);
	}

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 *             #addFragmentEntryLink(long, long, long, long, long, long,
	 *             String, String, String, String, String, String, int, String,
	 *             ServiceContext)}
	 */
	@Deprecated
	@Override
	public FragmentEntryLink addFragmentEntryLink(
			long userId, long groupId, long originalFragmentEntryLinkId,
			long fragmentEntryId, long classNameId, long classPK, String css,
			String html, String js, String editableValues, int position,
			String rendererKey, ServiceContext serviceContext)
		throws PortalException {

		return addFragmentEntryLink(
			userId, groupId, originalFragmentEntryLinkId, fragmentEntryId,
			classNameId, classPK, css, html, js, StringPool.BLANK,
			editableValues, StringPool.BLANK, position, rendererKey,
			serviceContext);
	}

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 *             #addFragmentEntryLink(long, long, long, long, long, long,
	 *             String, String, String, String, String, String, int, String,
	 *             ServiceContext)}
	 */
	@Deprecated
	@Override
	public FragmentEntryLink addFragmentEntryLink(
			long userId, long groupId, long originalFragmentEntryLinkId,
			long fragmentEntryId, long classNameId, long classPK, String css,
			String html, String js, String editableValues, String namespace,
			int position, String rendererKey, ServiceContext serviceContext)
		throws PortalException {

		return addFragmentEntryLink(
			userId, groupId, originalFragmentEntryLinkId, fragmentEntryId,
			classNameId, classPK, css, html, js, StringPool.BLANK,
			editableValues, namespace, position, rendererKey, serviceContext);
	}

	@Override
	public FragmentEntryLink addFragmentEntryLink(
			long userId, long groupId, long originalFragmentEntryLinkId,
			long fragmentEntryId, long classNameId, long classPK, String css,
			String html, String js, String configuration, String editableValues,
			String namespace, int position, String rendererKey,
			ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		_classNameLocalService.getClassName(classNameId);

		long fragmentEntryLinkId = counterLocalService.increment();

		FragmentEntryLink fragmentEntryLink =
			fragmentEntryLinkPersistence.create(fragmentEntryLinkId);

		fragmentEntryLink.setUuid(serviceContext.getUuid());
		fragmentEntryLink.setGroupId(groupId);
		fragmentEntryLink.setCompanyId(user.getCompanyId());
		fragmentEntryLink.setUserId(user.getUserId());
		fragmentEntryLink.setUserName(user.getFullName());
		fragmentEntryLink.setCreateDate(
			serviceContext.getCreateDate(new Date()));
		fragmentEntryLink.setModifiedDate(
			serviceContext.getModifiedDate(new Date()));
		fragmentEntryLink.setOriginalFragmentEntryLinkId(
			originalFragmentEntryLinkId);
		fragmentEntryLink.setFragmentEntryId(fragmentEntryId);
		fragmentEntryLink.setClassNameId(classNameId);
		fragmentEntryLink.setClassPK(classPK);
		fragmentEntryLink.setCss(css);

		html = _replaceResources(fragmentEntryId, html);

		fragmentEntryLink.setHtml(html);

		fragmentEntryLink.setJs(js);
		fragmentEntryLink.setConfiguration(configuration);

		String processedHTML = html;

		HttpServletRequest httpServletRequest = serviceContext.getRequest();
		HttpServletResponse httpServletResponse = serviceContext.getResponse();

		if ((httpServletRequest != null) && (httpServletResponse != null)) {
			FragmentEntryProcessorContext fragmentEntryProcessorContext =
				new DefaultFragmentEntryProcessorContext(
					httpServletRequest, httpServletResponse,
					FragmentEntryLinkConstants.EDIT,
					LocaleUtil.getMostRelevantLocale());

			processedHTML =
				_fragmentEntryProcessorRegistry.processFragmentEntryLinkHTML(
					fragmentEntryLink, fragmentEntryProcessorContext);
		}

		if (Validator.isNull(editableValues)) {
			editableValues = String.valueOf(
				_fragmentEntryProcessorRegistry.
					getDefaultEditableValuesJSONObject(
						processedHTML, configuration));
		}

		fragmentEntryLink.setEditableValues(editableValues);

		if (Validator.isNull(namespace)) {
			namespace = StringUtil.randomId();
		}

		fragmentEntryLink.setNamespace(namespace);

		fragmentEntryLink.setPosition(position);
		fragmentEntryLink.setRendererKey(rendererKey);
		fragmentEntryLink.setLastPropagationDate(
			serviceContext.getCreateDate(new Date()));

		return fragmentEntryLinkPersistence.update(fragmentEntryLink);
	}

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 *             #addFragmentEntryLink(long, long, long, long, long, long,
	 *             String, String, String, String, String, String, int, String,
	 *             ServiceContext)}
	 */
	@Deprecated
	@Override
	public FragmentEntryLink addFragmentEntryLink(
			long userId, long groupId, long fragmentEntryId, long classNameId,
			long classPK, String css, String html, String js,
			String editableValues, int position, ServiceContext serviceContext)
		throws PortalException {

		return addFragmentEntryLink(
			userId, groupId, 0, fragmentEntryId, classNameId, classPK, css,
			html, js, StringPool.BLANK, editableValues, StringPool.BLANK,
			position, null, serviceContext);
	}

	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public FragmentEntryLink deleteFragmentEntryLink(
		FragmentEntryLink fragmentEntryLink) {

		// Fragment entry link

		fragmentEntryLinkPersistence.remove(fragmentEntryLink);

		// Fragment entry processor registry

		_fragmentEntryProcessorRegistry.deleteFragmentEntryLinkData(
			fragmentEntryLink);

		return fragmentEntryLink;
	}

	@Override
	public FragmentEntryLink deleteFragmentEntryLink(long fragmentEntryLinkId)
		throws PortalException {

		FragmentEntryLink fragmentEntryLink =
			fragmentEntryLinkPersistence.findByPrimaryKey(fragmentEntryLinkId);

		return fragmentEntryLinkLocalService.deleteFragmentEntryLink(
			fragmentEntryLink);
	}

	@Override
	public void deleteFragmentEntryLinks(long groupId) {
		List<FragmentEntryLink> fragmentEntryLinks =
			fragmentEntryLinkPersistence.findByGroupId(groupId);

		for (FragmentEntryLink fragmentEntryLink : fragmentEntryLinks) {
			fragmentEntryLinkLocalService.deleteFragmentEntryLink(
				fragmentEntryLink);
		}
	}

	@Override
	public void deleteFragmentEntryLinks(long[] fragmentEntryLinkIds)
		throws PortalException {

		for (long fragmentEntryLinkId : fragmentEntryLinkIds) {
			fragmentEntryLinkLocalService.deleteFragmentEntryLink(
				fragmentEntryLinkId);
		}
	}

	@Override
	public List<FragmentEntryLink>
		deleteLayoutPageTemplateEntryFragmentEntryLinks(
			long groupId, long classNameId, long classPK) {

		List<FragmentEntryLink> fragmentEntryLinks = getFragmentEntryLinks(
			groupId, classNameId, classPK);

		if (ListUtil.isEmpty(fragmentEntryLinks)) {
			return Collections.emptyList();
		}

		List<FragmentEntryLink> deletedFragmentEntryLinks = new ArrayList<>();

		for (FragmentEntryLink fragmentEntryLink : fragmentEntryLinks) {
			fragmentEntryLinkLocalService.deleteFragmentEntryLink(
				fragmentEntryLink);

			deletedFragmentEntryLinks.add(fragmentEntryLink);
		}

		return deletedFragmentEntryLinks;
	}

	@Override
	public int getClassedModelFragmentEntryLinksCount(
		long groupId, long classNameId, long classPK) {

		return fragmentEntryLinkPersistence.countByG_C_C(
			groupId, classNameId, classPK);
	}

	@Override
	public List<FragmentEntryLink> getFragmentEntryLinks(
		long groupId, long fragmentEntryId, int start, int end,
		OrderByComparator<FragmentEntryLink> orderByComparator) {

		return fragmentEntryLinkFinder.findByG_F(
			groupId, fragmentEntryId, start, end, orderByComparator);
	}

	@Override
	public List<FragmentEntryLink> getFragmentEntryLinks(
		long groupId, long classNameId, long classPK) {

		return fragmentEntryLinkPersistence.findByG_C_C(
			groupId, classNameId, classPK);
	}

	@Override
	public List<FragmentEntryLink> getFragmentEntryLinks(
		long groupId, long fragmentEntryId, long classNameId,
		int layoutPageTemplateType, int start, int end,
		OrderByComparator<FragmentEntryLink> orderByComparator) {

		return fragmentEntryLinkFinder.findByG_F_C_L(
			groupId, fragmentEntryId, classNameId, layoutPageTemplateType,
			start, end, orderByComparator);
	}

	@Override
	public List<FragmentEntryLink> getFragmentEntryLinks(
		long groupId, long fragmentEntryId, long classNameId, int start,
		int end, OrderByComparator<FragmentEntryLink> orderByComparator) {

		return fragmentEntryLinkFinder.findByG_F_C(
			groupId, fragmentEntryId, classNameId, start, end,
			orderByComparator);
	}

	@Override
	public List<FragmentEntryLink> getFragmentEntryLinks(String rendererKey) {
		return fragmentEntryLinkPersistence.findByRendererKey(rendererKey);
	}

	@Override
	public List<FragmentEntryLink> getFragmentEntryLinksByFragmentEntryId(
		long fragmentEntryId) {

		return fragmentEntryLinkPersistence.findByFragmentEntryId(
			fragmentEntryId);
	}

	@Override
	public int getFragmentEntryLinksCount(long groupId, long fragmentEntryId) {
		return fragmentEntryLinkFinder.countByG_F(groupId, fragmentEntryId);
	}

	@Override
	public int getFragmentEntryLinksCount(
		long groupId, long fragmentEntryId, long classNameId) {

		return fragmentEntryLinkFinder.countByG_F_C(
			groupId, fragmentEntryId, classNameId);
	}

	@Override
	public int getFragmentEntryLinksCount(
		long groupId, long fragmentEntryId, long classNameId,
		int layoutPageTemplateType) {

		return fragmentEntryLinkFinder.countByG_F_C_L(
			groupId, fragmentEntryId, classNameId, layoutPageTemplateType);
	}

	@Override
	public int getFragmentEntryLinksCountByFragmentEntryId(
		long fragmentEntryId) {

		return fragmentEntryLinkPersistence.countByFragmentEntryId(
			fragmentEntryId);
	}

	@Override
	public List<FragmentEntryLink> getLayoutFragmentEntryLinksByFragmentEntryId(
		long groupId, long fragmentEntryId, int start, int end,
		OrderByComparator<FragmentEntryLink> orderByComparator) {

		return fragmentEntryLinkFinder.findByG_F_P_L(
			groupId, fragmentEntryId, -1, start, end, orderByComparator);
	}

	@Override
	public int getLayoutFragmentEntryLinksCountByFragmentEntryId(
		long groupId, long fragmentEntryId) {

		return fragmentEntryLinkFinder.countByG_F_P_L(
			groupId, fragmentEntryId, -1);
	}

	@Override
	public List<FragmentEntryLink>
		getLayoutPageTemplateFragmentEntryLinksByFragmentEntryId(
			long groupId, long fragmentEntryId, int layoutPageTemplateType,
			int start, int end,
			OrderByComparator<FragmentEntryLink> orderByComparator) {

		return fragmentEntryLinkFinder.findByG_F_P_L(
			groupId, fragmentEntryId, layoutPageTemplateType, start, end,
			orderByComparator);
	}

	@Override
	public int getLayoutPageTemplateFragmentEntryLinksCountByFragmentEntryId(
		long groupId, long fragmentEntryId, int layoutPageTemplateType) {

		return fragmentEntryLinkFinder.countByG_F_P_L(
			groupId, fragmentEntryId, layoutPageTemplateType);
	}

	@Override
	public void updateClassedModel(long classNameId, long classPK)
		throws PortalException {

		if (classNameId != _portal.getClassNameId(Layout.class)) {
			return;
		}

		Layout layout = _layoutLocalService.fetchLayout(classPK);

		if (layout == null) {
			return;
		}

		_layoutLocalService.updateLayout(
			layout.getGroupId(), layout.isPrivateLayout(), layout.getLayoutId(),
			layout.getTypeSettings());
	}

	@Override
	public FragmentEntryLink updateFragmentEntryLink(
			long fragmentEntryLinkId, int position)
		throws PortalException {

		FragmentEntryLink fragmentEntryLink = fetchFragmentEntryLink(
			fragmentEntryLinkId);

		fragmentEntryLink.setPosition(position);

		return fragmentEntryLinkPersistence.update(fragmentEntryLink);
	}

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 *             #updateFragmentEntryLink(long, long, long, long, long, long,
	 *             String, String, String, String, String, String, int,
	 *             ServiceContext)}
	 */
	@Deprecated
	@Override
	public FragmentEntryLink updateFragmentEntryLink(
			long userId, long fragmentEntryLinkId,
			long originalFragmentEntryLinkId, long fragmentEntryId,
			long classNameId, long classPK, String css, String html, String js,
			String editableValues, int position, ServiceContext serviceContext)
		throws PortalException {

		FragmentEntryLink fragmentEntryLink =
			fragmentEntryLinkPersistence.findByPrimaryKey(fragmentEntryLinkId);

		return updateFragmentEntryLink(
			userId, fragmentEntryLinkId, originalFragmentEntryLinkId,
			fragmentEntryId, classNameId, classPK, css, html, js,
			fragmentEntryLink.getConfiguration(), editableValues,
			fragmentEntryLink.getNamespace(), position, serviceContext);
	}

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 *             #updateFragmentEntryLink(long, long, long, long, long, long,
	 *             String, String, String, String, String, String, int,
	 *             ServiceContext)}
	 */
	@Deprecated
	@Override
	public FragmentEntryLink updateFragmentEntryLink(
			long userId, long fragmentEntryLinkId,
			long originalFragmentEntryLinkId, long fragmentEntryId,
			long classNameId, long classPK, String css, String html, String js,
			String editableValues, String namespace, int position,
			ServiceContext serviceContext)
		throws PortalException {

		FragmentEntryLink fragmentEntryLink =
			fragmentEntryLinkPersistence.findByPrimaryKey(fragmentEntryLinkId);

		return updateFragmentEntryLink(
			userId, fragmentEntryLinkId, originalFragmentEntryLinkId,
			fragmentEntryId, classNameId, classPK, css, html, js,
			fragmentEntryLink.getConfiguration(), editableValues, namespace,
			position, serviceContext);
	}

	@Override
	public FragmentEntryLink updateFragmentEntryLink(
			long userId, long fragmentEntryLinkId,
			long originalFragmentEntryLinkId, long fragmentEntryId,
			long classNameId, long classPK, String css, String html, String js,
			String configuration, String editableValues, String namespace,
			int position, ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		_classNameLocalService.getClassName(classNameId);

		FragmentEntryLink fragmentEntryLink = fetchFragmentEntryLink(
			fragmentEntryLinkId);

		fragmentEntryLink.setUserId(user.getUserId());
		fragmentEntryLink.setUserName(user.getFullName());
		fragmentEntryLink.setModifiedDate(
			serviceContext.getModifiedDate(new Date()));
		fragmentEntryLink.setOriginalFragmentEntryLinkId(
			originalFragmentEntryLinkId);
		fragmentEntryLink.setFragmentEntryId(fragmentEntryId);
		fragmentEntryLink.setClassNameId(classNameId);
		fragmentEntryLink.setClassPK(classPK);
		fragmentEntryLink.setCss(css);
		fragmentEntryLink.setHtml(html);
		fragmentEntryLink.setJs(js);
		fragmentEntryLink.setConfiguration(configuration);
		fragmentEntryLink.setEditableValues(editableValues);

		if (Validator.isNotNull(namespace)) {
			fragmentEntryLink.setNamespace(namespace);
		}

		fragmentEntryLink.setPosition(position);

		return fragmentEntryLinkPersistence.update(fragmentEntryLink);
	}

	@Override
	public FragmentEntryLink updateFragmentEntryLink(
			long fragmentEntryLinkId, String editableValues)
		throws PortalException {

		return updateFragmentEntryLink(
			fragmentEntryLinkId, editableValues, true);
	}

	@Override
	public FragmentEntryLink updateFragmentEntryLink(
			long fragmentEntryLinkId, String editableValues,
			boolean updateClassedModel)
		throws PortalException {

		FragmentEntryLink fragmentEntryLink = fetchFragmentEntryLink(
			fragmentEntryLinkId);

		fragmentEntryLink.setEditableValues(editableValues);

		if (updateClassedModel) {
			updateClassedModel(
				fragmentEntryLink.getClassNameId(),
				fragmentEntryLink.getClassPK());
		}

		return fragmentEntryLinkPersistence.update(fragmentEntryLink);
	}

	@Override
	public void updateFragmentEntryLinks(
			long userId, long groupId, long classNameId, long classPK,
			long[] fragmentEntryIds, String editableValues,
			ServiceContext serviceContext)
		throws PortalException {

		deleteLayoutPageTemplateEntryFragmentEntryLinks(
			groupId, classNameId, classPK);

		if (ArrayUtil.isEmpty(fragmentEntryIds)) {
			return;
		}

		JSONObject jsonObject = _jsonFactory.createJSONObject(editableValues);

		int position = 0;

		for (long fragmentId : fragmentEntryIds) {
			FragmentEntry fragmentEntry =
				fragmentEntryPersistence.fetchByPrimaryKey(fragmentId);

			addFragmentEntryLink(
				userId, groupId, 0, fragmentEntry.getFragmentEntryId(),
				classNameId, classPK, fragmentEntry.getCss(),
				fragmentEntry.getHtml(), fragmentEntry.getJs(),
				fragmentEntry.getConfiguration(),
				jsonObject.getString(String.valueOf(position)),
				StringPool.BLANK, position++, null, serviceContext);
		}
	}

	@Override
	public void updateFragmentEntryLinks(
			Map<Long, String> fragmentEntryLinksEditableValuesMap)
		throws PortalException {

		FragmentEntryLink fragmentEntryLink = null;

		for (Map.Entry<Long, String> entry :
				fragmentEntryLinksEditableValuesMap.entrySet()) {

			fragmentEntryLink = fetchFragmentEntryLink(entry.getKey());

			fragmentEntryLink.setEditableValues(entry.getValue());

			fragmentEntryLink = fragmentEntryLinkPersistence.update(
				fragmentEntryLink);
		}

		if (fragmentEntryLink != null) {
			updateClassedModel(
				fragmentEntryLink.getClassNameId(),
				fragmentEntryLink.getClassPK());
		}
	}

	@Override
	public void updateLatestChanges(long fragmentEntryLinkId)
		throws PortalException {

		FragmentEntryLink fragmentEntryLink =
			fragmentEntryLinkPersistence.findByPrimaryKey(fragmentEntryLinkId);

		FragmentEntry fragmentEntry = fragmentEntryPersistence.findByPrimaryKey(
			fragmentEntryLink.getFragmentEntryId());

		fragmentEntryLink.setHtml(fragmentEntry.getHtml());

		// LPS-132154 Set configuration before processing the HTML

		fragmentEntryLink.setConfiguration(fragmentEntry.getConfiguration());

		String processedHTML = _getProcessedHTML(
			fragmentEntryLink, ServiceContextThreadLocal.getServiceContext());

		String defaultEditableValues = String.valueOf(
			_fragmentEntryProcessorRegistry.getDefaultEditableValuesJSONObject(
				processedHTML, fragmentEntryLink.getConfiguration()));

		fragmentEntryLink.setCss(fragmentEntry.getCss());
		fragmentEntryLink.setJs(fragmentEntry.getJs());

		String newEditableValues = _mergeEditableValues(
			defaultEditableValues, fragmentEntryLink.getEditableValues());

		fragmentEntryLink.setEditableValues(newEditableValues);

		fragmentEntryLink.setLastPropagationDate(new Date());

		fragmentEntryLinkPersistence.update(fragmentEntryLink);
	}

	private String _getProcessedHTML(
			FragmentEntryLink fragmentEntryLink, ServiceContext serviceContext)
		throws PortalException {

		if (serviceContext == null) {
			return fragmentEntryLink.getHtml();
		}

		HttpServletRequest httpServletRequest = serviceContext.getRequest();
		HttpServletResponse httpServletResponse = serviceContext.getResponse();

		if ((httpServletRequest == null) || (httpServletResponse == null)) {
			return fragmentEntryLink.getHtml();
		}

		FragmentEntryProcessorContext fragmentEntryProcessorContext =
			new DefaultFragmentEntryProcessorContext(
				httpServletRequest, httpServletResponse,
				FragmentEntryLinkConstants.EDIT,
				LocaleUtil.getMostRelevantLocale());

		return _fragmentEntryProcessorRegistry.processFragmentEntryLinkHTML(
			fragmentEntryLink, fragmentEntryProcessorContext);
	}

	private String _mergeEditableValues(
		String defaultEditableValues, String editableValues) {

		try {
			JSONObject defaultEditableValuesJSONObject =
				JSONFactoryUtil.createJSONObject(defaultEditableValues);

			JSONObject editableValuesJSONObject =
				JSONFactoryUtil.createJSONObject(editableValues);

			for (String fragmentEntryProcessorKey :
					_FRAGMENT_ENTRY_PROCESSOR_KEYS) {

				JSONObject editableFragmentEntryProcessorJSONObject =
					editableValuesJSONObject.getJSONObject(
						fragmentEntryProcessorKey);

				if (editableFragmentEntryProcessorJSONObject == null) {
					continue;
				}

				JSONObject defaultEditableFragmentEntryProcessorJSONObject =
					defaultEditableValuesJSONObject.getJSONObject(
						fragmentEntryProcessorKey);

				if (defaultEditableFragmentEntryProcessorJSONObject == null) {
					continue;
				}

				Iterator<String> iterator =
					defaultEditableFragmentEntryProcessorJSONObject.keys();

				while (iterator.hasNext()) {
					String key = iterator.next();

					if (editableFragmentEntryProcessorJSONObject.has(key)) {
						defaultEditableFragmentEntryProcessorJSONObject.put(
							key,
							editableFragmentEntryProcessorJSONObject.get(key));
					}
				}

				editableValuesJSONObject.put(
					fragmentEntryProcessorKey,
					defaultEditableFragmentEntryProcessorJSONObject);
			}

			return editableValuesJSONObject.toString();
		}
		catch (JSONException jsonException) {
			if (_log.isDebugEnabled()) {
				_log.debug(jsonException, jsonException);
			}
		}

		return editableValues;
	}

	private String _replaceResources(long fragmentEntryId, String html)
		throws PortalException {

		FragmentEntry fragmentEntry =
			fragmentEntryPersistence.fetchByPrimaryKey(fragmentEntryId);

		if (fragmentEntry == null) {
			return html;
		}

		FragmentCollection fragmentCollection =
			fragmentCollectionPersistence.fetchByPrimaryKey(
				fragmentEntry.getFragmentCollectionId());

		Matcher matcher = _pattern.matcher(html);

		while (matcher.find()) {
			FileEntry fileEntry =
				PortletFileRepositoryUtil.fetchPortletFileEntry(
					fragmentEntry.getGroupId(),
					fragmentCollection.getResourcesFolderId(),
					matcher.group(1));

			String fileEntryURL = StringPool.BLANK;

			if (fileEntry != null) {
				fileEntryURL = _dlURLHelper.getDownloadURL(
					fileEntry, fileEntry.getFileVersion(), null,
					StringPool.BLANK, false, false);
			}

			html = StringUtil.replace(html, matcher.group(), fileEntryURL);
		}

		return html;
	}

	private static final String[] _FRAGMENT_ENTRY_PROCESSOR_KEYS = {
		"com.liferay.fragment.entry.processor.editable." +
			"EditableFragmentEntryProcessor"
	};

	private static final Log _log = LogFactoryUtil.getLog(
		FragmentEntryLinkLocalServiceImpl.class);

	private static final Pattern _pattern = Pattern.compile(
		"\\[resources:(.+?)\\]");

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private DLURLHelper _dlURLHelper;

	@Reference
	private FragmentEntryProcessorRegistry _fragmentEntryProcessorRegistry;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private Portal _portal;

}