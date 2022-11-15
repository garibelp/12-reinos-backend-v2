create table jobs (
    id uuid primary key,
    description text not null,
    main_attribute varchar(20) not null,
    mental_points int4 not null,
    name varchar(20) not null,
    physical_points int4 not null
);

create table aptitude_job (job_id uuid not null, aptitude_id uuid not null, primary key (job_id, aptitude_id));

alter table aptitude_job add constraint aptitude_id_fk foreign key (aptitude_id) references aptitudes;

alter table aptitude_job add constraint job_id_fk foreign key (job_id) references jobs;

insert into
    jobs(id, name, description, main_attribute, physical_points, mental_points)
values
    (
        'eb98fa25-634a-4e93-8898-0c9dc7f01c01',
        'Artífice',
        'Quem precisa de magia quando se tem a tecnologia a seu dispor? Os Artífices são entusiastas, amantes das bugigangas, sonhadores e criativos. Podem ter algum tipo de rivalidade com conjuradores, talvez por inveja, talvez por desprezo.',
        'INTELLIGENCE',
        5,
        8
    ),
    (
        'c0169b57-d4d2-49f2-8abe-b0f87c5e9fae',
        'Artista',
        'A energia de um surto criativo pode ser tão avassaladora quanto uma Bola de Fogo bem dada. Pelo menos é nisso que os Artistas acreditam. Não importa a forma, suas obras sempre causam um impacto maior do que o esperado, imbuídas não apenas de sentimentos, como de magia. Não costumam ter rivalidade com uma classe específica, mas com aqueles que não apreciam arte.',
        'CUNNING',
        4,
        9
    ),
    (
        '9132517e-f3da-4321-905d-63a743d9344a',
        'Atirador',
        'Lâminas e magias nunca serão tão rápidas quanto um dedo em um gatilho. Atiradores contam com armas de fogo para resolver seus problemas e podem ter uma leve rivalidade com Guerreiros e Bárbaros. Ao contrário do que se pensaria, são as armas de fogo que são consideradas brutas e menos elegantes.',
        'CELERITY',
        8,
        5
    ),
    (
        'fd505408-014e-4ee7-96fb-ed577c0f3e93',
        'Bárbaro',
        'A melhor defesa é um ataque destruidor. Sem ferimentos e risco de vida, não há combate. Bárbaros são a linha de frente de grupos de aventureiros por sua capacidade de resistir a dano e punir seus oponentes. Podem ter rivalidade com Ladinos e Atiradores por não se arriscarem em batalha, mas entendem os conjuradores que querem se proteger.',
        'TENACITY',
        9,
        4
    ),
    (
        '787c91b7-ab88-4e50-bf5d-30c09204e74d',
        'Diplomata',
        'Ao fim da Grande Guerra Bartoloriana, os 12 Reinos viam-se pela primeira vez separados, rancorosos uns com os outros e na iminência de criarem novas alianças para travarem novos embates. Felizmente, os líderes do continente ouviram à razão através da palavra de políticos ascendidos do povo. Desde então, a diplomacia é vista como uma das maiores virtudes que uma pessoa pode ter, e a política dada como uma carreira de prestígio e honra. Diplomatas são treinados na arte da palavra. Costumam ter rivalidade com Bárbaros e Guerreiros, treinados na arte da guerra.',
        'CUNNING',
        5,
        8
    ),
    (
        '4c5e9d82-d2f9-452e-bbf1-585788816098',
        'Druida',
        'Aos que têm a paciência e a sensibilidade para escutar, a mãe natureza tem muito a dizer. Os Druidas juram poder ouvir sua voz, não como um som, mas expressa em diferentes formas. No vento, na terra, na água. O fato é que, indiscutivelmente, algo na natureza os tocou e abençoou com habilidades extraordinárias. Druidas podem ter rivalidades com Artífices, por vê-los como símbolo da expansão da tecnologia sobre a natureza.',
        'TENACITY',
        7,
        6
    ),
    (
        'a719d6dd-ef3d-4438-b834-3c7a399df384',
        'Guerreiro',
        'Treino e confiança em suas próprias habilidades são as duas principais características dos Guerreiros. Sem poderes, truques, bugigangas ou resistência extraordinária, eles contam apenas com sua própria garra e sua Arma Favorita. Não têm rivalidade com nenhuma outra classe, mas por pessoas que não prezam pelo trabalho em equipe.',
        'TENACITY',
        9,
        4
    ),
    (
        '339c9479-06c9-42d5-9920-535b34ace847',
        'Ladino',
        'Astutos combatentes que contam com sua sorte em batalha, os Ladinos são sorrateiros e perspicazes, embora não sejam necessariamente maus ou quebradores de regras. São supersticiosos, amantes e protegidos do acaso. Podem ter rivalidade com Sacerdotes por seguirem um código moral.',
        'CELERITY',
        8,
        5
    ),
    (
        '4a48caa2-7f79-435f-ad42-92359248093c',
        'Mago',
        'Magos eram mais comuns no passado. Houve um tempo em que Bartol tinha duas Academias de magia. Hoje, o Arcano é mantido vivo com o esforço de poucos, passado apenas de Mestre para Discípulo através de grimórios e métodos árduos de estudo incessante. Os que ascendem o posto de aprendizes têm em mãos poderes tão fortes quanto a responsabilidade que ganham de passá-los adiante. Os Magos podem ter rivalidade com Nexus por não terem adquirido suas habilidades através de estudo e esforço.',
        'INTELLIGENCE',
        4,
        9
    ),
    (
        '839eef4c-294c-4ac1-b1e2-317229ddb093',
        'Nexus',
        'Bartol ainda está à espera de quem possa explicar o fenômeno Nexus. Nas últimas décadas, incidentes envolvendo outros planos têm transformado pessoas comuns em seres extraordinários. Elas parecem voltar do pós-vida com habilidades que desafiam a realidade, distorcendo o tempo, alterando o espaço e manipulando a matéria. Nexus não costumam ter rivalidades por estarem muito ocupados tentando entender e aceitar a si mesmos.',
        'INTELLIGENCE',
        6,
        7
    ),
    (
        '47117c6c-27a4-42db-88f2-a0972a93eadf',
        'Sacerdote',
        'O divino em Bartol é misterioso e incerto. Desde a Queda dos Elfos, religiões deixaram de existir e crenças passaram a ter seus limites cada vez mais incertos e indefinidos. Cada habitante do continente tem seu próprio conjunto de entidades protetoras e guias energéticos. Alguns dizem poder sentir sua influência na vida mundana. Os Sacerdotes sabem usá-la a seu favor. Não têm rivalidades específicas.',
        'CUNNING',
        6,
        7
    ),
    (
        '3d14cb08-3391-418e-bd74-37d7737be28b',
        'Sentinela',
        'A Grande Guerra Bartoloriana deixou boa parte do continente devastada, separou famílias e feriu, mesmo que indiretamente, todos os seres vivos de Bartol. Com a missão de garantir que nenhuma pessoa precisasse passar por tamanho sofrimento novamente, uma Ordem foi estabelecida através de um código de honra. Não há instituição, liderança, treinamento ou iniciação para fazer parte dela. Basta juramentar o código para si e carregá-lo junto ao desejo de proteger e fazer o bem para tornar-se um Sentinela.',
        'CELERITY',
        7,
        6
    );

insert into
    aptitude_job(job_id, aptitude_id)
values
    ('eb98fa25-634a-4e93-8898-0c9dc7f01c01', '48c00298-5e21-4e56-b5c2-5db4d4d37d6e'),
    ('eb98fa25-634a-4e93-8898-0c9dc7f01c01', '4fdb404b-f1dc-4ce3-a5fd-6be7eb56df15'),
    ('eb98fa25-634a-4e93-8898-0c9dc7f01c01', '2ba3a1fd-aad1-4e3a-93fd-ff4b3749d616'),
    ('eb98fa25-634a-4e93-8898-0c9dc7f01c01', '24f5e67e-ca4a-4fef-8325-85c6f8f81e55'),
    ('eb98fa25-634a-4e93-8898-0c9dc7f01c01', 'f4bfa384-93d4-415e-bd49-52d027e30335'),
    ('eb98fa25-634a-4e93-8898-0c9dc7f01c01', 'a51dbfc2-02a7-471f-8f80-29ace8158e91'),
    ('c0169b57-d4d2-49f2-8abe-b0f87c5e9fae', '3f855b9b-d352-4c23-9cf2-e5e4d8657916'),
    ('c0169b57-d4d2-49f2-8abe-b0f87c5e9fae', '30a19cf8-bb64-4dd2-b3f7-29b9364eb0e9'),
    ('c0169b57-d4d2-49f2-8abe-b0f87c5e9fae', 'da13f789-64f8-4728-8366-e8725c16a590'),
    ('c0169b57-d4d2-49f2-8abe-b0f87c5e9fae', '24f5e67e-ca4a-4fef-8325-85c6f8f81e55'),
    ('c0169b57-d4d2-49f2-8abe-b0f87c5e9fae', '4d7aec1a-b884-4d6f-adfa-85283c1d1df7'),
    ('c0169b57-d4d2-49f2-8abe-b0f87c5e9fae', 'a51dbfc2-02a7-471f-8f80-29ace8158e91'),
    ('9132517e-f3da-4321-905d-63a743d9344a', 'fc7f6815-53c6-4b17-b780-391735a1475a'),
    ('9132517e-f3da-4321-905d-63a743d9344a', '48c00298-5e21-4e56-b5c2-5db4d4d37d6e'),
    ('9132517e-f3da-4321-905d-63a743d9344a', '86d9c9b0-e698-477d-8b94-aa2a2f43e876'),
    ('9132517e-f3da-4321-905d-63a743d9344a', 'f4bfa384-93d4-415e-bd49-52d027e30335'),
    ('9132517e-f3da-4321-905d-63a743d9344a', 'a51dbfc2-02a7-471f-8f80-29ace8158e91'),
    ('9132517e-f3da-4321-905d-63a743d9344a', 'ca749272-1570-4136-955e-aa8d22d8870c'),
    ('fd505408-014e-4ee7-96fb-ed577c0f3e93', '86d9c9b0-e698-477d-8b94-aa2a2f43e876'),
    ('fd505408-014e-4ee7-96fb-ed577c0f3e93', '4fdb404b-f1dc-4ce3-a5fd-6be7eb56df15'),
    ('fd505408-014e-4ee7-96fb-ed577c0f3e93', '0c6e1449-d218-4ffc-b0bc-0fcfea87d891'),
    ('fd505408-014e-4ee7-96fb-ed577c0f3e93', 'eaf00e61-c5f2-46e3-b3e9-cfd91b5ae582'),
    ('fd505408-014e-4ee7-96fb-ed577c0f3e93', 'da13f789-64f8-4728-8366-e8725c16a590'),
    ('fd505408-014e-4ee7-96fb-ed577c0f3e93', '217b74f9-c95f-4185-a17c-8f9b546c41e7'),
    ('787c91b7-ab88-4e50-bf5d-30c09204e74d', '3f855b9b-d352-4c23-9cf2-e5e4d8657916'),
    ('787c91b7-ab88-4e50-bf5d-30c09204e74d', 'c8f5b55a-c2fa-4b32-95c1-d44ee9236bea'),
    ('787c91b7-ab88-4e50-bf5d-30c09204e74d', '48c00298-5e21-4e56-b5c2-5db4d4d37d6e'),
    ('787c91b7-ab88-4e50-bf5d-30c09204e74d', '2ba3a1fd-aad1-4e3a-93fd-ff4b3749d616'),
    ('787c91b7-ab88-4e50-bf5d-30c09204e74d', '30a19cf8-bb64-4dd2-b3f7-29b9364eb0e9'),
    ('787c91b7-ab88-4e50-bf5d-30c09204e74d', '24f5e67e-ca4a-4fef-8325-85c6f8f81e55'),
    ('4c5e9d82-d2f9-452e-bbf1-585788816098', 'c8f5b55a-c2fa-4b32-95c1-d44ee9236bea'),
    ('4c5e9d82-d2f9-452e-bbf1-585788816098', '86d9c9b0-e698-477d-8b94-aa2a2f43e876'),
    ('4c5e9d82-d2f9-452e-bbf1-585788816098', '0c6e1449-d218-4ffc-b0bc-0fcfea87d891'),
    ('4c5e9d82-d2f9-452e-bbf1-585788816098', '4d7aec1a-b884-4d6f-adfa-85283c1d1df7'),
    ('4c5e9d82-d2f9-452e-bbf1-585788816098', '217b74f9-c95f-4185-a17c-8f9b546c41e7'),
    ('4c5e9d82-d2f9-452e-bbf1-585788816098', 'ca749272-1570-4136-955e-aa8d22d8870c'),
    ('a719d6dd-ef3d-4438-b834-3c7a399df384', '48c00298-5e21-4e56-b5c2-5db4d4d37d6e'),
    ('a719d6dd-ef3d-4438-b834-3c7a399df384', '86d9c9b0-e698-477d-8b94-aa2a2f43e876'),
    ('a719d6dd-ef3d-4438-b834-3c7a399df384', '0c6e1449-d218-4ffc-b0bc-0fcfea87d891'),
    ('a719d6dd-ef3d-4438-b834-3c7a399df384', 'eaf00e61-c5f2-46e3-b3e9-cfd91b5ae582'),
    ('a719d6dd-ef3d-4438-b834-3c7a399df384', '217b74f9-c95f-4185-a17c-8f9b546c41e7'),
    ('a719d6dd-ef3d-4438-b834-3c7a399df384', 'ca749272-1570-4136-955e-aa8d22d8870c'),
    ('339c9479-06c9-42d5-9920-535b34ace847', 'fc7f6815-53c6-4b17-b780-391735a1475a'),
    ('339c9479-06c9-42d5-9920-535b34ace847', '3f855b9b-d352-4c23-9cf2-e5e4d8657916'),
    ('339c9479-06c9-42d5-9920-535b34ace847', 'c8f5b55a-c2fa-4b32-95c1-d44ee9236bea'),
    ('339c9479-06c9-42d5-9920-535b34ace847', '30a19cf8-bb64-4dd2-b3f7-29b9364eb0e9'),
    ('339c9479-06c9-42d5-9920-535b34ace847', 'da13f789-64f8-4728-8366-e8725c16a590'),
    ('339c9479-06c9-42d5-9920-535b34ace847', 'a51dbfc2-02a7-471f-8f80-29ace8158e91'),
    ('4a48caa2-7f79-435f-ad42-92359248093c', 'c8f5b55a-c2fa-4b32-95c1-d44ee9236bea'),
    ('4a48caa2-7f79-435f-ad42-92359248093c', '48c00298-5e21-4e56-b5c2-5db4d4d37d6e'),
    ('4a48caa2-7f79-435f-ad42-92359248093c', '2ba3a1fd-aad1-4e3a-93fd-ff4b3749d616'),
    ('4a48caa2-7f79-435f-ad42-92359248093c', '24f5e67e-ca4a-4fef-8325-85c6f8f81e55'),
    ('4a48caa2-7f79-435f-ad42-92359248093c', '4d7aec1a-b884-4d6f-adfa-85283c1d1df7'),
    ('4a48caa2-7f79-435f-ad42-92359248093c', 'a51dbfc2-02a7-471f-8f80-29ace8158e91'),
    ('839eef4c-294c-4ac1-b1e2-317229ddb093', '48c00298-5e21-4e56-b5c2-5db4d4d37d6e'),
    ('839eef4c-294c-4ac1-b1e2-317229ddb093', 'eaf00e61-c5f2-46e3-b3e9-cfd91b5ae582'),
    ('839eef4c-294c-4ac1-b1e2-317229ddb093', 'da13f789-64f8-4728-8366-e8725c16a590'),
    ('839eef4c-294c-4ac1-b1e2-317229ddb093', '4d7aec1a-b884-4d6f-adfa-85283c1d1df7'),
    ('839eef4c-294c-4ac1-b1e2-317229ddb093', 'a51dbfc2-02a7-471f-8f80-29ace8158e91'),
    ('839eef4c-294c-4ac1-b1e2-317229ddb093', '217b74f9-c95f-4185-a17c-8f9b546c41e7'),
    ('47117c6c-27a4-42db-88f2-a0972a93eadf', '3f855b9b-d352-4c23-9cf2-e5e4d8657916'),
    ('47117c6c-27a4-42db-88f2-a0972a93eadf', 'c8f5b55a-c2fa-4b32-95c1-d44ee9236bea'),
    ('47117c6c-27a4-42db-88f2-a0972a93eadf', '30a19cf8-bb64-4dd2-b3f7-29b9364eb0e9'),
    ('47117c6c-27a4-42db-88f2-a0972a93eadf', 'da13f789-64f8-4728-8366-e8725c16a590'),
    ('47117c6c-27a4-42db-88f2-a0972a93eadf', '4d7aec1a-b884-4d6f-adfa-85283c1d1df7'),
    ('47117c6c-27a4-42db-88f2-a0972a93eadf', 'ca749272-1570-4136-955e-aa8d22d8870c'),
    ('3d14cb08-3391-418e-bd74-37d7737be28b', '30a19cf8-bb64-4dd2-b3f7-29b9364eb0e9'),
    ('3d14cb08-3391-418e-bd74-37d7737be28b', 'eaf00e61-c5f2-46e3-b3e9-cfd91b5ae582'),
    ('3d14cb08-3391-418e-bd74-37d7737be28b', 'da13f789-64f8-4728-8366-e8725c16a590'),
    ('3d14cb08-3391-418e-bd74-37d7737be28b', 'f4bfa384-93d4-415e-bd49-52d027e30335'),
    ('3d14cb08-3391-418e-bd74-37d7737be28b', '217b74f9-c95f-4185-a17c-8f9b546c41e7'),
    ('3d14cb08-3391-418e-bd74-37d7737be28b', 'ca749272-1570-4136-955e-aa8d22d8870c');