-- Add NOT NULL constraint to first_name and last_name
-- First, update any existing NULL values to empty string (if any)
UPDATE users SET first_name = '' WHERE first_name IS NULL;
UPDATE users SET last_name = '' WHERE last_name IS NULL;

-- Then add NOT NULL constraint
ALTER TABLE users ALTER COLUMN first_name SET NOT NULL;
ALTER TABLE users ALTER COLUMN last_name SET NOT NULL;
