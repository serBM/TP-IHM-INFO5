package views;

import controller.Controller;
import controller.HomeController;
import model.Home;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

@SuppressWarnings("serial")
public class View extends JFrame {

	List<Home> list;
	List<Home> listCriteria;

	int aDistA = 1;
	int bDistA = 30;

	int aDistB = 1;
	int bDistB = 30;

	int aPrice = 50;
	int bPrice = 500;

	int aRooms = 1;
	int bRooms = 7;
		
	public View(List<Home> list) {

		this.list = list;

		setTitle("Range Slider");
		setSize(390, 700);
		setResizable(false);

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		JPanel infoPanel = new JPanel();
		createInfoPanel(list.size(), infoPanel);
		infoPanel.setPreferredSize(new Dimension(390, 30));
		panel.add(infoPanel);
		
		JPanel homesPanel = new JPanel();
		homesPanel.setPreferredSize(new Dimension(390, 700));
		
		JPanel rangeSlidersPanel = createRangeSliders(infoPanel, homesPanel);
		panel.add(rangeSlidersPanel);
		
		displayResults(list, homesPanel);
		
		panel.add(homesPanel);

		add(panel);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		setVisible(true);
	}

	/*
	 * displays the list of results into the homesPanel
	 */
	private void displayResults(List<Home> listResults, JPanel homesPanel) {
		for (int i = 0; i < listResults.size(); i++) {
			
			Home h = listResults.get(i);
			
			int price = h.getPrice();
			int distA = h.getDistToA();
			int distB = h.getDistToB();
			int nbRooms = h.getRooms();
			
			int num = list.indexOf(h);
			createHomes(num+1, price, distA, distB, nbRooms, homesPanel);
		}
		
	}

	/*
	 * displays the number of results availables
	 */
	private void createInfoPanel(int nb, JPanel infoPanel) {
		infoPanel.add(new JLabel("Nombre de biens disponibles : " + nb));
	}

	/*
	 * create the different range sliders
	 */
	private JPanel createRangeSliders(JPanel infoPanel, JPanel homesPanel) {

		JPanel rangeSliderDistAPanel = rangeSlider("distanceA", aDistA, bDistA, infoPanel, homesPanel);
		JPanel rangeSliderDistBPanel = rangeSlider("distanceB", aDistB, bDistB, infoPanel, homesPanel);
		JPanel rangeSliderRoomsPanel = rangeSlider("nbRooms", aRooms, bRooms, infoPanel, homesPanel);
		JPanel rangeSliderPriceBPanel = rangeSlider("price", aPrice, bPrice, infoPanel, homesPanel);

		JLabel distAText = new JLabel("Distance to A : ");
		JLabel distBText = new JLabel("Distance to B : ");
		JLabel roomsText = new JLabel("Number of rooms : ");
		JLabel priceText = new JLabel("Price : ");

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		panel.add(distAText);
		panel.add(rangeSliderDistAPanel);

		panel.add(distBText);
		panel.add(rangeSliderDistBPanel);

		panel.add(roomsText);
		panel.add(rangeSliderRoomsPanel);

		panel.add(priceText);
		panel.add(rangeSliderPriceBPanel);

		return panel;
	}

	/*
	 * create one range slider
	 * with listener on the TextField for the values
	 */
	private JPanel rangeSlider(String name, int a, int b, JPanel infoPanel, JPanel homesPanel) {

		String fromString = Integer.toString(a);
		String toString = Integer.toString(b);

		JTextField fromTextField = new JTextField(fromString);
		fromTextField.setPreferredSize(new Dimension(40, 20));
		fromTextField.setHorizontalAlignment(JTextField.CENTER);

		fromTextField.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				warn();
			}

			public void removeUpdate(DocumentEvent e) {
				warn();
			}

			public void insertUpdate(DocumentEvent e) {
				warn();
			}

			public void warn() {
				//get the values from the textField on the left

				if (!"".equals(fromTextField.getText())) {
					if (name == "distanceA") //range slider for the distance to A
						aDistA = Integer.parseInt(fromTextField.getText());
					if (name == "distanceB") //range slider for the distance to B
						aDistB = Integer.parseInt(fromTextField.getText());
					if (name == "price") //range slider for the price
						aPrice = Integer.parseInt(fromTextField.getText());
					if (name == "nbRooms") //range slider for the number of rooms
						aRooms = Integer.parseInt(fromTextField.getText());
				}
				updateViews(infoPanel, homesPanel);
			}
		});

		JTextField toTextField = new JTextField(toString);
		toTextField.setPreferredSize(new Dimension(40, 20));
		toTextField.setHorizontalAlignment(JTextField.CENTER);

		toTextField.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				warn();
			}

			public void removeUpdate(DocumentEvent e) {
				warn();
			}

			public void insertUpdate(DocumentEvent e) {
				warn();
			}

			public void warn() {
				//get the values from the textField on the right
				if (!"".equals(toTextField.getText())) {
					if (name == "distanceA")
						bDistA = Integer.parseInt(toTextField.getText());
					if (name == "distanceB")
						bDistB = Integer.parseInt(toTextField.getText());
					if (name == "price")
						bPrice = Integer.parseInt(toTextField.getText());
					if (name == "nbRooms")
						bRooms = Integer.parseInt(toTextField.getText());
				}
				
				updateViews(infoPanel, homesPanel);
			}
		});

		// creates the range slider and updates its values on the textfields
		Controller slider = new Controller(10, 10, 290, 10, 16, 16, fromTextField, toTextField);

		// creates a panel and add the textfields and slider to it
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(fromTextField, BorderLayout.WEST);
		panel.add(slider, BorderLayout.CENTER);
		panel.add(toTextField, BorderLayout.EAST);

		return panel;
	}

	/*
	 * create home into the homesPanel
	 */
	private void createHomes(int num, int price, int distA, int distB, int nb, JPanel homesPanel) {
		
		Box bv = Box.createVerticalBox();
	    
		JLabel numLabel = new JLabel("Number : " + num);
		JLabel priceLabel = new JLabel("Price : " + price);
		JLabel distanceBLabel = new JLabel("Distance to A : " + distA);
		JLabel distanceALabel = new JLabel("Distance to B : " + distB);
		JLabel nbRoomsLabel = new JLabel("Number of rooms : " + nb);
		
		bv.add(numLabel);
	    bv.add(priceLabel);
	    bv.add(distanceALabel);
	    bv.add(distanceBLabel);
	    bv.add(nbRoomsLabel);

		homesPanel.add(bv);
	}
	
	/*
	 * update the list of homes according to the new range sliders values
	 */
	private void updateViews(JPanel infoPanel, JPanel homesPanel) {
		//gets the list of homes that match the criteria
		listCriteria = HomeController.getListCriteria(list, aDistA, bDistA, aDistB, bDistB, aPrice, bPrice, aRooms, bRooms);
	
		//remove all components from infoPanel and homesPanel
		infoPanel.removeAll();				
		homesPanel.removeAll();

		//re create the info panel with the number of homes that match the criteria
		createInfoPanel(listCriteria.size(), infoPanel);
		//re create the homes panel with the list of homes that match the criteria
		displayResults(listCriteria, homesPanel);
		
		//update the views
		homesPanel.repaint();
		homesPanel.revalidate();
		
		infoPanel.repaint();
		infoPanel.revalidate();
	}

	public static void main(String[] args) {

		int nbHomes = 10;
		List<Home> list = HomeController.initRandomHomes(nbHomes);

		@SuppressWarnings("unused")
		View view = new View(list);
	}
}
