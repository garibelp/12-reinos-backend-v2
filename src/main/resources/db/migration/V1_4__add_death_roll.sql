alter table sheets add column updated_at timestamp default now();
alter table sheets add column death_rolls int2 default 0;