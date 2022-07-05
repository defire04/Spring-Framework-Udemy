select * from person;

-- Просто дата без точного времени
ALTER TABLE Person ADD COLUMN date_of_birth DATE;

-- Точное время. Значение timestamp сохраняются в секундах до или после получночи 1 января 2000 г.
ALTER TABLE Person ADD COLUMN created_at TIMESTAMP;

ALTER TABLE Person ADD COLUMN mood int; -- Потому что выбрали тип  @Enumerated(EnumType.ORDINAL) в моделе
ALTER TABLE Person DROP COLUMN mood;
ALTER TABLE Person ADD COLUMN mood varchar; -- Потому что выбрали тип  @Enumerated(EnumType.STRING) в моделе