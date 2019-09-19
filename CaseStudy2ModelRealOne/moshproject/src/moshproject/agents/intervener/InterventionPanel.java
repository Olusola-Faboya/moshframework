package moshproject.agents.intervener;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.jogamp.newt.event.KeyEvent;

public class InterventionPanel extends JPanel{

	public InterventionPanel(Intervener intervener){		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();		
		 
		JLabel name = new JLabel ("Select Travellers' Concerns:");
		c.insets=new Insets(0, 0, 0, 0);		
		c.gridx=0;
		c.gridy=0;
		c.anchor=GridBagConstraints.NORTHWEST;		
		 add(name,c);
//1)SideWalks		
		JCheckBox sideWalks =new JCheckBox("SideWalks/Paths:");		
		c.fill =GridBagConstraints.HORIZONTAL;
		c.weightx=0.5;
		c.gridx=0;
		c.gridy=1;		
		sideWalks.setMnemonic(KeyEvent.VK_S);
		sideWalks.setSelected(false);		
		sideWalks.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JCheckBox checkBox =(JCheckBox)e.getSource();					
				intervener.setSideWalksStatus(checkBox.isSelected());		
			}
		});
		add(sideWalks, c);

//2)Road Crossing		
		JCheckBox roadCrossing =new JCheckBox("Road Crossing:");		
		c.fill =GridBagConstraints.HORIZONTAL;
		c.weightx=0.5;
		c.gridx=1;
		c.gridy=1;		
		roadCrossing.setMnemonic(KeyEvent.VK_C);
		roadCrossing.setSelected(false);		
		roadCrossing.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
				JCheckBox checkBox =(JCheckBox)e.getSource();					
				intervener.setRoadCrossingStatus(checkBox.isSelected());		
			}
		});
		add(roadCrossing, c);

//3) Journey Time		
		c.fill =GridBagConstraints.HORIZONTAL;
		c.gridx =0;
		c.gridy =2;
		JCheckBox journey =new JCheckBox("Journey Time:");
		journey.setMnemonic(KeyEvent.VK_J);
		journey.setSelected(false);
		journey.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JCheckBox checkBox =(JCheckBox)e.getSource();
				intervener.setJourneyTimeStatus(checkBox.isSelected());				
			}
		});
		add(journey, c);	
//4) Others Attitude		
		c.fill =GridBagConstraints.HORIZONTAL;
		c.gridx =1;
		c.gridy =2;
		JCheckBox othersAttitude =new JCheckBox("Others Attitude:");
		othersAttitude.setMnemonic(KeyEvent.VK_A);
		othersAttitude.setSelected(false);
		othersAttitude.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JCheckBox checkBox =(JCheckBox)e.getSource();
				intervener.setAttitudeOfOthersStatus(checkBox.isSelected());				
			}
		});
		add(othersAttitude, c);	
//5)Route Availability
		c.fill =GridBagConstraints.HORIZONTAL;
		c.gridx =0;
		c.gridy =3;
		JCheckBox routeAvailability =new JCheckBox("Route Availability:");
		routeAvailability.setMnemonic(KeyEvent.VK_R);
		routeAvailability.setSelected(false);
		routeAvailability.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JCheckBox checkBox =(JCheckBox)e.getSource();
				intervener.setRouteAvailabilityStatus(checkBox.isSelected());				
			}
		});
		add(routeAvailability, c);
//6)facilities at Destination			
		c.fill =GridBagConstraints.HORIZONTAL;
		c.gridx =1;
		c.gridy =3;
		JCheckBox facilitiesAtDest =new JCheckBox("Facilities At Destination:");
		facilitiesAtDest.setMnemonic(KeyEvent.VK_F);
		facilitiesAtDest.setSelected(false);
		facilitiesAtDest.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JCheckBox checkBox =(JCheckBox)e.getSource();
				intervener.setFacilitiesAtDestStatus(checkBox.isSelected());				
			}
		});
		add(facilitiesAtDest, c);
//		
//7)Luggage Carrier		
		c.fill =GridBagConstraints.HORIZONTAL;
		c.gridx =0;
		c.gridy =4;
		JCheckBox luggageCarrier =new JCheckBox("Luggage Carrier:");
		luggageCarrier.setMnemonic(KeyEvent.VK_L);
		luggageCarrier.setSelected(false);
		luggageCarrier.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JCheckBox checkBox =(JCheckBox)e.getSource();
				intervener.setLuggageCarrierStatus(checkBox.isSelected());				
			}
		});
		add(luggageCarrier, c);

		JButton interventionButton = new JButton("Non-Active Travellers'Interventions");
		c.gridx=0;
		c.gridy=6;	
		interventionButton.addActionListener( new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				intervener.applyIntervention();
			}			
		});
		add(interventionButton,c);
////
		
//////
		JButton resetButton = new JButton("Reset Button");
		c.gridx=1;
		c.gridy=6;	
		resetButton.addActionListener( new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				sideWalks.setSelected(false);
				roadCrossing.setSelected(false);
				othersAttitude.setSelected(false);
				routeAvailability.setSelected(false);
				facilitiesAtDest.setSelected(false);
				journey.setSelected(false);
				luggageCarrier.setSelected(false);
				intervener.reset();
			
			}
			
		});
		add(resetButton,c);
		

	}
}
