# wchallenge

## Api externa 
https://jsonplaceholder.typicode.com

## Api wchallenge
### Los usuarios
```
GET /users
```
### Los albums por usuario
```
GET /users/{ID}/albums
```
### Las fotos de un usuario
```
GET /users/{ID}/photos
```
### Las fotos
```
GET /photos
```
### Los albums
```
GET /albums
```

## Gestión de permisos

Permisos para aplicar sobre album y usuario
```
Permission:
READ
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
