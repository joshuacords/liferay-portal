/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.source.formatter.checkstyle.check;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import java.util.List;
import java.util.Objects;

/**
 * @author Joshua Cords
 */
public class SearchContextSetEndCheck extends BaseCheck {

	@Override
	public int[] getDefaultTokens() {
		return new int[] {TokenTypes.CLASS_DEF};
	}

	@Override
	protected void doVisitToken(DetailAST detailAST) {
		for (DetailAST literalNewDetailAST :
				getAllChildTokens(detailAST, true, TokenTypes.LITERAL_NEW)) {

			DetailAST identDetailAST = literalNewDetailAST.findFirstToken(
				TokenTypes.IDENT);

			if ((identDetailAST == null) ||
				!Objects.equals(identDetailAST.getText(), "SearchContext")) {

				continue;
			}

			DetailAST parentDetailAST = literalNewDetailAST.getParent();

			DetailAST scopeDetailAST = detailAST;
			String variableName = null;

			if (parentDetailAST.getType() == TokenTypes.ASSIGN) {
				variableName = getName(parentDetailAST);
			}
			else if (parentDetailAST.getType() == TokenTypes.EXPR) {
				parentDetailAST = parentDetailAST.getParent();

				if (parentDetailAST.getType() != TokenTypes.ASSIGN) {
					continue;
				}

				parentDetailAST = parentDetailAST.getParent();

				if (parentDetailAST.getType() != TokenTypes.VARIABLE_DEF) {
					continue;
				}

				variableName = getName(parentDetailAST);

				DetailAST methodDefinitionDetailAST = getParentWithTokenType(
					parentDetailAST, TokenTypes.CTOR_DEF,
					TokenTypes.METHOD_DEF);

				if (methodDefinitionDetailAST != null) {
					scopeDetailAST = methodDefinitionDetailAST;
				}
			}

			if (variableName == null) {
				continue;
			}

			List<DetailAST> methodCallDetailASTs = getMethodCalls(
				scopeDetailAST, variableName, "setEnd");

			if (methodCallDetailASTs.isEmpty()) {
				log(literalNewDetailAST, _MSG_MISSING_SET_END_CALL);

				continue;
			}

			for (DetailAST methodCallDetailAST : methodCallDetailASTs) {
				DetailAST firstParameterExprDetailAST =
					getFirstParameterExprDetailAST(methodCallDetailAST);

				if (firstParameterExprDetailAST == null) {
					continue;
				}

				List<String> names = getNames(
					firstParameterExprDetailAST, true);

				if (!names.isEmpty() &&
					Objects.equals(names.get(names.size() - 1), "ALL_POS")) {

					log(methodCallDetailAST, _MSG_ALL_POS_SET_END_CALL);
				}
			}
		}
	}

	private static final String _MSG_ALL_POS_SET_END_CALL =
		"set.end.call.all.pos";

	private static final String _MSG_MISSING_SET_END_CALL =
		"set.end.call.missing";

}