# SaveUser Endpoint

_Endpoint de verbo POST para creación de usuarios con la siguiente estructura JSON en el cuerpo de la petición :_ 

```
User: 
	{
		"name": "Juan Rodriguez",
		"email": "juan@correo.org",
		"password": "Jvbsdfhs12",
		"phones": [
			{
			"number": "1234567",
			"citycode": "1",
			"contrycode": "57"
			}
		]
	}
```
_Luego de la solicitud , generará la siguiente respuesta , indicando que el usuario ha sido generado con éxito_

```
	{
	    "id": "ddd35562-d3b0-4c03-8918-f0d2542dae6e",
	    "name": "Juan Rodriguez",
	    "email": "juan@correo.org",
	    "password": "Jvbsdfhs12",
	    "created": "2020-10-05T18:15:07.626+00:00",
	    "modified": null,
	    "lastLogin": "2020-10-05T18:15:07.342+00:00",
	    "token": "bc5a6583-910c-4b05-9e03-3a7d1947a9c1",
	    "phones": [
	        {
	            "id": "3c555e6a-3285-49e0-981f-f200d587f614",
	            "number": "1234567",
	            "citycode": "1",
	            "contrycode": "57"
	        }
	    ],
	    "active": true
	}
```

## Pre-requisitos

Nuestro proyecto SpringBoot requerirá lo siguiente:  

* Java versión 8
* Gradle
* Lombok
* H2
* JPA

### Run application 

_Levantaremos el servicio con el siguiente comando_

* gradle bootRun

```
Este servicio levantará por defecto en el puerto 9090
```

## Run test application

-Para ejecutar pruebas unitarias ejecutar el siguiente comando :_

*gradle test

```
Puedes ver el detalle de los test en la siguiente ruta:
/build/reports/tests/test/index.html
```

## Construido con 

_Eclipse IDE_

---