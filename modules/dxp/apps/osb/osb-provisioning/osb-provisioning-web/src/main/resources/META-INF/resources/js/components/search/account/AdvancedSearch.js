/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayPanel from '@clayui/panel';
import PropTypes from 'prop-types';
import React, {useRef, useState} from 'react';

import useClickOutside from '../../../hooks/useClickOutside';
import {ACCOUNTS_PORTLET_NAMESPACE as NAMESPACE} from '../../../utilities/constants';
import {submitOnEnter} from '../../../utilities/helpers';
import AccountDetails from './AccountDetails';
import Categorization from './Categorization';
import Dates from './Dates';

const AdvancedSearch = React.forwardRef(
	(
		{
			activeSLANames,
			clickOutsideCallback,
			countryNames,
			formAction,
			regionNames,
			selectAccountURL,
			selectFirstLineSupportURL,
			selectPartnerURL,
			subscriptionStateNames,
			tierNames
		},
		ref
	) => {
		const [isAndOperator, setIsAndOperator] = useState(true);

		const formRef = useRef();

		useClickOutside(clickOutsideCallback, ref);

		function handleOnCheck() {
			setIsAndOperator(!isAndOperator);
		}

		function handleOnKeyDown(event) {
			submitOnEnter(event, formRef);
		}

		return (
			<div className="advanced-search-container" id="advancedSearch">
				<form
					action={formAction}
					method="get"
					name="advancedSearch"
					onKeyDown={handleOnKeyDown}
					ref={formRef}
				>
					<input
						name="p_p_id"
						type="hidden"
						value="com_liferay_osb_provisioning_web_portlet_AccountsPortlet"
					/>
					<input name="p_p_lifecycle" type="hidden" value="0" />
					<input
						name={`${NAMESPACE}advancedSearch`}
						type="hidden"
						value="true"
					/>

					<div className="form-group search-match">
						<h5 className="form-check-inline">
							{Liferay.Language.get('match')}:
						</h5>

						<div className="form-check form-check-inline">
							<label className="form-check-label">
								<input
									checked={isAndOperator}
									className="form-check-input"
									name={`${NAMESPACE}andOperator`}
									onChange={() => handleOnCheck()}
									type="radio"
									value={true}
								/>
								<span className="form-check-label-text">
									{Liferay.Language.get('all')}
								</span>
							</label>
						</div>

						<div className="form-check form-check-inline">
							<label className="form-check-label">
								<input
									checked={!isAndOperator}
									className="form-check-input"
									name={`${NAMESPACE}andOperator`}
									onChange={() => handleOnCheck()}
									type="radio"
									value={false}
								/>
								<span className="form-check-label-text">
									{Liferay.Language.get('any')}
								</span>
							</label>
						</div>
					</div>

					<ClayPanel
						collapsable
						defaultExpanded={true}
						displayTitle={Liferay.Language.get('account')}
						displayType="secondary"
						showCollapseIcon={true}
					>
						<AccountDetails
							countryNames={countryNames}
							selectAccountURL={selectAccountURL}
							selectFirstLineSupportURL={
								selectFirstLineSupportURL
							}
							selectPartnerURL={selectPartnerURL}
						/>
					</ClayPanel>

					<ClayPanel
						collapsable
						displayTitle={Liferay.Language.get('categorization')}
						displayType="secondary"
						showCollapseIcon={true}
					>
						<Categorization
							activeSLANames={activeSLANames}
							regionNames={regionNames}
							subscriptionStateNames={subscriptionStateNames}
							tierNames={tierNames}
						/>
					</ClayPanel>

					<ClayPanel
						collapsable
						displayTitle={Liferay.Language.get('dates')}
						displayType="secondary"
						showCollapseIcon={true}
					>
						<Dates />
					</ClayPanel>

					<div
						className="button-holder button-holder-lg"
						role="group"
					>
						<button
							className="btn btn-secondary"
							role="button"
							type="reset"
						>
							{Liferay.Language.get('clear')}
						</button>

						<button
							className="btn btn-primary"
							role="button"
							type="submit"
						>
							{Liferay.Language.get('search')}
						</button>
					</div>
				</form>
			</div>
		);
	}
);

AdvancedSearch.propTypes = {
	activeSLANames: PropTypes.array.isRequired,
	clickOutsideCallback: PropTypes.func.isRequired,
	countryNames: PropTypes.array.isRequired,
	formAction: PropTypes.string.isRequired,
	regionNames: PropTypes.array.isRequired,
	selectAccountURL: PropTypes.string.isRequired,
	selectFirstLineSupportURL: PropTypes.string.isRequired,
	selectPartnerURL: PropTypes.string.isRequired,
	subscriptionStateNames: PropTypes.array.isRequired,
	tierNames: PropTypes.array.isRequired
};

export default AdvancedSearch;
