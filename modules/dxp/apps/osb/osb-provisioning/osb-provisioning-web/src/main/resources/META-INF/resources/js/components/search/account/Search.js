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

import ClayAutocomplete from '@clayui/autocomplete';
import ClayDropDown from '@clayui/drop-down';
import fuzzy from 'fuzzy';
import debounce from 'lodash.debounce';
import PropTypes from 'prop-types';
import React, {useRef, useState} from 'react';

import {ACCOUNTS_PORTLET_NAMESPACE as NAMESPACE} from '../../../utilities/constants';
import {request} from '../../../utilities/helpers';
import {
	getAccountSearchFilterDisplayName,
	getSearchParameter,
	getSearchPlaceholder,
	setAdvancedSearchAriaAttributes
} from '../../../utilities/search';
import AdvancedSearch from './AdvancedSearch';

const MAX_RESULTS = 7;

const AutocompleteItem = React.forwardRef(
	(
		{innerRef, match = '', secondaryValue = '', value, ...otherProps},
		ref
	) => {
		const fuzzyMatch = fuzzy.match(match, value);

		return (
			<ClayDropDown.Item {...otherProps} innerRef={innerRef} ref={ref}>
				<>
					{match && fuzzyMatch ? (
						<div className="main-item">{fuzzyMatch.rendered}</div>
					) : (
						<div className="main-item">{value}</div>
					)}

					{!!secondaryValue && (
						<div className="secondary-information">
							{secondaryValue}
						</div>
					)}
				</>
			</ClayDropDown.Item>
		);
	}
);

AutocompleteItem.propTypes = {
	href: PropTypes.string,
	match: PropTypes.string,
	secondaryValue: PropTypes.string,
	value: PropTypes.string
};

function Search({
	accountsHomeURL = '',
	activeSLANames,
	countryNames,
	regionNames,
	resourceURL,
	selectAccountURL,
	selectFirstLineSupportURL,
	selectPartnerURL,
	subscriptionStateNames,
	tierNames
}) {
	const [error, setError] = useState(false);
	const [keywords, setKeywords] = useState(
		getSearchParameter(`${NAMESPACE}accountSearchKeywords`)
	);
	const [results, setResults] = useState([]);
	const [showAdvancedSearch, setShowAdvancedSearch] = useState(false);

	const searchRef = useRef();
	const {current: requestSearchResults} = useRef(
		debounce(value => {
			request(
				resourceURL,
				{
					autocompleteKeywords: value,
					maxResults: MAX_RESULTS
				},
				'json',
				'get'
			)
				.then(data => {
					if (data.length === 0) {
						setError(true);
					}
					else {
						setError(false);
						setResults(data);
					}
				})
				.catch(err => {
					setError(true);

					console.error(`Request to search failed with: ${err}`);
				});
		}, 200)
	);

	function buildSearchResultsURL() {
		return `${accountsHomeURL}&${NAMESPACE}accountSearchKeywords=${keywords}`;
	}

	function handleClickOutside(event) {
		const activeDatePicker = document.querySelector(
			'.date-picker-dropdown-menu.show'
		);
		const selectedItemChangeContainer = document.getElementById(
			'selectedItemChange'
		);

		if (
			(!activeDatePicker && !selectedItemChangeContainer) ||
			(activeDatePicker && !activeDatePicker.contains(event.target)) ||
			(selectedItemChangeContainer &&
				!selectedItemChangeContainer.contains(event.target))
		) {
			setShowAdvancedSearch(false);

			handleOnToggle();
		}
	}

	function handleOnChange(event) {
		setKeywords(event.target.value);

		requestSearchResults(event.target.value);
	}

	function handleOnKeyDown(event) {
		if (event.keyCode === 13) {
			handleOnKeywordSearch();
		}
	}

	function handleOnKeywordSearch() {
		window.location.assign(buildSearchResultsURL());
	}

	function handleOnToggle() {
		const newState = !showAdvancedSearch;

		setShowAdvancedSearch(newState);

		setAdvancedSearchAriaAttributes('accountAdvancedSearchBtn', newState);
	}

	return (
		<div ref={searchRef}>
			<ClayAutocomplete>
				<ClayAutocomplete.Input
					className="search-input"
					disabled={showAdvancedSearch}
					onChange={handleOnChange}
					onKeyDown={handleOnKeyDown}
					placeholder={getSearchPlaceholder({
						defaultPlaceholder: Liferay.Language.get(
							'search-accounts'
						),
						getFilterDisplayNameCallback: getAccountSearchFilterDisplayName,
						namespace: NAMESPACE
					})}
					value={keywords}
				/>

				<div className="advanced-search-trigger">
					<button
						aria-controls="advancedSearch"
						aria-expanded="false"
						aria-label={Liferay.Language.get(
							'open-advanced-search'
						)}
						className="advanced-search-btn btn btn-monospaced btn-sm"
						id="accountAdvancedSearchBtn"
						onClick={handleOnToggle}
					>
						<svg
							aria-label={Liferay.Language.get(
								'advanced-search-icon'
							)}
							className="lexicon-icon lexicon-icon-advanced-search"
							role="image"
						>
							<use xlinkHref="#caret-bottom" />
						</svg>
					</button>
				</div>

				<button
					aria-label={Liferay.Language.get('keyword-search')}
					className="btn btn-default search-btn"
					disabled={showAdvancedSearch}
					onClick={handleOnKeywordSearch}
					role="link"
					type="button"
				>
					<svg
						aria-hidden="true"
						aria-label={Liferay.Language.get('search-icon')}
						className="lexicon-icon lexicon-icon-search"
						role="image"
					>
						<use xlinkHref="#search" />
					</svg>
				</button>

				{!showAdvancedSearch && (
					<ClayAutocomplete.DropDown active={keywords}>
						{error && (
							<ul className="list-unstyled">
								<ClayDropDown.Item className="disabled">
									{Liferay.Language.get(
										'no-results-were-found'
									)}
								</ClayDropDown.Item>
							</ul>
						)}

						{!error && (
							<>
								<ClayDropDown.ItemList>
									{results.map(result => (
										<AutocompleteItem
											href={`${result.url}&${NAMESPACE}accountSearchKeywords=${keywords}`}
											key={result.key}
											match={keywords}
											secondaryValue={result.code}
											value={result.name}
										/>
									))}
								</ClayDropDown.ItemList>

								{results.length === MAX_RESULTS && (
									<a
										className="all-results dropdown-item"
										href={buildSearchResultsURL()}
									>
										{Liferay.Language.get(
											'see-all-results'
										)}
									</a>
								)}
							</>
						)}
					</ClayAutocomplete.DropDown>
				)}
			</ClayAutocomplete>

			{showAdvancedSearch && (
				<AdvancedSearch
					activeSLANames={activeSLANames}
					clickOutsideCallback={handleClickOutside}
					countryNames={countryNames}
					formAction={accountsHomeURL}
					ref={searchRef}
					regionNames={regionNames}
					selectAccountURL={selectAccountURL}
					selectFirstLineSupportURL={selectFirstLineSupportURL}
					selectPartnerURL={selectPartnerURL}
					subscriptionStateNames={subscriptionStateNames}
					tierNames={tierNames}
				/>
			)}
		</div>
	);
}

Search.propTypes = {
	accountsHomeURL: PropTypes.string.isRequired,
	activeSLANames: PropTypes.array.isRequired,
	countryNames: PropTypes.array.isRequired,
	regionNames: PropTypes.array.isRequired,
	resourceURL: PropTypes.string.isRequired,
	selectAccountURL: PropTypes.string.isRequired,
	selectFirstLineSupportURL: PropTypes.string.isRequired,
	selectPartnerURL: PropTypes.string.isRequired,
	subscriptionStateNames: PropTypes.array.isRequired,
	tierNames: PropTypes.array.isRequired
};

export default Search;
