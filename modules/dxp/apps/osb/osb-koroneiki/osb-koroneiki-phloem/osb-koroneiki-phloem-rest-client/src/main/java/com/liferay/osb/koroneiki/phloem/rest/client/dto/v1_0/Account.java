/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.client.function.UnsafeSupplier;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.AccountSerDes;

import java.io.Serializable;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author Amos Fong
 * @generated
 */
@Generated("")
public class Account implements Cloneable, Serializable {

	public static Account toDTO(String json) {
		return AccountSerDes.toDTO(json);
	}

	public Team[] getAssignedTeams() {
		return assignedTeams;
	}

	public void setAssignedTeams(Team[] assignedTeams) {
		this.assignedTeams = assignedTeams;
	}

	public void setAssignedTeams(
		UnsafeSupplier<Team[], Exception> assignedTeamsUnsafeSupplier) {

		try {
			assignedTeams = assignedTeamsUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Team[] assignedTeams;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setCode(UnsafeSupplier<String, Exception> codeUnsafeSupplier) {
		try {
			code = codeUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String code;

	public String getContactEmailAddress() {
		return contactEmailAddress;
	}

	public void setContactEmailAddress(String contactEmailAddress) {
		this.contactEmailAddress = contactEmailAddress;
	}

	public void setContactEmailAddress(
		UnsafeSupplier<String, Exception> contactEmailAddressUnsafeSupplier) {

		try {
			contactEmailAddress = contactEmailAddressUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String contactEmailAddress;

	public Contact[] getContacts() {
		return contacts;
	}

	public void setContacts(Contact[] contacts) {
		this.contacts = contacts;
	}

	public void setContacts(
		UnsafeSupplier<Contact[], Exception> contactsUnsafeSupplier) {

		try {
			contacts = contactsUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Contact[] contacts;

	public Contact[] getCustomerContacts() {
		return customerContacts;
	}

	public void setCustomerContacts(Contact[] customerContacts) {
		this.customerContacts = customerContacts;
	}

	public void setCustomerContacts(
		UnsafeSupplier<Contact[], Exception> customerContactsUnsafeSupplier) {

		try {
			customerContacts = customerContactsUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Contact[] customerContacts;

	public DataRegion getDataRegion() {
		return dataRegion;
	}

	public String getDataRegionAsString() {
		if (dataRegion == null) {
			return null;
		}

		return dataRegion.toString();
	}

	public void setDataRegion(DataRegion dataRegion) {
		this.dataRegion = dataRegion;
	}

	public void setDataRegion(
		UnsafeSupplier<DataRegion, Exception> dataRegionUnsafeSupplier) {

		try {
			dataRegion = dataRegionUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected DataRegion dataRegion;

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public void setDateCreated(
		UnsafeSupplier<Date, Exception> dateCreatedUnsafeSupplier) {

		try {
			dateCreated = dateCreatedUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Date dateCreated;

	public Date getDateModified() {
		return dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	public void setDateModified(
		UnsafeSupplier<Date, Exception> dateModifiedUnsafeSupplier) {

		try {
			dateModified = dateModifiedUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Date dateModified;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDescription(
		UnsafeSupplier<String, Exception> descriptionUnsafeSupplier) {

		try {
			description = descriptionUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String description;

	public Entitlement[] getEntitlements() {
		return entitlements;
	}

	public void setEntitlements(Entitlement[] entitlements) {
		this.entitlements = entitlements;
	}

	public void setEntitlements(
		UnsafeSupplier<Entitlement[], Exception> entitlementsUnsafeSupplier) {

		try {
			entitlements = entitlementsUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Entitlement[] entitlements;

	public ExternalLink[] getExternalLinks() {
		return externalLinks;
	}

	public void setExternalLinks(ExternalLink[] externalLinks) {
		this.externalLinks = externalLinks;
	}

	public void setExternalLinks(
		UnsafeSupplier<ExternalLink[], Exception> externalLinksUnsafeSupplier) {

		try {
			externalLinks = externalLinksUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected ExternalLink[] externalLinks;

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public void setFaxNumber(
		UnsafeSupplier<String, Exception> faxNumberUnsafeSupplier) {

		try {
			faxNumber = faxNumberUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String faxNumber;

	public Boolean getInternal() {
		return internal;
	}

	public void setInternal(Boolean internal) {
		this.internal = internal;
	}

	public void setInternal(
		UnsafeSupplier<Boolean, Exception> internalUnsafeSupplier) {

		try {
			internal = internalUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Boolean internal;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setKey(UnsafeSupplier<String, Exception> keyUnsafeSupplier) {
		try {
			key = keyUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String key;

	public Language getLanguage() {
		return language;
	}

	public String getLanguageAsString() {
		if (language == null) {
			return null;
		}

		return language.toString();
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public void setLanguage(
		UnsafeSupplier<Language, Exception> languageUnsafeSupplier) {

		try {
			language = languageUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Language language;

	public Long getLogoId() {
		return logoId;
	}

	public void setLogoId(Long logoId) {
		this.logoId = logoId;
	}

	public void setLogoId(
		UnsafeSupplier<Long, Exception> logoIdUnsafeSupplier) {

		try {
			logoId = logoIdUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long logoId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setName(UnsafeSupplier<String, Exception> nameUnsafeSupplier) {
		try {
			name = nameUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String name;

	public String getParentAccountKey() {
		return parentAccountKey;
	}

	public void setParentAccountKey(String parentAccountKey) {
		this.parentAccountKey = parentAccountKey;
	}

	public void setParentAccountKey(
		UnsafeSupplier<String, Exception> parentAccountKeyUnsafeSupplier) {

		try {
			parentAccountKey = parentAccountKeyUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String parentAccountKey;

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setPhoneNumber(
		UnsafeSupplier<String, Exception> phoneNumberUnsafeSupplier) {

		try {
			phoneNumber = phoneNumberUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String phoneNumber;

	public PostalAddress[] getPostalAddresses() {
		return postalAddresses;
	}

	public void setPostalAddresses(PostalAddress[] postalAddresses) {
		this.postalAddresses = postalAddresses;
	}

	public void setPostalAddresses(
		UnsafeSupplier<PostalAddress[], Exception>
			postalAddressesUnsafeSupplier) {

		try {
			postalAddresses = postalAddressesUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected PostalAddress[] postalAddresses;

	public ProductPurchase[] getProductPurchases() {
		return productPurchases;
	}

	public void setProductPurchases(ProductPurchase[] productPurchases) {
		this.productPurchases = productPurchases;
	}

	public void setProductPurchases(
		UnsafeSupplier<ProductPurchase[], Exception>
			productPurchasesUnsafeSupplier) {

		try {
			productPurchases = productPurchasesUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected ProductPurchase[] productPurchases;

	public String getProfileEmailAddress() {
		return profileEmailAddress;
	}

	public void setProfileEmailAddress(String profileEmailAddress) {
		this.profileEmailAddress = profileEmailAddress;
	}

	public void setProfileEmailAddress(
		UnsafeSupplier<String, Exception> profileEmailAddressUnsafeSupplier) {

		try {
			profileEmailAddress = profileEmailAddressUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String profileEmailAddress;

	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

	public void setProperties(
		UnsafeSupplier<Map<String, String>, Exception>
			propertiesUnsafeSupplier) {

		try {
			properties = propertiesUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Map<String, String> properties;

	public Region getRegion() {
		return region;
	}

	public String getRegionAsString() {
		if (region == null) {
			return null;
		}

		return region.toString();
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public void setRegion(
		UnsafeSupplier<Region, Exception> regionUnsafeSupplier) {

		try {
			region = regionUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Region region;

	public Status getStatus() {
		return status;
	}

	public String getStatusAsString() {
		if (status == null) {
			return null;
		}

		return status.toString();
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setStatus(
		UnsafeSupplier<Status, Exception> statusUnsafeSupplier) {

		try {
			status = statusUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Status status;

	public Tier getTier() {
		return tier;
	}

	public String getTierAsString() {
		if (tier == null) {
			return null;
		}

		return tier.toString();
	}

	public void setTier(Tier tier) {
		this.tier = tier;
	}

	public void setTier(UnsafeSupplier<Tier, Exception> tierUnsafeSupplier) {
		try {
			tier = tierUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Tier tier;

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public void setWebsite(
		UnsafeSupplier<String, Exception> websiteUnsafeSupplier) {

		try {
			website = websiteUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String website;

	public Contact[] getWorkerContacts() {
		return workerContacts;
	}

	public void setWorkerContacts(Contact[] workerContacts) {
		this.workerContacts = workerContacts;
	}

	public void setWorkerContacts(
		UnsafeSupplier<Contact[], Exception> workerContactsUnsafeSupplier) {

		try {
			workerContacts = workerContactsUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Contact[] workerContacts;

	@Override
	public Account clone() throws CloneNotSupportedException {
		return (Account)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Account)) {
			return false;
		}

		Account account = (Account)object;

		return Objects.equals(toString(), account.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return AccountSerDes.toJSON(this);
	}

	public static enum DataRegion {

		BRAZIL("Brazil"), HUNGARY("Hungary"), JAPAN("Japan"),
		UNITED_STATES("United States");

		public static DataRegion create(String value) {
			for (DataRegion dataRegion : values()) {
				if (Objects.equals(dataRegion.getValue(), value) ||
					Objects.equals(dataRegion.name(), value)) {

					return dataRegion;
				}
			}

			return null;
		}

		public String getValue() {
			return _value;
		}

		@Override
		public String toString() {
			return _value;
		}

		private DataRegion(String value) {
			_value = value;
		}

		private final String _value;

	}

	public static enum Language {

		CHINESE("Chinese"), ENGLISH("English"), JAPANESE("Japanese"),
		PORTUGUESE("Portuguese"), SPANISH("Spanish");

		public static Language create(String value) {
			for (Language language : values()) {
				if (Objects.equals(language.getValue(), value) ||
					Objects.equals(language.name(), value)) {

					return language;
				}
			}

			return null;
		}

		public String getValue() {
			return _value;
		}

		@Override
		public String toString() {
			return _value;
		}

		private Language(String value) {
			_value = value;
		}

		private final String _value;

	}

	public static enum Region {

		AUSTRALIA("Australia"), BRAZIL("Brazil"), CHINA("China"),
		GLOBAL("Global"), HUNGARY("Hungary"), INDIA("India"), JAPAN("Japan"),
		SPAIN("Spain"), UNITED_STATES("United States");

		public static Region create(String value) {
			for (Region region : values()) {
				if (Objects.equals(region.getValue(), value) ||
					Objects.equals(region.name(), value)) {

					return region;
				}
			}

			return null;
		}

		public String getValue() {
			return _value;
		}

		@Override
		public String toString() {
			return _value;
		}

		private Region(String value) {
			_value = value;
		}

		private final String _value;

	}

	public static enum Status {

		ACTIVE("Active"), CLOSED("Closed");

		public static Status create(String value) {
			for (Status status : values()) {
				if (Objects.equals(status.getValue(), value) ||
					Objects.equals(status.name(), value)) {

					return status;
				}
			}

			return null;
		}

		public String getValue() {
			return _value;
		}

		@Override
		public String toString() {
			return _value;
		}

		private Status(String value) {
			_value = value;
		}

		private final String _value;

	}

	public static enum Tier {

		T1("T1"), T2("T2"), T3("T3"), T4("T4");

		public static Tier create(String value) {
			for (Tier tier : values()) {
				if (Objects.equals(tier.getValue(), value) ||
					Objects.equals(tier.name(), value)) {

					return tier;
				}
			}

			return null;
		}

		public String getValue() {
			return _value;
		}

		@Override
		public String toString() {
			return _value;
		}

		private Tier(String value) {
			_value = value;
		}

		private final String _value;

	}

}