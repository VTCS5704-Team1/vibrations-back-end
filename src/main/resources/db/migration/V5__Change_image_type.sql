-- Assuming your table is named 'your_table' and the column is 'your_column'
-- First, make sure to back up your data before making such changes

-- Step 1: Create a new column with the desired data type
ALTER TABLE profile_images
    ADD COLUMN imagedata1 bigint;

-- Step 2: Update the new column with the values from the old column


-- Step 3: Drop the old column
ALTER TABLE profile_images
DROP COLUMN imagedata;

-- Step 4: Rename the new column to the original column name (if needed)
ALTER TABLE profile_images
    RENAME COLUMN imagedata1 TO imagedata;
