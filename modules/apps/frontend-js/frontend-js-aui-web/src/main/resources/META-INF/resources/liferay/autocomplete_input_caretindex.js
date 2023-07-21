/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

/**
 * The Autocomplete Input Caretindex Component.
 *
 * @deprecated since 7.2, unused
 * @module liferay-autocomplete-input-caretindex
 */

AUI.add(
	'liferay-autocomplete-input-caretindex',
	A => {
		var STR_INPUT_NODE = 'inputNode';

		var AutcompleteInputCaretIndex = function() {};

		AutcompleteInputCaretIndex.prototype = {
			_getCaretIndex(node) {
				var instance = this;

				node = node || instance.get(STR_INPUT_NODE);

				var input = node.getDOM();

				return {
					end: input.selectionStart,
					start: input.selectionStart
				};
			},

			_setCaretIndex(node, caretIndex) {
				var instance = this;

				node = node || instance.get(STR_INPUT_NODE);

				var input = node.getDOM();

				Liferay.Util.focusFormField(input);

				input.setSelectionRange(caretIndex, caretIndex);
			}
		};

		A.Base.mix(Liferay.AutoCompleteTextarea, [AutcompleteInputCaretIndex]);
	},
	'',
	{
		requires: ['liferay-autocomplete-textarea']
	}
);
