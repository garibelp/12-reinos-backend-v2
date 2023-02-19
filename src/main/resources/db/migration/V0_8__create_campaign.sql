create table campaign
(
    id               uuid primary key,
    name             varchar(30) not null,
    notes            text,
    is_active        boolean     not null,
    created_at       timestamp   not null,
    updated_at       timestamp   not null,
    player_id        uuid        not null
);

alter table campaign
    add constraint player_id_fk foreign key (player_id) references player;

alter table sheets add campaign_id uuid;

alter table sheets
    add constraint campaign_id_fk foreign key (campaign_id) references campaign;