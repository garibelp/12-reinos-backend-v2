create table backgrounds
(
    id              uuid primary key,
    mental_points   int4        not null,
    name            varchar(20) not null,
    physical_points int4        not null,
    advantage_id    uuid        not null
);

create table advantages
(
    id          uuid primary key,
    description text        not null,
    name        varchar(20) not null
);

alter table backgrounds
    add constraint advantage_id_fk foreign key (advantage_id) references advantages;

insert into advantages(id, name, description)
values ('47fe0f9c-cc35-49bb-a513-360314533d93', 'Auto',
        'Você começa o jogo com um Automóvel Leve que transporta até duas pessoas. Não há necessidade de usar Pontos de Heroísmo nessa Vantagem.'),
       ('8b5a7a72-7fc6-4b37-83ca-822ed0410879', 'Contato',
        'Gaste 1 Ponto de Heroísmo para influir na história e dizer que você possui um contato útil que pode estar presente no local da cena atual, ou que pode encontrá-lo em um local específico na próxima cena. Cabe a você descrever quem é esse contato, como se conheceram e como você acredita que ele pode te ajudar. O Mestre deve aceitar, e a forma exata que seu contato lhe será útil será definida por ele. Você apenas acredita que ele pode ser útil, mas não sabe exatamente como. Mas o Mestre deverá sim torná-lo de alguma forma útil. Contatos normalmente são úteis com informações e com a venda de itens raros, mas dificilmente irão distribuir souvenires por aí...'),
       ('a8315ecb-0d2a-4e23-b9c3-81ebfd3cfcd3', 'Fama',
        'Você é famoso por algum motivo entre a comunidade relativa ao seu Antecedente. Gaste 1 Ponto de Heroísmo para alterar o rumo de uma cena e dar uma carteirada ao interagir com um NPC. Caso esteja entre a comunidade de seu Antecedente (por ex. você tem o Antecedente Comerciante e está em uma loja), você consegue o que quer. Caso não esteja, você consegue o que quer, mas há um custo.'),
       ('4d5b3422-43bd-4cf3-9a17-f0399a122915', 'Recurso',
        'Gaste 1 Ponto de Heroísmo para influir na história e dizer que você possui, ou sabe onde encontrar, os recursos necessários para a realização de uma tarefa específica. Recursos não são itens específicos como um item mágico, ou um mapa de uma região: são peças, ingredientes, ferramentas ou consumíveis. Por exemplo, se seu grupo precisa conseguir comida, você pode simplesmente ter algum estoque com você. Descreva como você conseguiu o recurso e, pronto, o Mestre deve acatar. Isso não funciona com recursos de Preço 3 ou maior.'),
       ('4cc5a30a-3928-44ab-b8e4-a55f5e8a1008', 'Refúgio',
        'Gaste 1 Ponto de Heroísmo para alterar o rumo da história e dizer que você possui um refúgio por perto, com os suprimentos necessários para que o grupo descanse nele por um dia ou uma noite. Descreva como é o refúgio, onde fica e por que você o tem. Criaturas que Repousam em um Refúgio conseguem fazê-lo em apenas 3 dias.'),
       ('fb11f0d9-a2e0-4e88-a539-ae9fea165912', 'Riqueza',
        'Você recebe 2d12 Peças de Olivina por dia. Gaste 1 Ponto de Heroísmo para rolar o dado novamente nessa sessão, se precisar fazer uma compra cara, por exemplo. Você deve ter uma fonte clara de renda - que, obviamente, sempre pode ser sabotada por alguém…');

insert into backgrounds(id, name, physical_points, mental_points, advantage_id)
values ('9cee85c6-d548-414b-bf3c-a5e9eb57a3eb', 'Acadêmico', -1, 4, 'a8315ecb-0d2a-4e23-b9c3-81ebfd3cfcd3'),
       ('d7ffebea-6906-4469-bb22-bbe8bae63ccd', 'Comerciante', 1, 2, '4d5b3422-43bd-4cf3-9a17-f0399a122915'),
       ('8f268d74-366f-44bd-a381-ad563e58988f', 'Fazendeiro', 4, -1, 'fb11f0d9-a2e0-4e88-a539-ae9fea165912'),
       ('e97bf4d3-c9ae-4cf3-af94-6dca32031bae', 'Herdeiro', 2, 1, 'fb11f0d9-a2e0-4e88-a539-ae9fea165912'),
       ('78e29e3f-0d1a-4880-b445-14c903cddacd', 'Investigador', -1, 4, '8b5a7a72-7fc6-4b37-83ca-822ed0410879'),
       ('729f6416-7133-428d-b480-96a1ac93c881', 'Operário', 3, 0, '8b5a7a72-7fc6-4b37-83ca-822ed0410879'),
       ('6a0e46ef-a26c-4d37-8105-e69faba4f8dc', 'Piloto', 2, 1, '47fe0f9c-cc35-49bb-a513-360314533d93'),
       ('8b305a34-1fcf-45a4-9ec5-aa129329a8ca', 'Radialista', 0, 3, 'a8315ecb-0d2a-4e23-b9c3-81ebfd3cfcd3'),
       ('0eab7c1d-934e-44b2-a90a-92d1176cc1a4', 'Religioso', 0, 3, '4cc5a30a-3928-44ab-b8e4-a55f5e8a1008'),
       ('97dc5f51-e8f1-4137-bd86-b5d1ebfe75d4', 'Soldado', 4, -1, '4cc5a30a-3928-44ab-b8e4-a55f5e8a1008'),
       ('4a4e7b7c-ea33-46bc-9030-5f1042616bf5', 'Técnico', 1, 2, '47fe0f9c-cc35-49bb-a513-360314533d93'),
       ('8d4f5995-4d05-403a-9d41-eb206f325294', 'Viajante', 3, 0, '4d5b3422-43bd-4cf3-9a17-f0399a122915');