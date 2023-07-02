-- Add bonus values received in the attributes when leveled up
alter table jobs
add column mental_per_level int4,
add column physical_per_level int4;

update jobs
set mental_per_level = 2, physical_per_level = 4
where id in (
    'eb98fa25-634a-4e93-8898-0c9dc7f01c01',
    'c0169b57-d4d2-49f2-8abe-b0f87c5e9fae',
    '787c91b7-ab88-4e50-bf5d-30c09204e74d',
    '4a48caa2-7f79-435f-ad42-92359248093c'
);

update jobs
set mental_per_level = 3, physical_per_level = 3
where id in (
    '4c5e9d82-d2f9-452e-bbf1-585788816098',
    '839eef4c-294c-4ac1-b1e2-317229ddb093',
    '47117c6c-27a4-42db-88f2-a0972a93eadf',
    '3d14cb08-3391-418e-bd74-37d7737be28b'
);

update jobs
set mental_per_level = 4, physical_per_level = 2
where id in (
    '9132517e-f3da-4321-905d-63a743d9344a',
    'fd505408-014e-4ee7-96fb-ed577c0f3e93',
    'a719d6dd-ef3d-4438-b834-3c7a399df384',
    '339c9479-06c9-42d5-9920-535b34ace847'
);

alter table jobs alter column mental_per_level set not null;
alter table jobs alter column physical_per_level set not null;

-- Add skill level
alter table skills add column skill_level int4 not null default 1;

-- Update skills descriptions
update skills
set
    description = 'Um Artífice pode apanhar objetos mundanos, aprimorá-los e lançá-los contra seus inimigos. O Atributo usado é Intelecto, causa dano em Energia Física + Ataque Básico e custa 2 de Energia Mental. Caso acerte, o Artífice pode escolher um dos efeitos a seguir: \n\tEstilhaços: causa um dado extra de Energia Física adicional.\n\tGosmenta: o alvo não pode movimentar-se no próximo turno.\n\tOfuscante: o alvo não pode atacar no próximo turno.',
    cost = 2
where id = '796d27d5-0a97-487a-b96e-654275f51d96';

update skills
set description = 'Concentrando-se brevemente, um Artífice entende os detalhes dos mais complexos mecanismos. Com uma Ação Complexa, ele pode enxergar através de paredes e objetos não-metálicos, observando engrenagens e detalhes de mecanismos em uma distância de até 5m. Gasta 1 ponto de Energia Mental e faz um teste de Intelecto. Caso um outro Artífice tenha fabricado o mecanismo, 1 ponto de Energia adicional será gasto, ou a análise falhará.'
where id = 'f4a888da-e658-465c-80d3-1febe35f7075';

update skills
set
    description = 'A simples presença de um Artista é suficiente para inspirar aliados e abalar inimigos. Apenas em combate, o Artista usa sua Astúcia e gasta 2 Pontos de Energia Mental. Ele então escolhe um aliado e um inimigo para receber o efeito de sua Presença. Ambos deverão rolar dois dados em sua próxima rolagem, seja de Ataque ou Defesa. O aliado deve utilizar o melhor resultado, enquanto o inimigo, o pior. Em um aliado, este efeito se acumula com o bônus de Aptidão.',
    cost = 2
where id = 'c83ea26d-d63a-4377-a748-c0d3b1c78317';

update skills
set description = 'Ao gastar 1 ponto de Energia Mental adicional, independente do acerto, o Artista realiza um teste de Astúcia apenas fora de combate. Em um sucesso, seu charme influencia a atmosfera ao seu redor e caso não houvesse nenhuma briga já acontecendo, nenhum ato violento pode ser iniciado enquanto não houver distrações que tirem o foco do Artista. Apenas seres do plano material e inteligentes são afetados por seu Charme.'
where id = 'b120ecb8-ee19-483e-9333-03c91f6787f5';

update skills
set
    description = 'Perito no combate à distância, encontrar cobertura para se proteger enquanto desfere seus ataques é uma habilidade nata de um Atirador. Usando sua Celeridade, o Atirador gasta 2 pontos de energia Física para Levantar a Guarda utilizando uma Ação Simples.',
    cost = 2
where id = '8cc61538-a9c0-418e-a8e8-82e96518fda1';

update skills
set description = 'Ao gastar 1 ponto de Energia Física e realizar um Teste de Celeridade, o Atirador se concentra no território ao seu redor até onde sua vista alcança. Em áreas abertas, o alcance é de 1km. Em áreas fechadas, desde que não haja nada bloqueando sua visão, o reconhecimento é total. Em caso de sucesso, pode conseguir identificar e localizar uma das seguintes opções:\n\tUm alvo específico, desde que já o tenha visto ou tenha uma descrição aproximada.\n\tSeres vivos, podendo dizer número, sem distinguir entre hostis, aliados ou neutros.\n\tTerreno, bloqueios, atalhos e áreas perigosas.'
where id = '78c3d6ef-eab5-4237-8559-90027f1ae721';

update skills
set description = 'Manter-se de pé até que todas as suas forças sejam exauridas é o maior mecanismo de defesa de um Bárbaro. Sempre que receber um dano que reduziria sua Energia Física a 0, o Bárbaro pode converter Energia Mental em Física para manter-se de pé.'
where id = '2984acb5-8bf3-4a14-a74e-3fa33ad9c2c7';

update skills
set
    description = 'Um Diplomata sabe tomar as atenções para si, mesmo que de forma negativa. Utilizando sua Astúcia, ao gastar 2 pontos de Energia Mental, o Diplomata pode forçar de um a três inimigos a o atacarem em seus próximos turnos. Quando o fizer, ele será considerado com a Guarda Levantada.',
    cost = 2
where id = '3d555ba0-d9e8-4d17-b2de-4af0fe5fd30b';

update skills
set description = 'Enquanto estiver argumentando, um Diplomata pode realizar um teste de Intelecto com um custo adicional de 1 Energia Mental, independente do acerto, para ganhar um argumento, convencer alguém a aceitar uma oferta que não coloque a pessoa em risco ou extrair alguma informação sem parecer invasivo. Este efeito é aplicado em apenas um alvo e, caso outras pessoas presenciam a conversa, podem perceber a manipulação do Diplomata.'
where id = 'c74acd60-1b76-4967-9229-0217b208382a';

update skills
set
    description = 'Usando de sua própria energia vital e Tenacidade, o Druida pode gastar 2 Pontos de Energia Física para criar invocações etéreas de plantas ou animais para auxiliá-lo em combate. O Druida pode chamar quantas invocações desejar, mas elas sempre contam apenas como uma única criatura. As invocações podem realizar Ataques Básicos no turno do Druida e são desfeitas ao receberem qualquer tipo de dano.',
    cost = 2
where id = '2afe89c7-3185-43d1-8eef-6e2b68050a3f';

update skills
set
    description = 'Ao tomar a frente em um combate, o Guerreiro pode escolher gastar 2 Pontos Energia Física e usar sua Tenacidade para realizar qualquer ação. Se o fizer, cada aliado que agir de acordo com a liderança do Guerreiro, ganhará um bônus igual ao seu Ataque Básico adicional de dano ou redução de dano em seu turno.',
    cost = 2
where id = '9c934970-eb08-4426-a8d9-77d6f7936faf';

update skills
set
    name = 'Ocultação',
    description = 'O Ladino pode usar sua Celeridade e gastar 2 Pontos Energia Física para tornar-se Oculto, praticamente invisível no campo de batalha, antes ou após realizar seu ataque, como uma Ação Simples. Até se mover ou atacar novamente, o Ladino não pode ser alvo de ataques ou habilidades direcionados.',
    cost = 2
where id = '1f49c878-8e5d-45ac-afc8-5537b28936fc';

update skills
set description = 'O Ladino tem um talento especial para ir e vir despercebido. Desde que tome um pouco mais de cuidado, o Ladino é capaz de gastar uma Ação para fazer um teste de Celeridade com 1 Ponto de Energia Física, independente do acerto, e adicional e tornar seu rosto quase completamente imemorável. Em um lugar relacionado a seu Antecedente, ele é capaz de estender esse efeito a até 4 pessoas. Caso cause algum tipo de alarde, o Ladino perde esse efeito imediatamente, atraindo a atenção de todos ao redor. Apenas tome cuidado: Bárbaros ainda podem perceber que Algo de Errado Não Está Certo!'
where id = '1f49c878-8e5d-45ac-afc8-5537b28936fc';

update skills
set
    description = 'A manipulação dos quatro elementos é o início do aprendizado de um Mago. Ele pode usar seu Intelecto para gastar 2 Pontos de Energia Mental e conjurar uma magia com o efeito de um elemento à sua escolha. O dano é de 2d Intelecto + Ataque Básico e o efeito deverá ser escolhido com a ajuda do mestre.',
    cost = 2
where id = '1b803a9b-1e22-4ee3-99e8-2ea7dffa145f';

update skills
set
    description = 'O Nexus pode usar seu Intelecto e gastar 2 Pontos de Energia Mental para distorcer o espaço ao seu redor. Todas as criaturas dentro de uma circunferência com 5m de raio, ficam aprisionadas e podem mover-se apenas em direção ao centro da área, que é o próprio Nexus.',
    cost = 2
where id = '4f3cc16a-7178-4463-bc24-90a4ba98ee5f';

update skills
set
    description = 'Um Sacerdote pode gastar 2 pontos de Energia Mental para curar um alvo em 4 pontos de qualquer Energia.',
    cost = 2
where id = 'f8163993-6a30-44bb-968e-b64b11bb80a6';

update skills
set cost = 4
where id = 'b82c0233-06c8-43e1-bb99-d328112ce1e3';

update skills
set
    description = 'Quando um aliado recebe um ataque, o Sentinela pode, fora de seu turno, usar sua Celeridade e gastar 2 Pontos de Energia Física para mover-se rapidamente até o aliado e absorver o dano do ataque para si. Quando o fizer, a Defesa será considerada com Guarda Levantada.',
    cost = 2
where id = '939f3d16-d6c1-4471-b394-f81752824a97';