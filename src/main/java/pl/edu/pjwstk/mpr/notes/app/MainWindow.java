package pl.edu.pjwstk.mpr.notes.app;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JMenuBar;

public class MainWindow implements ActionListener, KeyListener {

	public JFrame frame;
	public JComboBox select_category, select_thread;
	public Storage s;
	private JButton btnAddThread, btnAddCategory, btnDeleteCategory,
			btnDeleteThread, btnSendmessage;
	private JTextPane txtpnMessage;
	private JEditorPane jEditorPane;
	private HTMLEditorKit kit;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainWindow() {
		initialize();

		s = new Storage();
		fillCategoryList();
		fillThreadList();
		fillThreadView();
	}

	private void initialize() {
		frame = new JFrame("Notes");
		frame.getContentPane().setBackground(UIManager.getColor("OptionPane.errorDialog.border.background"));
		frame.getContentPane().setForeground(new Color(128, 128, 128));
		frame.setResizable(false);
		frame.setBounds(100, 100, 691, 474);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		select_category = new JComboBox();
		select_category.setBounds(89, 12, 462, 24);
		select_category.addActionListener(this);
		frame.getContentPane().add(select_category);

		btnAddCategory = new JButton("+");
		btnAddCategory.setBounds(563, 12, 44, 25);
		btnAddCategory.addActionListener(this);
		frame.getContentPane().add(btnAddCategory);

		select_thread = new JComboBox();
		select_thread.setBounds(89, 48, 462, 24);
		select_thread.addActionListener(this);
		frame.getContentPane().add(select_thread);

		btnAddThread = new JButton("+");
		btnAddThread.setBounds(563, 48, 44, 24);
		btnAddThread.addActionListener(this);
		frame.getContentPane().add(btnAddThread);

	

		txtpnMessage = new JTextPane();
		txtpnMessage.setBounds(68, 380, 607, 55);
		txtpnMessage.addKeyListener(this);
		frame.getContentPane().add(txtpnMessage);

		btnSendmessage = new JButton("+");
		btnSendmessage.setBounds(12, 380, 44, 55);
		btnSendmessage.addActionListener(this);
		frame.getContentPane().add(btnSendmessage);

		JLabel lblCategory = new JLabel("Category");
		lblCategory.setBounds(12, 17, 70, 15);
		frame.getContentPane().add(lblCategory);

		JLabel lblThread = new JLabel("Thread");
		lblThread.setBounds(12, 53, 70, 15);
		frame.getContentPane().add(lblThread);

		btnDeleteCategory = new JButton("-");
		btnDeleteCategory.setBounds(619, 12, 46, 25);
		btnDeleteCategory.addActionListener(this);
		frame.getContentPane().add(btnDeleteCategory);

		btnDeleteThread = new JButton("-");
		btnDeleteThread.setBounds(619, 49, 46, 25);
		btnDeleteThread.addActionListener(this);
		frame.getContentPane().add(btnDeleteThread);
		frame.setLocationRelativeTo(null);

		
		jEditorPane = new JEditorPane();
		jEditorPane.setEditable(false);
		kit = new HTMLEditorKit();
		jEditorPane.setEditorKit(kit);
		jEditorPane.setBounds(0, 0, 500, 200);
		Document doc = kit.createDefaultDocument();
		jEditorPane.setDocument(doc);
		StyleSheet styleSheet = kit.getStyleSheet();
		styleSheet.addRule("body {color:#000; font-family:times; margin: 4px; }");
		styleSheet.addRule("h1 {color: blue;}");
		styleSheet.addRule("h2 {color: #ff0000;}");
		styleSheet.addRule("pre {font : 10px monaco; color : black; background-color : #fafafa; }");
		
		
		JScrollPane jsp = new JScrollPane(jEditorPane);
		jsp.setBounds(12, 84, 663, 284);
		frame.getContentPane().add(jsp);
		
	}

	public void fillCategoryList() {
		ArrayList<Category> cl = s.getCategories();
		select_category.removeAllItems();
		for (Category ck : cl) {
			select_category.addItem(ck);
		}

	}

	public void fillThreadList() {
		select_thread.removeAllItems();
		Category ct = (Category) select_category.getSelectedItem();
		if (ct != null) {
			ArrayList<Thread> il = s.getThreadsFor(ct.getId());
			for (Thread i : il) {
				select_thread.addItem(i);
			}
		}
	}

	public void fillThreadView(){
		String htmlString = "<html>\n" + "<body>\n";
			
		if(select_thread.getSelectedItem() != null){
			ArrayList<Note> pl = s.getNotesFor(((Thread)select_thread.getSelectedItem()).getId());
			for(Note p : pl){
			
				htmlString += "<p>";
				htmlString += "<b>" + p.getCreated_at() +"</b>:  ";
				htmlString += p.getContent();
				htmlString += "</p><hr>";
			}
		}
		htmlString += "</body>\n</html>";
		jEditorPane.setText(htmlString);
			
			
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnAddCategory)) {
			AddCategory ac = new AddCategory(this);
			ac.setVisible(true);
			fillThreadView();

		} else if (e.getSource().equals(select_category)) {
			fillThreadList();
			fillThreadView();
		}else if (e.getSource().equals(select_thread)) {
			fillThreadView();
		}else if (e.getSource().equals(btnAddThread)) {
			AddThread ai = new AddThread(this);
			ai.setVisible(true);
		} else if (e.getSource().equals(btnDeleteCategory)) {
			s.deleteCategory(((Category) select_category.getSelectedItem())
					.getId());
			fillCategoryList();
			fillThreadList();
			fillThreadView();
		} else if (e.getSource().equals(btnDeleteThread)) {
			s.deleteThread(((Thread) select_thread.getSelectedItem()).getId());
			fillThreadList();
			fillThreadView();
		} else if (e.getSource().equals(btnSendmessage)) {
			SendMessage();
		}
	}

	public void SendMessage(){
		String msg = txtpnMessage.getText();
			s.addNoteFor(txtpnMessage.getText(), ((Thread) select_thread
					.getSelectedItem()).getId());
			fillThreadView();
			txtpnMessage.setText("");
		
	}
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			SendMessage();
		}
		
	}

	public void keyReleased(KeyEvent arg0) {
		
		
	}

	public void keyTyped(KeyEvent arg0) {
		
		
	}
}
