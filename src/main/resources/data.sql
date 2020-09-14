insert into categoria (id, nome, descricao) values
   (1, 'INFORMATICA','produtos de informática'),
   (2, 'ESCRITORIO','cadeiras, mesas, e materiais de escritório'),
   (3, 'LIVRARIA','livros, revistas, quadrinhos');
   
insert into funcionario (id, cpf, nome) values
   (1, '62730162089', 'José da silva'),
   (2, '17602085078', 'Maria José'),
   (3, '99124915050', 'Joaquim Manoel');
   
insert into cliente(id, cpf, nome, email, data_nascimento, login) values
   (1, '65782885033', 'Jose das Coves',    'jose.coves@email.com',     '1992-02-01', 'josecoves'),
   (2, '69191608031', 'Maria das Coves',   'maria.coves@email.com',    '1993-04-06', 'maria'),
   (3, '64294714034', 'Claudio Jose',      'claudio.jose@email.com',   '1991-08-13', 'claudio'),
   (4, '64294714034', 'Daniele Aparecida', 'dani.aparecida@email.com', '2002-03-21', 'dani'),
   (5, '56943214055', 'Cleberson Carlos',  'kleb1990@email.com',       '2001-07-11', 'clebinho');
   
insert into endereco (id, id_cliente, logradouro, numero, complemento, bairro, cidade, cep, uf) values
   (1, 1, 'Rua dos Bobos', '0',  '',        'Castanheira', 'Metropolis',      '23451234', 'SP'),
   (2, 2, 'Rua dos Bobos', '0',  '',        'Castanheira', 'Metropolis',      '23451234', 'SP'),
   (3, 3, 'Estrada Torta', '45', 'Bloco 2', 'Castanheira', 'Aroeira',         '25839248', 'MG'),
   (4, 4, 'Estrada Reta',  '19', 'Casa 2',  'Sinistro',    'Tangamandapio',   '45627894', 'MG'),
   (5, 5, 'Rua das Ruas',  '99', '',        'Distrito 1',  'Cidade-Alerta',   '52742264', 'AL');
   
insert into foto (id, nome, mimetype, data) values
   (1, 'cadeira.jpg',            'image/jpeg', FILE_READ('classpath:fotos/cadeira.jpg')),
   (2, 'escrivaninha.jpg',       'image/jpeg', FILE_READ('classpath:fotos/escrivaninha.jpg')),
   (3, 'doinferno.jpg',          'image/jpeg', FILE_READ('classpath:fotos/doinferno.jpg')),
   (4, 'useacabeca.jpg',         'image/jpeg', FILE_READ('classpath:fotos/useacabeca.jpg')),
   (5, 'tablet.jpg',             'image/jpeg', FILE_READ('classpath:fotos/tablet.jpg')),
   (6, 'mouse.jpg',              'image/jpeg', FILE_READ('classpath:fotos/mouse.jpg')),
   (7, 'fitacrepe.jpg',          'image/jpeg', FILE_READ('classpath:fotos/fitacrepe.jpg')),
   (8, 'mousepad.jpg',           'image/jpeg', FILE_READ('classpath:fotos/mousepad.jpg')),
   (9, 'batalhaapocalipse.jpg',  'image/jpeg', FILE_READ('classpath:fotos/batalhaapocalipse.jpg'));
   
   
insert into produto (id, nome, descricao, qtd_estoque, valor, id_categoria, id_funcionario, data_fabricacao, id_foto) values
   (1, 'Cadeira bx9',             'cadeira ergonomica confortavel',   3 ,  850.00, 2, 3, '2019-10-01', 1),
   (2, 'Escrivaniha 1000',        'escrivainha para computador',      4 , 1850.00, 2, 3, '2019-08-11', 2),
   (3, 'Do Inferno',              'Quadrinho do Alan More',           2 ,  150.00, 3, 2, '2017-12-21', 3),
   (4, 'Use a Cabeca Java',       'Livro principal para Java',       10 ,  75.00,  3, 2, '2016-04-22', 4),
   (5, 'Tablet Samsung',          'Tablet 10 polegadas',              2 , 3850.00, 1, 1, '2018-02-04', 5),
   (6, 'Mouse Logitec',           'Mouse com 3 botoes e uma rodinha', 2 ,   50.00, 1, 3, '2019-07-13', 6),
   (7, 'Fita Crepe',              'Fita crepe simples',              33 ,    1.30, 2, 1, '2012-12-15', 7),
   (8, 'Mousepad',                'Mousepad estilizado com foto',    13 ,   25.00, 1, 1, '2020-01-04', 8),
   (9, 'A Batalha do Apocalipse', 'Melhor livro que voce deve ler',  42 ,   55.00, 3, 2, '2009-01-21', 9);
   
insert into pedido (id, id_cliente, data_pedido, status) values 
  ( 1, 1, '2020-08-30 20:10:10', 4),
  ( 2, 1, '2020-09-10 12:13:12', 2),
  ( 3, 2, '2020-07-11 12:34:21', 2), 
  ( 4, 2, '2020-08-15 09:10:36', 1),
  ( 5, 3, '2020-09-13 19:12:26', 1);
  
insert into pedido_item  (id_pedido, id_produto, quantidade, valor) values
  ( 1, 1, 1, 830.00),
  ( 1, 2, 1, 1570.00),
  ( 1, 7, 1, 1.20),
  ( 2, 1, 1, 835.00), 
  ( 2, 2, 1, 1770.00),
  ( 3, 8, 3, 22.00), 
  ( 3, 6, 3, 45.50),
  ( 4, 3, 1, 127.99),
  ( 4, 9, 1, 60.00),
  ( 5, 4, 2, 65.90),
  ( 5, 5, 1, 3850.00);
  



