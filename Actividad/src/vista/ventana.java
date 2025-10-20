/**
 *
 * @author kchil
 */

package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import controlador.ControladorNuevo;
import java.awt.Font;

public class ventana extends JFrame {

    // --- Componentes de la interfaz ---
    public JPanel contentPane;
    public JTextField txt_nombres;
    public JTextField txt_telefono;
    public JTextField txt_email;
    public JTextField txt_buscar;
    public JCheckBox chb_favorito;
    public JComboBox<String> cmb_categoria;
    public JButton btn_add;
    public JButton btn_modificar;
    public JButton btn_eliminar;
    public JList<String> lst_contactos;
    public JScrollPane scrLista;

    public JTabbedPane tabbedPane; // Nueva pestaña para organizar la interfaz
    public JTable tablaContactos; // Tabla para mostrar contactos
    public DefaultTableModel modeloTabla; // Modelo de la tabla
    public JComboBox<String> cmbFiltrarCat; // ComboBox para filtrar contactos por categoría
    public JButton btnImportar; // Botón para importar contactos desde la lista a la tabla
    public JButton btnExportar; // Botón para exportar la tabla a CSV
    public JProgressBar progressBar; // Barra de progreso para indicar procesos largos

    // --- Menu contextual ---
    public JPopupMenu menuTabla; // Menú emergente para acciones sobre la tabla
    public JMenuItem menuModificar;
    public JMenuItem menuEliminar;

    public ControladorNuevo controlador; // Controlador de la aplicación (MVC)

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ventana frame = new ventana();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ventana() {
        setTitle("GESTION DE CONTACTOS");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(100, 100, 1026, 748);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        // JTabbedPane para organizar la interfaz en pestañas ---
        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        contentPane.add(tabbedPane, BorderLayout.CENTER);

        // Panel de Contactos
        JPanel panelContactos = new JPanel();
        panelContactos.setLayout(null);
        tabbedPane.addTab("Contactos", panelContactos);

        // Panel de Estadísticas
        JPanel panelEstadisticas = new JPanel();
        panelEstadisticas.setLayout(null);
        tabbedPane.addTab("Estadísticas", panelEstadisticas);

        // --- Campos y etiquetas de Contactos ---
        JLabel lbl_etiqueta1 = new JLabel("NOMBRES:");
        lbl_etiqueta1.setBounds(25, 41, 89, 13);
        lbl_etiqueta1.setFont(new Font("Tahoma", Font.BOLD, 15));
        panelContactos.add(lbl_etiqueta1);

        txt_nombres = new JTextField();
        txt_nombres.setBounds(124, 28, 427, 31);
        txt_nombres.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txt_nombres.setColumns(10);
        panelContactos.add(txt_nombres);

        txt_telefono = new JTextField();
        txt_telefono.setBounds(124, 69, 427, 31);
        txt_telefono.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txt_telefono.setColumns(10);
        panelContactos.add(txt_telefono);

        txt_email = new JTextField();
        txt_email.setBounds(124, 110, 427, 31);
        txt_email.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txt_email.setColumns(10);
        panelContactos.add(txt_email);

        txt_buscar = new JTextField();
        txt_buscar.setBounds(212, 650, 784, 31);
        txt_buscar.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txt_buscar.setColumns(10);
        panelContactos.add(txt_buscar);

        chb_favorito = new JCheckBox("CONTACTO FAVORITO");
        chb_favorito.setBounds(51, 170, 193, 21);
        chb_favorito.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panelContactos.add(chb_favorito);

        cmb_categoria = new JComboBox<>();
        cmb_categoria.setBounds(300, 167, 251, 31);
        String[] categorias = {"Elija una Categoria", "Familia", "Amigos", "Trabajo"};
        for (String categoria : categorias) cmb_categoria.addItem(categoria);
        panelContactos.add(cmb_categoria);

        // --- Botones principales ---
        btn_add = new JButton("AGREGAR");
        btn_add.setBounds(601, 70, 125, 65);
        btn_add.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panelContactos.add(btn_add);

        btn_modificar = new JButton("MODIFICAR");
        btn_modificar.setBounds(736, 70, 125, 65);
        btn_modificar.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panelContactos.add(btn_modificar);

        btn_eliminar = new JButton("ELIMINAR");
        btn_eliminar.setBounds(871, 69, 125, 65);
        btn_eliminar.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panelContactos.add(btn_eliminar);

        // --- Lista de contactos ---
        lst_contactos = new JList<>();
        lst_contactos.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lst_contactos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrLista = new JScrollPane(lst_contactos);
        scrLista.setBounds(25, 242, 971, 398);
        panelContactos.add(scrLista);
        
        // Etiquetas adicionales
        JLabel lbl_etiqueta1_1 = new JLabel("TELEFONO:");
        lbl_etiqueta1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
        lbl_etiqueta1_1.setBounds(25, 80, 89, 13);
        panelContactos.add(lbl_etiqueta1_1);
        
        JLabel lbl_etiqueta1_2 = new JLabel("EMAIL:");
        lbl_etiqueta1_2.setFont(new Font("Tahoma", Font.BOLD, 15));
        lbl_etiqueta1_2.setBounds(25, 121, 89, 13);
        panelContactos.add(lbl_etiqueta1_2);

        // --- Tabla de Estadísticas ---
        String[] columnasTabla = {"Nombre", "Teléfono", "Email", "Categoría", "Favorito"};
        modeloTabla = new DefaultTableModel(columnasTabla, 0);
        tablaContactos = new JTable(modeloTabla);
        tablaContactos.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tablaContactos.setAutoCreateRowSorter(true); // Permite ordenamiento de columnas

        JScrollPane scrollTabla = new JScrollPane(tablaContactos);
        scrollTabla.setBounds(10, 10, 971, 516);
        panelEstadisticas.add(scrollTabla);

        JLabel lblFiltrarCat = new JLabel("Filtrar por categoría:");
        lblFiltrarCat.setBounds(39, 618, 168, 25);
        lblFiltrarCat.setFont(new Font("Tahoma", Font.BOLD, 15));
        panelEstadisticas.add(lblFiltrarCat);

        cmbFiltrarCat = new JComboBox<>();
        cmbFiltrarCat.setBounds(51, 581, 150, 25);
        cmbFiltrarCat.setFont(new Font("Tahoma", Font.PLAIN, 15));
        cmbFiltrarCat.addItem("Todos");
        cmbFiltrarCat.addItem("Familia");
        cmbFiltrarCat.addItem("Amigos");
        cmbFiltrarCat.addItem("Trabajo");
        panelEstadisticas.add(cmbFiltrarCat);

        // --- Botones de importar y exportar ---
        btnImportar = new JButton("Importar desde Contactos");
        btnImportar.setBounds(321, 571, 207, 45);
        btnImportar.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panelEstadisticas.add(btnImportar);

        btnExportar = new JButton("Exportar a CSV");
        btnExportar.setBounds(538, 567, 207, 49);
        btnExportar.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panelEstadisticas.add(btnExportar);

        // --- Barra de progreso ---
        progressBar = new JProgressBar();
        progressBar.setBounds(10, 536, 971, 25);
        progressBar.setStringPainted(true);
        panelEstadisticas.add(progressBar);

        // --- Menú contextual ---
        menuTabla = new JPopupMenu();
        menuModificar = new JMenuItem("Modificar");
        menuEliminar = new JMenuItem("Eliminar");
        menuTabla.add(menuModificar);
        menuTabla.add(menuEliminar);

        // --- Inicialización del controlador ---
        controlador = new ControladorNuevo(this);
        controlador.inicializar();
    }
}

/**
*
* @author kchil
*/