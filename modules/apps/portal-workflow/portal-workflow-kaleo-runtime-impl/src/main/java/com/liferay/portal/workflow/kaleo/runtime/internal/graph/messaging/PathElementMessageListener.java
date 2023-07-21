/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.runtime.internal.graph.messaging;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBus;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.workflow.kaleo.runtime.constants.KaleoRuntimeDestinationNames;
import com.liferay.portal.workflow.kaleo.runtime.graph.GraphWalker;
import com.liferay.portal.workflow.kaleo.runtime.graph.PathElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(
	immediate = true,
	property = "destination.name=" + KaleoRuntimeDestinationNames.KALEO_GRAPH_WALKER,
	service = MessageListener.class
)
public class PathElementMessageListener extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		Queue<List<PathElement>> queue = new LinkedList<>();

		queue.add(Collections.singletonList((PathElement)message.getPayload()));

		List<PathElement> pathElements = null;

		while ((pathElements = queue.poll()) != null) {
			for (PathElement pathElement : pathElements) {
				List<PathElement> remainingPathElements = new ArrayList<>();

				_graphWalker.follow(
					pathElement.getStartNode(), pathElement.getTargetNode(),
					remainingPathElements, pathElement.getExecutionContext());

				if (!remainingPathElements.isEmpty()) {
					queue.add(remainingPathElements);
				}
			}
		}
	}

	@Reference
	private GraphWalker _graphWalker;

	@Reference
	private MessageBus _messageBus;

}