# Runbook
## Build
    mvn -T 1C clean package
## Run Demo
    docker-compose up demo
## Open Demo Page in your favorite web-browser
    http://localhost:8081/
## Start HBase shell
    docker exec -i -t wsmt-hbase hbase shell
## Scan Database
    scan 'profiles', {COLUMNS => ['HISTORY', 'HEADER'], VERSIONS => 10}
## Run Reporter
    docker-compose up reporter
## Stop Environment
    docker-compose stop
## Remove Data
    docker-compose down
## Links
* [Remote Debug of Pixel Tracker](pixel-tracker-remote-debug.md)