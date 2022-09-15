create table Provisioning_SubscriptionEntry (
	mvccVersion LONG default 0 not null,
	subscriptionEntryId LONG not null primary key,
	createDate DATE null,
	classNameId LONG,
	classPK LONG,
	contactUuid VARCHAR(75) null
);