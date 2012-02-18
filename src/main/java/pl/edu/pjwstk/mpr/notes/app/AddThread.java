package pl.edu.pjwstk.mpr.notes.app;


import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class AddThread extends JDialog implements ActionListener, WindowListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCategoryname;
	private JButton okButton, cancelButton;
	private JFrame own;
	private MainWindow mw;
	public AddThread(MainWindow mw) {
		this.mw = mw;
		mw.frame.setEnabled(false);
	
		
		
		addWindowListener(this);
		setTitle("New Thread");
		setBounds(100, 100, 444, 116);
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
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 43, 446, 38);
			contentPanel.add(buttonPane);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
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
			try {
				mw.s.addThread(txtCategoryname.getText(), ((Category)mw.select_category.getSelectedItem()).getId());
			} catch (MySQLIntegrityConstraintViolationException e1) {
				 JOptionPane.showMessageDialog(this,"Thread already exists!");
			}
			mw.fillThreadList();
		}else if(e.getSource().equals(cancelButton)){
			dispose();
			mw.frame.setEnabled(true);
		}
	}


	public void windowActivated(WindowEvent arg0) {
	
		
	}


	public void windowClosed(WindowEvent arg0) {
		
		
	}

	public void windowClosing(WindowEvent arg0) {
		dispose();
		mw.frame.setEnabled(true);
		
	}

	public void windowDeactivated(WindowEvent arg0) {
		
		
	}


	public void windowDeiconified(WindowEvent arg0) {
	
		
	}


	public void windowIconified(WindowEvent arg0) {
	
		
	}


	public void windowOpened(WindowEvent arg0) {
		
		
	}
}
