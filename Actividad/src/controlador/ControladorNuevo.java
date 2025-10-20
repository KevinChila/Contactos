

/**
 *
 * @author kchil
 */

package controlador;

import vista.ventana;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.RowFilter;
import javax.swing.SwingWorker;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class ControladorNuevo {

    private ventana vista;

    public ControladorNuevo(ventana vista) {
        this.vista = vista;
    }

    public void inicializar() {
        // --- Atajos de teclado Ctrl+1 y Ctrl+2 para cambiar de pestaña ---
        InputMap im = vista.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = vista.getRootPane().getActionMap();
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_1, KeyEvent.CTRL_DOWN_MASK), "tab1");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_2, KeyEvent.CTRL_DOWN_MASK), "tab2");
        am.put("tab1", new AbstractAction() { public void actionPerformed(ActionEvent e) { vista.tabbedPane.setSelectedIndex(0); }});
        am.put("tab2", new AbstractAction() { public void actionPerformed(ActionEvent e) { vista.tabbedPane.setSelectedIndex(1); }});

        // --- Eventos de botones ---
        vista.btn_add.addActionListener(e -> agregarContacto());
        vista.cmbFiltrarCat.addActionListener(e -> filtrarCategoria());
        vista.btnImportar.addActionListener(e -> importarContactos());
        vista.btnExportar.addActionListener(e -> exportarCSV());

        // --- Evento para buscar contactos en la lista ---
        vista.txt_buscar.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { filtrarLista(); }
            public void removeUpdate(DocumentEvent e) { filtrarLista(); }
            public void changedUpdate(DocumentEvent e) { filtrarLista(); }
        });

        // --- Inicializar menú contextual en la tabla ---
        inicializarMenuContextual();
    }

    // --- Menú contextual con clic derecho ---
    private void inicializarMenuContextual() {
        vista.tablaContactos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                if (e.isPopupTrigger()) {
                    vista.menuTabla.show(e.getComponent(), e.getX(), e.getY());
                }
            }
            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                if (e.isPopupTrigger()) {
                    vista.menuTabla.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
    }

    // --- Métodos de gestión de contactos ---
    private void agregarContacto() {
        String nombre = vista.txt_nombres.getText();
        String telefono = vista.txt_telefono.getText();
        String email = vista.txt_email.getText();
        String categoria = (String) vista.cmb_categoria.getSelectedItem();
        boolean favorito = vista.chb_favorito.isSelected();

        // Agrega contacto a la lista JList
        vista.lst_contactos.setListData(
            appendToList(vista.lst_contactos.getModel(),
            nombre + "," + telefono + "," + email + "," + categoria + "," + (favorito ? "Sí" : "No"))
        );
    }

    private String[] appendToList(ListModel<String> model, String nuevo) {
        int size = model.getSize();
        String[] arr = new String[size + 1];
        for (int i = 0; i < size; i++) arr[i] = model.getElementAt(i);
        arr[size] = nuevo;
        return arr;
    }

    // --- Filtrar tabla por categoría ---
    private void filtrarCategoria() {
        String cat = (String) vista.cmbFiltrarCat.getSelectedItem();
        if ("Todos".equals(cat)) {
            ((javax.swing.table.TableRowSorter<?>) vista.tablaContactos.getRowSorter()).setRowFilter(null);
        } else {
            ((javax.swing.table.TableRowSorter<?>) vista.tablaContactos.getRowSorter())
                    .setRowFilter(RowFilter.regexFilter(cat, 3));
        }
    }

    // --- Importar contactos desde la lista a la tabla con barra de progreso ---
    private void importarContactos() {
        vista.modeloTabla.setRowCount(0);
        SwingWorker<Void, Integer> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                int total = vista.lst_contactos.getModel().getSize();
                for (int i = 0; i < total; i++) {
                    String contacto = vista.lst_contactos.getModel().getElementAt(i);
                    String[] datos = contacto.split(",");
                    vista.modeloTabla.addRow(datos);
                    int progreso = (i + 1) * 100 / total;
                    setProgress(progreso);
                    Thread.sleep(50); // Simula proceso largo
                }
                return null;
            }
            @Override
            protected void done() {
                vista.progressBar.setValue(100);
                JOptionPane.showMessageDialog(null, "Datos importados correctamente");
            }
        };
        worker.addPropertyChangeListener(evt -> {
            if ("progress".equals(evt.getPropertyName())) {
                vista.progressBar.setValue((Integer) evt.getNewValue());
            }
        });
        worker.execute();
    }

 // --- Exportar tabla a CSV ---
    private void exportarCSV() {
        // Abrir un cuadro de diálogo para seleccionar la ubicación y nombre del archivo
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile(); // Obtener el archivo seleccionado
            
            // Usar PrintWriter con try-with-resources para escribir en el archivo de forma segura
            try (PrintWriter pw = new PrintWriter(file)) {
                
                // Recorrer cada fila de la tabla
                for (int i = 0; i < vista.modeloTabla.getRowCount(); i++) {
                    StringBuilder sb = new StringBuilder(); // Construir la línea CSV
                    
                    // Recorrer cada columna de la fila
                    for (int j = 0; j < vista.modeloTabla.getColumnCount(); j++) {
                        sb.append(vista.modeloTabla.getValueAt(i, j)); // Agregar el valor de la celda
                        if (j < vista.modeloTabla.getColumnCount() - 1) sb.append(","); // Separar con coma
                    }
                    
                    pw.println(sb.toString()); // Escribir la línea en el archivo
                }
                
                // Mostrar mensaje de éxito
                JOptionPane.showMessageDialog(null, "Archivo CSV exportado correctamente.");
            } catch (IOException ex) {
                // Mostrar mensaje de error si ocurre un problema al escribir el archivo
                JOptionPane.showMessageDialog(null, "Error al exportar CSV: " + ex.getMessage());
            }
        }
    }
    /**
    *
    * @author kchil
    */

    // --- Filtrar contactos en la lista por búsqueda ---
    private void filtrarLista() {
        String texto = vista.txt_buscar.getText().toLowerCase();
        DefaultListModel<String> model = new DefaultListModel<>();
        for (int i = 0; i < vista.lst_contactos.getModel().getSize(); i++) {
            String contacto = vista.lst_contactos.getModel().getElementAt(i).toLowerCase();
            if (contacto.contains(texto))
                model.addElement(vista.lst_contactos.getModel().getElementAt(i));
        }
        vista.lst_contactos.setModel(model);
    }

    public void cambiarPestana(int indice) {
        vista.tabbedPane.setSelectedIndex(indice);
    }
}
