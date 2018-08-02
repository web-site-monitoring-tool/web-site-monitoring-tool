# Runbook
## Start
### Build
    mvn -T 1C clean package
### Run Demo and Admin modules
    docker-compose up demo admin
### Open Demo Page in your favorite web-browser
    http://localhost:8081/
### Open Admin Page in your favorite web-browser
    http://localhost:8083/
We should click "Refresh" button for updating reports

## Read HBase
### Start HBase shell
    docker exec -i -t wsmt-hbase hbase shell
### Scan Database
    scan 'profiles', {COLUMNS => ['HISTORY', 'HEADER'], VERSIONS => 10}

## Stop
### Stop Environment
    docker-compose stop
### Remove Data
    docker-compose down

## Links
* [Remote Debug](remote-debug.md)