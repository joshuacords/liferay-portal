/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {RulesVisitor} from 'dynamic-data-mapping-form-renderer/js/util/visitors.es';

import Token from '../../../expressions/Token.es';
import Tokenizer from '../../../expressions/Tokenizer.es';

export const isFieldValueOperand = operands => {
	return (
		operands.length == 2 &&
		operands[0].type === 'field' &&
		operands[1].type == 'string'
	);
};

export const renameFieldInsideExpression = (
	expression,
	fieldName,
	newFieldName
) => {
	const tokens = Tokenizer.tokenize(expression);

	return Tokenizer.stringifyTokens(
		tokens.map(token => {
			if (token.type === Token.VARIABLE && token.value === fieldName) {
				token = new Token(Token.VARIABLE, newFieldName);
			}

			return token;
		})
	);
};

export const renameFieldInsideAutofill = (
	object,
	oldFieldName,
	newFieldName
) => {
	Object.keys(object).map(key => {
		if (object[key] === oldFieldName) {
			object[key] = newFieldName;
		}
	});

	return object;
};

export const updateRulesReferences = (
	newProperties,
	oldProperties,
	optionIndex,
	rules
) => {
	const oldFieldName = oldProperties.fieldName;
	const newFieldName = newProperties.fieldName;
	const visitor = new RulesVisitor(rules);

	rules = visitor.mapActions(action => {
		if (action.target === oldFieldName) {
			action = {
				...action,
				target: newFieldName
			};
		}

		if (action.action === 'calculate') {
			action = {
				...action,
				expression: renameFieldInsideExpression(
					action.expression,
					oldFieldName,
					newFieldName
				)
			};
		}
		else if (action.action === 'auto-fill') {
			action = {
				...action,
				inputs: renameFieldInsideAutofill(
					action.inputs,
					oldFieldName,
					newFieldName
				),
				outputs: renameFieldInsideAutofill(
					action.outputs,
					oldFieldName,
					newFieldName
				)
			};
		}

		return action;
	});

	visitor.setRules(rules);

	return visitor.mapConditions(condition => {
		return {
			...condition,
			operands: condition.operands.map((operand, index) => {
				const oldOptions = oldProperties.options;
				const newOptions = newProperties.options;

				if (
					operand.type === 'field' &&
					operand.value === oldFieldName
				) {
					return {
						...operand,
						value: newFieldName
					};
				}
				else if (
					index === 1 &&
					!isNaN(optionIndex) &&
					optionIndex < oldOptions.length &&
					isFieldValueOperand(condition.operands) &&
					oldOptions[optionIndex].value == operand.value &&
					newOptions[optionIndex].value !=
						oldOptions[optionIndex].value
				) {
					return {
						...operand,
						value: newOptions[optionIndex].value
					};
				}

				return operand;
			})
		};
	});
};
