# Gestión de Contactos Mejorada

## Descripción
Esta aplicación permite gestionar contactos personales y laborales de manera eficiente. Se implementaron mejoras sobre la versión anterior, agregando nuevas funcionalidades y componentes gráficos que optimizan la experiencia del usuario y amplían la interactividad de la interfaz gráfica. Se aplicó el patrón **MVC** para organizar el código y mejorar la mantenibilidad.

---

## Funcionalidades Principales

1. **Interfaz con pestañas**
   - `JTabbedPane` con pestañas Contactos y Estadísticas.
   - **Contactos**: Crear, modificar, eliminar y buscar contactos.
   - **Estadísticas**: Visualizar contactos en una tabla con filtros y ordenamiento.

![Vista de Contactos](https://i.postimg.cc/vmGg3x79/Captura-de-pantalla-2025-10-19-215546.png)
![Vista de Estadísticas](https://i.postimg.cc/C58kWTRP/Captura-de-pantalla-2025-10-19-215719.png)

2. **Atajos de teclado**
   - **Ctrl+1**: Cambiar a la pestaña Contactos.
   - **Ctrl+2**: Cambiar a la pestaña Estadísticas.

3. **Menú contextual**
   - Clic derecho sobre la tabla para modificar o eliminar contactos.

4. **Filtro de búsqueda en tiempo real**
   - `DocumentListener` actualiza la lista de contactos al escribir en el campo de búsqueda.

5. **Importación de contactos**
   - Importa contactos de la lista al `JTable`.
   - Incluye `JProgressBar` para mostrar el progreso de la importación.

6. **Exportación a CSV**
   - Exporta los contactos de la tabla a un archivo CSV.

---

## Componentes Principales

- **Ventana (Vista)**
  - `JTabbedPane` con pestañas Contactos y Estadísticas.
  - Campos de texto (`txt_nombres`, `txt_telefono`, `txt_email`) y combobox (`cmb_categoria`).
  - Lista de contactos (`JList`) y tabla de estadísticas (`JTable`).
  - Botones: Agregar, Modificar, Eliminar, Importar, Exportar.
  - Barra de progreso (`JProgressBar`).
  - Menú contextual (`JPopupMenu`) sobre la tabla.

- **Controlador**
  - Maneja eventos de teclado y mouse.
  - Gestiona la lógica de agregar, modificar, eliminar, importar y exportar contactos.
  - Filtrado de lista y tabla en tiempo real.

---

## Cómo usar

1. Ejecutar la clase `ventana`.
2. Para agregar un contacto: completar los campos y presionar **Agregar**.
3. Para modificar o eliminar un contacto: clic derecho sobre la fila y seleccionar la acción.
4. Para buscar un contacto: escribir en el campo de búsqueda; la lista se actualizará automáticamente.
5. Para importar contactos a la tabla: presionar **Importar desde Contactos** y observar la barra de progreso.
6. Para exportar contactos: presionar **Exportar a CSV** y seleccionar la ubicación del archivo.

---

## Tecnologías utilizadas

- Java Swing
- MVC (Modelo-Vista-Controlador)
- JTable y JList
- JFileChooser y PrintWriter
- SwingWorker para tareas en segundo plano
- DocumentListener para filtrado en tiempo real

---

## Autor
Kevin Chila
