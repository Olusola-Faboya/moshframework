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
		 int min =0;
		 int max=5;
		 int initial =0;
		
		JLabel name = new JLabel ("Select Measures:");
		c.insets=new Insets(0, 0, 0, 0);		
		c.gridx=0;
		c.gridy=0;
		c.anchor=GridBagConstraints.NORTHWEST;		
		 add(name,c);
//1)Reliability		
		JCheckBox reliability =new JCheckBox("Reliability:");		
		c.fill =GridBagConstraints.HORIZONTAL;
		c.weightx=0.5;
		c.gridx=0;
		c.gridy=1;		
		reliability.setMnemonic(KeyEvent.VK_R);
		reliability.setSelected(false);		
		reliability.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JCheckBox checkBox =(JCheckBox)e.getSource();					
				intervener.setReliabiltyStatus(checkBox.isSelected());		
			}
		});
		add(reliability, c);
			
		JSlider reliabilitySlide = new JSlider(JSlider.HORIZONTAL,min,max,initial);
		c.gridx =1;
		c.gridy =1;
		reliabilitySlide.setMajorTickSpacing(1);
		reliabilitySlide.setMinorTickSpacing(1);
		reliabilitySlide.setPaintTicks(true);
		reliabilitySlide.setPaintLabels(true);
		
		reliabilitySlide.addChangeListener(new ChangeListener() {			
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
			    if (!source.getValueIsAdjusting()) {
			        intervener.setReliability((double)source.getValue());			
			    }
			}
		});
		add(reliabilitySlide,c);
//2) Journey Time		
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

		JSlider journeyTimeSlide = new JSlider(JSlider.HORIZONTAL,min,max,initial);
		c.gridx =1;
		c.gridy =2;
		journeyTimeSlide.setMajorTickSpacing(1);
		journeyTimeSlide.setMinorTickSpacing(1);
		journeyTimeSlide.setPaintTicks(true);
		journeyTimeSlide.setPaintLabels(true);
		
		journeyTimeSlide.addChangeListener(new ChangeListener() {			
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
			    if (!source.getValueIsAdjusting()) {
			        intervener.setJourneyTime((double)source.getValue());			
			    }
			}
		});
		add(journeyTimeSlide,c);	
//3)
		c.fill =GridBagConstraints.HORIZONTAL;
		c.gridx =0;
		c.gridy =3;
		JCheckBox costs =new JCheckBox("Value For Money:");
		costs.setMnemonic(KeyEvent.VK_V);
		costs.setSelected(false);
		costs.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JCheckBox checkBox =(JCheckBox)e.getSource();
				intervener.setSecurityStatus(checkBox.isSelected());				
			}
		});
		add(costs, c);
		
		JSlider costsSlide = new JSlider(JSlider.HORIZONTAL,min,max,initial);
		c.gridx =1;
		c.gridy =3;
		costsSlide.setMajorTickSpacing(1);
		costsSlide.setMinorTickSpacing(1);
		costsSlide.setPaintTicks(true);
		costsSlide.setPaintLabels(true);
		
		costsSlide.addChangeListener(new ChangeListener() {			
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
			    if (!source.getValueIsAdjusting()) {
			        intervener.setValueForMoney((double)source.getValue());			
			    }
			}
		});
		add(costsSlide,c);
//4)
		c.fill =GridBagConstraints.HORIZONTAL;
		c.gridx =0;
		c.gridy =4;
		JCheckBox security =new JCheckBox("Security:");
		security.setMnemonic(KeyEvent.VK_S);
		security.setSelected(false);
		security.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JCheckBox checkBox =(JCheckBox)e.getSource();
				intervener.setSecurityStatus(checkBox.isSelected());				
			}
		});
		add(security, c);
		
		JSlider securitySlide = new JSlider(JSlider.HORIZONTAL,min,max,initial);
		c.gridx =1;
		c.gridy =4;
		securitySlide.setMajorTickSpacing(1);
		securitySlide.setMinorTickSpacing(1);
		securitySlide.setPaintTicks(true);
		securitySlide.setPaintLabels(true);
		
		securitySlide.addChangeListener(new ChangeListener() {			
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
			    if (!source.getValueIsAdjusting()) {
			        intervener.setSecurity((double)source.getValue());			
			    }
			}
		});
		add(securitySlide,c);
//Personal Mobility		
		c.fill =GridBagConstraints.HORIZONTAL;
		c.gridx =0;
		c.gridy =5;
		JCheckBox mobility =new JCheckBox("Personality Mobility:");
		mobility.setMnemonic(KeyEvent.VK_M);
		mobility.setSelected(false);
		mobility.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JCheckBox checkBox =(JCheckBox)e.getSource();
				intervener.setMobilityStatus(checkBox.isSelected());				
			}
		});
		add(mobility, c);

		JSlider mobilitySlide = new JSlider(JSlider.HORIZONTAL,min,max,initial);
		c.gridx =1;
		c.gridy =5;
		mobilitySlide.setMajorTickSpacing(1);
		mobilitySlide.setMinorTickSpacing(1);
		mobilitySlide.setPaintTicks(true);
		mobilitySlide.setPaintLabels(true);
		
		mobilitySlide.addChangeListener(new ChangeListener() {			
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
			    if (!source.getValueIsAdjusting()) {
			        intervener.setMobility((double)source.getValue());			
			    }
			}
		});
		add(mobilitySlide,c);
// Convenience	
		c.fill =GridBagConstraints.HORIZONTAL;
		c.gridx =0;
		c.gridy =6;
		JCheckBox convenience =new JCheckBox("Convenience:");
		convenience.setMnemonic(KeyEvent.VK_C);
		convenience.setSelected(false);
		convenience.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JCheckBox checkBox =(JCheckBox)e.getSource();
				intervener.setConvenienceStatus(checkBox.isSelected());				
			}
		});
		add(convenience, c);
		
		JSlider convenienceSlide = new JSlider(JSlider.HORIZONTAL,min,max,initial);
		c.gridx =1;
		c.gridy =6;
		convenienceSlide.setMajorTickSpacing(1);
		convenienceSlide.setMinorTickSpacing(1);
		convenienceSlide.setPaintTicks(true);
		convenienceSlide.setPaintLabels(true);
		
		convenienceSlide.addChangeListener(new ChangeListener() {			
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
			    if (!source.getValueIsAdjusting()) {
			        intervener.setConvenience((double)source.getValue());			
			    }
			}
		});
		add(convenienceSlide,c);
//Comfort		
		c.fill =GridBagConstraints.HORIZONTAL;
		c.gridx =0;
		c.gridy =7;
		JCheckBox comfort =new JCheckBox("Comfort:");
		comfort.setMnemonic(KeyEvent.VK_F);
		comfort.setSelected(false);
		comfort.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JCheckBox checkBox =(JCheckBox)e.getSource();
				intervener.setComfortStatus(checkBox.isSelected());				
			}
		});
		add(comfort, c);
		
		JSlider comfortSlide = new JSlider(JSlider.HORIZONTAL,min,max,initial);
		c.gridx =1;
		c.gridy =7;
		comfortSlide.setMajorTickSpacing(1);
		comfortSlide.setMinorTickSpacing(1);
		comfortSlide.setPaintTicks(true);
		comfortSlide.setPaintLabels(true);
		
		comfortSlide.addChangeListener(new ChangeListener() {			
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
			    if (!source.getValueIsAdjusting()) {
			        intervener.setConvenience((double)source.getValue());			
			    }
			}
		});
		add(comfortSlide,c);

		JButton cyclistInterventionButton = new JButton("Cyclists'Interventions");
		c.gridx=0;
		c.gridy=8;
		c.weightx =0.5;
		c.weighty = 0.5;
		cyclistInterventionButton.addActionListener( new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				intervener.applyCyclistIntervention();
			}			
		});
		add(cyclistInterventionButton,c);
/////		
		JButton carUserInterventionButton = new JButton("Car Users'Interventions");
		c.gridx=1;
		c.gridy=8;		
		carUserInterventionButton.addActionListener( new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				intervener.applyPrivateUsersIntervention();
			}			
		});
		add(carUserInterventionButton,c);
			
////
		JButton publicTransInterventionButton = new JButton("Public Trans Users'Interventions");
		c.gridx=0;
		c.gridy=9;
		publicTransInterventionButton.addActionListener( new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					intervener.applyPublicTransIntervention();
			}			
		});
		add(publicTransInterventionButton,c);
	////
		JButton pedestrianInterventionButton = new JButton("Pedestrians'Interventions");
		c.gridx=1;
		c.gridy=9;
		pedestrianInterventionButton.addActionListener( new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				intervener.applyPedestrianIntervention();
			}			
		});
		add(pedestrianInterventionButton,c);
	///	
		JButton generalInterventionButton = new JButton("All Passengers'Interventions");
		c.gridx=0;
		c.gridy=10;	
		generalInterventionButton.addActionListener( new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
			intervener.applyAllModesIntervention();
			}			
		});
		add(generalInterventionButton,c);
////
		
////
		JButton resetButton = new JButton("Reset Button");
		c.gridx=1;
		c.gridy=10;	
		resetButton.addActionListener( new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				reliability.setSelected(false);	
				journey.setSelected(false);	
				security.setSelected(false);
				comfort.setSelected(false);
				mobility.setSelected(false);
				convenience.setSelected(false);
				costs.setSelected(false);
				reliabilitySlide.setValue(initial);
				journeyTimeSlide.setValue(initial);
				securitySlide.setValue(initial);
				comfortSlide.setValue(initial);
				mobilitySlide.setValue(initial);
				convenienceSlide.setValue(initial);
				costsSlide.setValue(initial);
				intervener.reset();
			
			}
			
		});
		add(resetButton,c);
		

	}
}
