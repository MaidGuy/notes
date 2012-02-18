package pl.edu.pjwstk.mpr.notes.app;


import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextPane;

public class AddCategory extends JDialog implements ActionListener, WindowListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCategoryname;
	private JButton okButton, cancelButton;
	private JFrame own;
	private MainWindow mw;
	private JTextPane txtpnCatdesc;
	public AddCategory(MainWindow mw) {
		this.mw = mw;
		mw.frame.setEnabled(false);
	
		
		
		addWindowListener(this);
		setTitle("New Category");
		setBounds(100, 100, 450, 151);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 446, 81);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			txtCategoryname = new JTextField();
			txtCategoryname.setBounds(79, 12, 355, 19);
			contentPanel.add(txtCategoryname);
			txtCategoryname.setColumns(10);
		}
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(26, 14, 70, 15);
		contentPanel.add(lblName);
		
		txtpnCatdesc = new JTextPane();
		txtpnCatdesc.setBounds(79, 37, 355, 44);
		contentPanel.add(txtpnCatdesc);
		
		JLabel lblDesc = new JLabel("Desc:");
		lblDesc.setBounds(26, 43, 70, 15);
		contentPanel.add(lblDesc);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 86, 446, 38);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(this);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(this);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		setLocationRelativeTo(null);
		setResizable(false);
	}

	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(okButton)){
			dispose();
			mw.frame.setEnabled(true);
			mw.s.addCategory(txtCategoryname.getText(), txtpnCatdesc.getText());
			mw.fillCategoryList();
			mw.fillThreadList();
		}else if(e.getSource().equals(cancelButton)){
			dispose();
			mw.frame.setEnabled(true);
		}
	}


	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void windowClosing(WindowEvent arg0) {
		dispose();
		mw.frame.setEnabled(true);
		
	}

	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
