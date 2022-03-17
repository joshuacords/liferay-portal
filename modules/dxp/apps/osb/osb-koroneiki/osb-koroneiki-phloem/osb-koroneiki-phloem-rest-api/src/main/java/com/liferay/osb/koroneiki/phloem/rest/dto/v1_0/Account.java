/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.koroneiki.phloem.rest.dto.v1_0;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import com.liferay.petra.function.UnsafeSupplier;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;
import com.liferay.portal.vulcan.util.ObjectMapperUtil;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.annotation.Generated;

import javax.validation.Valid;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Amos Fong
 * @generated
 */
@Generated("")
@GraphQLName(description = "Represents an account.", value = "Account")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "Account")
public class Account implements Serializable {

	public static Account toDTO(String json) {
		return ObjectMapperUtil.readValue(Account.class, json);
	}

	public static Account unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(Account.class, json);
	}

	@Schema(
		description = "The teams that are assigned to this account. Optional field that can retrieved with nestedFields."
	)
	@Valid
	public Team[] getAssignedTeams() {
		return assignedTeams;
	}

	public void setAssignedTeams(Team[] assignedTeams) {
		this.assignedTeams = assignedTeams;
	}

	@JsonIgnore
	public void setAssignedTeams(
		UnsafeSupplier<Team[], Exception> assignedTeamsUnsafeSupplier) {

		try {
			assignedTeams = assignedTeamsUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "The teams that are assigned to this account. Optional field that can retrieved with nestedFields."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Team[] assignedTeams;

	@Schema(description = "The code of the account.")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@JsonIgnore
	public void setCode(UnsafeSupplier<String, Exception> codeUnsafeSupplier) {
		try {
			code = codeUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The code of the account.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String code;

	@Schema(description = "The account's contact email address.")
	public String getContactEmailAddress() {
		return contactEmailAddress;
	}

	public void setContactEmailAddress(String contactEmailAddress) {
		this.contactEmailAddress = contactEmailAddress;
	}

	@JsonIgnore
	public void setContactEmailAddress(
		UnsafeSupplier<String, Exception> contactEmailAddressUnsafeSupplier) {

		try {
			contactEmailAddress = contactEmailAddressUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The account's contact email address.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String contactEmailAddress;

	@Schema(
		description = "The account's contacts. Optional field that can retrieved with nestedFields."
	)
	@Valid
	public Contact[] getContacts() {
		return contacts;
	}

	public void setContacts(Contact[] contacts) {
		this.contacts = contacts;
	}

	@JsonIgnore
	public void setContacts(
		UnsafeSupplier<Contact[], Exception> contactsUnsafeSupplier) {

		try {
			contacts = contactsUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "The account's contacts. Optional field that can retrieved with nestedFields."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Contact[] contacts;

	@Schema(
		description = "The account's customer contacts. Optional field that can retrieved with nestedFields."
	)
	@Valid
	public Contact[] getCustomerContacts() {
		return customerContacts;
	}

	public void setCustomerContacts(Contact[] customerContacts) {
		this.customerContacts = customerContacts;
	}

	@JsonIgnore
	public void setCustomerContacts(
		UnsafeSupplier<Contact[], Exception> customerContactsUnsafeSupplier) {

		try {
			customerContacts = customerContactsUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "The account's customer contacts. Optional field that can retrieved with nestedFields."
	)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Contact[] customerContacts;

	@Schema(
		description = "The region where we should store the account's customer owned data."
	)
	@Valid
	public DataRegion getDataRegion() {
		return dataRegion;
	}

	@JsonIgnore
	public String getDataRegionAsString() {
		if (dataRegion == null) {
			return null;
		}

		return dataRegion.toString();
	}

	public void setDataRegion(DataRegion dataRegion) {
		this.dataRegion = dataRegion;
	}

	@JsonIgnore
	public void setDataRegion(
		UnsafeSupplier<DataRegion, Exception> dataRegionUnsafeSupplier) {

		try {
			dataRegion = dataRegionUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "The region where we should store the account's customer owned data."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected DataRegion dataRegion;

	@Schema(description = "The account's creation date.")
	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@JsonIgnore
	public void setDateCreated(
		UnsafeSupplier<Date, Exception> dateCreatedUnsafeSupplier) {

		try {
			dateCreated = dateCreatedUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The account's creation date.")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Date dateCreated;

	@Schema(
		description = "The most recent time that any of the account's fields changed."
	)
	public Date getDateModified() {
		return dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	@JsonIgnore
	public void setDateModified(
		UnsafeSupplier<Date, Exception> dateModifiedUnsafeSupplier) {

		try {
			dateModified = dateModifiedUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "The most recent time that any of the account's fields changed."
	)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Date dateModified;

	@Schema(description = "The description of the account.")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@JsonIgnore
	public void setDescription(
		UnsafeSupplier<String, Exception> descriptionUnsafeSupplier) {

		try {
			description = descriptionUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The description of the account.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String description;

	@Schema(description = "The account's entitlements.")
	@Valid
	public Entitlement[] getEntitlements() {
		return entitlements;
	}

	public void setEntitlements(Entitlement[] entitlements) {
		this.entitlements = entitlements;
	}

	@JsonIgnore
	public void setEntitlements(
		UnsafeSupplier<Entitlement[], Exception> entitlementsUnsafeSupplier) {

		try {
			entitlements = entitlementsUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The account's entitlements.")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Entitlement[] entitlements;

	@Schema(
		description = "The account's links to entities in external domains."
	)
	@Valid
	public ExternalLink[] getExternalLinks() {
		return externalLinks;
	}

	public void setExternalLinks(ExternalLink[] externalLinks) {
		this.externalLinks = externalLinks;
	}

	@JsonIgnore
	public void setExternalLinks(
		UnsafeSupplier<ExternalLink[], Exception> externalLinksUnsafeSupplier) {

		try {
			externalLinks = externalLinksUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "The account's links to entities in external domains."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected ExternalLink[] externalLinks;

	@Schema(description = "The account's fax number.")
	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	@JsonIgnore
	public void setFaxNumber(
		UnsafeSupplier<String, Exception> faxNumberUnsafeSupplier) {

		try {
			faxNumber = faxNumberUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The account's fax number.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String faxNumber;

	@Schema(
		description = "A flag that identifies whether this account is an internal or test account."
	)
	public Boolean getInternal() {
		return internal;
	}

	public void setInternal(Boolean internal) {
		this.internal = internal;
	}

	@JsonIgnore
	public void setInternal(
		UnsafeSupplier<Boolean, Exception> internalUnsafeSupplier) {

		try {
			internal = internalUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "A flag that identifies whether this account is an internal or test account."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Boolean internal;

	@Schema(description = "The account's key.")
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@JsonIgnore
	public void setKey(UnsafeSupplier<String, Exception> keyUnsafeSupplier) {
		try {
			key = keyUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The account's key.")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected String key;

	@Schema(description = "The account's language.")
	@Valid
	public Language getLanguage() {
		return language;
	}

	@JsonIgnore
	public String getLanguageAsString() {
		if (language == null) {
			return null;
		}

		return language.toString();
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	@JsonIgnore
	public void setLanguage(
		UnsafeSupplier<Language, Exception> languageUnsafeSupplier) {

		try {
			language = languageUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The account's language.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Language language;

	@Schema(
		deprecated = true,
		description = "The assetAttachmentId of the account's logo."
	)
	public Long getLogoId() {
		return logoId;
	}

	public void setLogoId(Long logoId) {
		this.logoId = logoId;
	}

	@JsonIgnore
	public void setLogoId(
		UnsafeSupplier<Long, Exception> logoIdUnsafeSupplier) {

		try {
			logoId = logoIdUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Deprecated
	@GraphQLField(description = "The assetAttachmentId of the account's logo.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Long logoId;

	@Schema(description = "The name of the account.")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	public void setName(UnsafeSupplier<String, Exception> nameUnsafeSupplier) {
		try {
			name = nameUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The name of the account.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String name;

	@Schema(description = "The account's parent account key.")
	public String getParentAccountKey() {
		return parentAccountKey;
	}

	public void setParentAccountKey(String parentAccountKey) {
		this.parentAccountKey = parentAccountKey;
	}

	@JsonIgnore
	public void setParentAccountKey(
		UnsafeSupplier<String, Exception> parentAccountKeyUnsafeSupplier) {

		try {
			parentAccountKey = parentAccountKeyUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The account's parent account key.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String parentAccountKey;

	@Schema(description = "The account's phone number.")
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@JsonIgnore
	public void setPhoneNumber(
		UnsafeSupplier<String, Exception> phoneNumberUnsafeSupplier) {

		try {
			phoneNumber = phoneNumberUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The account's phone number.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String phoneNumber;

	@Schema(description = "The account's postal addresses.")
	@Valid
	public PostalAddress[] getPostalAddresses() {
		return postalAddresses;
	}

	public void setPostalAddresses(PostalAddress[] postalAddresses) {
		this.postalAddresses = postalAddresses;
	}

	@JsonIgnore
	public void setPostalAddresses(
		UnsafeSupplier<PostalAddress[], Exception>
			postalAddressesUnsafeSupplier) {

		try {
			postalAddresses = postalAddressesUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The account's postal addresses.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected PostalAddress[] postalAddresses;

	@Schema(
		description = "The products that the account has purchased. Optional field that can retrieved with nestedFields."
	)
	@Valid
	public ProductPurchase[] getProductPurchases() {
		return productPurchases;
	}

	public void setProductPurchases(ProductPurchase[] productPurchases) {
		this.productPurchases = productPurchases;
	}

	@JsonIgnore
	public void setProductPurchases(
		UnsafeSupplier<ProductPurchase[], Exception>
			productPurchasesUnsafeSupplier) {

		try {
			productPurchases = productPurchasesUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "The products that the account has purchased. Optional field that can retrieved with nestedFields."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected ProductPurchase[] productPurchases;

	@Schema(description = "The account's profile email address.")
	public String getProfileEmailAddress() {
		return profileEmailAddress;
	}

	public void setProfileEmailAddress(String profileEmailAddress) {
		this.profileEmailAddress = profileEmailAddress;
	}

	@JsonIgnore
	public void setProfileEmailAddress(
		UnsafeSupplier<String, Exception> profileEmailAddressUnsafeSupplier) {

		try {
			profileEmailAddress = profileEmailAddressUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The account's profile email address.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String profileEmailAddress;

	@Schema
	@Valid
	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

	@JsonIgnore
	public void setProperties(
		UnsafeSupplier<Map<String, String>, Exception>
			propertiesUnsafeSupplier) {

		try {
			properties = propertiesUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Map<String, String> properties;

	@Schema(description = "The region responsible for the account.")
	@Valid
	public Region getRegion() {
		return region;
	}

	@JsonIgnore
	public String getRegionAsString() {
		if (region == null) {
			return null;
		}

		return region.toString();
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	@JsonIgnore
	public void setRegion(
		UnsafeSupplier<Region, Exception> regionUnsafeSupplier) {

		try {
			region = regionUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The region responsible for the account.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Region region;

	@Schema(description = "The status of the account.")
	@Valid
	public Status getStatus() {
		return status;
	}

	@JsonIgnore
	public String getStatusAsString() {
		if (status == null) {
			return null;
		}

		return status.toString();
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@JsonIgnore
	public void setStatus(
		UnsafeSupplier<Status, Exception> statusUnsafeSupplier) {

		try {
			status = statusUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The status of the account.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Status status;

	@Schema(description = "The tier of the account.")
	@Valid
	public Tier getTier() {
		return tier;
	}

	@JsonIgnore
	public String getTierAsString() {
		if (tier == null) {
			return null;
		}

		return tier.toString();
	}

	public void setTier(Tier tier) {
		this.tier = tier;
	}

	@JsonIgnore
	public void setTier(UnsafeSupplier<Tier, Exception> tierUnsafeSupplier) {
		try {
			tier = tierUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The tier of the account.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Tier tier;

	@Schema(description = "The account's website.")
	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@JsonIgnore
	public void setWebsite(
		UnsafeSupplier<String, Exception> websiteUnsafeSupplier) {

		try {
			website = websiteUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The account's website.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String website;

	@Schema(
		description = "The account's worker contacts. Optional field that can retrieved with nestedFields."
	)
	@Valid
	public Contact[] getWorkerContacts() {
		return workerContacts;
	}

	public void setWorkerContacts(Contact[] workerContacts) {
		this.workerContacts = workerContacts;
	}

	@JsonIgnore
	public void setWorkerContacts(
		UnsafeSupplier<Contact[], Exception> workerContactsUnsafeSupplier) {

		try {
			workerContacts = workerContactsUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "The account's worker contacts. Optional field that can retrieved with nestedFields."
	)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Contact[] workerContacts;

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
		StringBundler sb = new StringBundler();

		sb.append("{");

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");

		if (assignedTeams != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"assignedTeams\": ");

			sb.append("[");

			for (int i = 0; i < assignedTeams.length; i++) {
				sb.append(String.valueOf(assignedTeams[i]));

				if ((i + 1) < assignedTeams.length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (code != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"code\": ");

			sb.append("\"");

			sb.append(_escape(code));

			sb.append("\"");
		}

		if (contactEmailAddress != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"contactEmailAddress\": ");

			sb.append("\"");

			sb.append(_escape(contactEmailAddress));

			sb.append("\"");
		}

		if (contacts != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"contacts\": ");

			sb.append("[");

			for (int i = 0; i < contacts.length; i++) {
				sb.append(String.valueOf(contacts[i]));

				if ((i + 1) < contacts.length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (customerContacts != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"customerContacts\": ");

			sb.append("[");

			for (int i = 0; i < customerContacts.length; i++) {
				sb.append(String.valueOf(customerContacts[i]));

				if ((i + 1) < customerContacts.length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (dataRegion != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"dataRegion\": ");

			sb.append("\"");

			sb.append(dataRegion);

			sb.append("\"");
		}

		if (dateCreated != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"dateCreated\": ");

			sb.append("\"");

			sb.append(liferayToJSONDateFormat.format(dateCreated));

			sb.append("\"");
		}

		if (dateModified != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"dateModified\": ");

			sb.append("\"");

			sb.append(liferayToJSONDateFormat.format(dateModified));

			sb.append("\"");
		}

		if (description != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"description\": ");

			sb.append("\"");

			sb.append(_escape(description));

			sb.append("\"");
		}

		if (entitlements != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"entitlements\": ");

			sb.append("[");

			for (int i = 0; i < entitlements.length; i++) {
				sb.append(String.valueOf(entitlements[i]));

				if ((i + 1) < entitlements.length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (externalLinks != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"externalLinks\": ");

			sb.append("[");

			for (int i = 0; i < externalLinks.length; i++) {
				sb.append(String.valueOf(externalLinks[i]));

				if ((i + 1) < externalLinks.length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (faxNumber != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"faxNumber\": ");

			sb.append("\"");

			sb.append(_escape(faxNumber));

			sb.append("\"");
		}

		if (internal != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"internal\": ");

			sb.append(internal);
		}

		if (key != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"key\": ");

			sb.append("\"");

			sb.append(_escape(key));

			sb.append("\"");
		}

		if (language != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"language\": ");

			sb.append("\"");

			sb.append(language);

			sb.append("\"");
		}

		if (logoId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"logoId\": ");

			sb.append(logoId);
		}

		if (name != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"name\": ");

			sb.append("\"");

			sb.append(_escape(name));

			sb.append("\"");
		}

		if (parentAccountKey != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"parentAccountKey\": ");

			sb.append("\"");

			sb.append(_escape(parentAccountKey));

			sb.append("\"");
		}

		if (phoneNumber != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"phoneNumber\": ");

			sb.append("\"");

			sb.append(_escape(phoneNumber));

			sb.append("\"");
		}

		if (postalAddresses != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"postalAddresses\": ");

			sb.append("[");

			for (int i = 0; i < postalAddresses.length; i++) {
				sb.append(String.valueOf(postalAddresses[i]));

				if ((i + 1) < postalAddresses.length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (productPurchases != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"productPurchases\": ");

			sb.append("[");

			for (int i = 0; i < productPurchases.length; i++) {
				sb.append(String.valueOf(productPurchases[i]));

				if ((i + 1) < productPurchases.length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (profileEmailAddress != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"profileEmailAddress\": ");

			sb.append("\"");

			sb.append(_escape(profileEmailAddress));

			sb.append("\"");
		}

		if (properties != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"properties\": ");

			sb.append(_toJSON(properties));
		}

		if (region != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"region\": ");

			sb.append("\"");

			sb.append(region);

			sb.append("\"");
		}

		if (status != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"status\": ");

			sb.append("\"");

			sb.append(status);

			sb.append("\"");
		}

		if (tier != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"tier\": ");

			sb.append("\"");

			sb.append(tier);

			sb.append("\"");
		}

		if (website != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"website\": ");

			sb.append("\"");

			sb.append(_escape(website));

			sb.append("\"");
		}

		if (workerContacts != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"workerContacts\": ");

			sb.append("[");

			for (int i = 0; i < workerContacts.length; i++) {
				sb.append(String.valueOf(workerContacts[i]));

				if ((i + 1) < workerContacts.length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		accessMode = Schema.AccessMode.READ_ONLY,
		defaultValue = "com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Account",
		name = "x-class-name"
	)
	public String xClassName;

	@GraphQLName("DataRegion")
	public static enum DataRegion {

		BRAZIL("Brazil"), HUNGARY("Hungary"), JAPAN("Japan"),
		UNITED_STATES("United States");

		@JsonCreator
		public static DataRegion create(String value) {
			if ((value == null) || value.equals("")) {
				return null;
			}

			for (DataRegion dataRegion : values()) {
				if (Objects.equals(dataRegion.getValue(), value)) {
					return dataRegion;
				}
			}

			throw new IllegalArgumentException("Invalid enum value: " + value);
		}

		@JsonValue
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

	@GraphQLName("Language")
	public static enum Language {

		CHINESE("Chinese"), ENGLISH("English"), JAPANESE("Japanese"),
		PORTUGUESE("Portuguese"), SPANISH("Spanish");

		@JsonCreator
		public static Language create(String value) {
			if ((value == null) || value.equals("")) {
				return null;
			}

			for (Language language : values()) {
				if (Objects.equals(language.getValue(), value)) {
					return language;
				}
			}

			throw new IllegalArgumentException("Invalid enum value: " + value);
		}

		@JsonValue
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

	@GraphQLName("Region")
	public static enum Region {

		AUSTRALIA("Australia"), BRAZIL("Brazil"), CHINA("China"),
		GLOBAL("Global"), HUNGARY("Hungary"), INDIA("India"), JAPAN("Japan"),
		SPAIN("Spain"), UNITED_STATES("United States");

		@JsonCreator
		public static Region create(String value) {
			if ((value == null) || value.equals("")) {
				return null;
			}

			for (Region region : values()) {
				if (Objects.equals(region.getValue(), value)) {
					return region;
				}
			}

			throw new IllegalArgumentException("Invalid enum value: " + value);
		}

		@JsonValue
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

	@GraphQLName("Status")
	public static enum Status {

		ACTIVE("Active"), CLOSED("Closed");

		@JsonCreator
		public static Status create(String value) {
			if ((value == null) || value.equals("")) {
				return null;
			}

			for (Status status : values()) {
				if (Objects.equals(status.getValue(), value)) {
					return status;
				}
			}

			throw new IllegalArgumentException("Invalid enum value: " + value);
		}

		@JsonValue
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

	@GraphQLName("Tier")
	public static enum Tier {

		T1("T1"), T2("T2"), T3("T3"), T4("T4");

		@JsonCreator
		public static Tier create(String value) {
			if ((value == null) || value.equals("")) {
				return null;
			}

			for (Tier tier : values()) {
				if (Objects.equals(tier.getValue(), value)) {
					return tier;
				}
			}

			throw new IllegalArgumentException("Invalid enum value: " + value);
		}

		@JsonValue
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

	private static String _escape(Object object) {
		return StringUtil.replace(
			String.valueOf(object), _JSON_ESCAPE_STRINGS[0],
			_JSON_ESCAPE_STRINGS[1]);
	}

	private static boolean _isArray(Object value) {
		if (value == null) {
			return false;
		}

		Class<?> clazz = value.getClass();

		return clazz.isArray();
	}

	private static String _toJSON(Map<String, ?> map) {
		StringBuilder sb = new StringBuilder("{");

		@SuppressWarnings("unchecked")
		Set set = map.entrySet();

		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, ?>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ?> entry = iterator.next();

			sb.append("\"");
			sb.append(_escape(entry.getKey()));
			sb.append("\": ");

			Object value = entry.getValue();

			if (_isArray(value)) {
				sb.append("[");

				Object[] valueArray = (Object[])value;

				for (int i = 0; i < valueArray.length; i++) {
					if (valueArray[i] instanceof String) {
						sb.append("\"");
						sb.append(valueArray[i]);
						sb.append("\"");
					}
					else {
						sb.append(valueArray[i]);
					}

					if ((i + 1) < valueArray.length) {
						sb.append(", ");
					}
				}

				sb.append("]");
			}
			else if (value instanceof Map) {
				sb.append(_toJSON((Map<String, ?>)value));
			}
			else if (value instanceof String) {
				sb.append("\"");
				sb.append(_escape(value));
				sb.append("\"");
			}
			else {
				sb.append(value);
			}

			if (iterator.hasNext()) {
				sb.append(", ");
			}
		}

		sb.append("}");

		return sb.toString();
	}

	private static final String[][] _JSON_ESCAPE_STRINGS = {
		{"\\", "\"", "\b", "\f", "\n", "\r", "\t"},
		{"\\\\", "\\\"", "\\b", "\\f", "\\n", "\\r", "\\t"}
	};

}