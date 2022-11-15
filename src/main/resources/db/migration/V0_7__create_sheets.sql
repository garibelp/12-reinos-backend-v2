create table sheets (
    id uuid primary key,
    bond varchar(200),
    celerity int4 not null,
    created_at timestamp not null,
    cunning int4 not null,
    heroism_current int4 not null,
    heroism_total int4 not null,
    intelligence int4 not null,
    is_active boolean not null,
    level int4 not null,
    mental_current int4 not null,
    mental_total int4 not null,
    motivation varchar(200),
    name varchar(30) not null,
    notes text,
    physical_current int4 not null,
    physical_total int4 not null,
    tenacity int4 not null,
    background_id uuid not null,
    job_id uuid not null,
    lineage_id uuid not null,
    player_id uuid not null
);

alter table sheets add constraint background_id_fk foreign key (background_id) references backgrounds;
alter table sheets add constraint job_id_fk foreign key (job_id) references jobs;
alter table sheets add constraint lineage_id_fk foreign key (lineage_id) references lineages;
alter table sheets add constraint player_id_fk foreign key (player_id) references player;

create table aptitude_sheet (sheet_id uuid not null, aptitude_id uuid not null, primary key (sheet_id, aptitude_id));
alter table aptitude_sheet add constraint aptitude_id_fk foreign key (aptitude_id) references aptitudes;
alter table aptitude_sheet add constraint sheet_id_fk foreign key (sheet_id) references sheets;
