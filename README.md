# TechU: Plataforma Educativa Colaborativa para Estudiantes

## Nombre del Curso:
**CS 2031 Desarrollo Basado en Plataforma**

### Integrantes:
- Lucia Elisa Rodriguez Simon
- Stefano Andre Canales Soto
- Juan Pablo Esteban Portugal Gutierrez
- Alvaro Andre Pichilingue Guardia

Correo de contacto:
- [lucia.rodriguez@utec.edu.pe](mailto:lucia.rodriguez@utec.edu.pe)
- [stefano.canales@utec.edu.pe](mailto:stefano.canales@utec.edu.pe)
- [juan.portugal@utec.edu.pe](mailto:juan.portugal@utec.edu.pe)
- [alvaro.pichilingue@utec.edu.pe](mailto:alvaro.pichilingue@utec.edu.pe)

---

## Índice

1. [Introducción](#introducción)
2. [Identificación del Problema o Necesidad](#identificación-del-problema-o-necesidad)
3. [Descripción de la Solución](#descripción-de-la-solución)
    - 3.1. Funcionalidades Implementadas
    - 3.2. Tecnologías Utilizadas
4. [Modelo de Entidades](#modelo-de-entidades)
5. [Testing y Manejo de Errores](#testing-y-manejo-de-errores)
6. [Medidas de Seguridad Implementadas](#medidas-de-seguridad-implementadas)
7. [Eventos y Asincronía](#eventos-y-asincronía)
8. [GitHub](#github)
9. [Conclusión](#conclusión)
10. [Apéndices](#apéndices)

---

## Introducción 📚

**Contexto:**
En la actualidad, los estudiantes buscan formas activas de aprender y compartir conocimientos. TechU nace para satisfacer esta demanda, ofreciendo una plataforma donde los estudiantes no solo pueden acceder a recursos educativos, sino también compartir sus propios conocimientos mediante la creación de cursos y tutorías. Esto fomenta el aprendizaje colaborativo, generando una comunidad dinámica donde todos aprenden y enseñan.

**Objetivos del Proyecto:**
- Promover el aprendizaje colaborativo entre estudiantes.
- Facilitar la creación y compartición de cursos por parte de los propios estudiantes.
- Mejorar el acceso a recursos educativos dinámicos y actualizados.
- Fomentar una comunidad educativa activa y en constante crecimiento.
- Desarrollar habilidades clave como comunicación y liderazgo en los estudiantes.

---

## Identificación del Problema o Necesidad

**Descripción del Problema:**
El sistema educativo actual se enfoca en un aprendizaje pasivo, donde los estudiantes consumen información sin la oportunidad de enseñarla. Los estudios demuestran que enseñar es una de las formas más efectivas de consolidar el aprendizaje. Además, los recursos educativos en muchas plataformas no siempre son accesibles ni adaptables a los estilos de aprendizaje de los estudiantes.

**Justificación:**
TechU es relevante porque proporciona a los estudiantes una plataforma para crear y compartir conocimientos, lo cual no solo mejora su propio entendimiento, sino que también ofrece recursos diversos y actualizados a sus compañeros. Esto contribuye a una educación más inclusiva y participativa.

---

## Descripción de la Solución 💡

### Funcionalidades Implementadas:
1. **Rol Dual: Estudiante y Profesor:** Los usuarios pueden ser estudiantes y profesores, fomentando el intercambio de conocimientos.
2. **Creación de Cursos por Estudiantes:** Los estudiantes crean sus propios cursos, lo que ofrece contenido más dinámico y actualizado.
3. **Sistema de Reputación y Retroalimentación:** Los cursos se califican y comentan, lo que fomenta la mejora continua.
4. **Grupos de Estudio Colaborativos:** Los estudiantes forman grupos para sesiones de tutoría y debates, incentivando el trabajo en equipo.
5. **Gamificación:** Logros y recompensas por enseñar y aprender, incentivando la participación.

### Tecnologías Utilizadas:
- **Lenguajes de Programación:** JavaScript, Python.
- **Frameworks y Librerías:** React para el frontend, Node.js para el backend.
- **Bases de Datos:** MongoDB para el almacenamiento de datos.
- **APIs Externas:** Integración de servicios de autenticación como OAuth y Google Classroom API.

---

## Modelo de Entidades 📊

### Descripción de Entidades:
1. **Usuario:** Atributos como ID, nombre, correo electrónico, rol (estudiante/profesor), y cursos impartidos.
2. **Curso:** Atributos como ID, nombre del curso, descripción, creador, calificación, y lista de estudiantes inscritos.
3. **Grupo de Estudio:** Atributos como ID, tema, lista de miembros, y sesiones agendadas.

---

## Testing y Manejo de Errores

### Niveles de Testing Realizados:
- **Pruebas Unitarias:** Para asegurar que cada componente funcione de forma independiente.
- **Pruebas de Integración:** Verificación de la interacción entre los distintos módulos.
- **Pruebas de Sistema:** Evaluación del sistema completo para asegurar su correcta funcionalidad.

**Resultados:**
Las pruebas detectaron algunos errores en la integración de la base de datos y la interfaz, que fueron corregidos en las siguientes iteraciones.

**Manejo de Errores:**
Se implementó un manejo global de excepciones para gestionar errores como fallos de conexión, errores de autenticación, y manejo de datos no válidos.

---

## Medidas de Seguridad Implementadas 🔒

**Seguridad de Datos:**
TechU emplea técnicas de cifrado para proteger la información de los usuarios y autenticación basada en OAuth para garantizar la seguridad en el acceso.

**Prevención de Vulnerabilidades:**
- Inyección SQL: Uso de consultas parametrizadas en MongoDB.
- XSS: Validación y escape de entradas de usuario en la interfaz.
- CSRF: Implementación de tokens CSRF para la seguridad en formularios.

---

## Eventos y Asincronía

TechU implementa eventos para la actualización en tiempo real de sesiones de grupo y creación de cursos. La asincronía es fundamental para manejar tareas como el envío de notificaciones y la carga de datos en segundo plano sin interrumpir la experiencia del usuario.

---

## GitHub

- Se utilizó GitHub Projects para organizar las tareas, asignar issues y establecer deadlines.
- Se implementaron GitHub Actions para la integración continua, ejecutando pruebas automáticas y despliegue del proyecto.

---

## Conclusión 🔑

### Logros del Proyecto:
TechU ha logrado crear una plataforma colaborativa donde los estudiantes pueden aprender y enseñar, fomentando una comunidad activa de aprendizaje.

### Aprendizajes Clave:
El desarrollo de TechU permitió al equipo mejorar sus habilidades técnicas, como la gestión de datos, desarrollo de interfaces y la implementación de seguridad.

### Trabajo Futuro:
Se podrían implementar nuevas funcionalidades como la integración con otras plataformas educativas y la posibilidad de ofrecer cursos con certificaciones.

---

## Apéndices

### Licencia:
Este proyecto se distribuye bajo la licencia MIT.

### Referencias:
(Incluir las referencias aquí)
