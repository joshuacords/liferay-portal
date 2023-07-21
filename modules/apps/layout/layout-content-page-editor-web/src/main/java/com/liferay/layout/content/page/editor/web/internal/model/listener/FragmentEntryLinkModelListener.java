/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.content.page.editor.web.internal.model.listener;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.model.AssetEntryUsage;
import com.liferay.asset.service.AssetEntryUsageLocalService;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLinkLocalService;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalService;
import com.liferay.fragment.model.FragmentEntryLink;
import com.liferay.layout.content.page.editor.web.internal.util.ContentUtil;
import com.liferay.portal.kernel.comment.CommentManager;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ClassName;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.display.template.PortletDisplayTemplate;

import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pavel Savinov
 */
@Component(service = ModelListener.class)
public class FragmentEntryLinkModelListener
	extends BaseModelListener<FragmentEntryLink> {

	@Override
	public void onAfterRemove(FragmentEntryLink fragmentEntryLink)
		throws ModelListenerException {

		_assetEntryUsageLocalService.deleteAssetEntryUsages(
			_portal.getClassNameId(FragmentEntryLink.class),
			String.valueOf(fragmentEntryLink.getFragmentEntryLinkId()),
			fragmentEntryLink.getClassPK());

		try {
			_deleteDDMTemplateLinks(fragmentEntryLink);

			_commentManager.deleteDiscussion(
				FragmentEntryLink.class.getName(),
				fragmentEntryLink.getFragmentEntryLinkId());
		}
		catch (PortalException portalException) {
			throw new ModelListenerException(portalException);
		}
	}

	@Override
	public void onAfterUpdate(FragmentEntryLink fragmentEntryLink)
		throws ModelListenerException {

		_updateAssetEntryUsage(fragmentEntryLink);

		_updateDDMTemplateLink(fragmentEntryLink);
	}

	private void _deleteDDMTemplateLinks(FragmentEntryLink fragmentEntryLink)
		throws PortalException {

		_ddmTemplateLinkLocalService.deleteTemplateLink(
			_portal.getClassNameId(FragmentEntryLink.class),
			fragmentEntryLink.getFragmentEntryLinkId());

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
			fragmentEntryLink.getEditableValues());

		Iterator<String> keysIterator = jsonObject.keys();

		while (keysIterator.hasNext()) {
			String key = keysIterator.next();

			JSONObject editableProcessorJSONObject = jsonObject.getJSONObject(
				key);

			if (editableProcessorJSONObject == null) {
				continue;
			}

			Iterator<String> editableKeysIterator =
				editableProcessorJSONObject.keys();

			while (editableKeysIterator.hasNext()) {
				String editableKey = editableKeysIterator.next();

				String compositeClassName =
					ResourceActionsUtil.getCompositeModelName(
						FragmentEntryLink.class.getName(), editableKey);

				ClassName className = _classNameLocalService.fetchClassName(
					compositeClassName);

				if (className == null) {
					continue;
				}

				_ddmTemplateLinkLocalService.deleteTemplateLink(
					className.getClassNameId(),
					fragmentEntryLink.getFragmentEntryLinkId());
			}
		}
	}

	private void _updateAssetEntryUsage(FragmentEntryLink fragmentEntryLink) {
		_assetEntryUsageLocalService.deleteAssetEntryUsages(
			_portal.getClassNameId(FragmentEntryLink.class),
			String.valueOf(fragmentEntryLink.getFragmentEntryLinkId()),
			fragmentEntryLink.getClassPK());

		Set<AssetEntry> assetEntries =
			ContentUtil.getFragmentEntryLinkMappedAssetEntries(
				fragmentEntryLink);

		for (AssetEntry assetEntry : assetEntries) {
			AssetEntryUsage assetEntryUsage =
				_assetEntryUsageLocalService.fetchAssetEntryUsage(
					assetEntry.getEntryId(),
					_portal.getClassNameId(FragmentEntryLink.class),
					String.valueOf(fragmentEntryLink.getFragmentEntryLinkId()),
					fragmentEntryLink.getClassPK());

			if (assetEntryUsage != null) {
				continue;
			}

			ServiceContext serviceContext = Optional.ofNullable(
				ServiceContextThreadLocal.getServiceContext()
			).orElse(
				new ServiceContext()
			);

			_assetEntryUsageLocalService.addAssetEntryUsage(
				fragmentEntryLink.getGroupId(), assetEntry.getEntryId(),
				_portal.getClassNameId(FragmentEntryLink.class),
				String.valueOf(fragmentEntryLink.getFragmentEntryLinkId()),
				fragmentEntryLink.getClassPK(), serviceContext);
		}
	}

	private void _updateDDMTemplateLink(FragmentEntryLink fragmentEntryLink) {
		_ddmTemplateLinkLocalService.deleteTemplateLink(
			_portal.getClassNameId(FragmentEntryLink.class),
			fragmentEntryLink.getFragmentEntryLinkId());

		try {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
				fragmentEntryLink.getEditableValues());

			Iterator<String> keysIterator = jsonObject.keys();

			while (keysIterator.hasNext()) {
				String key = keysIterator.next();

				JSONObject editableProcessorJSONObject =
					jsonObject.getJSONObject(key);

				if (editableProcessorJSONObject == null) {
					continue;
				}

				Iterator<String> editableKeysIterator =
					editableProcessorJSONObject.keys();

				while (editableKeysIterator.hasNext()) {
					String editableKey = editableKeysIterator.next();

					JSONObject editableJSONObject =
						editableProcessorJSONObject.getJSONObject(editableKey);

					if (editableJSONObject == null) {
						continue;
					}

					String fieldId = editableJSONObject.getString("fieldId");

					String mappedField = editableJSONObject.getString(
						"mappedField", fieldId);

					if (Validator.isNull(mappedField)) {
						continue;
					}

					_updateDDMTemplateLink(
						fragmentEntryLink, editableKey, mappedField);
				}
			}
		}
		catch (PortalException portalException) {
			throw new ModelListenerException(portalException);
		}
	}

	private void _updateDDMTemplateLink(
		FragmentEntryLink fragmentEntryLink, String editableKey,
		String mappedField) {

		if (!mappedField.startsWith(
				PortletDisplayTemplate.DISPLAY_STYLE_PREFIX)) {

			return;
		}

		String ddmTemplateKey = mappedField.substring(
			PortletDisplayTemplate.DISPLAY_STYLE_PREFIX.length());

		DDMTemplate ddmTemplate = _ddmTemplateLocalService.fetchTemplate(
			fragmentEntryLink.getGroupId(),
			_portal.getClassNameId(DDMStructure.class), ddmTemplateKey);

		if (ddmTemplate == null) {
			return;
		}

		String compositeClassName = ResourceActionsUtil.getCompositeModelName(
			FragmentEntryLink.class.getName(), editableKey);

		_ddmTemplateLinkLocalService.updateTemplateLink(
			_portal.getClassNameId(compositeClassName),
			fragmentEntryLink.getFragmentEntryLinkId(),
			ddmTemplate.getTemplateId());
	}

	@Reference
	private AssetEntryUsageLocalService _assetEntryUsageLocalService;

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private CommentManager _commentManager;

	@Reference
	private DDMTemplateLinkLocalService _ddmTemplateLinkLocalService;

	@Reference
	private DDMTemplateLocalService _ddmTemplateLocalService;

	@Reference
	private Portal _portal;

}