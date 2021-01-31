# wchallenge

## Api externa 
https://jsonplaceholder.typicode.com

## Api wchallenge

### Los usuarios
/users
### Las fotos
/photos
### Los albums
/albums
### Los albums por usuario
/users/{USER_ID}/albums
### Las fotos de un usuario
/users/{USER_ID}/photos

## Gestión de permisos (Lectura / Escritura)
- Registrar album compartido con usuario y permiso
/permisssion
  
- Cambiar permisos de un usuario para un album
/permission
  
- Traer todos los usuarios que tienen un permiso determinado respecto a un álbum específico
/users/permission/{permiso}/albums/{albumId}
