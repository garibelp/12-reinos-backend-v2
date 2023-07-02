insert into skills(id, job_id, cost, range, name, energy_type, skill_type, skill_level, description)
values
-- Artifice
(
    '91329379-f2a9-4a10-8a38-722ebb62647b',
    'eb98fa25-634a-4e93-8898-0c9dc7f01c01',
    3,
    NULL,
    'Armadilha',
    'MENTAL',
    'COMBAT',
    2,
    'Gambiarras agora podem tornar-se Armadilhas. Para fazer uma, o Atributo usado é Intelecto, ela causa dano em Energia Física e custa 3 de Energia Mental. A Armadilha deve ser plantada em um local específico e é ativada com um gatilho definido pelo Artífice. Tem os mesmos efeitos que a Gambiarra, com um bônus de um dado de dano em qualquer um deles.'
),
(
    '797c82e2-cd26-48ff-a0df-e322c2ffd0b0',
    'eb98fa25-634a-4e93-8898-0c9dc7f01c01',
    2,
    NULL,
    'Reparo',
    'MENTAL',
    'INTERPRETATION',
    2,
    'Um Artífice pode realizar pequenos reparos em objetos e mecanismos que estejam leve ou moderadamente danificados, gastando 2 Pontos de Energia Mental e realizando um Teste de Intelecto.'
),
(
    'a38f5007-613a-4012-be7d-e71fcab1f44e',
    'eb98fa25-634a-4e93-8898-0c9dc7f01c01',
    5,
    NULL,
    'Bugiganga',
    'MENTAL',
    'COMBAT',
    3,
    'Prática e estudos podem transformar Gambiarras em Bugigangas. Para fazer uma, o Atributo usado é Intelecto, ela causa dano em Energia Física e custa 5 de Energia Mental. Cada Bugiganga tem um efeito adicional a ser definido pelo Artífice em conjunto com o Narrador. Ela pode andar, voar, nadar, criar efeito sonoro ou visual, chamar atenção, esconder-se… a criatividade do Artífice é o limite. Além disso, ela mantém os efeitos da Gambiarra, com bônus de 2d Intelecto.'
),
(
    '0a3da04a-60b4-4b6f-ab7d-312699e2841d',
    'eb98fa25-634a-4e93-8898-0c9dc7f01c01',
    3,
    NULL,
    'Engenharia Reversa',
    'MENTAL',
    'INTERPRETATION',
    3,
    'O conhecimento e a familiaridade com invenções de um Artífice à essa altura permite que ele consiga fazer um Teste de Intelecto, gastando 3 Pontos de Energia Mental, para desmontar qualquer máquina e compreender como ela foi construída. Em um sucesso, o  Artífice deve passar um dia dedicado à atividade e, então, aprende a montá-la e pode fazê-lo quando quiser, desde que tenha os materiais necessários.'
),
-- Artista
(
    'e5319c81-6444-4a4b-94fa-cf628959e9ef',
    'c0169b57-d4d2-49f2-8abe-b0f87c5e9fae',
    3,
    NULL,
    'Crítica',
    'MENTAL',
    'COMBAT',
    2,
    'Um Artista sabe mais do que ninguém o tamanho do poder de uma Crítica, seja ela positiva ou negativa. Apenas dentro de um combate, ao gastar 3 Pontos de Energia Mental adicionais, o Artista pode proferir uma Crítica Construtiva (para aliados) ou Destrutiva (para inimigos), que em um sucesso causará um dos seguintes efeitos:\n\tAliado: o alvo adiciona um dado extra à sua próxima rolagem de Ataque ou Defesa.\n\tInimigo: o Artista causa 1d Astúcia de dano em Energia Mental.'
),
(
    'd6a307c2-9bc4-4539-8fbf-1bcd842ab26b',
    'c0169b57-d4d2-49f2-8abe-b0f87c5e9fae',
    2,
    NULL,
    'Inspiração',
    'MENTAL',
    'INTERPRETATION',
    2,
    'Apenas fora de combate, um Artista pode inspirar aqueles ao seu redor. Para cada rolagem realizada na presença de um Artista de alvos que ele deseja inspirar, 2 Pontos de Energia Mental são gastos. O alvo, então, fica impedido de obter Falhas Críticas, e Sucessos são sempre considerados Sucessos Absolutos.'
),
(
    '5b59a688-d548-4e17-bbd7-f67b585a249f',
    'c0169b57-d4d2-49f2-8abe-b0f87c5e9fae',
    5,
    NULL,
    'Entusiasmo',
    'MENTAL',
    'COMBAT',
    3,
    'O Artista pode gastar 5 Pontos de Energia Mental para curar instantaneamente 10 Pontos de qualquer Energia, distribuídos entre qualquer número de alvos.'
),
(
    '11883240-5731-478d-9c80-87a3b77e5d00',
    'c0169b57-d4d2-49f2-8abe-b0f87c5e9fae',
    3,
    NULL,
    'Abrigo',
    'MENTAL',
    'INTERPRETATION',
    3,
    'A presença de um Artista durante um descanso fazer de um simples acampamento um verdadeiro Abrigo. Ao gastar 3 Pontos de Energia Mental, o Artista faz com que todas as Energias sejam recuperadas pelo grupo em 4 horas, não 8. Os 3 Pontos são gastos antes do descanso, ou seja, o Artista começa o dia também recuperado.'
),
-- Atirador
(
    '40aa9027-88f9-442d-a1ba-c63bd7b9d581',
    '9132517e-f3da-4321-905d-63a743d9344a',
    3,
    NULL,
    'Tiro Certo',
    'PHYSICAL',
    'COMBAT',
    2,
    'Desde que esteja em Cobertura, o Atirador pode gastar 3 Pontos de Energia Física para estabilizar sua mira e realizar um Tiro Certo em seu próximo Ataque Básico, utilizando 3x o valor de seu Ataque Básico na soma.'
),
(
    '44fcc9fb-4baa-4647-b8ff-06ed0258469d',
    '9132517e-f3da-4321-905d-63a743d9344a',
    2,
    NULL,
    'Rastreamento',
    'PHYSICAL',
    'INTERPRETATION',
    2,
    'Ao gastar 2 Pontos de Energia Física, independente do acerto, e realizar um Teste de Celeridade, o Atirador pode rastrear um alvo que já tenha visto, que já lhe tenha sido descrito ou do qual possua algum pertence. Caso o alvo esteja a até 1 dia de viagem de distância, o Atirador consegue rastreá-lo e descobrir a direção para onde está indo, seu meio de locomoção, velocidade e se está acompanhado e o número de acompanhantes.'
),
(
    'a6ea3772-2579-42e9-b1a6-0325b69c0d3c',
    '9132517e-f3da-4321-905d-63a743d9344a',
    5,
    NULL,
    'Duplo Disparo',
    'PHYSICAL',
    'COMBAT',
    3,
    'O Atirador pode gastar 5 Pontos de Energia Física para realizar dois Disparos usando sua Ação Complexa. Se estiver em Cobertura, os dois são considerados Tiro Certos.'
),
(
    '3729d5f8-f3ff-4471-aad1-f1b7ee9158dc',
    '9132517e-f3da-4321-905d-63a743d9344a',
    3,
    NULL,
    'Patrulha',
    'PHYSICAL',
    'INTERPRETATION',
    3,
    'O Atirador pode gastar 3 Pontos de Energia Física para realizar um Teste de Celeridade e passar 1 hora fazendo uma Patrulha em um território de 5km de raio a partir de si mesmo. Ele o faz sem ser percebido e retorna com informações detalhadas sobre o entorno. Além disso, o Atirador pode encontrar comida e água e trazer para o ponto original, se assim desejar.'
),
-- Bárbaro
(
    'f934b147-73cb-4d20-9a30-d04a46d75816',
    'fd505408-014e-4ee7-96fb-ed577c0f3e93',
    0,
    NULL,
    'Fúria',
    'MENTAL',
    'COMBAT',
    2,
    'O Bárbaro pode converter Pontos de Energia Mental em dano extra para seu próximo ataque. Cada ponto gasto é convertido em 3 de dano.'
),
(
    '08faa896-886d-40a0-9b63-26a5d10d9c73',
    'fd505408-014e-4ee7-96fb-ed577c0f3e93',
    2,
    NULL,
    'Sentido Sobre-humano',
    'PHYSICAL',
    'INTERPRETATION',
    2,
    'O Bárbaro pode gastar 2 Pontos de Energia Física, independente do acerto, para realizar um Teste de Tenacidade e concentrar-se em seus sentidos. Em um Sucesso, sua sensibilidade aumenta e ele pode escolher um dos 5 sentidos para tornar-se sobre-humano. Isto é, ele pode escolher sua audição para ouvir uma conversa a metros de distância em meio à uma multidão; ou o olfato, para rastrear um aroma específico dentro de um mercado. O efeito depende de sua criatividade e de um acordo com o Narrador.'
),
(
    'b97a556f-2c04-46c7-be4a-718ebfd87fe4',
    'fd505408-014e-4ee7-96fb-ed577c0f3e93',
    0,
    NULL,
    'Frenesi',
    'PHYSICAL',
    'COMBAT',
    3,
    'O Bárbaro pode intensificar seu poder e força a ponto de regenerar suas Energias conforme desfere dano. A cada 5 Pontos na soma do dano, mesmo que não acertem o alvo por conta de sua Defesa, o Bárbaro recupera 1 Energia Física.'
),
(
    '60ccfa29-7de6-42f0-97fe-958b13382a4d',
    'fd505408-014e-4ee7-96fb-ed577c0f3e93',
    3,
    NULL,
    'Pulmões de Aço',
    'PHYSICAL',
    'INTERPRETATION',
    3,
    'O Bárbaro pode gastar 3 Pontos de Energia Física para fazer um Teste de Tenacidade e prender a respiração por até 30 minutos.'
),
-- Diplomata
(
    '7cbfe5ec-3403-4593-bbc9-d9996a1f0bfd',
    '787c91b7-ab88-4e50-bf5d-30c09204e74d',
    3,
    NULL,
    'Ganhar Tempo',
    'MENTAL',
    'COMBAT',
    2,
    'O Diplomata pode escolher três alvos hostis e usar uma Ação Complexa para distraí-los com um discurso eloquente. O atributo usado é Astúcia, e o custo é de 3 Pontos de Energia Mental por alvo. Os alvos devem ouvi-lo e não podem atacar até que sejam atacados.'
),
(
    'fb244147-af48-404d-a9fe-76fb0103fe55',
    '787c91b7-ab88-4e50-bf5d-30c09204e74d',
    2,
    NULL,
    'Paranóia',
    'MENTAL',
    'INTERPRETATION',
    2,
    'Fora de combate, um Diplomata pode realizar um teste de Intelecto com um custo adicional de 2 Energia Mental, independente do acerto, para criar uma paranóia em um alvo e convencê-lo de um perigo ou situação que lhe esteja colocando em risco. Este efeito é aplicado em apenas um alvo e, caso outras pessoas presenciam a conversa, podem perceber a manipulação do Diplomata.'
),
(
    '23895edd-fb79-4ef4-af99-c8135efd4f07',
    '787c91b7-ab88-4e50-bf5d-30c09204e74d',
    5,
    NULL,
    'Negociar',
    'MENTAL',
    'COMBAT',
    3,
    'O Diplomata pode gastar 5 Pontos de Energia Mental para Apaziguar uma briga. Com argumentos convincentes, um combate contra criaturas inteligentes pode ser convertido em uma negociação. Caso a negociação não atenda às necessidades dos inimigos, o combate será retomado.'
),
(
    'e859e229-80a8-4e66-8db3-0f3f34a784a2',
    '787c91b7-ab88-4e50-bf5d-30c09204e74d',
    3,
    NULL,
    'Inserir Ideia',
    'MENTAL',
    'INTERPRETATION',
    3,
    'Desde que fora de combate, o Diplomata pode gastar 3 Pontos de Energia Mental, independente do acerto, para rolar um Teste de Astúcia. Em um sucesso, um alvo se deixa levar pela lábia do Diplomata, que consegue lhe implantar uma ideia sem que ele saiba que ela não é sua. Este efeito é aplicado em apenas um alvo e, caso outras pessoas presenciem a conversa, podem perceber a manipulação do Diplomata.'
),
-- Druida
(
    '473671be-2a43-4f9a-a777-f692ba8759ec',
    '4c5e9d82-d2f9-452e-bbf1-585788816098',
    3,
    NULL,
    'Modificar Terreno',
    'PHYSICAL',
    'COMBAT',
    2,
    'O Druida pode gastar 3 Pontos de Energia Física para modificar o terreno à sua volta, numa área de 10m diâmetro a partir de si mesmo, criando barreiras físicas, abrindo buracos no chão ou mesmo fazendo água fluir onde antes havia solo seco. Embora esta Habilidade não cause dano, ela poderá ajudar ou atrapalhar aliados e inimigos. O efeito deverá ser combinado com o Narrador.'
),
(
    'b9ebe79b-2e63-41c9-92cb-e4ae07362eb3',
    '4c5e9d82-d2f9-452e-bbf1-585788816098',
    2,
    NULL,
    'Invocar Montaria',
    'PHYSICAL',
    'INTERPRETATION',
    2,
    'O Druida pode gastar 2 Pontos de Energia Física para realizar uma Invocação Etérea mais sólida, planta ou animal, que serve de montaria. Ela não pode atacar e se desfaz ao receber dano.'
),
(
    'd5ce0aba-348f-49fa-8b9a-d29f4444ba38',
    '4c5e9d82-d2f9-452e-bbf1-585788816098',
    5,
    NULL,
    'Invocação Tangível',
    'PHYSICAL',
    'COMBAT',
    3,
    'Ao gastar 5 Pontos de Energia Física, o Druida pode fazer uma Invocação Tangível além da sua Invocação Etérea. Ela deve ser uma única criatura ou planta, com Energia Física máxima igual à do Druida, mas sem Energia Mental. Ela é desfeita quando tiver sua Energia Física reduzida à zero, ou se ela ou o Druida receberem dano em Energia Mental.'
),
(
    '4fb89503-f246-43fa-813c-5e3544fd4d6a',
    '4c5e9d82-d2f9-452e-bbf1-585788816098',
    0,
    NULL,
    'Comunhão',
    'MENTAL',
    'INTERPRETATION',
    3,
    'Desde fora de combate e em contato direto com a natureza, em uma área vasta não explorada pela civilização, o Druida não precisa descansar. Em Comunhão, basta isolar-se por 1h em silêncio e meditação que ele recuperará todos os seus Pontos de Energia.'
),
-- Guerreiro
(
    '1c0489aa-654e-410f-80af-98ca66f65187',
    'a719d6dd-ef3d-4438-b834-3c7a399df384',
    3,
    NULL,
    'Estudar Alvo',
    'PHYSICAL',
    'COMBAT',
    2,
    'O Guerreiro pode gastar 3 Pontos de Energia Física para usar sua Tenacidade e gastar seu Movimento e Ação Complexa para rodear e analisar o alvo, desvendando um ponto fraco que pode ser explorado em combate.'
),
(
    '040a1104-05b6-469e-b2d7-d61f630659e2',
    'a719d6dd-ef3d-4438-b834-3c7a399df384',
    2,
    NULL,
    'Preparação',
    'PHYSICAL',
    'INTERPRETATION',
    2,
    'O Guerreiro pode gastar 2 Pontos de Energia Física para respirar, concentrar-se e analisar um problema que exija sua Preparação. Este deve durar uma hora e um plano deve ser traçado, composto de no mínimo 3 ações que exigirão Testes do Guerreiro ou de seus aliados. Feita a Preparação, no desenrolar do plano, quando as ações traçadas forem realizadas, seus Testes ganham +1 e não podem resultar em Falhas Críticas: caso o dado aponte uma, deverá ser rolado novamente. No entanto, qualquer outra rolagem não antecipada ao longo do desenrolar do plano não recebe o bônus e deve ser tratada como um Teste comum.'
),
(
    'e9e5d2d0-05ba-4b99-be3b-7817c1fb1659',
    'a719d6dd-ef3d-4438-b834-3c7a399df384',
    5,
    NULL,
    'Estratégia',
    'PHYSICAL',
    'COMBAT',
    3,
    'O Guerreiro pode gastar 5 Pontos de Energia Física para parar a cena de combate e discutir uma estratégia com seus aliados. Dessa forma, quando o combate for retomado, toda sua equipe terá 1 rodada extra para agir em que seus oponentes não poderão fazer nada.'
),
(
    '719b2776-34bd-487c-a5d2-a4a52ac9a59f',
    'a719d6dd-ef3d-4438-b834-3c7a399df384',
    3,
    NULL,
    'Plano B',
    'PHYSICAL',
    'INTERPRETATION',
    3,
    'O Guerreiro pode gastar 3 Pontos de Energia Física e fazer um Teste de Tenacidade para pensar rapidamente em solução alternativa para um problema. Dessa forma, mesmo que no meio de um diálogo ou interação, o Guerreiro pode tomar um tempo para discutir com o grupo e bolar um Plano B. A partir de então, as próximas 2 rolagens de Teste feitas serão consideradas como Testes de Preparação.'
),
-- Ladino
(
    'eb1afc34-fec9-4eb5-acdf-a2842360f58e',
    '339c9479-06c9-42d5-9920-535b34ace847',
    3,
    NULL,
    'Ataque Furtivo',
    'PHYSICAL',
    'COMBAT',
    2,
    'Desde que esteja Oculto, o Ladino pode gastar 3 Pontos de Energia Física para receber um bônus de mais 1d em seu próximo Ataque Básico. Após realizar o ataque, o Ladino continua Oculto sem gastar Pontos adicionais.'
),
(
    'f04aa4f2-edf9-4467-81a4-98ee448275ad',
    '339c9479-06c9-42d5-9920-535b34ace847',
    2,
    NULL,
    'Abrir Fechadura',
    'PHYSICAL',
    'INTERPRETATION',
    2,
    'O Ladino pode gastar 2 Pontos de Energia Física, independente do acerto, para rolar um Teste de Celeridade e tentar abrir uma fechadura. Em uma Falha Crítica, outro dado é rolado e, caso este segundo resultado seja uma Falha ou outra Falha Crítica, a fechadura é danificada e não poderá ser aberta novamente. Em Sucesso ou Sucesso Absoluto, o Ladino não apenas consegue abrir a fechadura como não precisará mais rolar Testes para trancá-la ou destrancá-la novamente no futuro. Fechaduras mágicas não podem ser abertas com essa habilidade.'
),
(
    '5b197d3f-aa66-45c4-8d19-8187dc905933',
    '339c9479-06c9-42d5-9920-535b34ace847',
    5,
    NULL,
    'Esquiva',
    'PHYSICAL',
    'COMBAT',
    3,
    'Quando atacado, Ladino pode gastar 5 Pontos de Energia Física para realizar uma Esquiva, prevenir 2d Astúcia de dano e tornar-se Oculto imediatamente.'
),
(
    '620d8f5f-0409-4644-b9ae-c29da5a811d6',
    '339c9479-06c9-42d5-9920-535b34ace847',
    3,
    NULL,
    'Caminhante das Sombras',
    'PHYSICAL',
    'INTERPRETATION',
    3,
    'O Ladino pode gastar 3 Pontos de Energia Física e fazer um Teste de Celeridade para escalar, correr, saltar e esgueirar-se à vontade por 1h sem ser percebido.'
),
-- Mago
(
    'f516a48c-9739-4fda-9a2e-c9859722505b',
    '4a48caa2-7f79-435f-ad42-92359248093c',
    3,
    NULL,
    'Escudo Arcano',
    'MENTAL',
    'COMBAT',
    2,
    'O Mago pode gastar 3 Pontos de Energia Mental para conjurar um Escudo Arcano em um alvo. O Mago deve rolar 2d de seu Intelecto e o resultado será o dano que o escudo poupará. O escudo é quebrado quando o valor chegar a 0.'
),
(
    '2e4adcfc-9f14-4101-b4d0-3dedfeebaea0',
    '4a48caa2-7f79-435f-ad42-92359248093c',
    2,
    NULL,
    'Projeção',
    'MENTAL',
    'INTERPRETATION',
    2,
    'O Mago pode gastar 2 Pontos de Energia Mental para entrar em um estado de transe e projetar sua mente para seu Familiar. Dessa forma, tudo o que o Familiar experienciar será também vivido pelo Mago. No entanto, o corpo do Mago fica vulnerável enquanto o efeito durar. Apenas o Mago pode encerrar a Projeção e, caso ele morra com sua consciência dentro do Familiar, ela ficará presa para sempre.'
),
(
    '9871f4ce-ca81-457a-a1a6-1b5bca626717',
    '4a48caa2-7f79-435f-ad42-92359248093c',
    5,
    NULL,
    'Projétil Arcano',
    'MENTAL',
    'COMBAT',
    3,
    'O Mago pode gastar 5 Pontos de Energia Mental para conjurar um Projétil Arcano e causar 3d Intelecto + Ataque Básico de dano. Cada dado pode ser direcionado a um alvo diferente.'
),
(
    '48388a3f-c24b-45eb-aaaa-12b50ea35883',
    '4a48caa2-7f79-435f-ad42-92359248093c',
    3,
    NULL,
    'Familiar Invisível',
    'MENTAL',
    'INTERPRETATION',
    3,
    'O Mago pode gastar 3 Pontos de Energia Mental para tornar seu familiar invisível. Ele não pode ser detectado, a não ser que o alvo possua alguma habilidade específica para tal, como Bárbaros e Atiradores.'
),
-- Nexus
(
    '9e629916-96de-4e2a-9f11-f7f0fbebbc1f',
    '839eef4c-294c-4ac1-b1e2-317229ddb093',
    3,
    NULL,
    'Vórtice',
    'MENTAL',
    'COMBAT',
    2,
    'Enquanto dentro da área afetada por Distorcer Espaço, o Nexus pode criar um Vórtice ao gastar 3 Pontos de Energia Mental. Ao fazê-lo, todas as criaturas dentro da área da magia, com exceção do Nexus, recebem 1d Intelecto + Ataque Básico de dano em Pontos de Energia Física de Dano e são deslocadas em um movimento circular em até 5m, no sentido horário ou anti-horário, a ser definido pelo Nexus.'
),
(
    '41fbf689-c85d-49a2-a6ec-4f0a016a2ca1',
    '839eef4c-294c-4ac1-b1e2-317229ddb093',
    2,
    NULL,
    'Rebobinar',
    'MENTAL',
    'INTERPRETATION',
    2,
    'Desde que fora de combate, o Nexus pode gastar 2 Pontos de Energia Mental, independente do acerto, para rolar um Teste de Intelecto e rebobinar o tempo para todos exceto ele em até 5 segundos. Com isso, é possível impedir uma ação imprudente de um aliado, intervir em um diálogo que terá um resultado negativo ou reverter uma rolagem mal sucedida.'
),
(
    '708db8bd-9992-4305-9fa1-5315cf85dc55',
    '839eef4c-294c-4ac1-b1e2-317229ddb093',
    5,
    NULL,
    'Órbita',
    'MENTAL',
    'COMBAT',
    3,
    'Enquanto dentro do Vórtice, o Nexus pode gastar 5 Pontos de Energia Mental para criar um campo gravitacional que fará com que todos afetados pela área da Habilidade passem a orbitá-lo. Os alvos, então, não podem deixar a área de efeito e recebem 2d Intelecto + Ataque Básico por turno. A Órbita dura enquanto o Nexus estiver em pé.'
),
(
    '67c1d873-1865-4727-8da5-fff0d03892d2',
    '839eef4c-294c-4ac1-b1e2-317229ddb093',
    3,
    NULL,
    'Acelerar',
    'MENTAL',
    'INTERPRETATION',
    3,
    'Desde que fora de combate, o Nexus pode gastar 3 Pontos de Energia Mental para fazer um Teste de Intelecto. Em um sucesso, ele cria uma área de aceleração do tempo para si e seus aliados, que passam a se mover duas vezes mais rápido. O efeito também pode ser aplicado em Montarias e Automóveis.'
),
-- Sacerdote
(
    '0b64bf97-a35e-4702-b806-54f9c0690bbf',
    '47117c6c-27a4-42db-88f2-a0972a93eadf',
    3,
    NULL,
    'Santuário',
    'MENTAL',
    'COMBAT',
    2,
    'Um Sacerdote pode gastar 3 Pontos de Energia Mental para criar uma área de 5m ao seu redor que cura 3 Pontos de cada Energia para quem começar seu turno nele. Caso uma das Energias esteja cheia, a cura excedente é transferida para a outra. Para manter o Santuário ativo, o Sacerdote não pode deixar a área e deve continuar gastando 3 Pontos de Energia Mental por turno, que são convertidos em 2, já que o Santuário também lhe cura 1 Ponto.'
),
(
    '9709e935-b740-4b2b-b091-ccce43f7ded3',
    '47117c6c-27a4-42db-88f2-a0972a93eadf',
    2,
    NULL,
    'Cura',
    'MENTAL',
    'INTERPRETATION',
    2,
    'O Sacerdote pode gastar 2 Pontos de Energia Mental, independente do acerto e desde que fora de combate, para Curar um alvo de uma doença, veneno ou efeito temporário ou permanente. Para tanto, é necessário que o Sacerdote saiba o que deseja Curar, saiba qual a fonte do malogro e que obtenha pelo menos um Sucesso em um Teste de Astúcia.'
),
(
    'da9c9fd8-39e4-4348-b24c-649dc389e3de',
    '47117c6c-27a4-42db-88f2-a0972a93eadf',
    5,
    NULL,
    'Prece',
    'MENTAL',
    'COMBAT',
    3,
    'O Sacerdote pode gastar 5 Pontos de Energia Mental para pedir auxílio de sua Deidade. Ele e todos os seus aliados ganham 3d para usar como preferirem, em qualquer Atributo, até o fim do combate.'
),
(
    '22535f42-f888-43fe-a30f-4033cad314a3',
    '47117c6c-27a4-42db-88f2-a0972a93eadf',
    3,
    NULL,
    'Confissão',
    'MENTAL',
    'INTERPRETATION',
    3,
    'O Sacerdote pode gastar 3 Pontos de Energia Mental para fazer um Teste de Astúcia e, em troca de um segredo seu, extrair uma confissão sincera de um alvo indiferente ou amigável a ele.'
),
-- Sentinela
(
    '22f42cf7-7cc5-407e-95bd-601eec2d3231',
    '3d14cb08-3391-418e-bd74-37d7737be28b',
    3,
    NULL,
    'Escudo Vivo',
    'PHYSICAL',
    'COMBAT',
    2,
    'O Sentinela pode gastar 3 Pontos de Energia Física para tornar-se um Escudo Vivo. Para tanto, ele deve se fixar em um ponto e não pode se movimentar ou atacar. Em contrapartida, o Sentinela não pode receber dano de Energia Física até o fim do efeito da Habilidade. Caso algum ataque ou habilidade prejudique sua Energia Mental, ou caso queira, a postura de Escudo Vivo é desfeita.'
),
(
    'd011c05e-9c7f-4c42-a52f-cb287c97a081',
    '3d14cb08-3391-418e-bd74-37d7737be28b',
    2,
    NULL,
    'Vigília',
    'PHYSICAL',
    'INTERPRETATION',
    2,
    'Com um Sentinela no grupo, não há necessidade de trocar turnos de Vigília durante um Descanso. O Sentinela pode, sozinho, guardar o acampamento enquanto todos usufruem da restauração de Energias. Ao fim do Descanso, o Sentinela recupera todas as Energias naturalmente, mas deve descontar 2 Pontos de Energia Física do total.'
),
(
    '9ab895fc-d0fd-469e-b8d3-8b6c1458e5e5',
    '3d14cb08-3391-418e-bd74-37d7737be28b',
    5,
    NULL,
    'Barreira',
    'PHYSICAL',
    'COMBAT',
    3,
    'As Habilidades e instintos defensivos do Sentinela evoluem tanto que ele é capaz de se transformar em uma barreira entre seus inimigos e aliados. Ele gasta 5 Pontos de Energia Física para, durante o próximo turno, correr pelo campo de batalha e conceder 1d Celeridade para todos os aliados em suas defesas.'
),
(
    'ecb3a6fe-b13d-472f-9cbf-81d78362bba6',
    '3d14cb08-3391-418e-bd74-37d7737be28b',
    3,
    NULL,
    'Virtude',
    'PHYSICAL',
    'INTERPRETATION',
    3,
    'O Sentinela pode gastar 3 Pontos de Energia Física para fazer um Teste de Celeridade e evocar o senso de responsabilidade em seus aliados. Todos passam a ter seus próprios Vínculos até realizarem um Descanso.'
);