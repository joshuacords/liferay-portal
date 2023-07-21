/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import RuleList from '../../../src/main/resources/META-INF/resources/js/components/RuleList/RuleList.es';

let component;

const spritemap = 'icons.svg';

const pages = [
	{
		rows: [
			{
				columns: [
					{
						fields: [
							{
								fieldName: 'text1',
								label: 'label text 1'
							},
							{
								fieldName: 'text2',
								label: 'label text 2',
								type: 'text'
							}
						]
					}
				]
			}
		]
	}
];

const brokenRuleConfig = {
	pages,
	rules: [
		{
			actions: [
				{
					action: 'require',
					label: 'label text 1',
					target: 'text1'
				}
			],
			conditions: [
				{
					operands: [
						{
							type: 'field',
							value: 'text1'
						},
						{
							type: '',
							value: ''
						}
					],
					operator: 'contains'
				},
				{
					operands: [
						{
							type: 'field',
							value: 'text1'
						},
						{
							type: 'field',
							value: 'text2'
						}
					],
					operator: 'equals-to'
				}
			],
			['logical-operator']: 'OR'
		}
	],
	spritemap
};

const configDefault = {
	pages,
	rules: [
		{
			actions: [
				{
					action: 'require',
					expression: '[x+2]',
					label: 'label text 1',
					target: 'text1'
				}
			],
			conditions: [
				{
					operands: [
						{
							type: 'field',
							value: 'text1'
						},
						{
							type: 'value',
							value: 'value 2'
						}
					],
					operator: 'equals-to'
				}
			],
			['logical-operator']: 'OR'
		}
	],
	spritemap
};

describe('RuleList', () => {
	beforeEach(() => {
		jest.useFakeTimers();
	});

	afterEach(() => {
		if (component) {
			component.dispose();
		}
	});

	it('returns the field label for each action', () => {
		component = new RuleList(configDefault);

		const contextLabel =
			component.pages[0].rows[0].columns[0].fields[0].label;

		const actionLabel = component.rules[0].actions[0].label;

		jest.runAllTimers();

		expect(actionLabel).toEqual(contextLabel);
	});

	it('shows message when rule list is empty', () => {
		component = new RuleList({
			pages,
			rules: [],
			spritemap,
			strings: {
				emptyListText:
					'there-are-no-rules-yet-click-on-plus-icon-below-to-add-the-first'
			}
		});

		expect(component).toMatchSnapshot();
	});

	it('shows rule with json type operand', () => {
		const rule = {
			actions: [
				{
					action: 'require',
					label: 'label text 1',
					target: 'text1'
				}
			],
			conditions: [
				{
					operands: [
						{
							type: 'field',
							value: 'grid1'
						},
						{
							type: 'json',
							value: '{"value1" : "value2"}'
						}
					],
					operator: 'equals-to'
				}
			],
			['logical-operator']: 'OR'
		};

		const config = {
			pages: [
				{
					rows: [
						{
							columns: [
								{
									fields: [
										{
											fieldName: 'grid1',
											label: 'label grid 1'
										},
										{
											fieldName: 'text1',
											label: 'label text 1'
										}
									]
								}
							]
						}
					]
				}
			],
			rules: [rule],
			spritemap
		};

		component = new RuleList(config);

		expect(component._getOperandLabel(rule.conditions[0].operands, 1)).toBe(
			'value1:value2'
		);
	});

	it('shows the label broken rule when a rule is incomplete', () => {
		component = new RuleList(brokenRuleConfig);

		expect(component).toMatchSnapshot();
	});
});
