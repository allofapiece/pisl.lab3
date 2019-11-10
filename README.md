For connection to database, create "db.properties" file in resources directory of the project. Insert in it the following connection properties:

db.driver=DRIVER (e.g. com.mysql.jdbc.Driver)
db.url=jdbc:YOUR_DBMS://YOUR_HOST:YOUR_PORT/YOUR_DATABASE_NAME
db.user=YOUR_LOGIN
db.password=YOUR_PASSWORD
db.poolsize=CONNECTION_POOL_SIZE

Dump file of data base in "db" folder.