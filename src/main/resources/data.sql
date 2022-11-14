INSERT INTO
    ROLES(id, name)
VALUES
    ('21e3f8c1-9f8c-437d-9ae5-de635dc8d202', 'ROLE_USER'),
    ('463ba3a0-fe9b-4453-ad60-dc2492c9ecff', 'ROLE_ADMIN')
ON CONFLICT DO NOTHING;

INSERT INTO
    LINEAGES(
        id,
        name,
        size,
        language,
        maturity,
        description,
        positive_trait_name,
        positive_trait_description,
        negative_trait_name,
        negative_trait_description
    )
VALUES
    (
        'd31aef87-abbb-4837-9f01-45cc19dd3090',
        'Anão',
        'Podem variar entre 90cm a 1,50m.',
         'Comum e Anão.',
         'Anões atingem a maturidade com 30 anos, e vivem em média até os 120.',
        'Durante a colonização de Bartol e nos primeiros séculos subsequentes, os anões foram os pilares do comércio em grandes cidades. Sua proeza e facilidade natas para trabalhar com minério os fizeram essenciais onde quer que decidissem instaurar uma guilda.\nInfelizmente, na Era do Vapor, poder e prestígio não vêm mais de moldar o metal e outras matérias primas. Vêm através de estudo, ciência, avanços tecnológicos minuciosos, e a grande maioria dos Anões nunca deu importância para isso.',
        'Azarão',
        'Anões, desde sempre, trabalham contra as probabilidades, para bem ou para mal. Podem se atrapalhar e falhar completamente em atividades simples, como obter sucessos surpreendentes em desafios quase impossíveis. Por isso, ao jogar com um Anão, você inverterá o resultado de seus dados, sempre. Dessa forma, em um d20, 1 se torna 20, 2 se torna 19, 3 se torna 18, e vice-versa.',
        'Provação',
        'O declínio dos Anões os tornou ainda mais amargurados que o seu normal por natureza. Todos os Anões ainda sonham que, um dia, tempos mais simples virão. Até lá, tentam se provar úteis e prestativos sempre que puderem... do jeito deles. Sempre que um Anão receber um desafio, iniciado pela palavra "Duvido...", ele tentará cumpri-lo.'
    ),
    (
        '4c43448f-8c7a-4bc2-833a-33daccd12501',
        'Ashariano',
        'Podem variar entre 1,20m a 2,10m.',
        'Comum e mais uma à escolha.',
        'Asharianos nascem já maduros, e vivem até serem fisicamente destruídos.',
        'A história dos Asharianos é um tanto... complicada.\nCriados para servirem de armas na Grande Guerra Bartoloriana, foram forjados a partir de almas humanóides encravadas em corpos de metal, todos ligados a uma mesma consciência coletiva, uma colmeia controlada por sua criadora, Andrômeda.\nO fim da guerra foi o início de sua libertação, física e mental. Fora da colmeia, com consciência própria, os Asharianos agora procuram um novo propósito para sua existência.',
        'Construto',
        'Asharianos não precisam comer, beber e descansar. Para recuperar suas energias, ao invés de usufruir de um descanso como as demais raças, eles entram em modo de reparo, e recebem os privilégios de um repouso em apenas uma noite.',
        'Auto-destruição',
        'Andrômeda deu a todas as suas criações um mecanismo de auto-destruição imbatível. Muitos Asharianos dedicaram suas vidas para descobrir uma forma de desabilitá-lo, em vão. Asharianos derrubados em combate sempre têm automaticamente 2 falhas em Teste de Vigor contra a morte, assim que caem. Caso acumulem a terceira, seu corpo explode causando 8d6 de dano a todas as criaturas a até 9m dele em qualquer direção.'
    ),
    (
        'f6ada2fd-2fb8-4dcf-be5d-133afa39cae2',
        'Cogni',
        'Podem variar entre 1,70m a 2m.',
        'Comum e Cogni.',
        'Cognis atingem a maturidade com 20 anos, e vivem em média até os 100.',
        'Os Cogni deixaram Faéria com os Eclésen, mas não ousaram viajar para o Sul de Bartol e, por isso, quase foram extintos o cataclisma do Deserto. Os sobreviventes tiveram que desenvolver tecnologias para se manterem vivos no clima árido do Norte, já que Cogni são meio anfíbios.\nA sede literal e as soluções criativas feitas para sobrevivência, com o passar do tempo, deram lugar a uma sede figurada por conhecimento. Os avanços tecnológicos dos Cogni os tornaram conhecidos por serem exímios inventores e cientistas. E sua alta capacidade de adaptação os tornaram ainda mais próximos de sua mãe e elemento natural, a água.',
        'Anfíbio',
        'Cognis podem respirar embaixo da água naturalmente por tempo indeterminado.',
        'Hidrofílico',
        'O Pó da Seca foi inventado pelos Cogni para sempre terem consigo uma fonte de água. Até hoje, cada um carrega uma bolsa carregada. Mas se, por algum motivo, um Cogni perder seu Pó da Seca, e passar um dia sem contato com água, perderá os privilégios de um descanso.'
    ),
    (
        '204625af-ab73-4261-aa65-7004945e6858',
        'Eclésen',
        'Podem variar entre 1m a 1,70m.',
        'Comum e Eclésen.',
        'Eclésens atingem a maturidade com 20 anos, e vivem em média até os 100.',
        'Alguns historiadores dizem que os Eclésen saíram de Faéria e habitavam toda Bartol. Os que permaneceram perto das Santerras e do hoje Deserto, foram quase todos dizimados por algum cataclisma ainda desconhecido. Os que desceram para o Sul do continente sofreram nas mãos dos Elfos ao ponto de terem sua natureza transformada, e viaram Goblins e Bugursos.\nOs Eclésen que permaneceram ao Norte e sobreviveram passaram por longos períodos de dificuldade e exploração devido a sua cultura dócil, pacífica e aparência frágil. Eles puderam sobreviver contando uns com os outros e seu senso de comunidade.',
        'Telepatia',
        'Para perdurar através das Eras de perigos que tiveram que atravessar, os Eclésen desenvolveram habilidades telepáticas. Suas comunidades podiam ficar em silêncio completo por anos a fio para não chamar atenção de animais selvagens. Posteriormente, quando explorados por outras raças, usavam essa habilidade para se comunicar em sigilo. Eclésen podem apenas falar através de telepatia, não ouvir os pensamentos de outras criaturas, a menos que esteja ouvindo a mensagem de outro telepata.',
        'Comunidade',
        'O senso de comunidade para sobrevivência está enraizado em todos os Eclésen. Por isso, ao jogar com um, esteja sempre próximo a pelo menos outro Eclésen, ou não poderá usufruir de um descanso. Apenas ver outro de sua raça uma vez no dia é o suficiente.'
    ),
    (
        '66020d52-4351-4f3c-9503-329e7c69dab2',
        'Elfo',
        'Podem variar entre 1,70m a 2m.',
        'Comum e Élfico.',
        'Elfos atingem a maturidade com 100 anos, e podem viver por tempo indeterminado.',
        'Primeiro, colonizadores. Depois, regentes tiranos. Hoje, esquecidos e desprezados por quase todos. Elfos vagam pelo continente tentando reparar sua imagem manchada por séculos de maldade, luxúria e arrogância.\nExistem vilarejos élficos escondidos aqui e ali, dos que ainda não aceitam se igualar às outras raças. Mas os mais sensatos buscam reconquistar seu lugar na sociedade através da bondade e da confiança. Seu acolhimento, no entanto, nem sempre é recíproco.',
        'Meditação',
        'Elfos não precisam dormir para usufruir de um descanso. Bastam duas horas ininterruptas em meditação plena para que estejam restaurados.',
        'Propósito',
        'Ao contrário da crença popular, Elfos não são exatamente imortais. A verdade é que esses seres vivem indeterminadamente apenas porque o destino os mantém intactos até que tenham cumprido seu propósito em vida. Mas, uma vez que o tenham feito, perecem tornando-se um com o cosmos.'
    ),
    (
        'bea483dc-73d4-4eaf-8426-621550351263',
        'Erepe',
        'Podem variar entre 2m a 2,50m.',
        'Comum e Erepes.',
        'Erepes atingem a maturidade com 100 anos, e vivem em média até os 450.',
        'Erepes deixaram as Santerras mas, ao contrário de Ecléses e Cognis, dirigiram-se para o Sudoeste das florestas, onde acabaram encontrando terras frias e impiedosas. O lado positivo é que puderam viver reclusos, afastados de outras raças, trabalhando seu espírito e mergulhando em uma profunda jornada para seu interior.\nPor isso, têm-se que Erepes são seres sábios, quase místicos, vistos raramente em outros cantos de Bartol. Sua serenidade é real, mas da mesma forma que são serenos, podem virar máquinas de destruição se ameaçados.',
        'Tromba',
        'Erepes podem usar suas trombas como um terceiro braço, para agarrar, lutar ou carregar objetos. Considere isso um segundo ataque grátis em sua ação no caso de optar por segurar uma arma. Escudos também podem ser usados dessa forma.',
        'Claustrofobia',
        'As casas e templos dos Erepes foram construídas aos pés de montanhas geladas com salões imensos e amplos. A grande maioria das construções de outras raças são pequenas demais para eles. Erepes não conseguem entrar em nenhum tipo de construção com menos de 5m de altura, ou se sentem sufocados. Essa característica também se aplica a cavernas, túneis, e qualquer outro lugar fechado.'
    ),
    (
        '0868c1ac-3170-4803-be88-4a511014f408',
        'Gnomo',
        'Podem variar entre 50cm a 1m.',
        'Comum e Gnomo.',
        'Gnomos atingem a maturidade com 100 anos, e vivem até os 200.',
        'Gnomos são originalmente de Bartol. Suas comunidades foram divididas durante a colonização élfica e, os que não se adaptam ao convívio com os novos povos retornaram ao seu plano de origem, Faéria. Os que ficaram, aprenderam a viver em harmonia com os demais.\nGrupos de gnomos são raros e pequenos. Quando atingem a maturidade, jovens sempre deixam sua casa para conhecer o mundo.',
        'Fortuna',
        'Gnomos, de alguma forma, sabem qual o caminho certo a seguir. Diferente dos Iuventi, onde a sorte os protege, para esses seres, o acaso sempre lhes indica a direção certa para seguir e encontrar fortuna, esteja ela na forma que for. É como um sexto sentido, impreciso, enigmático, porém confiável.',
        'Traquinagem',
        'Por viverem muito tempo entre outros jovens Gnomos até amadurecerem, e ter pouco tempo em sua vida adulta, é de se compreender que alguns traços infantis permaneçam nesses seres. Sempre que possível, um Gnomo pregará uma peça, seja em forma de piada ou de uma pegadinha prática.'
    ),
    (
        '870d1283-ed22-4002-87a0-2008583572e6',
        'Humano',
        'Podem variar entre 1,50m a 2m.',
        'Comum e mais um à escolha.',
        'Humanos atingem a maturidade com 20 anos, e vivem em média até os 80.',
        'Entre todas as raças, os Humanos são os mais efêmeros. Transitam entre as terras como se nunca pudessem ser donos verdadeiros de nada, pois alguém estará esperando para tomar suas conquistas. Por isso, a instituição da família entre Humanos é muito forte, para que descendentes carreguem legados e espólios dos antepassados. \nA brevidade de sua existência é, em essência, uma faca de dois gumes. Cientes de sua mortalidade, Humanos tendem a ser muito ambiciosos em vida. Mas há uma linha tênue entre ambição e ganância.',
        'Ambição',
        'Humanos lutam para fazer suas vidas valerem a pena, e para partirem do plano material em paz, por causas naturais. Ao cair em combate pela primeira vez, um Humano pode se levantar imediatamente e continuar lutando sem realizar testes.',
        'Ganância',
        'Algo na natureza transitória desses seres faz com que sua busca por conquistas em vida torne-se um pouco destrutiva. Humanos têm culturalmente uma obsessão em vida, movida pela ganância. Seja obter riquezas, conquistar fama, constituir uma família, algo em seu âmago os leva a tentar compulsivamente preencher o vazio gerado pela consciência de sua mortalidade. Um Humano trairá aliados para alimentar sua obsessão, se assim for necessário.'
    ),
    (
        '76cf6a07-8fd6-42b8-9b25-bd47f62c7914',
        'Iuventi',
        'Pode variar entre 60cm a 1m.',
        'Comum e mais um à escolha.',
        'Iuventis atingem a maturidade com 40 anos, e vivem em média até os 150.',
        'O entendimento popular sobre Iuventis é que os pequenos desejam apenas comer bem, viver bem e ter um lugar confortável para repousar sua cabeça à noite. Mas a verdade é que, como a maioria das outras comunidades de Bartol, existem heróis que anseiam por aventura entre esses simpáticos e amáveis seres.\nSua natureza, no entanto, precede sua fama. Embora sua bravura e curiosidade, por vezes, falem mais alto, algo em seu instinto os faz ser um pouco... passivos. Para compensar, há um toque de sorte divina nos Iuventi que os mantém longe de perigos e enrascadas.',
        'Sorte',
        'Iuventis são protegidos pela força do acaso. Uma vez entre descansos, um Iventi pode rolar escolher ter um Sucesso Absoluto em uma jogada que teria sido um Sucesso ou Falha.',
        'Passividade',
        'Os pequeninos ainda estão se acostumando com uma vida de aventuras, e algo em sua genética os faz precisar de um empurrão para engatar em atos heróicos. Por isso, Iuventis nunca tomam iniciativa. Eles podem dar ideias, discutir, se defender, mas nunca darão o primeiro passo de um plano.'
    ),
    (
        'a5ee042f-5033-4b33-b645-737a132bbdbf',
        'Kadit',
        'Podem variar entre 1,50m a 1,70m.',
        'Comum e Kadit.',
        'Kadits atingem a maturidade com 30 anos, e vivem em média até os 110.',
        'Misteriosos e fascinantes, os Kadits são humanóides meio gato que deixaram Ballaring muito antes da Ditadura de Has para desbravar Bartol.\nCada Kadit é único, solitário e desconfiado. Mas, uma vez que se apegam a alguém, tornam-se extremamente leais e apegados.',
        'Irrastreável',
        'Um Kadit nunca, jamais, deixa rastros por onde anda. Não importa o terreno e região, se na natureza ou na cidade, é impossível rastrear um Kadit se ele não o quiser.',
        'Expressivos',
        'Os grandes olhos, orelhas articuladas e calda não deixam os Kadits esconderem seus sentimentos. Esses seres são extremamente expressivos e nunca podem verdadeiramente esconder suas intenções. Suas palavras podem tentar dissuadir, mas sua linguagem corporal sempre revelará a verdade.'
    ),
    (
        '756e27c8-610c-4a96-a4d2-c27f6a913345',
        'Lavi',
        'Podem variar entre 1,80m a 2,20m.',
        'Comum e Laví.',
        'Lavís atingem a maturidade com 20 anos, e vivem em média até os 100.',
        'Lavís sempre foram e continuam sendo os reis de Ballaring. Regentes antes, durante e depois da Ditadura de Has, os humanóide meio leão são rivais naturais dos Áquilas, mas há muitas Eras conquistaram seu espaço na terra, para deixá-los sobrevoar pelo ar.\nUma grande parcela desses seres realmente se vê como superiores. Esse movimento deu força a Has e seu regime totalitário, que exilou as demais raças da grande Ballaring. Poucos se opuseram, mas o que fizeram hoje são tidos como grandes heróis.',
        'Rugido',
        'Para impor seu poder e realeza sobre os demais, a maior arma dos Lavís é seu rugido. Essa explosão sonora avassaladora é capaz de fazer inimigos soltarem suas armas e fugirem antes mesmo de começarem um combate. Uma vez entre descansos, quando um Laví ruge, criaturas hostis a ele perdem um turno de combate. Em cenas interpretativas, as criaturas são intimidadas.',
        'Descanso',
        'Como leões selvagens, Lavís precisam de muitas horas de sono para recuperarem suas forças. Caso jogue com um, para usufruir de um descanso, serão necessárias 14 horas ao invés de 8.'
    ),
    (
        'f9591e0a-94af-4253-9598-dd7a49cc84a4',
        'Orc',
        'Podem variar entre 1,70m a 2,10m.',
        'Comum e Orc.',
        'Orcs atingem a maturidade com 20 anos, e vivem em média até os 100.',
        'Orcs foram trazidos à força para Bartol pelos Elfos durante a colonização. Anos de sofrimento e revoltas violentas se sucederam até que conquistassem sua liberdade. A maldade dos Elfos criou um discurso de que Orcs eram selvagens e violentos e, portanto, inaptos para trabalhar com obediência, e por isso estavam sendo libertos. Mas isso não era, e nunca foi, verdade.\nEsse povo hoje vive sob um voto de paz e, mesmo que sua índole não seja boa, um Orc não apelará para violência física para não dar esse gosto aos Elfos.',
        'Respeito',
        'Orcs talvez sejam os únicos entre todos os habitantes de Bartol que não inspiram qualquer sentimento negativo à primeira vista. Onde quer que estejam, serão recebidos com respeito, e assim seguirão suas interações até que eles façam algo para evocar hostilidade para si.',
        'Pacifismo',
        'O confronto físico é uma prática abominável para os Orcs. A qualquer sinal de ameaça, sua reação será sempre tentar dialogar e apaziguar a situação. Por isso, um Orc nunca pode atacar na primeira rodada de um combate.'
    )
ON CONFLICT DO NOTHING;

INSERT INTO
    ADVANTAGES(id, name, description)
VALUES
    ('47fe0f9c-cc35-49bb-a513-360314533d93', 'Auto', 'Você começa o jogo com um Automóvel Leve que transporta até duas pessoas. Não há necessidade de usar Pontos de Heroísmo nessa Vantagem.'),
    ('8b5a7a72-7fc6-4b37-83ca-822ed0410879', 'Contato', 'Gaste 1 Ponto de Heroísmo para influir na história e dizer que você possui um contato útil que pode estar presente no local da cena atual, ou que pode encontrá-lo em um local específico na próxima cena. Cabe a você descrever quem é esse contato, como se conheceram e como você acredita que ele pode te ajudar. O Mestre deve aceitar, e a forma exata que seu contato lhe será útil será definida por ele. Você apenas acredita que ele pode ser útil, mas não sabe exatamente como. Mas o Mestre deverá sim torná-lo de alguma forma útil. Contatos normalmente são úteis com informações e com a venda de itens raros, mas dificilmente irão distribuir souvenires por aí...'),
    ('a8315ecb-0d2a-4e23-b9c3-81ebfd3cfcd3', 'Fama', 'Você é famoso por algum motivo entre a comunidade relativa ao seu Antecedente. Gaste 1 Ponto de Heroísmo para alterar o rumo de uma cena e dar uma carteirada ao interagir com um NPC. Caso esteja entre a comunidade de seu Antecedente (por ex. você tem o Antecedente Comerciante e está em uma loja), você consegue o que quer. Caso não esteja, você consegue o que quer, mas há um custo.'),
    ('4d5b3422-43bd-4cf3-9a17-f0399a122915', 'Recurso', 'Gaste 1 Ponto de Heroísmo para influir na história e dizer que você possui, ou sabe onde encontrar, os recursos necessários para a realização de uma tarefa específica. Recursos não são itens específicos como um item mágico, ou um mapa de uma região: são peças, ingredientes, ferramentas ou consumíveis. Por exemplo, se seu grupo precisa conseguir comida, você pode simplesmente ter algum estoque com você. Descreva como você conseguiu o recurso e, pronto, o Mestre deve acatar. Isso não funciona com recursos de Preço 3 ou maior.'),
    ('4cc5a30a-3928-44ab-b8e4-a55f5e8a1008', 'Refúgio', 'Gaste 1 Ponto de Heroísmo para alterar o rumo da história e dizer que você possui um refúgio por perto, com os suprimentos necessários para que o grupo descanse nele por um dia ou uma noite. Descreva como é o refúgio, onde fica e por que você o tem. Criaturas que Repousam em um Refúgio conseguem fazê-lo em apenas 3 dias.'),
    ('fb11f0d9-a2e0-4e88-a539-ae9fea165912', 'Riqueza', 'Você recebe 2d12 Peças de Olivina por dia. Gaste 1 Ponto de Heroísmo para rolar o dado novamente nessa sessão, se precisar fazer uma compra cara, por exemplo. Você deve ter uma fonte clara de renda - que, obviamente, sempre pode ser sabotada por alguém…')
ON CONFLICT DO NOTHING;

INSERT INTO
    BACKGROUNDS(id, name, physical_points, mental_points, advantage_id)
VALUES
    ('9cee85c6-d548-414b-bf3c-a5e9eb57a3eb', 'Acadêmico', -1, 4, 'a8315ecb-0d2a-4e23-b9c3-81ebfd3cfcd3'),
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
    ('8d4f5995-4d05-403a-9d41-eb206f325294', 'Viajante', 3, 0, '4d5b3422-43bd-4cf3-9a17-f0399a122915')
ON CONFLICT DO NOTHING;

INSERT INTO
    JOBS(id, name, description, main_attribute, physical_points, mental_points)
VALUES
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
    )
ON CONFLICT DO NOTHING;

INSERT INTO
    SKILLS(id, job_id, cost, range, name, energy_type, skill_type, description)
VALUES
    ('2995f1d5-ab5d-475d-9089-7cf9b3b35d74', 'eb98fa25-634a-4e93-8898-0c9dc7f01c01', 2, 5, 'Ataque Básico', 'PHYSICAL' , 'BASIC', NULL),
    ('796d27d5-0a97-487a-b96e-654275f51d96', 'eb98fa25-634a-4e93-8898-0c9dc7f01c01', 1, NULL, 'Gambiarra', 'MENTAL', 'COMBAT', 'Um Artífice pode apanhar objetos mundanos, aprimorá-los e lançá-los contra seus inimigos. A habilidade usada é Intelecto, causa dano em Energia Física e custa 1 de Energia Mental, independente do acerto. Caso acerte, o Artífice pode escolher um dos efeitos a seguir: Estilhaços, causa 1 ponto de Energia Física adicional. Gosmenta, impõe penalidade de 1 de Energia Física adicional na próxima ação do alvo. Ofuscante, impõe penalidade de 1 de Energia Mental adicional na próxima ação do alvo.'),
    ('f4a888da-e658-465c-80d3-1febe35f7075', 'eb98fa25-634a-4e93-8898-0c9dc7f01c01', 1, NULL, 'Entendimento Mecânico', 'MENTAL', 'INTERPRETATION', 'Concentrando-se brevemente, um Artífice entende os detalhes dos mais complexos mecanismos. Com uma Ação Complexa, ele pode enxergar através de paredes e objetos não-metálicos, observando engrenagens e detalhes de mecanismos em uma distância de até 5m. Gasta 1 ponto de Energia Mental e faz um teste de Intelecto. Caso um outro Artífice ou Ladino tenha fabricado o mecanismo, 1 ponto de Energia adicional será gasto, ou ou a análise falhará.'),
    ('91014dc0-a454-435a-8cf6-9eb01615dd1d', 'c0169b57-d4d2-49f2-8abe-b0f87c5e9fae', 2, 10, 'Ataque Básico', 'PHYSICAL', 'BASIC', NULL),
    ('c83ea26d-d63a-4377-a748-c0d3b1c78317', 'c0169b57-d4d2-49f2-8abe-b0f87c5e9fae', 1, NULL, 'Presença', 'MENTAL', 'COMBAT', 'A simples presença de um Artista é suficiente para inspirar aliados e abalar inimigos. Apenas em combate, ao gastar 1 Ponto de Energia Mental adicional, independente do acerto, o Artista faz um teste de Astúcia e, caso passe, escolhe um aliado e um inimigo para receber o efeito de sua Presença. Ambos deverão rolar dois dados em sua próxima rolagem e o aliado deve utilizar o melhor resultado, enquanto o inimigo, o pior. Em um aliado, este efeito se acumula com o bônus de Aptidão.'),
    ('b120ecb8-ee19-483e-9333-03c91f6787f5', 'c0169b57-d4d2-49f2-8abe-b0f87c5e9fae', 1, NULL, 'Charme', 'MENTAL', 'INTERPRETATION', 'Ao gastar 1 ponto de Energia Mental adicional, independente do acerto, o Artista realiza um teste de Astúcia apenas fora de combate. Em um sucesso, seu charme influencia a atmosfera ao seu redor e caso não houvesse nenhuma briga já acontecendo, nenhum ato violento pode ser iniciado enquanto não houver distrações que tirem o foco do Artista.'),
    ('50be3121-114f-462d-a5d1-061313c288fe', '9132517e-f3da-4321-905d-63a743d9344a', 3, 10, 'Ataque Básico', 'PHYSICAL', 'BASIC', NULL),
    ('8cc61538-a9c0-418e-a8e8-82e96518fda1', '9132517e-f3da-4321-905d-63a743d9344a', 1, NULL, 'Cobertura', 'PHYSICAL', 'COMBAT', 'Perito no combate à distância, encontrar cobertura para se proteger enquanto desfere seus ataques é uma habilidade nata de um Atirador. Ao realizar um teste de Celeridade com um custo adicional de 1 energia Física, independente do acerto, o Atirador consegue encontrar e se deslocar até uma cobertura que o protege até sua próxima ação, tornando-o invulnerável a ataques à distância.'),
    ('78c3d6ef-eab5-4237-8559-90027f1ae721', '9132517e-f3da-4321-905d-63a743d9344a', 1, NULL, 'Reconhecimento', 'PHYSICAL', 'INTERPRETATION', 'Ao gastar 1 ponto de Energia Física, independente do acerto, e realizar um Teste de Celeridade, o Atirador se concentra no território ao seu redor até onde sua vista alcança. Em áreas abertas, o alcance é de 1km. Em áreas fechadas, desde que não haja nada bloqueando sua visão, o reconhecimento é total. Em caso de sucesso, pode conseguir identificar e localizar uma das seguintes opções:\n\tUm alvo específico, desde que já o tenha visto ou tenha uma descrição aproximada.\n\tSeres vivos, podendo dizer número, sem distinguir entre hostis, aliados ou neutros.\n\tTerreno, bloqueios, atalhos e áreas perigosas.'),
    ('9e8e73e6-327d-4501-9463-357559487908', 'fd505408-014e-4ee7-96fb-ed577c0f3e93', 3, 1, 'Ataque Básico', 'PHYSICAL', 'BASIC', NULL),
    ('2984acb5-8bf3-4a14-a74e-3fa33ad9c2c7', 'fd505408-014e-4ee7-96fb-ed577c0f3e93', 0, NULL, 'Imorrível', 'MENTAL', 'COMBAT', 'Manter-se de pé até que todas as suas forças sejam exauridas é o maior mecanismo de defesa de um Bárbaro. Sempre que receber um dano que reduziria sua Energia Física a 0, o Bárbaro pode converter Energia Mental para manter-se de pé.'),
    ('48aa9a82-1669-47ff-9713-8a06f19bb250', 'fd505408-014e-4ee7-96fb-ed577c0f3e93', 1, NULL, 'Algo de Errado Não Está Certo', 'PHYSICAL', 'INTERPRETATION', 'Embora seja eficiente em se jogar, furioso, para o perigo, o Bárbaro é também capaz de perceber o perigo, quase como um sexto sentido. Com uma Ação, o Bárbaro realiza um teste de Tenacidade com custo adicional de 1 ponto de Energia Física, independente do acerto, para sentir todas as criaturas, humanoides ou não, que têm intenções imediatamente hostis contra si ou contra seu grupo num raio de 30m.'),
    ('42b491de-757a-46d7-a9ac-6c2e75a42744', '787c91b7-ab88-4e50-bf5d-30c09204e74d', 2, 5, 'Ataque Básico', 'PHYSICAL', 'BASIC', NULL),
    ('3d555ba0-d9e8-4d17-b2de-4af0fe5fd30b', '787c91b7-ab88-4e50-bf5d-30c09204e74d', 1, NULL, 'Provocar', 'MENTAL', 'COMBAT', 'Um Diplomata sabe tomar as atenções para si, mesmo que de forma negativa. Ao fazer um teste de Astúcia com custo adicional de 1 ponto de Energia Mental, independente do acerto, e passar, o Diplomata pode forçar de um a três inimigos a o atacarem em seus próximos turnos.'),
    ('c74acd60-1b76-4967-9229-0217b208382a', '787c91b7-ab88-4e50-bf5d-30c09204e74d', 1, NULL, 'Ludibriar', 'MENTAL', 'INTERPRETATION', 'Enquanto estiver argumentando, um Diplomata pode realizar um teste de Intelecto com um custo adicional de 1 Energia Mental, independente do acerto, para ganhar um argumento, convencer alguém a aceitar uma oferta que não coloque a pessoa em risco ou extrair alguma informação sem parecer invasivo.'),
    ('34db8277-efa8-4231-9e1d-5b6ae513cc42', '4c5e9d82-d2f9-452e-bbf1-585788816098', 3, 9, 'Ataque Básico', 'PHYSICAL', 'BASIC', NULL),
    ('2afe89c7-3185-43d1-8eef-6e2b68050a3f', '4c5e9d82-d2f9-452e-bbf1-585788816098', 0, NULL, 'Invocação Etérea', 'PHYSICAL', 'COMBAT', 'Usando de sua própria energia vital, o Druida pode criar invocações etéreas para auxiliá-lo no combate. O Druida pode chamar quantas invocações desejar, mas elas funcionam como uma extensão de seu corpo, ou seja, seus ataques contam como apenas um Ataque Básico. Elas não podem ser desfeitas a não ser que o Druida receba dano.'),
    ('a933bae4-0d12-4d86-a06c-3416eed76798', '4c5e9d82-d2f9-452e-bbf1-585788816098', 1, NULL, 'Druidismo', 'PHYSICAL', 'INTERPRETATION', 'Enquanto estiver na natureza, um Druida pode fazer um teste de Tenacidade com custo adicional de 1 ponto de Energia Física, independente de acerto, Druidismo para aprender sobre plantas e animais típicos da região e conseguir comida e água para descansar. Também pode falar com animais.'),
    ('f29f20e8-87c2-455f-a0cf-eba604a262a7', 'a719d6dd-ef3d-4438-b834-3c7a399df384', 3, 1, 'Ataque Básico', 'PHYSICAL' , 'BASIC', NULL),
    ('9c934970-eb08-4426-a8d9-77d6f7936faf', 'a719d6dd-ef3d-4438-b834-3c7a399df384', 1, NULL, 'Liderança', 'PHYSICAL' , 'COMBAT', 'Ao tomar a frente em um combate, o Guerreiro pode escolher gastar 1 Ponto adicional de Energia Física ao tomar qualquer ação. Se o fizer, cada aliado que agir de acordo com a liderança do Guerreiro, ganhará um bônus de 1 Ponto de Energia adicional de dano ou redução de dano em seu turno.'),
    ('b57cc278-8292-477d-b93d-43b0774e1188', 'a719d6dd-ef3d-4438-b834-3c7a399df384', 1, NULL, 'Competitivo', 'PHYSICAL' , 'INTERPRETATION', 'O Guerreiro é uma máquina de vencer, não só dentro das batalhas! Em situações de competição - uma partida de poker, uma corrida ou até uma queda de braço - em que uma aposta estiver em jogo, o Guerreiro pode gastar uma Ação para fazer um teste de Tenacidade gastando 1 ponto de Energia Física adicional, independente do acerto, e ganhar um bônus a ser combinado com o mestre.'),
    ('cdcfbc22-6165-4165-8fa8-a8629b7377a3', '339c9479-06c9-42d5-9920-535b34ace847', 3, 5, 'Ataque Básico', 'PHYSICAL' , 'BASIC', NULL),
    ('1f49c878-8e5d-45ac-afc8-5537b28936fc', '339c9479-06c9-42d5-9920-535b34ace847', 1, NULL, 'Furtividade', 'PHYSICAL' , 'COMBAT', 'O Ladino pode gastar 1 Ponto adicional de Energia Física e realizar um teste de Celeridade para tornar-se praticamente invisível no campo de batalha antes ou após realizar seu ataque. Até se mover ou atacar novamente, o Ladino não pode ser alvo de ataques ou habilidades direcionados.'),
    ('2df6ae6e-f1ac-4c60-958f-25802b034bc4', '339c9479-06c9-42d5-9920-535b34ace847', 1, NULL, 'Zé Ninguém', 'PHYSICAL' , 'INTERPRETATION', 'O Ladino tem um talento especial para ir e vir despercebido. Desde que tome um pouco mais de cuidado, o Ladino é capaz de gastar uma Ação para fazer um teste de Celeridade com 1 Ponto de Energia Física, independente do acerto, e adicional e tornar seu rosto quase completamente imemorável. Em um lugar relacionado a seu Antecedente, ele é capaz de estender esse efeito a até 4 pessoas. Caso cause algum tipo de alarde, o Ladino perde esse efeito imediatamente, atraindo a atenção de todos ao redor. Apenas tome cuidado: Bárbaros ainda podem perceber que Algo de Errado Não Está Certo!'),
    ('b46fc204-127c-45e1-9449-48cff75694c4', '4a48caa2-7f79-435f-ad42-92359248093c', 2, 10, 'Ataque Básico', 'PHYSICAL' , 'BASIC', NULL),
    ('1b803a9b-1e22-4ee3-99e8-2ea7dffa145f', '4a48caa2-7f79-435f-ad42-92359248093c', 1, NULL, 'Magia Elemental', 'MENTAL' , 'COMBAT', 'A manipulação dos quatro elementos é o início do aprendizado de um Mago. Ao gastar 1 Ponto adicional de Energia Mental, independente do acerto, o Mago faz um teste de Intelecto para conjurar uma magia com o efeito de um elemento à sua escolha. O dano é de 3 Pontos de Energia Física e o elemento causará um efeito adicional a ser combinado com o mestre.'),
    ('f7c83af3-206a-4ea2-80f1-5835ad543634', '4a48caa2-7f79-435f-ad42-92359248093c', 1, NULL, 'Familiar', 'MENTAL' , 'INTERPRETATION', 'Um familiar pode servir como canalizador de magias e se comunicar telepaticamente com você. Ele pode transmitir mensagens telepáticas para outras pessoas de até 10 palavras desde que você possa vê-lo, ele possa ver o alvo, e você gaste 1 ponto de Energia Mental.'),
    ('1e6e1215-b3f5-438a-9688-8dbac3471cfb', '839eef4c-294c-4ac1-b1e2-317229ddb093', 2, 1, 'Ataque Básico', 'PHYSICAL' , 'BASIC', NULL),
    ('4f3cc16a-7178-4463-bc24-90a4ba98ee5f', '839eef4c-294c-4ac1-b1e2-317229ddb093', 1, NULL, 'Distorcer Espaço', 'MENTAL' , 'COMBAT', 'O Nexus pode gastar 1 Ponto de Energia Mental adicional, independente do acerto, para realizar um teste de Intelecto e distorcer o espaço ao seu redor. Em caso de sucesso, todas as criaturas dentro de uma circunferência com 5m de raio, ficam aprisionadas e podem mover-se apenas em direção ao centro da área, que é o próprio Nexus.'),
    ('bcc3401a-ab60-4f9e-9d59-6cb78a71d3fc', '839eef4c-294c-4ac1-b1e2-317229ddb093', 1, NULL, 'Reprogramar', 'MENTAL' , 'INTERPRETATION', 'O Nexus pode gastar 1 ponto de Energia Mental para trocar uma de suas Aptidões até o fim da cena. Uma Aptidão trocada não pode ser reprogramada novamente até o efeito acabar.'),
    ('e272a158-348b-4e3a-90d9-22fe02ed3582', '47117c6c-27a4-42db-88f2-a0972a93eadf', 2, 1, 'Ataque Básico', 'PHYSICAL' , 'BASIC', NULL),
    ('f8163993-6a30-44bb-968e-b64b11bb80a6', '47117c6c-27a4-42db-88f2-a0972a93eadf', 1, NULL, 'Súplica', 'MENTAL' , 'COMBAT', 'Um Sacerdote pode gastar 1 ponto de Energia Mental para curar um alvo em 2 pontos de qualquer Energia.'),
    ('c01d0689-7fd6-4fda-b6a5-41ca1b5afb70', '47117c6c-27a4-42db-88f2-a0972a93eadf', 1, NULL, 'Indulgência', 'PHYSICAL' , 'INTERPRETATION', 'Ao fazer um teste de Astúcia com um custo adicional de 1 ponto de Energia Mental, independente do acerto, um Sacerdote pode identificar até três emoções que um alvo possa estar sentindo no momento e alterar uma.'),
    ('b82c0233-06c8-43e1-bb99-d328112ce1e3', '3d14cb08-3391-418e-bd74-37d7737be28b', 3, 1, 'Ataque Básico', 'PHYSICAL' , 'BASIC', NULL),
    ('939f3d16-d6c1-4471-b394-f81752824a97', '3d14cb08-3391-418e-bd74-37d7737be28b', 1, NULL, 'Proteção', 'PHYSICAL' , 'COMBAT', 'Quando um aliado recebe um ataque, o Sentinela pode, fora de seu turno, gastar 1 Ponto adicional de Energia Física, independente do acerto, para realizar um teste de Celeridade. Em caso de sucesso, o Sentinela se move rapidamente até o aliado e absorve o dano do ataque para si.'),
    ('2d6983f8-6590-40c9-a349-ca76f2be2f26', '3d14cb08-3391-418e-bd74-37d7737be28b', 1, NULL, 'Vínculo', 'PHYSICAL' , 'INTERPRETATION', 'O Sentinela pode criar um vínculo com uma criatura voluntária, gastando 1 Ponto de Energia Física para fazê-lo. Sempre que seu vínculo gastar Energia Física, desde que fora de combate, o Sentinela pode escolher gastar sua própria no lugar. Além disso, o Sentinela sempre sabe como encontrar seu vínculo ou pressentir quando ele está em perigo, desde que estejam a no máximo 1km de distância um do outro, Toda vez que trocar o alvo do vínculo, um novo Ponto é gasto.')
ON CONFLICT DO NOTHING;

INSERT INTO
    APTITUDES(id, name, description)
VALUES
    ('fc7f6815-53c6-4b17-b780-391735a1475a', 'Acobertamento', 'Essa aptidão representa a capacidade de ocultar ações ou objetos e, quando necessário, desviar a atenção de terceiros para alcançar esse objetivo.\nUso em interpretação: Desviar a atenção do padeiro para que ele não perceba seu amigo roubando aquele pão-doce; procurar a melhor janela para invadir aquele apartamento sem ser visto.\nUso em combate: Se aproximar furtivamente de um inimigo desavisado; ter o máximo de proteção contra disparos inimigos atrás de um anteparo; distrair um inimigo para atacá-lo num golpe de surpresa.'),
    ('3f855b9b-d352-4c23-9cf2-e5e4d8657916', 'Arguição', 'Denota a habilidade questionar, argumentar e pesquisar opiniões ou conhecimentos.\nUso em interpretação: Acessar seus contatos do submundo do crime para descobrir onde vai ser o próximo embate do Clube da Luta; fornecer argumentos para que o policial deixe você ir dessa vez - a placa com o limite de velocidade estava quase invisível, com a grama crescendo ao redor...\nUso em combate: Interrogar um inimigo com perguntas rápidas e incisivas, fazendo ele dar com a língua nos dentes por não ter tempo de pensar nas respostas; encerrar um combate provando para o inimigo que não há necessidade de lutar.'),
    ('c8f5b55a-c2fa-4b32-95c1-d44ee9236bea', 'Amestração', 'Representa o talento para lidar com criaturas estranhas, selvagens ou alienígenas, o que pode incluir domesticá-las ou se comunicar com elas.\nUso em interpretação: Comunicar para o gorilefante, por meio de gestos, que você e seu grupo vieram em paz; pilotar duas carroças, com três cavalos cada, ao mesmo tempo, fazendo uma abertura de pernas entre elas, a 50km/h.\nUso em combate: Desviar o curso da manada de gorilefantes, quando eles se irritam com seus companheiros e decidem pisoteá-lo; convencer o familiar do mago maligno de que o opressor, na verdade, é o mago.'),
    ('48c00298-5e21-4e56-b5c2-5db4d4d37d6e', 'Atenção', 'Expressa um estado de constante vigilância do mundo ao seu redor, bem como a capacidade de se concentrar em um objeto/tópico específico\nUso em interpretação: Fechar os olhos para escutar qual engrenagem daquela buginganga está causando seu mal funcionamento; encontrar uma pista na cena do crime.\nUso em combate: Procurar as falhas na armadura de um oponente, enquanto ele dispara contra você; ouvir seu companheiro sendo nocauteado no andar de baixo, enquanto você luta contra os seus oponentes.'),
    ('86d9c9b0-e698-477d-8b94-aa2a2f43e876', 'Avanço', 'Percorrer um trajeto entre um ponto A e um ponto B, ganhar território rapidamente entre as ruas de uma cidade, ou até mesmo escalar uma montanha, são atividades facilitadas pela Aptidão Avanço.\nUso em interpretação: Correr em uma perseguição; nadar para fugir de um jacaré dos esgotos; escalar um prédio para salvar um gatinho.\nUso em combate: Desferir um golpe com uma investida após uma corrida para ganhar impulso e força. Realizar um ataque furtivo se já estiver escondido e quiser chegar no alvo rapidamente, sem ser percebido.'),
    ('2ba3a1fd-aad1-4e3a-93fd-ff4b3749d616', 'Ciências', 'Aptidão em Ciências não é o mesmo que conhecimento científico. Embora uma pessoa com essa aptidão tenha facilidade, curiosidade e até mesmo um pouco de pensamento crítico científico nato, ela não necessariamente teve um estudo formal para tanto. É, de fato, uma Aptidão, uma familiaridade natural e um olhar detalhista para com o funcionamento das coisas.\nUso em interpretação: Testar plantas diversas em composições químicas que podem ou não gerar poções;  Inferir o funcionamento de um mecanismo desconhecido; Tentar se encontrar pela posição das estrelas.\nUso em combate: Atacar um autômato em partes que lhe atrapalharão o funcionamento; desvendar pontos fortes e fracos de uma criatura; calcular potências diferentes de tipo de danos diferentes.'),
    ('4fdb404b-f1dc-4ce3-a5fd-6be7eb56df15', 'Demolição', 'Tem gente que só quer ver o mundo queimar... Talvez você seja uma dessas pessoas! A Aptidão Demolição envolve a capacidade de destruir objetos ou identificar fraquezas neles com uma maior desenvoltura.\nUso em Interpretação: Derrubar uma porta com um chute; calcular o local ideal para armar um explosivo, de forma que ele derrube apenas um prédio, sem machucar os inocentes ao redor.\nUso em Combate: Disparar no pneu de um automóvel para, talvez, fazer com que ele capote; descobrir uma forma de superar a armadura quase indestrutível de um modelo novo de autômato.'),
    ('30a19cf8-bb64-4dd2-b3f7-29b9364eb0e9', 'Empatia', 'Olhos que enxergam as emoções além da superfície... Esses são os olhos de alguém com Empatia. Perceber intenções, detectar mentiras, compreender o propósito de obras de arte, julgar motivações - todas essas competências são capacidades da Aptidão Empatia.\nUso em Interpretação: Olhar para uma plateia de aristocratas e saber que tipo de discurso eles estão esperando ouvir; perceber se esse guarda em específico é propenso ou não a aceitar propina.\nUso em Combate: Ter certeza de para que lado o criminoso parece querer fugir; descobrir, pelos urros no combate, quem é o inimigo por trás daquela maldita máscara!'),
    ('0c6e1449-d218-4ffc-b0bc-0fcfea87d891', 'Fôlego', 'Se Avanço é sobre velocidade, Fôlego é sobre capacidade! Correr uma maratona, mergulhar por mais de um minuto, falar por horas a fio... Fôlego envolve não só a capacidade dos seus pulmões, mas a fibra de se manter em esforço!\nUso em Interpretação: Pregar um grande sermão para uma plateia de 100, sem microfone; fingir-se de morto por 3 horas, até o legista ir embora.\nUso em Combate: Observar os gângsters dos telhados, pulando de prédio em prédio, por horas a fio; Degladiar-se com o polvo, debaixo da água, por 3 minutos inteiros!'),
    ('eaf00e61-c5f2-46e3-b3e9-cfd91b5ae582', 'Fortitude', 'FORÇA INCOMPARÁVEL! PEITO DE AÇO! HEAVY METAL! Fortitude é sobre isso! Se você pretende arremessar postes de luz, barris ou Iuventis indefesos, Fortitude é pra você! Essa Aptidão representa a capacidade de concentrar todo o seu esforço de uma vez só, realizando feitos de grande força física ou mental!\nUso em Interpretação: Suportar horríveis dores enquanto os vilões torturam você, buscando informações; carregar seus três amigos bêbados nos ombros, enquanto os donos do bar jogam garrafas em vocês.\nUso em Combate: Agarrar o bugurso adversário, impedindo-o de sair do lugar; evitar que o teto da sala-armadilha esmague seus amigos, segurando-o com as mãos enquanto eles pensam no que fazer.'),
    ('da13f789-64f8-4728-8366-e8725c16a590', 'Impavidez', 'Se Fortitude é sobre peito de aço... Impavidez é sobre nervos de aço! Essa Aptidão envolve ações que representem um personagem que não pestaneja, responde prontamente a perigos e ameaças sem pensar duas vezes, com frieza e calculismo - ou, talvez, só muito pouco amor à vida! Isso vai de dar respostas rápidas e incisivas até sacar suas armas antes de todos os outros caubóis.\nUso em Interpretação: Mentir com calma e tranquilidade diante do juiz, em rede nacional; sempre sair por cima em uma discussão ou debate.\nUso em Combate: Iniciar o combate com uma vantagem, dando um chute na mesa para surpreender seus oponentes; disparar contra seus inimigos enquanto dirige um automóvel à beira de um penhasco.'),
    ('24f5e67e-ca4a-4fef-8325-85c6f8f81e55', 'Interpretação', 'Interpretação é a capacidade de conjugar diferentes informações, conhecimentos ou operações, criando novos conhecimentos a partir disso. É o que arqueólogos, poetas, detetives e inventores têm em comum!\nUso em Interpretação: Entender a relação entre os hieroglifos antigos e misteriosos na parede do templo; captar o significado oculto na mensagem criptografada do espião.\nUso em Combate: Perceber o padrão sequencial de ataques que aquele autômato repete o tempo todo; observar o terreno de uma batalha e definir a melhor posição estratégica para o seu grupo.'),
    ('4d7aec1a-b884-4d6f-adfa-85283c1d1df7', 'Misticismo', 'Essa aptidão relaciona-se não ao conhecimento do oculto, mas também a uma certa disposição a compreender ou manipular objetos e criaturas místicas; você não precisa ser um grande estudioso para isso, entretanto - nunca ouviu falar daquele bárbaro médium na Rua 3?\nUso em Interpretação: Avaliar a força de um feitiço, ou a melhor maneira de desfazê-lo; deduzir o efeito de uma poção mágica provando apenas uma gota dela.\nUso em Combate: Adaptar as palavras mágicas de um feitiço, para conjurá-lo mesmo amordaçado; utilizar com sucesso o cajado que você acabou de roubar do necromante.'),
    ('f4bfa384-93d4-415e-bd49-52d027e30335', 'Pilotagem', 'Algumas pessoas simplesmente nascem com o dom de pilotar, não é? Sejam automóveis, navios mercantes ou a bicicleta enferrujada do seu tio, você sempre consegue deduzir pra que serve essa alavanca ou aquele botão.\nUso em Interpretação: Pousar um zepelin no meio de uma tempestade de raios cósmicos; encontrar o furo por onde o óleo do automóvel está vazando.\nUso em Combate: Fugir dos bandidos no automóvel usando a bicicleta enferrujada do seu tio; acertar um ponto fraco no robô que o artífice maligno está pilotando.'),
    ('a51dbfc2-02a7-471f-8f80-29ace8158e91', 'Prestidigitação', 'A famosa "mão leve". Prestidigitação envolve habilidades manuais ágeis e precisas, bem como a capacidade acompanhar as "mãos leves" de outros espertinhos...\nUso em Interpretação: Intimidar os mineiros com um malabarismo de dinamites; subtrair o relógio de bolso daquele cavalheiro distraído...\nUso em Combate: Arremessar uma poção para o seu companheiro por entre as pernas do adversário; confundir um adversário com movimentos rápidos de sua alabarda.'),
    ('217b74f9-c95f-4185-a17c-8f9b546c41e7', 'Resistência', 'Essa aptidão envolve a capacidade de aguentar ofensas externas ao seu corpo, intempéries e grandes quantidades de dano. São os famosos peitos de aço!\nUso em Interpretação: Tomar o vinho envenenado e pedir "mais um copo, por favor"; agarrar a caldeira do barco a vapor e não deixá-la se desmantelar - nem tá tão quente assim!\nUso em Combate: Pular na frente do disparo, para impedir que ele atinja seu amigo; ignorar a doença que o vilão tentou inocular em você.'),
    ('ca749272-1570-4136-955e-aa8d22d8870c', 'Sobrevivência', 'Identificar os becos mais perigosos da cidade, conhecer as partes mais inóspitas do pântano... Sobrevivência é a aptidão daqueles que já tiveram muitas adversidades nessa vida e sabem exatamente em que calo cada sapato dói. É a habilidade de reconher e contornar perigos relacionados ao ambiente, seja ele qual for.\nUso em Interpretação: Dar uma olhada na cidade nova e já identificar o símbolo da principal gangue local; observar as pegadas na lama e associá-las ao urso-coruja que as fez.\nUso em Combate: Saber quanto tempo seu grupo tem antes que o prédio desabe pelo incêndio; reconhecer uma erva nas redondezas para salvar seu amigo envenenado.'),
    ('bdf2a8ec-5018-4307-9ed7-923b663074f5', 'Vitalidade', 'Essa aptidão se parece com Resistência, mas se relaciona a ofensas internas ao seu corpo. Sangramentos volumosos, sono, fome e sede - a vida dentro de você supera tudo isso! Essa é a única aptidão que pode ser utilizada em Testes Contra a Morte.\nUso em Interpretação: Ignorar o sono durante os 3 dias de pesquisa constante na biblioteca local; suportar a colocação de um implante mecânico experimental em seu corpo.\nUso em Combate: Sobreviver aos Testes da Morte.')
ON CONFLICT DO NOTHING;

INSERT INTO
    APTITUDE_JOB(job_id, aptitude_id)
VALUES
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
    ('3d14cb08-3391-418e-bd74-37d7737be28b', 'ca749272-1570-4136-955e-aa8d22d8870c')
ON CONFLICT DO NOTHING;