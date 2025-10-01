-- data.sql
-- Inserir usuários
INSERT INTO users (EMAIL, NAME, PASSWORD, ROLE) VALUES 
('admin@email.com', 'Administrador', 'admin123', 'ADMIN'),
('user@email.com', 'Usuário Comum', 'user123', 'USER'),
('moto@email.com', 'Motoboy Teste', 'moto123', 'MOTORCYCLE'),
('joao@email.com', 'João Motoboy', 'moto456', 'MOTORCYCLE');

-- Inserir motocicletas
INSERT INTO TBL_MOTORCYCLE (MODEL, PLATE, COLOR, USER_ID) VALUES 
('Honda CG 160', 'ABC1D23', 'Vermelha', 3),
('Yamaha Factor 150', 'XYZ9K87', 'Preta', 3),
('Honda Biz 125', 'DEF4G56', 'Azul', 4);

-- Inserir endereços
INSERT INTO TBL_ADRESS (STREET, NUMBER, NEIGHBORHOOD, CITY, STATE, ZIP_CODE) VALUES 
('Rua Principal', '123', 'Centro', 'São Paulo', 'SP', '01234-567'),
('Avenida Secundária', '456', 'Jardins', 'São Paulo', 'SP', '04567-890');

-- Inserir áreas
INSERT INTO TBL_AREA (NAME, DESCRIPTION) VALUES 
('Zona Norte', 'Área de cobertura da Zona Norte'),
('Centro Expandido', 'Área central da cidade'),
('Zona Sul', 'Área de cobertura da Zona Sul');

-- Inserir patologias
INSERT INTO TBL_PATHO (NAME, DESCRIPTION) VALUES 
('Entrega Urgente', 'Entregas que requerem tempo mínimo'),
('Carga Frágil', 'Itens que necessitam cuidado especial'),
('Documentos', 'Entrega de documentos importantes');