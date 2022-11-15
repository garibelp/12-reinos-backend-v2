create table lineages (
    id uuid primary key,
    description text not null,
    language varchar(50) not null,
    maturity varchar(100) not null,
    name varchar(20) not null,
    negative_trait_description text not null,
    negative_trait_name varchar(20) not null,
    positive_trait_description text not null,
    positive_trait_name varchar(20) not null,
    size varchar(50) not null
);

insert into
    lineages (
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
values
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
    );