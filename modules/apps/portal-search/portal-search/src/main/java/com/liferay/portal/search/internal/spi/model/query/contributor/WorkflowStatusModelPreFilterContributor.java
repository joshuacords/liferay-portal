/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.internal.spi.model.query.contributor;

import com.liferay.change.tracking.constants.CTConstants;
import com.liferay.petra.function.UnsafeSupplierValue;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.ClassedModel;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.filter.TermsFilter;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.search.model.uid.UIDFactory;
import com.liferay.portal.search.spi.model.query.contributor.ModelPreFilterContributor;
import com.liferay.portal.search.spi.model.registrar.ModelSearchSettings;

import java.io.Serializable;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(
	property = "model.pre.filter.contributor.id=WorkflowStatus",
	service = ModelPreFilterContributor.class
)
public class WorkflowStatusModelPreFilterContributor
	implements ModelPreFilterContributor {

	@Override
	public void contribute(
		BooleanFilter booleanFilter, ModelSearchSettings modelSearchSettings,
		SearchContext searchContext) {

		String className = modelSearchSettings.getClassName();

		Map<Serializable, Object> previewValues =
			PreviewModelPreFilterContributor.getPreviewValues(className);

		BooleanFilter statusBooleanFilter = booleanFilter;

		if (MapUtil.isNotEmpty(previewValues)) {
			statusBooleanFilter = new BooleanFilter();
		}

		int[] statuses = GetterUtil.getIntegerValues(
			searchContext.getAttribute(Field.STATUS), null);

		if (ArrayUtil.isEmpty(statuses)) {
			int status = GetterUtil.getInteger(
				searchContext.getAttribute(Field.STATUS),
				WorkflowConstants.STATUS_APPROVED);

			statuses = new int[] {status};
		}

		if (!ArrayUtil.contains(statuses, WorkflowConstants.STATUS_ANY)) {
			TermsFilter statusesTermsFilter = new TermsFilter(Field.STATUS);

			statusesTermsFilter.addValues(ArrayUtil.toStringArray(statuses));

			statusBooleanFilter.add(
				statusesTermsFilter, BooleanClauseOccur.MUST);
		}
		else {
			statusBooleanFilter.addTerm(
				Field.STATUS, String.valueOf(WorkflowConstants.STATUS_IN_TRASH),
				BooleanClauseOccur.MUST_NOT);
		}

		if (MapUtil.isEmpty(previewValues)) {
			return;
		}

		TermsFilter uidTermsFilter = new TermsFilter(Field.UID);

		for (Object value : previewValues.values()) {
			if (value instanceof
					UnsafeSupplierValue<?, ?> unsafeSupplierValue) {

				try {
					uidTermsFilter.addValue(
						_uidFactory.getUID(
							(ClassedModel)unsafeSupplierValue.getValue()));
				}
				catch (Throwable throwable) {
					throw new SystemException(throwable);
				}
			}
			else {
				uidTermsFilter.addValue(
					_uidFactory.getUID(
						className, (Serializable)value,
						CTConstants.CT_COLLECTION_ID_PRODUCTION));
			}
		}

		BooleanFilter previewBooleanFilter = new BooleanFilter();

		previewBooleanFilter.add(
			statusBooleanFilter, BooleanClauseOccur.SHOULD);
		previewBooleanFilter.add(uidTermsFilter, BooleanClauseOccur.SHOULD);

		booleanFilter.add(previewBooleanFilter, BooleanClauseOccur.MUST);
	}

	@Reference
	private UIDFactory _uidFactory;

}