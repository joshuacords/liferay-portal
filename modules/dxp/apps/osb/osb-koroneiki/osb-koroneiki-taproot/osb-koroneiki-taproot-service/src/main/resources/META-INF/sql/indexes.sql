create unique index IX_49B51109 on Koroneiki_Account (accountKey[$COLUMN_LENGTH:75$]);
create index IX_812ADE3F on Koroneiki_Account (code_[$COLUMN_LENGTH:75$]);
create index IX_68C36822 on Koroneiki_Account (name[$COLUMN_LENGTH:150$]);
create index IX_B9A3F00B on Koroneiki_Account (parentAccountId);
create index IX_946B1DD7 on Koroneiki_Account (uuid_[$COLUMN_LENGTH:75$], companyId);

create index IX_782E8095 on Koroneiki_AccountField (accountId);

create index IX_23D24370 on Koroneiki_AccountNote (accountId, type_[$COLUMN_LENGTH:75$], priority, status[$COLUMN_LENGTH:75$]);
create index IX_C79C658 on Koroneiki_AccountNote (accountId, type_[$COLUMN_LENGTH:75$], status[$COLUMN_LENGTH:75$]);
create index IX_965A8949 on Koroneiki_AccountNote (accountNoteKey[$COLUMN_LENGTH:75$]);
create index IX_ABD56E69 on Koroneiki_AccountNote (uuid_[$COLUMN_LENGTH:75$], companyId);

create unique index IX_F83C6BA9 on Koroneiki_Contact (contactKey[$COLUMN_LENGTH:75$]);
create unique index IX_875E5602 on Koroneiki_Contact (emailAddress[$COLUMN_LENGTH:75$]);
create index IX_23B9404A on Koroneiki_Contact (uuid_[$COLUMN_LENGTH:75$], companyId);

create index IX_309F9F1F on Koroneiki_ContactAccountRole (accountId);
create index IX_7C3346A8 on Koroneiki_ContactAccountRole (contactRoleId);

create unique index IX_6860DBE9 on Koroneiki_ContactRole (contactRoleKey[$COLUMN_LENGTH:75$]);
create index IX_650EEDCA on Koroneiki_ContactRole (name[$COLUMN_LENGTH:75$], type_[$COLUMN_LENGTH:75$]);
create index IX_3DBAA169 on Koroneiki_ContactRole (type_[$COLUMN_LENGTH:75$]);
create index IX_856F85E0 on Koroneiki_ContactRole (uuid_[$COLUMN_LENGTH:75$], companyId);

create index IX_CCCB218 on Koroneiki_ContactTeamRole (contactRoleId);
create index IX_216B6075 on Koroneiki_ContactTeamRole (teamId);

create index IX_98F3AB04 on Koroneiki_Team (accountId, name[$COLUMN_LENGTH:150$]);
create index IX_28C061DB on Koroneiki_Team (accountId, system_);
create index IX_AD200E72 on Koroneiki_Team (name[$COLUMN_LENGTH:150$]);
create unique index IX_736051DF on Koroneiki_Team (teamKey[$COLUMN_LENGTH:75$]);
create index IX_ADF0CC27 on Koroneiki_Team (uuid_[$COLUMN_LENGTH:75$], companyId);

create index IX_7A43F47C on Koroneiki_TeamAccountRole (accountId);
create index IX_6E0D01BE on Koroneiki_TeamAccountRole (teamRoleId);

create index IX_37F7F24D on Koroneiki_TeamRole (name[$COLUMN_LENGTH:75$], type_[$COLUMN_LENGTH:75$]);
create unique index IX_DAAD5F33 on Koroneiki_TeamRole (teamRoleKey[$COLUMN_LENGTH:75$]);
create index IX_22D6042C on Koroneiki_TeamRole (type_[$COLUMN_LENGTH:75$]);
create index IX_CF13DB3D on Koroneiki_TeamRole (uuid_[$COLUMN_LENGTH:75$], companyId);