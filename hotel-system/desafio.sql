-- Desafio SQL --

-- 1. Quantos clientes na base? --
SELECT COUNT(*) AS total_clientes FROM Customers;

-- 2. Quantos quartos cadastrados? --
SELECT COUNT(*) AS total_quartos FROM Rooms;

-- 3. Quantas reservas em aberto o hotel possui no momento? --
SELECT COUNT(*) AS reservas_em_aberto FROM Reservations WHERE status = 'SCHEDULED';

-- 4. Quantos quartos temos vagos no momento? --
SELECT COUNT(*) AS quartos_vagos FROM Rooms WHERE id NOT IN (SELECT room_id FROM Reservations WHERE status = 'IN_USE');

-- 5. Quantos quartos temos ocupados no momento? --
SELECT COUNT(*) AS quartos_ocupados FROM Reservations WHERE status = 'IN_USE';

-- 6. Quantas reservas futuras o hotel possui? --
SELECT COUNT(*) AS reservas_futuras FROM Reservations WHERE checkin > CURDATE();

-- 7. Qual o quarto mais caro do hotel? --
SELECT id, room_number, type, price FROM Rooms ORDER BY price DESC LIMIT 1;

-- 8. Qual o quarto com maior histórico de cancelamentos? --
SELECT room_id, COUNT(*) AS cancelamentos 
FROM Reservations 
WHERE status = 'CANCELED' 
GROUP BY room_id 
ORDER BY cancelamentos DESC 
LIMIT 1;

-- 9. Liste todos os quartos e a quantidade de clientes que já ocuparam cada um --
SELECT r.id, r.room_number, COUNT(DISTINCT res.customer_id) AS clientes_ocuparam 
FROM Rooms r 
LEFT JOIN Reservations res ON r.id = res.room_id 
GROUP BY r.id, r.room_number;

-- 10. Quais são os 3 quartos que possuem um histórico maior de ocupações? --
SELECT room_id, COUNT(*) AS ocupacoes 
FROM Reservations 
WHERE status IN ('IN_USE', 'FINISHED') 
GROUP BY room_id 
ORDER BY ocupacoes DESC 
LIMIT 3;

-- 11. No próximo mês, o hotel fará uma promoção para os seus 10 clientes que possuírem maior histórico de reservas e você foi acionado pelo seu time para extrair esta informação do banco de dados. Quem são os 10 clientes? --
SELECT c.id, c.name, COUNT(*) AS total_reservas 
FROM Customers c 
JOIN Reservations res ON c.id = res.customer_id 
GROUP BY c.id, c.name 
ORDER BY total_reservas DESC 
LIMIT 10;