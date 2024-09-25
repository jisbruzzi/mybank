CREATE TABLE IF NOT EXISTS "transactions"
(
    id uuid default random_uuid() primary key,
    slogan varchar(255),
    reference varchar(255),
    created_at timestamp with time zone,
    amount int
);