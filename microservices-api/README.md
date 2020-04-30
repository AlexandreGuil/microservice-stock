## Creer un topic pour le suivit des délétion de stock

 > commande kafka

```bash

$ kafka-topics --create --topic  stock-delete-v1 --zookeeper zookeeper:2181 --config cleanup.policy=delete  --config  delete.retention.ms=604800000 --config  retention.ms=604800000 --partitions 3 --replication-factor 1

````