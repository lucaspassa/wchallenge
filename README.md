# WChallenge

**Api Externa:** https://jsonplaceholder.typicode.com

## Api WChallenge
### Todos los usuarios
```
GET /users
```
### Los albums por usuario
```
GET /users/{ID}/albums
```
### Las fotos por usuario
```
GET /users/{ID}/photos
```
### Todas las fotos
```
GET /photos
```
### Todos los albums
```
GET /albums
```
### Todos los comentarios
opcional filtro por contenido del atributo name
```
GET /comments
GET /comments?name={text}
```

## Gestión de permisos

Permisos para aplicar sobre album y usuario
```
READ
READ_AND_WRITE
```
1) Registrar album compartido con usuario y permiso
```  
POST /shared_albums
```
Ejemplo JSON:
```
{"userId": "1","albumId": "1","permission": "READ"}
```
2) Cambiar permisos de usuario para albúm determinado
```
PUT /shared_albums
```
Ejemplo JSON:
```
{"userId": "1","albumId": "1","permission": "READ_AND_WRITE"}
```
3) Traer todos los usuarios que tienen permiso determinado respecto a un albúm especifico
```
GET /users?album={albumId}&permisson={permisson}
```
