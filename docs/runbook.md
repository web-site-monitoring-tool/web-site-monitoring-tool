# Runbook
## Build
    mvn -T 1C -P demo clean package -DskipTests
## Run
    docker-compose up
## Open Demo Page in your favorite web-browser
    http://localhost:8081/
## Start HBase shell
    docker exec -i -t wsmt-hbase hbase shell
## Scan Database
    scan 'profiles', {COLUMNS => ['HISTORY', 'HEADER'], VERSIONS => 10}