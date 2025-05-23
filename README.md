# Tarea 2

### Grupo 25
#### Integrantes:
* José Efraín González Aguayo
* Diego Antonio Matus Salas

## Cambios respecto al UML y justificaciones
### Modificaciones realizadas
1. **Método `creadorDeInstant`:**
   Se añadió un método estático para gestionar fácilmente la creación de objetos `Instant`. Esto facilita la reutilización de código y mejora la legibilidad.
2. **Almacenamiento del invitado en `Invitación`:**
   Ahora la clase `Invitación` contiene a los individuos invitados (`Invitable`). Esto permite que las reuniones puedan gestionar las listas de invitaciones.
3. **Se quitó la interfaz `Invitable` en `Departamento`:**
   La interfaz `Invitable` la consideramos una característica asociada a individuos. Ahora, si se desea invitar a un `Departamento`, la reunión gestionará la invitación individualmente para cada miembro.
4. **Nuevo método `getNombreCompleto` en `Invitable`:**
   Dado que trabajamos la interfaz `Invitable` sólo para individuos, ahora incluye un nuevo método que permite recuperar el nombre completo del invitado.
5. **Clase `Asistencia`:**
   Se extendió para incluir al invitado, un booleano que indica si asistió o no a la reunión, y un registro de la hora de llegada. Esto permite manejar correctamente el marcado de presentes, ausentes y retrasos.
6. **Clase `InvitadoExterno`:**
   Se creó esta nueva clase para representar invitados externos según lo solicitado.
7. **Modificación en `Retraso`:**
   Ahora incluye al invitable asociado al retraso. Esto permite una relación más directa entre el retraso y el invitado.
8. **Gestión centralizada de invitaciones en `Reunión`:**
   La clase `Reunión` ahora se encarga de administrar las invitaciones a sus reuniones específicas. Esto asegura consistencia y evita duplicados.
9. **Gestión centralizada de asistencia en `Reunión`:**
   La clase `Reunión` ahora centraliza la lógica para registrar asistencia y manejar retrasos. Las instancias de asistencia se almacenan en un mapa que relaciona a cada invitado con su registro de asistencia.
10. **Constantes estáticas en `Reunión`:**
    Se añadieron constantes para dar formato a las fechas y horas para que sean uniformes en el proyecto.
11. **Cálculo de retrasos respecto a la hora prevista:**
    Se encontró lógico que los retrasos se cuenten respecto a la hora prevista de la reunión y no la hora real, dado a que eso fue lo "avisado" al individuo invitado, cualquier diferencia entre la hora prevista y real no es culpa del invitado.
12. **Ausentes marcados automáticamente al finalizar la reunión:**
    Los invitados que no estuvieron presentes antes del fin de la reunión son registrados automáticamente como ausentes.
13. **Porcentaje de asistencia basado únicamente en invitados:**
    El porcentaje de asistencia incluye únicamente a los invitados registrados por la reunión y no a todos los objectos `Invitable` activos.
14. **Nuevas excepciones que se lanzan al tener problemas al crear reunión:**
    Existen nuevas excepciones `DuracionReunionInvalidaException`, `FechaReunionInvalidaException`, `OrganizadorInvalidoException`.
15. **Clase `Nota`:**
    La clase `Nota` también guarda el instante de tiempo en que se creo dicha nota.
16. **Implementación de interfaz `Comparable` en clase `Nota`:**
    La clase `Nota` tiene implementado un método compareTo() para poder ordenar notas cronológicamente. 
17. **Implementación de método para generar informe:**
    La clase `Reunion` tiene un método para poder generar un informe con de la reunión tal como lo solicitado, demostración de esto en main, con el documento `test.txt` como resultado.

### Nuevo diagrama UML
Se puede acceder presionando [este enlace](https://raw.githubusercontent.com/Diego-Mtus/Tarea2/refs/heads/main/UML%20Tarea2.png)

![](https://raw.githubusercontent.com/Diego-Mtus/Tarea2/refs/heads/main/UML%20Tarea2.png)

### Puedes acceder al JavaDoc de este proyecto presionando [este enlace](https://diego-mtus.github.io/tarea2_javadoc/) (alojado en github.io).
