--liquibase formatted sql
--changeSet runAlways:true splitStatements:false

DROP FUNCTION IF EXISTS search_products(params json);
CREATE FUNCTION search_products(params json)
	RETURNS TABLE (
		"id" uuid,
		available_amount integer,
		description varchar(255),
		price numeric,
		title varchar(255),
		store_id uuid
	)
	LANGUAGE plpgsql AS $$

DECLARE
    f_store uuid;
	f_title varchar(255);
	f_price_from numeric;
	f_price_to numeric;
	f_is_available boolean;
BEGIN

	IF params IS NULL THEN
		RETURN QUERY
		    SELECT * FROM products;
	END IF;

	f_store := JSON_EXTRACT_PATH_TEXT(params, 'store')::uuid;
	f_title := COALESCE(JSON_EXTRACT_PATH_TEXT(params, 'title')::varchar(255), '');
	f_price_from := COALESCE(JSON_EXTRACT_PATH_TEXT(params, 'priceFrom')::numeric, 0);
	f_price_to := COALESCE(JSON_EXTRACT_PATH_TEXT(params, 'priceTo')::numeric, 0);
	f_is_available := COALESCE(JSON_EXTRACT_PATH_TEXT(params, 'isAvailable')::boolean, FALSE);

	RETURN QUERY
        SELECT * FROM products AS p
        WHERE (
            (LENGTH(LOWER(f_title)) > 0 AND POSITION(LOWER(f_title) in LOWER(p.title)) > 0)
            OR
            (LENGTH(f_title) = 0 AND p.title = p.title)
        ) AND (
            (f_price_from > 0 AND p.price >= f_price_from)
            OR
            (f_price_from <= 0 AND p.price = p.price)
        ) AND (
            (f_price_to > 0 AND p.price <= f_price_to)
            OR
            (f_price_to <= 0 AND p.price = p.price)
        ) AND (
            (f_is_available IS TRUE AND p.available_amount > 0)
            OR
            (f_is_available IS FALSE AND p.available_amount = p.available_amount)
        ) AND (
            (f_store IS NOT NULL AND p.store_id = f_store)
            OR
            (f_store IS NULL AND p.store_id = p.store_id)
        );
END;
$$;

