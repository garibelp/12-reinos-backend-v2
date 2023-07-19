alter table sheets drop column death_rolls;
alter table sheets add column death_roll_body varchar(10) default 'UNCHECKED';
alter table sheets add column death_roll_mind varchar(10) default 'UNCHECKED';
alter table sheets add column death_roll_spirit varchar(10) default 'UNCHECKED';
