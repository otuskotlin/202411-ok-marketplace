CREATE TABLE IF NOT EXISTS marketplace_ads (
    id text PRIMARY KEY,
    title text,
    description text,
    owner_id text,
    visibility text,
    product_id text,
    deal_side text,
    lock text
);

--- При использовании SASI индекса типа StandardAnalyzer происходит токенизация текста по пробелам.
--- Оператор LIKE в этом случае должен быть НЕ LIKE '%<токен>%' а LIKE '<токен>%'

CREATE CUSTOM INDEX IF NOT EXISTS ON marketplace_ads (title)
  USING 'org.apache.cassandra.index.sasi.SASIIndex'
  WITH OPTIONS = {
    'analyzer_class': 'org.apache.cassandra.index.sasi.analyzer.StandardAnalyzer',
    'tokenization_enable_stemming': 'true',
    'analyzed': 'true',
    'tokenization_normalize_lowercase': 'true',
    'tokenization_locale': 'en'
  };
