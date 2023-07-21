/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

/**
 * The Autocomplete Textarea Component.
 *
 * @deprecated since 7.2, unused
 * @module liferay-autocomplete-textarea
 */

AUI.add(
	'liferay-autocomplete-textarea',
	A => {
		var KeyMap = A.Event.KeyMap;
		var Lang = A.Lang;

		var KEY_DOWN = KeyMap.DOWN;

		var KEY_LIST = [KEY_DOWN, KeyMap.LEFT, KeyMap.RIGHT, KeyMap.UP].join();

		var STR_INPUT_NODE = 'inputNode';

		var STR_SPACE = ' ';

		var AutoCompleteTextarea = function() {};

		AutoCompleteTextarea.prototype = {
			_bindUIACTextarea() {
				var instance = this;

				var inputNode = instance.get(STR_INPUT_NODE);

				instance._eventHandles = [
					inputNode.on(
						'key',
						A.bind('_onKeyUp', instance),
						'up:' + KEY_LIST
					)
				];
			},

			_getACPositionBase() {
				var instance = this;

				return instance.get(STR_INPUT_NODE).getXY();
			},

			_getACPositionOffset() {
				var instance = this;

				var inputNode = instance.get(STR_INPUT_NODE);

				return [0, Lang.toInt(inputNode.getStyle('fontSize'))];
			},

			_getACVal() {
				var instance = this;

				return instance.get(STR_INPUT_NODE).val();
			},

			_getPrevTrigger(content, position) {
				var instance = this;

				var result = -1;

				var trigger = null;

				var triggers = instance._getTriggers();

				for (var i = position; i >= 0; --i) {
					var triggerIndex = triggers.indexOf(content[i]);

					if (triggerIndex >= 0) {
						result = i;
						trigger = triggers[triggerIndex];

						break;
					}
				}

				return {
					index: result,
					value: trigger
				};
			},

			_getQuery(val) {
				var instance = this;

				var result = null;

				var caretIndex = instance._getCaretIndex();

				if (caretIndex) {
					val = val.substring(0, caretIndex.start);

					instance._getTriggers().forEach(item => {
						var lastTriggerIndex = val.lastIndexOf(item);

						if (lastTriggerIndex >= 0) {
							val = val.substring(lastTriggerIndex);

							var regExp = instance._getRegExp();

							var res = regExp.exec(val);

							if (
								res &&
								res.index + res[1].length + item.length ===
									val.length &&
								(!result || val.length < result.length)
							) {
								result = val;
							}
						}
					});
				}

				return result;
			},

			_onKeyUp(event) {
				var instance = this;

				var acVisible = instance.get('visible');

				if (!acVisible || event.isKeyInSet('left', 'right')) {
					var inputNode = instance.get(STR_INPUT_NODE);

					var query = instance._getQuery(inputNode.val());

					instance._processKeyUp(query);
				}
			},

			_setACVal(text) {
				var instance = this;

				var inputNode = instance.get(STR_INPUT_NODE);

				inputNode.val(text);
			},

			_updateValue(text) {
				var instance = this;

				var caretIndex = instance._getCaretIndex();

				if (caretIndex) {
					var val = instance._getACVal();

					if (val) {
						var lastTrigger = instance._getPrevTrigger(
							val,
							caretIndex.start
						);

						var lastTriggerIndex = lastTrigger.index;

						if (lastTriggerIndex >= 0) {
							var prefix = val.substring(0, lastTriggerIndex);

							val = val.substring(lastTriggerIndex);

							var regExp = instance._getRegExp();

							var res = regExp.exec(val);

							if (res) {
								var restText = val.substring(res[1].length + 1);

								var spaceAdded = 1;

								if (
									restText.length === 0 ||
									restText[0] !== STR_SPACE
								) {
									text += STR_SPACE;

									spaceAdded = 0;
								}

								var resultText =
									prefix + lastTrigger.value + text;

								var resultEndPos =
									resultText.length + spaceAdded;

								instance._setACVal(resultText + restText);

								instance._setCaretIndex(
									instance.get(STR_INPUT_NODE),
									resultEndPos
								);
							}
						}
					}
				}
			},

			destructor() {
				var instance = this;

				if (instance._inputMirror) {
					instance._inputMirror.remove();
				}
			},

			initializer() {
				var instance = this;

				instance._bindUIACTextarea();
			}
		};

		Liferay.AutoCompleteTextarea = A.Base.create(
			'liferayautocompletetextarea',
			A.AutoComplete,
			[Liferay.AutoCompleteInputBase, AutoCompleteTextarea],
			{},
			{
				CSS_PREFIX: A.ClassNameManager.getClassName('aclist')
			}
		);
	},
	'',
	{
		requires: ['liferay-autocomplete-input']
	}
);
