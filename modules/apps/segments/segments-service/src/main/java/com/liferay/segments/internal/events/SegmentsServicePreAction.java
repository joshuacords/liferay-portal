/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.segments.internal.events;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.segments.SegmentsEntryRetriever;
import com.liferay.segments.configuration.SegmentsConfiguration;
import com.liferay.segments.constants.SegmentsExperienceConstants;
import com.liferay.segments.constants.SegmentsWebKeys;
import com.liferay.segments.context.RequestContextMapper;
import com.liferay.segments.model.SegmentsExperience;
import com.liferay.segments.processor.SegmentsExperienceRequestProcessorRegistry;
import com.liferay.segments.service.SegmentsExperienceLocalService;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eduardo García
 */
@Component(
	configurationPid = "com.liferay.segments.configuration.SegmentsConfiguration",
	service = {}
)
public class SegmentsServicePreAction extends Action {

	@Override
	public void run(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws ActionException {

		try {
			doRun(httpServletRequest, httpServletResponse);
		}
		catch (Exception exception) {
			throw new ActionException(exception);
		}
	}

	@Activate
	protected void activate(
		BundleContext bundleContext, Map<String, Object> properties) {

		SegmentsConfiguration segmentsConfiguration =
			ConfigurableUtil.createConfigurable(
				SegmentsConfiguration.class, properties);

		if (segmentsConfiguration.segmentationEnabled()) {
			_serviceRegistration = bundleContext.registerService(
				LifecycleAction.class, this,
				MapUtil.singletonDictionary(
					"key", "servlet.service.events.pre"));
		}
	}

	@Deactivate
	protected void deactivate() {
		if (_serviceRegistration != null) {
			_serviceRegistration.unregister();
		}
	}

	protected void doRun(
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		if (!themeDisplay.isLifecycleRender()) {
			return;
		}

		Layout layout = themeDisplay.getLayout();

		if ((layout == null) ||
			!Objects.equals(layout.getType(), LayoutConstants.TYPE_CONTENT) ||
			layout.isTypeControlPanel()) {

			return;
		}

		httpServletRequest.setAttribute(
			SegmentsWebKeys.SEGMENTS_EXPERIENCE_IDS,
			_getSegmentsExperienceIds(
				httpServletRequest, httpServletResponse, layout.getGroupId(),
				themeDisplay.getUserId(),
				_portal.getClassNameId(Layout.class.getName()),
				layout.getPlid()));
	}

	private long[] _getSegmentsExperienceIds(
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse, long groupId, long userId,
		long classNameId, long classPK) {

		try {
			long[] segmentsExperienceIds =
				_segmentsExperienceRequestProcessorRegistry.
					getSegmentsExperienceIds(
						httpServletRequest, httpServletResponse, groupId,
						classNameId, classPK);

			if (segmentsExperienceIds.length > 0) {
				Set<Long> segmentsExperienceIdsSegmentsEntryIds =
					new HashSet<>();

				for (long segmentsExperienceId : segmentsExperienceIds) {
					SegmentsExperience segmentsExperience =
						_segmentsExperienceLocalService.fetchSegmentsExperience(
							segmentsExperienceId);

					segmentsExperienceIdsSegmentsEntryIds.add(
						segmentsExperience.getSegmentsEntryId());
				}

				long[] cachedSegmentsEntryIds =
					(long[])httpServletRequest.getAttribute(
						SegmentsWebKeys.SEGMENTS_ENTRY_IDS);

				long[] segmentsEntryIds = null;

				if (cachedSegmentsEntryIds != null) {
					segmentsEntryIds = cachedSegmentsEntryIds;
				}
				else {
					segmentsEntryIds =
						_segmentsEntryRetriever.getSegmentsEntryIds(
							groupId, userId,
							_requestContextMapper.map(httpServletRequest),
							ArrayUtil.toArray(
								segmentsExperienceIdsSegmentsEntryIds.toArray(
									new Long[0])));
				}

				httpServletRequest.setAttribute(
					SegmentsWebKeys.SEGMENTS_ENTRY_IDS, segmentsEntryIds);

				return ArrayUtil.append(
					_segmentsExperienceRequestProcessorRegistry.
						getSegmentsExperienceIds(
							httpServletRequest, httpServletResponse, groupId,
							classNameId, classPK, segmentsEntryIds),
					SegmentsExperienceConstants.ID_DEFAULT);
			}
		}
		catch (PortalException portalException) {
			if (_log.isWarnEnabled()) {
				_log.warn(portalException, portalException);
			}
		}

		return new long[] {SegmentsExperienceConstants.ID_DEFAULT};
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SegmentsServicePreAction.class);

	@Reference
	private Portal _portal;

	@Reference
	private RequestContextMapper _requestContextMapper;

	@Reference
	private volatile SegmentsEntryRetriever _segmentsEntryRetriever;

	@Reference
	private SegmentsExperienceLocalService _segmentsExperienceLocalService;

	@Reference
	private SegmentsExperienceRequestProcessorRegistry
		_segmentsExperienceRequestProcessorRegistry;

	private ServiceRegistration<LifecycleAction> _serviceRegistration;

}