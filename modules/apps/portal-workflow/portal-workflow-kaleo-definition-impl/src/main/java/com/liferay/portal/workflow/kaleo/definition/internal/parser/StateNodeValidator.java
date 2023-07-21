/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.definition.internal.parser;

import com.liferay.portal.workflow.kaleo.definition.Definition;
import com.liferay.portal.workflow.kaleo.definition.State;
import com.liferay.portal.workflow.kaleo.definition.exception.KaleoDefinitionValidationException;
import com.liferay.portal.workflow.kaleo.definition.parser.NodeValidator;

import java.util.Objects;

import org.osgi.service.component.annotations.Component;

/**
 * @author Michael C. Han
 * @author Marcellus Tavares
 */
@Component(
	immediate = true, property = "node.type=STATE",
	service = NodeValidator.class
)
public class StateNodeValidator extends BaseNodeValidator<State> {

	@Override
	protected void doValidate(Definition definition, State state)
		throws KaleoDefinitionValidationException {

		if (state.isInitial()) {
			validateInitialState(definition, state);
		}
		else if (state.getIncomingTransitionsCount() == 0) {
			throw new KaleoDefinitionValidationException.
				MustSetIncomingTransition(state.getName());
		}
	}

	protected void validateInitialState(Definition definition, State state)
		throws KaleoDefinitionValidationException {

		State initialState = definition.getInitialState();

		if (!Objects.equals(initialState, state)) {
			throw new KaleoDefinitionValidationException.
				MultipleInitialStateNodes(
					state.getName(), initialState.getName());
		}

		if (state.getIncomingTransitionsCount() > 0) {
			throw new KaleoDefinitionValidationException.
				MustNotSetIncomingTransition(state.getName());
		}

		if (state.getOutgoingTransitionsCount() == 0) {
			throw new KaleoDefinitionValidationException.
				MustSetOutgoingTransition(state.getName());
		}
	}

}