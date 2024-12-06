package Vista;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Controlador.ArrayUsuario;
import Modelo.Usuario;

import java.awt.event.*;

public class Login extends JFrame implements ActionListener, KeyListener{

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtLoginUsuario;
    private JPasswordField passwordField;
    private JButton btnLoginIniciarSesion;
    private String nombreUsuario;
    
    ArrayUsuario usuarios = new ArrayUsuario();
   
    
    //Constructor en el cual aÃ±adimos los componentes del frame
    public Login() {
        setResizable(false);
        setTitle("Iniciar Sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 720);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setLocationRelativeTo(null);
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panelPrincipalLogin = new JPanel();
        panelPrincipalLogin.setBounds(0, 0, 484, 681);
        panelPrincipalLogin.setBackground(new Color(166, 0, 0));
        contentPane.add(panelPrincipalLogin);
        panelPrincipalLogin.setLayout(new BorderLayout(0, 0));

        JPanel panelLogin = new JPanel();
        panelPrincipalLogin.add(panelLogin, BorderLayout.CENTER);

       
        panelLogin.setLayout(new BorderLayout(0, 0));

        JLabel lblLogoLogin = new JLabel();
        lblLogoLogin.setIcon(new ImageIcon("src/Imagenes/logo-steelBlue.png"));
        panelLogin.add(lblLogoLogin, BorderLayout.NORTH);

        JPanel panelInterfaz = new JPanel();
        panelInterfaz.setBackground(new Color(176, 196, 222));
        panelLogin.add(panelInterfaz, BorderLayout.CENTER);
        panelInterfaz.setLayout(null);

        JLabel lblLoginIniciarSesion = new JLabel("INICIAR SESIÓN");
        lblLoginIniciarSesion.setHorizontalAlignment(SwingConstants.CENTER);
        lblLoginIniciarSesion.setFont(new Font("Arial", Font.BOLD, 28));
        lblLoginIniciarSesion.setForeground(new Color(0, 0, 0));
        lblLoginIniciarSesion.setBounds(72, 27, 339, 40);
        panelInterfaz.add(lblLoginIniciarSesion);

        JLabel lblLoginUsuario = new JLabel("Usuario:");
        lblLoginUsuario.setHorizontalAlignment(SwingConstants.LEFT);
        lblLoginUsuario.setForeground(new Color(0, 0, 0));
        lblLoginUsuario.setFont(new Font("Arial", Font.BOLD, 25));
        lblLoginUsuario.setBounds(72, 225, 140, 30);
        panelInterfaz.add(lblLoginUsuario);

        txtLoginUsuario = new JTextField();
        txtLoginUsuario.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtLoginUsuario.setBounds(72, 266, 339, 30);
        txtLoginUsuario.addKeyListener(this);
        panelInterfaz.add(txtLoginUsuario);
        txtLoginUsuario.setColumns(10);

        JLabel lblLoginContra = new JLabel("Contraseña:");
        lblLoginContra.setHorizontalAlignment(SwingConstants.LEFT);
        lblLoginContra.setForeground(new Color(0, 0, 0));
        lblLoginContra.setFont(new Font("Arial", Font.BOLD, 25));
        lblLoginContra.setBounds(73, 331, 182, 30);
        panelInterfaz.add(lblLoginContra);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 20));
        passwordField.setBounds(72, 372, 339, 30);
        passwordField.addKeyListener(this);
        panelInterfaz.add(passwordField);

        btnLoginIniciarSesion = crearBoton("INGRESAR");
        btnLoginIniciarSesion.setBounds(166, 429, 150, 40);
        btnLoginIniciarSesion.addActionListener(this);
        panelInterfaz.add(btnLoginIniciarSesion);
        
        JLabel lblLogo = new JLabel("");
        lblLogo.setIcon(new ImageIcon("src/Imagenes/avatar-150x150.png"));
        lblLogo.setBounds(166, 68, 150, 150);
        panelInterfaz.add(lblLogo);
        
    }
    
    //Metodo para Iniciar sesión y lanzar la Ventana Principal
    public void iniciarVentanaPrincipal(String nUsuario, String nCompletoUsuario) {
    	
    	String usuario = nUsuario;
    	String nombreUsuario = nCompletoUsuario;
    	
    	JOptionPane.showMessageDialog(null, "Bienvenido(a) " + usuario + ", iniciaste sesión exitosamente.","Inicio de sesión",JOptionPane.INFORMATION_MESSAGE);
        
        // Crear e iniciar la ventana principal
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frmAlquilerVehiculos ventana = new frmAlquilerVehiculos(usuario, nombreUsuario);
                    ventana.setVisible(true);
                    System.out.println("Iniciaste sesión.");
                    System.out.println("Abriendo programa principal.");
                     //Cierra la ventana de inicio de sesión
                    dispose();
                    System.out.println("Login cerrado.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    
    //Metodo para iniciar sesion
    private void iniciarSesion() {
		String usuario = txtLoginUsuario.getText();
		String contrasena = new String(passwordField.getPassword());
		Usuario u = usuarios.getUsuarioXUsuario(usuario);
		if(!usuario.isEmpty()) {
			if(!contrasena.isEmpty()) {
				if(u!=null) {
					if(usuario.equals(u.getUser())) {
						if(contrasena.equals(u.getPass())) {
							nombreUsuario = u.getNombre();
							iniciarVentanaPrincipal(usuario, nombreUsuario);
						}
						else {
							JOptionPane.showMessageDialog(null, "Contraseña incorrecta.", "Error de Inicio de Sesión", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "No se encontró usuario.", "Error de Inicio de Sesión", JOptionPane.ERROR_MESSAGE);
				}
			}
			else {
				System.out.println("Contraseña vacía");
				JOptionPane.showMessageDialog(null, "Por favor ingrese una contraseña.", "Error de Inicio de Sesión", JOptionPane.ERROR_MESSAGE);
			}
		}
		else {
			System.out.println("Usuario vacío.");
			JOptionPane.showMessageDialog(null, "Usuario vacío.", "Error de Inicio de Sesión", JOptionPane.ERROR_MESSAGE);
		}
	}
    
    //Metodo para crear boton
    private JButton crearBoton(String texto) {
        JButton btnIngresar = new JButton(texto);
        btnIngresar.setFont(new Font("Arial", Font.BOLD, 20));
        btnIngresar.setBackground(new Color(255, 255, 255));
        btnIngresar.setForeground(new Color(0, 0, 0));
        btnIngresar.setBorder(new LineBorder(new Color(0, 0, 0), 1));
        btnIngresar.setFocusPainted(false);
        btnIngresar.setContentAreaFilled(true);
        btnIngresar.setOpaque(true);
        return btnIngresar;
    }
    
    //Action event
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnLoginIniciarSesion) {
			iniciarSesion();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
	@Override
	public void keyPressed(KeyEvent e) {
		//Configurar Enter para iniciar sesión
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
        	iniciarSesion();
        }
	}
	@Override
	public void keyReleased(KeyEvent e) {
	}
}
