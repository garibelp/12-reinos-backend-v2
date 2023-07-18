create table wounds
(
    id               uuid primary key,
    name             varchar(30) not null,
    description      varchar(300) not null,
    dice_range       varchar(5) not null
);

insert into wounds(id, name, dice_range, description)
values
    ('c21f0584-1606-4849-93b7-ee1fcba0c257', 'Só um arranhão', '1-1', 'Levante-se, recuperando o equivalente a seu Capítulo em Energia. Em 1d4 rodadas, você cai novamente, inconsciente porém estabilizado.'),
    ('a1775cb9-7318-4f5d-9894-1a1ea2f94774', 'Sangramento interno', '2-3', 'Qualquer tipo de cura que você recebe ou dá, mágica ou não, é reduzida à metade, arredondada para cima. Esse ferimento só pode ser curado por Repouso ou Cuidados Médicos.'),
    ('d24e726e-d899-4741-80ad-a90321d56eda', 'Membro quebrado', '4-5', 'Seu deslocamento é reduzido a 0. Você só é capaz de se deslocar na presença de um aliado ou apoio adjacente. O uso de uma bengala/muletas consome a Ação e permite metade do seu deslocamento.'),
    ('042a1652-d31f-4197-bbc5-1abbfeedd8b1', 'Atordoamento', '6-7', 'A cada turno, você deve escolher entre sua Ação ou seu Movimento. Você é incapaz de fazer ambos em um turno. Suas Ações Bônus continuam inalteradas.'),
    ('ba530be0-7a77-4acc-833d-a99165b9c789', 'Luxação', '8-9', 'Qualquer tipo de dano que você inflige, mágico ou não, é reduzido à metade, arredondado para cima.'),
    ('784dd588-ea81-466f-a1ae-056e891e0980', 'Sangramento externo', '10-11', 'Independente de quanto de Energia tenha, seu personagem constantemente perde 1d4 de Energia Física por rodada, até que chegue a 1. Seu personagem não pode ser levado à Inconsciência por esse ferimento.'),
    ('8f64e22a-5a80-4607-9bd6-ca198be91d59', 'Coup de Grace', '12-12', 'Você perde um olho ou ganha uma grande cicatriz. Escolha um Atributo e diminua o tamanho de seu dado.');

alter table sheets add wound_id uuid;

alter table sheets
    add constraint wound_id_fk foreign key (wound_id) references wounds;