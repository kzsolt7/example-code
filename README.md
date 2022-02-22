## Docker command mongodb:
```
docker run -d --name some-mongo -p 27017:27017 -e MONGO_INITDB_ROOT_USERNAME=mongoadmin -e MONGO_INITDB_ROOT_PASSWORD=secret mongo
```
## Create Admin user:
- http://localhost:7070/api/user/create-admin-user
### Default user:
- username: user
- password: superuser

### start:
- cd ui
- npm i
- npm run serve
- start backend spring app