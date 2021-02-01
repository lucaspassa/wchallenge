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

## Gestión de permisos

Permisos para aplicar sobre album y usuario
```
READONLY
READ_AND_WRITE
```
Registrar album compartido con usuario y permiso
```  
POST /shared_albums
```
Ejemplo JSON:
```
{        
    "userId": "1",
    "albumId": "1",
    "permission": "READ"
}
```
Cambiar permisos de usuario para albúm determinado
```
PUT /shared_albums
```
Ejemplo JSON:
```
{        
    "userId": "1",
    "albumId": "1",
    "permission": "READ_AND_WRITE"
}
```
