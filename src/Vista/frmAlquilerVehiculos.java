package Vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Controlador.ArrayHistorial;
import Controlador.ArrayRegistro;
import Controlador.ArrayUsuario;
import Controlador.ArrayVehiculo;
import Controlador.Pagar;
import Modelo.Historial;
import Modelo.Registro;
import Modelo.Usuario;
import Modelo.Vehiculo;

import java.awt.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.FlowLayout;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.table.DefaultTableModel;

public class frmAlquilerVehiculos extends JFrame implements ActionListener, ChangeListener, KeyListener{
	
	//************************************************************************************************************************************************************
	//Componentes del formulario
	//************************************************************************************************************************************************************
	private static final long serialVersionUID = 1L;
	private JPanel contentPane,pnlUsuariosLista,pnlAgregarUsuario,pnlModificarUsuario,pnlBuscarRegistro,pnlKilimetros,pnlDetalle;
	private JTextField txtApellidos;
	private JTextField txtNroLicencia;
	private JTextField txtNroDocumento;
	private JTextField txtTelefono;
	private JTextField txtDireccion;
	private JTextField txtEmail;
	private JTable tblReporte;
	private JComboBox<String> cboTipoDocumento;
	
	JScrollPane scpListaUsuarios;
	
	JTabbedPane tabbedPane;
	JRadioButton rbtnAdministrador,rbtnUsuario;
	JLabel lblNmero,lblEmail;
	JLabel lblHora, lblFecha, txtUsuario, txtNombre, lblFechaDevolucion,lblNroRegistro,lblImagenVehiculo,lblTipovehiculo,lblCodigoVehiculo,
	lblMarcavehiculo,lblModeloVehiculo,lblPlacavehiculo,lblColorvehiculo,lblPuertasvehiculo,lblPasajerosvehiculo,lblMaletasvehiculo,
	lblVelocidadmax,lblTipocombustible,lblKilometrajeactual,lblVehiculosDisponibles;
	
	JLabel lblNombreNuevoUs,lblUsuarioNuevoUs,lblPassNuevoUs,lblRepPassNuevoUs,lblRolNuevoUs;
	JLabel lblNombreUsuarioMod,lblUsuarioMod,lblPassAntModUsuario,lblNuevaContrasea,lblConfirmarContrasena;
	JLabel lblDetalle1,lblDetalle2,lblDetalle3,lblDetalle4,lblDetalle5,lblDetalle6,lblDetalle7,lblDetalle8,lblDetalle9,
			lblClienteDetalle,lblPlacaDetalle,lblCodigoDetalle,lblFechaRegistroDetalle,lblFechaDevolucionDetalle,
			lblKmInicialesDetalle,lblKmRecorridosDetalle,lblTotalDetalle,lblNroRegistroDetalle;
	
	JLabel lblApellidos,lblKiloRecorridos;
	
	JButton btnRegistrar,btnBuscarRegistro,btnAnterior,btnSiguiente,btnAnadir,btnEliminar,btnModificar,btnRegistrarUsuario,
			btnIngresarKilometros,btnCancelar,btnConfirmarMod,btnCancelarMod,btnPagar;
	JSpinner snpDiasAlquiler;
	SpinnerNumberModel cantidadSnp;
	DefaultTableModel mdlRegistro, mdlListaUsuarios, mdlHistorial;
	ArrayRegistro registro;
	ArrayVehiculo vehiculos;
	ArrayUsuario usuarios;
	ArrayHistorial historiales;
	Historial historial;
	Usuario champi;
	Registro buscarPagar;
	Vehiculo vehiculoPagar;
	DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	int digitosDocumento;
	private JTextField txtNumeroRegistro;
	private JTable tblListaUsuarios;
	private JTextField txtNombreNuevoUsuario;
	private JTextField txtUsuarioNuevo;
	private JPasswordField pswPassNuevo;
	private JPasswordField pswPassNuevoRe;
	private JPasswordField pswContrasenaAnt;
	private JPasswordField pswContraNueva;
	private JPasswordField pswContraNuevaRep;
	private JTextField txtKiloRecorridos;
	private JLabel lblDetalle10;
	private JLabel lblDetalle11;
	private JLabel lblSubtotalDetalle;
	private JLabel lblIGVDetalle;
	private JTable tblHistorial;
	private JButton btnAnular;
	
	boolean isRuc;
	
	//************************************************************************************************************************************************************
	//Metodo constructor
	//************************************************************************************************************************************************************
	public frmAlquilerVehiculos(String user, String usuario) {
		//Instanciamos los arrays
		registro = new ArrayRegistro();
		vehiculos = new ArrayVehiculo();
		usuarios = new ArrayUsuario();
		buscarPagar = new Registro();
		vehiculoPagar = new Vehiculo();
		historiales = new ArrayHistorial();
		historial = new Historial();
		setTitle("Alquiler de Vehículos");
		setResizable(false);
		setSize(1280,720);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlBarraInferior = new JPanel();
		FlowLayout fl_pnlBarraInferior = (FlowLayout) pnlBarraInferior.getLayout();
		fl_pnlBarraInferior.setAlignment(FlowLayout.LEFT);
		contentPane.add(pnlBarraInferior, BorderLayout.SOUTH);
		
		JLabel lblNewLabel = new JLabel("Fecha:");
		pnlBarraInferior.add(lblNewLabel);
		
		lblFecha = new JLabel("FechaActual");
		pnlBarraInferior.add(lblFecha);
		
		JLabel lblSeparador_1 = new JLabel("|");
		pnlBarraInferior.add(lblSeparador_1);
		
		JLabel lblNewLabel_2 = new JLabel("Hora:");
		pnlBarraInferior.add(lblNewLabel_2);
		
		lblHora = new JLabel("HoraActual");
		pnlBarraInferior.add(lblHora);
		
		JLabel lblSeparador_2 = new JLabel("|");
		pnlBarraInferior.add(lblSeparador_2);
		
		JLabel lblNewLabel_1 = new JLabel("Usuario:");
		pnlBarraInferior.add(lblNewLabel_1);
		
		txtUsuario = new JLabel(user);
		
		pnlBarraInferior.add(txtUsuario);
		
		JLabel lblSeparador_3 = new JLabel("|");
		pnlBarraInferior.add(lblSeparador_3);
		
		JLabel lblNewLabel_4 = new JLabel("Nombre:");
		pnlBarraInferior.add(lblNewLabel_4);
		
		txtNombre = new JLabel(usuario);
		pnlBarraInferior.add(txtNombre);
		
		JLabel lblSeparador = new JLabel("|");
		pnlBarraInferior.add(lblSeparador);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(255, 255, 255));
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		tabbedPane.addChangeListener(this);
		
		JPanel pnlRegistrar = new JPanel();
		pnlRegistrar.setBackground(new Color(176, 196, 222));
		tabbedPane.addTab("Registrar", null, pnlRegistrar, null);
		pnlRegistrar.setLayout(null);
		
		JPanel pnlDatosCliente = new JPanel();
		pnlDatosCliente.setBackground(new Color(176, 196, 222));
		pnlDatosCliente.setBorder(new TitledBorder(null, "Datos del Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlDatosCliente.setBounds(20, 25, 563, 280);
		pnlRegistrar.add(pnlDatosCliente);
		pnlDatosCliente.setLayout(null);
		
		lblApellidos = new JLabel("Cliente:");
		lblApellidos.setFont(new Font("Arial", Font.PLAIN, 13));
		lblApellidos.setBounds(21, 23, 130, 14);
		pnlDatosCliente.add(lblApellidos);
		
		lblNmero = new JLabel("Número de Licencia:");
		lblNmero.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNmero.setBounds(404, 23, 119, 14);
		pnlDatosCliente.add(lblNmero);
		
		txtApellidos = new JTextField();
		txtApellidos.setToolTipText("Ingresa los datos del cliente.");
		txtApellidos.setFont(new Font("Arial", Font.PLAIN, 13));
		txtApellidos.setBounds(21, 48, 320, 25);
		txtApellidos.addKeyListener(this);
		pnlDatosCliente.add(txtApellidos);
		txtApellidos.setColumns(10);
		
		txtNroLicencia = new JTextField();
		txtNroLicencia.setToolTipText("Ingresa el nro de licencia.");
		txtNroLicencia.setFont(new Font("Arial", Font.PLAIN, 13));
		txtNroLicencia.setColumns(10);
		txtNroLicencia.setBounds(404, 48, 119, 25);
		txtNroLicencia.addKeyListener(this);
		pnlDatosCliente.add(txtNroLicencia);
		
		JLabel lblTipo = new JLabel("Tipo de Documento:");
		lblTipo.setFont(new Font("Arial", Font.PLAIN, 13));
		lblTipo.setBounds(21, 93, 119, 14);
		pnlDatosCliente.add(lblTipo);
		
		JLabel lblNmeroDeDocumento = new JLabel("Número de Documento:");
		lblNmeroDeDocumento.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNmeroDeDocumento.setBounds(214, 93, 146, 14);
		pnlDatosCliente.add(lblNmeroDeDocumento);
		
		JLabel lblTelfono = new JLabel("Teléfono:");
		lblTelfono.setFont(new Font("Arial", Font.PLAIN, 13));
		lblTelfono.setBounds(404, 93, 62, 14);
		pnlDatosCliente.add(lblTelfono);
		
		cboTipoDocumento = new JComboBox<String>();
		cboTipoDocumento.setToolTipText("Selecciona el tipo de documento.");
		cboTipoDocumento.setModel(new DefaultComboBoxModel<String>(new String[] {"", "DNI", "CE", "RUC"}));
		cboTipoDocumento.setFont(new Font("Arial", Font.PLAIN, 13));
		cboTipoDocumento.setBounds(21, 118, 119, 25);
		cboTipoDocumento.addActionListener(this);
		pnlDatosCliente.add(cboTipoDocumento);
		
		txtNroDocumento = new JTextField();
		txtNroDocumento.setToolTipText("Ingresa el número de documento.");
		txtNroDocumento.setEnabled(false);
		txtNroDocumento.setFont(new Font("Arial", Font.PLAIN, 13));
		txtNroDocumento.setColumns(10);
		txtNroDocumento.setBounds(214, 118, 180, 25);
		txtNroDocumento.addKeyListener(this);
		pnlDatosCliente.add(txtNroDocumento);
		
		txtTelefono = new JTextField();
		txtTelefono.setToolTipText("Ingresa el número de teléfono.");
		txtTelefono.setFont(new Font("Arial", Font.PLAIN, 13));
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(404, 118, 119, 25);
		txtTelefono.addKeyListener(this);
		pnlDatosCliente.add(txtTelefono);
		
		JLabel lblDireccin = new JLabel("Dirección:");
		lblDireccin.setFont(new Font("Arial", Font.PLAIN, 13));
		lblDireccin.setBounds(21, 161, 62, 14);
		pnlDatosCliente.add(lblDireccin);
		
		lblEmail = new JLabel("E-Mail:");
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 13));
		lblEmail.setBounds(346, 161, 48, 14);
		pnlDatosCliente.add(lblEmail);
		
		txtDireccion = new JTextField();
		txtDireccion.setToolTipText("Ingresa la dirección.");
		txtDireccion.setFont(new Font("Arial", Font.PLAIN, 13));
		txtDireccion.setColumns(10);
		txtDireccion.addKeyListener(this);
		txtDireccion.setBounds(21, 186, 300, 25);
		pnlDatosCliente.add(txtDireccion);
		
		txtEmail = new JTextField();
		txtEmail.setToolTipText("Ingresa el E-mail.");
		txtEmail.setFont(new Font("Arial", Font.PLAIN, 13));
		txtEmail.setColumns(10);
		txtEmail.setBounds(346, 186, 177, 25);
		txtEmail.addKeyListener(this);
		pnlDatosCliente.add(txtEmail);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 387, 1208, 225);
		pnlRegistrar.add(scrollPane);
		
		//instanciando modelo para la tabla del reporte
		mdlRegistro = new DefaultTableModel(new Object[][] {},new String[] {"N\u00BA Registro", "Cliente",
				"N\u00BA Documento", "N\u00BA Licencia", "Telefono", "C\u00F3digo Veh\u00EDculo", "Placa",
				"D\u00EDas Alquiler", "Fecha Devoluci\u00F3n", "Fecha Registro", "Hora Registro"}) {
			
			@Override
			public boolean isCellEditable(int filas, int columnas) {
					return false;
			}
		};
		
		//Creando JTable
		tblReporte = new JTable();
		tblReporte.setBackground(new Color(218, 227, 239));
		tblReporte.setFont(new Font("Arial", Font.PLAIN, 13));
		tblReporte.setModel(mdlRegistro);
		tblReporte.getColumnModel().getColumn(0).setPreferredWidth(72);
		tblReporte.getColumnModel().getColumn(1).setPreferredWidth(149);
		tblReporte.getColumnModel().getColumn(2).setPreferredWidth(83);
		tblReporte.getColumnModel().getColumn(5).setPreferredWidth(98);
		tblReporte.getColumnModel().getColumn(8).setPreferredWidth(101);
		tblReporte.getColumnModel().getColumn(9).setPreferredWidth(97);
		tblReporte.getColumnModel().getColumn(10).setPreferredWidth(90);
		scrollPane.setViewportView(tblReporte);
		
		JPanel pnlVehiculos = new JPanel();
		pnlVehiculos.setBackground(new Color(176, 196, 222));
		pnlVehiculos.setBorder(new TitledBorder(null, "Veh\u00EDculo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlVehiculos.setBounds(593, 25, 635, 280);
		pnlRegistrar.add(pnlVehiculos);
		pnlVehiculos.setLayout(null);
		
		JLabel lblTipoV = new JLabel("Tipo:");
		lblTipoV.setFont(new Font("Arial", Font.PLAIN, 13));
		lblTipoV.setBounds(22, 26, 30, 20);
		pnlVehiculos.add(lblTipoV);
		
		JLabel lblCodigoV = new JLabel("Código:");
		lblCodigoV.setFont(new Font("Arial", Font.PLAIN, 13));
		lblCodigoV.setBounds(192, 26, 50, 20);
		pnlVehiculos.add(lblCodigoV);
		
		lblCodigoVehiculo = new JLabel("codigoVehiculo");
		lblCodigoVehiculo.setFont(new Font("Arial", Font.PLAIN, 13));
		lblCodigoVehiculo.setBounds(252, 26, 88, 20);
		pnlVehiculos.add(lblCodigoVehiculo);
		
		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setFont(new Font("Arial", Font.PLAIN, 13));
		lblMarca.setBounds(22, 57, 41, 20);
		pnlVehiculos.add(lblMarca);
		
		lblMarcavehiculo = new JLabel("marcaVehiculo");
		lblMarcavehiculo.setFont(new Font("Arial", Font.PLAIN, 13));
		lblMarcavehiculo.setBounds(73, 57, 88, 20);
		pnlVehiculos.add(lblMarcavehiculo);
		
		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setFont(new Font("Arial", Font.PLAIN, 13));
		lblModelo.setBounds(192, 57, 50, 20);
		pnlVehiculos.add(lblModelo);
		
		lblModeloVehiculo = new JLabel("marcaVehiculo");
		lblModeloVehiculo.setFont(new Font("Arial", Font.PLAIN, 13));
		lblModeloVehiculo.setBounds(252, 57, 88, 20);
		pnlVehiculos.add(lblModeloVehiculo);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds(403, 22, 210, 210);
		pnlVehiculos.add(panel);
		
		lblImagenVehiculo = new JLabel("");
		panel.add(lblImagenVehiculo);
		lblImagenVehiculo.setIcon(new ImageIcon("src/Imagenes/toyota-yaris-blanco-200x200.png"));
		
		lblTipovehiculo = new JLabel("tipoVehiculo");
		lblTipovehiculo.setFont(new Font("Arial", Font.PLAIN, 13));
		lblTipovehiculo.setBounds(62, 26, 88, 20);
		pnlVehiculos.add(lblTipovehiculo);
		
		JLabel lblPlaca = new JLabel("Placa:");
		lblPlaca.setFont(new Font("Arial", Font.PLAIN, 13));
		lblPlaca.setBounds(22, 88, 41, 20);
		pnlVehiculos.add(lblPlaca);
		
		lblPlacavehiculo = new JLabel("PlacaVehiculo");
		lblPlacavehiculo.setFont(new Font("Arial", Font.PLAIN, 13));
		lblPlacavehiculo.setBounds(73, 88, 88, 20);
		pnlVehiculos.add(lblPlacavehiculo);
		
		JLabel lblColor = new JLabel("Color:");
		lblColor.setFont(new Font("Arial", Font.PLAIN, 13));
		lblColor.setBounds(192, 88, 41, 20);
		pnlVehiculos.add(lblColor);
		
		lblColorvehiculo = new JLabel("ColorVehiculo");
		lblColorvehiculo.setFont(new Font("Arial", Font.PLAIN, 13));
		lblColorvehiculo.setBounds(243, 88, 88, 20);
		pnlVehiculos.add(lblColorvehiculo);
		
		JLabel lblPuertas = new JLabel("Puertas:");
		lblPuertas.setFont(new Font("Arial", Font.PLAIN, 13));
		lblPuertas.setBounds(22, 119, 50, 20);
		pnlVehiculos.add(lblPuertas);
		
		lblPuertasvehiculo = new JLabel("###");
		lblPuertasvehiculo.setFont(new Font("Arial", Font.PLAIN, 13));
		lblPuertasvehiculo.setBounds(83, 119, 30, 20);
		pnlVehiculos.add(lblPuertasvehiculo);
		
		JLabel lblPasajeros = new JLabel("Pasajeros:");
		lblPasajeros.setFont(new Font("Arial", Font.PLAIN, 13));
		lblPasajeros.setBounds(134, 119, 62, 20);
		pnlVehiculos.add(lblPasajeros);
		
		lblPasajerosvehiculo = new JLabel("###");
		lblPasajerosvehiculo.setFont(new Font("Arial", Font.PLAIN, 13));
		lblPasajerosvehiculo.setBounds(203, 119, 30, 20);
		pnlVehiculos.add(lblPasajerosvehiculo);
		
		JLabel lblMaletas = new JLabel("Maletas:");
		lblMaletas.setFont(new Font("Arial", Font.PLAIN, 13));
		lblMaletas.setBounds(250, 119, 50, 20);
		pnlVehiculos.add(lblMaletas);
		
		lblMaletasvehiculo = new JLabel("###");
		lblMaletasvehiculo.setFont(new Font("Arial", Font.PLAIN, 13));
		lblMaletasvehiculo.setBounds(310, 119, 30, 20);
		pnlVehiculos.add(lblMaletasvehiculo);
		
		JLabel lblVelocidadMax = new JLabel("Velocidad Max:");
		lblVelocidadMax.setFont(new Font("Arial", Font.PLAIN, 13));
		lblVelocidadMax.setBounds(22, 150, 91, 20);
		pnlVehiculos.add(lblVelocidadMax);
		
		lblVelocidadmax = new JLabel("lblVelMax");
		lblVelocidadmax.setFont(new Font("Arial", Font.PLAIN, 13));
		lblVelocidadmax.setBounds(123, 150, 62, 20);
		pnlVehiculos.add(lblVelocidadmax);
		
		JLabel lblCombustible = new JLabel("Combustible:");
		lblCombustible.setFont(new Font("Arial", Font.PLAIN, 13));
		lblCombustible.setBounds(192, 150, 76, 20);
		pnlVehiculos.add(lblCombustible);
		
		lblTipocombustible = new JLabel("TipoCombustible");
		lblTipocombustible.setFont(new Font("Arial", Font.PLAIN, 13));
		lblTipocombustible.setBounds(277, 150, 101, 20);
		pnlVehiculos.add(lblTipocombustible);
		
		JLabel lblKilometraje = new JLabel("Kilometraje:");
		lblKilometraje.setFont(new Font("Arial", Font.PLAIN, 13));
		lblKilometraje.setBounds(22, 181, 69, 20);
		pnlVehiculos.add(lblKilometraje);
		
		lblKilometrajeactual = new JLabel("KilometrajeActual");
		lblKilometrajeactual.setFont(new Font("Arial", Font.PLAIN, 13));
		lblKilometrajeactual.setBounds(101, 181, 101, 20);
		pnlVehiculos.add(lblKilometrajeactual);
		
		btnAnterior = new JButton("Anterior");
		btnAnterior.setFont(new Font("Arial", Font.PLAIN, 13));
		btnAnterior.setBounds(403, 243, 89, 25);
		btnAnterior.addActionListener(this);
		btnAnterior.setEnabled(false);
		pnlVehiculos.add(btnAnterior);
		
		btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setFont(new Font("Arial", Font.PLAIN, 13));
		btnSiguiente.setBounds(524, 243, 89, 25);
		btnSiguiente.addActionListener(this);
		pnlVehiculos.add(btnSiguiente);
		
		JLabel lblVehculosDisponibles = new JLabel("Vehículos disponibles:");
		lblVehculosDisponibles.setFont(new Font("Arial", Font.PLAIN, 13));
		lblVehculosDisponibles.setBounds(10, 248, 130, 20);
		pnlVehiculos.add(lblVehculosDisponibles);
		
		lblVehiculosDisponibles = new JLabel("0");
		lblVehiculosDisponibles.setFont(new Font("Arial", Font.PLAIN, 13));
		lblVehiculosDisponibles.setBounds(150, 248, 35, 20);
		pnlVehiculos.add(lblVehiculosDisponibles);
		
		JPanel pnlAlquiler = new JPanel();
		pnlAlquiler.setBackground(new Color(176, 196, 222));
		pnlAlquiler.setBorder(new TitledBorder(null, "Alquiler", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlAlquiler.setBounds(20, 316, 463, 60);
		pnlRegistrar.add(pnlAlquiler);
		pnlAlquiler.setLayout(null);
		
		JLabel lblDiasAlquiler = new JLabel("Días de Alquiler:");
		lblDiasAlquiler.setBounds(56, 25, 95, 16);
		lblDiasAlquiler.setFont(new Font("Arial", Font.PLAIN, 13));
		pnlAlquiler.add(lblDiasAlquiler);
		
		//Instanciando Modelo del spinner
		cantidadSnp = new SpinnerNumberModel(1, 1, 15, 1);
		
		//Instanciando Spinner
		snpDiasAlquiler = new JSpinner();
		snpDiasAlquiler.setToolTipText("Selecciona los días de alquiler.");
		snpDiasAlquiler.setModel(cantidadSnp);
		snpDiasAlquiler.setBounds(161, 21, 50, 25);
		snpDiasAlquiler.addChangeListener(this);
		pnlAlquiler.add(snpDiasAlquiler);
		
		JLabel lblFechaDevolucin = new JLabel("Fecha de devolución:");
		lblFechaDevolucin.setBounds(225, 25, 122, 16);
		lblFechaDevolucin.setFont(new Font("Arial", Font.PLAIN, 13));
		pnlAlquiler.add(lblFechaDevolucin);
		
		lblFechaDevolucion = new JLabel("##/##/####");
		lblFechaDevolucion.setBounds(358, 25, 80, 16);
		lblFechaDevolucion.setFont(new Font("Arial", Font.PLAIN, 13));
		pnlAlquiler.add(lblFechaDevolucion);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setFont(new Font("Arial", Font.PLAIN, 13));
		btnRegistrar.setBounds(493, 334, 90, 25);
		btnRegistrar.addActionListener(this);
		pnlRegistrar.add(btnRegistrar);
		
		JLabel lblTotalRegistrados = new JLabel("Total Registrados:");
		lblTotalRegistrados.setFont(new Font("Arial", Font.PLAIN, 13));
		lblTotalRegistrados.setBounds(610, 360, 105, 16);
		pnlRegistrar.add(lblTotalRegistrados);
		
		lblNroRegistro = new JLabel("0");
		lblNroRegistro.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNroRegistro.setBounds(725, 360, 25, 16);
		pnlRegistrar.add(lblNroRegistro);
		
		//Panel para cobrar y listar transacciones
		JPanel pnlCobrar = new JPanel();
		pnlCobrar.setBackground(new Color(176, 196, 222));
		tabbedPane.addTab("Cobrar", null, pnlCobrar, null);
		pnlCobrar.setLayout(null);
		
		pnlBuscarRegistro = new JPanel();
		pnlBuscarRegistro.setBackground(new Color(176, 196, 222));
		pnlBuscarRegistro.setBorder(new TitledBorder(null, "Buscar Registro", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlBuscarRegistro.setBounds(10, 11, 380, 60);
		pnlCobrar.add(pnlBuscarRegistro);
		pnlBuscarRegistro.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblBuscarRegistro = new JLabel("Numero de Registro:");
		lblBuscarRegistro.setFont(new Font("Arial", Font.PLAIN, 13));
		pnlBuscarRegistro.add(lblBuscarRegistro);
		
		txtNumeroRegistro = new JTextField();
		txtNumeroRegistro.setFont(new Font("Arial", Font.PLAIN, 13));
		txtNumeroRegistro.setColumns(10);
		txtNumeroRegistro.addKeyListener(this);
		pnlBuscarRegistro.add(txtNumeroRegistro);
		
		btnBuscarRegistro = new JButton("Buscar");
		btnBuscarRegistro.setFont(new Font("Arial", Font.PLAIN, 13));
		btnBuscarRegistro.addActionListener(this);
		pnlBuscarRegistro.add(btnBuscarRegistro);
		
		//panel para ingresar km
		pnlKilimetros = new JPanel();
		pnlKilimetros.setBackground(new Color(176, 196, 222));
		pnlKilimetros.setBorder(new TitledBorder(null, "Kilometros", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlKilimetros.setBounds(10, 82, 380, 60);
		pnlCobrar.add(pnlKilimetros);
		pnlKilimetros.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblKiloRecorridos = new JLabel("Kilometros recorridos:");
		lblKiloRecorridos.setFont(new Font("Arial", Font.PLAIN, 13));
		pnlKilimetros.add(lblKiloRecorridos);
		
		txtKiloRecorridos = new JTextField();
		txtKiloRecorridos.setFont(new Font("Arial", Font.PLAIN, 13));
		pnlKilimetros.add(txtKiloRecorridos);
		txtKiloRecorridos.setColumns(10);
		txtKiloRecorridos.addKeyListener(this);
		
		btnIngresarKilometros = new JButton("Ingresar");
		btnIngresarKilometros.setFont(new Font("Arial", Font.PLAIN, 13));
		btnIngresarKilometros.addActionListener(this);
		pnlKilimetros.add(btnIngresarKilometros);
		
		apagarPanelKilometros();
		
		//Panel Detalle
		pnlDetalle = new JPanel();
		pnlDetalle.setBackground(new Color(176, 196, 222));
		pnlDetalle.setBorder(new TitledBorder(null, "Detalle", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlDetalle.setBounds(10, 153, 380, 455);
		pnlCobrar.add(pnlDetalle);
		pnlDetalle.setLayout(null);
		
		lblDetalle1 = new JLabel("Registro Nº:");
		lblDetalle1.setFont(new Font("Arial", Font.BOLD, 13));
		lblDetalle1.setBounds(98, 24, 75, 14);
		pnlDetalle.add(lblDetalle1);
		
		lblDetalle2 = new JLabel("Cliente:");
		lblDetalle2.setFont(new Font("Arial", Font.PLAIN, 13));
		lblDetalle2.setBounds(115, 58, 46, 14);
		pnlDetalle.add(lblDetalle2);
		
		lblDetalle3 = new JLabel("Placa:");
		lblDetalle3.setFont(new Font("Arial", Font.PLAIN, 13));
		lblDetalle3.setBounds(124, 93, 37, 14);
		pnlDetalle.add(lblDetalle3);
		
		lblDetalle4 = new JLabel("Codigo Vehiculo:");
		lblDetalle4.setFont(new Font("Arial", Font.PLAIN, 13));
		lblDetalle4.setBounds(63, 128, 98, 14);
		pnlDetalle.add(lblDetalle4);
		
		lblDetalle5 = new JLabel("Fecha registro:");
		lblDetalle5.setFont(new Font("Arial", Font.PLAIN, 13));
		lblDetalle5.setBounds(74, 163, 87, 14);
		pnlDetalle.add(lblDetalle5);
		
		lblDetalle6 = new JLabel("Fecha devolucion:");
		lblDetalle6.setFont(new Font("Arial", Font.PLAIN, 13));
		lblDetalle6.setBounds(57, 198, 104, 14);
		pnlDetalle.add(lblDetalle6);
		
		lblDetalle7 = new JLabel("Km iniciales:");
		lblDetalle7.setFont(new Font("Arial", Font.PLAIN, 13));
		lblDetalle7.setBounds(86, 233, 75, 14);
		pnlDetalle.add(lblDetalle7);
		
		lblDetalle8 = new JLabel("Km recorridos:");
		lblDetalle8.setFont(new Font("Arial", Font.PLAIN, 13));
		lblDetalle8.setBounds(74, 268, 87, 14);
		pnlDetalle.add(lblDetalle8);
		
		lblClienteDetalle = new JLabel("New label");
		lblClienteDetalle.setFont(new Font("Arial", Font.PLAIN, 13));
		lblClienteDetalle.setBounds(188, 58, 182, 14);
		pnlDetalle.add(lblClienteDetalle);
		
		lblPlacaDetalle = new JLabel("New label");
		lblPlacaDetalle.setFont(new Font("Arial", Font.PLAIN, 13));
		lblPlacaDetalle.setBounds(188, 93, 182, 14);
		pnlDetalle.add(lblPlacaDetalle);
		
		lblCodigoDetalle = new JLabel("New label");
		lblCodigoDetalle.setFont(new Font("Arial", Font.PLAIN, 13));
		lblCodigoDetalle.setBounds(188, 128, 182, 14);
		pnlDetalle.add(lblCodigoDetalle);
		
		lblFechaRegistroDetalle = new JLabel("New label");
		lblFechaRegistroDetalle.setFont(new Font("Arial", Font.PLAIN, 13));
		lblFechaRegistroDetalle.setBounds(188, 163, 182, 14);
		pnlDetalle.add(lblFechaRegistroDetalle);
		
		lblFechaDevolucionDetalle = new JLabel("New label");
		lblFechaDevolucionDetalle.setFont(new Font("Arial", Font.PLAIN, 13));
		lblFechaDevolucionDetalle.setBounds(188, 198, 182, 14);
		pnlDetalle.add(lblFechaDevolucionDetalle);
		
		lblKmInicialesDetalle = new JLabel("New label");
		lblKmInicialesDetalle.setFont(new Font("Arial", Font.PLAIN, 13));
		lblKmInicialesDetalle.setBounds(188, 233, 182, 14);
		pnlDetalle.add(lblKmInicialesDetalle);
		
		lblKmRecorridosDetalle = new JLabel("New label");
		lblKmRecorridosDetalle.setFont(new Font("Arial", Font.PLAIN, 13));
		lblKmRecorridosDetalle.setBounds(188, 268, 182, 14);
		pnlDetalle.add(lblKmRecorridosDetalle);
		
		lblDetalle9 = new JLabel("Total:");
		lblDetalle9.setFont(new Font("Arial", Font.BOLD, 30));
		lblDetalle9.setBounds(77, 367, 87, 36);
		pnlDetalle.add(lblDetalle9);
		
		lblTotalDetalle = new JLabel("S/.00.00");
		lblTotalDetalle.setFont(new Font("Arial", Font.BOLD, 30));
		lblTotalDetalle.setBounds(174, 367, 196, 36);
		pnlDetalle.add(lblTotalDetalle);
		
		lblNroRegistroDetalle = new JLabel("0000000000");
		lblNroRegistroDetalle.setFont(new Font("Arial", Font.BOLD, 13));
		lblNroRegistroDetalle.setBounds(183, 24, 113, 14);
		pnlDetalle.add(lblNroRegistroDetalle);
		
		btnPagar = new JButton("PAGAR");
		btnPagar.setFont(new Font("Arial", Font.BOLD, 18));
		btnPagar.setBounds(206, 419, 110, 25);
		btnPagar.addActionListener(this);
		pnlDetalle.add(btnPagar);
		
		lblDetalle10 = new JLabel("Subtotal:");
		lblDetalle10.setFont(new Font("Arial", Font.BOLD, 16));
		lblDetalle10.setBounds(91, 301, 70, 14);
		pnlDetalle.add(lblDetalle10);
		
		lblDetalle11 = new JLabel("IGV:");
		lblDetalle11.setFont(new Font("Arial", Font.BOLD, 16));
		lblDetalle11.setBounds(128, 336, 33, 14);
		pnlDetalle.add(lblDetalle11);
		
		lblSubtotalDetalle = new JLabel("New label");
		lblSubtotalDetalle.setFont(new Font("Arial", Font.PLAIN, 13));
		lblSubtotalDetalle.setBounds(188, 303, 182, 14);
		pnlDetalle.add(lblSubtotalDetalle);
		
		lblIGVDetalle = new JLabel("New label");
		lblIGVDetalle.setFont(new Font("Arial", Font.PLAIN, 13));
		lblIGVDetalle.setBounds(188, 338, 182, 14);
		pnlDetalle.add(lblIGVDetalle);
		
		btnAnular = new JButton("ANULAR");
		btnAnular.setFont(new Font("Arial", Font.BOLD, 18));
		btnAnular.setBounds(63, 419, 110, 25);
		btnAnular.addActionListener(this);
		pnlDetalle.add(btnAnular);
		
		//Panel Historial
		JPanel pnlHistorial = new JPanel();
		pnlHistorial.setBackground(new Color(176, 196, 222));
		pnlHistorial.setBorder(new TitledBorder(null, "Historial", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlHistorial.setBounds(400, 11, 839, 597);
		pnlCobrar.add(pnlHistorial);
		pnlHistorial.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(14, 20, 815, 566);
		pnlHistorial.add(scrollPane_1);
		
		mdlHistorial = new DefaultTableModel(
				new Object[][] {},new String[] {"N\u00BA Registro", "Cliente", "Placa",
				"KM Recorridos", "Fecha Alquiler", "Fecha Pago",
				"Total", "Subtotal", "IGV"}) {
			
			public boolean isCellEditable(int rows, int columns) {
				return false;
			}
			
		};
		
		tblHistorial = new JTable();
		scrollPane_1.setViewportView(tblHistorial);
		tblHistorial.setModel(mdlHistorial);
		tblHistorial.getColumnModel().getColumn(0).setPreferredWidth(84);
		tblHistorial.getColumnModel().getColumn(1).setPreferredWidth(182);
		tblHistorial.getColumnModel().getColumn(2).setPreferredWidth(84);
		tblHistorial.getColumnModel().getColumn(3).setPreferredWidth(88);
		tblHistorial.getColumnModel().getColumn(4).setPreferredWidth(89);
		tblHistorial.getColumnModel().getColumn(5).setPreferredWidth(80);
		tblHistorial.setBackground(new Color(218, 227, 239));
		tblHistorial.setFont(new Font("Arial", Font.PLAIN, 13));
		
		apagarPanelDetalle();
		
		setearHora();
		iniciarReloj();
		lblFechaDevolucion.setText(calcularFechaDevolucion().format(formatoFecha));
		
		try {
			setVehiculo(vehiculos.primerVehiculoDispnible().getCodigo());
			lblVehiculosDisponibles.setText(String.valueOf(vehiculos.vehiculosDisponibles()));
		}
		catch(Exception ex) {
			Vehiculo v = vehiculos.obtener(0);
			vehiculos.activarVehiculo(v);
			lblVehiculosDisponibles.setText(String.valueOf(vehiculos.vehiculosDisponibles()));
			setVehiculo(vehiculos.primerVehiculoDispnible().getCodigo());
			vehiculos.guardar();
		}
		champi = usuarios.getUsuarioXUsuario(user);
		
		if(champi.isAdmin()) {
			activarPanelGestionUsuarios();
		}
		
		llenarTablaReporte();
		llenarTablaHistorial();
		lblNroRegistro.setText(String.valueOf(calcularNumeroDeRegistros()));
	}
	
	//************************************************************************************************************************************************************
	//Metodos del Formulario
	//************************************************************************************************************************************************************
	
	
	//Crear panel para gestionar Usuarios
	public void activarPanelGestionUsuarios() {

		//Creando panel de gestion de usuarios
		JPanel pnlGestionUsuarios = new JPanel();
		pnlGestionUsuarios.setBackground(new Color(176, 196, 222));
		tabbedPane.addTab("Gestionar Usuarios", null, pnlGestionUsuarios, null);
		pnlGestionUsuarios.setLayout(null);
		
		
		pnlUsuariosLista = new JPanel();
		pnlUsuariosLista.setBorder(new TitledBorder(null, "Usuarios del sistema", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlUsuariosLista.setBackground(new Color(176, 196, 222));
		pnlUsuariosLista.setBounds(25, 25, 470, 260);
		pnlGestionUsuarios.add(pnlUsuariosLista);
		pnlUsuariosLista.setLayout(null);
		
		scpListaUsuarios = new JScrollPane();
		scpListaUsuarios.setBounds(20, 22, 430, 180);
		pnlUsuariosLista.add(scpListaUsuarios);
		
		mdlListaUsuarios = new DefaultTableModel(new Object[][] {},new String[] {"Nombre","Usuario","Rol"}) {
			public boolean isCellEditable(int rows, int columns) {
				return false;
			}
		};
		tblListaUsuarios = new JTable();
		tblListaUsuarios.setFont(new Font("Arial", Font.PLAIN, 13));
		tblListaUsuarios.setBackground(new Color(218, 227, 239));
		tblListaUsuarios.setModel(mdlListaUsuarios);
		scpListaUsuarios.setViewportView(tblListaUsuarios);
		
		btnAnadir = new JButton("Añadir");
		btnAnadir.setFont(new Font("Arial", Font.PLAIN, 13));
		btnAnadir.setBounds(20, 213, 89, 25);
		btnAnadir.addActionListener(this);
		pnlUsuariosLista.add(btnAnadir);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Arial", Font.PLAIN, 13));
		btnEliminar.setBounds(361, 213, 89, 25);
		pnlUsuariosLista.add(btnEliminar);
		btnEliminar.addActionListener(this);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setFont(new Font("Arial", Font.PLAIN, 13));
		btnModificar.setBounds(198, 213, 89, 25);
		pnlUsuariosLista.add(btnModificar);
		btnModificar.addActionListener(this);
		
		
		llenarTablaUsuarios();
		
		pnlAgregarUsuario = new JPanel();
		pnlAgregarUsuario.setBackground(new Color(176, 196, 222));
		pnlAgregarUsuario.setBorder(new TitledBorder(null, "Agregar Usuario", TitledBorder.LEADING, TitledBorder.TOP, null, Color.GRAY));
		pnlAgregarUsuario.setBounds(505, 25, 202, 471);
		pnlGestionUsuarios.add(pnlAgregarUsuario);
		pnlAgregarUsuario.setLayout(null);
		
		lblNombreNuevoUs = new JLabel("Nombre:");
		lblNombreNuevoUs.setEnabled(false);
		lblNombreNuevoUs.setBounds(10, 39, 49, 14);
		pnlAgregarUsuario.add(lblNombreNuevoUs);
		lblNombreNuevoUs.setFont(new Font("Arial", Font.PLAIN, 13));
		
		txtNombreNuevoUsuario = new JTextField();
		txtNombreNuevoUsuario.setEnabled(false);
		txtNombreNuevoUsuario.setBounds(10, 64, 182, 25);
		pnlAgregarUsuario.add(txtNombreNuevoUsuario);
		txtNombreNuevoUsuario.setColumns(10);
		
		lblUsuarioNuevoUs = new JLabel("Usuario:");
		lblUsuarioNuevoUs.setEnabled(false);
		lblUsuarioNuevoUs.setBounds(10, 100, 49, 14);
		pnlAgregarUsuario.add(lblUsuarioNuevoUs);
		lblUsuarioNuevoUs.setFont(new Font("Arial", Font.PLAIN, 13));
		
		txtUsuarioNuevo = new JTextField();
		txtUsuarioNuevo.setEnabled(false);
		txtUsuarioNuevo.setBounds(10, 125, 182, 25);
		pnlAgregarUsuario.add(txtUsuarioNuevo);
		txtUsuarioNuevo.setColumns(10);
		
		lblPassNuevoUs = new JLabel("Contrase\u00F1a:");
		lblPassNuevoUs.setEnabled(false);
		lblPassNuevoUs.setBounds(10, 161, 70, 14);
		pnlAgregarUsuario.add(lblPassNuevoUs);
		lblPassNuevoUs.setFont(new Font("Arial", Font.PLAIN, 13));
		
		pswPassNuevo = new JPasswordField();
		pswPassNuevo.setEnabled(false);
		pswPassNuevo.setBounds(10, 186, 182, 25);
		pnlAgregarUsuario.add(pswPassNuevo);
		
		lblRepPassNuevoUs = new JLabel("Repetir contrase\u00F1a:");
		lblRepPassNuevoUs.setEnabled(false);
		lblRepPassNuevoUs.setBounds(10, 222, 113, 14);
		pnlAgregarUsuario.add(lblRepPassNuevoUs);
		lblRepPassNuevoUs.setFont(new Font("Arial", Font.PLAIN, 13));
		
		pswPassNuevoRe = new JPasswordField();
		pswPassNuevoRe.setEnabled(false);
		pswPassNuevoRe.setBounds(10, 247, 182, 25);
		pnlAgregarUsuario.add(pswPassNuevoRe);
		
		lblRolNuevoUs = new JLabel("Rol:");
		lblRolNuevoUs.setEnabled(false);
		lblRolNuevoUs.setBounds(10, 283, 31, 14);
		pnlAgregarUsuario.add(lblRolNuevoUs);
		lblRolNuevoUs.setFont(new Font("Arial", Font.PLAIN, 13));
		
		ButtonGroup grpRol = new ButtonGroup();
		
		rbtnAdministrador = new JRadioButton("Administrador");
		rbtnAdministrador.setEnabled(false);
		rbtnAdministrador.setBounds(10, 304, 113, 23);
		pnlAgregarUsuario.add(rbtnAdministrador);
		rbtnAdministrador.setFont(new Font("Arial", Font.PLAIN, 13));
		
		grpRol.add(rbtnAdministrador);
		
		rbtnUsuario = new JRadioButton("Usuario");
		rbtnUsuario.setEnabled(false);
		rbtnUsuario.setBounds(125, 304, 70, 23);
		pnlAgregarUsuario.add(rbtnUsuario);
		rbtnUsuario.setFont(new Font("Arial", Font.PLAIN, 13));
		grpRol.add(rbtnUsuario);
		
		btnRegistrarUsuario = new JButton("Registrar");
		btnRegistrarUsuario.setEnabled(false);
		btnRegistrarUsuario.setFont(new Font("Arial", Font.PLAIN, 13));
		btnRegistrarUsuario.setBounds(41, 364, 120, 25);
		pnlAgregarUsuario.add(btnRegistrarUsuario);
		btnRegistrarUsuario.addActionListener(this);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 13));
		btnCancelar.setEnabled(false);
		btnCancelar.setBounds(41, 407, 120, 25);
		pnlAgregarUsuario.add(btnCancelar);
		btnCancelar.addActionListener(this);
		
		pnlModificarUsuario = new JPanel();
		pnlModificarUsuario.setBackground(new Color(176, 196, 222));
		pnlModificarUsuario.setBorder(new TitledBorder(null, "Modificar contrase\u00F1a", TitledBorder.LEADING, TitledBorder.TOP, null, Color.GRAY));
		pnlModificarUsuario.setBounds(25, 296, 470, 252);
		pnlGestionUsuarios.add(pnlModificarUsuario);
		pnlModificarUsuario.setLayout(null);
		
		lblNombreUsuarioMod = new JLabel("NombreUsuario");
		lblNombreUsuarioMod.setBounds(198, 33, 240, 14);
		pnlModificarUsuario.add(lblNombreUsuarioMod);
		lblNombreUsuarioMod.setFont(new Font("Arial", Font.BOLD, 18));
		
		lblUsuarioMod = new JLabel("Usuario:");
		lblUsuarioMod.setBounds(114, 33, 74, 14);
		pnlModificarUsuario.add(lblUsuarioMod);
		lblUsuarioMod.setFont(new Font("Arial", Font.BOLD, 18));
		
		lblPassAntModUsuario = new JLabel("Contrase\u00F1a:");
		lblPassAntModUsuario.setBounds(99, 75, 70, 14);
		pnlModificarUsuario.add(lblPassAntModUsuario);
		lblPassAntModUsuario.setFont(new Font("Arial", Font.PLAIN, 13));
		
		lblNuevaContrasea = new JLabel("Nueva contrase\u00F1a:");
		lblNuevaContrasea.setBounds(63, 111, 107, 14);
		pnlModificarUsuario.add(lblNuevaContrasea);
		lblNuevaContrasea.setFont(new Font("Arial", Font.PLAIN, 13));
		
		lblConfirmarContrasena = new JLabel("Confirmar contrase\u00F1a:");
		lblConfirmarContrasena.setBounds(42, 147, 127, 14);
		pnlModificarUsuario.add(lblConfirmarContrasena);
		lblConfirmarContrasena.setFont(new Font("Arial", Font.PLAIN, 13));
		
		btnConfirmarMod = new JButton("Confirmar");
		btnConfirmarMod.setFont(new Font("Arial", Font.PLAIN, 13));
		btnConfirmarMod.setBounds(179, 193, 120, 25);
		pnlModificarUsuario.add(btnConfirmarMod);
		btnConfirmarMod.addActionListener(this);
		
		btnCancelarMod = new JButton("Cancelar");
		btnCancelarMod.setFont(new Font("Arial", Font.PLAIN, 13));
		btnCancelarMod.setBounds(318, 193, 120, 25);
		pnlModificarUsuario.add(btnCancelarMod);
		
		pswContrasenaAnt = new JPasswordField();
		pswContrasenaAnt.setBounds(179, 70, 259, 25);
		pnlModificarUsuario.add(pswContrasenaAnt);
		
		pswContraNueva = new JPasswordField();
		pswContraNueva.setBounds(180, 108, 259, 25);
		pnlModificarUsuario.add(pswContraNueva);
		
		pswContraNuevaRep = new JPasswordField();
		pswContraNuevaRep.setBounds(179, 144, 259, 25);
		pnlModificarUsuario.add(pswContraNuevaRep);
		btnCancelarMod.addActionListener(this);
		
		apagarPanelModificarUsuario();
	}
	//Metodo para apagar panel Detalle
	public void apagarPanelDetalle() {
		lblDetalle1.setEnabled(false);
		lblDetalle2.setEnabled(false);
		lblDetalle3.setEnabled(false);
		lblDetalle4.setEnabled(false);
		lblDetalle5.setEnabled(false);
		lblDetalle6.setEnabled(false);
		lblDetalle7.setEnabled(false);
		lblDetalle8.setEnabled(false);
		lblDetalle9.setEnabled(false);
		lblDetalle10.setEnabled(false);
		lblDetalle11.setEnabled(false);
		lblClienteDetalle.setEnabled(false);
		lblPlacaDetalle.setEnabled(false);
		lblCodigoDetalle.setEnabled(false);
		lblFechaRegistroDetalle.setEnabled(false);
		lblFechaDevolucionDetalle.setEnabled(false);
		lblKmInicialesDetalle.setEnabled(false);
		lblKmRecorridosDetalle.setEnabled(false);
		lblSubtotalDetalle.setEnabled(false);
		lblIGVDetalle.setEnabled(false);
		lblTotalDetalle.setEnabled(false);
		lblNroRegistroDetalle.setEnabled(false);
		btnPagar.setEnabled(false);
		btnAnular.setEnabled(false);
		pnlDetalle.setBorder(new TitledBorder(null, "Detalle", TitledBorder.LEADING, TitledBorder.TOP, null, Color.GRAY));
		limpiarDetalle();
	}
	
	//Metodo para encender panel Detalle
	public void encenderPanelDetalle() {
		lblDetalle1.setEnabled(true);
		lblDetalle2.setEnabled(true);
		lblDetalle3.setEnabled(true);
		lblDetalle4.setEnabled(true);
		lblDetalle5.setEnabled(true);
		lblDetalle6.setEnabled(true);
		lblDetalle7.setEnabled(true);
		lblDetalle8.setEnabled(true);
		lblDetalle9.setEnabled(true);
		lblDetalle10.setEnabled(true);
		lblDetalle11.setEnabled(true);
		lblClienteDetalle.setEnabled(true);
		lblPlacaDetalle.setEnabled(true);
		lblCodigoDetalle.setEnabled(true);
		lblFechaRegistroDetalle.setEnabled(true);
		lblFechaDevolucionDetalle.setEnabled(true);
		lblKmInicialesDetalle.setEnabled(true);
		lblKmRecorridosDetalle.setEnabled(true);
		lblSubtotalDetalle.setEnabled(true);
		lblIGVDetalle.setEnabled(true);
		lblTotalDetalle.setEnabled(true);
		lblNroRegistroDetalle.setEnabled(true);
		btnPagar.setEnabled(true);
		btnAnular.setEnabled(true);
		pnlDetalle.setBorder(new TitledBorder(null, "Detalle", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
	}
	
	//Metodo para apagar panel kilometros recorridos
	public void apagarPanelKilometros() {
		txtKiloRecorridos.setEnabled(false);
		txtKiloRecorridos.setText("");
		lblKiloRecorridos.setEnabled(false);
		btnIngresarKilometros.setEnabled(false);
		pnlKilimetros.setBorder(new TitledBorder(null, "Kilometros", TitledBorder.LEADING, TitledBorder.TOP, null, Color.GRAY));
		txtNumeroRegistro.requestFocus();
		txtNumeroRegistro.setText("");
	}
	
	//Metodo para encender panel kilometros recorridos
	public void encenderPanelKilometros() {
		txtKiloRecorridos.setEnabled(true);
		txtKiloRecorridos.requestFocus();
		lblKiloRecorridos.setEnabled(true);
		btnIngresarKilometros.setEnabled(true);
		pnlKilimetros.setBorder(new TitledBorder(null, "Kilometros", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
	}
	
	//Metodo para limpiar lo del nuevo usuario
	public void limpiarPanelRegistrarUsuario() {
		txtNombreNuevoUsuario.setText("");
		txtUsuarioNuevo.setText("");
		pswPassNuevo.setText("");
		pswPassNuevoRe.setText("");
	}
	
	//Metodo para limpiar el panel de modificacion de usuario
	public void limpiarPanelModificarUsuario() {
		lblNombreUsuarioMod.setText("");
		pswContrasenaAnt.setText("");
		pswContraNueva.setText("");
		pswContraNuevaRep.setText("");
	}
	
	//Metodo para apgar el panel de la tabal de usuarios
	public void apagarPanelListaUsuario() {
		scpListaUsuarios.setEnabled(false);
		tblListaUsuarios.setEnabled(false);
		btnAnadir.setEnabled(false);
		btnEliminar.setEnabled(false);
		btnModificar.setEnabled(false);
		tblListaUsuarios.setForeground(Color.GRAY);
		
		pnlUsuariosLista.setBorder(new TitledBorder(null, "Usuarios del sistema", TitledBorder.LEADING, TitledBorder.TOP, null, Color.GRAY));
	}
	
	//Metodo para apgar el panel de la tabal de usuarios
	public void encenderPanelListaUsuario() {
		scpListaUsuarios.setEnabled(true);
		tblListaUsuarios.setEnabled(true);
		btnAnadir.setEnabled(true);
		btnEliminar.setEnabled(true);
		btnModificar.setEnabled(true);
		tblListaUsuarios.clearSelection();
		tblListaUsuarios.setForeground(Color.BLACK);
		pnlUsuariosLista.setBorder(new TitledBorder(null, "Usuarios del sistema", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
	}
	
	//Metodo para encender panel de RegistrarNuevoUsuario
	public void encenderPanelRegistrarUsuario() {
		lblNombreNuevoUs.setEnabled(true);
		lblUsuarioNuevoUs.setEnabled(true);
		lblPassNuevoUs.setEnabled(true);
		lblRepPassNuevoUs.setEnabled(true);
		lblRolNuevoUs.setEnabled(true);
		txtNombreNuevoUsuario.setEnabled(true);
		txtUsuarioNuevo.setEnabled(true);
		pswPassNuevo.setEnabled(true);
		pswPassNuevoRe.setEnabled(true);
		rbtnAdministrador.setEnabled(true);
		rbtnUsuario.setEnabled(true);
		rbtnUsuario.setSelected(true);
		btnRegistrarUsuario.setEnabled(true);
		btnCancelar.setEnabled(true);
		tblListaUsuarios.clearSelection();
		pnlAgregarUsuario.setBorder(new TitledBorder(null, "Agregar Usuario", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
	}
	
	//Metodo para apagar panel de registrarNUevoUsuario
	public void apagarPanelRegistrarUsuario() {
		limpiarPanelRegistrarUsuario();
		lblNombreNuevoUs.setEnabled(false);
		lblUsuarioNuevoUs.setEnabled(false);
		lblPassNuevoUs.setEnabled(false);
		lblRepPassNuevoUs.setEnabled(false);
		lblRolNuevoUs.setEnabled(false);
		txtNombreNuevoUsuario.setEnabled(false);
		txtUsuarioNuevo.setEnabled(false);
		pswPassNuevo.setEnabled(false);
		pswPassNuevoRe.setEnabled(false);
		rbtnAdministrador.setEnabled(false);
		rbtnUsuario.setEnabled(false);
		btnRegistrarUsuario.setEnabled(false);
		btnCancelar.setEnabled(false);
		pnlAgregarUsuario.setBorder(new TitledBorder(null, "Agregar Usuario", TitledBorder.LEADING, TitledBorder.TOP, null, Color.GRAY));
	}
	
	//Metodo para encender panel de modificar usuario
	public void encenderPanelModificarUsuario() {
		lblNombreUsuarioMod.setEnabled(true);
		lblUsuarioMod.setEnabled(true);
		lblPassAntModUsuario.setEnabled(true);
		lblNuevaContrasea.setEnabled(true);
		lblConfirmarContrasena.setEnabled(true);
		pswContrasenaAnt.setEnabled(true);
		pswContraNueva.setEnabled(true);
		pswContraNuevaRep.setEnabled(true);
		btnConfirmarMod.setEnabled(true);
		btnCancelarMod.setEnabled(true);
		pnlModificarUsuario.setBorder(new TitledBorder(null, "Modificar contrase\u00F1a", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
	}
	
	//Metodo para apagar panel de modificar usuario
	public void apagarPanelModificarUsuario() {
		limpiarPanelModificarUsuario();
		lblNombreUsuarioMod.setEnabled(false);
		lblUsuarioMod.setEnabled(false);
		lblPassAntModUsuario.setEnabled(false);
		lblNuevaContrasea.setEnabled(false);
		lblConfirmarContrasena.setEnabled(false);
		pswContrasenaAnt.setEnabled(false);
		pswContraNueva.setEnabled(false);
		pswContraNuevaRep.setEnabled(false);
		btnConfirmarMod.setEnabled(false);
		btnCancelarMod.setEnabled(false);
		pnlModificarUsuario.setBorder(new TitledBorder(null, "Modificar contrase\u00F1a", TitledBorder.LEADING, TitledBorder.TOP, null, Color.GRAY));
	}
	
	//Metodo para limpiar panel de registro de alquiler
	public void limpiarPanelRegistro() {
		txtApellidos.setText("");
		txtNroLicencia.setText("");
		txtNroDocumento.setText("");
		txtTelefono.setText("");
		txtDireccion.setText("");
		txtEmail.setText("");
		cantidadSnp.setValue(1);
	}
		
	//Metodo para iniciar Reloj
	private void iniciarReloj() {
		Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setearHora();
            }
        });
        timer.start();
    }
	
	//Metodo para setear la hora actual
	public void setearHora() {
		SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
        Date ahora = new Date();
        lblFecha.setText(sdfDate.format(ahora));
        lblHora.setText(sdfTime.format(ahora));
	}
	
	
	//Metodo para llenar informacion del vehiculo
	public void setVehiculo(int cod) {
		lblTipovehiculo.setText(vehiculos.obtenerXCOdigo(cod).getTipo());
		lblCodigoVehiculo.setText(String.valueOf(vehiculos.obtenerXCOdigo(cod).getCodigo()));
		lblMarcavehiculo.setText(vehiculos.obtenerXCOdigo(cod).getMarca());
		lblModeloVehiculo.setText(vehiculos.obtenerXCOdigo(cod).getModelo());
		lblPlacavehiculo.setText(vehiculos.obtenerXCOdigo(cod).getPlaca());
		lblColorvehiculo.setText(vehiculos.obtenerXCOdigo(cod).getColor());
		lblPuertasvehiculo.setText(String.valueOf(vehiculos.obtenerXCOdigo(cod).getNumPuertas()));
		lblPasajerosvehiculo.setText(String.valueOf(vehiculos.obtenerXCOdigo(cod).getNumPasajeros()));
		lblMaletasvehiculo.setText(String.valueOf(vehiculos.obtenerXCOdigo(cod).getNumMaletas()));
		lblVelocidadmax.setText(String.valueOf(vehiculos.obtenerXCOdigo(cod).getVelocidadMax()));
		lblTipocombustible.setText(vehiculos.obtenerXCOdigo(cod).getTipoCombustible());
		lblKilometrajeactual.setText(String.valueOf(vehiculos.obtenerXCOdigo(cod).getKilometraje()));
		lblImagenVehiculo.setIcon(new ImageIcon(vehiculos.obtenerXCOdigo(cod).getImagenURL()));
	}
	
	//Metodo para ingresar registro
	public void registrar() {
		String cliente, nroLicencia, nroDocumento, telefono, direccion, email,placa;
		int codv;
        
        cliente = txtApellidos.getText();
        nroLicencia = isRuc ? "null" :txtNroLicencia.getText();
        nroDocumento = txtNroDocumento.getText();
        telefono = txtTelefono.getText();
        codv = Integer.parseInt(lblCodigoVehiculo.getText());
        placa = lblPlacavehiculo.getText();
        direccion = txtDireccion.getText();
        email = isRuc ? "null" : txtEmail.getText();
        snpDiasAlquiler.getModel();
        int diasAlquiler = cantidadSnp.getNumber().intValue();
        LocalDate fechaRegistro = LocalDate.now();
        LocalDate fechaDevolucion = fechaRegistro.plusDays(diasAlquiler);
        LocalTime horaRegistro= LocalTime.now();
        
        //Validar que los campos no se encuentren vacios
        if (!cliente.isEmpty()) {
        	if (!nroLicencia.isEmpty()) {
                if (!nroDocumento.isEmpty()) {
                    if (!telefono.isEmpty()) {
                        if (!direccion.isEmpty()) {
                            if (!email.isEmpty()) {
                            	
                            	Vehiculo v = vehiculos.obtenerXCOdigo(codv);
                            	
                            	try {
                            		if(v.isDisponible() && v.getCodigo()!=0) {
                                		Registro r = new Registro(registro.calcularNroRegistro(), cliente, nroDocumento, nroLicencia,telefono, codv, placa, diasAlquiler, fechaRegistro,fechaDevolucion, horaRegistro, direccion,email,true);
                                    	
                                    	registro.crearRegistro(r);
                                    	agregarFilaTablaRegistro(r);
                                    	
                                    	lblNroRegistro.setText(Integer.toString(calcularNumeroDeRegistros()));
                                    	
                                        JOptionPane.showMessageDialog(null, "Registro completado");
                                    	
                                        limpiarPanelRegistro();
                                        
                                        vehiculos.desactivarVehiculo(v);
                                        vehiculos.guardar();
                                        lblVehiculosDisponibles.setText(String.valueOf(vehiculos.vehiculosDisponibles()));
                                        if(vehiculos.haySiguiente(v)) {
                                    		siguienteVehiculo();
                                    	}
                                    	else{
                                    		anteriorVehiculo();
                                    	}
                                    	
                                        if(vehiculos.vehiculosDisponibles()==0) {
                                			v = vehiculos.obtener(0);
                                			vehiculos.activarVehiculo(v);
                                			//JOptionPane.showMessageDialog(null, "");
                                			setVehiculo(v.getCodigo());
                                			btnAnterior.setEnabled(false);
                                			btnSiguiente.setEnabled(false);
                                			lblVehiculosDisponibles.setText("0");
                                		}
                                	}
                                	else {
                                		JOptionPane.showMessageDialog(null, "Champi, ya no puedes hacer mas registros por que no hay vehículos disponibles");
                                	}
                            	}
                            	catch (Exception ex) {
                            		
								}
                            	
                                
                            } else {
                                JOptionPane.showMessageDialog(null, "Ingresa un email.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Ingresa una dirección.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Ingresa el número de teléfono.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ingresa el número de documento.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingresa el número de la licencia.");
            }
        } else {
            JOptionPane.showMessageDialog(null, isRuc ? "Ingresa razón social." :"Ingresa datos del cliente." );
        }
        
	}
	
	//Metodo para calcular numero de Registro
	public int calcularNumeroDeRegistros() {
		int nrRegistros = 0;
    	for( int i =0; i<registro.tamanio(); i++) {
    		Registro regTem = registro.obtener(i);
    		if(regTem.isActivo()) {
    			nrRegistros++;
    		}
    	}
    	return nrRegistros;
	}
	
	//Metodo para Actualizar fecha de devolución
	public LocalDate calcularFechaDevolucion() {
		snpDiasAlquiler.getModel();
        int dias = cantidadSnp.getNumber().intValue();
		LocalDate fechaRegistro = LocalDate.now();
		LocalDate fechaDevolucion =fechaRegistro.plusDays(dias);
		return fechaDevolucion;
	}
	
	//Metodo para comprobar tipo de documento
	public void comprobarTipoDocumento() {
		String tipo = (String) cboTipoDocumento.getSelectedItem();
    	switch (tipo) {
		case "DNI":
			digitosDocumento = 8;
			txtNroDocumento.setText("");
			lblApellidos.setText("Cliente:");
			txtApellidos.setToolTipText("Ingresa los datos del cliente");
			isRuc = false;
			encenderLicEmail();
			break;
		case "CE":
			digitosDocumento = 9;
			txtNroDocumento.setText("");
			lblApellidos.setText("Cliente:");
			txtApellidos.setToolTipText("Ingresa los datos del cliente");
			isRuc = false;
			encenderLicEmail();
			break;
		case "RUC":
			digitosDocumento = 11;
			txtNroDocumento.setText("");
			lblApellidos.setText("Razón social:");
			txtApellidos.setToolTipText("Ingresa la razón social");
			isRuc = true;
			apagarLicEmail();
			break;
		default:
			break;
		}
	}
	
	//Metodo para apagar lo que no requiere RUC
	public void apagarLicEmail() {
		txtNroLicencia.setVisible(false);
		txtEmail.setVisible(false);
		lblNmero.setVisible(false);
		lblEmail.setVisible(false);
	}
	
	//Metodo para apagar lo que no requiere RUC
	public void encenderLicEmail() {
		txtNroLicencia.setVisible(true);
		txtEmail.setVisible(true);
		lblNmero.setVisible(true);
		lblEmail.setVisible(true);
	}
	
	//Metodo para permitir solo numeros
	public void filtrarNumeros(KeyEvent e) {
		char c = e.getKeyChar();
        if (!Character.isDigit(c)) {
            e.consume();
        }
	}
	
	//Metodo para permitir solo Letras
	public void filtrarLetras(KeyEvent e) {
		char c = e.getKeyChar();
	       if (!Character.isLetter(c) && c != KeyEvent.VK_SPACE && c != KeyEvent.VK_BACK_SPACE && c!= KeyEvent.VK_DELETE) {
	           e.consume();
	       }
	}
	
	//Metodo para permitir solo numeros y letras sin espacios ni caracteres especiales
	public void filtrarSerie(KeyEvent e) {
		char c = e.getKeyChar();
        if (!Character.isDigit(c) && !Character.isLetter(c)) {
            e.consume();
        }
	}
	//Metodo para permitir solo letras, numeros, '@' y '.'
	public void filtrarFormatoCorreo(KeyEvent e) {
		char c = e.getKeyChar();
	        if (!Character.isDigit(c) && !Character.isLetter(c) && c!='@' && c!='.') {
	            e.consume();
	        }
		}
	
	//Metodo para limitar digitos
	public void limitarDigitos(KeyEvent e, JTextField txt, int limite) {
		if (txt.getText().length() >= limite) {
            e.consume();
            JOptionPane.showMessageDialog(null, "No se pueden agregar mas digitos","Limite de digitos alcanzado",JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public void siguienteVehiculo() {
		try {
	    	int cod = Integer.parseInt(lblCodigoVehiculo.getText());
	    	Vehiculo v = vehiculos.obtenerXCOdigo(cod);
    		do {
	    		cod++;
				v = vehiculos.obtenerXCOdigo(cod);
				if(cod==vehiculos.getTamanio()) {
					btnSiguiente.setEnabled(false);
				}
				else {
					if(!btnAnterior.isEnabled()) {
						btnAnterior.setEnabled(true);
					}
				}
			} while (!v.isDisponible());
    		setVehiculo(cod);
    	}
    	catch(Exception ex) {
    		/*
    		System.out.println("No hay mas vehiculos disponibles");
    		JOptionPane.showMessageDialog(null,"No hay mas vehiculos disponibles");
    		*/
    	}
	}
	
	public void anteriorVehiculo() {
		try {
			int cod = Integer.parseInt(lblCodigoVehiculo.getText());
	    	Vehiculo v = vehiculos.obtenerXCOdigo(cod);
	    	do {
	    		cod--;
				v = vehiculos.obtenerXCOdigo(cod);
				if(cod==1) {
					btnAnterior.setEnabled(false);
				}
				else {
					if(!btnSiguiente.isEnabled()) {
						btnSiguiente.setEnabled(true);
					}
				}
			} while (!v.isDisponible());
	    	
	    	setVehiculo(cod);
		}
		catch(Exception ex) {
			/*
			System.out.println("No hay mas vehiculos disponibles");
    		JOptionPane.showMessageDialog(null,"No hay mas vehiculos disponibles");
    		*/
		}
		
	}
	
	//Metodo para buscar registro
	public void buscarRegistro() {
		try{
        	int nroRegistro = Integer.parseInt(txtNumeroRegistro.getText());
        	buscarPagar =registro.buscarRegistro(nroRegistro);
        	if(buscarPagar.isActivo()) {
    	        JOptionPane.showMessageDialog(null, "Se encontró registro: "+buscarPagar.getNroRegistro());
    	        encenderPanelKilometros();
        	}
        	else {
        		JOptionPane.showMessageDialog(null, "Registro concluido");
        	}
        	
        }
        catch (Exception ex) {
        	System.out.println(ex);
        	JOptionPane.showMessageDialog(null, "No se encontró el registro","Error: "+ex,JOptionPane.ERROR_MESSAGE);
		}
	}
	
	//Metodo para calcular monto a pagar
	public void calcularPago() {
		try {
			vehiculoPagar = vehiculos.obtenerXCOdigo(buscarPagar.getCodigoVehiculo());
			int kmRecorridos = Integer.parseInt(txtKiloRecorridos.getText());
			int kmAnterior = vehiculoPagar.getKilometraje();
			int kmActual = kmRecorridos-kmAnterior;
	    	if(kmActual>=0) {
				encenderPanelDetalle();
		    	llenarDetalle(kmAnterior,kmActual);
			}
			else {
				JOptionPane.showMessageDialog(null, "Km ingresados incorrectamente","Error de km", JOptionPane.WARNING_MESSAGE);
			}
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null, "Ingresa numeros validos.");
		}
	}
	
	//Metodo para llenar los campos del detalle
	public void llenarDetalle(int kmI, int kmR) {
		double montoPagar = Pagar.calcularMontoAPagar(kmR);
		double subtotal = Pagar.calcularSubtotal(montoPagar);
		lblNroRegistroDetalle.setText(String.valueOf(buscarPagar.getNroRegistro()));
		lblClienteDetalle.setText(buscarPagar.getCliente());
		lblPlacaDetalle.setText(buscarPagar.getPlaca());
		lblCodigoDetalle.setText(String.valueOf(buscarPagar.getCodigoVehiculo()));
		lblFechaRegistroDetalle.setText(String.valueOf(buscarPagar.getFechaRegistro()));
		lblFechaDevolucionDetalle.setText(String.valueOf(buscarPagar.getFechaDevolucion()));
		lblKmInicialesDetalle.setText(String.valueOf(kmI)+" km");
		lblKmRecorridosDetalle.setText(String.valueOf(kmR)+" km");
		lblTotalDetalle.setText("S/."+String.format("%.2f",montoPagar));
		lblSubtotalDetalle.setText("S/."+String.format("%.2f",subtotal));
		lblIGVDetalle.setText("S/."+String.format("%.2f",montoPagar-subtotal));
	}
	
	//Metodo para limpiar detalle
	public void limpiarDetalle() {
		lblNroRegistroDetalle.setText("");
		lblClienteDetalle.setText("");
		lblPlacaDetalle.setText("");
		lblCodigoDetalle.setText("");
		lblFechaRegistroDetalle.setText("");
		lblFechaDevolucionDetalle.setText("");
		lblKmInicialesDetalle.setText("");
		lblKmRecorridosDetalle.setText("");
		lblTotalDetalle.setText("");
		lblSubtotalDetalle.setText("");
		lblIGVDetalle.setText("");
	}
	
	//Metodo para afectuar pago
	public void efectuarPago() {
		try {
			int numReg = buscarPagar.getNroRegistro();
			String cliente = buscarPagar.getCliente();
			String placa = buscarPagar.getPlaca();
			int kmRec = ((Integer.parseInt(txtKiloRecorridos.getText())- vehiculoPagar.getKilometraje()));
			LocalDate fechaAl = buscarPagar.getFechaRegistro();
			LocalDate fechaPag = LocalDate.now();
			double total = quitarSimbolos(lblTotalDetalle.getText());
			double subT = quitarSimbolos(lblSubtotalDetalle.getText());
			double igv = quitarSimbolos(lblIGVDetalle.getText());
			historial = new Historial(numReg, cliente, placa, kmRec, fechaAl, fechaPag, total, subT, igv);
			historiales.agregarHistorial(historial);
			agregarFilaTablaHistorial(historial);
			eliminarFilaTablaRegistro(buscarPagar);
			registro.buscarRegistro(buscarPagar.getNroRegistro()).setActivo(false);
			registro.guardar();
			vehiculos.obtenerXCOdigo(vehiculoPagar.getCodigo()).setDisponible(true);
			vehiculos.obtenerXCOdigo(vehiculoPagar.getCodigo()).setKilometraje(Integer.parseInt(txtKiloRecorridos.getText()));
			vehiculos.obtener(0).setDisponible(false);
			vehiculos.guardar();
			setVehiculo(vehiculos.primerVehiculoDispnible().getCodigo());
			apagarPanelDetalle();
	    	apagarPanelKilometros();
	    	lblNroRegistro.setText(String.valueOf(calcularNumeroDeRegistros()));
	    	JOptionPane.showInternalMessageDialog(null, "El pago del alquiler se completado con exito","Pago completado",JOptionPane.INFORMATION_MESSAGE);
		}
		catch(Exception ex) {
			JOptionPane.showInternalMessageDialog(null, "No se pudo efectuar el pago","Error de pago",JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
	
	//Metodo para eliminar los simbolos
	public double quitarSimbolos(String x) {
		x = x.replace("S/.", "");
		x = x.replace(",", ".");
		x = x.trim();
		return Double.parseDouble(x);
	}
	
	//Metodo para llenar tabla historial
	public void llenarTablaHistorial() {
		for(int i=0;i<historiales.tamanio();i++) {
			Historial hst = historiales.obtener(i);
			agregarFilaTablaHistorial(hst);
		}
	}
	
	//Meotodo para añadir historial a la tabla historial
	public void agregarFilaTablaHistorial(Historial h) {
		mdlHistorial.addRow(historiales.convertirAObjeto(h));
	}
	
	//Metodo para llenar tabla de Reporte
	public void llenarTablaReporte() {
		for(int i= 0; i<registro.tamanio(); i++) {
			Registro rt = registro.obtener(i);
			if(rt.isActivo()) {
				agregarFilaTablaRegistro(rt);
			}
		}
	}
	
	//Metodo para agregar fila en la tabla registro
	public void agregarFilaTablaRegistro(Registro r) {
		mdlRegistro.addRow(registro.crearObjRegistro(r));
	}
	
	//Metodo para eliminar una fila de la tabla registro
	public void eliminarFilaTablaRegistro(Registro r) {
		for(int i = 0; i <mdlRegistro.getRowCount();i++) {
			int filaNro = (int) mdlRegistro.getValueAt(i,0);
			if(filaNro== r.getNroRegistro()) {
				mdlRegistro.removeRow(i);
			}
		}
	}
	
	//Metodo para llenar tabla de usuarios
	public void llenarTablaUsuarios() {
		for(int i = 0; i<usuarios.getTamanio(); i++) {
			Usuario ut = usuarios.getUsuario(i);
			agregarFilaTablaUsuario(ut);
		}
	}
	
	//Metodo para agregar una fila a la tabla usuarios
	public void agregarFilaTablaUsuario(Usuario u) {
		String roltemporal = (u.isAdmin()) ? "Administrador" : "Usuario";
		mdlListaUsuarios.addRow(new Object[]{u.getNombre(),u.getUser(),roltemporal});
	}
	
	//Metodo para registrar nuevo usuario
	public void registrarUsuario() {
		try {
			String nombre = txtNombreNuevoUsuario.getText();
			String usuarioN = txtUsuarioNuevo.getText();
			String passN = new String(pswPassNuevo.getPassword());
			String passRepN = new String(pswPassNuevoRe.getPassword());
			boolean  admin= (rbtnAdministrador.isSelected())?true:false;
			
			if(passN.equals(passRepN)) {
				Usuario u = new Usuario(usuarioN, passRepN, nombre, admin);
				
				if(!usuarios.existeUsuario(u)) {
					usuarios.agregar(u);
					agregarFilaTablaUsuario(u);
					JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente");
					apagarPanelRegistrarUsuario();
					encenderPanelListaUsuario();
				}
				else {
					JOptionPane.showMessageDialog(null, "Usuario ya existe en el sistema");
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Contraseñas distintas","Error de validación", JOptionPane.ERROR_MESSAGE);
			}
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null, "Ocurrió un error");
		}
	}
	
	//Metodo para verificar la modificacion
	public void verificarParaModificar() {
		try {
    		int filaSel = tblListaUsuarios.getSelectedRow();
    		String us = String.valueOf(mdlListaUsuarios.getValueAt(filaSel, 1));
			Usuario ub = usuarios.getUsuarioXUsuario(us);
    		if(!ub.getUser().equals("admin")) {
    			if(filaSel!=-1) {
    				apagarPanelListaUsuario();
    				encenderPanelModificarUsuario();
    				lblNombreUsuarioMod.setText(ub.getUser());
    			}
    		}
    		else {
    			JOptionPane.showMessageDialog(null, "No puedes modificar este usuario");
    		}
    	}
    	catch(Exception ex) {
    		JOptionPane.showMessageDialog(null, "Selecciona un usuario para modificar");
    	}
	}
	
	//Metodo para modificar usuario
	public void modificarUsuario() {
		try {
			int filaSel = tblListaUsuarios.getSelectedRow();
			String us = String.valueOf(mdlListaUsuarios.getValueAt(filaSel, 1));
			Usuario ub = usuarios.getUsuarioXUsuario(us);
			
			if(!ub.getUser().equals("admin")) {
				if(filaSel!=-1) {
					
					String pswA = new String(pswContrasenaAnt.getPassword());
					String pswN = new String(pswContraNueva.getPassword());
					String pswNR= new String(pswContraNuevaRep.getPassword());
					
					if(!pswA.equals("")) {
						if(!pswN.equals("")) {
							if(!pswNR.equals("")) {
								if(pswA.equals(ub.getPass())) {
									if(pswN.equals(pswNR)) {
										int rmv = JOptionPane.showConfirmDialog(null, "¿Desea modificar al usuario "+ us+"?", "Confirmar", JOptionPane.YES_NO_OPTION);
										if(rmv ==JOptionPane.YES_NO_OPTION) {
											ub.setPass(pswN);
											System.out.println(pswN);
											usuarios.reemplazarUsuario(ub);
											JOptionPane.showMessageDialog(null, "Contraseña actualizada correctamente");
											apagarPanelModificarUsuario();
											encenderPanelListaUsuario();
										}
									}
									else {
										JOptionPane.showMessageDialog(null, "Contraseñas nos son iguales.");
									}
								}
								else {
									JOptionPane.showMessageDialog(null, "Contraseña incorrecta.");
								}
							}
							else {
								JOptionPane.showMessageDialog(null, "Confirmación de contraseña nueva vacía");
							}
						}
						else {
							JOptionPane.showMessageDialog(null, "Contraseña nueva vacía");
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Campo vacío");
					}
				}
			}
			
		}
		catch(Exception ex) {
			
		}
	}
	
	//Metodo para eliminar usuario
	public void eliminarUsuario() {
		try {
			int filaSel = tblListaUsuarios.getSelectedRow();
			String us = String.valueOf(mdlListaUsuarios.getValueAt(filaSel, 1));
			Usuario ub = usuarios.getUsuarioXUsuario(us);
			
			if(!ub.getUser().equals("admin")) {
				if(filaSel!=-1) {
					int rmv = JOptionPane.showConfirmDialog(null, "¿Desea eliminar al usuario "+ us + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
					if(rmv ==JOptionPane.YES_NO_OPTION) {
						mdlListaUsuarios.removeRow(filaSel);
						usuarios.eliminar(ub);
						JOptionPane.showMessageDialog(null, "Usuario elimiado con exito");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "No sepueden eliminar mas usuarios");
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "No puedes eliminar al administrador principal del sistema");
			}
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null, "Selecciona un usuario para eliminar");
		}
	}
	
	
	
	//************************************************************************************************************************************************************
	//Acciones de los Botones
	//************************************************************************************************************************************************************
	@Override
	public void actionPerformed(ActionEvent e) {
	    if(e.getSource() == btnRegistrar) {
	        registrar();
	    }
	    
	    if(e.getSource() == btnBuscarRegistro) {
	        buscarRegistro();	        
	    }
	    
	    if(e.getSource() == btnIngresarKilometros) {
	    	calcularPago();
	    }
	    
	    if(e.getSource() == btnPagar) {
	    	efectuarPago();
	    }
	    
	    if(e.getSource() == btnAnular) {
	    	apagarPanelDetalle();
	    	apagarPanelKilometros();
	    }
	    
	    if(e.getSource() == cboTipoDocumento) {
	    	if(cboTipoDocumento.getItemAt(0).equals("")) {
	    		cboTipoDocumento.removeItemAt(0);
	    		txtNroDocumento.setEnabled(true);
	    	}
	    	comprobarTipoDocumento();
	    }
	    
	    if(e.getSource()==btnAnterior) {
	    	anteriorVehiculo();
	    	
	    }
	    if(e.getSource()==btnSiguiente) {
	    	siguienteVehiculo();
	    }
	    
	    if(e.getSource()==btnAnadir) {
	    	encenderPanelRegistrarUsuario();
	    	apagarPanelListaUsuario();
	    }
	    
	    if(e.getSource()==btnCancelar) {
	    	apagarPanelRegistrarUsuario();
	    	encenderPanelListaUsuario();
	    }
	    
	    if(e.getSource()==btnRegistrarUsuario) {
	    	registrarUsuario();
	    }
	    
	    if(e.getSource()==btnModificar) {
	    	verificarParaModificar();
	    }
	    
	    if(e.getSource()==btnCancelarMod) {
	    	apagarPanelModificarUsuario();
	    	encenderPanelListaUsuario();
	    }
	    
	    if(e.getSource()==btnConfirmarMod) {
	    	modificarUsuario();
	    }
	    if(e.getSource()==btnEliminar) {
	    	eliminarUsuario();
	    }
	    
	}
	//************************************************************************************************************************************************************
	//Accion del Snniper
	//************************************************************************************************************************************************************
	@Override
	public void stateChanged(ChangeEvent e) {
		//Al cambiar el spinner
		if(e.getSource() == snpDiasAlquiler) {
			LocalDate devolucion = calcularFechaDevolucion();
			lblFechaDevolucion.setText(devolucion.format(formatoFecha));
	    }
		
		//Al cambiar de panel en JTable- especificamente al registrar usuario
		if(e.getSource()==tabbedPane) {
			if(tabbedPane.getSelectedIndex()==2) {
				setSize(755,720);
				setLocationRelativeTo(null);
			}
			else {
				setSize(1280,720);
				setLocationRelativeTo(null);
			}
		}
	}
	
	//************************************************************************************************************************************************************
	//Accion de Presionar teclas
	//************************************************************************************************************************************************************
	
	//Cuando tipean una tecla
	//*******************************************
	@Override
	public void keyTyped(KeyEvent e) {
		if(e.getSource() == txtNroLicencia) {
			filtrarSerie(e);
			limitarDigitos(e, txtNroLicencia, 9);
	    }
		if(e.getSource() == txtNroDocumento) {
			filtrarNumeros(e);
			limitarDigitos(e, txtNroDocumento, digitosDocumento);
	    }
		if(e.getSource() == txtTelefono) {
			filtrarNumeros(e);
			limitarDigitos(e, txtTelefono, 9);
	    }
		if(e.getSource() == txtApellidos) {
			filtrarLetras(e);
	    }
		if(e.getSource() == txtEmail) {
			filtrarFormatoCorreo(e);
		}
		if(e.getSource() == txtNumeroRegistro) {
			if(e.getKeyChar()== KeyEvent.VK_ENTER) {
				btnBuscarRegistro.doClick();
			}
			else {
				filtrarNumeros(e);
				limitarDigitos(e, txtNumeroRegistro, 10);
			}
		}
		
		if(e.getSource() == txtKiloRecorridos) {
			if(e.getKeyChar()== KeyEvent.VK_ENTER) {
				btnIngresarKilometros.doClick();
			}
			else {
				filtrarNumeros(e);
			}
		}
	}
	
	//Cuando tienen presionada una tecla
	//*******************************************
	@Override
	public void keyPressed(KeyEvent e) {
		
	}
	
	//cuando dejan de presionar una tecla
	//********************************************
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getSource() == txtApellidos) {
			txtApellidos.setText(txtApellidos.getText().toUpperCase());
	    }
		if(e.getSource() == txtDireccion) {
			txtDireccion.setText(txtDireccion.getText().toUpperCase());
	    }
	}
}
