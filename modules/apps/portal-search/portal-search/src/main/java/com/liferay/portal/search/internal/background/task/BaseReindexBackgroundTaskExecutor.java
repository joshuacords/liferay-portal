/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.internal.background.task;

import com.liferay.portal.kernel.backgroundtask.BackgroundTask;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskResult;
import com.liferay.portal.kernel.backgroundtask.BaseBackgroundTaskExecutor;
import com.liferay.portal.kernel.backgroundtask.display.BackgroundTaskDisplay;
import com.liferay.portal.kernel.search.background.task.ReindexBackgroundTaskConstants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.search.internal.background.task.display.ReindexBackgroundTaskDisplay;

import java.io.Serializable;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Andrew Betts
 */
public abstract class BaseReindexBackgroundTaskExecutor
	extends BaseBackgroundTaskExecutor {

	public BaseReindexBackgroundTaskExecutor() {
		setBackgroundTaskStatusMessageTranslator(
			new ReindexBackgroundTaskStatusMessageTranslator());
	}

	@Override
	public BackgroundTaskResult execute(BackgroundTask backgroundTask)
		throws Exception {

                Map<String, Serializable> taskContextMap =
                        backgroundTask.getTaskContextMap();

                String className = (String)taskContextMap.get(
                        ReindexBackgroundTaskConstants.CLASS_NAME);
                List<Long> companyIds = _getCompanyIds(taskContextMap);
                String executionMode = (String)taskContextMap.get(
                        ReindexBackgroundTaskConstants.EXECUTION_MODE);

                reindex(className, companyIds, executionMode);

		return BackgroundTaskResult.SUCCESS;
	}

	@Override
	public BackgroundTaskDisplay getBackgroundTaskDisplay(
		BackgroundTask backgroundTask) {

		return new ReindexBackgroundTaskDisplay(backgroundTask);
        }

        protected abstract void reindex(
                        String className, List<Long> companyIds, String executionMode)
                throws Exception;

        private List<Long> _getCompanyIds(
                Map<String, Serializable> taskContextMap) {

                Object companyIds = taskContextMap.get(
                        ReindexBackgroundTaskConstants.COMPANY_IDS);

                if (companyIds instanceof List) {
                        List<?> companyIdsList = (List<?>)companyIds;

                        return companyIdsList.stream(
                        ).map(
                                GetterUtil::getLong
                        ).collect(
                                Collectors.toList()
                        );
                }

                return Arrays.stream(
                        GetterUtil.getLongValues(companyIds)
                ).boxed().collect(
                        Collectors.toList()
                );
        }

}