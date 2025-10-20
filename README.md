# Gestión de Contactos Mejorada

## Descripción
Esta aplicación permite gestionar contactos personales y laborales de manera eficiente. Se implementaron mejoras sobre la versión anterior, agregando nuevas funcionalidades y componentes gráficos que optimizan la experiencia del usuario y amplían la interactividad de la interfaz gráfica.

Se aplicó el patrón **MVC** (Modelo-Vista-Controlador) para organizar el código, mejorar la mantenibilidad y escalabilidad del proyecto.

---

## Funcionalidades Principales

1. **Interfaz con pestañas**
   - La aplicación cuenta con un `JTabbedPane` que separa la gestión de contactos y la visualización de estadísticas.
   - Pestañas disponibles:
     - **Contactos**: Crear, modificar, eliminar y buscar contactos.
     - **Estadísticas**: Visualizar contactos en una tabla con filtros y ordenamiento.

2. **Atajos de teclado**
   - **Ctrl+1**: Cambiar a la pestaña Contactos.
   - **Ctrl+2**: Cambiar a la pestaña Estadísticas.

3. **Menú contextual**
   - Clic derecho sobre la tabla (`tablaContactos`) para modificar o eliminar contactos.

4. **Filtro de búsqueda en tiempo real**
   - `DocumentListener` permite filtrar la lista de contactos automáticamente al escribir en el campo de búsqueda.

5. **Importación de contactos**
   - Se pueden importar los contactos de la lista al `JTable`.
   - Incluye una `JProgressBar` para mostrar el progreso de la importación.

6. **Exportación a CSV**
   - Permite exportar los contactos de la tabla a un archivo CSV para su respaldo o uso externo.

---

## Componentes Principales

- **Ventana (Vista)**
  - `JTabbedPane` con pestañas Contactos y Estadísticas.
  - Campos de texto (`txt_nombres`, `txt_telefono`, `txt_email`) y combinaciones (`cmb_categoria`).
  - Lista de contactos (`JList`) y tabla de estadísticas (`JTable`).
  - Botones: Agregar, Modificar, Eliminar, Importar, Exportar.
  - Barra de progreso (`JProgressBar`) para operaciones largas.
  - Menú contextual (`JPopupMenu`) sobre la tabla.

- **Controlador**
  - Maneja eventos de teclado y mouse.
  - Gestiona la lógica de agregar, modificar, eliminar, importar y exportar contactos.
  - Filtrado de lista y tabla en tiempo real.

---

## Cómo usar

1. Abrir la aplicación ejecutando la clase `ventana`.
2. Para agregar un contacto:
   - Completar los campos Nombre, Teléfono, Email y Categoría.
   - Marcar si es favorito.
   - Presionar **Agregar**.
3. Para modificar o eliminar un contacto:
   - Hacer clic derecho sobre la fila en la tabla y seleccionar la acción.
4. Para buscar un contacto:
   - Escribir en el campo de búsqueda; la lista se actualizará automáticamente.
5. Para importar contactos a la tabla:
   - Presionar el botón **Importar desde Contactos** y observar la barra de progreso.
6. Para exportar contactos:
   - Presionar el botón **Exportar a CSV** y seleccionar la ubicación del archivo.

---

## Tecnologías utilizadas

- Java Swing
- Java Collections (List, DefaultListModel)
- MVC (Modelo-Vista-Controlador)
- JTable y JList
- JFileChooser y PrintWriter para exportación
- SwingWorker para tareas en segundo plano
- DocumentListener para filtrado en tiempo real

---

## Autor
Kevin Chila
