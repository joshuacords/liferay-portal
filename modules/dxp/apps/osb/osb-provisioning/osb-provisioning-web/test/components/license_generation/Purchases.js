/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 */

import {cleanup, fireEvent, render, wait, within} from '@testing-library/react';
import React from 'react';

import Purchases from '../../../src/main/resources/META-INF/resources/js/components/license_generation/Purchases';
import {
	License,
	NewLicenseProvider
} from '../../../src/main/resources/META-INF/resources/js/hooks/newLicense';
import {PermissionsProvider} from '../../../src/main/resources/META-INF/resources/js/hooks/permissions';
import {
	CURRENT_TIME,
	DASH
} from '../../../src/main/resources/META-INF/resources/js/utilities/constants';
import {
	formatDate,
	generateNewDateByYear
} from '../../../src/main/resources/META-INF/resources/js/utilities/date';

function renderPurchases({
	initialLicense = new License(),
	permission = true,
	props = {}
} = {}) {
	return render(
		<NewLicenseProvider initialLicense={initialLicense}>
			<PermissionsProvider
				permissions={{updateDatePermission: permission}}
			>
				<Purchases
					purchased={[
						{
							endDate: '2020-04-16',
							instanceSize: 1,
							licenseKeysGenerated: '0 / 1',
							perpetual: false,
							productPurchaseKey: 'PURCHKEY-123',
							startDate: '2020-03-17'
						},
						{
							endDate: '',
							instanceSize: 1,
							licenseKeysGenerated: '1 / 1',
							perpetual: true,
							productPurchaseKey: 'PURCHKEY-456',
							startDate: ''
						}
					]}
					{...props}
				/>
			</PermissionsProvider>
		</NewLicenseProvider>
	);
}

describe('Purchases', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderPurchases();

		expect(container).toBeTruthy();
	});

	it('displays a Start Date column', () => {
		const {getByText} = renderPurchases();

		getByText('start-date');
	});

	it('displays an Expiration Date column', () => {
		const {getByText} = renderPurchases();

		getByText('expiration-date');
	});

	it('displays an Instance Size column', () => {
		const {getByText} = renderPurchases();

		getByText('instance-size');
	});

	it('displays a License Keys Generated column', () => {
		const {getByText} = renderPurchases();

		getByText('licenses-generated');
	});

	it('always displays a Detached section', () => {
		const {getByText} = renderPurchases();

		getByText('detached');
	});

	it('displays only the Active section for purchased subscriptions if none are expired', () => {
		const {getByText, queryByText} = renderPurchases({
			props: {
				purchased: [
					{
						endDate: '',
						instanceSize: 1,
						licenseKeysGenerated: '1 / 1',
						perpetual: true,
						productPurchaseKey: 'PURCHKEY-123',
						startDate: ''
					},
					{
						endDate: '',
						instanceSize: 5,
						licenseKeysGenerated: '1 / 1',
						perpetual: true,
						productPurchaseKey: 'PURCHKEY-456',
						startDate: ''
					}
				]
			}
		});

		getByText('active-subscriptions');
		expect(queryByText('expired-subscriptions')).toBeFalsy();
	});

	it('displays only the Expired section if no subscriptions are active', () => {
		const {getByText, queryByText} = renderPurchases({
			props: {
				purchased: [
					{
						endDate: '2020-04-16',
						instanceSize: 1,
						licenseKeysGenerated: '0 / 1',
						perpetual: false,
						productPurchaseKey: 'PURCHKEY-123',
						startDate: '2020-03-17'
					},
					{
						endDate: '2020-05-16',
						instanceSize: 2,
						licenseKeysGenerated: '1 / 1',
						perpetual: false,
						productPurchaseKey: 'PURCHKEY-456',
						startDate: '2019-05-16'
					}
				]
			}
		});

		getByText('expired-subscriptions');
		expect(queryByText('active-subscriptions')).toBeFalsy();
	});

	it('renders the Detached section with default values (dash for license generated) if no purchased product is provided', () => {
		const {getByText} = renderPurchases({
			props: {
				purchased: []
			}
		});

		getByText('-');
		expect(getByText('choose').disabled).toBeTruthy();
	});

	it('allows the user to select an Instance Size from a list of choices in the Detached section', () => {
		const {getByLabelText} = renderPurchases({
			props: {
				detached: {
					instanceSizes: [0, 1, 2, 3, 4],
					licenseKeysGenerated: '0'
				}
			}
		});

		within(getByLabelText('instance-size')).getByText(DASH);
		within(getByLabelText('instance-size')).getByText('1');
		within(getByLabelText('instance-size')).getByText('2');
		within(getByLabelText('instance-size')).getByText('3');
		within(getByLabelText('instance-size')).getByText('4');
	});

	it('displays the Choose button as disabled in the Detached section until an instance size is selected', () => {
		const {getAllByText, getByLabelText} = renderPurchases({
			props: {
				detached: {
					instanceSizes: [0, 1, 2, 3, 4],
					licenseKeysGenerated: '0'
				}
			}
		});

		const chooseBtns = getAllByText('choose');

		expect(chooseBtns[2].disabled).toBeTruthy();

		fireEvent.change(getByLabelText('instance-size'), {target: {value: 1}});

		expect(chooseBtns[2].disabled).toBeFalsy();
	});

	it('displays a Choose button for each Purchase section', () => {
		const {getAllByText} = renderPurchases();

		expect(getAllByText('choose').length).toBe(3);
	});

	describe('Date Fields', () => {
		describe('Choose Button behavior', () => {
			it('displays the Choose button as disabled when a date field is left empty', () => {
				const {
					getAllByPlaceholderText,
					getAllByText
				} = renderPurchases();

				const firstChooseBtn = getAllByText('choose')[0];

				expect(firstChooseBtn.disabled).toBeFalsy();

				fireEvent.change(getAllByPlaceholderText('YYYY-MM-DD')[0], {
					target: {value: ''}
				});

				expect(firstChooseBtn.disabled).toBeTruthy();
			});

			it('displays the Choose button as disabled when an invalid date is entered', () => {
				const {
					getAllByPlaceholderText,
					getAllByText
				} = renderPurchases();

				const firstChooseBtn = getAllByText('choose')[0];

				expect(firstChooseBtn.disabled).toBeFalsy();

				fireEvent.change(getAllByPlaceholderText('YYYY-MM-DD')[0], {
					target: {value: '2021-04-32'}
				});

				expect(firstChooseBtn.disabled).toBeTruthy();
			});

			it('displays the Choose button as disabled when the start date entered is later than the expiration date', () => {
				const {
					getAllByPlaceholderText,
					getAllByText
				} = renderPurchases();

				const firstChooseBtn = getAllByText('choose')[0];

				expect(firstChooseBtn.disabled).toBeFalsy();

				fireEvent.change(getAllByPlaceholderText('YYYY-MM-DD')[0], {
					target: {value: '2127-04-16'}
				});

				expect(firstChooseBtn.disabled).toBeTruthy();
			});

			describe('Limited Privilege, Detached Section', () => {
				describe('when Type is Enterpirse, Limited, OEM, or Virtual Cluster', () => {
					it('disables the Choose button if the user selects an Expiration Date that is not within 365 days from the start date', async () => {
						const {
							container,
							getAllByPlaceholderText
						} = renderPurchases({
							initialLicense: new License({
								licenseEntry: {
									licenseEntryType: 'virtual_cluster'
								}
							}),
							permission: false,
							props: {
								detached: {
									instanceSizes: [1, 2, 3, 4],
									licenseKeysGenerated: '0'
								},
								purchased: []
							}
						});

						fireEvent.change(
							getAllByPlaceholderText('YYYY-MM-DD')[1],
							{
								target: {
									value: formatDate(
										generateNewDateByYear(CURRENT_TIME, 2)
									)
								}
							}
						);

						await wait(() =>
							expect(
								within(container).getByText('choose').disabled
							).toBeTruthy()
						);
					});

					it('disables the Choose button if the user selects a Start Date that is further back than 365 days from the expiration date', async () => {
						const {
							container,
							getAllByPlaceholderText
						} = renderPurchases({
							initialLicense: new License({
								licenseEntry: {
									licenseEntryType: 'virtual_cluster'
								}
							}),
							permission: false,
							props: {
								detached: {
									instanceSizes: [1, 2, 3, 4],
									licenseKeysGenerated: '0'
								},
								purchased: []
							}
						});

						fireEvent.change(
							getAllByPlaceholderText('YYYY-MM-DD')[0],
							{
								target: {
									value: '2020-02-10'
								}
							}
						);

						await wait(() =>
							expect(
								within(container).getByText('choose').disabled
							).toBeTruthy()
						);
					});
				});
			});
		});
	});
});
